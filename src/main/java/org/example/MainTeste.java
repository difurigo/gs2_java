package org.example;

import org.example.dominio.TarifaEnergia;
import org.example.infra.dao.TarifaEnergiaDAO;

import java.util.ArrayList;

public class MainTeste {
    public static void main(String[] args) {
        TarifaEnergiaDAO dao = new TarifaEnergiaDAO();
        ArrayList<TarifaEnergia> tarifas = dao.recuperarTarifas();
        dao.fechar();

        for (TarifaEnergia tarifa : tarifas) {
            System.out.println(tarifa);
        }
    }
}
