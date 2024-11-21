package org.example.dominio;

import java.util.ArrayList;

public interface RepositorioBem {


    void adcionarEletrodomestico(Bem bem);

    void adcionarCarro(Bem bem);

    void adcionarGas(Bem bem);

    void fechar();
     ArrayList<Bem> buscarBens(int idResidencia);
}
