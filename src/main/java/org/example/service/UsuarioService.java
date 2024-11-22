package org.example.service;

import org.example.dominio.RepositorioUsuarios;
import org.example.dominio.Usuario;

import java.util.List;

public class UsuarioService {
    private final RepositorioUsuarios repositorioUsuarios;

    // Construtor que recebe um repositório de usuários
    public UsuarioService(RepositorioUsuarios repositorioUsuarios) {
        this.repositorioUsuarios = repositorioUsuarios;
    }

    // Busca um usuário pelo email
    public Usuario buscarUsuarioPorEmail(String email) {
        return repositorioUsuarios.buscarUsuarioPorEmail(email);
    }

    // Retorna o ID de um usuário com base no email
    public int buscarIdUsuario(String email) {
        return repositorioUsuarios.buscarIdUsuario(email);
    }

    // Busca um usuário pelo nome
    public Usuario buscarUsuarioPorNome(String nome) {
        return repositorioUsuarios.buscarUsuarioPorNome(nome);
    }

    // Adiciona um novo usuário
    public void cadastrarUsuario(Usuario usuario) {
        repositorioUsuarios.adicionar(usuario);
    }

    // Lista todos os usuários
    public List<Usuario> listarTodosUsuarios() {
        return repositorioUsuarios.listarTodos();
    }
}
