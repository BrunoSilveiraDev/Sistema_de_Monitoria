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
import view.ViewCadSala;

/**
 *
 * @author snow
 */
public class ControleCadSala {

    ViewCadSala viewCadSala;
    SalaDAO dao = new SalaDAO();

    public void setView(ViewCadSala viewCadSala) {
        this.viewCadSala = viewCadSala;
    }

    public void addSala(Sala sala) throws DAOException, SQLException {
        dao.addSala(sala);
        viewCadSala.updateView();
    }
    
    public void delSala(Sala sala) throws DAOException, SQLException {
        dao.delSala(sala);
        viewCadSala.updateView();
    }

    public List<Sala> getAllData() throws DAOException, SQLException {
        return dao.pesquisarTodas("_");
    }
}
