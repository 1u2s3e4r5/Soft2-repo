//Registro Articulo
package edu.ulima.servlets;

import edu.ulima.bd.ConexionDAO;
import edu.ulima.clases.Articulo;
import edu.ulima.clases.Usuario;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig
@WebServlet(name = "Servlet03", urlPatterns = {"/s03"})
public class Servlet03 extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        HttpSession ses = request.getSession();
        Usuario user = (Usuario) ses.getAttribute("usuario");
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        Part filePart = request.getPart("foto");
        String fileName = filePart.getSubmittedFileName();
        InputStream fileContent = filePart.getInputStream();
        try{
            
            Float precioB = Float.parseFloat(request.getParameter("preciob"));
        String tipos = request.getParameter("tipos");
         ConexionDAO g = new ConexionDAO();
          
        Articulo a = new Articulo();
        a.setVendedor(user);
        
        a.setNombre(nombre);
        
        a.setPrecioBase(precioB);
        a.setDescripcion(descripcion); 
        a.setTipo(tipos);
        String timeStamp = new SimpleDateFormat("yy/MM/dd-HH:mm:ss").format(Calendar.getInstance().getTime());
        a.setFechaInicio(timeStamp);
       
       boolean exito = g.registrarNuevaPiezas(a, filePart);
        
       if (exito) {
       ses.setAttribute("msj2", "Pieza Registrada");
       response.sendRedirect("homeUsuario.jsp");
       } else {
       ses.setAttribute("msj", "Error en registro");
       response.sendRedirect("registraArticulo.jsp");
       }
        }catch (Exception e){
         ses.setAttribute("msj", "Error en registro");
       response.sendRedirect("registraArticulo.jsp");
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
