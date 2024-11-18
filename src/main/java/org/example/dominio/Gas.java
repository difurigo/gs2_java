package org.example.dominio;

public class Gas extends Bem {
    private double tamanhoL;
    private String tipo;

    public Gas(String nome, String classe, double consumoGasPorHora, double horasUsoDiario, double tamanhoL, String tipo) {
        super(nome, classe, calcularEmissaoCO2hPorTipo(consumoGasPorHora, tipo), horasUsoDiario);
        this.tamanhoL = tamanhoL;
        this.tipo = tipo;
    }

    private static double calcularEmissaoCO2hPorTipo(double consumoGasPorHora, String tipo) {
        double emissaoCO2gPorL;

        if (tipo.equalsIgnoreCase("glp")) {
            emissaoCO2gPorL = 2800;
        } else {
            emissaoCO2gPorL = 2000;
        }

        double emissaoCO2gPorHora = emissaoCO2gPorL * consumoGasPorHora;

        // Converte de g/h para kg/h
        return emissaoCO2gPorHora / 1000.0;
    }

    public double getTamanhoL() {
        return tamanhoL;
    }

    public String getTipo() {
        return tipo;
    }
}
