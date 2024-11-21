package org.example.infra.dao;

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
            String sql = "INSERT INTO tb_usuario (nome, email) VALUES(?, ?)";
            PreparedStatement comandoDeInsercao = conexao.prepareStatement(sql);
            comandoDeInsercao.setString(1, usuario.getNome());
            comandoDeInsercao.setString(2, usuario.getEmail());
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
                        resultados.getString("email"));
            }
            resultados.close();
            comandoDeSelecao.close();
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

    @Override
    public Usuario buscarUsuarioPorId(int idUsuario) {
        Usuario usuario = null;
        try {
            String sql = "SELECT * FROM tb_usuario WHERE id_usuario = ?";
            PreparedStatement comandoDeSelecao = conexao.prepareStatement(sql);
            comandoDeSelecao.setInt(1, idUsuario);
            ResultSet resultados = comandoDeSelecao.executeQuery();
            while(resultados.next()) {
                usuario = new Usuario(resultados.getString("nome"),
                        resultados.getString("email"));
            }
            resultados.close();
            comandoDeSelecao.close();
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }


    @Override
    public Usuario buscarUsuarioPorNome(String nomeUsuario) {
        Usuario usuario = null;
        try {
            String sql = "SELECT * FROM tb_usuario WHERE nome = ?";
            PreparedStatement comandoDeSelecao = conexao.prepareStatement(sql);
            comandoDeSelecao.setString(1, nomeUsuario); // Passa o nome como parâmetro
            ResultSet resultados = comandoDeSelecao.executeQuery();

            while (resultados.next()) {
                usuario = new Usuario(
                        resultados.getString("nome"),
                        resultados.getString("email"));
            }

            resultados.close();
            comandoDeSelecao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e); // Trata exceções de banco de dados
        }
        return usuario; // Retorna o usuário ou null se não encontrado
    }


    @Override
    public int buscarIdUsuario(String email) {
        int idUsuario = 0;
        try {
            String sql = "SELECT * FROM tb_usuario WHERE email = ?";
            PreparedStatement comandoDeSelecao = conexao.prepareStatement(sql);
            comandoDeSelecao.setString(1, email);
            ResultSet resultados = comandoDeSelecao.executeQuery();
            while(resultados.next()) {
                idUsuario = resultados.getInt("id_usuario");
            }
            resultados.close();
            comandoDeSelecao.close();
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return idUsuario;
    }
}
