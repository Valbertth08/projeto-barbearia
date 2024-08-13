package com.projeto.barbearia.domain.endereco.dto;

import jakarta.validation.constraints.Pattern;

public record DadosAtualizarEndereco(
        String bairro,
        @Pattern(regexp = "\\d{8}")
        String cep,
        String numero,
        String complemento,
        String cidade

) {
}
