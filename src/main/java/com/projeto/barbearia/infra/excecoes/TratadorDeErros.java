package com.projeto.barbearia.infra.excecoes;


import com.projeto.barbearia.domain.agendamento.validacoes.ValidacaoExpection;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404(EntityNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity tratarErro400(MethodArgumentNotValidException ex){
            var erros= ex.getFieldErrors();
            return ResponseEntity.badRequest().body(erros.stream().map(DadosErrosValidacao::new).toList());
    }
    @ExceptionHandler(ValidacaoExpection.class)
    public ResponseEntity tratarValidacao(ValidacaoExpection ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    private record DadosErrosValidacao(String campo, String mensagem){
        public DadosErrosValidacao(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
