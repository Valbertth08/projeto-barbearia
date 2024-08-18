package com.projeto.barbearia.configuration.profiles;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("main")
public class MainProfile implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Estou no perfil principal");
    }
}
