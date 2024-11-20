package org.example.dominio;

public class Usuario {
    private String nome;
    private String email;
    private RepositorioUsuarios repositorioUsuarios;

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return repositorioUsuarios.buscarIdUsuario(email);
    }
}
