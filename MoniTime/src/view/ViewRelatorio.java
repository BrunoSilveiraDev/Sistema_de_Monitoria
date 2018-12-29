/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControleRelatorios;
import exception.DAOException;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import model.Relatorio;

/**
 *
 * @author snow
 */
public class ViewRelatorio extends javax.swing.JFrame {

    ViewRelatorioTM viewRelatorioTM;
    ControleRelatorios controller;
    List<Relatorio> relatorios;

    public ViewRelatorio() throws DAOException, SQLException {
        initComponents();
        viewRelatorioTM = new ViewRelatorioTM();
        URL iconURL = getClass().getResource("/img/clock4.png");
        ImageIcon icon = new ImageIcon(iconURL);
        this.setIconImage(icon.getImage());
        tbRelatorio.setVisible(false);
        comboRelat.removeAllItems();
        comboRelat.addItem("Selecione o tipo");
        comboRelat.addItem("Horarios mais procurados");
        comboRelat.addItem("Dias mais procurados");
        comboRelat.addItem("Disciplinas mais procuradas");
        comboRelat.addItem("Alunos por monitor");
        comboRelat.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == 1) {
                if (comboRelat.getSelectedIndex() >= 0) {
                    try {
                        switch (comboRelat.getSelectedIndex()) {
                            case 0://em branco
                                tbRelatorio.setVisible(false);
                                break;
                            case 1://horarios
                                tbRelatorio.setVisible(true);
                                defRelat();
                                break;
                            case 2:
                                tbRelatorio.setVisible(true);
                                viewRelatorioTM.setColumnCount(2);
                                this.relatorios = controller.constroiRelatorio("vw_diasprocurados", 2);
                                this.relatorios.sort((o1, o2) -> o2.getB().compareTo(o1.getB()));
                                viewRelatorioTM.refresh(this.relatorios);
                                tbRelatorio.setModel(viewRelatorioTM);
                                tbRelatorio.getColumnModel().getColumn(0).setMinWidth(320);
                                tbRelatorio.getColumnModel().getColumn(0).setHeaderValue("Dia");
                                alinhaColunaCentro(tbRelatorio, 1);
                                tbRelatorio.getColumnModel().getColumn(1).setHeaderValue("Alunos");
                                tbRelatorio.getTableHeader().repaint();
                                break;
                            case 3:
                                tbRelatorio.setVisible(true);
                                viewRelatorioTM.setColumnCount(2);
                                this.relatorios = controller.constroiRelatorio("vw_disciplinaprocuradas", 2);
                                this.relatorios.sort((o1, o2) -> o2.getB().compareTo(o1.getB()));
                                viewRelatorioTM.refresh(this.relatorios);
                                tbRelatorio.setModel(viewRelatorioTM);
                                tbRelatorio.getColumnModel().getColumn(0).setMinWidth(320);
                                tbRelatorio.getColumnModel().getColumn(0).setHeaderValue("Disciplina");
                                alinhaColunaCentro(tbRelatorio, 1);
                                tbRelatorio.getColumnModel().getColumn(1).setHeaderValue("Alunos");
                                tbRelatorio.getTableHeader().repaint();
                                break;
                            case 4:
                                tbRelatorio.setVisible(true);
                                viewRelatorioTM.setColumnCount(2);
                                this.relatorios = controller.constroiRelatorio("vw_alunospormonitor", 2);
                                this.relatorios.sort((o1, o2) -> o2.getB().compareTo(o1.getB()));
                                viewRelatorioTM.refresh(this.relatorios);
                                tbRelatorio.setModel(viewRelatorioTM);
                                tbRelatorio.getColumnModel().getColumn(0).setMinWidth(320);
                                tbRelatorio.getColumnModel().getColumn(0).setHeaderValue("Monitor");
                                alinhaColunaCentro(tbRelatorio, 1);
                                tbRelatorio.getColumnModel().getColumn(1).setHeaderValue("Alunos");
                                tbRelatorio.getTableHeader().repaint();
                                break;
                        }
                    } catch (DAOException | SQLException ex) {
                        Logger.getLogger(ViewRelatorio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    public ViewRelatorio(List<Relatorio> relat) {
        initComponents();
        this.relatorios = relat;
        viewRelatorioTM = new ViewRelatorioTM(relat);
    }

    public void defRelat() throws DAOException, SQLException {
        viewRelatorioTM.setColumnCount(3);
        this.relatorios = controller.constroiRelatorio("vw_horariosprocurados", 3);
        this.relatorios.sort((o1, o2) -> o2.getC().compareTo(o1.getC()));
        viewRelatorioTM.refresh(this.relatorios);
        tbRelatorio.setModel(viewRelatorioTM);
        alinhaColunaCentro(tbRelatorio, 0);
        alinhaColunaCentro(tbRelatorio, 1);
        alinhaColunaCentro(tbRelatorio, 2);
        tbRelatorio.getColumnModel().getColumn(0).setMinWidth(160);
        tbRelatorio.getColumnModel().getColumn(0).setHeaderValue("Horario inicial");
        tbRelatorio.getColumnModel().getColumn(1).setMinWidth(160);
        tbRelatorio.getColumnModel().getColumn(1).setHeaderValue("Horario final");
        tbRelatorio.getColumnModel().getColumn(2).setHeaderValue("Alunos");
        tbRelatorio.getTableHeader().repaint();
    }

    public void setDataSet(List<Relatorio> relats) {
        viewRelatorioTM = new ViewRelatorioTM(relats);
        tbRelatorio.setModel(viewRelatorioTM);//vazio
        this.relatorios = relats;//fill monitorias
    }

    public void updateView() throws SQLException, DAOException {
        viewRelatorioTM.refresh(this.relatorios);
    }

    public void setController(ControleRelatorios controleRelatorios) throws DAOException, SQLException {
        this.controller = controleRelatorios;
        defRelat();
        comboRelat.setSelectedIndex(0);
    }

    public static void alinhaColunaCentro(JTable table, int numCol) {

        DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
        cellRender.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(numCol).setCellRenderer(
                cellRender);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbRelatorio = new javax.swing.JTable();
        comboRelat = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Monitime | Relatórios");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbRelatorio.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbRelatorio);

        comboRelat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("RELATÓRIOS DE COORDENAÇÃO");

        jLabel2.setText("Selecione o tipo:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(79, 79, 79))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboRelat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboRelat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btSair.setText("Fechar");
        btSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btSair)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btSair)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btSairActionPerformed

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
            java.util.logging.Logger.getLogger(ViewRelatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewRelatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewRelatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewRelatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ViewRelatorio().setVisible(true);
                } catch (DAOException | SQLException ex) {
                    Logger.getLogger(ViewRelatorio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btSair;
    private javax.swing.JComboBox<String> comboRelat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbRelatorio;
    // End of variables declaration//GEN-END:variables
}
