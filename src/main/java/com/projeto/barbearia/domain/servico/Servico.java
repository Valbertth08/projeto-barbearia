package com.projeto.barbearia.domain.servico;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projeto.barbearia.domain.agendamento.Agendamento;
import com.projeto.barbearia.domain.servico.dto.DadosCadastrarServico;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Servico")
@Table(name = "tb_servico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String nome;
    private String descricao;
    private Double preco;

    @OneToMany(mappedBy = "servico")
    private List<Agendamento> agendamentos = new ArrayList<>();

    public Servico(DadosCadastrarServico dados) {
        this.nome=dados.nome();
        this.descricao=dados.descricao();
        this.preco=dados.preco();

    }
}
