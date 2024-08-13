package com.projeto.barbearia.infra.seguranca;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity

public class SecurityConfiguration {
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

        return  http.csrf(csrf ->csrf.disable()).sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();
    }
}
