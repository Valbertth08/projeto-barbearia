package com.projeto.barbearia.repository;

import com.projeto.barbearia.domain.cliente.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    @Query("""
            select count(a) > 0 from Agendamento a
            where a.data= :dataAgendamento
            and a.cliente.id= :id
            """)
    Boolean validarClienteAgendamentoMesmoDia(LocalDateTime dataAgendamento, Long id);
}
