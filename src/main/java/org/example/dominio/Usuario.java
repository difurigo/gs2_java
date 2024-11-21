package org.example.dominio;

public class Usuario {
    private String nome;
    private String email;
    private int id;
    private String senha;

    // Construtor padrão necessário para desserialização de JSON
    public Usuario() {
    }

    // Construtor que inicializa os atributos
    public Usuario(int idUsuario, String nomeUsuario, String emailUsuario, String senhaUsuario) {
        this.id = idUsuario;
        this.nome = nomeUsuario;
        this.email = emailUsuario;
        this.senha = senhaUsuario;
    }

    // Getters e setters
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
