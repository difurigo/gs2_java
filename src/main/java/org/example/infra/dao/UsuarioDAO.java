package org.example.infra.dao;

import org.example.dominio.Endereco;
import org.example.dominio.RepositorioUsuarios;
import org.example.dominio.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO implements RepositorioUsuarios {

    private Connection conexao;

    public UsuarioDAO() {
        conexao = new ConnectionFactory().getConnection();
    }

    @Override
    public void adicionar(Usuario usuario) {
        try {
            String sql = "INSERT INTO tb_usuario (id_residencia, nome, email, endereco) VALUES(?, ?, ?)";
            PreparedStatement comandoDeInsercao = conexao.prepareStatement(sql);
            comandoDeInsercao.setInt(1, usuario.getIdResidencia());
            comandoDeInsercao.setString(2, usuario.getNome());
            comandoDeInsercao.setString(3, usuario.getEmail());
            comandoDeInsercao.setString(4, usuario.getEndereco());
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
    public Usuario buscarUsuarioPorEmail(String email) {
        Usuario usuario = null;
        try {
            String sql = "SELECT * FROM tb_usuario WHERE email = ?";
            PreparedStatement comandoDeSelecao = conexao.prepareStatement(sql);
            comandoDeSelecao.setString(1, email);
            ResultSet resultados = comandoDeSelecao.executeQuery();
            while(resultados.next()) {
                usuario = new Usuario(resultados.getString("nome"),
                        resultados.getString("email"),
                        resultados.getObject("endereco", Endereco.class),
                        resultados.getString("id_residencia"));
            }
            resultados.close();
            comandoDeSelecao.close();
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }
}
