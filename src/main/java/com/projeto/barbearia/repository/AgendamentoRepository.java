package com.projeto.barbearia.repository;

import com.projeto.barbearia.domain.agendamento.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
