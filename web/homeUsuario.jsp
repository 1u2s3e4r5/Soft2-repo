<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
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
        
        <c:if test="${msj2!=null}">
        <script type="text/javascript">
          alert("${msj2}");
        </script>
        <c:remove var="msj2" scope="session"/>
        </c:if>
    </body>
</html>