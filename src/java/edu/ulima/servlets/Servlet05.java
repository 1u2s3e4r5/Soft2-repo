//Comprar Creditos
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


@WebServlet(name = "Servlet05", urlPatterns = {"/s05"})
public class Servlet05 extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       HttpSession ses = request.getSession();
       Usuario u = (Usuario) ses.getAttribute("usuario");
       try{
       
       int creditosComprados = Integer.parseInt(request.getParameter("creditos"));
       if (creditosComprados < 15 || creditosComprados >300){
       ses.setAttribute("cred","Error en la Compra, solo se permite comprar entre 15 a 300 creditos" );
       response.sendRedirect("homeUsuario.jsp");
       } else {
       
       u.setCreditos(u.getCreditos() + creditosComprados);
       ConexionDAO dao = new ConexionDAO();
       boolean exito = dao.actualizarCreditos(u);
       if (exito) {
           ses.setAttribute("cred","Se han agregado " + creditosComprados + " créditos a su cuenta" );
       response.sendRedirect("homeUsuario.jsp");
       }else {
       ses.setAttribute("cred","Error en la Compra" );
       response.sendRedirect("homeUsuario.jsp");
            }
       }
    }catch (Exception e){
        ses.setAttribute("cred","Error en la Compra" );
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
