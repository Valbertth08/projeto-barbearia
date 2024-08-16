package com.projeto.barbearia.domain.agendamento.enums;

public enum StatusAgendamento {
    PAGO("pago"),
    PENDENTE("pendente");

    private String status;

    private StatusAgendamento(String status){
        this.status=status;
    }
    public String getStatus() {
        return status;
    }
}
