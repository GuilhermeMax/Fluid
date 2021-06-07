/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import informacoes.InformacoesDoSistema;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author miguel.assuncao
 */
public class ControllerLog {

    private String dataHoraCriacao;
    private RandomAccessFile log; // Intancia arq de log
    // private DataOutputStream gravarArq; // Grava dados no arq de log
    private static ControllerLog logAtivo;
    private final String enderecoDiretorio;
    private Integer idRegistro;
    private final InformacoesDoSistema info;

    private ControllerLog() throws FileNotFoundException {
        info = new InformacoesDoSistema();
        if(info.getSistemaOperacional().equals("windows")){ 
            enderecoDiretorio = String.format("C:\\Users\\%s\\Desktop\\Fluid\\Log", System.getProperty("user.name"));
        } else { // endereco do diretorio caso o sistema seja linux
            enderecoDiretorio = String.format("/home/%s/Desktop/Fluid/Log", System.getProperty("user.name"));
        }
        
        File fluidLog = new File(enderecoDiretorio); // Instacia o diretório que ira armazenar os arquivos de log

        if (!fluidLog.exists()) { // Se o diretório não existir chama a função para criar (alterar para quando usuário ultilizar linux)
            fluidLog.mkdir();
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss"); // Formatar data coletada
        if (dataHoraCriacao == null) {
            this.dataHoraCriacao = dtf.format(LocalDateTime.now());
        }
        this.idRegistro = 0;
        this.logAtivo = this;
    }
    
    public static synchronized ControllerLog getLogAtivo() throws FileNotFoundException {
        if (logAtivo == null) {
            logAtivo = new ControllerLog();
        }
        return logAtivo;
    }

    // Verifica se o tamanho do arquivo de log
    public void verificarTamanhoLog() throws IOException {
        if (log.length() > 20000) { // Se for maior que 20000 bytes outro arquivo de log é criado
            gravarDadosLog("INFO", "Arquivo está grande demais, um novo arquivo de log será criado.");
            log = new RandomAccessFile(enderecoDiretorio + dataHoraCriacao + " LOG.txt", "rw");
            gravarDadosLog("INFO", "Continuação do arquivo de log: " + enderecoDiretorio + dataHoraCriacao + " LOG.txt");
            this.logAtivo = this;
        }
    }

    // Grava os dados ou informações no arquivo de log
    public void gravarDadosLog(String tipo, String desc) throws IOException {
        this.log = new RandomAccessFile(enderecoDiretorio + "\\" + dataHoraCriacao + " LOG.txt", "rw"); // Intãncia arquivo de log

        log.seek(log.length() + 2); // Define que os novos registros serão feitos apartir do final do arquivo
        idRegistro++;

        if (tipo.equals("INFO")) {
            log.writeBytes(idRegistro + "  " + LocalDateTime.now() + "  " + "INFO  " + desc + "\n");
        } else {
            log.writeBytes(idRegistro + "  " + LocalDateTime.now() + "  " + "ERRO  " + desc + "\n");
        }

        verificarTamanhoLog(); // Chama metodo sempre que um novo resgitro é feito

        log.close(); // Fecha arquivo de log depois que os registros foram feitos
    }

    public static void setLogAtivo(ControllerLog logAtivo) {
        ControllerLog.logAtivo = logAtivo;
    }

    public String getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(String dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public RandomAccessFile getLog() {
        return log;
    }

    public void setLog(RandomAccessFile log) {
        this.log = log;
    }
}
