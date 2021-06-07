/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import informacoes.InformacoesDoSistema;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Computador;
import model.Usuario;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author fehig
 */
public final class ControllerSessao {

    private Usuario usuarioAtivo;
    private Computador computadorUtilizado;
    private static ControllerSessao sessaoAtiva;    
    private final InformacoesDoSistema info;
    private final ControllerBancoDeDados comandosSQL;
    //private ControllerLogin sessaoAtiva;
    private ControllerLog logger;

    public ControllerSessao(Usuario usuario) throws IOException {
        logger = ControllerLog.getLogAtivo();
        this.info = new InformacoesDoSistema();        
        this.comandosSQL = new ControllerBancoDeDados();
        this.usuarioAtivo = usuario;
        this.computadorUtilizado = buscarComputador();
        this.sessaoAtiva = this;
    }

    public Computador buscarComputador() throws IOException {
        // Pegar hostname do computador local
        String hostname = "Sem hostname";

        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ControllerSessao.class.getName()).log(Level.SEVERE, null, ex);
            logger.gravarDadosLog("ERRO", "Não foi possivel coletar hostname da máquina.");
        }

        // Verificar se existe hostname cadastrado no banco igual ao hostname obitido
        List<Computador> computadores = comandosSQL.selectAllFromComputadorWhereHostnameLike(hostname);
        
        if (computadores.isEmpty()) {
            logger.gravarDadosLog("INFO", "A máquina(" + hostname + ") não está cadastrada.");
            // Insert do computador que ainda não está cadastrado
            // Instânciar um computador novo e retorna-lo
            // Importar get info
            Integer fkEmpresa = usuarioAtivo.getFkEmpresa();

            Computador computador = new Computador();
            computador.setFkEmpresa(fkEmpresa);
            computador.setHostname(hostname);
            computador.setSistemaOperacional(info.getSistemaOperacional());
            
            comandosSQL.insertIntoComputador(computador.getHostname(), computador.getSistemaOperacional() , computador.getFkEmpresa());                       
            computadores = comandosSQL.selectAllFromComputadorWhereHostnameLike(hostname);
        }
        comandosSQL.insertIntoAcesso(usuarioAtivo.getIdUsuario(), computadores.get(0).getIdComputador());
        logger.gravarDadosLog("INFO", "O usuário " + usuarioAtivo.getUsername() + " está ultilizando a máquina (" + hostname + ").");
        logger.gravarDadosLog("INFO", "Dados da máquina atual: Sistema operacional(" + computadores.get(0).getSistemaOperacional() + ") Hostname(" + computadores.get(0).getHostname() + ")");
        return computadores.get(0);
        
    }

    public static ControllerSessao getSessaoAtiva() {
        return sessaoAtiva;
    }

    public Computador getComputadorUtilizado() {
        return computadorUtilizado;
    }

    public Usuario getUsuarioAtivo() {
        return usuarioAtivo;
    }

    public void setUsuarioAtivo(Usuario usuarioAtivo) {
        this.usuarioAtivo = usuarioAtivo;
    }

    public void setComputadorUtilizado(Computador computadorUtilizado) {
        this.computadorUtilizado = computadorUtilizado;
    }
    
    public InformacoesDoSistema getInfo() {
        return info;
    }
}
