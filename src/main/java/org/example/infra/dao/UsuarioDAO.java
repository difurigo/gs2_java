package org.example.infra.dao;

import org.example.dominio.RepositorioUsuarios;
import org.example.dominio.Usuario;

import java.sql.Connection;
import java.sql.SQLException;

public class UsuarioDAO implements RepositorioUsuarios {

    private Connection conexao;

    public UsuarioDAO(Connection conexao) {
        conexao = new ConnectionFactory().getConnection();
    }

    @Override
    public void adicionar(Usuario usuario) {

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
        return null;
    }
}
