package org.example.dominio;

import java.util.ArrayList;

public class CalculadoraEcoLar {
    private RepositorioTarifas repositorioTarifas;
    private Residencia residencia;
    private double emissao;
    private double gasto;

    public CalculadoraEcoLar(RepositorioTarifas repositorioTarifas, Residencia residencia) {
        this.repositorioTarifas = repositorioTarifas;
        this.residencia = residencia;
    }

    private double calcularEmissaoCO2(Residencia residencia) {
        ArrayList<Bem> bens = residencia.getBens();
        double totalEmissaoCO2 = 0.0;

        for (Bem b : bens) {
            if (b instanceof Eletrodomestico) {
                Eletrodomestico eletro = (Eletrodomestico) b;
                // Multiplica a emissão por hora pelo número de horas de uso diário
                totalEmissaoCO2 += eletro.getEmissaoCO2h() * eletro.getHorasUsoDiario();
            } else if (b instanceof Gas) {
                Gas gas = (Gas) b;
                // Consumo médio horário ajustado para gases (em litros/hora)
                double consumoHorario = 0.2; // Ajuste o consumo médio horário
                totalEmissaoCO2 += gas.getEmissaoCO2h() * gas.getHorasUsoDiario();
            } else if (b instanceof Carro) {
                Carro carro = (Carro) b;
                // Considera a emissão por hora do carro multiplicada pelas horas diárias
                totalEmissaoCO2 += carro.getEmissaoCO2h() * carro.getHorasUsoDiario();
            }
        }
        return totalEmissaoCO2;
    }

    private double calcularGastoEnergia(Residencia residencia) {
        ArrayList<Bem> bens = residencia.getBens();
        double totalGastoEnergetico = 0.0;

        for (Bem b : bens) {
            if (b instanceof Eletrodomestico) {
                Eletrodomestico eletro = (Eletrodomestico) b;
                totalGastoEnergetico += eletro.getPotencia() * eletro.getHorasUsoDiario();
            } else if (b instanceof Gas) {
                totalGastoEnergetico += 0;
            } else if (b instanceof Carro) {
                Carro carro = (Carro) b;
                totalGastoEnergetico += carro.getConsumoEnergetico() * carro.getHorasUsoDiario();
            }
        }
        return totalGastoEnergetico;
    }

    public double getEmissao() {
        emissao = calcularEmissaoCO2(residencia);
        return emissao;
    }

    public double getGasto() {
        gasto = calcularGastoEnergia(residencia);
        return gasto;
    }
}
