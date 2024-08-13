package com.projeto.barbearia.domain.servico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarServico(
        @NotNull
        Long id,
        String nome,
        String descricao,

        Double preco
) {



}
