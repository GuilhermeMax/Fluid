/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.teste.api;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscosGroup;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.processos.Processo;
import com.github.britooo.looca.api.group.processos.ProcessosGroup;
import com.github.britooo.looca.api.group.servicos.Servico;
import com.github.britooo.looca.api.group.servicos.ServicosGroup;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.group.temperatura.Temperatura;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author fehig
 */
public class TesteAPI {

    public static void main(String[] args) throws IOException {
        Looca looca = new Looca();

        //Instanciamento do objeto sistema
        Sistema sistema = looca.getSistema();
        
        System.out.println("----- Sistema -----");
        System.out.println(sistema);
        

//        String fabricante = sistema.getFabricante();;
//        System.out.println(fabricante);
        //Capturar dados do RAM        
        Memoria memoria = looca.getMemoria();
        System.out.println("\n----- Memoria RAM -----");
        System.out.println(memoria);

        //Capturar informações de temperatura
        Temperatura temperatura = looca.getTemperatura();

        System.out.println(temperatura);

        //Capturar dados do processador
        System.out.println("\n----- Processador -----");
        Processador processador = looca.getProcessador();
        System.out.println(processador);

        //Capturar grupos de disco HD
        System.out.println("\n----- Disco -----");
        DiscosGroup grupoDeDiscos = looca.getGrupoDeDiscos();

        List<Disco> discos = grupoDeDiscos.getDiscos();

        System.out.println(discos.get(0));

        long tamanhoDoDisco = discos.get(0).getTamanho();

        System.out.println("Tamanho do HD:" + tamanhoDoDisco);

        // Capturar dados de serviço
        System.out.println("\n----- Serviços -----");
        ServicosGroup grupoDeServicos = looca.getGrupoDeServicos();

        List<Servico> servicos = grupoDeServicos.getServicosAtivos();

        for (Servico servico : servicos) {;
            System.out.println(servicos);
        }

        //Captura de dados de processos
        System.out.println("\n----- Processos -----");
        ProcessosGroup grupoDeProcessos = looca.getGrupoDeProcessos();
        List<Processo> processos = grupoDeProcessos.getProcessos();

        // Seção para testes
        for (Processo processo : processos) {
            System.out.println("\n" + processo);
        }
                        
        // Filtros para exibição de processos especificos
        for (int count = 0; count < processos.size(); count++) {
            String nomeProcesso = processos.get(count).getNome();
            Boolean processoPesado = processos.get(count).getBytesUtilizados() > 100523904;

            //exibir apenas considerados que utilizam muita memória
            if (processoPesado) {
                System.out.println(processos.get(count));
            }

            //Exibe processos com nome especifico
            if ("vegas150".equals(nomeProcesso)) {
                System.out.println(processos.get(count));
            }

            //Double usoCpuEditor = processos.get(posicaoEditor).getUsoCpu();
        }

        
        // Guardar a posição do processo do editor na lista
        for (int count = 0; count < processos.size(); count++) {
            String nomeProcesso = processos.get(count).getNome();

            if ("vegas150".equals(nomeProcesso)) {
                int posicaoEditor = count;
            }
        }
        //Double usoCpuEditor = processos.get(posicaoEditor).getUsoCpu();
        
        // Matar processos através de comandos +java
        // LocalShell shell = new LocalShell();
        // shell.executeCommand("taskkill /F /PID 18336");

    }
}
