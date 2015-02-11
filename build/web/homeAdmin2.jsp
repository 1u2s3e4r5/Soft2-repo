
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
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
    
        <h1 align="center">Iniciar Subasta (paso 2)</h1>
        
        
        <!-- lista de articulos  -->
        <!-- Thumbnails -->
     
        <form action="servletiniciar2" method="post">
              <div class="row">
     
                 <div class="row">
                    
                    <div class="small-6 columns">
                        <p align="right"><font size=+2>Fecha</font></p>
                    </div>
                        <div class="small-6 columns ela-left">
                           <input type="date" name="fecha" required ></div>
                        <!--    <input type="text" name="descripcion" required size="20px">    -->
                        </div>
                </div>     
                  
                  <div class="row">
                    
                    <div class="small-6 columns">
                        <p align="right"><font size=+2>Hora Inicio</font></p>
                    </div>
                        <div class="small-6 columns ela-left">
                           <input type="time" name="hora" required ></div>
                        <!--    <input type="text" name="descripcion" required size="20px">    -->
                        </div>
                </div>     
                
                
                  
                  
                  
                  
               
                  
  
              </div>
                <div class="row">
                    <div class="small-4 medium-4 large-4 columns large-centered medium-centered small-centered">
                        <center> <input type ="submit" value ="Confirmar subasta" class="button round"/></center>
                    </div>
                </div>
              </form>
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
