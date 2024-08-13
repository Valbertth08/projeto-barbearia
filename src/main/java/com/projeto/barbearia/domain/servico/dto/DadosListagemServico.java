package com.projeto.barbearia.domain.servico.dto;

import com.projeto.barbearia.domain.servico.Servico;

public record DadosListagemServico(Long id, String nome, String descricao, Double preco) {
    public DadosListagemServico(Servico servico) {
        this(servico.getId(), servico.getNome(), servico.getDescricao(), servico.getPreco());
    }

}
