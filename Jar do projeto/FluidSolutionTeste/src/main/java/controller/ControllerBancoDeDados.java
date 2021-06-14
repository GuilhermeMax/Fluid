/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Computador;
import model.Projeto;
import model.Usuario;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author migue
 */
public class ControllerBancoDeDados {

    // Conexão para Azure
    private final ControllerConnectionAzure connectionConfig;
    private final JdbcTemplate con;
    // Conexão para MySQL
    private final ControllerConnectionLocal connectionConfigAws;
    private final JdbcTemplate conAws;
    private ControllerLog logger;
    private Boolean erro; 

    public ControllerBancoDeDados() throws FileNotFoundException{
        logger = ControllerLog.getLogAtivo();
        this.connectionConfig = new ControllerConnectionAzure();
        this.con = new JdbcTemplate(connectionConfig.getDataSouce());
        this.connectionConfigAws = new ControllerConnectionLocal();
        this.conAws = new JdbcTemplate(connectionConfigAws.getDataSouce());
        this.erro = false;
    }

    // Select e Insert na tabela projeto
    public List selectAllFromProjeto() throws IOException {
        erro = false;
        List projetos = null;
        try{
            projetos = con.query("Select * from projeto", new BeanPropertyRowMapper(Projeto.class));
        } catch (CannotGetJdbcConnectionException ex){
            logger.gravarDadosLog("ERRO","SELECT" , "Não foi possivel realizar a consulta dos projetos cadastrados." + Arrays.toString(ex.getStackTrace()));
            erro = true;
        }
        if(!erro){
            logger.gravarDadosLog("INFO", "SELECT" , "A busca por projetos cadastrados foi realizada com sucesso.");
        }
        erro = false;
        return projetos;
    }

    public void deleteFromProjeto(String nome, String etapa) throws IOException {
        erro = false;
        String query = "Delete from projeto where nomeProjeto = ? and etapaProjeto = ?";
        try{
            con.update(query,
                nome,
                etapa);
        } catch (CannotGetJdbcConnectionException ex){
            logger.gravarDadosLog("ERRO","DELETE" , "Não foi possivel realizar o delete do projeto." + Arrays.toString(ex.getStackTrace()));
            erro = true;
        }
        if(!erro){
            logger.gravarDadosLog("INFO", "DELETE" , "O projeto " + nome + " foi deletado com sucesso.");
        }
        erro = false;
    }

