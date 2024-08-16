package com.projeto.barbearia.domain.agendamento.validacoes.cancelamento;

import com.projeto.barbearia.domain.agendamento.dto.DadosCancelarAgendamento;
import com.projeto.barbearia.domain.agendamento.enums.StatusAgendamento;
import com.projeto.barbearia.repository.AgendamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarAgendamentoEstaDisponivel  implements ValidarCancelamento{

    @Autowired
    private AgendamentoRepository agendamentoRepository;
    @Override
    public void validar(DadosCancelarAgendamento dados) {
        var  agendamento = agendamentoRepository.getReferenceById(dados.idAgendamento());
        if(agendamento.getStatus()!= StatusAgendamento.PENDENTE){
            throw new EntityNotFoundException("Esse agendamento ja esta concluido e pago");
        }
    }
}
