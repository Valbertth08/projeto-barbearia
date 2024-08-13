package com.projeto.barbearia.domain.agendamento.validacoes.agendamento;

import com.projeto.barbearia.domain.agendamento.dto.DadosFazerAgendamento;
import com.projeto.barbearia.domain.agendamento.validacoes.ValidacaoExpection;
import com.projeto.barbearia.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class FazerUmUnicoAgendamentoFuncionarioDataHoraDiferente implements ValidarAgendamento {

    @Autowired
    private FuncionarioRepository repository;
    @Override
    public void validar(DadosFazerAgendamento dados) {
        LocalDateTime dataAgendamento= dados.data();
        var dataAgendamentoNoMesmoDiaHora= repository.validarFuncioeAgendamentoMesmoDiaHora(dataAgendamento, dados.funcionario());
        if(dataAgendamentoNoMesmoDiaHora){
            throw new ValidacaoExpection("Ja existe uma consulta para o funcionario na data e hora informada");
        }
    }
}
