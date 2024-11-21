package org.example.dominio;

public interface RepositorioUsuarios {
    void adicionar(Usuario usuario);
    void fechar();
    Usuario buscarUsuarioPorEmail(String email);
    Usuario buscarUsuarioPorId(int idUsuario);

    Usuario buscarUsuarioPorNome(String nomeUsuario);

    int buscarIdUsuario(String email);
}
