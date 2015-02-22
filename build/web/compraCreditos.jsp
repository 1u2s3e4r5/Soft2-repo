<%-- 
    Document   : compraCreditos.jsp
    Created on : Feb 11, 2015, 8:31:16 AM
    Author     : v5103
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <c:set var="cred" scope="session" value="${sessionScope.cred}"/>

<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/foundation.css"/>
        <link rel="stylesheet" href="css/foundation.min.css"/>
        <link rel="stylesheet" href="css/normalize.css"/>
        <link rel="stylesheet" href="css/main2.css"/>
        <title>JSP Page</title>
    </head>
    <body>
         <!-- franja superior -->    
        <div>
        <jsp:include page="barra.jsp"/>
        </div>
                   
 <!-- FIN Header-->  
 <br><br>
 
 <div class="row">
  <div class="small-6 large-8 columns medium-centered"> 
      <p class="panel">
            <strong class="show-for-medium-up">Muchas gracias por usar nuestros servicios. A continuacion ingrese sus datos personales de su tarjeta de preferencia para proceder con la compra de Creditos de Casa de Subastas. El precio por Credito es de 1 sol por Crédito</strong>
      </p>
  </div>
</div>

 <br>
 <!-- asdasdasaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa -->
 <div class="large-7 medium-centered columns ">
 <form method="post" action="s05">
     <div class="row">
    <div class="large-4 columns">
      <label>Nombre del Propietario
        <input type="text" placeholder="Nombres" />
      </label>
    </div>
    <div class="large-4 columns">
      <label>Apellidos del Propietario
        <input type="text" placeholder="Apellidos" />
      </label>
    </div>
    <div class="large-4 columns">
      <div class="row collapse">
        <label>Codigo de seguridad</label>
        <div class="small-3 columns">
          <input type="text" placeholder="" />
        </div>
      </div>
    </div>
  </div>
  <div class="row">
    <div class="large-12 columns">
      <label>Ingrese numero de tarjeta:
        <input type="text" placeholder="xxxx-xxxx-xxxx-xxxx" />
      </label>
    </div>
  </div>
  <div class="row">
    <div class="large-12 columns">
      <label>Tarjeta de credito:
        <select>
          <option value="VISA">VISA</option>
          <option value="MasterCard">MasterCard</option>
        </select>
      </label>
    </div>
  </div>
  <div class="row">
 <div class="large-4 columns">
      <div class="row collapse">
        <label>Creditos a Comprar</label>
        <div class="small-3 columns">
          <input type="text" placeholder="" name="creditos"/>
        </div>
      </div>
    </div>
  </div>
     <div class="row">
              <div class="large-4 columns">
                <input type ="submit" value ="Comprar" class="postfix button right-align expand"/>
                </form>
              </div>
     </div>
</form>
 </div>
 
  <c:if test="${cred!=null}">
        <script type="text/javascript">
          alert("${cred}");
        </script>
        <c:remove var="cred" scope="session"/>
        </c:if>
 
    </body>
</html>
