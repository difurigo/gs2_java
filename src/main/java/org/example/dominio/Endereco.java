package org.example.dominio;

public class Endereco {
    private String cep;
    private String logradouro;
    private String cidade;
    private String estado;

    public Endereco(String cep, String logradouro, String cidade, String estado) {
    }

    public Endereco() {
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return logradouro + ", " + cidade + " - " + estado + ", CEP: " + cep;
    }
}

