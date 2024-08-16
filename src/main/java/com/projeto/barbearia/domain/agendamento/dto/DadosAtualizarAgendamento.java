package com.projeto.barbearia.domain.agendamento.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DadosAtualizarAgendamento(

        Long id,
        Long idFuncionario,
        Long idServico,
        LocalDateTime data

        ) {
}
