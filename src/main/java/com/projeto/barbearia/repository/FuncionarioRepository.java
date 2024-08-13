package com.projeto.barbearia.repository;

import com.projeto.barbearia.domain.funcionario.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {

    @Query("""
            select count(a) > 0 from Agendamento a
            where a.data= :dataAgendamento
            and a.funcionario.id= :id
            """)
    Boolean validarFuncioeAgendamentoMesmoDiaHora(LocalDateTime dataAgendamento, Long id);

}
