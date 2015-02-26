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
public class ConexionDAOTest111 {
    Usuario refUsuario1 = new Usuario(9999999,"pruebaRegNU","pruebaRNU","prueba",100,"Cliente");
    
    Articulo refArticulo1 = new Articulo(refUsuario1,"ArticuloSurreal","ArticuloSurreal","Directa",new SimpleDateFormat("yy/MM/dd-HH:mm:ss").format(Calendar.getInstance().getTime()),90.0f); //borrar este artículo
            
    Subasta refSubasta1 = new Subasta(refArticulo1, "Iniciado", 91.0f);
    
    Oferta refOferta1=new Oferta(refUsuario1, refSubasta1, 91.1f);
    
    
    
    public ConexionDAOTest111() {
            System.out.println("***3");
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
        //borrar el usuario, el artículo, la oferta
    }

    /**
     * Test of validarUsuario method, of class ConexionDAO.
     */
    @Test
    public void testValidarUsuario() {
        System.out.println("validarUsuarioTest");
        String username = "andriu";
        String password = "andriu";
        ConexionDAO instance = new ConexionDAO();
        
        //devuelve un usuario con los datos cargados o un null
        Usuario result = instance.validarUsuario(username, password);
        assertNotNull(result);
        
    }

    /**
     * Test of registrarNuevoUsuario method, of class ConexionDAO.
     */
    @Test
    public void testRegistrarNuevoUsuario() {
        System.out.println("registrarNuevoUsuarioTest");
        Usuario refUsuario = refUsuario1;
        ConexionDAO instance = new ConexionDAO();
        boolean expResult = true;
        //luego de hacer el registro se espera que devuelva un boolean con valor 'true'
        boolean result = instance.registrarNuevoUsuario(refUsuario);
        assertEquals(expResult, result);
        
        
        //podría servir como para el buscarUsuarioPorDNITest
        Usuario testing=instance.buscarUsuarioPorDNI(9999999);
        assertEquals(refUsuario.getDNI(), testing.getDNI());
        assertEquals(refUsuario.getNombre(), testing.getNombre());
        assertEquals(refUsuario.getUsuario(), testing.getUsuario());
        assertEquals(refUsuario.getPassword(), testing.getPassword());
        assertEquals(refUsuario.getCreditos(), testing.getCreditos());
        assertEquals(refUsuario.getTipo(), testing.getTipo());
    }


}
