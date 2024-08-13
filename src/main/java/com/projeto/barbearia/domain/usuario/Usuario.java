package com.projeto.barbearia.domain.usuario;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Usuario")
@Table(name = "tb_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String login;
    private String senha;
    @Enumerated(EnumType.STRING)
    private Role role;
    public Usuario(String login, String senha, Role role) {
        this.login=login;
        this.senha= senha;
        this.role=role;
    }
}
