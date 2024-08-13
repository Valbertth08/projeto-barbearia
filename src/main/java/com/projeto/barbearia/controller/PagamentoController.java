package com.projeto.barbearia.controller;

import com.projeto.barbearia.domain.pagamento.dto.DadosCadastarPagamento;
import com.projeto.barbearia.service.PagamentoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {


    @Autowired
    private PagamentoService service;
    @PostMapping()
    @Transactional
    public ResponseEntity inserirPagamento(@RequestBody @Valid DadosCadastarPagamento dados, UriComponentsBuilder builder){
        return service.inserirPagamento(dados,builder);
    }
}
