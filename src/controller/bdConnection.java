/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import sun.util.logging.PlatformLogger;

/**
 *
 * @author reisaolucas
 */
public class bdConnection {
    
    private Connection connection = null;
    
    public bdConnection() throws SQLException, ClassNotFoundException{

        String usuario = "postgres";
        String senha = "postgres";
        String urlconexao = "jdbc:postgresql://localhost:5433/bdfarmacia";
        Class.forName("org.postgresql.Driver");
        this.connection = DriverManager.getConnection(urlconexao, usuario, senha);
        this.connection.setAutoCommit(false);
    }
    
    public Connection getConnection(){
        return this.connection;
    }
    
    public void close(){
        if(this.connection!=null){
            try{
                this.connection.close();
            } catch (SQLException ex){
                Logger.getLogger(bdConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void commit() throws SQLException{
        this.connection.commit();
        this.close();
    }
    
    public void rollback(){
        if (this.connection!=null){
            try{
                this.connection.rollback();
            } catch (SQLException ex){
                Logger.getLogger(bdConnection.class.getName()).log(Level.SEVERE,null,ex);
            } finally {
                this.close();
            }
        }
    }
    
    
}
