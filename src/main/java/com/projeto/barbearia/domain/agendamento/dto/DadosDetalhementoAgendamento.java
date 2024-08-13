package com.projeto.barbearia.domain.agendamento.dto;

import com.projeto.barbearia.domain.agendamento.Agendamento;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosDetalhementoAgendamento(Long id,Long idCliente, Long idFuncionario, Long idServicco, LocalDateTime data) {
    public DadosDetalhementoAgendamento(Agendamento agendamento) {
        this(agendamento.getId(), agendamento.getCliente().getId(),agendamento.getFuncionario().getId(),agendamento.getServico().getId(),agendamento.getData());
    }
}
