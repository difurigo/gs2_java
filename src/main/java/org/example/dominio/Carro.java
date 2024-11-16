package org.example.dominio;

public class Carro extends Bem {
    private String marca;
    private String modelo;
    private boolean eletrico;

    public Carro(String nome, double emissaoCO2h, double horasUsoDiario, String marca, String modelo, boolean eletrico) {
        super(nome, emissaoCO2h, horasUsoDiario);
        this.marca = marca;
        this.modelo = modelo;
        this.eletrico = eletrico;
    }
}
