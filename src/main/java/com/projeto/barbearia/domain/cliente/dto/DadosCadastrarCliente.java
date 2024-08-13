package com.projeto.barbearia.domain.cliente.dto;

import com.projeto.barbearia.domain.endereco.Endereco;
import com.projeto.barbearia.domain.usuario.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosCadastrarCliente(
        @NotBlank
        String login,
        @NotBlank
         String senha,
        @NotNull
        Role role,
        @NotBlank
        String telefone,
        @NotBlank
        String nome,
        @NotNull
        LocalDate data_aniversario,
        @NotNull
        Endereco endereco

) {
}
