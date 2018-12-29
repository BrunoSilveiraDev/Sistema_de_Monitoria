/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.MonitoriaNBD;

/**
 *
 * @author snow
 */
public class ViewAlunoSelMonitoriaTM extends AbstractTableModel {

    List<MonitoriaNBD> monitorias;

    public ViewAlunoSelMonitoriaTM() {
    }

    public ViewAlunoSelMonitoriaTM(List<MonitoriaNBD> monitorias) {
        this.monitorias = monitorias;
    }

    @Override
    public int getRowCount() {
        return monitorias.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MonitoriaNBD m = monitorias.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return m.getMonitor().getDisp().getNome();
            case 1:
                return m.getMonitor().getNome();
            case 2:
                return m.getSala().getId();
            case 3:
                return m.getSala().getDiaSemana();
            case 4:
                return m.getSala().getHorarioIni().substring(0, 5);
            case 5:
                return m.getSala().getHorarioFim().substring(0, 5);
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
                return "Monitor";
            case 2:
                return "Sala";
            case 3:
                return "Dia da semana";
            case 4:
                return "Hora inicio";
            case 5:
                return "Hora fim";
            default:
                return null;
        }
    }

    public void refresh(List<MonitoriaNBD> monitorias) {
        this.monitorias = monitorias;
        fireTableChanged(null);
    }
}
