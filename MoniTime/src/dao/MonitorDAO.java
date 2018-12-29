/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.Conexao;
import exception.DAOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Disciplina;
import model.Monitor;
import model.Usuario;

/**
 *
 * @author snow
 */
public class MonitorDAO {

    private Connection conexao;

    // ADICIONA MONITOR
    public boolean addMonitor(Monitor monitor) throws DAOException, SQLException {
        conexao = null;
        //String query = "INSERT INTO monitor(monusumatricula, monmtndissigla, mondtinicio, mondtfim) VALUES (?,?,?,?)";
        String sp_call = "{call sp_addMonitor(?,?,?,?)}";
        try {
            conexao = Conexao.getConnection();
            CallableStatement call = conexao.prepareCall(sp_call);
            //PreparedStatement stmt = conexao.prepareStatement(query);
            //stmt.setString(1, monitor.getMatricula());
            call.setString(1, monitor.getMatricula());
            //stmt.setString(2, monitor.getDisp().getSigla());
            call.setString(2, monitor.getDisp().getSigla());
            java.sql.Date iniDate = new java.sql.Date(monitor.getDtInicio().getTime());
            java.sql.Date endDate = new java.sql.Date(monitor.getDtFinal().getTime());
            //stmt.setDate(3, iniDate);
            call.setDate(3, iniDate);
            //stmt.setDate(4, endDate);
            call.setDate(4, endDate);
            //stmt.execute();
            call.execute();
            conexao.close();
            return true;
        } catch (SQLException ex) {
            throw new DAOException("Erro ao inserir monitor:\n\n" + ex.getMessage(), ex);
        } finally {
            Conexao.closeConnection(conexao);
        }
    }

    // ADICIONA MONITOR
    public boolean addMonitorNovo(Monitor monitor) throws DAOException, SQLException {
        conexao = null;
        String query = "INSERT INTO monitor(monusumatricula, mondissigla, mondtinicio, mondtfim) VALUES (?,?,?,?)";
        try {
            conexao = Conexao.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setString(1, monitor.getMatricula());
            stmt.setString(2, monitor.getDisp().getSigla());
            java.sql.Date iniDate = new java.sql.Date(monitor.getDtInicio().getTime());
            java.sql.Date endDate = new java.sql.Date(monitor.getDtFinal().getTime());
            stmt.setDate(3, iniDate);
            stmt.setDate(4, endDate);
            stmt.execute();
            conexao.close();
            return true;
        } catch (SQLException ex) {
            throw new DAOException("Erro ao inserir monitor:\n\n" + ex.getMessage(), ex);
        } finally {
            Conexao.closeConnection(conexao);
        }
    }

    // REMOVE MONITOR
    public boolean remMonitor(Monitor monitor) throws DAOException, SQLException {
        conexao = null;
        String sp_call = "{call sp_remMonitor(?)}";
        try {
            conexao = Conexao.getConnection();
            CallableStatement call = conexao.prepareCall(sp_call);
            call.setString(1, monitor.getMatricula());
            call.execute();
            return true;
        } catch (SQLException ex) {
            throw new DAOException("Erro ao excluir monitor:\n\n" + ex.getMessage(), ex);
        } finally {
            Conexao.closeConnection(conexao);
        }
    }

    public Monitor getMonitor(Usuario usuario) throws DAOException, SQLException {
        conexao = null;
        String query = "SELECT *"
                + " FROM monitor"
                + " inner join usuario on usumatricula = monusumatricula"
                + " inner join disciplina on dissigla = mondissigla"
                + " where monusumatricula = " + usuario.getMatricula();
        try {
            conexao = Conexao.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(query);
            ResultSet resultado = stmt.executeQuery();
            Monitor monitor = new Monitor(usuario);
            if (resultado.first()) {
                monitor.getDisp().setSigla(resultado.getString("mondissigla"));
                monitor.setDtInicio(resultado.getDate("mondtinicio"));
                monitor.setDtFinal(resultado.getDate("mondtfim"));
                return monitor;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            throw new DAOException("Erro ao localizar monitor:\n\n" + ex.getMessage(), ex);
        } finally {
            Conexao.closeConnection(conexao);
        }
    }

    // CONSULTA DE MONITORES
    public List<Monitor> pesquisarTodos() throws DAOException, SQLException {
        conexao = null;
        List<Monitor> lista = new ArrayList<>();
        String query = "SELECT usumatricula, usunome, disnome, dissigla, mondtinicio, mondtfim "
                + "FROM monitor "
                + "inner join usuario on usumatricula = monusumatricula "
                + "inner join disciplina on mondissigla = dissigla";

        try {
            conexao = Conexao.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(query);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Monitor monitor = new Monitor();
                Disciplina disc = new Disciplina();
                disc.setSigla(resultado.getString("dissigla"));
                disc.setNome(resultado.getString("disnome"));
                monitor.setMatricula(resultado.getString("usumatricula"));
                monitor.setNome(resultado.getString("usunome"));
                monitor.setDisp(disc);
                monitor.getDisp().setNome(resultado.getString("disnome"));
                monitor.setDtInicio(resultado.getDate("mondtinicio"));
                monitor.setDtFinal(resultado.getDate("mondtfim"));
                lista.add(monitor);
            }
            stmt.close();
            return lista;

        } catch (SQLException ex) {
            throw new DAOException("Erro ao listar monitores:\n\n" + ex.getMessage(), ex);
        } finally {
            Conexao.closeConnection(conexao);
        }
    }

    // Pesquisa nome aluno
    public String pesqNomeUsuario(String mat) throws DAOException, SQLException {
        conexao = null;
        String saida = "Matricula invalida";
        String query = "SELECT usunome"
                + " FROM usuario where usumatricula = " + mat;
        try {
            conexao = Conexao.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(query);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                saida = resultado.getString("usunome");
            }
            stmt.close();
            return saida;

        } catch (SQLException ex) {
            return "Matricula invalida";
            //throw new DAOException("Erro ao pesquisar usuario:\n\n" + ex.getMessage(), ex);
        } finally {
            Conexao.closeConnection(conexao);
        }
    }

    public String pesqMonitoriaAtiva(String disc) throws DAOException, SQLException {
        conexao = null;
        String saida = "Monitoria invalida";
        String query = "SELECT disnome from disciplina where dissigla = '" + disc + "'";
        try {
            conexao = Conexao.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(query);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                saida = resultado.getString("disnome");
            }
            stmt.close();
            return saida;

        } catch (SQLException ex) {
            throw new DAOException("Erro ao pesquisar nome da monitoria:\n\n" + ex.getMessage(), ex);
        } finally {
            Conexao.closeConnection(conexao);
        }
    }
}
