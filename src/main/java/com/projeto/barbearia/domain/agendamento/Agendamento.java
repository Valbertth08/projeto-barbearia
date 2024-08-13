package com.projeto.barbearia.domain.agendamento;

import com.projeto.barbearia.domain.cliente.Cliente;
import com.projeto.barbearia.domain.funcionario.Funcionario;
import com.projeto.barbearia.domain.pagamento.Pagamento;
import com.projeto.barbearia.domain.servico.Servico;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Agendamento")
@Table(name = "tb_agendamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @JoinColumn(name = "id_cliente")
    @ManyToOne
    private Cliente cliente;

    @JoinColumn(name = "id_funcionario")
    @ManyToOne
    private Funcionario funcionario;

    @JoinColumn(name = "id_servico")
    @ManyToOne
    private Servico servico;

    @OneToOne(mappedBy = "agendamento",cascade = CascadeType.ALL,orphanRemoval = true)
    private Pagamento pagamento;

    private LocalDateTime data;

    public Agendamento(Cliente cliente, Funcionario funcionario, Servico servico, LocalDateTime data) {
        this.cliente=cliente;
        this.funcionario=funcionario;
        this.servico=servico;
        this.data=data;
    }

}
