package com.mycompany.kangarooproject;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscosGroup;
import com.github.britooo.looca.api.group.memoria.Memoria;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.springframework.jdbc.core.JdbcTemplate;

public class App {
    public static void main(String[] args) {
        
        Looca looca = new Looca(); 
        Memoria memoria = looca.getMemoria(); 
        Processador processadorCpu = new Processador();
        DiscosGroup grupoDeDiscos = looca.getGrupoDeDiscos();
        List<Disco> discos = grupoDeDiscos.getDiscos();
        
        Conexao con = new Conexao(); 
        JdbcTemplate template = new JdbcTemplate(con.getBanco());
        
        Conexao2 con2 = new Conexao2(); 
        JdbcTemplate template2 = new JdbcTemplate(con2.getBanco());

        Timer timer = new Timer(); 
        int delay = 5000;  
        int interval = 2000;
        
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {

               
                //DISCO
                Double valorDiscoTotal = 0.0;
                Double valorDiscoUsado = 0.0;
                Double valorDiscoLivre = 0.0;

                for (Disco disco : discos) {
                    valorDiscoUsado = Double.valueOf(disco.getEscritas());
                    valorDiscoTotal = Double.valueOf(disco.getTamanho() / 100000);
                    valorDiscoLivre = (valorDiscoTotal - valorDiscoUsado) / 10000;
                   
                }
                String insert = "INSERT INTO monitoramento(totalDisco, livreDisco) VALUES (?, ?)";

                template.update(insert, valorDiscoTotal, valorDiscoLivre);
                template2.update(insert, valorDiscoTotal, valorDiscoLivre);

                
                Double valorLeitura = 0.0;
                for (Disco disco : discos) {
                    valorLeitura = Double.valueOf(disco.getEscritas().toString());
                    valorLeitura = valorLeitura / 10000;
                }
                System.out.println("Disco total: "+valorDiscoTotal+"\nDisco livre:"+valorDiscoLivre);
                String insertDisco = "INSERT INTO monitoramento(usandoDisco) VALUES (?)";

                template.update(insertDisco, valorLeitura);
                template2.update(insertDisco, valorLeitura);
                System.out.println("Usando disco:"+valorLeitura);
                //FIM DO DISCO
                
                
                //CPU
                System.out.println("chegou 5");
                Double valorCpuUsadoConta = processadorCpu.getUso();
                
                Integer processadorFisico = (processadorCpu.getNumeroCpusFisicas());
         
                Integer processadorLogico = (processadorCpu.getNumeroCpusLogicas());
                
                String insertCpu = "INSERT INTO monitoramento(processadorLogico, processadorFisico, usandoCpu) VALUES (?, ?, ?)";

                template.update(insertCpu, processadorLogico, processadorFisico, valorCpuUsadoConta);
                template2.update(insertCpu, processadorLogico, processadorFisico, valorCpuUsadoConta);
                System.out.println("Processador Logico:"+processadorLogico+"\nProcessador fisico"+processadorFisico+"\nUsando Cpu"+valorCpuUsadoConta);
                //FIM DA CPU
                
                //RAM
                Double valorRamUsadaConta = Double.valueOf(memoria.getEmUso());
                Double valorRamTotalConta = Double.valueOf(memoria.getTotal()); 
                Double valorRamLivre = Double.valueOf(memoria.getDisponivel()); 
               
                valorRamUsadaConta = valorRamUsadaConta / 1024 / 1024 /1024;
                valorRamTotalConta = Math.floor(valorRamTotalConta / 1024 / 1024 /1000);
           
                String insertRam = "INSERT INTO monitoramento(totalRam, livreRam, usandoRam) VALUES (?, ?, ?)"; 
                
                template.update(insertRam, valorRamTotalConta, valorRamLivre, valorRamUsadaConta); 
                template2.update(insertRam, valorRamTotalConta, valorRamLivre, valorRamUsadaConta);
                System.out.println("Ram total"+valorRamTotalConta+"\nRam livre:"+valorRamLivre+"\nRam usada:"+valorRamUsadaConta);
                //FIM DA RAM
            }
        }, delay, interval);       
    }
}
