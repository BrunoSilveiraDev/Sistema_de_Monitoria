/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.RelatorioDAO;
import exception.DAOException;
import java.sql.SQLException;
import java.util.List;
import model.Relatorio;
import view.ViewRelatorio;

/**
 *
 * @author snow
 */
public class ControleRelatorios {

    ViewRelatorio viewRelatorio;
    RelatorioDAO dao = new RelatorioDAO();

    public ControleRelatorios() {

    }

    public void setView(ViewRelatorio viewRelatorio) {
        this.viewRelatorio = viewRelatorio;
    }

    public List<Relatorio> constroiRelatorio(String viewName, int nFields) throws DAOException, SQLException {
        return dao.constroiRelatorio(viewName, nFields);
    }
}
