
package edu.ulima.bd;

import edu.ulima.clases.Subasta;
import edu.ulima.clases.Usuario;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.Part;


public class ConexionDAO {
     
     public Usuario validarUsuario(String username, String password){
        boolean existe = false;
        Usuario user = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql="Select * from usuario where  Username = ? and Password = ?";
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if(rs.next()){
                existe = true;
                user = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
                
             }
           
        } catch (SQLException ex) {
           ex.printStackTrace();
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
           
        }
        
        return user;
    
 }
    
     
     public boolean registrarNuevoUsuario(Usuario refUsuario) {
        Connection con = null;
        PreparedStatement ps = null;
        boolean exito = false;
        String sql="insert into Usuario (DNI, Nombre, Username, Password, Creditos) values(?,?,?,?,?)";
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, refUsuario.getDNI());
            ps.setString(2,refUsuario.getNombre());
            ps.setString(3, refUsuario.getUsuario());
            ps.setString(4, refUsuario.getPassword() );
            ps.setInt(5, refUsuario.getCreditos());
            ps.executeUpdate();
            exito = true;
        } catch (SQLException ex) {
           ex.printStackTrace();
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return exito;
    }

     
     public boolean registrarNuevaPiezas(Subasta refSubasta, Part file) throws IOException {
        Connection con = null;
        boolean exito = false;
        PreparedStatement ps = null;
        String sql = "Insert into subasta (DNI, Nombre, Descripcion, OfertaMayor, PrecioBase, Foto, PrecioActual) values(?,?,?,?,?,?,?)";
        InputStream fis = null;
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, refSubasta.getVendedor().getDNI());
            ps.setString(2, refSubasta.getNombre());
            ps.setString(3, refSubasta.getDescripcion());
            ps.setInt(4, 0);
            ps.setFloat(5, refSubasta.getPrecioBase());
            ps.setFloat(7, refSubasta.getPrecioActual());
           
            fis = file.getInputStream();
            ps.setBinaryStream(6,fis);
            ps.executeUpdate();
            exito = true;
        } catch (SQLException ex) {
           ex.printStackTrace();
        } catch (FileNotFoundException ex) {
           ex.printStackTrace();
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return exito;
    }
}
