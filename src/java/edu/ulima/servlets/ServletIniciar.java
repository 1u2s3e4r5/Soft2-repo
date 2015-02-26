
package edu.ulima.servlets;

import edu.ulima.bd.ConexionDAO;
import edu.ulima.clases.Cobro;
import edu.ulima.clases.IOferta;
import edu.ulima.clases.Oferta;
import edu.ulima.clases.Pago;
import edu.ulima.clases.Premio;
import edu.ulima.clases.Subasta;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "ServletIniciar1", urlPatterns = {"/servletiniciar1"})
public class ServletIniciar extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ConexionDAO dao = new ConexionDAO();
        HttpSession ses = request.getSession();
        
        List<Subasta> lista = new ArrayList<>();
        String action = request.getParameter("action");
        if ("Iniciar Subasta".equals(action)) {
            
            try{
        String[] checkbox = request.getParameterValues("feedback");
        
        for (String s: checkbox){
            //System.out.println(checkbox);
        int a= Integer.parseInt(s);
        Subasta sub = dao.buscarSubastaPorID(a);
        if (sub.getEstado().equalsIgnoreCase("No Iniciado")){
        lista.add(sub);
        }
        
        
        
        }
        
        ses.setAttribute("listaIniciar", lista);
        response.sendRedirect("homeAdmin2.jsp");
        }catch(Exception e){
        ses.setAttribute("error", "No selecciono ninguna subasta");
        response.sendRedirect("homeAdmin.jsp");
        }
        
        
        }else {
        //Concluir Servlet
           String[] checkbox = request.getParameterValues("concluir");
        
        for (String s: checkbox){
        int a= Integer.parseInt(s);
        Subasta sub = dao.buscarSubastaPorID(a);    
        sub.setOfertas(dao.retornarOfertasporSubasta(a));
        int count = sub.getOfertas().size();
        
        List<IOferta> listaOfertas = new ArrayList();
      
        
        
       
        //Averiguar 3 ofertas distintas
        for ( int i = 0; i< count;i++){
        IOferta of = sub.getOfertas().get(i);
        boolean existe = false;
            for (IOferta oT : listaOfertas){
                if (oT.getComprador().getDNI() == of.getComprador().getDNI()){
                existe = true;
                }
            }
        if (!existe){
        listaOfertas.add(of);
        }    
            
        }
        
        
        int trueCount = listaOfertas.size();
        
        
        Pago pago = new Pago();
        pago.setMonto(sub.getPrecioActual());
        pago.setSubasta(sub);
        pago.setVendedor(sub.getArticulo().getVendedor());
        String timeStamp = new SimpleDateFormat("yy/MM/dd-HH:mm:ss").format(Calendar.getInstance().getTime());
        pago.setFecha(timeStamp);
        Cobro cobro = new Cobro();
        cobro.setFecha(timeStamp);
        cobro.setSubasta(sub);
        Premio premio1 = new Premio();
        premio1.setSubasta(sub);
        premio1.setCantidad(200);
        premio1.setTipo("Primero");
        
        Premio premio2 = new Premio();
        premio2.setSubasta(sub);
        premio2.setCantidad(100);
        premio2.setTipo("segunda");
        
        Premio premio3 = new Premio();
        premio3.setSubasta(sub);
        premio3.setCantidad(50);
        premio3.setTipo("tercera");
        switch(trueCount){
            case 0:
            dao.concluirSubasta(sub);
                break;
            case 1:
            IOferta o1 = listaOfertas.get(0);
            //Actualizar Premio y Pagos.
            cobro.setMonto(o1.getMonto());
            cobro.setComprador(o1.getComprador());
            
            pago.setVendedor(sub.getArticulo().getVendedor());
            pago.setMonto(o1.getMonto()*0.8f);
            premio1.setUsuario(o1.getComprador());
            
            dao.concluirSubasta(sub);
            dao.insertarPago(pago);
            dao.insertarCobro(cobro);
            dao.insertarPremio(premio1);
            o1.getComprador().setCreditos(o1.getComprador().getCreditos()+200);
            dao.actualizarCreditos(o1.getComprador());
                break;
            case 2:
             IOferta o21 = listaOfertas.get(0);
             IOferta o22 = listaOfertas.get(1);
            //Actualizar Premio y Pagos.
             
             cobro.setMonto(o21.getMonto());
            cobro.setComprador(o21.getComprador());
            
             pago.setVendedor(sub.getArticulo().getVendedor());
            pago.setMonto(o21.getMonto()*0.8f);
            
            premio1.setUsuario(o21.getComprador());
            premio2.setUsuario(o22.getComprador());
            dao.concluirSubasta(sub);
            dao.insertarPago(pago);
            dao.insertarCobro(cobro);
            dao.insertarPremio(premio1);
            dao.insertarPremio(premio2);
            
            o21.getComprador().setCreditos(o21.getComprador().getCreditos()+200);
            o22.getComprador().setCreditos(o22.getComprador().getCreditos()+100);
            dao.actualizarCreditos(o21.getComprador());
            dao.actualizarCreditos(o22.getComprador());
            
                break;
            default:
                IOferta o31 = listaOfertas.get(0);
                IOferta o32 = listaOfertas.get(1);
                IOferta o33 = listaOfertas.get(2);
            //Actualizar Premio y Pagos.
          cobro.setMonto(o31.getMonto());
            cobro.setComprador(o31.getComprador());
            
             pago.setVendedor(sub.getArticulo().getVendedor());
            pago.setMonto(o31.getMonto()*0.8f);
            
            premio1.setUsuario(o31.getComprador());
            premio2.setUsuario(o32.getComprador());
            premio3.setUsuario(o33.getComprador());
            dao.concluirSubasta(sub);
            dao.insertarPago(pago);
            dao.insertarCobro(cobro);
            dao.insertarPremio(premio1);
            dao.insertarPremio(premio2);
            dao.insertarPremio(premio3);
            
            o31.getComprador().setCreditos(o31.getComprador().getCreditos()+200);
            o32.getComprador().setCreditos(o32.getComprador().getCreditos()+100);
            o33.getComprador().setCreditos(o33.getComprador().getCreditos()+50);
            dao.actualizarCreditos(o31.getComprador());
            dao.actualizarCreditos(o32.getComprador());
            dao.actualizarCreditos(o33.getComprador());
                break;
          }
        }
        
        response.sendRedirect("homeAdmin.jsp");
        
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
