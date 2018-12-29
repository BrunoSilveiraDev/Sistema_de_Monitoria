package MVC;

import exception.DAOException;
import java.sql.SQLException;
import model.Usuario;
import view.MainView;
import view.ViewLogin;

public class Main {
    
    public static void main(String[] args) throws DAOException, SQLException {
        boolean login = true;
        if (login) {
            //("000000001","Andrea Pereira Mendonca","coo",)
            //(2015118970061)(2017118970165)(2017118970181)
            //(2017118970034)
            //(2017118970001)(2017118970002)
            ViewLogin viewLogin = new ViewLogin();
            viewLogin.setLocationRelativeTo(null);
            viewLogin.setVisible(true);
        } else {
            Usuario usuario = new Usuario();
            usuario.setMatricula("0000000000001");
            usuario.setNome("Teste Coordenador");
            MainView mainView = new MainView(usuario);
            mainView.setLocationRelativeTo(null);
            mainView.setVisible(true);
        }
    }
    
}
