/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.SalaDAO;
import exception.DAOException;
import java.sql.SQLException;
import java.util.List;
import model.Sala;
import view.ViewMonitorSelecaoSala;

/**
 *
 * @author snow
 */
public class ControleMonitorSelSala {

    ViewMonitorSelecaoSala viewMonitorSelecaoSala;
    SalaDAO dao = new SalaDAO();

    public void setView(ViewMonitorSelecaoSala viewMonitorSelecaoSala) {
        this.viewMonitorSelecaoSala = viewMonitorSelecaoSala;
    }

    public void addSala(Sala sala) throws DAOException, SQLException {
        dao.addSala(sala);
        //viewMonitorSelecaoSala.updateView();
    }

    public void atualizaOcupacao(Sala sala, String ocup) throws DAOException, SQLException {
        dao.atualizaOcupacao(sala, ocup);
        //viewMonitorSelecaoSala.updateView();
    }

    public void delSala(Sala sala) throws DAOException, SQLException {
        dao.delSala(sala);
        //viewMonitorSelecaoSala.updateView();
    }

    public List<Sala> getAllData() throws DAOException, SQLException {
        return dao.pesquisarTodas("_");
    }
}
