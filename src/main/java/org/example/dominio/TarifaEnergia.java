package org.example.dominio;

public class TarifaEnergia {
    private String estado;
    private double precoKwh;

    public TarifaEnergia(String estado, double precoKwh) {
        this.estado = estado;
        this.precoKwh = precoKwh;
    }
}
