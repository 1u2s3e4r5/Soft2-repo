
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <c:set var="error" scope="session" value="${sessionScope.error}"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">        
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
       <!-- <link rel="stylesheet" href="css/normalize.css"/> -->
        <link rel="stylesheet" href="css/main2.css"/>
        <link rel="stylesheet" href="css/foundation-icons/foundations-icons.css"/>
        <link rel="stylesheet" href="css/foundation.css" /> 
        <link href='http://fonts.googleapis.com/css?family=Bevan' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:800,400' rel='stylesheet' type='text/css'>
        
        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">

        
       
       
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       
    </head>
    <body class="bb">
        
            <script src="js/foundation.js"></script>
           
            <script src="js/foundation.tab.js"></script>
        <div>
        <jsp:include page="barra.jsp"/>
        </div>
           
    
        <table style ="width: 100%">
            <td style="width:15%"></td>
            <td style="width:70%">
        <div class="center row fffblanco"  >
        <div class="section-container tabs" data-section="tabs">
        <section class="active">
        <p class="title" data-section-title><a href="#">Registrar Nuevo Usuario</a></p>
      <div class="content" data-section-content>
        <p>
          <div class="row">
            <div class="small-12 columns">
              <div class="signup-panel">
                <p class="welcome">¡Bienvenido a Casa de Subastas!</p>
                <form action="s04" method="post">
                  
                    <div class="row collapse">        
                    <div class="small-2 small-push-4 columns">
                      <span class="prefix"><i class="fa fa-quote-right fa-2x nada"></i></span>
                    </div>
                    <div class="small-6  columns">
                      <input type="text" name ="DNI" placeholder="DNI" required>
                    </div>  
                          
                          
                     
                  </div>
                    <div class="row collapse">
                        <!--  <div class="small-4 columns"><span class="ocupa"></span></div>  -->
                    <div class="small-2 small-push-4 columns">
                      <span class="prefix"><i class="fa fa-male fa-2x nada"></i></span>
                    </div>
                    <div class="small-6  columns">
                      <input type="text" name ="Nombre" placeholder="Nombre" required>
                    </div>
                  </div>
                  <div class="row collapse">
                    <div class="small-2 small-push-4 columns">
                      <span class="prefix"><i class="fa fa-users fa-2x nada"></i></span>
                    </div>
                    <div class="small-6  columns">
                      <input type="text" name= "Apellido" placeholder="Apellido" required>
                    </div>
                  </div>
                  <div class="row collapse">
                    <div class="small-2 small-push-4 columns ">
                      <span class="prefix"><i class="fa fa-user fa-2x nada"></i></span>
                    </div>
                    <div class="small-6 columns ">
                      <input type="text" name= "User" placeholder="User" required>
                    </div>
                  </div>
                  <div class="row collapse">
                    <div class="small-2 small-push-4 columns ">
                      <span class="prefix"><i class="fa fa-unlock fa-2x nada"></i></span>
                    </div>
                    <div class="small-6 columns ">
                      <input type="password"  name= "Password"placeholder="Password" required>
                    </div>
                  </div>
                 <input type="submit" class="button small-push-5" value="Registrarse"/>
                </form>
                
              </div>
            </div>
           </div></p>
      </div>
    </section>
    
  </div>
        </div>    </td>
            <td style="width:15%"></td>
        </table>

        <c:if test="${error!=null}">
        <script type="text/javascript">
          alert("${error}");
        </script>
        <c:remove var="error" scope="session"/>
        </c:if>
    </body>
</html>
