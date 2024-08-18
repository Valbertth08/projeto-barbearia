package com.projeto.barbearia.controller;


import com.projeto.barbearia.service.Relatorio.RelatorioService;
import com.projeto.barbearia.service.Relatorio.dto.DadosRelatorioFuncionario;
import com.projeto.barbearia.service.Relatorio.dto.DadosRelatorioTodosFuncionarios;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/relatorio")
@SecurityRequirement(name = "bearer-key")
public class RelatorioController {

    @Autowired
    private RelatorioService service;

    @GetMapping("/funcionario")
    public ResponseEntity retornoFuncionario(@RequestBody DadosRelatorioFuncionario dados){
        return ResponseEntity.ok().body(service.RelatorioFuncionario(dados));
    }
    @GetMapping("/funcionarios")
    public ResponseEntity retornoTodosFuncionarios(@RequestBody @Valid DadosRelatorioTodosFuncionarios dados){
        return ResponseEntity.ok().body(service.RelatorioTodosOsFuncionarios(dados));
    }




}
