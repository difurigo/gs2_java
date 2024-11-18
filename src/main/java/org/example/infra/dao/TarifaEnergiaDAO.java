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

            int contador = 0; // Para contar o número de linhas recuperadas
            while (resultados.next()) {
                contador++;
                TarifaEnergia tarifa = new TarifaEnergia(
                        resultados.getString("estado"),
                            resultados.getDouble("preco_kwh")
                );
                tarifas.add(tarifa);
            }
            System.out.println("Linhas recuperadas: " + contador); // Verificar quantas linhas foram recuperadas
            resultados.close();
            comandoDeSelecao.close();
        } catch (SQLException e) {
            e.printStackTrace(); // Mostra o erro no console
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
