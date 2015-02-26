/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulima.bd;

import edu.ulima.clases.Articulo;
import edu.ulima.clases.Cobro;
import edu.ulima.clases.IOferta;
import edu.ulima.clases.Oferta;
import edu.ulima.clases.Pago;
import edu.ulima.clases.Premio;
import edu.ulima.clases.Subasta;
import edu.ulima.clases.Usuario;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.Part;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Useer3
 */
public class ConexionDAOTest1111 {
    Usuario refUsuario1 = new Usuario(9999999,"pruebaRegNU","pruebaRNU","prueba",100,"Cliente");
    
    Articulo refArticulo1 = new Articulo(refUsuario1,"ArticuloSurreal","ArticuloSurreal","Directa",new SimpleDateFormat("yy/MM/dd-HH:mm:ss").format(Calendar.getInstance().getTime()),90.0f); //borrar este artículo
            
    Subasta refSubasta1 = new Subasta(refArticulo1, "Iniciado", 91.0f);
    
    Oferta refOferta1=new Oferta(refUsuario1, refSubasta1, 91.1f);
    
    
    
    public ConexionDAOTest1111() {
            System.out.println("***4");
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {

        
        
    }

    @Test
    public void finalizar(){
        //borrar el usuario, el artículo, la subasta
        
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        boolean exito = false;
        //String sql="insert into Usuario (DNI, Nombre, Username, Password, Creditos, Tipo) values(?,?,?,?,?,?)";
        String sql="delete from Usuario where dni=?";
        String sql2="delete from Articulo where dni=?";
        String sql3="delete from Subasta where idarticulo=(select idarticulo from articulo where dni=?)";
        try {
            con = DBConexion.getConnection();
            
            ps3 = con.prepareStatement(sql3);
            ps3.setInt(1, 9999999);
            ps3.executeUpdate();
            
            ps2 = con.prepareStatement(sql2);
            ps2.setInt(1, 9999999);
            ps2.executeUpdate();
            
            ps = con.prepareStatement(sql);
            ps.setInt(1, 9999999);
            ps.executeUpdate();
            
            exito = true;
            
            
        } catch (SQLException ex) {
           ex.printStackTrace();
           exito = false;
            System.out.println("tearnDown"+" murió aquí");
        } finally {
            try {
                ps.close();
                ps2.close();
                ps3.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        
    }
    
    
}
