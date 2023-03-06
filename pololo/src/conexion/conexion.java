/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class conexion {
      public static final String url="jdbc:mysql://localhost:3306/muebles";
public static final String usuario="root";
public static final String clave="";
    private Connection cone=null;
    
    public  Connection getConnection(){
    
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cone=DriverManager.getConnection(url,usuario,clave);
            System.out.println("conecion exitosa");
            
        } catch (Exception e) {
            System.err.println(" error en la conexion "+e);
            JOptionPane.showMessageDialog(null, "error en la conexion");
        }
    return cone;
    } 
}
