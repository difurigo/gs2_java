package org.example.dominio;

public interface RepositorioGastosEnergeticos {
    public void adcionar(int idResidencia, CalculadoraEcoLar resultados);
    public void fechar();
    public double buscarEmissaoCO2(int idResidencia);
    public double buscarGastoEnergia(int idResidencia);
}
