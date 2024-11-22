package org.example.infra.dao;

import org.example.dominio.Endereco;
import org.example.dominio.RepositorioResidencias;
import org.example.dominio.Residencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResidenciaDAO implements RepositorioResidencias {

    private Connection conexao;

    public ResidenciaDAO() {
        conexao = new ConnectionFactory().getConnection();
    }

    @Override
    public void adicionar(Residencia residencia) {
        try {
            String sql = "INSERT INTO tb_residencia (id_usuario, tipo_residencia, endereco, numero_residencia, numero_moradores) "
                    + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement comandoDeInsercao = conexao.prepareStatement(sql);

            // Dados básicos da residência
            comandoDeInsercao.setInt(1, residencia.getIdUsuario()); // ID do usuário
            comandoDeInsercao.setString(2, residencia.getTipoResidencia()); // Tipo de residência

            // Concatenando os atributos do endereço em uma string
            Endereco endereco = residencia.getEndereco();
            String enderecoCompleto = endereco.getLogradouro() + ", " +
                    endereco.getCidade() + ", " +
                    endereco.getEstado() + ", " +
                    endereco.getCep();
            comandoDeInsercao.setString(3, enderecoCompleto); // Endereço completo

            comandoDeInsercao.setString(4, String.valueOf(residencia.getNumResidencia())); // Número da residência
            comandoDeInsercao.setInt(5, residencia.getNumMoradores()); // Número de moradores

            // Executa o comando
            comandoDeInsercao.execute();
            comandoDeInsercao.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar residência: " + e.getMessage(), e);
        }
    }

    @Override
    public void fechar() {
        try {
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Residencia buscarResidencia(int idResidencia) {
        Residencia residencia = null;
        try {
            String sql = "SELECT * FROM tb_residencia WHERE residencia_id = ?";
            PreparedStatement comandoDeSelecao = conexao.prepareStatement(sql);
            comandoDeSelecao.setInt(1, idResidencia);

            ResultSet resultados = comandoDeSelecao.executeQuery();
            if (resultados.next()) {
                // Dividir o campo "endereco" em seus atributos
                String enderecoCompleto = resultados.getString("endereco");
                String[] enderecoPartes = enderecoCompleto.split(", ");
                Endereco endereco = new Endereco(
                        enderecoPartes[3], // CEP
                        enderecoPartes[0], // Logradouro
                        enderecoPartes[1], // Cidade
                        enderecoPartes[2]  // Estado
                );

                residencia = new Residencia(
                        resultados.getInt("residencia_id"),              // ID da residência
                        resultados.getInt("id_usuario"),                // ID do usuário
                        resultados.getString("tipo_residencia"),        // Tipo de residência
                        endereco,                                       // Endereço reconstruído
                        resultados.getInt("numero_moradores"),          // Número de moradores
                        resultados.getInt("numero_residencia")          // Número da residência
                );
            }

            resultados.close();
            comandoDeSelecao.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar a residência: " + e.getMessage(), e);
        }

        return residencia;
    }



    @Override
    public List<Residencia> listarPorUsuario(int idUsuario) {
        List<Residencia> residencias = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tb_residencia WHERE id_usuario = ?";
            PreparedStatement comandoDeSelecao = conexao.prepareStatement(sql);
            comandoDeSelecao.setInt(1, idUsuario);

            ResultSet resultados = comandoDeSelecao.executeQuery();
            while (resultados.next()) {
                // Dividir o campo "endereco" em seus atributos
                String enderecoCompleto = resultados.getString("endereco");
                String[] enderecoPartes = enderecoCompleto.split(", ");
                Endereco endereco = new Endereco(
                        enderecoPartes[3], // CEP
                        enderecoPartes[0], // Logradouro
                        enderecoPartes[1], // Cidade
                        enderecoPartes[2]  // Estado
                );

                Residencia residencia = new Residencia(
                        resultados.getInt("residencia_id"),              // ID da residência
                        resultados.getInt("id_usuario"),                // ID do usuário
                        resultados.getString("tipo_residencia"),        // Tipo de residência
                        endereco,                                       // Endereço reconstruído
                        resultados.getInt("numero_moradores"),          // Número de moradores
                        resultados.getInt("numero_residencia")          // Número da residência
                );
                residencias.add(residencia);
            }

            resultados.close();
            comandoDeSelecao.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar residências: " + e.getMessage(), e);
        }

        return residencias;
    }
}
