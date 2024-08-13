package com.projeto.barbearia.domain.cliente.dto;

import com.projeto.barbearia.domain.endereco.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

public record DadosAtualizarCliente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        LocalDate data,
        @Valid Endereco endereco) {
}
