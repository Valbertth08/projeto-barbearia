package com.projeto.barbearia.domain.pagamento;


import com.projeto.barbearia.domain.agendamento.Agendamento;
import com.projeto.barbearia.domain.cliente.Cliente;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "Pagamento")
@Table(name = "tb_pagamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Agendamento agendamento;
    private Double valor;
    private LocalDateTime data;
    @Enumerated(EnumType.STRING)
    private FormaPagamento forma_pagamento;
    public Pagamento(Agendamento agendamento, Double valor, FormaPagamento formaPagamento, LocalDateTime data) {
        this.agendamento=agendamento;
        this.valor=valor;
        this.forma_pagamento=formaPagamento;
        this.data= data;
    }
}
