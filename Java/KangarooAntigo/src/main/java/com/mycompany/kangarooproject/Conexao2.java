package com.mycompany.kangarooproject;

import java.util.List;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

public class Conexao2 {
    private BasicDataSource banco;

    public Conexao2() {
        this.banco = new BasicDataSource();
        
        this.banco = new BasicDataSource();
        banco.setDriverClassName("com.mysql.jdbc.Driver");
        banco.setUrl("jdbc:mysql://localhost:3306/dbKangaroo");
        banco.setUsername("ubuntu");
        banco.setPassword("urubu100");
    }

    public BasicDataSource getBanco() {
        return banco;
    } 
}