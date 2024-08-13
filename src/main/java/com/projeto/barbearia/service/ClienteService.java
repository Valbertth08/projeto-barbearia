package com.projeto.barbearia.service;


import com.projeto.barbearia.domain.cliente.*;
import com.projeto.barbearia.domain.cliente.dto.DadosAtualizarCliente;
import com.projeto.barbearia.domain.cliente.dto.DadosCadastrarCliente;
import com.projeto.barbearia.domain.cliente.dto.DadosDetalhamentoCliente;
import com.projeto.barbearia.domain.cliente.dto.DadosListagemCliente;
import com.projeto.barbearia.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    public ResponseEntity inserirCliente(DadosCadastrarCliente dados, UriComponentsBuilder builder){
        var cliente= clienteRepository.save(new Cliente(dados));
        var uri= builder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoCliente(cliente));
    }
    public Page<DadosListagemCliente>  listarCLiente(Pageable pageable){
        var lista = clienteRepository.findAll(pageable).map(DadosListagemCliente::new);
        return  lista ;
    }
    public DadosDetalhamentoCliente listarClienteId(Long id) {
       if(!clienteRepository.existsById(id)){
           throw new EntityNotFoundException("Cliente não cadastrado no sistema");
       }
        var cliente= clienteRepository.getReferenceById(id);
        return  new DadosDetalhamentoCliente(cliente);
    }
    public DadosDetalhamentoCliente atualizarCliente(DadosAtualizarCliente dados){
        if(!clienteRepository.existsById(dados.id())){
            throw new EntityNotFoundException("Cliente não cadastro no sistema");
        }
        var cliente= clienteRepository.getReferenceById(dados.id());
        if(dados.nome()!=null){
            cliente.setNome(dados.nome());
        }
        if(dados.telefone()!=null){
            cliente.setTelefone(dados.telefone());
        }
        if(dados.endereco()!=null){
            cliente.setEndereco(dados.endereco());
        }
        if(dados.data()!=null){
            cliente.setData_aniversario(dados.data());
        }
        return new DadosDetalhamentoCliente(cliente);
    }
    public void deletarClienteId( Long id){
        if(!clienteRepository.existsById(id)){
            throw new EntityNotFoundException("Cliente não cadastrado no sistema");
        }
        clienteRepository.deleteById(id);
    }
}
