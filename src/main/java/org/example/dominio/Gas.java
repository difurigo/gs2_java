package org.example.dominio;

public class Gas extends Bem {
    private double tamanhoL;
    private String tipo;

    public Gas(String nome, double emissaoCO2h, double horasUsoDiario, double tamanhoL, String tipo) {
        super(nome, emissaoCO2h, horasUsoDiario);
        this.tamanhoL = tamanhoL;
        this.tipo = tipo;
    }
}
