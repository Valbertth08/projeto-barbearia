package com.projeto.barbearia.domain.usuario;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "Usuario")
@Table(name = "tb_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario  implements UserDetails{
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
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role== Role.ADMIN){
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"),new SimpleGrantedAuthority("ROLE_FUNC"));
        }
        if(this.role==Role.FUNC){
            return List.of(new SimpleGrantedAuthority("ROLE_FUNC"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
    @Override
    public String getPassword() {
        return senha;
    }
    @Override
    public String getUsername() {
        return login;
    }

}
