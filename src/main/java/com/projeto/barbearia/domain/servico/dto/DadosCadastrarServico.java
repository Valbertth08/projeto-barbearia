package com.projeto.barbearia.domain.servico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastrarServico(
        @NotBlank
        String nome,
        @NotBlank
        String descricao,
        @NotNull
        Double preco
) {
}
