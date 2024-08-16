package com.projeto.barbearia.service.Relatorio;


import com.projeto.barbearia.domain.funcionario.Funcionario;
import com.projeto.barbearia.repository.FuncionarioRepository;
import com.projeto.barbearia.service.Relatorio.dto.DadosRelatorioFuncionario;
import com.projeto.barbearia.service.Relatorio.dto.DadosRelatorioTodosFuncionarios;
import com.projeto.barbearia.service.Relatorio.dto.DadosRetornoFuncionario;
import com.projeto.barbearia.service.Relatorio.dto.DadosRetornoTodosFuncionario;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RelatorioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public void RelatorioGeral (){
    }
    public DadosRetornoFuncionario RelatorioFuncionario(DadosRelatorioFuncionario dados){
        if(!funcionarioRepository.existsById(dados.id())) {
          throw new EntityNotFoundException("Funcionario não encontrado");
       }
         var funcinario =verificarSeEstarChegandoDatasFuncionario(dados);

        if(funcinario.total()!=null){
            return funcinario;
        }
       throw new EntityNotFoundException("O funcionario não possui nenhum agendamento nessa data");
    }

    public DadosRetornoTodosFuncionario RelatorioTodosOsFuncionarios(DadosRelatorioTodosFuncionarios dados) {

        var valorTotalTodosFuncionarios= verificarSeEstarChegandoDatasTodosFuncionario(dados);
        var listaFuncionariosRetornoRelatorio= new ArrayList<DadosRetornoFuncionario>();
        for (Funcionario fun:funcionarioRepository.findAll() ){
            var valor = verificarSeEstarChegandoDatasFuncionario(new DadosRelatorioFuncionario(fun.getId(),dados.dataIincio(),dados.dataFinal()));
            if (valor.total()!=null){
                listaFuncionariosRetornoRelatorio.add(new DadosRetornoFuncionario(fun.getNome(),valor.total()));
            }
        }
        return new DadosRetornoTodosFuncionario(valorTotalTodosFuncionarios,listaFuncionariosRetornoRelatorio);
    }

    private DadosRetornoFuncionario verificarSeEstarChegandoDatasFuncionario( DadosRelatorioFuncionario dados){
        var funcionario= funcionarioRepository.getReferenceById(dados.id());

        if( dados.dataIincio()!=null && dados.dataFinal()!=null){
            var valor= funcionarioRepository.ValorTotalDeServicoFeitoFuncionarioPeriodo(dados.id(),dados.dataIincio(),dados.dataFinal());
            return new DadosRetornoFuncionario(funcionario.getNome(),valor);
        }
        if(dados.dataIincio()!=null){
             var valor= funcionarioRepository.ValorTotalDeServicoFeitoFuncionarioData(dados.id(),dados.dataIincio());
            return new DadosRetornoFuncionario(funcionario.getNome(),valor);
        }
           var valor= funcionarioRepository.ValorTotalDeServicoFeitoFuncionario(dados.id());
             return new DadosRetornoFuncionario(funcionario.getNome(),valor);

    }
    private Double verificarSeEstarChegandoDatasTodosFuncionario( DadosRelatorioTodosFuncionarios dados){

        if( dados.dataIincio()!=null && dados.dataFinal()!=null){
            return funcionarioRepository.ValorTotalDeServicoFeitoTodosFuncionarioPeriodo(dados.dataIincio(),dados.dataFinal());
        }
        if(dados.dataIincio()!=null){
            return  funcionarioRepository.ValorTotalDeSericoFeitoTodosFuncionariosData(dados.dataIincio());
        }
        return funcionarioRepository.ValorTotalDeServicoFeitoTodosFuncionario();

    }



}
