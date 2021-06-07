/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

/**
 *
 * @author fehig
 */
public class ControllerConnectionAzure {

    private final BasicDataSource dataSource;

    // Construtor
    public ControllerConnectionAzure() {        
        dataSource = new BasicDataSource();
        dataSource​.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource​.setUrl("jdbc:sqlserver://servergrupo9.database.windows.net:1433;database=Grupo9;user=fluid@servergrupo9;password={#Gfgrupo9};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
        dataSource​.setUsername("fluid");
        dataSource​.setPassword("#Gfgrupo9");
    }

    // Getter do dataSource
    public BasicDataSource getDataSource() {
        return dataSource;
    }

    public DataSource getDataSouce() {
        return dataSource;
    }
}
