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
public class Usuario {
    private Integer idUsuario;
    private String username;
    private String senha;
    private Integer fkTipoUsuario;
    private Integer fkEmpresa;

    public Usuario() {
    }

    public Usuario(Integer idUsuario, String username, String senha, Integer fkTipoUsuario,Integer fkEmpresa) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.senha = senha;
        this.fkTipoUsuario  = fkTipoUsuario;
        this.fkEmpresa = fkEmpresa;
    }
    
    
    
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(Integer fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }
    
        public Integer getFkTipoUsuario() {
        return fkTipoUsuario;
    }
    
    public void setFkTipoUsuario(Integer fkTipoUsuario) {
        this.fkTipoUsuario = fkTipoUsuario;
    }
    
    // Settar o tipo usuario(cargo) de acordo com a fkTipoUsuario retornada
    // os IDs dos campos sempre serão esses para os cargos
    public String getTipoUsuario() {
        String tipoUsuario = "";
        switch(fkTipoUsuario){
            case 1:
                tipoUsuario = "Administrador";
                break;
            case 2:
                tipoUsuario = "Estagiário";
                break;
            case 3:
                tipoUsuario = "Tecnico";
                break;
            default:
                tipoUsuario = "Cargo inválido";
                break;
        }
        return tipoUsuario;
    }
    
    @Override
    public String toString() {
        return   "id=" + idUsuario + ", username=" + username + ", senha=" + senha + ", fkEmpresa=" + fkEmpresa;
    }
                
}
