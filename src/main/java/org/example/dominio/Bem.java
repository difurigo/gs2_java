package org.example.dominio;

public abstract class Bem {
    private String nome;
    private String classe;
    protected double emissaoCO2h;
    private double horasUsoDiario;

    public Bem(String nome, String classe, double emissaoCO2h, double horasUsoDiario) {
        this.nome = nome;
        this.classe = classe;
        this.emissaoCO2h = emissaoCO2h;
        this.horasUsoDiario = horasUsoDiario;
    }

    public double getEmissaoCO2h() {
        return emissaoCO2h;
    }

    public double getHorasUsoDiario() {
        return horasUsoDiario;
    }
}
