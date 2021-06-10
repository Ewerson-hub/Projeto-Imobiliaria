/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Bairro;

/**
 *
 * @author Acer
 */
public class BairroDAO {

    public void dataUpload(String name, double percent) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO bairro (name, percent) VALUES (?, ?) ");
            stmt.setString(1, name);
            stmt.setDouble(2, percent);
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Salver" + ex);
        } finally {
           ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    
    public void delete(Bairro b){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareCall("DELETE FROM bairro WHERE id = ?");
            stmt.setInt(1, b.getId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Exluir" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void update(Bairro b){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE bairro SET name = ?, percent = ? WHERE id = ?");
            stmt.setString(1, b.getName());
            stmt.setDouble(2, b.getPercent());
            stmt.setInt(3, b.getId());
            
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar"+ ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Bairro> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Bairro> vetor = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM bairro");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Bairro bairro = new Bairro();
                bairro.setId(rs.getInt("id"));
                bairro.setName(rs.getString("name"));
                bairro.setPercent(rs.getDouble("percent"));
                
                vetor.add(bairro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BairroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }   
        return vetor;   
    }
    public int arrayLength(){
        List x = read();
        return x.size();
    }

}
