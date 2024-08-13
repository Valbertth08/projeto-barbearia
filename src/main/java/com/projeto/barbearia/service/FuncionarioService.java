package com.projeto.barbearia.service;


import com.projeto.barbearia.domain.funcionario.Funcionario;
import com.projeto.barbearia.domain.funcionario.dto.DadosAtualizarFuncionario;
import com.projeto.barbearia.domain.funcionario.dto.DadosCadastrarFuncionario;
import com.projeto.barbearia.domain.funcionario.dto.DadosDetalhamentoFuncionario;
import com.projeto.barbearia.domain.funcionario.dto.DadosListagemFuncionario;
import com.projeto.barbearia.repository.FuncionarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository repository;

    public ResponseEntity inserirFuncionario(DadosCadastrarFuncionario dados, UriComponentsBuilder builder){
        var funcionario = repository.save(new Funcionario(dados));
        var uri= builder.path("/funcionario/{id}").buildAndExpand(funcionario.getId()).toUri();
        return  ResponseEntity.created(uri).body(new DadosDetalhamentoFuncionario(funcionario));
    }
    public DadosDetalhamentoFuncionario autalizarFuncionario(DadosAtualizarFuncionario dados){
        if(!repository.existsById(dados.id())){
            throw new EntityNotFoundException("Funcionario não cadastrado no sistema");
        }
        var funcionario= repository.getReferenceById(dados.id());
        if(dados.nome()!=null){
          funcionario.setNome(dados.nome());
        }
        if(dados.especialidade()!=null){
            funcionario.setEspecialidade((dados.especialidade()));
        }
        if(dados.telefone()!=null){
            funcionario.setTelefone((dados.telefone()));
        }
        if(dados.endereco()!=null){
            funcionario.getEndereco().atualizarEndereco(dados.endereco());
        }
        return new DadosDetalhamentoFuncionario(funcionario);
    }
    public Page<DadosListagemFuncionario> listarFuncionario(Pageable pageable){
        var lista= repository.findAll(pageable).map(DadosListagemFuncionario::new);
        return lista;

    }
    public DadosDetalhamentoFuncionario listarFuncionarioId(Long id) {
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Funcionario não econtrado");
        }
        var funcionario= repository.getReferenceById(id);
        return new DadosDetalhamentoFuncionario(funcionario);
    }

    public void deletarFuncionarioId(Long id) {
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Funcionario não econtrado");
        }
        repository.deleteById(id);
    }
}
