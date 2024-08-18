package com.projeto.barbearia.domain.funcionario.dto;

import com.projeto.barbearia.domain.endereco.dto.DadosEndereco;
import com.projeto.barbearia.domain.funcionario.Especialidade;
import com.projeto.barbearia.domain.usuario.Role;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastrarFuncionario(
        @NotBlank
        String login,
        @NotBlank
        String senha,
        @NotBlank
        String nome,
        @NotBlank
        String telefone,
        @NotNull
        Especialidade especialidade,
        @NotNull
        @Valid
        DadosEndereco endereco
) {
}
