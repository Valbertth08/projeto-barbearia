package com.projeto.barbearia.domain.agendamento.validacoes;

public class ValidacaoExpection extends RuntimeException{
    public ValidacaoExpection( String msg){
        super(msg);
    }
}
