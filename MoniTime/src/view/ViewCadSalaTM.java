/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Sala;

/**
 *
 * @author snow
 */
public class ViewCadSalaTM extends AbstractTableModel {

    List<Sala> salas;

    public ViewCadSalaTM() {
    }

    public ViewCadSalaTM(List<Sala> salas) {
        this.salas = salas;
    }

    @Override
    public int getRowCount() {
        return salas.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Sala sl = salas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return sl.getId();
            case 1:
                return sl.getDiaSemana();
            case 2:
                return sl.getHorarioIni();
            case 3:
                return sl.getHorarioFim();
            case 4:
                return sl.getOcupacao();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Sala";
            case 1:
                return "Dia";
            case 2:
                return "Início";
            case 3:
                return "Fim";
            case 4:
                return "Status";
            default:
                return null;
        }
    }

    public void refresh(List<Sala> salas) {
        this.salas = salas;
        fireTableChanged(null);
    }
}
