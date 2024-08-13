package com.projeto.barbearia.domain.pagamento;

public enum FormaPagamento {
    DINHEIRO("dinheiro"),
    PIX("pix"),
    CARTAO("cartao");
    private String forma;
    FormaPagamento(String forma){
        this.forma=forma;
    }
    public String getForma() {

        return forma;
    }
}
