package com.projeto.barbearia.service;

import com.projeto.barbearia.domain.agendamento.Agendamento;
import com.projeto.barbearia.domain.agendamento.dto.DadosAtualizarAgendamento;
import com.projeto.barbearia.domain.agendamento.dto.DadosCancelarAgendamento;
import com.projeto.barbearia.domain.agendamento.dto.DadosDetalhementoAgendamento;
import com.projeto.barbearia.domain.agendamento.dto.DadosFazerAgendamento;
import com.projeto.barbearia.domain.agendamento.enums.StatusAgendamento;
import com.projeto.barbearia.domain.agendamento.validacoes.ValidacaoExpection;
import com.projeto.barbearia.domain.agendamento.validacoes.agendamento.ValidarAgendamento;
import com.projeto.barbearia.domain.agendamento.validacoes.cancelamento.ValidarCancelamento;
import com.projeto.barbearia.domain.funcionario.Funcionario;
import com.projeto.barbearia.domain.pagamento.Pagamento;
import com.projeto.barbearia.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

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
    public DadosDetalhementoAgendamento atualizarAgendamento(DadosAtualizarAgendamento dados){
        if(!agendamentoRepository.existsById(dados.id())){
            throw new EntityNotFoundException("Agendamento não encontrado ");
        }
        var agendamento= agendamentoRepository.getReferenceById(dados.id());
        if(agendamento.getStatus()!= StatusAgendamento.PENDENTE){
            throw new EntityNotFoundException("Esse agendamento ja esta concluido e pago");
        }
        if (dados.idFuncionario() != null) {
            var funcionario = funcionarioRepository.findById(dados.idFuncionario()).orElseThrow(() -> new EntityNotFoundException("Funcionario não encontrado"));
            agendamento.setFuncionario(funcionario);
        }
        if (dados.idServico() != null) {
            var servico = servicoRepository.findById(dados.idServico()).orElseThrow(() -> new EntityNotFoundException("Servico não encontrado"));
            agendamento.setServico(servico);
        }
        if (dados.data()!= null){
            agendamento.setData(dados.data());
        }
        return new DadosDetalhementoAgendamento(agendamento);
    }

    public Page<DadosDetalhementoAgendamento> listarTodosAgendamento(Pageable pageable){
        var lista = agendamentoRepository.findAll(pageable).map(DadosDetalhementoAgendamento::new);
        return  lista;
    }


    public DadosDetalhementoAgendamento listarAgendamentoId( Long id) {
        if(!agendamentoRepository.existsById(id)){
            throw new ValidacaoExpection("Agendamento não encontrado");
        }
        var agendamento= agendamentoRepository.getReferenceById(id);
        return  new DadosDetalhementoAgendamento(agendamento);
    }
}
