/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControllerDashboard;
import com.github.britooo.looca.api.group.discos.Volume;
import controller.ControllerBancoDeDados;
import controller.ControllerProjeto;
import controller.ControllerSessao;
import informacoes.InformacoesDoSistema;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.TimerTask;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Projeto;

/**
 *
 * @author fehig
 */
public class TelaProjetos extends javax.swing.JFrame {

    // InformacoesDoSistema info;    
    private ControllerProjeto controller;    
    private DefaultTableModel modelo;
    private List<Projeto> projetos;
    private controller.ControllerBancoDeDados banco;
    
    public TelaProjetos() throws IOException {
        initComponents();
        controller = new ControllerProjeto(this);

        banco = new ControllerBancoDeDados();
        modelo = (DefaultTableModel) tblProjeto.getModel();
        projetos = banco.selectAllFromProjeto();
        controller.atualizarTabela();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField3 = new javax.swing.JTextField();
        menuLateral = new javax.swing.JPanel();
        btnDashboard = new javax.swing.JPanel();
        btnDashBoard = new javax.swing.JLabel();
        btnProjetos = new javax.swing.JPanel();
        bntProjetos = new javax.swing.JLabel();
        btnConfig = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btnAjuda = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        imgUser = new javax.swing.JLabel();
        lblCargo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        imgLogo = new javax.swing.JLabel();
        lblBemVindo = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProjeto = new javax.swing.JTable();
        txtNome = new javax.swing.JTextField();
        txtEtapa = new javax.swing.JTextField();
        txtData = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();

        jTextField3.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menuLateral.setBackground(new java.awt.Color(38, 38, 46));

        btnDashboard.setBackground(new java.awt.Color(102, 102, 102));

        btnDashBoard.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        btnDashBoard.setForeground(new java.awt.Color(204, 204, 204));
        btnDashBoard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDashBoard.setText("Dashboard");
        btnDashBoard.setAlignmentY(0.4F);
        btnDashBoard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDashBoardMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnDashboardLayout = new javax.swing.GroupLayout(btnDashboard);
        btnDashboard.setLayout(btnDashboardLayout);
        btnDashboardLayout.setHorizontalGroup(
            btnDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnDashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDashBoard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnDashboardLayout.setVerticalGroup(
            btnDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDashBoard, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
        );

        btnProjetos.setBackground(new java.awt.Color(102, 102, 102));

        bntProjetos.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        bntProjetos.setForeground(new java.awt.Color(204, 204, 204));
        bntProjetos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bntProjetos.setText("Projetos");
        bntProjetos.setAlignmentY(0.4F);

        javax.swing.GroupLayout btnProjetosLayout = new javax.swing.GroupLayout(btnProjetos);
        btnProjetos.setLayout(btnProjetosLayout);
        btnProjetosLayout.setHorizontalGroup(
            btnProjetosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnProjetosLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(bntProjetos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );
        btnProjetosLayout.setVerticalGroup(
            btnProjetosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bntProjetos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
        );

        btnConfig.setBackground(new java.awt.Color(102, 102, 102));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Configurações");
        jLabel4.setToolTipText("");
        jLabel4.setAlignmentY(0.4F);

        javax.swing.GroupLayout btnConfigLayout = new javax.swing.GroupLayout(btnConfig);
        btnConfig.setLayout(btnConfigLayout);
        btnConfigLayout.setHorizontalGroup(
            btnConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnConfigLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnConfigLayout.setVerticalGroup(
            btnConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnConfigLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnAjuda.setBackground(new java.awt.Color(102, 102, 102));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Ajuda");
        jLabel5.setAlignmentY(0.4F);

        javax.swing.GroupLayout btnAjudaLayout = new javax.swing.GroupLayout(btnAjuda);
        btnAjuda.setLayout(btnAjudaLayout);
        btnAjudaLayout.setHorizontalGroup(
            btnAjudaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAjudaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnAjudaLayout.setVerticalGroup(
            btnAjudaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
        );

        imgUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/userIconX128.png"))); // NOI18N

        lblCargo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCargo.setForeground(new java.awt.Color(204, 204, 204));
        lblCargo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCargo.setText("Administrador");

        javax.swing.GroupLayout menuLateralLayout = new javax.swing.GroupLayout(menuLateral);
        menuLateral.setLayout(menuLateralLayout);
        menuLateralLayout.setHorizontalGroup(
            menuLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnConfig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnAjuda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(menuLateralLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(imgUser)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuLateralLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblCargo)
                .addGap(30, 30, 30))
            .addComponent(btnProjetos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        menuLateralLayout.setVerticalGroup(
            menuLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuLateralLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(imgUser, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblCargo)
                .addGap(18, 18, 18)
                .addComponent(btnDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnProjetos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAjuda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        getContentPane().add(menuLateral, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 160, 480));

        jPanel2.setBackground(new java.awt.Color(38, 38, 46));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        imgLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/FluidLogoX128.png"))); // NOI18N

        lblBemVindo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblBemVindo.setForeground(new java.awt.Color(204, 204, 204));
        lblBemVindo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBemVindo.setText("Bem vindo,");

        lblUsername.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(204, 204, 204));
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblUsername.setText("Nome");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(imgLogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblBemVindo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(636, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imgLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBemVindo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 60));

        jPanel4.setBackground(new java.awt.Color(233, 231, 231));
        jPanel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        tblProjeto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nome", "Etapa", "Data de entrega"
            }
        ));
        jScrollPane1.setViewportView(tblProjeto);
        if (tblProjeto.getColumnModel().getColumnCount() > 0) {
            tblProjeto.getColumnModel().getColumn(2).setResizable(false);
        }

        txtNome.setText("Projeto exemplo");
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        txtEtapa.setText("Color Grading");

        txtData.setText("29/06/2021");

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Nome:");

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Etapa:");

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Data de entega:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Projetos");

        btnAdd.setText("Adicionar");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnRemover.setText("Remover");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(22, 22, 22)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEtapa, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(346, 346, 346)
                        .addComponent(jLabel8))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(302, 302, 302)
                        .addComponent(btnAdd)
                        .addGap(35, 35, 35)
                        .addComponent(btnRemover)))
                .addContainerGap(179, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel8)
                .addGap(51, 51, 51)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEtapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnRemover))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 800, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        try {
            controller.criarProjeto();
        } catch (IOException ex) {
            Logger.getLogger(TelaProjetos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        try {
            controller.removerProjeto();
        } catch (IOException ex) {
            Logger.getLogger(TelaProjetos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnDashBoardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDashBoardMouseClicked
        TelaDash dashboard = null;
        try {
            dashboard = new TelaDash();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TelaProjetos.class.getName()).log(Level.SEVERE, null, ex);
        }
        dashboard.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnDashBoardMouseClicked

    public JTable getTblProjeto() {
        return tblProjeto;
    }

    public void setTblProjeto(JTable tblProjeto) {
        this.tblProjeto = tblProjeto;
    }

    public JTextField getTxtData() {
        return txtData;
    }

    public void setTxtData(JTextField txtData) {
        this.txtData = txtData;
    }

    public JTextField getTxtEtapa() {
        return txtEtapa;
    }

    public void setTxtEtapa(JTextField txtEtapa) {
        this.txtEtapa = txtEtapa;
    }

    public JTextField getTxtNome() {
        return txtNome;
    }

    public void setTxtNome(JTextField txtNome) {
        this.txtNome = txtNome;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public JLabel getLblCargo() {
        return lblCargo;
    }

    public void setLblCargo(JLabel lblCargo) {
        this.lblCargo = lblCargo;
    }

    public JLabel getLblUsername() {
        return lblUsername;
    }

    public void setLblUsername(JLabel lblUsername) {
        this.lblUsername = lblUsername;
    }

    
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
            java.util.logging.Logger.getLogger(TelaProjetos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaProjetos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaProjetos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaProjetos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TelaProjetos().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(TelaProjetos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bntProjetos;
    private javax.swing.JButton btnAdd;
    private javax.swing.JPanel btnAjuda;
    private javax.swing.JPanel btnConfig;
    private javax.swing.JLabel btnDashBoard;
    private javax.swing.JPanel btnDashboard;
    private javax.swing.JPanel btnProjetos;
    private javax.swing.JButton btnRemover;
    private javax.swing.JLabel imgLogo;
    private javax.swing.JLabel imgUser;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel lblBemVindo;
    private javax.swing.JLabel lblCargo;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPanel menuLateral;
    private javax.swing.JTable tblProjeto;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtEtapa;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
