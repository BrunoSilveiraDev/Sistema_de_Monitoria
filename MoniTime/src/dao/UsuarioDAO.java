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
import model.Usuario;

/**
 *
 * @author snow
 */
public class UsuarioDAO {

    private Connection conexao;

    // ADICIONA Usuario
    public boolean addUsuario(Usuario usuario) throws DAOException, SQLException {

        conexao = null;
        String query = "INSERT INTO usuario(usumatricula, usunome, ususenha, usutipo) VALUES (?,?,?,?)";

        try {
            conexao = Conexao.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setString(1, usuario.getMatricula());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTipo());
            stmt.execute();
            stmt.close();
            return true;
        } catch (SQLException ex) {
            throw new DAOException("Erro ao inserir usuario:\n\n" + ex.getMessage(), ex);
        } finally {
            Conexao.closeConnection(conexao);
        }
    }

    public boolean delUsuario(Usuario usuario) throws DAOException, SQLException {

        String query = "DELETE FROM usuario WHERE usumatricula = ?";
        try {
            conexao = Conexao.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setString(1, usuario.getMatricula());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            throw new DAOException("Erro ao excluir usuario:\n\n" + ex.getMessage(), ex);
        } finally {
            Conexao.closeConnection(conexao);
        }
    }

    public Usuario validaUsuario(String matricula, String senha) throws DAOException, SQLException {

        String query = "select * FROM usuario WHERE usumatricula = ?";
        try {
            conexao = Conexao.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setString(1, matricula);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.first()) {
                if (resultado.getString("ususenha").equals(senha)) {
                    Usuario usr = new Usuario();
                    usr.setMatricula(resultado.getString(1));
                    usr.setNome(resultado.getString(2));
                    usr.setSenha(resultado.getString(3));
                    usr.setTipo(resultado.getString(4));
                    return usr;
                }
                return null;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            throw new DAOException("Erro ao validar usuario:\n\n" + ex.getMessage(), ex);
        } finally {
            Conexao.closeConnection(conexao);
        }
    }

    // CONSULTA DE Salas
    public List<Usuario> pesquisarTodas(String nome) throws DAOException, SQLException {
        conexao = null;
        List<Usuario> lista = new ArrayList<>();
        String query = "SELECT * FROM usuario WHERE usunome LIKE ?";

        try {
            conexao = Conexao.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setString(1, "%" + nome + "%");
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                Usuario usuario = new Usuario();
                usuario.setMatricula(resultado.getString("usumatricula"));
                usuario.setNome(resultado.getString("usunome"));
                usuario.setSenha(resultado.getString("ususenha"));
                usuario.setTipo(resultado.getString("usuTipo"));
                lista.add(usuario);
            }
            stmt.close();
            return lista;
        } catch (SQLException ex) {
            throw new DAOException("Erro ao listar usuarios:\n\n" + ex.getMessage(), ex);
        } finally {
            Conexao.closeConnection(conexao);
        }
    }
}
