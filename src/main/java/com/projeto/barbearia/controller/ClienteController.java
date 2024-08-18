package com.projeto.barbearia.controller;


import com.projeto.barbearia.domain.cliente.dto.DadosAtualizarCliente;
import com.projeto.barbearia.domain.cliente.dto.DadosCadastrarCliente;
import com.projeto.barbearia.domain.cliente.dto.DadosListagemCliente;
import com.projeto.barbearia.service.ClienteService;
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
@RequestMapping("/cliente")
@SecurityRequirement(name = "bearer-key")
public class ClienteController {
    @Autowired
    private ClienteService service;
    @PostMapping("/registrar")
    @Transactional
    public ResponseEntity inserirCliente(@RequestBody @Valid DadosCadastrarCliente dados, UriComponentsBuilder builder){
        return  service.inserirCliente(dados,builder);
    }
    @GetMapping("/listar")
    public  ResponseEntity<Page<DadosListagemCliente>>listarCliente(Pageable page){
        return ResponseEntity.ok().body(service.listarCLiente(page));
    }
    @GetMapping("listar/{id}")
    public ResponseEntity listarClienteId(@PathVariable Long id){
        return ResponseEntity.ok().body(service.listarClienteId(id));
    }
    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity atualizarCliente(@RequestBody @Valid DadosAtualizarCliente dados){
        return ResponseEntity.ok().body(service.atualizarCliente(dados));
    }
    @DeleteMapping("deletar/{id}")
    @Transactional
    public ResponseEntity deletarClienteId(@PathVariable Long id) {
        service.deletarClienteId(id);
        return ResponseEntity.noContent().build();
    }
}
