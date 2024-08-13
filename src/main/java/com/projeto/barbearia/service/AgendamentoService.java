package com.projeto.barbearia.service;

import com.projeto.barbearia.domain.agendamento.Agendamento;
import com.projeto.barbearia.domain.agendamento.dto.DadosCancelarAgendamento;
import com.projeto.barbearia.domain.agendamento.dto.DadosDetalhementoAgendamento;
import com.projeto.barbearia.domain.agendamento.dto.DadosFazerAgendamento;
import com.projeto.barbearia.domain.agendamento.validacoes.ValidacaoExpection;
import com.projeto.barbearia.domain.agendamento.validacoes.agendamento.ValidarAgendamento;
import com.projeto.barbearia.domain.agendamento.validacoes.cancelamento.ValidarCancelamento;
import com.projeto.barbearia.domain.pagamento.Pagamento;
import com.projeto.barbearia.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class AgendamentoService {
    @Autowired
    private AgendamentoRepository agendamentoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private ServicoRepository servicoRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    List<ValidarAgendamento> validadores;
    @Autowired
    List<ValidarCancelamento> validarCancelamento;

    public ResponseEntity agendar(DadosFazerAgendamento dados, UriComponentsBuilder builder){

        if(!funcionarioRepository.existsById(dados.funcionario())){
            throw new EntityNotFoundException("Funcionario não encontrado");
        }
        if (!clienteRepository.existsById(dados.cliente())){
            throw new EntityNotFoundException("Cliente não encontrado");
        }
        if (!servicoRepository.existsById(dados.servico())){
            throw new EntityNotFoundException("Servico indisponivel");
        }
        var cliente=clienteRepository.getReferenceById(dados.cliente());
        var funcionario=funcionarioRepository.getReferenceById(dados.funcionario());
        var servico=servicoRepository.getReferenceById(dados.servico());

        validadores.forEach(validadores-> validadores.validar(dados));

        var agendamento = new Agendamento(cliente, funcionario, servico, dados.data());
        agendamento = agendamentoRepository.save(agendamento);
        efetuarPagamentoComAntecedencia(dados,agendamento);

        var uri= builder.path("/agendar/{id}").buildAndExpand(agendamento.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhementoAgendamento(agendamento));
    }
    private void efetuarPagamentoComAntecedencia(DadosFazerAgendamento dados, Agendamento agendamento){
        if(dados.pagamento()!=null && dados.formaPagamento()!=null){
            var valor= agendamento.getServico().getPreco();
            var data= agendamento.getData();
            var formaPagamento=dados.formaPagamento();
            pagamentoRepository.save(new Pagamento(agendamento,valor,formaPagamento,data));
        }

    }
    public void CancelarAgendamento(DadosCancelarAgendamento dados){
        if(!agendamentoRepository.existsById(dados.idAgendamento())) {
            throw new ValidacaoExpection("Agendamento não encontrado");
        }
        validarCancelamento.forEach(validarCancelamento ->validarCancelamento.validar(dados) );
        agendamentoRepository.deleteById(dados.idAgendamento());
    }


}
