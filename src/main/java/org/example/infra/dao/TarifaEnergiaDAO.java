package org.example.infra.dao;

import org.example.dominio.RepositorioTarifas;
import org.example.dominio.TarifaEnergia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TarifaEnergiaDAO implements RepositorioTarifas {

    private Connection conexao;

    public TarifaEnergiaDAO() {
        conexao = new ConnectionFactory().getConnection();
    }

    @Override
    public ArrayList<TarifaEnergia> recuperarTarifas() {
        ArrayList<TarifaEnergia> tarifas = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tb_tarifa_energia";
            PreparedStatement comandoDeSelecao = conexao.prepareStatement(sql);
            ResultSet resultados = comandoDeSelecao.executeQuery();

            while (resultados.next()) {
                TarifaEnergia tarifa = new TarifaEnergia(
                        resultados.getString("estado"),
                            resultados.getDouble("preco_kwh")
                );
                tarifas.add(tarifa);
            }
            resultados.close();
            comandoDeSelecao.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return tarifas;
    }

    @Override
    public void fechar() {
        try {
            conexao.close();
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
