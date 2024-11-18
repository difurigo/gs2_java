package org.example.dominio;

import java.util.ArrayList;

public class Residencia {
    private Endereco endereco;
    private ArrayList<Bem> bens;
    private int numMoradores;

    public Residencia(Endereco endereco, int numMoradores) {
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
}
