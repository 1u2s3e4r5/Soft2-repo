<%@page import="edu.ulima.bd.*" %>
<%@page import ="edu.ulima.clases.*" %>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <%
        HttpSession ses = request.getSession(true);
        ConexionDAO dao = new ConexionDAO();
        
        List<Subasta> lista = (List) ses.getAttribute("lista");
        if(lista == null){
            ses.setAttribute("listaM", dao.retornarTodasLosSubastasDisponibles());
        }else{
            ses.setAttribute("listaM", lista);
            ses.setAttribute("lista",null);
        }
        
        %>
        
        <c:set var="error" scope="session" value="${sessionScope.error}"/>
        <c:set var="items" scope="session" value="${sessionScope.listaM}"/>
        <c:set var="totalLista" value="${fn:length(items)}" />
        <meta name ="viewport" content = "width=device-width, initial-scale=1, maximum-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Home</title>
        <link rel="stylesheet" href="css/foundation.css"/>
        <link rel="stylesheet" href="css/normalize.css"/>
        <link rel="stylesheet" href="css/main2.css"/>
    </head>
    <body></body>
        
        <!-- franja superior -->    
        <div>
        <jsp:include page="barra.jsp"/>
        </div>
                   
        <br>
        <br>
    
        <h1 align="center">Artículos</h1>
        
        
        <!-- lista de articulos  -->
        <!-- Thumbnails -->
        <div class="row">
        
        <div class="medium-2 large-2 columns hide-for-small-down fffblanco2">
                <ul class="side-nav">
                <li>Precio</li>
                <li><a href="servletbuscar?buscar=Bajo">0-100</a></li>
                <li><a href="servletbuscar?buscar=Medio">100-200</a></li>
                <li><a href="servletbuscar?buscar=Alto">200+</a></li>
                <li class="divider"></li>
                <li>Tipo de Subasta</li>
                <li><a href="servletbuscar?buscar=Directa">Directa</a></li>
                <li><a href="servletbuscar?buscar=PorCentimos">Por centimos</a></li>
                <li class="divider"></li>
                <li>Vendedor(usuario)</li>
                <form method="post" action="vendedorservlet?type=Obs">
                <li><input type="text" name="vendedor"></li>
                <input type="submit" value="Buscar"/>
                </form>
              </ul>
                
            </div>
        
        
        <div class="medium-10 large-10 columns">
        
        
     
              <div class="row">
                  <c:forEach var="i" items="${listaM}" varStatus="Counter">
                      <c:if test="${Counter.count == (totalLista)}">
                          <div class="large-3 medium-4 small-6 columns end"> 
                      </c:if>
                      <c:if test="${Counter.count != (totalLista)}">
                          <div class="large-3 medium-4 small-6 columns"> 
                      </c:if>
                       
                              <a href="detallearticuloadmin?idarticulo=${i.articulo.idarticulo}&type=obs"><img src="Imagen?id=${i.articulo.idarticulo}"></a>
                          <div class="panel">
                                <h5>${i.articulo.nombre}</h5>
                                <h6 class="subheader">${i.precioActual}</h6>
                                </div>
                        </div>
                      
                    </c:forEach>
               
                  
  
              </div>
        
        <!-- Fin Thumbnails -->
     
        
         <div class="row">
                    <div class="pagination-centered">
                        <ul class="pagination">
                        <li class="arrow unavailable"><a href="">&laquo;</a></li>
                        <li class="current"><a href="">1</a></li>
                        <li><a href="">2</a></li>
                        <li><a href="">3</a></li>
                        <li><a href="">4</a></li>
                        <li class="unavailable"><a href="">&hellip;</a></li>
                        <li><a href="">12</a></li>
                        <li><a href="">13</a></li>
                        <li class="arrow"><a href="">&raquo;</a></li>
                      </ul>
                        
                        
                    </div>
                </div>
        
        
        </div>
   
       </div>
        
   
    
    <c:if test="${error!=null}">
        <script type="text/javascript">
           
               alert("${error}");
        </script>
        
        <c:remove var="error" scope="session"/>
    </c:if>
        
        
        <!-- el footer  -->
        <jsp:include page="footer.jsp"/>
         
        
    </body>
</html>
