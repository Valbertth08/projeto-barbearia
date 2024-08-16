package com.projeto.barbearia.repository;

import com.projeto.barbearia.domain.funcionario.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {

    @Query("""
            select count(a) > 0 from Agendamento a
            where a.data= :dataAgendamento
            and a.funcionario.id= :id
            """)
    Boolean validarFuncioeAgendamentoMesmoDiaHora(LocalDateTime dataAgendamento, Long id);
    @Query("""
              SELECT SUM(P.valor)
                    FROM Agendamento A
                    INNER JOIN Pagamento P ON A.id = P.agendamento.id
                    INNER JOIN Funcionario F ON F.id = A.funcionario.id
                    WHERE  F.id= :id
            """)
    Double ValorTotalDeServicoFeitoFuncionario(Long id);

    @Query("""
            SELECT SUM(P.valor)
            FROM Funcionario  F INNER JOIN  Agendamento  A ON F.id = A.funcionario.id
            INNER JOIN  Pagamento  P ON A.id = P.agendamento.id where F.id= :id and  DATE(A.data)= :data
            """)
    Double ValorTotalDeServicoFeitoFuncionarioData(Long id, LocalDate data);
    @Query("""
            SELECT SUM(P.valor)
                    FROM Agendamento A
                    INNER JOIN Pagamento P ON A.id = P.agendamento.id
                    INNER JOIN Funcionario F ON F.id = A.funcionario.id
                    WHERE  F.id= :id AND DATE(A.data) BETWEEN :dataInicio AND :dataFinal
            """)
    Double ValorTotalDeServicoFeitoFuncionarioPeriodo(Long id, LocalDate dataInicio,LocalDate dataFinal);
    @Query("""
          SELECT SUM(P.valor) FROM Agendamento A INNER JOIN  Pagamento  P ON A.id = P.agendamento.id
            """)
    Double ValorTotalDeServicoFeitoTodosFuncionario();

    @Query("""
            SELECT SUM(P.valor) FROM  Agendamento A INNER JOIN  Pagamento  P ON A.id = P.agendamento.id where DATE(A.data)= :data
            """)
    Double ValorTotalDeSericoFeitoTodosFuncionariosData(LocalDate data);
    @Query("""
            SELECT SUM(P.valor)
                    FROM Agendamento A
                    INNER JOIN Pagamento P ON A.id = P.agendamento.id
                    INNER JOIN Funcionario F ON F.id = A.funcionario.id
                    WHERE DATE(A.data) BETWEEN :dataInicio AND :dataFinal
            """)
    Double ValorTotalDeServicoFeitoTodosFuncionarioPeriodo(LocalDate dataInicio,LocalDate dataFinal);
}
