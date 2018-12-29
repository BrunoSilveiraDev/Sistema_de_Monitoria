/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.LotacaoDAO;
import dao.MonitoriaNBDDAO;
import exception.DAOException;
import java.sql.SQLException;
import java.util.List;
import model.Lotacao;
import model.MonitoriaNBD;
import view.ViewAlunoSelMonitoria;

/**
 *
 * @author snow
 */
public class ControleAlunoSelMonitoria {

    ViewAlunoSelMonitoria viewAlunoSelMonitoria;
    MonitoriaNBDDAO mfdao = new MonitoriaNBDDAO();
    LotacaoDAO lotdao = new LotacaoDAO();

    public ControleAlunoSelMonitoria() {

    }

    public void setView(ViewAlunoSelMonitoria view) {
        this.viewAlunoSelMonitoria = view;
    }

    public void addMonitoria(MonitoriaNBD moni) throws DAOException, SQLException {
        mfdao.addMonitoria(moni);
    }

    public String addLotacao(Lotacao lot) throws DAOException, SQLException {
        return lotdao.addLotacao(lot);
    }

    public List<Lotacao> lotMonitoria(MonitoriaNBD moni) throws DAOException, SQLException {
       return mfdao.lotMonitoria(moni);
    }

    public void delMonitoriaProc(MonitoriaNBD moni) throws DAOException, SQLException {
        mfdao.delMonitoriaProc(moni);
    }

    public void delLotacao(MonitoriaNBD lot, String mat) throws DAOException, SQLException {
        lotdao.delLotacao(lot, mat);
    }

    public String pesqDiscpNome(String abrev) throws DAOException, SQLException {
        return mfdao.pesqMonitoriaAtiva(abrev);
    }

    public List<MonitoriaNBD> getAllMoniFuncionais() throws DAOException, SQLException {
        return mfdao.pesquisarTodas("_");
    }

    public List<MonitoriaNBD> getAllLotacaoMonis(String mat) throws DAOException, SQLException {
        return lotdao.pesquisarTodasMonis(mat);
    }
}
