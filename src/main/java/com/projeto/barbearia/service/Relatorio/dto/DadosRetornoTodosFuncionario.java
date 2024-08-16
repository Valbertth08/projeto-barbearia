package com.projeto.barbearia.service.Relatorio.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosRetornoTodosFuncionario(
        @NotNull
        Double valor,
        @NotNull
        List<DadosRetornoFuncionario> funcionarios

) {
}
