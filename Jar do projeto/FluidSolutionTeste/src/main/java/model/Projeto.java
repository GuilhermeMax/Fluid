/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fehig
 */
public class Projeto {

    private Integer idProjeto;
    private String nomeProjeto;
    private String etapaProjeto;
    private Integer fkEmpresa;
    private Date dataEntrega;

    public Projeto() {
    }

    public Projeto( String nomeProjeto, String etapaProjeto, Integer fkEmpresa, String data) {        
        this.nomeProjeto = nomeProjeto;
        this.etapaProjeto = etapaProjeto;
        this.fkEmpresa = fkEmpresa;
        try {
            this.dataEntrega = new SimpleDateFormat("dd/mm/yyyy").parse(data);
        } catch (ParseException ex) {
            Logger.getLogger(Projeto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Integer getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(Integer idProjeto) {
        this.idProjeto = idProjeto;
    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }

    public String getEtapaProjeto() {
        return etapaProjeto;
    }

    public void setEtapaProjeto(String etapaProjeto) {
        this.etapaProjeto = etapaProjeto;
    }

    public Integer getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(Integer fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(java.sql.Timestamp dateSql) {
        try {
            this.dataEntrega = new SimpleDateFormat("yyyy-MM-dd").parse(dateSql.toString());
        } catch (ParseException ex) {
            Logger.getLogger(Projeto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getDataString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String stringDate = formatter.format(dataEntrega);
        return stringDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String stringDate = formatter.format(dataEntrega);

        return "Projeto{" + "idProjeto=" + idProjeto + ", nomeProjeto=" + nomeProjeto + ", etapaProjeto=" + etapaProjeto + ", fkEmpresa=" + fkEmpresa + ", dataEntrega=" + stringDate + '}';
    }

}
