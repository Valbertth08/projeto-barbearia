package com.projeto.barbearia.infra.seguranca;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.projeto.barbearia.domain.usuario.Usuario;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    public String gerarToken(Usuario usuario){
        try {
            var algortimo= Algorithm.HMAC256("12345");
            return JWT.create()
                    .withIssuer("API barberia")
                    .withSubject(usuario.getLogin())
                    .withClaim("id",usuario.getId())
                    .withExpiresAt(dataExpiracaoToken())
                    .sign(algortimo);
        }catch (JWTCreationException exception){
            throw new RuntimeException("erro ao gerar o token",exception);
        }

    }
    public String pegarUsuarioDoToken(String token){
        try{
            var algoritimo= Algorithm.HMAC256("12345");
            return JWT.require(algoritimo)
                    .withIssuer("API barberia")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException exception){
            throw new RuntimeException("Token invalido ou expirado");
        }

    }
    private Instant dataExpiracaoToken() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));

    }
}