    public void insertIntoProjeto(String nomeProjeto, String etapaProjeto, Integer fkEmpresa, String data) throws IOException {
       erro = false;
        try {
            //this.dataEntrega = new SimpleDateFormat("dd/mm/yyyy").parse(data);
            java.util.Date utilDate = new SimpleDateFormat("dd/MM/yyyy").parse(data);
            
            Date dataSql = new Date(utilDate.getTime());
            
            con.update("insert into projeto(nomeProjeto, etapaProjeto, fkEmpresa, dataEntrega) values (?,?,?,?);",
                    nomeProjeto,
                    etapaProjeto,
                    fkEmpresa,
                    dataSql);

        } catch (ParseException ex) {
            Logger.getLogger(ControllerBancoDeDados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CannotGetJdbcConnectionException ex) {
            logger.gravarDadosLog("ERRO", "INSERT" , "Não foi possivel inserir um no projeto no banco de dados." + Arrays.toString(ex.getStackTrace()));
            erro = true;
        }
        if(!erro){
            logger.gravarDadosLog("INFO", "INSERT" , "O projeto " + nomeProjeto + " foi registrado com sucesso.");
        }
        erro = false;
    }

    // Mapear usuarios e computadores do banco de dados
    public List selectAllFromUsuario() throws IOException {
        erro = false;
        List usuarios = null;
        try {
            usuarios = con.query("Select * from usuario", new BeanPropertyRowMapper(Usuario.class));
        } catch (CannotGetJdbcConnectionException ex) {
            logger.gravarDadosLog("ERRO", "SELECT" , "Não foi possivel realizar a consulta dos usuário cadastrados." + Arrays.toString(ex.getStackTrace()));
            erro = true;
        }
        
        if(!erro){
            logger.gravarDadosLog("INFO", "SELECT" , "A busca de usuários cadastrados foi realizada com sucesso.");
        }
        erro = false;
        return usuarios;
    }
    
    public List selectAllFromComputadorWhereHostnameLike(String hostname) throws IOException {
        erro = false;
        List computadores = null;
        try{
            computadores = con.query("select * from computador where hostname = ?", new BeanPropertyRowMapper(Computador.class), hostname);
        } catch (CannotGetJdbcConnectionException ex){
            logger.gravarDadosLog("ERRO", "SELECT" , "Não foi possivel realizar a consulta dos computadores cadastrados." + Arrays.toString(ex.getStackTrace()));
            erro = true;
        }
        
        if(!erro){
            logger.gravarDadosLog("INFO", "SELECT" , "A busca de máquinas cadastradas foi realizada com sucesso.");
        }
        erro = false;
        return computadores;
    }
    
    // Cadastrar um nova máquina
    public void insertIntoComputador(String hostname, String sistemaOperacional, Integer fkEmpresa) throws IOException {
        erro = false;
        try{
            con.update("insert into computador(hostname, sistemaOperacional, fkEmpresa) values (?,?,?);", hostname, sistemaOperacional, fkEmpresa);
        } catch (CannotGetJdbcConnectionException ex){
            logger.gravarDadosLog("ERRO", "Não foi possivel cadastrar novo computador." + Arrays.toString(ex.getStackTrace()));
            erro = true;
        }
        
        if(!erro){
            logger.gravarDadosLog("INFO", "INSERT" , "A máquina(" + hostname + ") foi cadastrada com sucesso.");
        }
        erro = false;
    }

    // Insert de dados no banco da Azure
    public void insertIntoDado(String query, Double porcentagem, Integer fkHardware, Integer fkComputador) throws IOException {
        erro = false;
        try {
            con.update(query, porcentagem, fkHardware, fkComputador);
        } catch (CannotGetJdbcConnectionException ex) {
            logger.gravarDadosLog("ERRO","INSERT" , "Não foi possivel inserir dados no banco de dados na Azure" + Arrays.toString(ex.getStackTrace()));
            erro = true;
        }
        
        String hardware = "";
        switch (fkHardware) {
            case 1:
                hardware = "CPU";
                break;
            case 2:
                hardware = "RAM";
                break;
            case 3:
                hardware = "GPU";
                break;
            case 4:
                hardware = "Disco 1";
                break;
            case 5:
                hardware = "Disco 2";
                break;
            case 6:
                hardware = "Disco 3";
                break;
        }
        
        if(!erro){
            logger.gravarDadosLog("INFO", "INSERT" , "O dado coletado de " + hardware + "(" + porcentagem +  "%) foi registrado com sucesso.");
        }    
        erro = false;
    }

    public void insertIntoDado(String query, Short porcentagem, Integer fkHardware, Integer fkComputador) throws IOException {
        erro = false;
        try{
            con.update(query, porcentagem, fkHardware, fkComputador);
        } catch (CannotGetJdbcConnectionException ex){
            logger.gravarDadosLog("ERRO","INSERT" , "Não foi possivel inserir dados no banco de dados na Azure." + Arrays.toString(ex.getStackTrace()));
            erro = true;
        }
        
        String hardware = "";
        switch (fkHardware) {
            case 1:
                hardware = "CPU";
                break;
            case 2:
                hardware = "RAM";
                break;
            case 3:
                hardware = "GPU";
                break;
            case 4:
                hardware = "Disco 1";
                break;
            case 5:
                hardware = "Disco 2";
                break;
            case 6:
                hardware = "Disco 3";
                break;
        }
        if(!erro){
            logger.gravarDadosLog("INFO", "INSERT" , "O dado coletado de " + hardware + "(" + porcentagem +  "%) foi registrado com sucesso.");
        }    
        erro = false;
    }

    // Insert de dados no banco de dados local(Docker)
    public void insertIntoDadoAws(String query, Double porcentagem, Integer fkHardware, Integer fkComputador) throws IOException {
        erro = false;
        try{
            conAws.update(query, porcentagem, fkHardware, fkComputador);
        }  catch (CannotGetJdbcConnectionException ex) {
            //Logger.getLogger(ControllerDashboard.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Nao foi encontrado um banco de dados local.");
            logger.gravarDadosLog("ERRO","INSERT" , "Não foi possivel inserir dados no banco de dados local." + Arrays.toString(ex.getStackTrace()));
            erro = true;
        }
        String hardware = "";
        switch (fkHardware) {
            case 1:
                hardware = "CPU";
                break;
            case 2:
                hardware = "RAM";
                break;
            case 3:
                hardware = "GPU";
                break;
            case 4:
                hardware = "Disco 1";
                break;
            case 5:
                hardware = "Disco 2";
                break;
            case 6:
                hardware = "Disco 3";
                break;
        }
        if(!erro){
            logger.gravarDadosLog("INFO", "INSERT" , "O dado coletado de " + hardware + "(" + porcentagem +  "%) foi registrado com sucesso no banco de dados local.");
        }
        erro = false;
    }

    public void insertIntoDadoAws(String query, Short porcentagem, Integer fkHardware, Integer fkComputador) throws IOException {
        erro = false;
        try{
            conAws.update(query, porcentagem, fkHardware, fkComputador);
        }  catch (CannotGetJdbcConnectionException ex) {
            //Logger.getLogger(ControllerDashboard.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Nao foi encontrado um banco de dados local.");
            logger.gravarDadosLog("ERRO", "INSERT" ,"Não foi possivel inserir dados no banco de dados local." + Arrays.toString(ex.getStackTrace()));
            erro = true;
        }
        String hardware = "";
        switch (fkHardware) {
            case 1:
                hardware = "CPU";
                break;
            case 2:
                hardware = "RAM";
                break;
            case 3:
                hardware = "GPU";
                break;
            case 4:
                hardware = "Disco 1";
                break;
            case 5:
                hardware = "Disco 2";
                break;
            case 6:
                hardware = "Disco 3";
                break;
        }
        if(!erro){
            logger.gravarDadosLog("INFO", "INSERT" , "O dado coletado de " + hardware + "(" + porcentagem +  "%) foi registrado com sucesso no banco de dados local.");
        }
        erro = false;
    }
    
    // Insert da maquina e usuário na tabela acesso
    public void insertIntoAcesso(Integer idUsuario, Integer idComputador) throws IOException{
        erro = false;
        try{
            con.update("insert into acesso(fkComputador, fkUsuario) values (?,?)", idComputador, idUsuario);  
        } catch (CannotGetJdbcConnectionException ex) {
            logger.gravarDadosLog("ERRO","INSERT" , "Não foi possivel realizar insert do acesso do usuário." + Arrays.toString(ex.getStackTrace()));
            erro = true;
        }
        if(!erro){
            logger.gravarDadosLog("INFO", "INSERT" , "Registro do acesso foi realizado com sucesso.");
        }
    }
}
