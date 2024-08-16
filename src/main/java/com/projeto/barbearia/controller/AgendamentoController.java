package com.projeto.barbearia.controller;

import com.projeto.barbearia.domain.agendamento.dto.DadosAtualizarAgendamento;
import com.projeto.barbearia.domain.agendamento.dto.DadosCancelarAgendamento;
import com.projeto.barbearia.domain.agendamento.dto.DadosDetalhementoAgendamento;
import com.projeto.barbearia.domain.agendamento.dto.DadosFazerAgendamento;
import com.projeto.barbearia.domain.endereco.dto.DadosAtualizarEndereco;
import com.projeto.barbearia.service.AgendamentoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity deletarAgendamento(@RequestBody @Valid DadosCancelarAgendamento dados){
        service.CancelarAgendamento(dados);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity atualizarAgendamento(@RequestBody @Valid DadosAtualizarAgendamento dados){
        return ResponseEntity.ok().body(service.atualizarAgendamento(dados));
    }
    @GetMapping("/listar")
    public ResponseEntity<Page<DadosDetalhementoAgendamento>> listarTodosAgendamento(Pageable pageable){
        return ResponseEntity.ok().body(service.listarTodosAgendamento(pageable));
    }
    @GetMapping("/listar/{id}")
    public ResponseEntity listarAgendamentId(@PathVariable Long id){
        return ResponseEntity.ok().body(service.listarAgendamentoId(id));
    }
}
