/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dao.STRsDAO;
import exception.DAOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Disciplina;

/**
 *
 * @author snow
 */
public class STRs {

    public static ArrayList<String> diaSemana = new ArrayList<>();
    public static ArrayList<String> horarios = new ArrayList<>();
    public static STRsDAO strdao = new STRsDAO();
    public static ArrayList<Disciplina> discips = new ArrayList<>();
    public static ArrayList<String> disciplinas = new ArrayList<>();
    public static ArrayList<String> monitoriasAtivas = new ArrayList<>();
    public static String disciplina;

    public static List<String> getDiaSemana(int tipo) {
        try {
            return strdao.diaSemana(tipo);
        } catch (DAOException | SQLException ex) {
            Logger.getLogger(STRs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return diaSemana;
    }

    public static List<String> getHorarios(int tipo) {
        try {
            return strdao.horarios(tipo);
        } catch (DAOException | SQLException ex) {
            Logger.getLogger(STRs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return horarios;
    }

    public static List<String> getSalas() {
        try {
            return strdao.salas();
        } catch (DAOException | SQLException ex) {
            Logger.getLogger(STRs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return horarios;
    }
    
    public static List<Disciplina> getDisciplinas() {
        try {
            return strdao.disciplinas();
        } catch (DAOException | SQLException ex) {
            Logger.getLogger(STRs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return discips;
    }

    public static List<String> getDisciplinasSiglas() {
        try {
            return strdao.disciplinasSiglas();
        } catch (DAOException | SQLException ex) {
            Logger.getLogger(STRs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return disciplinas;
    }
    
    public static List<String> getDisciplinasNomes() {
        try {
            return strdao.disciplinasNomes();
        } catch (DAOException | SQLException ex) {
            Logger.getLogger(STRs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return disciplinas;
    }

    public static List<String> getMonitoriasAtivas() {
        try {
            return strdao.monitoriasAtivas();
        } catch (DAOException | SQLException ex) {
            Logger.getLogger(STRs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return monitoriasAtivas;
    }

    /*
    public static List<String> getMonitores() {
        try {
            return strdao.pesquisarMonitores("_");
        } catch (DAOException | SQLException ex) {
            Logger.getLogger(STRs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return monitores;
    }*/
}
