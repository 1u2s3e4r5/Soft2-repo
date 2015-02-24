package edu.ulima.servlets;


import edu.ulima.bd.ConexionDAO;
import edu.ulima.clases.Oferta;
import edu.ulima.clases.Subasta;
import edu.ulima.clases.Usuario;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = {"/servletofertar"})
public class ServletOfertar extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
       HttpSession ses = request.getSession();
       Usuario u = (Usuario) ses.getAttribute("usuario");
       
       int idsub = Integer.parseInt(request.getParameter("idsubasta"));
       
       ConexionDAO dao = new ConexionDAO();
       Oferta o = new Oferta();
       o.setComprador(u);
       Subasta sus = dao.buscarSubastaPorID(idsub);
       sus.setOfertas(dao.retornarOfertasporSubasta(idsub));
       o.setSubasta(sus);
       dao.revisarSubastasPorTerminar();
       if (sus.getEstado().equalsIgnoreCase("Iniciado")){
       
       float monto;
       if (sus.getArticulo().getTipo().equalsIgnoreCase("Directa")){
       
       monto = Float.parseFloat(request.getParameter("monto"));
       } else {
       monto = sus.getPrecioActual()+0.5f;
       }
       
       o.setMonto(monto);
        u = dao.buscarUsuarioPorDNI(u.getDNI());
       if (u.getDNI() == sus.getArticulo().getVendedor().getDNI()){
            ses.setAttribute("msjOferta", "No se puede realizar ofertas sobre su propio articulo");
            response.sendRedirect("homeUsuario.jsp");
       }else {
       
                if(sus.getPrecioActual()< monto){
                    if (u.getCreditos() !=0){
                boolean exito = sus.agregarOferta(o);

                     if(exito){

                             u.setCreditos(u.getCreditos()-1);
                             dao.actualizarCreditos(u);
                              u = dao.buscarUsuarioPorDNI(u.getDNI());
                              ses.setAttribute("usuario", u);
                             ses.setAttribute("msjOferta", "Oferta Registrada");
                             response.sendRedirect("homeUsuario.jsp");

                     }else{
                     ses.setAttribute("msjOferta", "Error en Oferta");
                     response.sendRedirect("homeUsuario.jsp");
                     }
                }else {

                        ses.setAttribute("msjOferta", "No tiene creditos disponibles");
                        response.sendRedirect("homeUsuario.jsp");
                }

             }else {
                         ses.setAttribute("msjOferta", "Monto por debajo del precio");
                     response.sendRedirect("homeUsuario.jsp"); 
                    
                    
                }}}else {
                     ses.setAttribute("msjOferta", "La oferta ha expirado");
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ServletOfertar.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ServletOfertar.class.getName()).log(Level.SEVERE, null, ex);
        }
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
