package org.example.dominio;

import java.util.ArrayList;

public class Residencia {
    private int residenciaId;
    private int idUsuario;
    private String tipoResidencia;
    private Endereco endereco;
    private int numMoradores;
    private int numResidencia;

    public Residencia(int residenciaId, int idUsuario, String tipoResidencia, Endereco endereco, int numMoradores, int numResidencia) {
        this.residenciaId = residenciaId;
        this.idUsuario = idUsuario;
        this.tipoResidencia = tipoResidencia;
        this.endereco = endereco;
        this.numMoradores = numMoradores;
        this.numResidencia = numResidencia;
    }

    public int getResidenciaId() {
        return residenciaId;
    }

    public void setResidenciaId(int residenciaId) {
        this.residenciaId = residenciaId;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipoResidencia() {
        return tipoResidencia;
    }

    public void setTipoResidencia(String tipoResidencia) {
        this.tipoResidencia = tipoResidencia;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public int getNumMoradores() {
        return numMoradores;
    }

    public void setNumMoradores(int numMoradores) {
        this.numMoradores = numMoradores;
    }

    public int getNumResidencia() {
        return numResidencia;
    }

    public void setNumResidencia(int numResidencia) {
        this.numResidencia = numResidencia;
    }
}

