package com.projeto.barbearia.domain.agendamento.dto;

import com.projeto.barbearia.domain.pagamento.FormaPagamento;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosFazerAgendamento(
        @NotNull
        Long cliente,
        @NotNull
        Long funcionario,
        @NotNull
        Long servico,
        @NotNull
        @Future
        LocalDateTime  data,
        Boolean pagamento,
        FormaPagamento formaPagamento

) {
}
