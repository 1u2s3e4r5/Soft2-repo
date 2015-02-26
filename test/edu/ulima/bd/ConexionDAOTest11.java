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
public class ConexionDAOTest11 {
    Usuario refUsuario1 = new Usuario(9999999,"pruebaRegNU","pruebaRNU","prueba",100,"Cliente");
    
    Articulo refArticulo1 = new Articulo(refUsuario1,"ArticuloSurreal","ArticuloSurreal","Directa",new SimpleDateFormat("yy/MM/dd-HH:mm:ss").format(Calendar.getInstance().getTime()),90.0f); //borrar este artículo
            
    Subasta refSubasta1 = new Subasta(refArticulo1, "Iniciado", 91.0f);
    
    Oferta refOferta1=new Oferta(refUsuario1, refSubasta1, 91.1f);
    
    
    
    public ConexionDAOTest11() {
            System.out.println("***2");
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

    

    /**
     * Test of retornarTodasLosSubastasDisponibles method, of class ConexionDAO.
     */
    @Test
    public void testRetornarTodasLosSubastasDisponibles() {
        System.out.println("retornarTodasLosSubastasDisponiblesTest");
        ConexionDAO instance = new ConexionDAO();
        //List<Subasta> expResult = null;
        List<Subasta> result = instance.retornarTodasLosSubastasDisponibles();
        //esto va buscar las subastas en estado 'Iniciado' o 'Por Iniciar' o 'No Iniciado'
        //en el anterior test nos aseguramos que exista al menos 
        assertNotNull(result);
        assertTrue(!result.isEmpty());
        
    }

    /**
     * Test of retornarSubastasPorDNI method, of class ConexionDAO.
     */
    @Test
    public void testRetornarSubastasPorDNI() {
        System.out.println("retornarSubastasPorDNITest");
        long dni =9999999 ;
        ConexionDAO instance = new ConexionDAO();
        
        List<Subasta> result = instance.retornarSubastasPorDNI(dni);
        //en un test anterior nos aseguramos que si exista una subasta que a la que enconntrar en la BD
        assertNotNull(result);
        assertTrue(!result.isEmpty());
       
    }

    /**
     * Test of retornarSubastasPorTipo method, of class ConexionDAO.
     */
    @Test
    public void testRetornarSubastasPorTipo() {
        System.out.println("retornarSubastasPorTipoTest");
        String tipo = "Directa";
        ConexionDAO instance = new ConexionDAO();
        
        List<Subasta> result = instance.retornarSubastasPorTipo(tipo);
        //antes nos aseguramos que existiera la referencia en BD
        assertNotNull(result);
        assertTrue(!result.isEmpty());
        
    }

    /**
     * Test of retornarSubastasPorEstado method, of class ConexionDAO.
     */
    @Test
    public void testRetornarSubastasPorEstado() {
        System.out.println("retornarSubastasPorEstadoTest");
        String tipo = "No Iniciado";
        ConexionDAO instance = new ConexionDAO();
        
        List<Subasta> result = instance.retornarSubastasPorEstado(tipo);
        //ya existía la referencia en BD
        assertNotNull(result);
        assertTrue(!result.isEmpty());
    }

    /**
     * Test of retornarSubastasPorPrecio method, of class ConexionDAO.
     */
    @Test
    public void testRetornarSubastasPorPrecio() {
        System.out.println("retornarSubastasPorPrecioTest");
        float menor = 89.9F;
        float mayor = 92.0F;
        ConexionDAO instance = new ConexionDAO();
        
        List<Subasta> result = instance.retornarSubastasPorPrecio(menor, mayor);
        //ya existía la referencia en BD
        assertNotNull(result);
        assertTrue(!result.isEmpty());
  
    }

    /**
     * Test of retornarSubastasPorFecha method, of class ConexionDAO.
     */
    
    /**
     * Test of retornarSubastasPorUser method, of class ConexionDAO.
     */
    

    /**
     * Test of retornarSubastasPorUserTodos method, of class ConexionDAO.
     */
    @Test
    public void testRetornarSubastasPorUserTodos() {
        System.out.println("retornarSubastasPorUserTodosTest");
        String user = "pruebaRNU";
        ConexionDAO instance = new ConexionDAO();
        //se podría ingresar otra subasta y hacer la prueba para que detecte 2 subastas
        List<Subasta> result = instance.retornarSubastasPorUserTodos(user);
        //ya existía la referencia en BD
        assertNotNull(result);
        assertTrue(!result.isEmpty());
        
    }

    /**
     * Test of actualizarPrecio method, of class ConexionDAO.
     */
    @Test
    public void testActualizarPrecio() {
        System.out.println("actualizarPrecioTest");
        
        ConexionDAO instance = new ConexionDAO();
        boolean expResult = true;
        long dni=9999999;
        List<Subasta> ll=instance.retornarSubastasPorDNI(dni);
        Subasta refSubast=null;
        
        for(Subasta refSubasta:ll){
            refSubast=refSubasta;
            System.out.println(refSubasta.getIdsubasta());
        }
        refSubast.setPrecioActual(91.3f);
        
        boolean result = instance.actualizarPrecio(refSubast);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of actualizarPrecioArticulo method, of class ConexionDAO.
     */
    @Test
    public void testActualizarPrecioArticulo() {
        System.out.println("actualizarPrecioArticuloTest");
        Articulo refArticulo = refArticulo1;
            refArticulo.setPrecioBase(90.5f); 
        ConexionDAO instance = new ConexionDAO();
        boolean expResult = true;
        boolean result = instance.actualizarPrecioArticulo(refArticulo);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of buscarUsuarioPorDNI method, of class ConexionDAO.
     */
    @Test
    public void testBuscarUsuarioPorDNI() {
        System.out.println("buscarUsuarioPorDNITest");
        long DNI = 9999999;
        ConexionDAO instance = new ConexionDAO();
        
        Usuario result = instance.buscarUsuarioPorDNI(DNI);
        assertNotNull(result);
    }

    /**
     * Test of buscarSubastaPorID method, of class ConexionDAO.
     */
    @Test
    public void testBuscarSubastaPorID() {
        System.out.println("buscarSubastaPorIDTest");
        
        ConexionDAO instance = new ConexionDAO();
        long dni=9999999;
        int id=0 ;
        for(Subasta ll:instance.retornarSubastasPorDNI(dni)){
            id=ll.getIdsubasta();
        }
        System.out.println(id);
        Subasta result = instance.buscarSubastaPorID(id);
        assertNotNull(result);
    }

    /**
     * Test of buscarArticuloPorID method, of class ConexionDAO.
     */
    @Test
    public void testBuscarArticuloPorID() {
        System.out.println("buscarArticuloPorIDTest");
        long dni=9999999;
        ConexionDAO instance = new ConexionDAO();
        int id = instance.retornarArticuloPorIDUsuario(dni).getIdarticulo();
        Articulo result = instance.buscarArticuloPorID(id);
        assertNotNull(result);
        
    }
    
}
