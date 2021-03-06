<%@page import="edu.ulima.bd.*" %>
<%@page import ="edu.ulima.clases.*" %>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="edu.ulima.clases.Usuario" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
            <%
            Usuario u = (Usuario) request.getSession().getAttribute("usuario");
            if(u==null){
            response.sendRedirect("home.jsp");
            } else {
            %>
        <meta name ="viewport" content = "width=device-width, initial-scale=1, maximum-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Home</title>
        <link rel="stylesheet" href="css/foundation.css"/>
        <link rel="stylesheet" href="css/normalize.css"/>
        <link rel="stylesheet" href="css/main2.css"/>
    </head>
    <body></body>
        <div>
        <jsp:include page="barra.jsp"/>
        </div>
    
        <% 
        
        if (u.getTipo().equalsIgnoreCase("Cliente")){
        response.sendRedirect("homeUsuario.jsp");
        }else {
        
        HttpSession ses = request.getSession(true);
        ConexionDAO dao = new ConexionDAO();
        
        List<Subasta> lista = (List) ses.getAttribute("lista");
        if(lista == null){
            ses.setAttribute("listaM", dao.retornarTodasLosSubastasDisponibles());
        }else{
            ses.setAttribute("listaM", lista);
            ses.setAttribute("lista",null);
        }
        }
         
            }
        %>
           <c:set var="totalLista" value="${fn:length(listaM)}" />
        <c:set var="error" scope="session" value="${sessionScope.error}"/>
        <!-- franja superior -->    
        
                   
        <br>
        <br>
    

        <!-- segun la pestaÃ±a seleccionada -->
        <%
        int filters=(Integer)request.getSession().getAttribute("filters");
        switch(filters){
            case 2:  %>
        <h1 align="center">Subastas Activas</h1>
        <% break;
            case 1:%>       
         <h1 align="center">Iniciar Subasta (paso 1)</h1>  
         <% break;
            case 4:%>  
          <h1 align="center">Finalizar Subasta (Único paso)</h1>  
          <% break;
            case 3:%> 
           <h1 align="center">Subastas Concluidas</h1>  
           <% break;
            case 0:%> 
           <h1 align="center">Artículos</h1>   
           <% break;
            case 5:%> 
            <h1 align="center">Subastas</h1>  
        <% break;    
        }    
        %>

        
        
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
                <% HttpSession ses=request.getSession(true);
        
                    switch(filters){  
                    case 5:
                    ses.setAttribute("active5", "active");
                    ses.setAttribute("active1", "");
                    ses.setAttribute("active2", "");
                    ses.setAttribute("active3", "");
                    ses.setAttribute("active4", "");
                    ses.setAttribute("active0", "");
                    break; 
                    case 2:
                    ses.setAttribute("active2", "active");
                    ses.setAttribute("active1", "");
                    ses.setAttribute("active5", "");
                    ses.setAttribute("active3", "");
                    ses.setAttribute("active4", "");
                    ses.setAttribute("active0", "");
                        break;
                    case 1:
                    ses.setAttribute("active1", "active");
                    ses.setAttribute("active2", "");
                    ses.setAttribute("active3", "");
                    ses.setAttribute("active4", "");
                    ses.setAttribute("active5", ""); 
                    ses.setAttribute("active0", "");
                        break;
                    case 3:
                    ses.setAttribute("active3", "active");
                    ses.setAttribute("active2", "");
                    ses.setAttribute("active1", "");
                    ses.setAttribute("active4", "");
                    ses.setAttribute("active5", ""); 
                    ses.setAttribute("active0", "");
                        break;
                    case 4:
                    ses.setAttribute("active4", "active");
                    ses.setAttribute("active2", "");
                    ses.setAttribute("active3", "");
                    ses.setAttribute("active1", "");
                    ses.setAttribute("active5", "");  
                    ses.setAttribute("active0", "");
                        break;
                    case 0:
                    ses.setAttribute("active0", "active");
                    ses.setAttribute("active2", "");
                    ses.setAttribute("active3", "");
                    ses.setAttribute("active1", "");
                    ses.setAttribute("active5", "");  
                    ses.setAttribute("active4", "");
                        break;    
                        
                } %>
                <dd class="${active5}"><a href="servletbuscar2?buscar=All">All</a></dd>
                <dd class="${active2}"><a href="servletbuscar2?buscar=Activas">Activas</a></dd>
                <dd class="${active1}"><a href="servletbuscar2?buscar=NoIniciadas">No Iniciadas</a></dd>
                <dd class="${active0}"><a href="servletbuscar2?buscar=PorIniciar">Por Iniciar</a></dd>
                <dd class="${active4}"><a href="servletbuscar2?buscar=Finalizado">Finalizadas</a></dd>
                <dd class="${active3}"><a href="servletbuscar2?buscar=Concluido">Concluido</a></dd>
                <!-- <dd class-"hide-for-small-only"><a href="#">Suspended</a></dd>-->

               
              </dl>
            </div>
                  <form action="servletiniciar1" method ="post" id="formC">
                  <c:forEach var="i" items="${listaM}" varStatus="Counter">
                      <c:if test="${Counter.count == (totalLista)}">
                          <div class="large-3 medium-4 small-6 columns end"> 
                      </c:if>
                      <c:if test="${Counter.count != (totalLista)}">
                          <div class="large-3 medium-4 small-6 columns"> 
                      </c:if>
                       
                              <a href="detallearticuloadmin?idarticulo=${i.idsubasta}&type=admin"><img src="Imagen?id=${i.idsubasta}" style="width: 100%;"></a>
                          <div class="panel">
                              <c:if test="${sessionScope.filters eq 1}">
                                <h5><input type="checkbox" name="feedback" value="${i.idsubasta}"/>${i.articulo.nombre}</h5>
                                 </c:if>
                                <c:if test="${sessionScope.filters eq 4}">
                                
                                <h5><input type="checkbox" name="concluir" value="${i.idsubasta}"/>${i.articulo.nombre}</h5>
                                
                                 </c:if>
                               <h6>Tipo Subasta: ${i.articulo.tipo}</h6>
                               <h6 class="subheader">Precio Base:</h6>
                            <h6 class="subheader">${i.articulo.precioBase}</h6>
                            
                            
                           
                            <h6 class="subheader">Precio Actual:</h6>
                            <h6 class="subheader">${i.precioActual}</h6>
                            <h6 class="subheader">Estado:</h6>
                            <h6 class="subheader">${i.estado}</h6>
                            <c:if test="${i.fechaInicio != null}">
                         <h6 class="subheader">Fecha Inicio:</h6>
                            <h6 class="subheader">${i.fechaInicio}</h6>
                            <h6 class="subheader">Fecha Fin</h6>
                            <h6 class="subheader">${i.fechaFin}</h6>
                        </c:if>    
                            
                                </div>
                                
                        </div>
                      
                    </c:forEach>
     
                
                <!-- segun la pestaÃ±a seleccionada -->
                            <%
                    //String filters=(String)request.getSession().getAttribute("filters");
                    switch(filters){
                        case 2:  %>
                    <div class="row">
                                    <div class="small-4 medium-4 large-4 columns large-centered medium-centered small-centered">
                                       <!-- <center> <input type="submit" value ="Cerrar subasta" class="button round"></center> -->
                                    </div>  <br/>  <br/>
                    </div>
                    <% break;
                        case 1:%>       
                     <div class="row">
                                    <div class="small-4 medium-4 large-4 columns large-centered medium-centered small-centered">
                                        <center> <input type="submit" value ="Iniciar Subasta" class="button round" name="action"></center>
                                    </div>  <br/>  <br/>
                    </div>  

                     <% break;
                        case 4:%>  
                      <div class="row">
                                    <div class="small-4 medium-4 large-4 columns large-centered medium-centered small-centered">
                                        <center> <input type="submit" value ="Concluir Subasta" class="button round" name="action"></center>
                                    </div>  <br/>  <br/>
                    </div>   



                    <% break;    
                    }    
                    %>

                              
                    <br/>
                    <br/>
                    </form>
                
                
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
