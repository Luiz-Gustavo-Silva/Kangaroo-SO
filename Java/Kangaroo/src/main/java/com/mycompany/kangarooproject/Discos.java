package com.mycompany.kangarooproject;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscosGroup;
import java.awt.GraphicsConfiguration;
import com.github.britooo.looca.api.group.sistema.Sistema;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import org.springframework.jdbc.core.JdbcTemplate;

public class Discos extends javax.swing.JFrame {

    Looca looca = new Looca();
    DiscosGroup grupoDeDiscos = looca.getGrupoDeDiscos();
    List<Disco> discos = grupoDeDiscos.getDiscos();
   
    Conexao con = new Conexao();
    JdbcTemplate template = new JdbcTemplate(con.getBanco());
    Conexao2 con2 = new Conexao2(); 
    JdbcTemplate template2 = new JdbcTemplate(con2.getBanco());
    
    Computador pc = new Computador();
    
    Timer timer = new Timer();
    int delay = 5000;
    //Intervalo de captura dos dados
    int interval = 2000;

    
    public Discos() {
        initComponents();
        //Isso aqui deixa a tela no centro
        setLocationRelativeTo(null);
        
      
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {

                Double valor = Double.valueOf(grupoDeDiscos.getTamanhoTotal());
                valor /= 1000000000;
                //Esses if/else é para que caso o usuários tenha mais de 1TB
                //de disco, apareça em seguida do num
                //e o GB a mesma coisa, isso é para questão de apresentaação só
                if (valor < 1000.0) {
                    txtDiscoTotal.setText(String.format("%.1f", valor) + " Gb");
                } else {
                    valor /= 1000;
                    txtDiscoTotal.setText(String.format("%.1f", valor) + " Tb");
                }
           

                //Inicializando as variaveis que utilizamos para mostrar o disco total, livre
                //e a barra de progresso
                Double valorDiscoTotal = 0.0;
                Double valorDiscoUsado = 0.0;
                Double valorDiscoLivre = 0.0;
                Long tempoDeTransferencia = 0l;
                String nomeDisco="";
                
                for (Disco disco : discos) {
                    valorDiscoUsado = Double.valueOf(disco.getEscritas());
                    valorDiscoTotal = Double.valueOf(disco.getTamanho() / 100000);// foi dividido por 100.000 para ficarem na mesma unidade de medida
                    valorDiscoLivre = (valorDiscoTotal - valorDiscoUsado) / 10000;// foi dividido por 10000 para ficar em Gb
                    if (valorDiscoLivre < 1000.0) {
                        txtDiscoTotal.setText(String.format("%.1f", valor) + " Gb");
                    } else {
                        valorDiscoLivre /= 1000;
                        txtDiscoTotal.setText(String.format("%.1f", valor) + " Tb");
                    }
                    
                    tempoDeTransferencia = disco.getTempoDeTransferencia();
                    nomeDisco = disco.getNome();
                    System.out.println("tempo"+tempoDeTransferencia);
                    System.out.println("nome de disco"+nomeDisco);
                    
                    txtDiscoLivre.setText(String.format("%.1f Gb", valorDiscoLivre));
                  
                    Double valorPorcentagem;
                    valorPorcentagem = (valorDiscoUsado / valorDiscoTotal) * 100;
                    pgbPorcentagemDisco.setValue(valorPorcentagem.intValue());
                }

                
                String insert = "INSERT INTO monitoramento(totalDisco, livreDisco) VALUES (?, ?)";
                String insertInfoDisco = "INSERT INTO monitoramento (nomeDisco, tempoDeAtividade) VALUES (?, ?)";
                
                template.update(insert, valorDiscoTotal, valorDiscoLivre);
                template2.update(insert, valorDiscoTotal, valorDiscoLivre);
                template.update(insertInfoDisco, nomeDisco, tempoDeTransferencia);
                template2.update(insertInfoDisco, nomeDisco, tempoDeTransferencia);

                System.out.println(valorDiscoTotal + valorDiscoLivre);
                List resultados = template.queryForList("SELECT totalDisco, livreDisco FROM monitoramento");
                List resultados2 = template2.queryForList("SELECT totalDisco, livreDisco FROM monitoramento");
                System.out.println(resultados);
                System.out.println(resultados2);

                
                Double valorLeitura = 0.0;
                for (Disco disco : discos) {
                    valorLeitura = Double.valueOf(disco.getEscritas().toString());
                    valorLeitura = valorLeitura / 10000;
                    txtDiscoUsado.setText(String.format("%.1f ", valorLeitura) + "Gb");
                }

                String insertDisco = "INSERT INTO monitoramento(usandoDisco) VALUES (?)";

                //template.update(insertDisco, valorLeitura);
                //template2.update(insertDisco, valorLeitura);

                System.out.println(valorLeitura);
                List resultadosDisco = template.queryForList("SELECT usandoDisco FROM monitoramento");
                List resultadosDisco2 = template2.queryForList("SELECT usandoDisco FROM monitoramento");
                System.out.println(resultadosDisco);
                System.out.println(resultadosDisco2);
            }
        }, delay, interval);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnRam = new javax.swing.JButton();
        btnCpu = new javax.swing.JButton();
        btnDisco = new javax.swing.JButton();
        txtTitulo = new javax.swing.JLabel();
        txtDisco = new javax.swing.JLabel();
        pgbPorcentagemDisco = new javax.swing.JProgressBar();
        jPanel2 = new javax.swing.JPanel();
        txtTotal = new javax.swing.JLabel();
        txtLivre = new javax.swing.JLabel();
        txtUsado = new javax.swing.JLabel();
        txtDiscoUsado = new javax.swing.JLabel();
        txtDiscoLivre = new javax.swing.JLabel();
        txtDiscoTotal = new javax.swing.JLabel();

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        jLabel6.setText("Label:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(56, 63, 82));

        btnRam.setBackground(new java.awt.Color(255, 255, 255));
        btnRam.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        btnRam.setText("RAM");
        btnRam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRamActionPerformed(evt);
            }
        });

        btnCpu.setBackground(new java.awt.Color(255, 255, 255));
        btnCpu.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        btnCpu.setText("CPU");
        btnCpu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCpuActionPerformed(evt);
            }
        });

        btnDisco.setBackground(new java.awt.Color(255, 255, 255));
        btnDisco.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        btnDisco.setText("Disco");
        btnDisco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiscoActionPerformed(evt);
            }
        });

        txtTitulo.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        txtTitulo.setForeground(new java.awt.Color(255, 255, 255));
        txtTitulo.setText("MONITORAMENTO DA MÁQUINA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(txtTitulo)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDisco, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRam, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCpu, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addComponent(btnCpu)
                .addGap(18, 18, 18)
                .addComponent(btnRam)
                .addGap(18, 18, 18)
                .addComponent(btnDisco)
                .addGap(106, 106, 106))
        );

        txtDisco.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        txtDisco.setText("DISCO");

        pgbPorcentagemDisco.setStringPainted(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtTotal.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        txtTotal.setText("Total:");

        txtLivre.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        txtLivre.setText("Livre:");

        txtUsado.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        txtUsado.setText("Usado:");

        txtDiscoUsado.setText("Carregando...");

        txtDiscoLivre.setText("Carregando...");

        txtDiscoTotal.setText("Carregando...");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiscoTotal))
                .addGap(104, 104, 104)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLivre, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiscoLivre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUsado, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiscoUsado))
                .addGap(71, 71, 71))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLivre)
                    .addComponent(txtTotal)
                    .addComponent(txtUsado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiscoUsado)
                    .addComponent(txtDiscoLivre)
                    .addComponent(txtDiscoTotal))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtDisco)
                                .addGap(215, 215, 215))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pgbPorcentagemDisco, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(142, 142, 142))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(txtDisco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pgbPorcentagemDisco, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRamActionPerformed
        // TODO add your handling code here:
        //Isso aqui abre as novas janelas
        timer.cancel();
        Ram ram = new Ram();
        ram.setVisible(true);
        //Isso aqui fecha a janela caso abra a outra
        this.dispose();
    }//GEN-LAST:event_btnRamActionPerformed


    private void btnCpuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCpuActionPerformed
        // TODO add your handling code here:
        timer.cancel();
        Cpu cpu = new Cpu();
        cpu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCpuActionPerformed

    private void btnDiscoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiscoActionPerformed
        // TODO add your handling code here:
        timer.cancel();
        Discos disco = new Discos();
        disco.setVisible(true);
        //this.dispose();
    }//GEN-LAST:event_btnDiscoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Discos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Discos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Discos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Discos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Discos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCpu;
    private javax.swing.JButton btnDisco;
    private javax.swing.JButton btnRam;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JProgressBar pgbPorcentagemDisco;
    private javax.swing.JLabel txtDisco;
    private javax.swing.JLabel txtDiscoLivre;
    private javax.swing.JLabel txtDiscoTotal;
    private javax.swing.JLabel txtDiscoUsado;
    private javax.swing.JLabel txtLivre;
    private javax.swing.JLabel txtTitulo;
    private javax.swing.JLabel txtTotal;
    private javax.swing.JLabel txtUsado;
    // End of variables declaration//GEN-END:variables
}
