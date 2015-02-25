
package edu.ulima.servlets;

import edu.ulima.bd.ConexionDAO;
import edu.ulima.clases.Articulo;
import edu.ulima.clases.Subasta;
import edu.ulima.clases.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "ResubastarServlet", urlPatterns = {"/resubastarservlet"})
public class ResubastarServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession ses = request.getSession();
        try{
        
        Usuario u = (Usuario) ses.getAttribute("usuario");
        Float monto = Float.parseFloat(request.getParameter("nuevomonto"));
        Subasta s = (Subasta)ses.getAttribute("detallearticuloadmin");
        ConexionDAO dao = new ConexionDAO();
        
        Articulo refArticulo = dao.buscarArticuloPorID(s.getArticulo().getIdarticulo());
        refArticulo.setPrecioBase(monto);
        dao.actualizarPrecioArticulo(refArticulo);
        dao.resubastarSubasta(s);
        
        Subasta nuevo = new Subasta();
        nuevo.setArticulo(s.getArticulo());
        nuevo.setPrecioActual(monto);
        nuevo.setEstado("No Iniciado");
        dao.registrarSubasta(nuevo);
        
            ses.setAttribute("msj", "Articulo Resubastado");
            response.sendRedirect("homeUsuario.jsp");
            
        }catch (Exception e){
             ses.setAttribute("msj", "Error en la Resubasta");
            response.sendRedirect("homeUsuario.jsp");
           
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
