/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author miguel.assuncao
 */
public class ControllerConnectionLocal {
    private final BasicDataSource dataSource;

    // Construtor
    public ControllerConnectionLocal() {
        dataSource = new BasicDataSource();
        dataSource​.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource​.setUrl("jdbc:mysql://172.17.0.1:3306/bdFluid?useTimezone=true&serverTimezone=UTC");
        dataSource​.setUsername("root");
        dataSource​.setPassword("#Gfgrupo9");
        //dataSource​.setUsername("fluid");
        //dataSource​.setPassword("#Gfgrupo9");
    }

    // Getter do dataSource
    public BasicDataSource getDataSource() {
        return dataSource;
    }

    public DataSource getDataSouce() {
        return dataSource;
    }
}
