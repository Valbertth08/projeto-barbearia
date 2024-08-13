package com.projeto.barbearia.domain.pagamento.dto;

import com.projeto.barbearia.domain.pagamento.FormaPagamento;
import com.projeto.barbearia.domain.pagamento.Pagamento;

import java.time.LocalDateTime;

public record DadosDetalhamentoPagamento(Long id, Long agendamento, Double valor, FormaPagamento formaPagamento, LocalDateTime data) {
    public DadosDetalhamentoPagamento(Pagamento pagamento){
        this(pagamento.getId(),pagamento.getAgendamento().getId(),pagamento.getValor(),pagamento.getForma_pagamento(),pagamento.getData());
    }
}
