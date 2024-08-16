package com.projeto.barbearia.domain.agendamento.validacoes.cancelamento;

import com.projeto.barbearia.domain.agendamento.dto.DadosCancelarAgendamento;
import com.projeto.barbearia.domain.agendamento.validacoes.ValidacaoExpection;
import com.projeto.barbearia.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidarCancelamentoAgendamento implements ValidarCancelamento{
    @Autowired
    private AgendamentoRepository agendamentoRepository;
    @Override
    public void validar(DadosCancelarAgendamento dados) {
        LocalDateTime dataCancelamento= LocalDateTime.now();
        var agendamento= agendamentoRepository.getReferenceById(dados.idAgendamento());
        var horarioMinimo= Duration.between(dataCancelamento,agendamento.getData()).toMinutes();
        if(horarioMinimo<30){
            throw new ValidacaoExpection("O agendamento só pode ser cancelado com  30 minutos de antencedencia");
        }
    }
}
