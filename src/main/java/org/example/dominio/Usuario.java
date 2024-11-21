package org.example.dominio;

public class Usuario {
    private String nome;
    private String email;
    private int id;


    // Construtor padrão necessário para a desserialização
    //Sem o construtor padrão, a biblioteca JSON não consegue criar o objeto vazio e, por isso, lança o erro:
    //Cannot construct instance of 'org.example.dominio.Usuario'.
    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
