package com.projeto.barbearia.domain.funcionario.dto;

import com.projeto.barbearia.domain.endereco.dto.DadosAtualizarEndereco;
import com.projeto.barbearia.domain.endereco.dto.DadosEndereco;
import com.projeto.barbearia.domain.funcionario.Especialidade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarFuncionario(
        @NotNull
         Long id,
        String nome,
        Especialidade especialidade,
        String telefone,
       @Valid DadosAtualizarEndereco endereco) {
}
