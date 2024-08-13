package com.projeto.barbearia.domain.funcionario;

public enum Especialidade {
    BARBEIRO("barbeiro"),
    MANICURE("manicure");
    private String especialidade;
    Especialidade(String especialidade){

        this.especialidade=especialidade;
    }
    public String getEspecialidade() {
        return especialidade;
    }
}
