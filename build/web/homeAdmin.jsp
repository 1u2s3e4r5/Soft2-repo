<%@page import="edu.ulima.bd.*" %>
<%@page import ="edu.ulima.clases.*" %>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="edu.ulima.clases.Usuario" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
         <% Usuario u = (Usuario) request.getSession().getAttribute("usuario");
        if (u.getTipo().equalsIgnoreCase("Cliente")){
        response.sendRedirect("homeUsuario.jsp");
        }
        
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
    
        <h1 align="center">Iniciar Subasta (paso 1)</h1>
        
        
        <!-- lista de articulos  -->
        <!-- Thumbnails -->
        <div class="row">
            <div class="medium-2 large-2 columns hide-for-small-down fffblanco2">
                <ul class="side-nav">
               <li>Precio</li>
                <li><a href="servletbuscar2?buscar=Bajo">0-100</a></li>
                <li><a href="servletbuscar2?buscar=Medio">100-200</a></li>
                <li><a href="servletbuscar2?buscar=Alto">200+</a></li>
                <li class="divider"></li>
                <li>Tipo de Subasta</li>
                <li><a href="servletbuscar2?buscar=Directa">Directa</a></li>
                <li><a href="servletbuscar2?buscar=PorCentimos">Por centimos</a></li>
                <li class="divider"></li>
                <li>Vendedor(usuario)</li>
                <form method="post" action="vendedorservlet?type=admin">
                <li><input type="text" name="vendedor"></li>
                <input type="submit" value="Buscar"/>
                </form>
              </ul>
                
            </div>
            
            <div class="medium-10 large-10 columns">

              <div class="row">
   
                  <div class="medium-8 large-8 columns medium-centered hide-for-small-down ">
            <dl class="sub-nav medium-centered fffblanco">
                
                <dt>Filter:</dt>
                <dd class="active"><a href="servletbuscar2?buscar=All">All</a></dd>
                <dd><a href="servletbuscar2?buscar=Activas">Activas</a></dd>
                <dd><a href="servletbuscar2?buscar=NoIniciadas">No Iniciadas</a></dd>
                <dd><a href="servletbuscar2?buscar=Terminado">Destacadas</a></dd>
                <dd><a href="servletbuscar2?buscar=Finalizado">Finalizadas</a></dd>
                <!-- <dd class-"hide-for-small-only"><a href="#">Suspended</a></dd>-->
               
              </dl>
            </div>
                  <form action="servletiniciar1" method ="post">
                  <c:forEach var="i" items="${listaM}" varStatus="Counter">
                      <c:if test="${Counter.count == (totalLista)}">
                          <div class="large-3 medium-4 small-6 columns end"> 
                      </c:if>
                      <c:if test="${Counter.count != (totalLista)}">
                          <div class="large-3 medium-4 small-6 columns"> 
                      </c:if>
                       
                              <a href="detallearticuloadmin?idarticulo=${i.articulo.idarticulo}&type=admin"><img src="Imagen?id=${i.articulo.idarticulo}"></a>
                          <div class="panel">
                              <c:if test="${i.estado eq 'No Iniciado'}">
                                <h5><input type="checkbox" name="feedback" value="${i.articulo.idarticulo}"/>${i.articulo.nombre}</h5>
                                 </c:if>
                                <h6 class="subheader">${i.precioActual}</h6>
                                </div>
                        </div>
                      
                    </c:forEach>
     
                
                <div class="row">
                    <div class="small-4 medium-4 large-4 columns large-centered medium-centered small-centered">
                        <center> <input type="submit" value ="Iniciar subasta" class="button round"></center>
                    </div>
                    <br/>
                    <br/>
                    </form>
                </div>
                
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
            
            
                
        
        <!-- Fin Thumbnails -->
     
        
        
        
        
   
       
        
   
    
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
