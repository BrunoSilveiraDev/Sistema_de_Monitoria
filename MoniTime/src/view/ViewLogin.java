/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControleAlunoSelMonitoria;
import controller.ControleMonitorSelSala;
import controller.ControleUsuarios;
import exception.DAOException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import model.Monitor;
import model.Usuario;
import util.Alert;

/**
 *
 * @author snow
 */
public class ViewLogin extends javax.swing.JFrame {
    
    ControleUsuarios ctrlLogin;
    Action action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            acessar();
        }
    };
    
    public ViewLogin() throws DAOException, SQLException {
        initComponents();
        ctrlLogin = new ControleUsuarios();
        URL iconURL = getClass().getResource("/img/clock4.png");
        ImageIcon icon = new ImageIcon(iconURL);
        this.setIconImage(icon.getImage());
        cpUsuario.addActionListener(action);
        cpSenha.addActionListener(action);
    }
    
    public void acessar() {
        MainView mainView;
        ViewAlunoSelMonitoria viewAlunoSelMonitoria;
        ViewOpcoesCoordenador viewOpcoesCoordenador;
        ControleAlunoSelMonitoria controleAlunoSelMonitoria;
        Usuario u = null;
        Monitor m = null;
        
        try {
            u = ctrlLogin.validaUsuario(cpUsuario.getText(), String.valueOf(cpSenha.getPassword()));
        } catch (DAOException | SQLException ex) {
            Logger.getLogger(ViewLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (u != null) {
            switch (u.getTipo()) {
                case "alu":
                    this.dispose();
                    viewAlunoSelMonitoria = new ViewAlunoSelMonitoria(u);
                    controleAlunoSelMonitoria = new ControleAlunoSelMonitoria();
                    try {
                        viewAlunoSelMonitoria.setDataSet(controleAlunoSelMonitoria.getAllMoniFuncionais(),
                                controleAlunoSelMonitoria.getAllLotacaoMonis(u.getMatricula()));
                        viewAlunoSelMonitoria.setController(controleAlunoSelMonitoria);
                        controleAlunoSelMonitoria.setView(viewAlunoSelMonitoria);
                        viewAlunoSelMonitoria.setLocationRelativeTo(null);
                        viewAlunoSelMonitoria.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                        viewAlunoSelMonitoria.setVisible(true);
                        viewAlunoSelMonitoria.updateView();
                    } catch (SQLException | DAOException ex) {
                        Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case "mon":
                    this.dispose();
                    try {
                        m = ctrlLogin.getMonitor(u);
                        Date hoje = new Date();
                        if (!hoje.before(m.getDtInicio()) && !hoje.after(m.getDtFinal())) {
                            ViewOpcoesMonitor viewOpcoesMonitor = new ViewOpcoesMonitor(m);
                            viewOpcoesMonitor.setLocationRelativeTo(null);
                            viewOpcoesMonitor.setVisible(true);
                        } else {
                            viewAlunoSelMonitoria = new ViewAlunoSelMonitoria(u);
                            controleAlunoSelMonitoria = new ControleAlunoSelMonitoria();
                            try {
                                Alert.aviso("Seu periodo como monitor acabou. Voce sera logado como aluno.");
                                ctrlLogin.remMonitor(m);
                                viewAlunoSelMonitoria.setDataSet(controleAlunoSelMonitoria.getAllMoniFuncionais(),
                                        controleAlunoSelMonitoria.getAllLotacaoMonis(u.getMatricula()));
                                viewAlunoSelMonitoria.setController(controleAlunoSelMonitoria);
                                controleAlunoSelMonitoria.setView(viewAlunoSelMonitoria);
                                viewAlunoSelMonitoria.setLocationRelativeTo(null);
                                viewAlunoSelMonitoria.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                                viewAlunoSelMonitoria.setVisible(true);
                                viewAlunoSelMonitoria.updateView();
                            } catch (SQLException | DAOException ex) {
                                Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } catch (DAOException | SQLException ex) {
                        Logger.getLogger(ViewLogin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case "coo":
                    this.dispose();
                    viewOpcoesCoordenador = new ViewOpcoesCoordenador(u);
                    viewOpcoesCoordenador.setLocationRelativeTo(null);
                    viewOpcoesCoordenador.setVisible(true);
                    break;
                case "adm":
                    this.dispose();
                    mainView = new MainView(u);
                    mainView.setLocationRelativeTo(null);
                    mainView.setVisible(true);
                    break;
            }
        } else {
            Alert.aviso("Usuário ou senha incorretos.");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        cpSenha = new javax.swing.JPasswordField();
        btAcessar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cpUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Monitime | Login");
        setLocation(new java.awt.Point(400, 200));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cpSenha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btAcessar.setText("Acessar");
        btAcessar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btAcessarMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Seja bem vindo!");

        cpUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Usuário");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Senha");

        jLabel5.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        jLabel5.setText("MoniTime®");
        jLabel5.setToolTipText("");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Versão 1.0");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/clock4.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel1))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(25, 25, 25)
                                .addComponent(btAcessar))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cpUsuario)
                                    .addComponent(cpSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6)))
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(92, 92, 92))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(cpUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cpSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btAcessar)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btAcessarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btAcessarMouseClicked
        acessar();
    }//GEN-LAST:event_btAcessarMouseClicked

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
            java.util.logging.Logger.getLogger(ViewLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ViewLogin().setVisible(true);
                } catch (DAOException | SQLException ex) {
                    Logger.getLogger(ViewLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAcessar;
    private javax.swing.JPasswordField cpSenha;
    private javax.swing.JTextField cpUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
