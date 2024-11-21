package org.example.infra.dao;

import org.example.dominio.CalculadoraEcoLar;
import org.example.dominio.RepositorioGastosEnergeticos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GastoEnergeticoDAO implements RepositorioGastosEnergeticos {

    private Connection conexao;

    public GastoEnergeticoDAO(Connection conexao) {
        conexao = new ConnectionFactory().getConnection();
    }

    public GastoEnergeticoDAO() {

    }

    @Override
    public void adcionar(int idResidencia, CalculadoraEcoLar resultados) {
        try {
            String sql = "INSERT INTO tb_gasto_energetico (id_residencia, emissao, gasto) VALUES(?, ?, ?)";
            PreparedStatement comandoDeInsercao = conexao.prepareStatement(sql);
            comandoDeInsercao.setInt(1, idResidencia);
            comandoDeInsercao.setDouble(2, resultados.getEmissao(idResidencia));
            comandoDeInsercao.setDouble(3, resultados.getGasto(idResidencia));
            comandoDeInsercao.execute();
            comandoDeInsercao.close();
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
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
        double emissao = 0;
        try {
            String sql = "SELECT * FROM tb_gasto_energetico WHERE id_residencia = ?";
            PreparedStatement comandoDeSelecao = conexao.prepareStatement(sql);
            comandoDeSelecao.setInt(1, idResidencia);
            ResultSet resultados = comandoDeSelecao.executeQuery();
            while(resultados.next()) {
                emissao = resultados.getInt("emissao");
            }
            resultados.close();
            comandoDeSelecao.close();
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return emissao;
    }

    @Override
    public double buscarGastoEnergia(int idResidencia) {
        double gasto = 0;
        try {
            String sql = "SELECT * FROM tb_gasto_energetico WHERE id_residencia = ?";
            PreparedStatement comandoDeSelecao = conexao.prepareStatement(sql);
            comandoDeSelecao.setInt(1, idResidencia);
            ResultSet resultados = comandoDeSelecao.executeQuery();
            while(resultados.next()) {
                gasto = resultados.getInt("gasto");
            }
            resultados.close();
            comandoDeSelecao.close();
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return gasto;
    }
}
