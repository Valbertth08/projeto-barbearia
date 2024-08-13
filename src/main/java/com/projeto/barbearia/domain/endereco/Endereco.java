package com.projeto.barbearia.domain.endereco;


import com.projeto.barbearia.domain.endereco.dto.DadosAtualizarEndereco;
import com.projeto.barbearia.domain.endereco.dto.DadosEndereco;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;

    public Endereco(DadosEndereco endereco) {
        this.bairro=endereco.bairro();
        this.cep=endereco.cep();
        this.numero=endereco.numero();
        this.complemento=endereco.complemento();
        this.cidade=endereco.cidade();
    }
    public void atualizarEndereco(DadosAtualizarEndereco dados){
        if(dados.cep()!=null){
            this.cep=dados.cep();
        }
        if(dados.bairro()!=null){
            this.bairro=dados.bairro();
        }
        if(dados.cidade()!=null){
            this.cidade=dados.cidade();
        }
        if(dados.numero()!=null){
            this.numero=dados.numero();
        }
        if(dados.complemento()!=null){
            this.complemento=dados.complemento();
        }
    }
}
