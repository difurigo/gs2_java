package org.example.dominio;

public abstract class Bem {
    private String nome;
    private double emissaoCO2h;
    private double horasUsoDiario;

    public Bem(String nome, double emissaoCO2h, double horasUsoDiario) {
        this.nome = nome;
        this.emissaoCO2h = emissaoCO2h;
        this.horasUsoDiario = horasUsoDiario;
    }
}
