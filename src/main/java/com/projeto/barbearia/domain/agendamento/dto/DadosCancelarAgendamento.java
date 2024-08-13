package com.projeto.barbearia.domain.agendamento.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCancelarAgendamento(
        @NotNull
        Long idAgendamento

) {
}
