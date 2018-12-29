/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.Conexao;
import exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Disciplina;

/**
 *
 * @author snow
 */
public class STRsDAO {

    private Connection conexao;

    // Carrega diaSemana
    public List<String> diaSemana(int tipo) throws DAOException, SQLException {
        conexao = null;
        List<String> lista = new ArrayList<>();
        String query = "SELECT * FROM diasemana";

        try {
            conexao = Conexao.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(query);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                lista.add(resultado.getString(tipo));
            }
            stmt.close();
            return lista;

        } catch (SQLException ex) {
            throw new DAOException("Erro ao listar dias da semana:\n\n" + ex.getMessage(), ex);
        } finally {
            Conexao.closeConnection(conexao);
        }
    }

    // Carrega horarios
    public List<String> horarios(int tipo) throws DAOException, SQLException {
        conexao = null;
        List<String> lista = new ArrayList<>();
        String query = "SELECT * FROM horario";

        try {
            conexao = Conexao.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(query);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                lista.add(resultado.getString(tipo));
            }
            stmt.close();
            return lista;

        } catch (SQLException ex) {
            throw new DAOException("Erro ao listar horarios:\n\n" + ex.getMessage(), ex);
        } finally {
            Conexao.closeConnection(conexao);
        }
    }

    // Carrega horarios
    public List<String> salas() throws DAOException, SQLException {
        conexao = null;
        List<String> lista = new ArrayList<>();
        String query = "SELECT * FROM listasalas";

        try {
            conexao = Conexao.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(query);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                lista.add(resultado.getString(2));
            }
            stmt.close();
            return lista;

        } catch (SQLException ex) {
            throw new DAOException("Erro ao listar salas:\n\n" + ex.getMessage(), ex);
        } finally {
            Conexao.closeConnection(conexao);
        }
    }

    public List<String> getStrings(String table, int pos) throws DAOException, SQLException {
        conexao = null;
        List<String> lista = new ArrayList<>();
        String query = "SELECT * FROM " + table;

        try {
            conexao = Conexao.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(query);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                lista.add(resultado.getString(1));
            }
            stmt.close();
            return lista;

        } catch (SQLException ex) {
            throw new DAOException("Erro ao recuperar string:\n\n" + ex.getMessage(), ex);
        } finally {
            Conexao.closeConnection(conexao);
        }
    }

    public List<Disciplina> disciplinas() throws DAOException, SQLException {
        conexao = null;
        List<Disciplina> lista = new ArrayList<>();
        String query = "SELECT * FROM disciplina";

        try {
            conexao = Conexao.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(query);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                Disciplina disc = new Disciplina();
                disc.setSigla(resultado.getString(1));
                disc.setNome(resultado.getString(2));
                lista.add(disc);
            }
            stmt.close();
            return lista;

        } catch (SQLException ex) {
            throw new DAOException("Erro ao listar disciplinas:\n\n" + ex.getMessage(), ex);
        } finally {
            Conexao.closeConnection(conexao);
        }
    }

    public List<String> disciplinasSiglas() throws DAOException, SQLException {
        conexao = null;
        List<String> lista = new ArrayList<>();
        String query = "SELECT * FROM disciplina";

        try {
            conexao = Conexao.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(query);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                lista.add(resultado.getString(1));
            }
            stmt.close();
            return lista;

        } catch (SQLException ex) {
            throw new DAOException("Erro ao listar disciplinas:\n\n" + ex.getMessage(), ex);
        } finally {
            Conexao.closeConnection(conexao);
        }
    }

    public List<String> disciplinasNomes() throws DAOException, SQLException {
        conexao = null;
        List<String> lista = new ArrayList<>();
        String query = "SELECT * FROM disciplina";

        try {
            conexao = Conexao.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(query);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                lista.add(resultado.getString(2));
            }
            stmt.close();
            return lista;

        } catch (SQLException ex) {
            throw new DAOException("Erro ao listar disciplinas:\n\n" + ex.getMessage(), ex);
        } finally {
            Conexao.closeConnection(conexao);
        }
    }

    public List<String> monitoriasAtivas() throws DAOException, SQLException {
        conexao = null;
        List<String> lista = new ArrayList<>();
        String query = "SELECT dissigla FROM disciplina";

        try {
            conexao = Conexao.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(query);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                lista.add(resultado.getString(1));
            }
            stmt.close();
            return lista;

        } catch (SQLException ex) {
            throw new DAOException("Erro ao listar disciplinas:\n\n" + ex.getMessage(), ex);
        } finally {
            Conexao.closeConnection(conexao);
        }
    }
}
