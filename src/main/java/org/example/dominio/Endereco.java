package org.example.dominio;

public class Endereco {
    private String cep;
    private String logradouro;
    private String numero;
    private String cidade;
    private String estado;

    @Override
    public String toString() {
        return logradouro + ", " + numero + " - " + cidade + " (" + estado + ")";
    }
}
