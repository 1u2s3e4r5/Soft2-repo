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
        <script src="js/vendor/modernizr.js"></script>
    </head>
    <body>   
    
    
        
        <!-- franja superior -->
         <div>
        <jsp:include page="barra.jsp"/>
        </div>
        
         <% 
       
        if (u.getTipo().equalsIgnoreCase("Admin")){
        response.sendRedirect("homeAdmin.jsp");
        }else{
        
        HttpSession ses = request.getSession(true);
        ConexionDAO dao = new ConexionDAO();
        
        List<Subasta> lista = (List) ses.getAttribute("lista");
        if(lista == null){
            ses.setAttribute("listaM", dao.retornarTodasLosSubastasDisponibles());
        }else{
            ses.setAttribute("listaM", lista);
            ses.setAttribute("lista",null);
        }
        List<Subasta> listaP = dao.retornarSubastasPorUserTodos(u.getUsuario());
                   ses.setAttribute("listaP", listaP);
     
        }
        }
        %>
        <c:set var="totalLista" value="${fn:length(listaM)}" />
        <c:set var="totalListaP" value="${fn:length(listaP)}" />
        <c:set var="msj" scope="session" value="${sessionScope.msj}"/>
         <c:set var="cred" scope="session" value="${sessionScope.cred}"/>
        <c:set var="msjOferta" scope="session" value="${sessionScope.msjOferta}"/>
        <c:set var="msj2" scope="session" value="${sessionScope.msj2}"/>
        <c:set var="error" scope="session" value="${sessionScope.error}"/>
        <c:if test="${error!=null}">
        <script type="text/javascript">
               alert("${error}"); 
        </script>
        <c:remove var="error" scope="session"/>
        </c:if>
               <h1 align="center">Bienvenido a Casa de Subastas</h1>
               <br>
        <!-- INICIO DE LOS TABS -->     
        <ul class="tabs" data-tab>
  <li class="tab-title active"><a href="#panel11">Consultar Subastas</a></li>
  <li class="tab-title"><a href="#panel21">Registrar Articulo</a></li>
  <li class="tab-title"><a href="#panel31">Comprar Creditos</a></li>
  <li class="tab-title"><a href="#panel41">Mis Artículos</a></li>
  <li class="tab-title"><a href="#panel51">Novedades</a></li> 
