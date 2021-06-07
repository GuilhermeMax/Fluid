/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author fehig
 */
public class Computador {
    private Integer idComputador;
    private String hostname;
    private String tipoProcessador;
    private String sistemaOperacional;
    private Integer fkEmpresa;

    public Computador() {
    }

    public Integer getIdComputador() {
        return idComputador;
    }

    public String getHostname() {
        return hostname;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public Integer getFkEmpresa() {
        return fkEmpresa;
    }

    public void setIdComputador(Integer idComputador) {
        this.idComputador = idComputador;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    public void setFkEmpresa(Integer fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }

    public String getTipoProcessador() {
        return tipoProcessador;
    }

    public void setTipoProcessador(String tipoProcessador) {
        this.tipoProcessador = tipoProcessador;
    }
    
    
}
