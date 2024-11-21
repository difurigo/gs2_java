package org.example.service;

import org.example.dominio.RepositorioUsuarios;
import org.example.dominio.Usuario;

public class UsuarioService {
    private final RepositorioUsuarios repositorioUsuarios;

    public UsuarioService(RepositorioUsuarios repositorioUsuarios) {
        this.repositorioUsuarios = repositorioUsuarios;
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return repositorioUsuarios.buscarUsuarioPorEmail(email);
    }

    public int buscarIdUsuario(String email) {
        return repositorioUsuarios.buscarIdUsuario(email);
    }
    public Usuario buscarUsuarioPorNome(String nome) {
        return repositorioUsuarios.buscarUsuarioPorNome(nome);
    }

    public void cadastrarUsuario(Usuario usuario) {
        repositorioUsuarios.adicionar(usuario);
    }
}
