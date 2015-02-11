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
        if (u.getTipo().equalsIgnoreCase("Admin")){
        response.sendRedirect("homeAdmin.jsp");
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
        <c:set var="msj2" scope="session" value="${sessionScope.msj2}"/>
        <c:set var="error" scope="session" value="${sessionScope.error}"/>
        <meta name ="viewport" content = "width=device-width, initial-scale=1, maximum-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Home</title>
        <link rel="stylesheet" href="css/foundation.css"/>
        <link rel="stylesheet" href="css/normalize.css"/>
        <link rel="stylesheet" href="css/main2.css"/>
    </head>
    <body>
        
        <!-- franja superior -->
         <div>
        <jsp:include page="barra.jsp"/>
        </div>
                   
        <br>
        <br>
    
        <h1 align="center">Bienvenido a Casa de Subastas</h1>
        
        
   
    
    <c:if test="${error!=null}">
        <script type="text/javascript">
               alert("${error}"); 
        </script>
        <c:remove var="error" scope="session"/>
    </c:if>
        

<div class="row">
    
    <div class="large-6 medium-6 small-12 columns">
        <p class="welcome"> Opciones de Cuenta</p>
    	<p>El tipo de cuenta determina las acciones que puede realizar dependiendo si es usted un vendedor o un comprador. </p><br>
        <div class="row">
            <div class="large-8 medium-8 small-8 columns small-centered">
            <a href="#" class="button round">Articulos Comprados</a> <hr/>
            <a href="#" class="button round ">Subastas Activas</a>
            <br/>
            <br/>
            </div>
        </div> 
    </div>

        

        <div class="large-6 medium-6 small-12 columns">
            <p class="welcome"> Agregar un Nuevo Articulo</p>
            <div class="row">
                <div class="large-8 medium-8 small-8 columns small-centered">
                <a href="registraArticulo.jsp" class="button round">Agregar Articulo</a>
                </div>
            </div>
            <br/><br/>
            <p>* Al ingresar un nuevo articulo esta aceptando los terminos y condiciones de uso de la Casa de Subastas.</p>
            <br>
       </div>
    </div>
        
        <div>
            
            <div class="row">
            <div class="medium-2 large-2 columns hide-for-small-down fffblanco2">
                <ul class="side-nav">
               <li>Precio</li>
                <li><a href="servletbuscar3?buscar=Bajo">0-100</a></li>
                <li><a href="servletbuscar3?buscar=Medio">100-200</a></li>
                <li><a href="servletbuscar3?buscar=Alto">200+</a></li>
                <li class="divider"></li>
                <li>Tipo de Subasta</li>
                <li><a href="servletbuscar3?buscar=Directa">Directa</a></li>
                <li><a href="servletbuscar3?buscar=PorCentimos">Por centimos</a></li>
                <li class="divider"></li>
                <li>Vendedor(usuario)</li>
                <form method="post" action="vendedorservlet?type=user">
                <li><input type="text" name="vendedor"></li>
                <input type="submit" value="Buscar"/>
                </form>
              </ul>
                
            </div>
            
            <div class="medium-10 large-10 columns">

              <div class="row">
   
                  <div class="medium-8 large-8 columns medium-centered hide-for-small-down ">
                      
                      <br>
                      <br>
                      <br>
            </div>
                  
                  <c:forEach var="i" items="${listaM}" varStatus="Counter">
                      <c:if test="${Counter.count == (totalLista)}">
                          <div class="large-3 medium-4 small-6 columns end"> 
                      </c:if>
                      <c:if test="${Counter.count != (totalLista)}">
                          <div class="large-3 medium-4 small-6 columns"> 
                      </c:if>
                       
                              <a href="detallearticuloadmin?idarticulo=${i.articulo.idarticulo}&type=user"><img src="Imagen?id=${i.articulo.idarticulo}"></a>
                          <div class="panel">
                              <h5>${i.articulo.nombre}</h5>
                                <h6 class="subheader">${i.precioActual}</h6>
                                </div>
                        </div>
                      
                    </c:forEach>
     
                
                
                
                
                
                
                
                
                
                  
  
              </div>
            
            </div>
            
        </div>
        
        
        
        
        
         <jsp:include page="footer.jsp"/>
        
        
        
        
        
        
        
        
        
        <c:if test="${msj2!=null}">
        <script type="text/javascript">
          alert("${msj2}");
        </script>
        <c:remove var="msj2" scope="session"/>
        </c:if>
    </body>
</html>