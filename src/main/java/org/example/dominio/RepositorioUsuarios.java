package org.example.dominio;

public interface RepositorioUsuarios {
    public void adicionar(Usuario usuario);
    public void fechar();
    public Usuario buscarUsuarioPorEmail(String email);
}
