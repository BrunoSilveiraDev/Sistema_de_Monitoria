/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControleAlunoSelMonitoria;
import controller.ControleCadMonitor;
import controller.ControleCadSala;
import controller.ControleMonitorSelSala;
import controller.ControleRelatorios;
import exception.DAOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import model.Monitor;
import model.Usuario;

/**
 *
 * @author snow
 */
public class MainView extends javax.swing.JFrame {

    Usuario usuario;

    public MainView(Usuario usuario) {
        initComponents();
        this.usuario = usuario;
        URL iconURL = getClass().getResource("/img/clock4.png");
        ImageIcon icon = new ImageIcon(iconURL);
        this.setIconImage(icon.getImage());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        MenuCoordenador = new javax.swing.JMenu();
        itemMenuCadastro = new javax.swing.JMenu();
        itemMenuMonitores = new javax.swing.JMenuItem();
        itemMenuSalas = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        menuMinhasMoni = new javax.swing.JMenuItem();
        menuOutrasMoni = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MoniTime");
        setLocation(new java.awt.Point(400, 200));
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                formWindowLostFocus(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        jLabel2.setText("MoniTime®");
        jLabel2.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Versão 1.0");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/hydra_logo.PNG"))); // NOI18N

        jMenu7.setLabel("Menu");

        jMenuItem9.setText("Trocar usuário");
        jMenuItem9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem9MouseClicked(evt);
            }
        });
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem9);

        jMenuItem1.setText("Sair");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem1);

        jMenuBar1.add(jMenu7);

        MenuCoordenador.setText("Coordenador");

        itemMenuCadastro.setText("Cadastro");

        itemMenuMonitores.setText("Monitores");
        itemMenuMonitores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuMonitoresActionPerformed(evt);
            }
        });
        itemMenuCadastro.add(itemMenuMonitores);

        itemMenuSalas.setText("Salas");
        itemMenuSalas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuSalasActionPerformed(evt);
            }
        });
        itemMenuCadastro.add(itemMenuSalas);

        MenuCoordenador.add(itemMenuCadastro);

        jMenuItem8.setText("Relatórios");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        MenuCoordenador.add(jMenuItem8);

        jMenuBar1.add(MenuCoordenador);

        jMenu4.setText("Monitor");

        menuMinhasMoni.setText("Meus Horários");
        menuMinhasMoni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuMinhasMoniActionPerformed(evt);
            }
        });
        jMenu4.add(menuMinhasMoni);

        menuOutrasMoni.setText("Outras monitorias");
        menuOutrasMoni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOutrasMoniActionPerformed(evt);
            }
        });
        jMenu4.add(menuOutrasMoni);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Aluno");

        jMenuItem3.setText("Inscrição em Monotorias");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem3);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(187, 187, 187))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(241, 241, 241)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        //System.out.println("Visible");
        //this.setVisible(true);
    }//GEN-LAST:event_formWindowGainedFocus

    private void formWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowLostFocus
        //System.out.println("invisible");
        //this.setVisible(false);
    }//GEN-LAST:event_formWindowLostFocus

    private void itemMenuMonitoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuMonitoresActionPerformed
        ViewCadMonitor viewCadMonitor = new ViewCadMonitor(usuario);
        ControleCadMonitor controleCadMonitor = new ControleCadMonitor();
        try {
            viewCadMonitor.setDataSet(controleCadMonitor.getAllData());
            controleCadMonitor.setView(viewCadMonitor);
            viewCadMonitor.setController(controleCadMonitor);
            viewCadMonitor.updateView();
        } catch (DAOException | SQLException ex) {
            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        }
        viewCadMonitor.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        viewCadMonitor.setLocationRelativeTo(null);
        viewCadMonitor.setVisible(true);
    }//GEN-LAST:event_itemMenuMonitoresActionPerformed

    private void itemMenuSalasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuSalasActionPerformed
        ViewCadSala viewCadSala = new ViewCadSala();
        ControleCadSala controleCadSala = new ControleCadSala();
        try {
            viewCadSala.setDataSet(controleCadSala.getAllData());
            viewCadSala.setController(controleCadSala);
            controleCadSala.setView(viewCadSala);
            viewCadSala.updateView();
        } catch (DAOException | SQLException ex) {
            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        }
        viewCadSala.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        viewCadSala.setLocationRelativeTo(null);
        viewCadSala.setVisible(true);
    }//GEN-LAST:event_itemMenuSalasActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void menuMinhasMoniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMinhasMoniActionPerformed
        usuario = new Usuario();
        usuario.setMatricula("0000000000002");
        usuario.setNome("Teste Monitor");
        usuario.setTipo("mon");
        Monitor mon = new Monitor(usuario);
        ViewMonitorSelecaoSala viewMonitorSelecaoSala = new ViewMonitorSelecaoSala(mon);
        ControleMonitorSelSala controleMonitorSelSala = new ControleMonitorSelSala();
        controleMonitorSelSala.setView(viewMonitorSelecaoSala);
        viewMonitorSelecaoSala.setController(controleMonitorSelSala);
        try {
            viewMonitorSelecaoSala.setDataSet(controleMonitorSelSala.getAllData());
            viewMonitorSelecaoSala.updateView();
        } catch (DAOException | SQLException ex) {
            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        }
        viewMonitorSelecaoSala.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        viewMonitorSelecaoSala.setLocationRelativeTo(null);
        viewMonitorSelecaoSala.setVisible(true);
    }//GEN-LAST:event_menuMinhasMoniActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        usuario = new Usuario();
        usuario.setMatricula("0000000000003");
        usuario.setNome("Teste Aluno");
        usuario.setTipo("alu");
        ViewAlunoSelMonitoria viewAlunoSelMonitoria = new ViewAlunoSelMonitoria(usuario);
        ControleAlunoSelMonitoria controleAlunoSelMonitoria = new ControleAlunoSelMonitoria();
        try {
            viewAlunoSelMonitoria.setDataSet(controleAlunoSelMonitoria.getAllMoniFuncionais(),
                    controleAlunoSelMonitoria.getAllLotacaoMonis(usuario.getMatricula()));
            viewAlunoSelMonitoria.setController(controleAlunoSelMonitoria);
            controleAlunoSelMonitoria.setView(viewAlunoSelMonitoria);
            viewAlunoSelMonitoria.setLocationRelativeTo(null);
            viewAlunoSelMonitoria.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            viewAlunoSelMonitoria.setVisible(true);
            viewAlunoSelMonitoria.updateView();
        } catch (SQLException | DAOException ex) {
            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem9MouseClicked


    }//GEN-LAST:event_jMenuItem9MouseClicked

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        try {
            this.dispose();
            ViewLogin viewLogin = new ViewLogin();
            viewLogin.setVisible(true);
        } catch (DAOException ex) {
            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void menuOutrasMoniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOutrasMoniActionPerformed

        usuario = new Usuario();
        usuario.setMatricula("0000000000002");
        usuario.setNome("Teste Monitor");
        usuario.setTipo("mon");
        ViewAlunoSelMonitoria viewAlunoSelMonitoria = new ViewAlunoSelMonitoria(usuario);
        ControleAlunoSelMonitoria controleAlunoSelMonitoria = new ControleAlunoSelMonitoria();
        try {
            viewAlunoSelMonitoria.setDataSet(controleAlunoSelMonitoria.getAllMoniFuncionais(),
                    controleAlunoSelMonitoria.getAllLotacaoMonis(usuario.getMatricula()));
            viewAlunoSelMonitoria.setController(controleAlunoSelMonitoria);
            controleAlunoSelMonitoria.setView(viewAlunoSelMonitoria);
            viewAlunoSelMonitoria.setLocationRelativeTo(null);
            viewAlunoSelMonitoria.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            viewAlunoSelMonitoria.setVisible(true);
            viewAlunoSelMonitoria.updateView();
        } catch (SQLException | DAOException ex) {
            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuOutrasMoniActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        ViewRelatorio viewRelat;
        try {
            viewRelat = new ViewRelatorio();
            ControleRelatorios controlRelat = new ControleRelatorios();
            viewRelat.setController(controlRelat);
            controlRelat.setView(viewRelat);
            viewRelat.setLocationRelativeTo(null);
            viewRelat.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            viewRelat.setVisible(true);
        } catch (DAOException | SQLException ex) {
            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        }
//        try {
//            viewRelat.setDataSet(controlRelat.constroiRelatorio("vw_qtdealunos", 1));
//            viewRelat.setLocationRelativeTo(null);
//            viewRelat.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//            viewRelat.setVisible(true);
//            viewRelat.updateView();
//        } catch (DAOException | SQLException ex) {
//            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
//        }
        

//        try {
//            viewRelat.setDataSet(controlRelat.constroiRelatorio("vw_qtdealunos", 1));
//            viewRelat.setLocationRelativeTo(null);
//            viewRelat.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//            viewRelat.setVisible(true);
//            viewRelat.updateView();
//        } catch (DAOException | SQLException ex) {
//            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }//GEN-LAST:event_jMenuItem8ActionPerformed

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
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // new MainView().setVisible(true);
            }
        });
    }

    public void getClickCoordenador() {
        MenuCoordenador.doClick();
    }

    public void getClickCadastro() {
        itemMenuCadastro.doClick();
    }

    public void getClickMonitores() {
        itemMenuMonitores.doClick();
    }

    public void getClickSalas() {
        itemMenuSalas.doClick();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu MenuCoordenador;
    private javax.swing.JMenu itemMenuCadastro;
    private javax.swing.JMenuItem itemMenuMonitores;
    private javax.swing.JMenuItem itemMenuSalas;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenuItem menuMinhasMoni;
    private javax.swing.JMenuItem menuOutrasMoni;
    // End of variables declaration//GEN-END:variables
}
