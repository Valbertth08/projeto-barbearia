package com.projeto.barbearia.controller;

import com.projeto.barbearia.domain.servico.dto.DadosAtualizarServico;
import com.projeto.barbearia.domain.servico.dto.DadosCadastrarServico;
import com.projeto.barbearia.domain.servico.dto.DadosListagemServico;
import com.projeto.barbearia.service.ServicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/servico")
public class ServicoController {
    @Autowired
    private ServicoService service;

    @PostMapping("/registrar")
    @Transactional
    public ResponseEntity inserirServico(@RequestBody @Valid DadosCadastrarServico dados, UriComponentsBuilder builder){
        return service.inserirServico(dados,builder);
    }
    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity atualizarServico( @RequestBody @Valid DadosAtualizarServico dados){
        return  ResponseEntity.ok().body(service.atualizarServico(dados));
    }
    @DeleteMapping("/deletar/{id}")
    @Transactional
    public ResponseEntity deletarServicoId( @PathVariable Long id){
        service.deletarServicoId(id);
        return ResponseEntity.noContent().build();

    }
    @GetMapping("/listar")
    public ResponseEntity<Page<DadosListagemServico>> listarServico(Pageable pageable){
        return ResponseEntity.ok().body(service.listarServico(pageable));
    }
    @GetMapping("/listar/{id}")
    public ResponseEntity listarServicoId(@PathVariable Long id){
        return ResponseEntity.ok().body(service.listarServicoId(id));
    }

}
