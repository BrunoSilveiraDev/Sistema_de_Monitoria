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
import model.Lotacao;
import model.Monitor;
import model.MonitoriaNBD;
import model.Sala;

/**
 *
 * @author snow
 */
public class MonitoriaNBDDAO {

    private Connection conexao;

    // ADICIONA MONITORIA
    public boolean addMonitoria(MonitoriaNBD monitoria) throws DAOException, SQLException {
        conexao = null;
        String query = "INSERT INTO monitoria (moamonusumatricula, moadissigla, moasalid, moasaldiasemana, moasalhoraini, moasalhorafim)"
                + " VALUES (?,?,?,?,?,?)";

        try {
            conexao = Conexao.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setString(1, monitoria.getMonitor().getMatricula());
            stmt.setString(2, monitoria.getMonitor().getDisp().getSigla());
            stmt.setString(3, monitoria.getSala().getId());
            stmt.setString(4, monitoria.getSala().getDiaSemana());
            stmt.setString(5, monitoria.getSala().getHorarioIni());
            stmt.setString(6, monitoria.getSala().getHorarioFim());
            stmt.execute();
            conexao.close();
            return true;
        } catch (SQLException ex) {
            throw new DAOException("Erro ao inserir  monitoria:\n\n" + ex.getMessage(), ex);
        } finally {
            Conexao.closeConnection(conexao);
        }
    }

    // LOTAÇAO MONITORIA
    public List<Lotacao> lotMonitoria(MonitoriaNBD moni) throws DAOException, SQLException {

        String sp_call = "{call sp_listadefrequencia(?,?,?,?,?)}";
        List<Lotacao> lista = new ArrayList<>();
        try {
            conexao = Conexao.getConnection();
            CallableStatement call = conexao.prepareCall(sp_call);
            call.setString(1, moni.getMonitor().getMatricula());
            call.setString(2, moni.getMonitor().getDisp().getSigla());
            call.setString(3, moni.getSala().getId());
            call.setString(4, moni.getSala().getDiaSemana());
            call.setString(5, moni.getSala().getHorarioIni());
            ResultSet resultado = call.executeQuery();
            while (resultado.next()) {
                Lotacao lot = new Lotacao();
                lot.setMoaid(resultado.getInt("id"));
                lot.setUsumatricula(resultado.getString("Aluno"));
                lista.add(lot);
            }
            return lista;
        } catch (SQLException ex) {
            throw new DAOException("Erro ao ver lotaçao da monitoria:\n\n" + ex.getMessage(), ex);
        } finally {
            Conexao.closeConnection(conexao);
        }
    }

    // DELETA MONITORIA
    public boolean delMonitoriaProc(MonitoriaNBD moni) throws DAOException, SQLException {

        String sp_call = "{call sp_delhorario(?,?,?,?,?)}";
        try {
            conexao = Conexao.getConnection();
            CallableStatement call = conexao.prepareCall(sp_call);
            call.setString(1, moni.getMonitor().getMatricula());
            call.setString(2, moni.getMonitor().getDisp().getSigla());
            call.setString(3, moni.getSala().getId());
            call.setString(4, moni.getSala().getDiaSemana());
            call.setString(5, moni.getSala().getHorarioIni());
            call.execute();
            return true;
        } catch (SQLException ex) {
            throw new DAOException("Erro ao excluir horario:\n\n" + ex.getMessage(), ex);
        } finally {
            Conexao.closeConnection(conexao);
        }
    }

    // DELETA MONITORIA
    public boolean delMonitoria(MonitoriaNBD moni) throws DAOException, SQLException {

        String query = "DELETE FROM monitoria WHERE moamonusumatricula = ? and moadissigla = ? and moasalid = ?"
                + " and moasaldiasemana = ? and moasalhoraini = ? and moasalhorafim = ?";
        try {
            conexao = Conexao.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setString(1, moni.getMonitor().getDisp().getSigla());
            stmt.setString(2, moni.getMonitor().getMatricula());
            stmt.setString(3, moni.getSala().getId());
            stmt.setString(4, moni.getSala().getDiaSemana());
            stmt.setString(5, moni.getSala().getHorarioIni());
            stmt.setString(6, moni.getSala().getHorarioFim());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            throw new DAOException("Erro ao excluir monitoria:\n\n" + ex.getMessage(), ex);
        } finally {
            Conexao.closeConnection(conexao);
        }
    }

    public String pesqMonitoriaAtiva(String disc) throws DAOException, SQLException {
        conexao = null;
        String saida = "Monitoria invalida";
        String query = "SELECT disnome FROM monitorianova inner join disciplina on dissigla = mtndissigla where dissigla = '" + disc + "'";
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

    // CONSULTA DE MONITORIAS FUNCIONAIS
    public List<MonitoriaNBD> pesquisarTodas(String str) throws DAOException, SQLException {
        conexao = null;
        List<MonitoriaNBD> lista = new ArrayList<>();
        String query = "SELECT moaid, moamonusumatricula, moadissigla, disnome, usunome, moasalid, moasaldiasemana, moasalhoraini, moasalhorafim"
                + " FROM monitoria"
                + " inner join disciplina on dissigla = moadissigla"
                + " inner join usuario on usumatricula = moamonusumatricula"
                + " WHERE moadissigla LIKE ?";
        String queryb = "select lotusumatricula, usunome from monitoria"//pegar pela id
                + "                inner join lotacao on moaid = lotmoaid"
                + "                inner join usuario on usumatricula = lotusumatricula"
                + "                where moamonusumatricula = '0000000000002';";
        try {
            conexao = Conexao.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setString(1, "%" + str + "%");
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                MonitoriaNBD moni = new MonitoriaNBD(new Monitor(), new Sala());
                moni.setId(resultado.getInt("moaid"));
                moni.getMonitor().getDisp().setNome(resultado.getString("disnome"));
                moni.getMonitor().setMatricula(resultado.getString("moamonusumatricula"));
                moni.getMonitor().setNome(resultado.getString("usunome"));
                moni.getSala().setId(resultado.getString("moasalid"));
                moni.getSala().setDiaSemana(resultado.getString("moasaldiasemana"));
                moni.getSala().setHorarioIni(resultado.getString("moasalhoraini"));
                moni.getSala().setHorarioFim(resultado.getString("moasalhorafim"));
                lista.add(moni);
            }
            stmt.close();
            return lista;

        } catch (SQLException ex) {
            throw new DAOException("Erro ao listar monitorias funcionais:\n\n" + ex.getMessage(), ex);
        } finally {
            Conexao.closeConnection(conexao);
        }
    }
}
