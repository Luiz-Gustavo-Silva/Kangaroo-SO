package com.mycompany.kangarooproject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SitesMaliciosos {
    private String titulo;
    private String url;
    private Integer fkNavegacao;
    private String momentoVisitaSite = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    
    private List<String> sitesMaliciosos = new ArrayList<>();   
        
    public void adicionarSite(){
        sitesMaliciosos.add("www.morte.com");
        sitesMaliciosos.add("www.se-matar.com");
        sitesMaliciosos.add("www.hao123.com");
        sitesMaliciosos.add("https://sitesapostasbrasil.com/");
        
         sitesMaliciosos.add("https://sitedeapostas.com/bet365");
        sitesMaliciosos.add("https://sitedeapostas.com/betano");
        sitesMaliciosos.add("https://sitedeapostas.com/betway");
        sitesMaliciosos.add("http://www.planecrashinfo.com/lastwords.htm");
        
    }

    public String getMomentoVisitaSite() {
        return momentoVisitaSite;
    }

    public void setMomentoVisitaSite(String momentoVisitaSite) {
        this.momentoVisitaSite = momentoVisitaSite;
    }
    
     public List<String> getSitesMaliciosos() {
        return sitesMaliciosos;
    }

    public void setSitesMaliciosos(List<String> sitesMaliciosos) {
        this.sitesMaliciosos = sitesMaliciosos;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getFkNavegacao() {
        return fkNavegacao;
    }

    public void setFkNavegacao(Integer fkNavegacao) {
        this.fkNavegacao = fkNavegacao;
    }

    @Override
    public String toString() {
        return "\nLink: "+this.url+ "\nDia e hora: "+ this.momentoVisitaSite;
    }
    
    
}
