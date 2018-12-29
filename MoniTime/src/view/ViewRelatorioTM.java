package view;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Relatorio;

/**
 *
 * @author snow
 */
public class ViewRelatorioTM extends AbstractTableModel {

    private List<Relatorio> relatorios;
    private int columnCount = 1;

    public ViewRelatorioTM() {
    }

    public ViewRelatorioTM(List<Relatorio> relat) {
        this.relatorios = relat;
    }

    @Override
    public int getRowCount() {
        return relatorios.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int cols) {
        columnCount = cols;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Relatorio relat = relatorios.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return relat.getA();
            case 1:
                return relat.getB();
            case 2:
                return relat.getC();
            case 3:
                return relat.getD();
            case 4:
                return relat.getE();
            case 5:
                return relat.getF();
            case 6:
                return relat.getG();
            case 7:
                return relat.getH();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "A";
            case 1:
                return "B";
            case 2:
                return "C";
            case 3:
                return "D";
            case 4:
                return "E";
            case 5:
                return "F";
            case 6:
                return "G";
            case 7:
                return "H";
            default:
                return null;
        }
    }

    public void refresh(List<Relatorio> relat) {
        this.relatorios = relat;
        fireTableChanged(null);
    }
}
