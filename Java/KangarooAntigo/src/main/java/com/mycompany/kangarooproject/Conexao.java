package com.mycompany.kangarooproject;

import java.util.List;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

public class Conexao {
    private BasicDataSource banco;

    public Conexao() {
        this.banco = new BasicDataSource();
        
        this.banco = new BasicDataSource();
        banco.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        banco.setUrl("jdbc:sqlserver://srvkangaroo.database.windows.net;"
                + "databaseName=dbKangaroo");  
        banco.setUsername("kangaroo");
        banco.setPassword("#Gfgrupo9");
    }

    public BasicDataSource getBanco() {
        return banco;
    } 
}