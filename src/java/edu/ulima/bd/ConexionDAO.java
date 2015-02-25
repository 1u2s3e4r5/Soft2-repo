
package edu.ulima.bd;

import edu.ulima.clases.Oferta;
import edu.ulima.clases.Articulo;
import edu.ulima.clases.Cobro;
import edu.ulima.clases.IOferta;
import edu.ulima.clases.Pago;
import edu.ulima.clases.Premio;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
        String sql = "Insert into oferta (idSubasta, dni, Monto, Fecha, Mayor) values(?,?,?,?,?)";
        
        String timeStamp = new SimpleDateFormat("yy/MM/dd-HH:mm:ss").format(Calendar.getInstance().getTime());
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(2, refOferta.getComprador().getDNI());
            ps.setInt(1, refOferta.getSubasta().getIdsubasta());
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
        String sql = "Select * FROM subasta where Estado = 'No Iniciado' or Estado = 'Iniciado' or Estado = 'Por Iniciar'";
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
        String sql = "Select * FROM subasta join articulo on subasta.idarticulo = articulo.idarticulo where articulo.tipo = ? and (Estado = 'No Iniciado' or Estado = 'Iniciado' or Estado = 'Por Iniciar')";
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
      
     public List<Subasta> retornarSubastasPorEstado(String tipo){
        Connection con = null;
        
        PreparedStatement ps = null;
        List<Subasta> subastas = new ArrayList<>();
        ResultSet rs = null;
        String sql = "Select * FROM subasta where subasta.estado= ? ";
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
        String sql = "Select * FROM subasta where subasta.PrecioActual between  ? and ? and (Estado = 'No Iniciado' or Estado = 'Iniciado' or Estado = 'Por Iniciar')";
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
        String sql = "Select * FROM subasta where subasta.FechaInicio between  ? and ? and (Estado = 'No Iniciado' or Estado = 'Iniciado' or Estado = 'Por Iniciar')";
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,fechaini);
            ps.setString(2,fechafin);
            
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
     
     public List<Subasta> retornarSubastasPorUser(String user){
        Connection con = null;
        boolean exito = false;
        PreparedStatement ps = null;
        List<Subasta> subastas = new ArrayList<>();
        ResultSet rs = null;
        String sql = "Select s.idsubasta,s.idarticulo,s.estado,s.fechainicio,s.fechafin,s.precioactual FROM subasta s join articulo on s.idarticulo=articulo.idarticulo join usuario on articulo.dni = usuario.dni where username = ? and (Estado = 'No Iniciado' or Estado = 'Iniciado' or Estado = 'Por Iniciar')";
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,user);
           
            
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
     
     
      public List<Subasta> retornarSubastasPorUserTodos(String user){
        Connection con = null;
        boolean exito = false;
        PreparedStatement ps = null;
        List<Subasta> subastas = new ArrayList<>();
        ResultSet rs = null;
        String sql = "Select s.idsubasta,s.idarticulo,s.estado,s.fechainicio,s.fechafin,s.precioactual,s.tiempo FROM subasta s join articulo on s.idarticulo=articulo.idarticulo join usuario on articulo.dni = usuario.dni where username = ?";
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,user);
           
            
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
       // System.out.println(refSubasta.getPrecioActual() + " 3");
       // System.out.println(monto + " x");
        String sql = "Update subasta set PrecioActual= ? where idsubasta = ?";
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setFloat(1,refSubasta.getPrecioActual());
            ps.setInt(2,refSubasta.getIdsubasta());
          //  System.out.println(ps.toString());
            ps.executeUpdate();
            exito = true;
          //  System.out.println(refSubasta.getPrecioActual() + " 4");
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
     
     public boolean actualizarPrecioArticulo(Articulo refArticulo){
     Connection con = null;
        boolean exito = false;
        PreparedStatement ps = null;
       // System.out.println(refSubasta.getPrecioActual() + " 3");
       // System.out.println(monto + " x");
        String sql = "Update articulo set PrecioBase= ? where idarticulo = ?";
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setFloat(1,refArticulo.getPrecioBase());
            ps.setInt(2,refArticulo.getIdarticulo());
          //  System.out.println(ps.toString());
            ps.executeUpdate();
            exito = true;
          //  System.out.println(refSubasta.getPrecioActual() + " 4");
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
            ps.setInt(1, id);
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
            ps.setInt(1, id);
          
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
        String sql = "Update subasta set Estado = 'Iniciado'  where idsubasta = ?";
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
     
     public boolean porIniciarSubasta(Subasta refSubasta){
        Connection con = null;
        boolean exito = false;
        PreparedStatement ps = null;
        String sql = "Update subasta set Estado = 'Por Iniciar' , FechaInicio = ?, tiempo = ? where idsubasta = ?";
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, refSubasta.getFechaInicio());
            ps.setInt(3,refSubasta.getIdsubasta());
            ps.setInt(2, 300);
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
     
    public boolean concluirSubasta(Subasta refSubasta){
        Connection con = null;
        boolean exito = false;
        PreparedStatement ps = null;
        String sql = "Update subasta set Estado = 'Concluido' where idsubasta = ?";
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
        String sql = "Update subasta set Estado = 'Finalizado', FechaFin = ? where idsubasta = ?";
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, refSubasta.getFechaFin());
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
    
     
      public boolean resubastarSubasta(Subasta refSubasta){
        Connection con = null;
        boolean exito = false;
        PreparedStatement ps = null;
        String sql = "Update subasta set Estado = 'Resubastado' where idsubasta = ?";
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
        String sql = "Update usuario set Creditos = ? where DNI = ?";
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setInt(1,refUsuario.getCreditos());
            ps.setLong(2,refUsuario.getDNI());
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
     
     public List<IOferta> retornarOfertasporSubasta(int id){
        Connection con = null;
        
        PreparedStatement ps = null;
        List<IOferta> ofertas = new ArrayList<>();
        ResultSet rs = null;
        String sql = "Select * FROM oferta where idsubasta = ? order by monto desc";
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
     
     
     public void revisarSubastasPorTerminar() throws ParseException{
     List<Subasta> lista = new ArrayList<>();
     lista = this.retornarSubastasPorEstado("Iniciado");
     for (Subasta s : lista){
     int tiempo = s.getTiempo();
     String fecha = s.getFechaInicio();
     SimpleDateFormat fmt = new SimpleDateFormat("yy/MM/dd-HH:mm:ss");
     Date ini =  fmt.parse(fecha);
      String timeStamp = new SimpleDateFormat("yy/MM/dd-HH:mm:ss").format(Calendar.getInstance().getTime());
     Date ahora = fmt.parse(timeStamp);
     
     Calendar c = Calendar.getInstance();
     c.setTime(ini);
     c.add(Calendar.SECOND, tiempo);
     
     Date fin = c.getTime();
     
     if (ahora.compareTo(fin) > 0){
     String fechafin = fmt.format(fin);
     s.setFechaFin(fechafin);
     this.finalizarSubasta(s);
     }
     
     }
     
     }
     
      public void revisarSubastasPorIniciar() throws ParseException{
     List<Subasta> lista = new ArrayList<>();
     lista = this.retornarSubastasPorEstado("Por Iniciar");
     for (Subasta s : lista){
     int tiempo = s.getTiempo();
     String fecha = s.getFechaInicio();
     SimpleDateFormat fmt = new SimpleDateFormat("yy/MM/dd-HH:mm:ss");
     Date ini =  fmt.parse(fecha);
      String timeStamp = new SimpleDateFormat("yy/MM/dd-HH:mm:ss").format(Calendar.getInstance().getTime());
     Date ahora = fmt.parse(timeStamp);
     
    
     
     if (ahora.compareTo(ini) > 0){
     this.iniciarSubasta(s);
     }
     
     }
     
     }
     
     public boolean agregarTiempoPorCentimos(Subasta refSubasta){
     
      Connection con = null;
        boolean exito = false;
        PreparedStatement ps = null;
        String sql = "Update subasta set tiempo = ? where idsubasta = ?";
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            int tiempo = refSubasta.getTiempo();
            tiempo +=60;
            refSubasta.setTiempo(60);
            ps.setInt(1, tiempo);
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
     
     public List<Premio> retornarPremiosPorUsuario(int idUsuario){
     
      Connection con = null;
        boolean exito = false;
        PreparedStatement ps = null;
        List<Premio> premios = new ArrayList<>();
        String sql = "select * from premio where usuario = ?";
        ResultSet rs = null;
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, idUsuario);
           
            rs =ps.executeQuery();
            while (rs.next()){
                Premio p = this.retornarPremio(rs);
                premios.add(p);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                
                    con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return premios;
     
     
     
     }
   
     public boolean insertarPremio(Premio refPremio){
        Connection con = null;
        boolean exito = false;
        PreparedStatement ps = null;
        String sql = "Insert into premio (usuario, tipo, cantidad, subasta) values(?,?,?,?)";
        
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, refPremio.getUsuario().getDNI());
            ps.setString(2, refPremio.getTipo());
            ps.setInt(3, refPremio.getCantidad());
            ps.setInt(4, refPremio.getSubasta().getIdsubasta());
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
     
     public List<Pago> retornarPagosPorUsuario(int idUsuario){
     
      Connection con = null;
        boolean exito = false;
        PreparedStatement ps = null;
        List<Pago> pagos = new ArrayList<>();
        String sql = "select * from pago where vendedor = ?";
        ResultSet rs = null;
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, idUsuario);
           
            rs =ps.executeQuery();
            while (rs.next()){
                Pago p = this.retornarPago(rs);
                pagos.add(p);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                
                    con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return pagos;
     
     
     
     }
     
     
     public boolean insertarPago(Pago refPago){
        Connection con = null;
        boolean exito = false;
        PreparedStatement ps = null;
        String sql = "Insert into pago(vendedor, subasta,monto, fecha) values(?,?,?,?)";
        
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, refPago.getVendedor().getDNI());
            ps.setInt(2, refPago.getSubasta().getIdsubasta());
            ps.setFloat(3, refPago.getMonto());
            ps.setString(4, refPago.getFecha());
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
     
     public List<Cobro> retornarCobrosPorUsuario(int idUsuario){
     
      Connection con = null;
        boolean exito = false;
        PreparedStatement ps = null;
        List<Cobro> cobros = new ArrayList<>();
        String sql = "select * from cobro where comprador= ?";
        ResultSet rs = null;
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, idUsuario);
           
            rs =ps.executeQuery();
            while (rs.next()){
                Cobro c = this.retornarCobro(rs);
                cobros.add(c);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                
                    con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return cobros;
     
     
     
     }
     
     public boolean insertarCobro(Cobro refCobro){
        Connection con = null;
        boolean exito = false;
        PreparedStatement ps = null;
        String sql = "Insert into cobro(comprador, subasta,monto, fecha) values(?,?,?,?)";
        
        try {
            con = DBConexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, refCobro.getComprador().getDNI());
            ps.setInt(2, refCobro.getSubasta().getIdsubasta());
            ps.setFloat(3, refCobro.getMonto());
            ps.setString(4, refCobro.getFecha());
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
     
     private Subasta retornarSubasta (ResultSet rs) throws SQLException{
     
         
                Subasta s = new Subasta();
                s.setIdsubasta(rs.getInt("idsubasta"));
                s.setArticulo(this.buscarArticuloPorID(rs.getInt("idarticulo")));
                s.setEstado(rs.getString("Estado"));
                s.setFechaInicio(rs.getString("FechaInicio"));
                s.setFechaFin(rs.getString("FechaFin"));
                s.setPrecioActual(rs.getFloat("PrecioActual"));
                s.setTiempo(rs.getInt("tiempo"));
                //s.setOfertas(this.retornarOfertasporSubasta(s.getIdsubasta()));
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
                user.setTipo(rs.getString("Tipo"));
                return user;
                
             
     
     }
     
     private Premio retornarPremio(ResultSet rs) throws SQLException{
                Premio premio = new Premio();
                premio.setIdpremio(rs.getInt("idpremio"));
                premio.setUsuario(this.buscarUsuarioPorDNI(rs.getInt("usuario")));
                premio.setTipo(rs.getString("tipo"));
                premio.setCantidad(rs.getInt("cantidad"));
                premio.setSubasta(this.buscarSubastaPorID(rs.getInt("subasta")));
              
                return premio;
           }
     
      private Pago retornarPago(ResultSet rs) throws SQLException{
                Pago pago = new Pago();
                pago.setIdPago(rs.getInt("idpago"));
                pago.setVendedor(this.buscarUsuarioPorDNI(rs.getInt("vendedor")));
                pago.setSubasta(this.buscarSubastaPorID(rs.getInt("subasta")));
                pago.setFecha(rs.getString("fecha"));
                pago.setMonto(rs.getFloat("monto"));
              
                return pago;
           }
      
      private Cobro retornarCobro(ResultSet rs) throws SQLException{
                Cobro cobro = new Cobro();
                cobro.setIdCobro(rs.getInt("idcobro"));
                cobro.setComprador(this.buscarUsuarioPorDNI(rs.getInt("comprador")));
                cobro.setSubasta(this.buscarSubastaPorID(rs.getInt("subasta")));
                cobro.setFecha(rs.getString("fecha"));
                cobro.setMonto(rs.getFloat("monto"));
              
                return cobro;
           }
}
