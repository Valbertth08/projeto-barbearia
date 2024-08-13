package com.projeto.barbearia.service;

import com.projeto.barbearia.domain.servico.Servico;
import com.projeto.barbearia.domain.servico.dto.DadosAtualizarServico;
import com.projeto.barbearia.domain.servico.dto.DadosCadastrarServico;
import com.projeto.barbearia.domain.servico.dto.DadosDetalhamentoServico;
import com.projeto.barbearia.domain.servico.dto.DadosListagemServico;
import com.projeto.barbearia.repository.ServicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository repository;

    public ResponseEntity inserirServico(DadosCadastrarServico dados, UriComponentsBuilder builder){
        var servico= repository.save(new Servico(dados));
        var uri= builder.path("/servico/{id}").buildAndExpand(servico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoServico(servico));
    }

    public DadosDetalhamentoServico atualizarServico(DadosAtualizarServico dados) {
        if(!repository.existsById(dados.id())){
            throw new EntityNotFoundException("Servico não encontrado");
        }
        var servico= repository.getReferenceById(dados.id());
        if(dados.nome()!=null){
            servico.setNome(dados.nome());
        }
        if(dados.descricao()!=null){
            servico.setNome(dados.nome());
        }
        if(dados.preco()!=null){
            servico.setPreco(dados.preco());
        }
        return new DadosDetalhamentoServico(servico);
    }

    public void deletarServicoId(Long id) {
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Serviço não encontrado");
        }
        repository.deleteById(id);
    }
    public Page<DadosListagemServico> listarServico(Pageable pageable) {
       var servicos= repository.findAll(pageable).map(DadosListagemServico::new);
       return servicos;
    }

    public DadosDetalhamentoServico listarServicoId(Long id) {

        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Serviço não encontrado");
        }
        var servico = repository.getReferenceById(id);
        return new DadosDetalhamentoServico(servico);
    }
}
