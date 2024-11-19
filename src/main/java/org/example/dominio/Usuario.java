package org.example.dominio;

public class Usuario {
    private String nome;
    private String email;
    private Endereco endereco;
    private Residencia residencia;

    public Usuario(String nome, String email, Endereco endereco, Residencia residencia) {
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.residencia = residencia;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco.toString();
    }

    public int getIdResidencia() {
        return 0; // residencia.getId();
    }
}
