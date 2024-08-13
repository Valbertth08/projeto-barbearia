package com.projeto.barbearia.repository;

import com.projeto.barbearia.domain.servico.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.security.Provider;

public interface ServicoRepository extends JpaRepository<Servico,Long> {
}
