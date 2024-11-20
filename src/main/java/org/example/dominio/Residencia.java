package org.example.dominio;

import java.util.ArrayList;

public class Residencia {
    private Usuario usuario;
    private RepositorioUsuarios repositorioUsuarios;
    private Endereco endereco;
    private ArrayList<Bem> bens;
    private int numMoradores;

    public Residencia(int idUsuario, Endereco endereco, int numMoradores) {
        this.usuario = repositorioUsuarios.buscarUsuarioPorId(idUsuario);
        this.endereco = endereco;
        this.numMoradores = numMoradores;
        this.bens = new ArrayList<Bem>();
    }

    public void addBem(Bem bem) {
        bens.add(bem);
    }

    public ArrayList<Bem> getBens() {
        return bens;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public int getIdUsuario() {
        return usuario.getId();
    }

    public int getNumMoradores() {
        return numMoradores;
    }
}
