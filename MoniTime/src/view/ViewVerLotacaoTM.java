/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Lotacao;

/**
 *
 * @author snow
 */
public class ViewVerLotacaoTM extends AbstractTableModel {

    List<Lotacao> lots;

    public ViewVerLotacaoTM() {
    }

    public ViewVerLotacaoTM(List<Lotacao> lots) {
        this.lots = lots;
    }

    @Override
    public int getRowCount() {
        return lots.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Lotacao lot = lots.get(rowIndex);
        switch (columnIndex) {
            case 0:
                //return lot.getMoaid();
                return lot.getUsumatricula();
            case 1:
                return lot.getUsumatricula();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Alunos";
            case 1:
                return "Aluno";
            default:
                return null;
        }
    }

    public void refresh(List<Lotacao> lots) {
        this.lots = lots;
        fireTableChanged(null);
    }
}
