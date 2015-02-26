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
import java.util.GregorianCalendar;
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
public class ConexionDAOTest1 {
     Usuario refUsuario1 = new Usuario(9999999,"pruebaRegNU","pruebaRNU","prueba",100,"Cliente");
    
    Articulo refArticulo1 = new Articulo(refUsuario1,"ArticuloSurreal","ArticuloSurreal","Directa",new SimpleDateFormat("yy/MM/dd-HH:mm:ss").format(Calendar.getInstance().getTime()),90.0f); //borrar este artículo
            
    Subasta refSubasta1 = new Subasta(refArticulo1, "Iniciado", 91.0f);
    
    Oferta refOferta1=new Oferta(refUsuario1, refSubasta1, 91.1f);
    
    
    public ConexionDAOTest1() {
            System.out.println("***1");
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
        
        
        
        long dni=9999999;
        List<Subasta> ll=instance.retornarSubastasPorDNI(dni);
        Subasta refSubast=null;
        
        for(Subasta refSubasta:ll){
            refSubast=refSubasta;
            System.out.println(refSubasta.getIdsubasta());
        }
        //tengo el id de subasta
        
        //Instanciamos el objeto Calendar
        //en fecha obtenemos la fecha y hora del sistema
        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
       // int segundo = fecha.get(Calendar.SECOND);
        // String fechaInicio= fecha.substring(2,4)+"/"+fecha.substring(5,7)+"/"+fecha.substring(8,10)+"-"+hora+":00";
        String cadena=dia+"/"+(mes+1)+"/"+año+"-"+hora+minuto+":00";
        refSubast.setFechaInicio(cadena);
        instance.porIniciarSubasta(refSubast);

    }


    
}
