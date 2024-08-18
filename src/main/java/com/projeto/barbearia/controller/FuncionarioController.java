package com.projeto.barbearia.controller;

import com.projeto.barbearia.domain.funcionario.dto.DadosAtualizarFuncionario;
import com.projeto.barbearia.domain.funcionario.dto.DadosCadastrarFuncionario;
import com.projeto.barbearia.domain.funcionario.dto.DadosListagemFuncionario;
import com.projeto.barbearia.service.FuncionarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/funcionario")
@SecurityRequirement(name = "bearer-key")
public class FuncionarioController {
    @Autowired
    private FuncionarioService service;

    @PostMapping("/registrar")
    @Transactional
    public ResponseEntity inserirFuncionario(@RequestBody @Valid DadosCadastrarFuncionario dados, UriComponentsBuilder builder){
        return service.inserirFuncionario(dados,builder);
    }
    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity atualizarFuncionario(@RequestBody  @Valid DadosAtualizarFuncionario dados){
        return ResponseEntity.ok().body(service.autalizarFuncionario(dados));
    }
    @GetMapping("/listar")
    public ResponseEntity<Page<DadosListagemFuncionario>> listarFuncionario(Pageable pageable){
        return ResponseEntity.ok().body(service.listarFuncionario(pageable));
    }
    @GetMapping("/listar/{id}")
    public ResponseEntity listarFuncionarioId(@PathVariable Long id){
        return ResponseEntity.ok().body(service.listarFuncionarioId(id));

    }
    @DeleteMapping("/deletar/{id}")
    @Transactional
    public ResponseEntity deletarFuncionarioId(Long id){
        service.deletarFuncionarioId(id);
        return  ResponseEntity.noContent().build();
    }
}
