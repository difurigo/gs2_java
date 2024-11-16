package org.example.dominio;

public class Eletrodomestico extends Bem {
    private String marca;
    private double potencia;

    public Eletrodomestico(String nome, double emissaoCO2h, double horasUsoDiario, String marca, double potencia) {
        super(nome, emissaoCO2h, horasUsoDiario);
        this.marca = marca;
        this.potencia = potencia;
    }
}