</ul>
<div class="tabs-content">
  <div class="content active" id="panel11">
      <!-- Inicio Content 1-->
     
     
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
                      
                      <dl class="sub-nav medium-centered fffblanco">
                      <dt>Filter:</dt>
                <dd class="active"><a href="servletbuscar3?buscar=All">All</a></dd>
                <dd><a href="servletbuscar3?buscar=Activas">Activas</a></dd>
                <dd><a href="servletbuscar3?buscar=NoIniciadas">No Iniciadas</a></dd>
                 <dd><a href="servletbuscar3?buscar=PorIniciar">Por Iniciar</a></dd>
                      </dl>
            </div>
                  
                  <c:forEach var="i" items="${listaM}" varStatus="Counter">
                      <c:if test="${Counter.count == (totalLista)}">
                          <div class="large-3 medium-4 small-6 columns end"> 
                      </c:if>
                      <c:if test="${Counter.count != (totalLista)}">
                          <div class="large-3 medium-4 small-6 columns"> 
                      </c:if>
                       
                              <a href="detallearticuloadmin?idarticulo=${i.idsubasta}&type=user"><img src="Imagen?id=${i.idsubasta}" style="width: 100%;"></a>
                          <div class="panel">
                              <h5>${i.articulo.nombre}</h5>
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
                        </c:if>    
                        </div>
                               </div>
                      
                    </c:forEach>
     
                
                
                
                
                
                
                
                
                
                  
  
              </div>
            
            </div>
            
        </div>
        
            </div>
     <!-- Fin Content 1-->
  

  <div class="content" id="panel21">
      <!-- Inicio Content 2-->
       
       
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
       
       
       
    <!-- Fin Content 2-->
  </div>
  <div class="content" id="panel31">
      <!-- Inicio Content 3-->
        
       
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
         
         
         
         
         <!-- Fin Content 3-->
  </div>
  <div class="content" id="panel41">
      <!-- Inicio Content 4-->
      <div class="row">
            
            
            <div class="medium-11 large-11 columns medium-centered">

              <div class="row">
   
                 
                  
                  <c:forEach var="i" items="${listaP}" varStatus="Counter">
                      <c:if test="${Counter.count == (totalListaP)}">
                          <div class="large-3 medium-4 small-6 columns end"> 
                      </c:if>
                      <c:if test="${Counter.count != (totalListaP)}">
                          <div class="large-3 medium-4 small-6 columns"> 
                      </c:if>
                       
                              <a href="detallearticuloadmin?idarticulo=${i.idsubasta}&type=propio"><img src="Imagen?id=${i.idsubasta}" style="width: 100%;"></a>
                          <div class="panel">
                              <h5>${i.articulo.nombre}</h5>
                                <h6>Tipo Subasta: ${i.articulo.tipo}</h6>
                              <h6 class="subheader">Precio Base</h6>
                            <h6 class="subheader">${i.articulo.precioBase}</h6>
                            
                            
                           
                            <h6 class="subheader">Precio Actual</h6>
                            <h6 class="subheader">${i.precioActual}</h6>
                            
                            <h6 class="subheader">Estado:</h6>
                            <h6 class="subheader">${i.estado}</h6>
                            <c:if test="${i.fechaInicio != null}">
                         <h6 class="subheader">Fecha Inicio:</h6>
                            <h6 class="subheader">${i.fechaInicio}</h6>
                        </c:if>
                            <c:if test="${i.fechaFin != null}">
                         <h6 class="subheader">Fecha Fin:</h6>
                            <h6 class="subheader">${i.fechaFin}</h6>
                        </c:if> 
                        </div>
                               </div>
                      
                    </c:forEach>
     
                
                
                
                
                
                
                
                
                
                  
  
              </div>
            
            </div>
            
        </div>
        
            </div>
    <!-- Fin Content 4-->
    
  <div class="content" id="panel51">
      <!-- Inicio Content 5-->
      <div class="row">
          
            
            <div class="medium-11 large-11 columns medium-centered">

              <div class="row">
   
                  <div class="medium-8 large-8 columns medium-centered hide-for-small-down ">
                      
                      <dl class="sub-nav medium-centered fffblanco">
                      <dt>Filter:</dt>
                <dd class="active"><a href="servletbuscar3?buscar=All">All</a></dd>
                <dd><a href="servletbuscar3?buscar=Activas">Activas</a></dd>
                <dd><a href="servletbuscar3?buscar=NoIniciadas">No Iniciadas</a></dd>
                      </dl>
            </div>
                  
                  
              </div>
            
            </div>
            
        </div>
        
            
    <!-- Fin Content 5-->
    </div>
    
  
  
     
     
</div>
    
        <!-- FIN DE LOS TABS -->           
        <br>
        <br>
    
        
         <jsp:include page="footer.jsp"/>
        
        
        
        
        
        
        
              <c:if test="${msj!=null}">
        <script type="text/javascript">
          alert("${msj}");
        </script>
        <c:remove var="msj" scope="session"/>
        </c:if>
        
        <c:if test="${msj2!=null}">
        <script type="text/javascript">
          alert("${msj2}");
        </script>
        <c:remove var="msj2" scope="session"/>
        </c:if>
         <c:if test="${cred!=null}">
        <script type="text/javascript">
          alert("${cred}");
        </script>
        <c:remove var="cred" scope="session"/>
        </c:if>
        <c:if test="${msjOferta!=null}">
        <script type="text/javascript">
          alert("${msjOferta}");
          
        </script>
        <c:remove var="msjOferta" scope="session"/>
        </c:if>
        <script src="js/vendor/jquery.js"></script>
        <script src="js/vendor/fastclick.js"></script>
        <script src="js/foundation.min.js"></script>
        <script src="js/foundation/foundation.js"></script>
        <script src="js/foundation/foundation.tab.js"></script>
<script>
  $(document).foundation();
</script>
        
    </body>
</html>