//Registro
package edu.ulima.servlets;

import edu.ulima.bd.ConexionDAO;
import edu.ulima.clases.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "Servlet04", urlPatterns = {"/s04"})
public class Servlet04 extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession ses = request.getSession();
        
        String nombre = (String ) request.getParameter("Nombre");
        Long DNI = Long.parseLong((String) request.getParameter("DNI"));
        String apellido = (String ) request.getParameter("Apellido");
        String pw = (String ) request.getParameter("Password");
        String user = (String) request.getParameter("User");
        Usuario u = new Usuario(DNI, nombre+" " + apellido, user , pw, 0);
        
        ConexionDAO g = new ConexionDAO();
        boolean exito = g.registrarNuevoUsuario(u);
        
        if ( exito){
        ses.setAttribute("usuario", u);
        response.sendRedirect("homeUsuario.jsp");
        }else {
        ses.setAttribute("error", "Error en registro");
        response.sendRedirect("registrate.jsp");
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
