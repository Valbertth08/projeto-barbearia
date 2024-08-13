package com.projeto.barbearia.domain.agendamento.validacoes.agendamento;

import com.projeto.barbearia.domain.agendamento.dto.DadosFazerAgendamento;
import com.projeto.barbearia.domain.agendamento.validacoes.ValidacaoExpection;
import com.projeto.barbearia.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FazerUmUnicoAgendamentoPorDiaCliente implements ValidarAgendamento{

    @Autowired
    private ClienteRepository repository;

    @Override
    public void validar(DadosFazerAgendamento dados) {
        LocalDateTime dataAgendamento= dados.data();
       var dataAgendamentoNoMesmoDia= repository.validarClienteAgendamentoMesmoDia(dataAgendamento, dados.cliente());
       if(dataAgendamentoNoMesmoDia){
          throw new ValidacaoExpection("Ja existe uma consulta para o cliente na data informada");
       }
    }
}
