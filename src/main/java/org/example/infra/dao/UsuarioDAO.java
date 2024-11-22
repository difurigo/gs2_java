package org.example.infra.dao;

import org.example.dominio.RepositorioUsuarios;
import org.example.dominio.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements RepositorioUsuarios {

    private Connection conexao;

    public UsuarioDAO() {
        conexao = new ConnectionFactory().getConnection();
    }

    @Override
    public void adicionar(Usuario usuario) {
        try {
            String sql = "INSERT INTO TB_USUARIO (NOME_USUARIO, EMAIL_USUARIO, SENHA_USUARIO) VALUES (?, ?, ?)";
            PreparedStatement comandoDeInsercao = conexao.prepareStatement(sql);

            comandoDeInsercao.setString(1, usuario.getNome());
            comandoDeInsercao.setString(2, usuario.getEmail());
            comandoDeInsercao.setString(3, usuario.getSenha());

            comandoDeInsercao.execute();
            comandoDeInsercao.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar usuário: " + e.getMessage(), e);
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

    public Usuario buscarUsuarioPorNome(String nomeUsuario) {
        Usuario usuario = null;
        try {
            String sql = "SELECT * FROM TB_USUARIO WHERE NOME_USUARIO = ?";
            PreparedStatement comandoDeSelecao = conexao.prepareStatement(sql);
            comandoDeSelecao.setString(1, nomeUsuario);

            ResultSet resultados = comandoDeSelecao.executeQuery();
            if (resultados.next()) {
                usuario = new Usuario(
                        resultados.getInt("ID_USUARIO"),
                        resultados.getString("NOME_USUARIO"),
                        resultados.getString("EMAIL_USUARIO"),
                        resultados.getString("SENHA_USUARIO")
                );
            }

            resultados.close();
            comandoDeSelecao.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário por nome: " + e.getMessage(), e);
        }
        return usuario;
    }

    @Override
    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            String sql = "SELECT * FROM TB_USUARIO";
            PreparedStatement comandoDeSelecao = conexao.prepareStatement(sql);

            ResultSet resultados = comandoDeSelecao.executeQuery();
            while (resultados.next()) {
                Usuario usuario = new Usuario(
                        resultados.getInt("ID_USUARIO"),
                        resultados.getString("NOME_USUARIO"),
                        resultados.getString("EMAIL_USUARIO"),
                        resultados.getString("SENHA_USUARIO")
                );
                usuarios.add(usuario);
            }

            resultados.close();
            comandoDeSelecao.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar todos os usuários: " + e.getMessage(), e);
        }
        return usuarios;
    }


    @Override
    public Usuario buscarUsuarioPorId(int idUsuario) {
        Usuario usuario = null;
        try {
            String sql = "SELECT * FROM TB_USUARIO WHERE ID_USUARIO = ?";
            PreparedStatement comandoDeSelecao = conexao.prepareStatement(sql);
            comandoDeSelecao.setInt(1, idUsuario);

            ResultSet resultados = comandoDeSelecao.executeQuery();
            if (resultados.next()) {
                usuario = new Usuario(
                        resultados.getInt("ID_USUARIO"),
                        resultados.getString("NOME_USUARIO"),
                        resultados.getString("EMAIL_USUARIO"),
                        resultados.getString("SENHA_USUARIO")
                );
            }

            resultados.close();
            comandoDeSelecao.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário por ID: " + e.getMessage(), e);
        }
        return usuario;
    }


    @Override
    public int buscarIdUsuario(String email) {
        int idUsuario = 0;
        try {
            String sql = "SELECT * FROM tb_usuario WHERE email = ?";
            PreparedStatement comandoDeSelecao = conexao.prepareStatement(sql);
            comandoDeSelecao.setString(1, email);
            ResultSet resultados = comandoDeSelecao.executeQuery();
            while (resultados.next()) {
                idUsuario = resultados.getInt("id_usuario");
            }
            resultados.close();
            comandoDeSelecao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return idUsuario;
    }

    @Override
    public Usuario buscarUsuarioPorEmail(String emailUsuario) {
        Usuario usuario = null;
        try {
            String sql = "SELECT * FROM TB_USUARIO WHERE EMAIL_USUARIO = ?";
            PreparedStatement comandoDeSelecao = conexao.prepareStatement(sql);
            comandoDeSelecao.setString(1, emailUsuario);

            ResultSet resultados = comandoDeSelecao.executeQuery();
            if (resultados.next()) {
                usuario = new Usuario(
                        resultados.getInt("ID_USUARIO"),              // ID do usuário
                        resultados.getString("NOME_USUARIO"),         // Nome do usuário
                        resultados.getString("EMAIL_USUARIO"),        // Email do usuário
                        resultados.getString("SENHA_USUARIO")         // Senha do usuário
                );
            }

            resultados.close();
            comandoDeSelecao.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário por email: " + e.getMessage(), e);
        }
        return usuario;
    }
}
