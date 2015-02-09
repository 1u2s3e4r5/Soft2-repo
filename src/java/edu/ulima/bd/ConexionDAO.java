
package edu.ulima.bd;

import edu.ulima.clases.Oferta;
import edu.ulima.clases.Articulo;
import edu.ulima.clases.Subasta;
import edu.ulima.clases.Usuario;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
                user = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),rs.getString(6));
                
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
        String sql="insert into Usuario (DNI, Nombre, Username, Password, Creditos, Tipo) values(?,?,?,?,?,?)";
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, refUsuario.getDNI());
            ps.setString(2,refUsuario.getNombre());
            ps.setString(3, refUsuario.getUsuario());
            ps.setString(4, refUsuario.getPassword() );
            ps.setInt(5, refUsuario.getCreditos());
            ps.setString(6,"Cliente");
            ps.executeUpdate();
            exito = true;
        } catch (SQLException ex) {
           ex.printStackTrace();
           return false;
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

     public boolean registrarNuevaPiezas(Articulo refArticulo, Part file) throws IOException {
        Connection con = null;
        boolean exito = false;
        PreparedStatement ps = null;
        String sql = "Insert into articulo (DNI, Nombre, Descripcion, Tipo, FechaInicio, Foto, PrecioBase) values(?,?,?,?,?,?,?)";
         
         InputStream fis = null;
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, refArticulo.getVendedor().getDNI());
            ps.setString(2, refArticulo.getNombre());
            ps.setString(3, refArticulo.getDescripcion());
            ps.setString(4, refArticulo.getTipo());
            ps.setString(5, refArticulo.getFechaInicio());
            ps.setFloat(7, refArticulo.getPrecioBase());
           
            
            
            fis = file.getInputStream();
            ps.setBinaryStream(6,fis);
            ps.executeUpdate();
            
            sql= "Select * from articulo where  FechaInicio = ? and DNI = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, refArticulo.getFechaInicio());
            ps.setLong(2, refArticulo.getVendedor().getDNI());
             ResultSet rs = ps.executeQuery();
            if(rs.next()){
                refArticulo = this.retornarArticulo(rs);
                
             }
            
            
            
            
            
            
            Subasta s = new Subasta();
            s.setArticulo(refArticulo);
            s.setEstado("No Iniciado");
            s.setPrecioActual(refArticulo.getPrecioBase());
            exito = this.registrarSubasta(s);
            
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
     
     public boolean registrarOferta(Oferta refOferta){
        Connection con = null;
        boolean exito = false;
        PreparedStatement ps = null;
        String sql = "Insert into oferta (Subasta, Comprador, Monto, Fecha, Mayor) values(?,?,?,?,?)";
        
        String timeStamp = new SimpleDateFormat("yy/MM/dd-HH:mm:ss").format(Calendar.getInstance().getTime());
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, refOferta.getComprador().getDNI());
            ps.setInt(2, refOferta.getSubasta().getIdsubasta());
            ps.setFloat(3, refOferta.getMonto());
            ps.setString(4, timeStamp);
            ps.setString(5, "True");
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
     
     public boolean registrarSubasta(Subasta refSubasta){
      Connection con = null;
        boolean exito = false;
        PreparedStatement ps = null;
        String sql = "Insert into subasta (idarticulo, Estado, PrecioActual) values(?,?,?)";
         
         
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,refSubasta.getArticulo().getIdarticulo());
            ps.setString(2,refSubasta.getEstado());
            ps.setFloat(3,refSubasta.getPrecioActual());
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
     
     public List<Subasta> retornarTodasLosSubastasDisponibles(){
        Connection con = null;
        boolean exito = false;
        PreparedStatement ps = null;
        List<Subasta> subastas = new ArrayList<>();
        ResultSet rs = null;
        String sql = "Select * FROM subasta where Estado = 'No Iniciado' or Estado = 'Iniciado'";
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Subasta s = this.retornarSubasta(rs);
                subastas.add(s);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                ps.close();
            
                rs.close();
                    con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return subastas;
    }
     
     public List<Subasta> retornarSubastasPorDNI(long dni){
        Connection con = null;
        boolean exito = false;
        PreparedStatement ps = null;
        List<Subasta> subastas = new ArrayList<>();
        ResultSet rs = null;
        String sql = "Select * FROM subasta join articulo on subasta.idarticulo = articulo.idarticulo where dni = ?";
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, dni);
            rs = ps.executeQuery();
            while (rs.next()){
                Subasta s = this.retornarSubasta(rs);
                subastas.add(s);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                ps.close();
            
                rs.close();
                    con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return subastas;
    }
      
     public List<Subasta> retornarSubastasPorTipo(String tipo){
        Connection con = null;
        boolean exito = false;
        PreparedStatement ps = null;
        List<Subasta> subastas = new ArrayList<>();
        ResultSet rs = null;
        String sql = "Select * FROM subasta join articulo on subasta.idarticulo = articulo.idarticulo where articulo.tipo = ? and (Estado = 'No Iniciado' or Estado = 'Iniciado')";
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, tipo);
            rs = ps.executeQuery();
            while (rs.next()){
                Subasta s = this.retornarSubasta(rs);
                subastas.add(s);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                ps.close();
            
                rs.close();
                    con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return subastas;
    }
      
     public List<Subasta> retornarSubastasPorPrecio(float menor, float mayor){
        Connection con = null;
        boolean exito = false;
        PreparedStatement ps = null;
        List<Subasta> subastas = new ArrayList<>();
        ResultSet rs = null;
        String sql = "Select * FROM subasta where subasta.PrecioActual between  ? and ? and (Estado = 'No Iniciado' or Estado = 'Iniciado')";
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setFloat(1,menor);
            ps.setFloat(2,mayor);
            rs = ps.executeQuery();
            while (rs.next()){
                Subasta s = this.retornarSubasta(rs);
                subastas.add(s);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                ps.close();
            
                rs.close();
                    con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return subastas;
    } 
     
     public List<Subasta> retornarSubastasPorFecha(String fechaini, String fechafin){
        Connection con = null;
        boolean exito = false;
        PreparedStatement ps = null;
        List<Subasta> subastas = new ArrayList<>();
        ResultSet rs = null;
        String sql = "Select * FROM subasta where subasta.FechaInicio between  ? and ? and (Estado = 'No Iniciado' or Estado = 'Iniciado')";
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,fechaini);
            ps.setString(1,fechafin);
            
            rs = ps.executeQuery();
            while (rs.next()){
                Subasta s = this.retornarSubasta(rs);
                subastas.add(s);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                ps.close();
            
                rs.close();
                    con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return subastas;
    } 
     
     public boolean actualizarPrecio(Subasta refSubasta){
     Connection con = null;
        boolean exito = false;
        PreparedStatement ps = null;
        String sql = "Update subasta set PrecioActual= ? where idsubasta = ?";
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setFloat(1,refSubasta.getPrecioActual());
            ps.setInt(2,refSubasta.getIdsubasta());
            ps.executeUpdate();
            exito = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                
                    con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return exito;
     
     }
     
     public Usuario buscarUsuarioPorDNI(long DNI){
     
        Connection con = null;
        boolean exito = false;
        PreparedStatement ps = null;
        Usuario u = null;
        ResultSet rs = null;
        String sql = "Select * from usuario where DNI = ?";
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, DNI);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("DNI");
                u = this.retornarUsuario(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                ps.close();
            
                rs.close();
                    con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
     return u;
     }
     
     public Subasta buscarSubastaPorID(int id){
     
        Connection con = null;
        boolean exito = false;
        PreparedStatement ps = null;
        Subasta s = null;
        ResultSet rs = null;
        String sql = "Select * from subasta where idsubasta = ?";
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                
                s = this.retornarSubasta(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                ps.close();
            
                rs.close();
                    con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
     return s;
     }
     
     public Articulo buscarArticuloPorID(int id){
        Connection con = null;
        boolean exito = false;
        PreparedStatement ps = null;
        Articulo a = null;
        ResultSet rs = null;
        String sql = "Select * from articulo where idarticulo = ?";
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                
                a = this.retornarArticulo(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                ps.close();
            
                rs.close();
                    con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
     return a;
     
     
     
     
     
     
     
     }
     
     public boolean iniciarSubasta(Subasta refSubasta){
        Connection con = null;
        boolean exito = false;
        PreparedStatement ps = null;
        String sql = "Update subasta set Estado = 'Iniciado' where idsubasta = ?";
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setInt(1,refSubasta.getIdsubasta());
            ps.executeUpdate();
            exito = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                
                    con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return exito;
     
     }
     
    public boolean terminarSubasta(Subasta refSubasta){
        Connection con = null;
        boolean exito = false;
        PreparedStatement ps = null;
        String sql = "Update subasta set Estado = 'Terminado' where idsubasta = ?";
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setInt(1,refSubasta.getIdsubasta());
            ps.executeUpdate();
            exito = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                
                    con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return exito;
     
     }    
    
     public boolean finalizarSubasta(Subasta refSubasta){
        Connection con = null;
        boolean exito = false;
        PreparedStatement ps = null;
        String sql = "Update subasta set Estado = 'Finalizado' where idsubasta = ?";
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setInt(1,refSubasta.getIdsubasta());
            ps.executeUpdate();
            exito = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                
                    con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return exito;
     
     }    
    
     public boolean actualizarCreditos(Usuario refUsuario){
        Connection con = null;
        boolean exito = false;
        PreparedStatement ps = null;
        String sql = "Update subasta set Creditos = ? where DNI = ?";
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setInt(1,refUsuario.getCreditos());
            ps.setLong(1,refUsuario.getDNI());
            ps.executeUpdate();
            exito = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                
                    con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return exito;
     
     }    
    
     public boolean actualizarOferta(int id){
        Connection con = null;
        boolean exito = false;
        PreparedStatement ps = null;
        String sql = "Update oferta set mayor='false' where idoferta = ?";
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
            exito = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                
                    con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return exito;
     
     
     }
     
     public List<Oferta> retornarOfertasporSubasta(int id){
        Connection con = null;
        
        PreparedStatement ps = null;
        List<Oferta> ofertas = new ArrayList<>();
        ResultSet rs = null;
        String sql = "Select * FROM oferta where idsubasta = ?";
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()){
                Oferta o = this.retornarOferta(rs);
                ofertas.add(o);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                ps.close();
            
                rs.close();
                    con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return ofertas;
     
     }
     
     
     
     
     private Subasta retornarSubasta (ResultSet rs) throws SQLException{
     
         
                Subasta s = new Subasta();
                s.setIdsubasta(rs.getInt("idsubasta"));
                s.setArticulo(this.buscarArticuloPorID(rs.getInt("idarticulo")));
                s.setEstado(rs.getString("Estado"));
                s.setFechaInicio(rs.getString("FechaInicio"));
                s.setFechaFin(rs.getString("FechaFin"));
                s.setPrecioActual(rs.getFloat("PrecioActual"));
                
                return s;
     }
     
     private Oferta retornarOferta (ResultSet rs) throws SQLException{
     
         
                Oferta o = new Oferta();
                o.setIdoferta(rs.getInt("idoferta"));
                o.setSubasta(this.buscarSubastaPorID(rs.getInt("idsubasta")));
                o.setComprador(this.buscarUsuarioPorDNI(rs.getLong("DNI")));
                o.setMonto(rs.getFloat("monto"));
                o.setMayor(rs.getString("mayor"));
                o.setFecha(rs.getString("fecha"));
                
                return o;
     }
     
     private Articulo retornarArticulo (ResultSet rs) throws SQLException{
     
         
                Articulo a = new Articulo();
                a.setIdarticulo(rs.getInt("idarticulo"));
                a.setVendedor(this.buscarUsuarioPorDNI(rs.getLong("DNI")));
                
                a.setNombre(rs.getString("Nombre"));
                a.setDescripcion(rs.getString("Descripcion"));
                a.setTipo(rs.getString("Tipo"));
                a.setPrecioBase(rs.getFloat("PrecioBase"));
                a.setFechaInicio(rs.getString("FechaInicio"));
                a.setFechaFin(rs.getString("FechaFin"));
               
                Blob blob = rs.getBlob("Foto");
                 byte[] data = blob.getBytes(1, (int)blob.length());
               
                a.setFoto(data);
                //p.setImagen(rs.getBytes("Foto"));
                return a;
     }
     
     private Usuario retornarUsuario(ResultSet rs) throws SQLException{
                Usuario user = new Usuario();
                user.setDNI(rs.getLong("DNI"));
                user.setNombre(rs.getString("Nombre"));
                user.setUsuario(rs.getString("Username"));
                user.setPassword(rs.getString("Password"));
                user.setCreditos(rs.getInt("Creditos"));
                user.setUsuario(rs.getString("Tipo"));
                return user;
                
             
     
     }
}
