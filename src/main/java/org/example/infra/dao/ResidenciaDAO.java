package org.example.infra.dao;

import org.example.dominio.Endereco;
import org.example.dominio.RepositorioResidencias;
import org.example.dominio.Residencia;
import org.example.dominio.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            comandoDeInsercao.setString(3, residencia.getEndereco().toString()); // Endereço completo como String
            comandoDeInsercao.setInt(4, residencia.getNumResidencia()); // Número da residência
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
        }catch(SQLException e) {
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
                residencia = new Residencia(
                        resultados.getInt("residencia_id"),              // ID da residência
                        resultados.getInt("id_usuario"),                // ID do usuário
                        resultados.getString("tipo_residencia"),        // Tipo de residência
                        new Endereco(                                   // Construção do objeto Endereço
                                resultados.getString("cep"),
                                resultados.getString("Logradouro"),
                                resultados.getString("cidade"),
                                resultados.getString("estado")
                        ),
                        resultados.getInt("numero_moradores"),          // Número de moradores
                        resultados.getInt("num_residencia")             // Número da residência
                );
            }

            resultados.close();
            comandoDeSelecao.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar a residência: " + e.getMessage(), e);
        }

        return residencia;
    }

}
