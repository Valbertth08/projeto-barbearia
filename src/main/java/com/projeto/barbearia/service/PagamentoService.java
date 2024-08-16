package com.projeto.barbearia.service;

import com.projeto.barbearia.domain.agendamento.enums.StatusAgendamento;
import com.projeto.barbearia.domain.pagamento.Pagamento;
import com.projeto.barbearia.domain.pagamento.dto.DadosCadastarPagamento;
import com.projeto.barbearia.domain.pagamento.dto.DadosDetalhamentoPagamento;
import com.projeto.barbearia.repository.AgendamentoRepository;
import com.projeto.barbearia.repository.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private AgendamentoRepository agendamentoRepository;
    public ResponseEntity inserirPagamento(DadosCadastarPagamento dados, UriComponentsBuilder builder){
        if(!agendamentoRepository.existsById(dados.idAgendamento())){
            throw new EntityNotFoundException("Agendamento inexistente");
        }
        var agendamento= agendamentoRepository.getReferenceById(dados.idAgendamento());
        if(agendamento.getStatus()== StatusAgendamento.PENDENTE){
            agendamento.setStatus(StatusAgendamento.PAGO);
            var pagamento = pagamentoRepository.save(new Pagamento(agendamento,agendamento.getServico().getPreco(),dados.formaPagamento(), LocalDateTime.now()));
            var uri= builder.path("/pagamento/{id}").buildAndExpand(pagamento.getId()).toUri();
            return ResponseEntity.created(uri).body(new DadosDetalhamentoPagamento(pagamento));
        }
        throw new EntityNotFoundException("Esse agendamento ja foi pago");
    }
    public void cancelarPagamento(Long id ){
        if(!pagamentoRepository.existsById(id)){
            throw new EntityNotFoundException("Pagamento não encontrado");
        }
        var pagamento = pagamentoRepository.getReferenceById(id);
        pagamento.getAgendamento().setStatus(StatusAgendamento.PENDENTE);
        pagamento.getAgendamento().setPagamento(null);
        pagamentoRepository.deleteById(id);
    }
    public Page<DadosDetalhamentoPagamento> listarPagamento(Pageable pageable) {
            var agendamentos= pagamentoRepository.findAll(pageable).map(DadosDetalhamentoPagamento::new);
            return  agendamentos;
    }
    public DadosDetalhamentoPagamento listarPagamentoId(Long id) {
        if(!pagamentoRepository.existsById(id)){
            throw new EntityNotFoundException("Pagamento não encontrado");
        }
        var pagamento = pagamentoRepository.getReferenceById(id);
        return new DadosDetalhamentoPagamento(pagamento);
    }
}
