/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author fehig
 */
public class ControllerDashboard {

    // private final TelaDash teladash;
    private final ControllerSessao sessaoAtiva;
    private final ControllerBancoDeDados comandosSQL;
    private ControllerLog logger;

    public ControllerDashboard() throws FileNotFoundException {
        logger = ControllerLog.getLogAtivo();
        //this.teladash = teladash;
        sessaoAtiva = ControllerSessao.getSessaoAtiva();
        comandosSQL = new ControllerBancoDeDados();
    }

    public void insertDados(Double porcentagemRAM, Double porcentagemCPU, Double tempGpu) throws IOException {
        Integer cpu = 1;
        Integer ram = 2;
        Integer gpu = 3;

        // Pegar o id do computador
        Integer idComputador = sessaoAtiva.getComputadorUtilizado().getIdComputador();

        // Insert para CPU e RAM
        String insertStatementCpuRam = "insert into dado (usoEmPorcentagem, fkHardware, fkComputador) values (?, ?, ?);";

        // Insert para GPU
        String insertStatementGpu = "insert into dado (temperatura, fkHardware, fkComputador) values (?, ?, ?);";

        // Insert CPU        
        comandosSQL.insertIntoDado(insertStatementCpuRam, porcentagemCPU, cpu, idComputador);

        // Insert RAM
        comandosSQL.insertIntoDado(insertStatementCpuRam, porcentagemRAM.shortValue(), ram, idComputador);

        // Insert GPU
        if (tempGpu >= 0) { // Verifica se a máquina possui placa de video caso não tenha o insert não sera feito
            comandosSQL.insertIntoDado(insertStatementGpu, tempGpu, gpu, idComputador);
        }

        // Insert CPU - AWS
        comandosSQL.insertIntoDadoAws(insertStatementCpuRam, porcentagemCPU, cpu, idComputador);

        // Insert RAM - AWS
        comandosSQL.insertIntoDadoAws(insertStatementCpuRam, porcentagemRAM.shortValue(), ram, idComputador);

        // Insert GPU - AWS
        if (tempGpu >= 0) { // Verifica se a máquina possui placa de video caso não tenha o insert não sera feito
            comandosSQL.insertIntoDadoAws(insertStatementGpu, tempGpu, gpu, idComputador);
        }

    }

    public void insertDadosDisco(Double porcentagemUso, Integer fkHardware) throws IOException {
        // Pegar o id do computador
        Integer idComputador = sessaoAtiva.getComputadorUtilizado().getIdComputador();

        String insertStatementDisco = "insert into dado (usoEmPorcentagem, fkHardware, fkComputador) values (?, ?, ?);";
        
        // Insert Disco
        comandosSQL.insertIntoDado(insertStatementDisco, porcentagemUso, fkHardware, idComputador);
        comandosSQL.insertIntoDadoAws(insertStatementDisco, porcentagemUso, fkHardware, idComputador); 
    }
}
