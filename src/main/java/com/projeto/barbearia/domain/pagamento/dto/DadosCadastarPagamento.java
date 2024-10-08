package com.projeto.barbearia.domain.pagamento.dto;

import com.projeto.barbearia.domain.pagamento.FormaPagamento;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCadastarPagamento(
        @NotNull
        Long idAgendamento,
        @NotNull
        @Future
        LocalDateTime data,
        @NotNull
        FormaPagamento formaPagamento
) {
}
