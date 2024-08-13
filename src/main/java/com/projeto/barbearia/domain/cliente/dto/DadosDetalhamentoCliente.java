package com.projeto.barbearia.domain.cliente.dto;

import com.projeto.barbearia.domain.cliente.Cliente;
import com.projeto.barbearia.domain.endereco.Endereco;
import com.projeto.barbearia.domain.usuario.Role;

import java.time.LocalDate;

public record DadosDetalhamentoCliente(Long id, String login, String senha, Role role,String nome, String telefone, LocalDate data, Endereco endereco) {
    public DadosDetalhamentoCliente(Cliente cliente){
        this(cliente.getId(), cliente.getLogin(),cliente.getSenha(), cliente.getRole(),cliente.getNome(),cliente.getTelefone(),cliente.getData_aniversario(),cliente.getEndereco());
    }
}
