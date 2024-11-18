package org.example.infra.dao;

import org.example.dominio.CalculadoraEcoLar;
import org.example.dominio.RepositorioGastosEnergeticos;

import java.sql.Connection;
import java.sql.SQLException;

public class GastoEnergeticoDAO implements RepositorioGastosEnergeticos {

    private Connection conexao;

    public GastoEnergeticoDAO(Connection conexao) {
        conexao = new ConnectionFactory().getConnection();
    }

    @Override
    public void adcionar(int idResidencia, CalculadoraEcoLar resultados) {

    }

    @Override
    public void fechar() {
        try {
            conexao.close();
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
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
