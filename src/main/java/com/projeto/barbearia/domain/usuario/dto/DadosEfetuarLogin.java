package com.projeto.barbearia.domain.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosEfetuarLogin(
        @NotBlank
        String login,
        @NotBlank
        String senha
) {
}
