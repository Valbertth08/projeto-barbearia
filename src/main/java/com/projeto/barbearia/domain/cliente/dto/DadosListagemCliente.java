package com.projeto.barbearia.domain.cliente.dto;

import com.projeto.barbearia.domain.cliente.Cliente;
import com.projeto.barbearia.domain.endereco.Endereco;

import java.time.LocalDate;

public record DadosListagemCliente(Long id,String nome, String telefone, LocalDate date, Endereco endereco) {
    public DadosListagemCliente(Cliente cliente){
        this(cliente.getId(),cliente.getNome(),cliente.getTelefone(),cliente.getData_aniversario(),cliente.getEndereco());
    }


}
