package com.projeto.barbearia.infra.seguranca;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    @Profile("test")
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf
                        .ignoringRequestMatchers(toH2Console()).disable())
                        .authorizeHttpRequests(auth -> auth
                        .requestMatchers(toH2Console()).permitAll())
                        .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        http.csrf(c->c.disable()).sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(aq->aq.anyRequest().permitAll());
        return http.build();
    }
    @Bean
    @Profile("main")
    public SecurityFilterChain filterChainMain(HttpSecurity http) throws Exception {
        return  http.csrf(csrf ->csrf.disable()).sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req->{
                    req.requestMatchers("/usuario/login","/cliente/registrar").permitAll();//estou dando permissão de acesso sem um token, somente para login
                    req.requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**").permitAll();
                    req.requestMatchers(HttpMethod.PUT,"/agendamento/atualizar/**","/cliente/atualizar/**").hasRole("USER");
                    req.requestMatchers(HttpMethod.DELETE,"/agendamento/deletar/**","/cliente/deletar/**").hasRole("USER");
                    req.requestMatchers(HttpMethod.POST,"/agendamento/registrar").hasRole("USER");
                    req.requestMatchers("/funcionario/atualizar/**").hasRole("FUNC");
                    req.requestMatchers("/relatorio/**","/pagamento/**","/servico/**","/funcionario/**","/agendamento/**","/cliente/**").hasRole("ADMIN");
                    req.anyRequest().authenticated();//indico   que em qualquer outra requisição a pessoa precisa esta autenticada//estou indicando que quero chamar meu filtro personalizado primeiro do padrão de segurança.
                })
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }
}
