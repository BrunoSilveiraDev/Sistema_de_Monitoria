/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.MonitorDAO;
import exception.DAOException;
import java.sql.SQLException;
import java.util.List;
import model.Monitor;
import view.ViewCadMonitor;

/**
 *
 * @author snow
 */
public class ControleCadMonitor {

    ViewCadMonitor viewCadMonitor;
    MonitorDAO dao = new MonitorDAO();

    public ControleCadMonitor() {

    }

    public void setView(ViewCadMonitor viewCadMonitor) {
        this.viewCadMonitor = viewCadMonitor;
    }

    public boolean addMonitor(Monitor monitor) throws DAOException, SQLException {
       return dao.addMonitorNovo(monitor);
    }

    public void remMonitor(Monitor monitor) throws DAOException, SQLException {
        dao.remMonitor(monitor);
    }

    public Monitor getMonitor(Monitor monitor) throws DAOException, SQLException {
        return dao.getMonitor(monitor);
    }

    public List<Monitor> getAllData() throws DAOException, SQLException {
        return dao.pesquisarTodos();
    }

    public String pesqNomeUsuario(String mat) throws DAOException, SQLException {
        return dao.pesqNomeUsuario(mat);
    }

    public String pesqMonitoriaAtiva(String disc) throws DAOException, SQLException {
        return dao.pesqMonitoriaAtiva(disc);
    }
}
