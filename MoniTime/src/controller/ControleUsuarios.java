/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.MonitorDAO;
import dao.UsuarioDAO;
import exception.DAOException;
import java.sql.SQLException;
import java.util.List;
import model.Monitor;
import model.Usuario;
import view.ViewLogin;

/**
 *
 * @author snow
 */
public class ControleUsuarios {

    ViewLogin viewLogin;
    UsuarioDAO usudao = new UsuarioDAO();
    MonitorDAO mondao = new MonitorDAO();

    public void setView(ViewLogin viewLogin) {
        this.viewLogin = viewLogin;
    }

    public void addUsuario(Usuario usuario) throws DAOException, SQLException {
        usudao.addUsuario(usuario);
    }
    
    public void delUsuario(Usuario usuario) throws DAOException, SQLException {
        usudao.delUsuario(usuario);
    }
    
    public Usuario validaUsuario(String matricula,String senha) throws DAOException, SQLException {
       return usudao.validaUsuario(matricula,senha);
    }
    
    public Monitor getMonitor(Usuario u) throws DAOException, SQLException {
        return mondao.getMonitor(u);
    }
    
    public boolean remMonitor(Monitor u) throws DAOException, SQLException {
        return mondao.remMonitor(u);
    }

    public List<Usuario> getAllUsuarios() throws DAOException, SQLException {
        return usudao.pesquisarTodas("_");
    }
}
