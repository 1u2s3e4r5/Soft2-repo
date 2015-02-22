
package edu.ulima.servlets;

import edu.ulima.bd.ConexionDAO;
import edu.ulima.clases.Subasta;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "ServletBuscarAdmin", urlPatterns = {"/servletbuscar2"})
public class ServletBuscarAdmin extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession ses = request.getSession(true);
        String caso = (String) request.getParameter("buscar");
        ConexionDAO dao = new ConexionDAO();
        List<Subasta> lista = null;
        switch (caso) {
            case "Bajo":
                lista = dao.retornarSubastasPorPrecio(0, 100);
                break;
            case "Medio":
                lista = dao.retornarSubastasPorPrecio(100, 200);
                break;
            case "Alto":
                lista = dao.retornarSubastasPorPrecio(200, 100000);
                break;
            case "Directa":
                lista = dao.retornarSubastasPorTipo("Directa");
                break;
            case "PorCentimos":
                lista = dao.retornarSubastasPorTipo("Por Centimos");
                break;
            case "All":
                lista = dao.retornarTodasLosSubastasDisponibles();
                break;
            case "Activas":
                lista = dao.retornarSubastasPorEstado("Iniciado");
                break;
            case "NoIniciadas":
                lista = dao.retornarSubastasPorEstado("No Iniciado");
                break;
            case "Terminado":
                lista = dao.retornarSubastasPorEstado("Terminado");
                break;   
            case "Finalizado":
                lista = dao.retornarSubastasPorEstado("Finalizado");
                break; 
            case "PorIniciar":
            lista = dao.retornarSubastasPorEstado("Por Iniciar");
            break; 
       }
        ses.setAttribute("lista", lista);
        response.sendRedirect("homeAdmin.jsp");
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
