package org.example.infra.dao;

import org.example.dominio.CalculadoraEcoLar;
import org.example.dominio.RepositorioGastosEnergeticos;

public class GastoEnergeticoDAO implements RepositorioGastosEnergeticos {

    @Override
    public void adcionar(int idResidencia, CalculadoraEcoLar resultados) {

    }

    @Override
    public void fechar() {

    }

    @Override
    public double buscarEmissaoCO2(int idResidencia) {
        return 0;
    }

    @Override
    public double buscarGastoEnergia(int idResidencia) {
        return 0;
    }
}
