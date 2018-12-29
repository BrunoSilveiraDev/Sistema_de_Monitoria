/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Monitor;
import util.DataUtil;

/**
 *
 * @author snow
 */
public class ViewCadMonitorTM extends AbstractTableModel {

    List<Monitor> monitores;

    public ViewCadMonitorTM() {
    }

    public ViewCadMonitorTM(List<Monitor> monitores) {
        this.monitores = monitores;
    }

    @Override
    public int getRowCount() {
        return monitores.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Monitor m = monitores.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return m.getNome();
            case 1:
                return m.getDisp().getNome();
            case 2:
                return DataUtil.dateToStringddMMyyy(m.getDtInicio());
            case 3:
                return DataUtil.dateToStringddMMyyy(m.getDtFinal());
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Nome";
            case 1:
                return "Disciplina";
            case 2:
                return "Data Inicio";
            case 3:
                return "Data Fim";
            default:
                return null;
        }
    }

    public void refresh(List<Monitor> monitores) {
        this.monitores = monitores;
        fireTableChanged(null);
    }
}
