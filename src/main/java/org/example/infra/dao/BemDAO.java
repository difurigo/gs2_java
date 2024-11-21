package org.example.infra.dao;

import org.example.dominio.Bem;
import org.example.dominio.RepositorioBem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BemDAO implements RepositorioBem {
    private Connection conexao;

    public BemDAO() {
        conexao = new ConnectionFactory().getConnection();
    }



    @Override
    public void adcionarEletrodomestico(Bem bem) {
        try {
            String sql = "INSERT INTO tb_bens (residencia_id, tipo_bem, nome_bem, horas_diarias, emissao_CO2) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement comandoDeInsercao = conexao.prepareStatement(sql);

            // Corrige o acesso ao ID da residência
            comandoDeInsercao.setInt(1, bem.getResidencia());
            comandoDeInsercao.setString(2, "Eletrodomestico");
            comandoDeInsercao.setString(3, bem.getNomeBem());
            comandoDeInsercao.setDouble(4, bem.getHorasDiarias());
            comandoDeInsercao.setDouble(5, 500);

            comandoDeInsercao.execute();
            comandoDeInsercao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void adcionarCarro(Bem bem) {
        try {
            String sql = "INSERT INTO tb_bens (residencia_id, tipo_bem, nome_bem, tipo_veiculo, km_mensal, emissao_CO2) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement comandoDeInsercao = conexao.prepareStatement(sql);
            comandoDeInsercao.setInt(1, bem.getResidencia());
            comandoDeInsercao.setString(2, "Carro");
            comandoDeInsercao.setString(3, bem.getNomeBem());
            comandoDeInsercao.setString(4, bem.getTipoVeiculo());
            comandoDeInsercao.setDouble(5, bem.getKmMensal());
            comandoDeInsercao.setDouble(6, 250);
            comandoDeInsercao.execute();
            comandoDeInsercao.close();
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void adcionarGas(Bem bem) {
        try {
            String sql = "INSERT INTO tb_bens (residencia_id, tipo_bem, tipo_gas, quantidade_mensal, emissao_CO2) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement comandoDeInsercao = conexao.prepareStatement(sql);
            comandoDeInsercao.setInt(1, bem.getResidencia());
            comandoDeInsercao.setString(2, "Gas");
            comandoDeInsercao.setString(3, bem.getNomeBem());
            comandoDeInsercao.setDouble(4, bem.getQuantidadeMensal());
            comandoDeInsercao.setDouble(5, 39);
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
    public ArrayList<Bem> buscarBens(int residenciaId) {
        ArrayList<Bem> bens = new ArrayList<>();
        try {
            String sql = "SELECT * FROM TB_BENS WHERE RESIDENCIA_ID = ?";
            PreparedStatement comandoDeSelecao = conexao.prepareStatement(sql);
            comandoDeSelecao.setInt(1, residenciaId);

            ResultSet resultados = comandoDeSelecao.executeQuery();
            while (resultados.next()) {
                Bem bem = new Bem(
                        resultados.getInt("BEM_ID"),                 // ID do bem
                        resultados.getInt("RESIDENCIA_ID"),          // ID da residência
                        resultados.getString("TIPO_BEM"),            // Tipo do bem
                        resultados.getString("NOME_BEM"),            // Nome do bem
                        resultados.getDouble("HORAS_DIARIAS"),       // Horas diárias de uso
                        resultados.getString("TIPO_VEICULO"),        // Tipo de veículo (se aplicável)
                        resultados.getDouble("KM_MENSAL"),           // Quilometragem mensal
                        resultados.getString("TIPO_GAS"),            // Tipo de gás (se aplicável)
                        resultados.getDouble("QUANTIDADE_MENSAL"),   // Quantidade mensal consumida
                        resultados.getDouble("EMISSAO_CO2"),         // Emissão de CO2
                        resultados.getDouble("POTENCIA")             // Potência (se aplicável)
                );
                bens.add(bem);
            }

            resultados.close();
            comandoDeSelecao.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar bens para a residência ID: " + residenciaId, e);
        }
        return bens;
    }


}
