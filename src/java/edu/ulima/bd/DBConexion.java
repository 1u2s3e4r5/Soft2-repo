
package edu.ulima.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConexion {
  //  private static Connection con = null;
    
    
    
    public static Connection getConnection() {
         String url = "jdbc:mysql://localhost:3306/software2?user=root&password=root";

        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {
        
        }
        return con;
    }
        
    }
    
    


