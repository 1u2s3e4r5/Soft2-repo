
package edu.ulima.bd;

import java.sql.Connection;


public class MiShDwnHook extends Thread {
    
    public void run() {
     try {
     Connection con = DBConexion.getConnection();
     con.close();
     }catch (Exception e){
     e.printStackTrace();
     throw new RuntimeException(e);
     }
    }
    
}
