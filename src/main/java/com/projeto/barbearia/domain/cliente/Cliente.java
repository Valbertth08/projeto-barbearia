package com.projeto.barbearia.domain.cliente;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projeto.barbearia.domain.agendamento.Agendamento;
import com.projeto.barbearia.domain.cliente.dto.DadosCadastrarCliente;
import com.projeto.barbearia.domain.endereco.Endereco;
import com.projeto.barbearia.domain.usuario.Role;
import com.projeto.barbearia.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "tb_cliente")
@Getter
@Setter
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
public class Cliente extends Usuario {
    private String nome;
    private String telefone;
    private LocalDate data_aniversario;
    @Embedded
    private Endereco endereco;
    @OneToMany(mappedBy = "cliente")
    private List<Agendamento> agendamentos = new ArrayList<>();
    private static BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    public Cliente(DadosCadastrarCliente dados){
        super(dados.login(), passwordEncoder.encode(dados.senha()),Role.USER);
        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.data_aniversario = dados.data_aniversario();
        this.endereco = dados.endereco();
    }


}
