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
public class ConexionDAOTest {
    Usuario refUsuario1 = new Usuario(9999999,"pruebaRegNU","pruebaRNU","prueba",100,"Cliente");
    
    Articulo refArticulo1 = new Articulo(refUsuario1,"ArticuloSurreal","ArticuloSurreal","Directa",new SimpleDateFormat("yy/MM/dd-HH:mm:ss").format(Calendar.getInstance().getTime()),90.0f); //borrar este artículo
            
    Subasta refSubasta1 = new Subasta(refArticulo1, "Iniciado", 91.0f);
    
    Oferta refOferta1=new Oferta(refUsuario1, refSubasta1, 91.1f);
    
    
    
    public ConexionDAOTest() {
            
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

    /**
     * Test of registrarNuevaPiezas method, of class ConexionDAO.
     */
    @Test
    public void testRegistrarNuevaPiezas() throws Exception {
        System.out.println("registrarNuevaPiezasTest");
        
            Articulo refArticulo = refArticulo1;
            
            //
            URL url = new URL("https://orilladebutaca.files.wordpress.com/2011/06/po_baby_by_dolph67-d3i0f13.jpg");
            URLConnection connectionC = url.openConnection();
            Part partesita=new PseudoPart(connectionC.getInputStream());
            //
        
        ConexionDAO instance = new ConexionDAO();
        boolean expResult = true;
        boolean result = instance.registrarNuevaPiezas(refArticulo, partesita);
        assertEquals(expResult, result);

    }

    /**
     * Test of registrarOferta method, of class ConexionDAO.
     */
    @Test
    public void testRegistrarOferta() {
        System.out.println("registrarOfertaTest");
        Oferta refOferta = refOferta1;
        ConexionDAO instance = new ConexionDAO();
        boolean expResult = true;
        //se espera que devuelva true
        boolean result = instance.registrarOferta(refOferta);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of registrarSubasta method, of class ConexionDAO.
     */
    @Test
    public void testRegistrarSubasta() {
        System.out.println("registrarSubastaTest");
        Subasta refSubasta = refSubasta1;
        
        ConexionDAO instance = new ConexionDAO();
        long dni=9999999;
        refArticulo1.setIdarticulo(instance.retornarArticuloPorIDUsuario(dni).getIdarticulo());
        boolean expResult = true;
        boolean result = instance.registrarSubasta(refSubasta);
        assertEquals(expResult, result);
        
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
        String tipo = "Iniciado";
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
    @Test
    public void testRetornarSubastasPorUser() {
        System.out.println("retornarSubastasPorUserTest");
        String user = "pruebaRNU";
        ConexionDAO instance = new ConexionDAO();
        
        List<Subasta> result = instance.retornarSubastasPorUser(user);
        //ya existía la referencia en BD
        assertNotNull(result);
        assertTrue(!result.isEmpty());
        
    }

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
        int id = refSubasta1.getArticulo().getIdarticulo();
        ConexionDAO instance = new ConexionDAO();
        
        Subasta result = instance.buscarSubastaPorID(id);
        assertNotNull(result);
    }

    /**
     * Test of buscarArticuloPorID method, of class ConexionDAO.
     */
    @Test
    public void testBuscarArticuloPorID() {
        System.out.println("buscarArticuloPorIDTest");
        int id = refArticulo1.getIdarticulo();
        ConexionDAO instance = new ConexionDAO();
        
        Articulo result = instance.buscarArticuloPorID(id);
        assertNotNull(result);
        
    }

    /**
     * Test of iniciarSubasta method, of class ConexionDAO.
     */
    @Test
    public void testIniciarSubasta() {
        System.out.println("iniciarSubasta");
        Subasta refSubasta = null;
        ConexionDAO instance = new ConexionDAO();
        boolean expResult = false;
        boolean result = instance.iniciarSubasta(refSubasta);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of porIniciarSubasta method, of class ConexionDAO.
     */
    @Test
    public void testPorIniciarSubasta() {
        System.out.println("porIniciarSubasta");
        Subasta refSubasta = null;
        ConexionDAO instance = new ConexionDAO();
        boolean expResult = false;
        boolean result = instance.porIniciarSubasta(refSubasta);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of concluirSubasta method, of class ConexionDAO.
     */
    @Test
    public void testConcluirSubasta() {
        System.out.println("concluirSubasta");
        Subasta refSubasta = null;
        ConexionDAO instance = new ConexionDAO();
        boolean expResult = false;
        boolean result = instance.concluirSubasta(refSubasta);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of finalizarSubasta method, of class ConexionDAO.
     */
    @Test
    public void testFinalizarSubasta() {
        System.out.println("finalizarSubasta");
        Subasta refSubasta = null;
        ConexionDAO instance = new ConexionDAO();
        boolean expResult = false;
        boolean result = instance.finalizarSubasta(refSubasta);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resubastarSubasta method, of class ConexionDAO.
     */
    @Test
    public void testResubastarSubasta() {
        System.out.println("resubastarSubasta");
        Subasta refSubasta = null;
        ConexionDAO instance = new ConexionDAO();
        boolean expResult = false;
        boolean result = instance.resubastarSubasta(refSubasta);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actualizarCreditos method, of class ConexionDAO.
     */
    @Test
    public void testActualizarCreditos() {
        System.out.println("actualizarCreditos");
        Usuario refUsuario = null;
        ConexionDAO instance = new ConexionDAO();
        boolean expResult = false;
        boolean result = instance.actualizarCreditos(refUsuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actualizarOferta method, of class ConexionDAO.
     */
    @Test
    public void testActualizarOferta() {
        System.out.println("actualizarOferta");
        int id = 0;
        ConexionDAO instance = new ConexionDAO();
        boolean expResult = false;
        boolean result = instance.actualizarOferta(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of retornarOfertasporSubasta method, of class ConexionDAO.
     */
    @Test
    public void testRetornarOfertasporSubasta() {
        System.out.println("retornarOfertasporSubasta");
        int id = 0;
        ConexionDAO instance = new ConexionDAO();
        List<IOferta> expResult = null;
        List<IOferta> result = instance.retornarOfertasporSubasta(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of revisarSubastasPorTerminar method, of class ConexionDAO.
     */
    @Test
    public void testRevisarSubastasPorTerminar() throws Exception {
        System.out.println("revisarSubastasPorTerminar");
        ConexionDAO instance = new ConexionDAO();
        instance.revisarSubastasPorTerminar();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of revisarSubastasPorIniciar method, of class ConexionDAO.
     */
    @Test
    public void testRevisarSubastasPorIniciar() throws Exception {
        System.out.println("revisarSubastasPorIniciar");
        ConexionDAO instance = new ConexionDAO();
        instance.revisarSubastasPorIniciar();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of agregarTiempoPorCentimos method, of class ConexionDAO.
     */
    @Test
    public void testAgregarTiempoPorCentimos() {
        System.out.println("agregarTiempoPorCentimos");
        Subasta refSubasta = null;
        ConexionDAO instance = new ConexionDAO();
        boolean expResult = false;
        boolean result = instance.agregarTiempoPorCentimos(refSubasta);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of retornarPremiosPorUsuario method, of class ConexionDAO.
     */
    @Test
    public void testRetornarPremiosPorUsuario() {
        System.out.println("retornarPremiosPorUsuario");
        int idUsuario = 0;
        ConexionDAO instance = new ConexionDAO();
        List<Premio> expResult = null;
        List<Premio> result = instance.retornarPremiosPorUsuario(idUsuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertarPremio method, of class ConexionDAO.
     */
    @Test
    public void testInsertarPremio() {
        System.out.println("insertarPremio");
        Premio refPremio = null;
        ConexionDAO instance = new ConexionDAO();
        boolean expResult = false;
        boolean result = instance.insertarPremio(refPremio);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of retornarPagosPorUsuario method, of class ConexionDAO.
     */
    @Test
    public void testRetornarPagosPorUsuario() {
        System.out.println("retornarPagosPorUsuario");
        int idUsuario = 0;
        ConexionDAO instance = new ConexionDAO();
        List<Pago> expResult = null;
        List<Pago> result = instance.retornarPagosPorUsuario(idUsuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertarPago method, of class ConexionDAO.
     */
    @Test
    public void testInsertarPago() {
        System.out.println("insertarPago");
        Pago refPago = null;
        ConexionDAO instance = new ConexionDAO();
        boolean expResult = false;
        boolean result = instance.insertarPago(refPago);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of retornarCobrosPorUsuario method, of class ConexionDAO.
     */
    @Test
    public void testRetornarCobrosPorUsuario() {
        System.out.println("retornarCobrosPorUsuario");
        int idUsuario = 0;
        ConexionDAO instance = new ConexionDAO();
        List<Cobro> expResult = null;
        List<Cobro> result = instance.retornarCobrosPorUsuario(idUsuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertarCobro method, of class ConexionDAO.
     */
    @Test
    public void testInsertarCobro() {
        System.out.println("insertarCobro");
        Cobro refCobro = null;
        ConexionDAO instance = new ConexionDAO();
        boolean expResult = false;
        boolean result = instance.insertarCobro(refCobro);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
