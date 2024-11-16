package org.example.dominio;

import java.util.ArrayList;

public interface RepositorioTarifas {
    public ArrayList<TarifaEnergia> recuperarTarifas();
    public void fechar();
}
