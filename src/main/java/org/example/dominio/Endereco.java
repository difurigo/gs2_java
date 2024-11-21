package org.example.dominio;

public class Endereco {
    private String cep;
    private String logradouro;
    private String cidade;
    private String estado;

    public Endereco(String cep, String logradouro, String cidade, String estado) {
    }

    @Override
    public String toString() {
        return logradouro + ", " + cidade + " - " + estado + ", CEP: " + cep;
    }
}

