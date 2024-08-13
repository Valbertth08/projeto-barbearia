package com.projeto.barbearia.repository;

import com.projeto.barbearia.domain.pagamento.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
