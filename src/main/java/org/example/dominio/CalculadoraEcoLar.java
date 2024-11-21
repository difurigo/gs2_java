package org.example.dominio;

import org.example.infra.dao.BemDAO;
import org.example.infra.dao.GastoEnergeticoDAO;

import java.util.ArrayList;

public class CalculadoraEcoLar {
    private RepositorioTarifas repositorioTarifas;
    private Residencia residencia;
    private double emissao;
    private double gasto;
    private ArrayList<Bem> bens;

    public CalculadoraEcoLar(RepositorioTarifas repositorioTarifas, Residencia residencia) {
        this.repositorioTarifas = repositorioTarifas;
        this.residencia = residencia;
    }

    private double calcularEmissaoCO2(ArrayList<Bem> bens) {
        double totalEmissaoCO2 = 0.0;

        for (Bem b : bens) {
            if (b.getTipoBem().equals("Eletrodomestico")) {
                // Multiplica a emissão por hora pelo número de horas de uso diário
                totalEmissaoCO2 += b.getEmissaoCO2() * b.getHorasDiarias();
            } else if (b.getTipoBem().equals("Gas")) {
                // Consumo médio horário ajustado para gases (em litros/hora)
                double consumoHorario = 0.2; // Ajuste o consumo médio horário
                totalEmissaoCO2 += b.getEmissaoCO2() * b.getQuantidadeMensal();
            } else if (b.getTipoBem().equals("Carro")) {
                // Considera a emissão por hora do carro multiplicada pelas horas diárias
                totalEmissaoCO2 += b.getEmissaoCO2() * b.getKmMensal();
            }
        }
        return totalEmissaoCO2;
    }

    private double calcularGastoEnergia(ArrayList<Bem> bens) {
        double totalGastoEnergetico = 0.0;

        for (Bem b : bens) {
            if (b.getTipoBem().equals("Eletrodomestico")) {
                totalGastoEnergetico += b.getPotencia() * b.getHorasDiarias();
            }
        }

        return totalGastoEnergetico;
    }

    public double getEmissao (int idResidencia) {
        bens = new BemDAO().buscarBens(idResidencia);
        emissao = calcularEmissaoCO2(bens);
        return emissao;
    }

    public double getGasto (int idResidencia) {
        bens = new BemDAO().buscarBens(idResidencia);
        gasto = calcularGastoEnergia(bens);
        return gasto;
    }
}
