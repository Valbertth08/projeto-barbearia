package com.projeto.barbearia.configuration.profiles;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestProfile  implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Estou no perfil de teste");
    }
}
