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
            String sql = "INSERT INTO tb_residencia (id_usuario, endereco, bens, numero_moradores) VALUES(?, ?, ?, ?)";
            PreparedStatement comandoDeInsercao = conexao.prepareStatement(sql);
            comandoDeInsercao.setInt(1, residencia.getIdUsuario());
            comandoDeInsercao.setObject(2, residencia.getEndereco());
            comandoDeInsercao.setObject(3, residencia.getBens());
            comandoDeInsercao.setInt(4, residencia.getNumMoradores());
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
    public Residencia buscarResidencia(int idResidencia) {
        Residencia residencia = null;
        try {
            String sql = "SELECT * FROM tb_residencia WHERE id_residencia = ?";
            PreparedStatement comandoDeSelecao = conexao.prepareStatement(sql);
            comandoDeSelecao.setInt(1, idResidencia);
            ResultSet resultados = comandoDeSelecao.executeQuery();
            while(resultados.next()) {
                residencia = new Residencia(resultados.getInt("id_usuario"),
                        resultados.getObject("endereco", Endereco.class),
                        resultados.getInt("numero_moradores"));
            }
            resultados.close();
            comandoDeSelecao.close();
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return residencia;
    }
}
