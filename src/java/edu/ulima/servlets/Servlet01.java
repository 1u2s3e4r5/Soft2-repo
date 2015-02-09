//Login
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


@WebServlet(name = "Servlet01", urlPatterns = {"/s01"})
public class Servlet01 extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
        HttpSession ses = request.getSession(true);
        String username =  request.getParameter("username");
        String password =   request.getParameter("password");
        
        ConexionDAO dao = new ConexionDAO();
        Usuario u  = dao.validarUsuario(username, password);
       // System.out.print(username);
        if (u == null){
            ses.setAttribute("error", "Error en username o password");
            response.sendRedirect("home.jsp");
        } else {
            ses.setAttribute("usuario", u);
            //System.out.println(u.getTipo());
            if (u.getTipo().equalsIgnoreCase("Cliente")){
            response.sendRedirect("homeUsuario.jsp");
            }else{
            response.sendRedirect("homeAdmin.jsp");
            }
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
