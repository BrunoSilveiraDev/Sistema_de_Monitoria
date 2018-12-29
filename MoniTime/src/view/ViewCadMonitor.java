/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControleCadMonitor;
import exception.DAOException;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import model.Monitor;
import model.Usuario;
import util.Alert;
import util.DataUtil;
import util.STRs;

/**
 *
 * @author snow
 */
public class ViewCadMonitor extends javax.swing.JFrame {

    List<Monitor> monitores;
    ControleCadMonitor controller;
    ViewCadMonitorTM tableModel;
    Usuario usuario;

    public ViewCadMonitor(Usuario usuario) {
        initComponents();
        this.usuario = usuario;
        URL iconURL = getClass().getResource("/img/clock4.png");
        ImageIcon icon = new ImageIcon(iconURL);
        this.setIconImage(icon.getImage());
        comboDisciplinaSigla.removeAllItems();
        STRs.getMonitoriasAtivas().forEach((str) -> {
            comboDisciplinaSigla.addItem(str);
        });
        comboDisciplinaSigla.setEditable(false);
        cpNome.setEnabled(false);
        cpNome.setEditable(false);
        cpNome.setDisabledTextColor(Color.blue);
        cpNomeDisciplina.setEnabled(false);
        cpNomeDisciplina.setEditable(false);
        cpNomeDisciplina.setDisabledTextColor(Color.blue);
        comboDisciplinaSigla.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == 1) {
                try {
                    cpNomeDisciplina.setText(controller.pesqMonitoriaAtiva(comboDisciplinaSigla.getItemAt(comboDisciplinaSigla.getSelectedIndex())));
                } catch (DAOException | SQLException ex) {
                    Logger.getLogger(ViewCadMonitor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void setDataSet(List<Monitor> monitores) {
        tableModel = new ViewCadMonitorTM(monitores);
        tabelaMonitores.setModel(tableModel);//vazio
        this.monitores = monitores;//fill monitorias
    }

    public void updateView() throws SQLException, DAOException {
        this.monitores = this.controller.getAllData();
        tableModel.refresh(this.monitores);
    }

    public void setController(ControleCadMonitor controleCadMonitor) {
        this.controller = controleCadMonitor;
        comboDisciplinaSigla.setSelectedIndex(-1);
        comboDisciplinaSigla.setSelectedIndex(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botaoVoltar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        cpNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btCadMonSalvar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cpDtInicio = new javax.swing.JTextField();
        cpMatricula = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cpDtFinal = new javax.swing.JTextField();
        comboDisciplinaSigla = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cpNomeDisciplina = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaMonitores = new javax.swing.JTable();
        btRemover = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MoniTime | Cadastro de Monitores");

        botaoVoltar.setText("Fechar");
        botaoVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoVoltarActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("Disciplina:");

        btCadMonSalvar.setText("Cadastrar");
        btCadMonSalvar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btCadMonSalvarMouseClicked(evt);
            }
        });

        jLabel1.setText("Nome:");

        cpDtInicio.setText("01/02/2018");

        cpMatricula.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cpMatriculaFocusLost(evt);
            }
        });

        jLabel2.setText("Matrícula:");

        cpDtFinal.setText("20/12/2018");

        comboDisciplinaSigla.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboDisciplinaSigla.setEnabled(false);

        jLabel5.setText("Data Inicio:");

        jLabel6.setText("Data Fim:");

        jLabel7.setText("Nome disciplina:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("CADASTRO DE MONITORES");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(cpDtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(279, 279, 279))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGap(203, 203, 203)
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cpDtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btCadMonSalvar))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cpMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(cpNome)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(comboDisciplinaSigla, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cpNomeDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cpMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cpNome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboDisciplinaSigla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cpNomeDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cpDtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(cpDtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCadMonSalvar))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tabelaMonitores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabelaMonitores);

        btRemover.setText("Excluir");
        btRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btRemover)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btRemover)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(botaoVoltar)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoVoltar)
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCadMonSalvarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btCadMonSalvarMouseClicked
        if (comboDisciplinaSigla.isEnabled()) {
            Monitor monitor = new Monitor();
            monitor.setMatricula(cpMatricula.getText());
            monitor.setNome(cpNome.getText());
            monitor.setTipo("mon");
            monitor.getDisp().setSigla(comboDisciplinaSigla.getSelectedItem().toString());
            monitor.setDtInicio(DataUtil.stringToDateSQL(cpDtInicio.getText()));
            monitor.setDtFinal(DataUtil.stringToDateSQL(cpDtFinal.getText()));
            if (monitor.getDtInicio() == null || monitor.getDtFinal() == null) {
                Alert.avisoErro("Data não pode ser formatada.");
            } else {
                try {
                    if (controller.getMonitor(monitor) == null) {
                        if (controller.addMonitor(monitor)) {
                            Alert.inserirSucesso(monitor.getNome());
                        } else {
                            Alert.aviso("Falha ao inserir monitor.");
                        }
                        updateView();
                    } else {
                        Alert.aviso("Monitor já cadastrado.");
                    }
                } catch (DAOException | SQLException ex) {
                    Logger.getLogger(ViewCadMonitor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else {
            Alert.aviso("Dados invalidos.");
        }
    }//GEN-LAST:event_btCadMonSalvarMouseClicked

    private void botaoVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoVoltarActionPerformed
        this.dispose();
    }//GEN-LAST:event_botaoVoltarActionPerformed

    private void btRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoverActionPerformed
        if (tabelaMonitores.getSelectedRow() > -1) {
            try {
                Monitor mon = monitores.get(tabelaMonitores.getSelectedRow());
                controller.remMonitor(mon);
                updateView();
            } catch (DAOException | SQLException ex) {
                Logger.getLogger(ViewCadMonitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btRemoverActionPerformed

    private void cpMatriculaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cpMatriculaFocusLost
        //System.out.println("focus lost");
        if (!cpMatricula.getText().equals("")) {
            try {
                if (controller.pesqNomeUsuario(cpMatricula.getText()).equals("Matricula invalida")) {
                    comboDisciplinaSigla.setEnabled(false);
                } else {
                    comboDisciplinaSigla.setEnabled(true);
                    cpNome.setText(controller.pesqNomeUsuario(cpMatricula.getText()));
                }
            } catch (DAOException | SQLException ex) {
                Logger.getLogger(ViewCadMonitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cpMatriculaFocusLost

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
            java.util.logging.Logger.getLogger(ViewCadMonitor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewCadMonitor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewCadMonitor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewCadMonitor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new ViewCadMonitor().setVisible(true);
            }
        });
    }

    // gets dos campos de entrada
    public String getCampoMatricula() {
        return cpMatricula.getText();
    }

    public String getCampoNome() {
        return cpNome.getText();
    }

    public String getCampoDisciplina() {
        return comboDisciplinaSigla.getSelectedItem().toString();
    }

    // sets dos campos de texto
    public void setCampoMatricula(String texto) {
        this.cpMatricula.setText(texto);
    }

    public void setCampoNome(String texto) {
        this.cpNome.setText(texto);
    }

    public void setCampoDisciplina(String texto) {
        this.comboDisciplinaSigla.setSelectedItem(texto);
    }

    // gets dos botoes
    public JButton getBotaoVoltar() {
        return this.botaoVoltar;
    }

    // métodos de ação dos botoes    
    public void addBotaoVoltar(ActionListener listener) {
        this.botaoVoltar.addActionListener(listener);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoVoltar;
    private javax.swing.JButton btCadMonSalvar;
    private javax.swing.JButton btRemover;
    private javax.swing.JComboBox<String> comboDisciplinaSigla;
    private javax.swing.JTextField cpDtFinal;
    private javax.swing.JTextField cpDtInicio;
    private javax.swing.JTextField cpMatricula;
    private javax.swing.JTextField cpNome;
    private javax.swing.JTextField cpNomeDisciplina;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaMonitores;
    // End of variables declaration//GEN-END:variables
}