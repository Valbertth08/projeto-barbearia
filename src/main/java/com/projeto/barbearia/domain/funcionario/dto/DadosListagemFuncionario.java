package com.projeto.barbearia.domain.funcionario.dto;

import com.projeto.barbearia.domain.endereco.Endereco;
import com.projeto.barbearia.domain.funcionario.Especialidade;
import com.projeto.barbearia.domain.funcionario.Funcionario;

public record DadosListagemFuncionario(Long id, String login, String senha, String nome,String telegone, Especialidade especialidade, Endereco endereco) {
    public DadosListagemFuncionario(Funcionario funcionario){
        this(funcionario.getId(), funcionario.getLogin(), funcionario.getSenha(), funcionario.getNome(),funcionario.getTelefone(),funcionario.getEspecialidade(),funcionario.getEndereco());
    }
}
