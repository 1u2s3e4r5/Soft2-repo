
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
    
        <h1 align="center">Artículos</h1>
        
        
        <!-- lista de articulos  -->
        <!-- Thumbnails -->
        <div class="row">
        
        <div class="medium-2 large-2 columns hide-for-small-down fffblanco2">
                <ul class="side-nav">
                <li><a href="#">Precio</a></li>
                <li><a href="#">Muy Barato</a></li>
                <li><a href="#">Barato</a></li>
                <li><a href="#">Justo</a></li>
                <li><a href="#">Ajustado</a></li>
                <li><a href="#">Caro</a></li>
                <li><a href="#">Muy Caro</a></li>
                <li class="divider"></li>
                <li><a href="#">Tipo de Subasta</a></li>
                <li><a href="#">Directa</a></li>
                <li><a href="#">Por centimos</a></li>
                <li class="divider"></li>
                <li><a href="#">Vendedor</a></li>
                <li><input type="text" name="vendedor"></li>
              </ul>
                
            </div>
        
        
        <div class="medium-10 large-10 columns">
        
        <div class="row">
            
            <div class="medium-8 large-8 columns medium-centered hide-for-small-down">
            <dl class="sub-nav medium-centered fffblanco">
                <dt>Filter:</dt>
                <dd class="active"><a href="#">All</a></dd>
                <dd><a href="#">Activas</a></dd>
                <dd><a href="#">No Iniciadas</a></dd>
                <dd><a href="#">Destacadas</a></dd>
                <dd><a href="#">Finalizadas</a></dd>
                <!-- <dd class-"hide-for-small-only"><a href="#">Suspended</a></dd>-->
              </dl>
            </div>
            
            
            
            
            
        </div>
     
              <div class="row">
     
                <div class="large-3 medium-4 small-6 columns">
                  <img src="http://placehold.it/1000x1000&text=Thumbnail">
     
                  <div class="panel">
                    <h5>Item Name</h5>
                    <h6 class="subheader">$000.00</h6>
                  </div>
                </div>
     
                <div class="large-3 medium-4 small-6 columns">
                  <img src="http://placehold.it/500x500&text=Thumbnail">
     
                  <div class="panel">
                    <h5>Item Name</h5>
                    <h6 class="subheader">$000.00</h6>
                  </div>
                </div>
     
                <div class="large-3 medium-4 small-6 columns">
                  <img src="http://placehold.it/500x500&text=Thumbnail">
     
                  <div class="panel">
                    <h5>Item Name</h5>
                    <h6 class="subheader">$000.00</h6>
                  </div>
                </div>
     
                <div class="large-3 medium-4 small-6 columns">
                  <img src="http://placehold.it/500x500&text=Thumbnail">
     
                  <div class="panel">
                    <h5>Item Name</h5>
                    <h6 class="subheader">$000.00</h6>
                  </div>
                </div>
     
                <div class="large-3 medium-4 small-6 columns">
                  <img src="http://placehold.it/500x500&text=Thumbnail">
     
                  <div class="panel">
                    <h5>Item Name</h5>
                    <h6 class="subheader">$000.00</h6>
                  </div>
                </div>
     
                <div class="large-3 medium-4 small-6 columns">
                  <img src="http://placehold.it/500x500&text=Thumbnail">
     
                  <div class="panel">
                    <h5>Item Name</h5>
                    <h6 class="subheader">$000.00</h6>
                  </div>
                </div>
                  
                  <div class="large-3 medium-4 small-6 columns">
                  <img src="http://placehold.it/500x500&text=Thumbnail">
     
                  <div class="panel">
                    <h5>Item Name</h5>
                    <h6 class="subheader">$000.00</h6>
                  </div>
                </div>
                  
                  <div class="large-3 medium-4 small-6 columns">
                  <img src="http://placehold.it/500x500&text=Thumbnail">
     
                  <div class="panel">
                    <h5>Item Name</h5>
                    <h6 class="subheader">$000.00</h6>
                  </div>
                </div>
                  
                  
                  <!-- colocar la etiqueta 'end' en el último artículo  -->
                  <div class="large-3 medium-4 small-6 columns end">
                  <img src="http://placehold.it/500x500&text=Thumbnail">
     
                  <div class="panel">
                    <h5>Item Name</h5>
                    <h6 class="subheader">$000.00</h6>
                  </div>
                </div>
                  
  
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
