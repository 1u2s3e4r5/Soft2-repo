<%@page import="edu.ulima.bd.*" %>
<%@page import ="edu.ulima.clases.*" %>
<%@page import="java.util.List"%>
<%@page import="java.util.Collections"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/foundation.css"/>
        <link rel="stylesheet" href="css/foundation.min.css"/>
        <link rel="stylesheet" href="css/normalize.css"/>
        <link rel="stylesheet" href="css/main2.css"/>
        <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
        <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">

        <script src="js/vendor/modernizr.js"></script>


        <title>JSP Page</title>
    </head>
    <body>
            <!-- franja superior -->    
        
        <jsp:include page="barra.jsp"/>
        
        <%
        ConexionDAO dao = new ConexionDAO();
        HttpSession ses = request.getSession();
        Subasta s = (Subasta)ses.getAttribute("detallearticuloadmin");
        s.setOfertas(dao.retornarOfertasporSubasta(s.getIdsubasta()));
        ses.setAttribute("detallearticuloadmin", s);
        ses.setAttribute("cuentaOfertas", s.getOfertas().size());
        %>
                 
        <c:set var="i" scope="session" value="${sessionScope.detallearticuloadmin}"/>
        <c:set var="l" scope="session" value="${sessionScope.cuentaOfertas}"/>
        <div class="row">
        <div class="large-3 medium-3 columns left">
          <h1><img src="imagen/Logo3.png"></h1>
        </div>
        <div class="large-9 medium-9 columns right">
          
              <div class="row">
              <div class="large-10 small-8 columns">
                  <br><br>
                   <a href="homeUsuario.jsp" class="button round " style= " height: 10% ; width: 100%">Buscar otros articulos</a>
                </div>
                  </div>
          

        </div>
        </div>
 <!-- FIN Header-->   
        
        <div class="row">
        <hr>
         <!-- primera seccion -->   
        <div class="large-2 columns">
            .
                  </div>
         <!-- segunda seccion -->   
        <div class="large-5 columns">
          <img src="Imagen?id=${i.idsubasta}" style="width:100%">
        </div>
          <!-- tercera seccion -->   
        <div class="large-5 columns">
            
            <div class="off-canvas-wrap" data-offcanvas>
                  <div class="inner-wrap">
                      <nav class="tab-bar">
                          
                         <section class="left-small">
                               <a class="left-off-canvas-toggle icon-legal icon-2x" href="#" style="padding-left: 0.4444rem;color:#ffffd0;text-decoration:none;"><span></span></a>
                           </section>
                         

                           <section class="middle tab-bar-section">
                               <h1 class="title">${i.articulo.nombre}</h1>
                          </section>
                      </nav>
                
              <aside class="left-off-canvas-menu">
                 <ul class="off-canvas-list">
                     <li><label><c:out value="${i.articulo.nombre}"></c:out></label></li>
                     
                     
                     <div class="" style="overflow-x:hidden;overflow-y:auto;">
                         <div style="color:#ffffd0">
                         <!--
                            AQUI VAN LAS OFERTAS DEL PRODUCTO ********
                            -->
                            <p><c:forEach var="k" items="${i.ofertas}" varStatus="Counter">
                              
                             <h3>Oferta:</h3>
                                <h4>Comprador:</h4>
                                 <h5>${k.comprador.nombre}</h5>
                                  <h4>Fecha:</h4>
                                 <h5>${k.fecha}</h5>
                                  <h4>Monto:</h4>
                                 <h5>${k.monto}</h5>
                                ---------------------------   
                             </c:forEach></p>
                         
                         
                         </div>
                     </div>
                     
                     <!--  IF, SI NO HAY NINGUNA OFERTA, MENSAJE: "No existen ofertas para este artículo"  -->
                     
                     
                 </ul>
              </aside>
                     
         <section class="main-section">
          <p><c:out value="${i.articulo.descripcion}"></c:out></p>
          <div class="panel">
          
            <h5>Precio Actual</h5>
          <h6 class="subheader"><c:out value="${i.precioActual}"></c:out></h6>
          
            <h5>Precio Base</h5>
            <h6 class="subheader"><c:out value="${i.articulo.precioBase}"></c:out></h6>
            <h5>Tipo de Subasta</h5>
            <h6 class="subheader"><c:out value="${i.articulo.tipo}"></c:out></h6>
            <h5>Vendedor</h5>
            <h6 class="subheader"><c:out value="${i.articulo.vendedor.nombre}"></c:out></h6>
            <h5>Usuario</h5>
            <h6 class="subheader"><c:out value="${i.articulo.vendedor.usuario}"></c:out></h6>
            <h5>Estado</h5>
            <h6 class="subheader"><c:out value="${i.estado}"></c:out></h6>
          
          
          <!-- IF QUE ACTIVA EL BOTON************   -->
          <div class="row">
          <c:if test="${i.estado eq 'Finalizado'}">
              <c:if test="${l ==0}">
                     
                  <form action="resubastarservlet" method="post">
          <div class=" large-7 medium-7 columns medium-centered">
              <input type="text" placeholder="nuevo precio" required name="nuevomonto"/>
              <input type="submit" class="button" value ="Resubastar Aquí"/></div>
          </form>
          </c:if>
          </c:if>
          </div>
          
          </div>
          </section>

            <a class="exit-off-canvas"></a>

         </div>
         </div>
            


        </div>
        </div>
         
            <footer class="row">
        <div class="large-12 columns">
          <hr>
          <div class="row">
            <div class="large-6 columns">
              <p>© Copyright no one at all. Go to town.</p>
            </div>
            <div class="large-6 columns">
              <ul class="inline-list right">
                <li><a href="#">Link 1</a></li>
                <li><a href="#">Link 2</a></li>
                <li><a href="#">Link 3</a></li>
                <li><a href="#">Link 4</a></li>
              </ul>
            </div>
          </div>
        </div>
        </footer>

        
    
    
        <script src="js/vendor/jquery.js"></script>
        <script src="js/vendor/fastclick.js"></script>
        <script src="js/foundation.min.js"></script>
        <script src="js/foundation/foundation.js"></script>
         <script src="js/foundation/foundation.offcanvas.js"></script>
         <script src="js/foundation/foundation.dropdown.js"></script>

<script>
  $(document).foundation();
</script>
    
    </body>
</html>
