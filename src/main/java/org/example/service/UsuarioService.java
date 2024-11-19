package org.example.service;

import org.example.dominio.RepositorioUsuarios;
import org.example.dominio.Usuario;

public class UsuarioService {
    private RepositorioUsuarios repositorioUsuarios;

    public UsuarioService(RepositorioUsuarios repositorioUsuarios) {
        this.repositorioUsuarios = repositorioUsuarios;
    }

    public void adicionar(Usuario usuario) {
        repositorioUsuarios.adicionar(usuario);
        repositorioUsuarios.fechar();
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        Usuario usuario = repositorioUsuarios.buscarUsuarioPorEmail(email);
        repositorioUsuarios.fechar();
        return usuario;
    }
}
