package dao;

import connection.Conexao;
import exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import model.Relatorio;

/**
 *
 * @author snow
 */
public class RelatorioDAO {

    private Connection conexao;

    public List<Relatorio> constroiRelatorio(String viewName, int nFields) throws DAOException, SQLException {
        conexao = null;
        int fCount = 1;
        List<Relatorio> lista = new ArrayList<>();
        String query = "SELECT * from " + viewName;

        try {
            conexao = Conexao.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(query);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                Relatorio relat = new Relatorio();
                fCount = 1;
                if (fCount <= nFields) {
                    relat.setA(resultado.getString(fCount));
                    fCount++;
                }
                if (fCount <= nFields) {
                    relat.setB(resultado.getString(fCount));
                    fCount++;
                }
                if (fCount <= nFields) {
                    relat.setC(resultado.getString(fCount));
                    fCount++;
                }
                if (fCount <= nFields) {
                    relat.setD(resultado.getString(fCount));
                    fCount++;
                }
                if (fCount <= nFields) {
                    relat.setE(resultado.getString(fCount));
                    fCount++;
                }
                if (fCount <= nFields) {
                    relat.setF(resultado.getString(fCount));
                    fCount++;
                }
                if (fCount <= nFields) {
                    relat.setG(resultado.getString(fCount));
                    fCount++;
                }
                if (fCount <= nFields) {
                    relat.setH(resultado.getString(fCount));
                    fCount++;
                }
                lista.add(relat);
            }
            stmt.close();
            return lista;
        } catch (SQLException ex) {
            throw new DAOException("Erro ao construir relatorio:\n\n" + ex.getMessage(), ex);
        } finally {
            Conexao.closeConnection(conexao);
        }
    }
}
