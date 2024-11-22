package org.example.dominio;

import java.util.List;

public interface RepositorioUsuarios {
    void adicionar(Usuario usuario);

    void fechar();

    Usuario buscarUsuarioPorEmail(String email);

    List<Usuario> listarTodos(); // Apenas a assinatura do método

    Usuario buscarUsuarioPorId(int idUsuario);

    Usuario buscarUsuarioPorNome(String nomeUsuario);

    int buscarIdUsuario(String email);
}
