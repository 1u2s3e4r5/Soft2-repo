<%-- 
    Document   : detalleArticulo.jsp
    Created on : 8/02/2015, 09:27:20 PM
    Author     : Diego Nano A
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <c:set var="i" scope="session" value="${sessionScope.detallearticuloadmin}"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/foundation.css"/>
        <link rel="stylesheet" href="css/foundation.min.css"/>
        <link rel="stylesheet" href="css/normalize.css"/>
        <link rel="stylesheet" href="css/main2.css"/>
        <script src="js/vendor/modernizr.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
            <!-- franja superior -->    
        <div>
        <jsp:include page="barra.jsp"/>
        </div>
                   
        <br>
        <br>         

        <div class="row">
        <div class="large-3 columns left">
          <h1><img src="imagen/Logo3.png"></h1>
        </div>
        <div class="large-9 columns right">
          <form>

              <div class="large-10 small-8 columns">
                  <br><br>
                   <a href="homeAdmin.jsp" class="button round " style= " height: 10% ; width: 100%">Buscar otros articulos</a>
                </div>

              
          </form>

        </div>
        </div>
 <!-- FIN Header-->   
        
        <div class="row">
        <hr>
         <!-- primera seccion -->   
        <div class="large-2 columns">
                  </div>
         <!-- segunda seccion -->   
        <div class="large-5 columns">
          <img src="Imagen?id=${i.articulo.idarticulo}">
        </div>
          <!-- tercera seccion -->   
        <div class="large-5 columns">
            
            <div class="off-canvas-wrap" data-offcanvas>
                  <div class="inner-wrap">
                      <nav class="tab-bar">
                         <section class="left-small">
                               <a class="left-off-canvas-toggle menu-icon" href="#"><span></span></a>
                           </section>

                           <section class="middle tab-bar-section">
                               <h1 class="title">${i.articulo.nombre}</h1>
                          </section>
                      </nav>

              <aside class="left-off-canvas-menu">
                 <ul class="off-canvas-list">
                     <li><label><c:out value="${i.articulo.nombre}"></c:out></label></li>
                     <li><a href="#">The Psychohistorians</a></li>
                     <li><a href="#">...</a></li>
                 </ul>
              </aside>

         <section class="main-section">
          <p><c:out value="${i.articulo.descripcion}"></c:out></p>
          <div class="panel">
            <h5>Precio Actual</h5>
          <h6 class="subheader"><c:out value="${i.precioActual}"></c:out></h6>
            <h5>Precio</h5>
            <h6 class="subheader"><c:out value="${i.articulo.precioBase}"></c:out></h6>
            <h5>Vendedor</h5>
            <h6 class="subheader"><c:out value="${i.articulo.vendedor.nombre}"></c:out></h6>
            <h5>Usuario</h5>
            <h6 class="subheader"><c:out value="${i.articulo.vendedor.usuario}"></c:out></h6>
           
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
