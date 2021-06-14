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
    private int idRegistro;
    private final InformacoesDoSistema info;
    // booleans para definir o que vai ser logado 
    private Boolean logBanco;
    private Boolean logErro;
    private Boolean logSlack;
    private DateTimeFormatter dtf;

    private ControllerLog() throws FileNotFoundException {
        info = new InformacoesDoSistema();
        dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss"); // Formatar data coletada
        if(info.getSistemaOperacional().equals("windows")){ 
            enderecoDiretorio = String.format("C:\\Users\\%s\\Desktop\\Fluid\\Logs", System.getProperty("user.name"));
        } else { // endereco do diretorio caso o sistema seja linux
            enderecoDiretorio = String.format("/home/%s/Desktop/Fluid/Logs", System.getProperty("user.name"));
        }
        
        File fluidLog = new File(enderecoDiretorio); // Instacia o diretório que ira armazenar os arquivos de log

        if (!fluidLog.exists()) { // Se o diretório não existir chama a função para criar (alterar para quando usuário ultilizar linux)
            fluidLog.mkdir();
        }
       
        if (dataHoraCriacao == null) {
            dataHoraCriacao = dtf.format(LocalDateTime.now());
        }
        
        if(logBanco == null){
            logBanco = true;
        }
        
        if(logErro == null){
            logErro = true;
        }
        
        this.idRegistro = 0;
    }
    
    public static synchronized ControllerLog getLogAtivo() throws FileNotFoundException {
        if (logAtivo == null) {
            logAtivo = new ControllerLog();
        }
        return logAtivo;
    }

    // Verifica se o tamanho do arquivo de log
    public void verificarTamanhoLog() throws IOException {
        if (log.length() > 200000) { // Se for maior que 200000 bytes outro arquivo de log é criado
            gravarDadosLog("INFO", "Arquivo está grande demais, um novo arquivo de log será criado.");
            dataHoraCriacao = dtf.format(LocalDateTime.now());
            log = new RandomAccessFile(enderecoDiretorio + dataHoraCriacao + " LOG.dat", "rw");
            gravarDadosLog("INFO", "Continuação do arquivo de log: " + enderecoDiretorio + dataHoraCriacao + " LOG.dat");
            logAtivo = this;
        }
    }
    
    // Verificar "idade" do arquivo
    public void verificarIdadeLog(){
        
    }

    // Grava os dados ou informações no arquivo de log
    public void gravarDadosLog(String tipo, String desc) throws IOException {
        if(log == null){
            this.log = new RandomAccessFile(enderecoDiretorio + "\\" + dataHoraCriacao + " LOG.dat", "rw"); // Intãncia arquivo de log
        } else {
            log.close();
            this.log = new RandomAccessFile(enderecoDiretorio + "\\" + dataHoraCriacao + " LOG.dat", "rw"); // Intãncia arquivo de log
        }
        
        String dateTime =  LocalDateTime.now().toString();
        log.seek(log.length()); // Define que os novos registros serão feitos apartir do final do arquivo
        idRegistro++;
        if (tipo.equals("INFO")) {
            log.writeBytes("[" + idRegistro + "] " + dateTime + " [INFORMATION] " + desc + "\n");
        } else {
            if(logErro){
                log.writeBytes("[" + idRegistro + "] " + dateTime + " [CRITICAL] " + desc + "\n");
            }
        }

        verificarTamanhoLog(); // Chama metodo sempre que um novo resgitro é feito

        log.close(); // Fecha arquivo de log depois que os registros foram feitos
    }
    
    public void gravarDadosLog(String tipo, String operacao, String desc) throws IOException {
        if (logBanco) {
            if (log == null) {
                this.log = new RandomAccessFile(enderecoDiretorio + "\\" + dataHoraCriacao + " LOG.dat", "rw"); // Intãncia arquivo de log
            } else {
                log.close();
                this.log = new RandomAccessFile(enderecoDiretorio + "\\" + dataHoraCriacao + " LOG.dat", "rw"); // Intãncia arquivo de log
            }
            String dateTime =  LocalDateTime.now().toString();
            log.seek(log.length()); // Define que os novos registros serão feitos apartir do final do arquivo
            idRegistro++;
            if (tipo.equals("INFO")) {
                log.writeBytes("[" + idRegistro + "] " + dateTime + " [INFORMATION] [" + operacao + "] " + desc + "\n");
            } else {
                if (logErro) {
                    log.writeBytes("[" + idRegistro + "] " + dateTime +  " [CRITICAL] [" + operacao + "] " + desc + "\n");
                }
            }

            verificarTamanhoLog(); // Chama metodo sempre que um novo resgitro é feito

            log.close(); // Fecha arquivo de log depois que os registros foram feitos
        }
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

    public Boolean getLogBanco() {
        return logBanco;
    }

    public void setLogBanco(Boolean logBanco) {
        this.logBanco = logBanco;
    }

    public Boolean getLogErro() {
        return logErro;
    }

    public void setLogErro(Boolean logErro) {
        this.logErro = logErro;
    }

    public Boolean getLogSlack() {
        return logSlack;
    }

    public void setLogSlack(Boolean logSlack) {
        this.logSlack = logSlack;
    }
}
