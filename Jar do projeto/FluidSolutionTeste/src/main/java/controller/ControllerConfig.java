/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileNotFoundException;
import view.TelaConfiguracao;

/**
 *
 * @author miguel.assuncao
 */
public class ControllerConfig {
    private ControllerLog logger;
    private TelaConfiguracao telaConfiguracao;

    public ControllerConfig(TelaConfiguracao telaConfiguracao) throws FileNotFoundException {
        this.telaConfiguracao = telaConfiguracao;
        logger = ControllerLog.getLogAtivo();
    }
    
    public void habilitarLogErro(){
        logger.setLogErro(true);
    }
    
    public void desabilitarLogErro(){
        logger.setLogErro(false);
    }
    
    public void habilitarLogBanco(){
        logger.setLogBanco(true);
    }
    
    public void desabilitarLogBanco(){
        logger.setLogBanco(false);
    }
}
