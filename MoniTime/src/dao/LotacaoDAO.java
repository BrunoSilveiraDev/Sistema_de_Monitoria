package dao;

import connection.Conexao;
import exception.DAOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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
public class LotacaoDAO {

    private Connection conexao;

    // ADICIONA LOTACAO
    public String addLotacao(Lotacao lotacao) throws DAOException, SQLException {
        conexao = null;
        String sp_call = "{ ? = call func_insert_lotacao(?,?)}";
        try {
            conexao = Conexao.getConnection();
            CallableStatement call = conexao.prepareCall(sp_call);
            call.setInt(2, lotacao.getMoaid());
            call.setString(3, lotacao.getUsumatricula());
            call.registerOutParameter(1, java.sql.Types.VARCHAR);
            call.execute();
            return call.getString(1);
        } catch (SQLIntegrityConstraintViolationException de) {
            return "Este horario já esta reservado.";
        } catch (SQLException ex) {
            throw new DAOException("Erro ao inserir lotaçao:\n\n" + ex.getMessage(), ex);
        } finally {
            Conexao.closeConnection(conexao);
        }
    }

    // DELETA LOTACAO
    public boolean delLotacao(MonitoriaNBD lotacao, String mat) throws DAOException, SQLException {
        String query = "DELETE FROM lotacao WHERE lotmoaid = ? and lotusumatricula = ?";
        try {
            conexao = Conexao.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setInt(1, lotacao.getId());
            stmt.setString(2, mat);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            throw new DAOException("Erro ao excluir lotacao:\n\n" + ex.getMessage(), ex);
        } finally {
            Conexao.closeConnection(conexao);
        }
    }

    // CONSULTA DE MONITORIAS
    public List<MonitoriaNBD> pesquisarTodasMonis(String mat) throws DAOException, SQLException {
        conexao = null;
        List<MonitoriaNBD> lista = new ArrayList<>();
        String query = "SELECT lotmoaid, lotusumatricula, moadissigla, moasalid, moasaldiasemana, moasalhoraini, moasalhorafim, usunome, dissigla, disnome"
                + " FROM lotacao"
                + " inner join monitoria on moaid = lotmoaid"
                + " inner join disciplina on dissigla = moadissigla"
                + " inner join usuario on usumatricula = lotusumatricula"
                + " WHERE lotusumatricula = " + mat;

        try {
            conexao = Conexao.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(query);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                MonitoriaNBD moni = new MonitoriaNBD(new Monitor(), new Sala());
                moni.setId(resultado.getInt("lotmoaid"));
                moni.getMonitor().getDisp().setSigla(resultado.getString("dissigla"));
                moni.getMonitor().getDisp().setNome(resultado.getString("disnome"));
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
            throw new DAOException("Erro ao listar lotaçoes:\n\n" + ex.getMessage(), ex);
        } finally {
            Conexao.closeConnection(conexao);
        }
    }
}
