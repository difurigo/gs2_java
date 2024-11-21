package org.example.dominio;

public abstract class Bem {
    private int bemId;
    private Residencia residencia;
    private String tipoBem;
    private String nomeBem;
    private double horasDiarias;
    private String tipoVeiculo;
    private double kmMensal;
    private String tipoGas;
    private double quantidadeMensal;
    private double emissaoCO2;
    private double potencia;

    public double getPotencia() {
        return potencia;
    }

    public void setPotencia(double potencia) {
        this.potencia = potencia;
    }

    public double getEmissaoCO2() {
        return emissaoCO2;
    }

    public void setEmissaoCO2(double emissaoCO2) {
        this.emissaoCO2 = emissaoCO2;
    }

    public int getBemId() {
        return bemId;
    }

    public void setBemId(int bemId) {
        this.bemId = bemId;
    }

    public Residencia getResidencia() {
        return residencia;
    }

    public void setResidencia(Residencia residencia) {
        this.residencia = residencia;
    }

    public String getTipoBem() {
        return tipoBem;
    }

    public void setTipoBem(String tipoBem) {
        this.tipoBem = tipoBem;
    }

    public String getNomeBem() {
        return nomeBem;
    }

    public void setNomeBem(String nomeBem) {
        this.nomeBem = nomeBem;
    }

    public double getHorasDiarias() {
        return horasDiarias;
    }

    public void setHorasDiarias(double horasDiarias) {
        this.horasDiarias = horasDiarias;
    }

    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public double getKmMensal() {
        return kmMensal;
    }

    public void setKmMensal(double kmMensal) {
        this.kmMensal = kmMensal;
    }

    public String getTipoGas() {
        return tipoGas;
    }

    public void setTipoGas(String tipoGas) {
        this.tipoGas = tipoGas;
    }

    public double getQuantidadeMensal() {
        return quantidadeMensal;
    }

    public void setQuantidadeMensal(double quantidadeMensal) {
        this.quantidadeMensal = quantidadeMensal;
    }
}
