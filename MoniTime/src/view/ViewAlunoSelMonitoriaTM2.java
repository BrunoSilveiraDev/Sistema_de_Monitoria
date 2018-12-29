/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.MonitoriaNBD;

/**
 *
 * @author snow
 */
public class ViewAlunoSelMonitoriaTM2 extends AbstractTableModel {

    List<MonitoriaNBD> cadastrados;

    public ViewAlunoSelMonitoriaTM2() {
    }

    public ViewAlunoSelMonitoriaTM2(List<MonitoriaNBD> cadastrados) {
        this.cadastrados = cadastrados;
    }

    @Override
    public int getRowCount() {
        return cadastrados.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MonitoriaNBD l = cadastrados.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return l.getMonitor().getDisp().getNome();
            case 1:
                return l.getSala().getId();
            case 2:
                return l.getSala().getDiaSemana();
            case 3:
                return l.getSala().getHorarioIni().substring(0, 5);
            case 4:
                return l.getSala().getHorarioFim().substring(0, 5);
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Disciplina";
            case 1:
                return "Sala";
            case 2:
                return "Dia da semana";
            case 3:
                return "Hora inicio";
            case 4:
                return "Hora fim";
            default:
                return null;
        }
    }

    public void refresh(List<MonitoriaNBD> cad) {
        this.cadastrados = cad;
        fireTableChanged(null);
    }
    
    public void refresh(List<MonitoriaNBD> cad, String mat) {
        List<MonitoriaNBD> lots = new ArrayList();
        for (MonitoriaNBD l : cad) {
            if (l.getMonitor().getMatricula().equals(mat)) {
                lots.add(l);
            }
        }
        this.cadastrados = lots;
        fireTableChanged(null);
    }
}
