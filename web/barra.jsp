<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">

<script src="/js/vendor/modernizr.js"></script>
            
            <c:set var="usuario" scope="session" value="${sessionScope.usuario}"/>
            <c:if test="${usuario==null}">
            <table style ="width: 100%" class="lline row" align="top">            

                <td style="width:10%">
            <!--
              <a href="home.jsp"> <img src="imagen/Logo3.png"  style= " height: 70% ; width: 100%" /> </a>
            -->
                  <a href="home.jsp"> <img src="imagen/Logo3.png" href="homeUsuario.jsp" class='show-for-small-up' style= " height:56px ; width:56px" /> </a> 
                  <!-- <a href="home.jsp"> <img src="imagen/Logo3.png" href="homeUsuario.jsp" class='show-for-small-up hide-for-large-up' style= " height: 62% ; width: 62%" /> </a>  -->
                
                
                </td>
                <td style="width:30%">
                    <!--
                    <img src="imagen/Casa.png" href="homeUsuario.jsp" style= " height: 100% ; width: 100%" />
                    -->
                    <img src="imagen/Casa.png" href="homeUsuario.jsp" class='show-for-large-up' style= " height: 50px ; width:188px" />
                    <img src="imagen/Casa.png" href="homeUsuario.jsp" class='show-for-medium-up hide-for-large-up' style= " height: 50% ; width: 55%" />

                </td>
              
                <td style="width:15%">
                    <div align ="left" class=''>
                        <form action="s01" method="post">
                <input type="text" align ="left" style= " height: 100% ; width: 100%" name="username" placeholder="Usuario" required/>
                </div>
                    </td>
                <td style="width:15%">
                <div align ="left" class=''>
                <input type="password" align ="left"   style= " height: 100% ; width: 100%" name="password" placeholder="Password" required/>
                </div></td>
                <td style="width:10%">
                    <div align ="left" class=''>
                        <input type="submit"  class="button round " style= " height: 100% ; width: 100%" value="Login"/>
                </form>
                </div>
                </td>
                <td style="width:10%">
                <div align ="left" class=''>
                <a href="registrate.jsp" class="button round " style= " height: 10% ; width: 100%">Registrate</a>
                 </div>
                </td>
             
            </table>
            </c:if>
            <c:if test="${usuario!=null}">
        <table style ="width: 100%" class="lline row" align="top">            

                <td style="width:10%">
                    <!--
                <a href="homeUsuario.jsp"> <img src="imagen/Logo3.png" href="homeUsuario.jsp" style= " height: 3% ; width: 3%" /> </a> 
                    -->
                  <a href="homeUsuario.jsp"> <img src="imagen/Logo3.png" href="homeUsuario.jsp" class='show-for-small-up' style= " height:56px ; width:56px" /> </a> 
                  <!-- <a href="homeUsuario.jsp"> <img src="imagen/Logo3.png" href="homeUsuario.jsp" class='show-for-small-up hide-for-large-up' style= " height: 62% ; width: 62%" /> </a>  -->
                
                </td>
                <td style="width:30%">
                    <!--
                    <img src="imagen/Casa.png" href="homeUsuario.jsp" style= " height: 100% ; width: 100%" />
                    -->
                    <img src="imagen/Casa.png" href="homeUsuario.jsp" class='show-for-large-up' style= " height: 50px ; width:188px" />
                    <img src="imagen/Casa.png" href="homeUsuario.jsp" class='show-for-medium-up hide-for-large-up' style= " height: 50% ; width: 55%" />
                    
                </td>
          
                <td style="width:45%">
                    <div align ="center" class="tt hide-for-small-down">
                        <c:out value="Bienvenido,   "/><c:out value="${usuario.nombre}"/>
                </div>
                    </td>
                
                <td style="width:10%">
                    <div align ="left">
                 <a href="s02"  class="button round " style= " height: 100% ; width: 100%">Log Out</a> 
               
              <!--
                 <button href="#" data-dropdown="drop1" aria-controls="drop1" aria-expanded="false" class="button dropdown"><i class="fa fa-cog fa-2x"></i></button><br>
                   <ul id="drop1" data-dropdown-content class="f-dropdown" aria-hidden="true" tabindex="-1">
                        <li><a href="#">Perfil</a></li>
                        <li><a href="/CasaSubastas2/s02">Log out</a></li>
                    </ul>
                 -->
                 
                 <!--
               
                   <ul class="left">
                        <div class="has-dropdown">
                            <a href="#"><i class="fa fa-cog fa-2x"></i></a>
                         <ul class="dropdown">
                             <li><a href="#">Perfil</a></li>
                             <li><a href="/CasaSubastas2/s02">Log out</a></li>
                         </ul>
                        </div>
                   </ul>
              -->
              
               
               
               

                </div>
                </td>
                <td style="width:5%">
                <div align ="left">
                
                 </div>
                </td>
            </table>
                                  
                </c:if>

            
            
            <script src="/js/vendor/jquery.js"></script> <script src="/js/vendor/fastclick.js"></script>
            <script src="/js/foundation.min.js"></script>
            
            <script> $(document).foundation(); </script>