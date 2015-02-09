<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <c:set var="msj" scope="session" value="${sessionScope.msj}"/>
        <title>Registro de articulo</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">        
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <link rel="stylesheet" href="css/foundation.css"/>
        <link rel="stylesheet" href="css/normalize.css"/>
        <link rel="stylesheet" href="css/main2.css"/>
        <link href='http://fonts.googleapis.com/css?family=Bevan' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:800,400' rel='stylesheet' type='text/css'>
        
    </head>
    
    <body class="registro">  <!--estilo a revisar -->
        <div>
        <jsp:include page="barra.jsp"/>
        </div>
        
        <br><br>
    <center>
        <div class="row">
            <form action="s03" method="POST" enctype="multipart/form-data">    <!--servlet a implementar -->          
                <div class="row">
                        <div class="small-12 columns">
                                    <!--estilo a revisar -->
                            <center class="enzo"><h1 class="titulo">Registro de Artículo</h1></center>
                        
                        </div>
                    </div>
                
                <br>    
                <div class="row">
                    
                    <div class="small-6 columns">
                        <p align="right"><font size=+2>Nombre:</font></p></div>
                        <div class="small-6 columns ela-left">
                        <input type="text" name="nombre" required size="50"></div>
                </div>
                
                
                <div class="row">
                    
                    <div class="small-6 columns">
                        <p align="right"><font size=+2>Descripción:</font></p>
                    </div>
                        <div class="small-6 columns ela-left">
                            <textarea name="descripcion" rows="6" cols="50" required></textarea>
                        <!--    <input type="text" name="descripcion" required size="20px">    -->
                        
                        </div>
                </div>     
                    
                <div class="row">
                    <div class="small-6 columns">
                        <p align="right"><font size=+2>Foto (formato JPEG):</font></p></div>
                    <div class="small-6 columns ela-left">
                        <input type="file" class="ela" name="foto" required></div>  <!--cambiar leyenda de 'Choose File' -->
                    <!--<div class="small-2 columns"> **comentario de success or not*</div>-->
                </div>     
                    
                <div class="row">
                    <div class="small-6 columns">   
                        <p align="right"><font size=+2>Precio base (Soles):</font></p></div>
                    <div class="small-6 columns ela-left">
                        <input type="text" name="preciob" required></div>
                   <!-- <div class="small-2 columns">**en soles</div>-->
                </div>     
                    
                    
                <div class="row">
                    <div class="small-6 columns">  
                        <p align="right"><font size=+2>Tipo de subasta:</font></p></div>
                    <div class="small-6 columns ela-left">
                        
                            <!--  <input type="radio" name="tipos" required> <input type="radio" name="tipos" required>  -->
                            <input type="radio" name="tipos" value="Por Centimos" required>por centimos <input type="radio" name="tipos" value ="Directa" required> directa
                    
                    </div>
                    <!--<div class="small-2 columns">**comentario activado sobre la opcion marcada***</div>-->
                </div>     
            
                
                <div class="row">
                    <div class="small-12 columns"> ***Se acepta los términos y condiciones de la empresa </div>
                </div>
                    
                <div class="row"> 
                    <div class="small-12 columns ele"><center><button type="submit">Registrar Ahora</button></center>
                    </div>
                </div>    
                
               
                
            </form>
        </div>
        </center>
        
        <c:if test="${msj!=null}">
        <script type="text/javascript">
          alert("${msj}");
        </script>
        <c:remove var="msj" scope="session"/>
        </c:if>
    </body>
    
</html>
