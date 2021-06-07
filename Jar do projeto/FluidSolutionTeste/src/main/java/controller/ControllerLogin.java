/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Usuario;
import view.TelaDash;
import view.TelaLogin;

/**
 *
 * @author fehig
 */
public class ControllerLogin {

    private final TelaLogin telaLogin;
    private ControllerSessao sessaoAtiva;
    private ControllerBancoDeDados comandosSQL;
    private ControllerLog logger;

    public ControllerLogin(TelaLogin telaLogin) throws FileNotFoundException, IOException {
        // logger = new ControllerLog();
        logger = ControllerLog.getLogAtivo();
        logger.gravarDadosLog("INFO", "Arquivo de log criado.");
        this.telaLogin = telaLogin;      
        comandosSQL = new ControllerBancoDeDados();
        
    }

    public void fazerLogin() throws FileNotFoundException, IOException {
        //Pegar um usuario da telaLogin
        String usernameInserido = telaLogin.getTextUsername().getText();
        String senhaInserida = telaLogin.getTextSenha().getText();
        Boolean loginAprovado = false;

        // Usuario usuario = new Usuario(0, usernameInserido, senhaInserida);
        List<Usuario> usuarios = comandosSQL.selectAllFromUsuario();

        

        for (Usuario u : usuarios) {
            String username = u.getUsername();
            String senha = u.getSenha();

            if (usernameInserido.equals(username) && senhaInserida.equals(senha)) {
                System.out.println("Login efetuado");
                loginAprovado = true;
                logger.gravarDadosLog("INFO", "Usuário " + u.getUsername() + " foi autenticado com sucesso.");

                sessaoAtiva = new ControllerSessao(u);

                new TelaDash().setVisible(true);
                // Insert na tabela acesso
                // Select projetos
                this.telaLogin.setVisible(false);
                break;
            }
        }

        if (!loginAprovado) {
            System.out.println("Senha ou usuario incorretos");
            telaLogin.getLoadingGif().setVisible(false);
            telaLogin.getLblErro().setVisible(true);
            logger.gravarDadosLog("INFO", "Usuário ou senha informado não foi encontrado.");
        }
    }

    public ControllerSessao getSessaoAtiva() {
        return sessaoAtiva;
    }

}
