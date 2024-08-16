package com.projeto.barbearia.service.Relatorio.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosRelatorioFuncionario(
        @NotNull
        Long id,
        LocalDate dataIincio,
        LocalDate dataFinal
) {
}
