package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;

/**
 *
 * @author Ewerson Lucas
 */
public class UsuarioDAO {

    public boolean checkLogin(String login, String pass) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        boolean check = false;
        try {

            stmt = con.prepareStatement("SELECT * FROM usuario WHERE login = ? AND pass = ?");
            stmt.setString(1, login);
            stmt.setString(2, pass);
            
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                check = true;
            }
            
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro " +ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return check;
    }

}
