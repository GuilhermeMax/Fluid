/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.teste.api;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processos.Processo;
import com.github.britooo.looca.api.group.processos.ProcessosGroup;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fehig
 */
public class EncerramentoDeProcessos {

    Looca looca;
    ProcessosGroup grupoDeProcessos;
    List<Processo> processos;

    public EncerramentoDeProcessos() {
        looca = new Looca();
        grupoDeProcessos = looca.getGrupoDeProcessos();
        processos = grupoDeProcessos.getProcessos();

    }

    public List<Processo> getProcessosPesados() {
        List<Processo> processosPesados = new LinkedList<>();
       
        for (Processo processo : processos) {
            if (processo.getMemoriaVirtualUtilizada() > 450000000) {
                processosPesados.add(processo);
            }
        }

        return processosPesados;
    }

    public static void main(String[] args) {
        Looca looca = new Looca();

        System.out.println("\n----- Processos -----");
        ProcessosGroup grupoDeProcessos = looca.getGrupoDeProcessos();
        List<Processo> processos = grupoDeProcessos.getProcessos();

        // Seção para testes
        for (Processo processo : processos) {
            if (processo.getMemoriaVirtualUtilizada() > 450000000) {
                System.out.println("\n" + processo);
            }
        }

        try {
            String tokill = "20300";
            String cmd = "taskkill /F /T /PID " + tokill;
            Runtime.getRuntime().exec(cmd);
        } catch (IOException ex) {
            Logger.getLogger(EncerramentoDeProcessos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
