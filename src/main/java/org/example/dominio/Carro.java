package org.example.dominio;

public class Carro extends Bem {
    private String marca;
    private String modelo;
    private boolean eletrico;

    public Carro(String nome, String classe, double emissaoCO2h, double horasUsoDiario, String marca, String modelo, boolean eletrico) {
        super(nome, classe, emissaoCO2h, horasUsoDiario);
        this.marca = marca;
        this.modelo = modelo;
        this.eletrico = eletrico;
    }

    public double getConsumoEnergetico() {
        if (eletrico) {
            return 15;
        } else {
            return 0.8;
        }
    }
}
