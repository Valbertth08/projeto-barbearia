package com.projeto.barbearia.domain.servico.dto;

import com.projeto.barbearia.domain.servico.Servico;
import jakarta.validation.constraints.NotNull;

public record DadosDetalhamentoServico(Long id, String nome, String descricao, Double preco) {
    public DadosDetalhamentoServico(Servico servico) {
        this(servico.getId(), servico.getNome(), servico.getDescricao(), servico.getPreco());
    }
}
