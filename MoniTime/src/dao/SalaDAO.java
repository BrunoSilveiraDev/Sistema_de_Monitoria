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
import model.Sala;

/**
 *
 * @author snow
 */
public class SalaDAO {

    private Connection conexao;

    // ADICIONA SALA
    public boolean addSala(Sala sala) throws DAOException, SQLException {

        conexao = null;
        String query = "INSERT INTO sala(salid, saldiasemana, salhoraini, salhorafim, salmonitor) VALUES (?,?,?,?,?)";

        try {
            conexao = Conexao.getConnection();
            //conexao.setAutoCommit(false);
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setString(1, sala.getId());
            stmt.setString(2, sala.getDiaSemana());
            stmt.setString(3, sala.getHorarioIni());
            stmt.setString(4, sala.getHorarioFim());
            stmt.setString(5, sala.getOcupacao());
            stmt.execute();
            stmt.close();
            return true;
        } catch (SQLException ex) {
            throw new DAOException("Erro ao inserir sala:\n\n" + ex.getMessage(), ex);
        } finally {
            Conexao.closeConnection(conexao);
        }
    }

    // Atualiza ocupacao
    public boolean atualizaOcupacao(Sala sala, String ocup) throws DAOException, SQLException {

        conexao = null;
        String query = "update sala set salmonitor = ?"
                + " where salid=? and saldiasemana=? and salhoraini=? and salhorafim=?";

        try {
            conexao = Conexao.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setString(1, ocup);
            stmt.setString(2, sala.getId());
            stmt.setString(3, sala.getDiaSemana());
            stmt.setString(4, sala.getHorarioIni());
            stmt.setString(5, sala.getHorarioFim());
            stmt.execute();
            stmt.close();
            return true;
        } catch (SQLException ex) {
            throw new DAOException("Erro ao atualizar sala:\n\n" + ex.getMessage(), ex);
        } finally {
            Conexao.closeConnection(conexao);
        }
    }

    // DELETA SALA
    public boolean delSala(Sala sala) throws DAOException, SQLException {

        String query = "DELETE FROM sala WHERE salid = ? and saldiasemana = ? and salhoraini = ? and salhorafim = ?";

        try {
            conexao = Conexao.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setString(1, sala.getId());
            stmt.setString(2, sala.getDiaSemana());
            stmt.setString(3, sala.getHorarioIni());
            stmt.setString(4, sala.getHorarioFim());
            stmt.execute();

            return true;
        } catch (SQLException ex) {
            throw new DAOException("Erro ao excluir sala:\n\n" + ex.getMessage(), ex);
        } finally {
            Conexao.closeConnection(conexao);
        }
    }

    // CONSULTA DE Salas
    public List<Sala> pesquisarTodas(String id) throws DAOException, SQLException {
        conexao = null;
        List<Sala> lista = new ArrayList<>();
        String query = "SELECT salid, saldiasemana, salhoraini, salhorafim, salmonitor "
                + "FROM sala WHERE salid LIKE ?";

        try {
            conexao = Conexao.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setString(1, "%" + id + "%");
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                Sala sala = new Sala();
                sala.setId(resultado.getString("salid"));
                sala.setDiaSemana(resultado.getString("saldiasemana"));
                sala.setHorarioIni(resultado.getString("salhoraini").substring(0, 5));
                sala.setHorarioFim(resultado.getString("salhorafim").substring(0, 5));
                sala.setOcupacao(resultado.getString("salmonitor"));
                lista.add(sala);
            }
            stmt.close();
            return lista;

        } catch (SQLException ex) {
            throw new DAOException("Erro ao listar salas:\n\n" + ex.getMessage(), ex);
        } finally {
            Conexao.closeConnection(conexao);
        }
    }
}
