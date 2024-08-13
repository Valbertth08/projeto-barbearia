package com.projeto.barbearia.domain.funcionario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projeto.barbearia.domain.agendamento.Agendamento;
import com.projeto.barbearia.domain.endereco.Endereco;
import com.projeto.barbearia.domain.funcionario.dto.DadosCadastrarFuncionario;
import com.projeto.barbearia.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_funcionario")
@Getter
@Setter
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Funcionario extends Usuario {
    private String nome;
    private String telefone;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;

    @OneToMany(mappedBy = "funcionario")
    private List<Agendamento> agendamentos = new ArrayList<>();
    public Funcionario(DadosCadastrarFuncionario dados) {
        super(dados.login(), dados.senha(), dados.role());
        this.nome=dados.nome();
        this.telefone=dados.telefone();
        this.especialidade=dados.especialidade();
        this.endereco=new Endereco(dados.endereco());
    }
}
