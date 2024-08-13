package com.projeto.barbearia.controller;

import com.projeto.barbearia.domain.agendamento.dto.DadosCancelarAgendamento;
import com.projeto.barbearia.domain.agendamento.dto.DadosFazerAgendamento;
import com.projeto.barbearia.service.AgendamentoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    @Autowired
    private AgendamentoService service;

    @PostMapping("/registrar")
    @Transactional
    public ResponseEntity fazerAgendamento(@RequestBody @Valid DadosFazerAgendamento dados, UriComponentsBuilder builder){
                return service.agendar(dados,builder);
    }
    @DeleteMapping("/deletar")
    @Transactional
    public ResponseEntity deltarAgendamento(@RequestBody @Valid DadosCancelarAgendamento dados){
        service.CancelarAgendamento(dados);
        return ResponseEntity.noContent().build();
    }
}
