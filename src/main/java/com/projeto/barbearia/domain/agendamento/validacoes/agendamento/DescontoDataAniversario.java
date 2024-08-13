package com.projeto.barbearia.domain.agendamento.validacoes.agendamento;

import com.projeto.barbearia.domain.agendamento.dto.DadosFazerAgendamento;
import com.projeto.barbearia.repository.ClienteRepository;
import com.projeto.barbearia.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DescontoDataAniversario implements ValidarAgendamento {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ServicoRepository servicoRepository;

    @Override
    public void validar(DadosFazerAgendamento dados) {
        var cliente= clienteRepository.getReferenceById(dados.cliente());
        var servico=servicoRepository.getReferenceById(dados.servico());
        LocalDate dataAgendamento= dados.data().toLocalDate();
        LocalDate dataAniversario=cliente.getData_aniversario().withYear(LocalDate.now().getYear());
        if(dataAniversario.equals(dataAgendamento)){
            var valor= servico.getPreco();
            var desconto =(valor/100)*10;
            servico.setPreco(valor-desconto);
        }

    }
}
