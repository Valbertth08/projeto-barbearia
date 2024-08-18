package com.projeto.barbearia.controller;

import com.projeto.barbearia.domain.pagamento.dto.DadosCadastarPagamento;
import com.projeto.barbearia.domain.pagamento.dto.DadosDetalhamentoPagamento;
import com.projeto.barbearia.service.PagamentoService;
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
@RequestMapping("/pagamento")
@SecurityRequirement(name = "bearer-key")
public class PagamentoController {
    @Autowired
    private PagamentoService service;
    @PostMapping("/inserir")
    @Transactional
    public ResponseEntity inserirPagamento(@RequestBody @Valid DadosCadastarPagamento dados, UriComponentsBuilder builder){
        return service.inserirPagamento(dados,builder);
    }
    @DeleteMapping("deletar/{id}")
    @Transactional
    public ResponseEntity deletarPagamento(@PathVariable Long id){
        service.cancelarPagamento(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("listar")
    public ResponseEntity<Page<DadosDetalhamentoPagamento>> ListarPagamento(Pageable pageable){
        return ResponseEntity.ok().body(service.listarPagamento(pageable));
    }
    @GetMapping("listar/{id}")
    public ResponseEntity ListarPagamentoId(@PathVariable Long id){
        return ResponseEntity.ok().body(service.listarPagamentoId(id));
    }

}
