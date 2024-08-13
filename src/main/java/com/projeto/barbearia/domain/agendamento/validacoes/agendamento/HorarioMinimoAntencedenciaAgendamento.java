package com.projeto.barbearia.domain.agendamento.validacoes.agendamento;

import com.projeto.barbearia.domain.agendamento.dto.DadosFazerAgendamento;
import com.projeto.barbearia.domain.agendamento.validacoes.ValidacaoExpection;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class HorarioMinimoAntencedenciaAgendamento implements ValidarAgendamento {
    @Override
    public void validar(DadosFazerAgendamento dados) {
        LocalDateTime dataHoraAtual= LocalDateTime.now();
        LocalDateTime dataAgendamento=dados.data();
        var duration= Duration.between(dataHoraAtual,dataAgendamento);
        System.out.println(duration);
        if(duration.toMinutes()<30){
            throw new ValidacaoExpection("O agendamento deve ter antencencia minima de 30 minutos em relação ao horario desejado");
        }

    }
}
