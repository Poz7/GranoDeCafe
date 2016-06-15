/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author astrid
 */
public class ConexionSQLbase {
   public Connection ConexionSQL(){
       Connection con=null;
       try {
           Class.forName("com.mysql.jdbc.Driver");
           con=DriverManager.getConnection("jdbc:mysql://localhost/coffebean?user=root&password=123");
           
       } catch (ClassNotFoundException ex) {
       } catch (SQLException ex) {
           Logger.getLogger(ConexionSQLbase.class.getName()).log(Level.SEVERE, null, ex);
       }
       return con;
   }
}
