package com.projeto.barbearia.domain.agendamento.validacoes.agendamento;

import com.projeto.barbearia.domain.agendamento.dto.DadosFazerAgendamento;
import com.projeto.barbearia.domain.agendamento.validacoes.ValidacaoExpection;
import org.springframework.stereotype.Component;

import java.time.*;

@Component
public class VerificarHorarioDoAgendamentoCliente implements ValidarAgendamento {
    @Override
    public void validar(DadosFazerAgendamento dados) {

        var horaAgendamento= dados.data().toLocalTime();
        var horarioAbertura= LocalTime.of(7,0);
        var horarioFechamento= LocalTime.of(18,0);

        boolean foraDoHorario = horaAgendamento.isBefore(horarioAbertura) || horaAgendamento.isAfter(horarioFechamento);
        var dataDiaDeDomingo= dados.data().getDayOfWeek().equals(DayOfWeek.SUNDAY);
        if(foraDoHorario||dataDiaDeDomingo){
                throw new ValidacaoExpection("Horario fora do funcionamento da barbearia");
        }

    }
}
