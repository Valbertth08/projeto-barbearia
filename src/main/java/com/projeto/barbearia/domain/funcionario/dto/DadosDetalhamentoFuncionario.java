package com.projeto.barbearia.domain.funcionario.dto;

import com.projeto.barbearia.domain.endereco.Endereco;
import com.projeto.barbearia.domain.funcionario.Especialidade;
import com.projeto.barbearia.domain.funcionario.Funcionario;
import com.projeto.barbearia.domain.usuario.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosDetalhamentoFuncionario(String login, String senha, Role role, String nome, String telefone, Especialidade especialidade, Endereco endereco) {
   public DadosDetalhamentoFuncionario(Funcionario dados) {
        this(dados.getLogin(), dados.getSenha(), dados.getRole(), dados.getNome(), dados.getTelefone(), dados.getEspecialidade(), dados.getEndereco());
    }
}
