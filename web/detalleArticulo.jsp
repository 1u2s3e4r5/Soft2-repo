<%-- 
    Document   : detalleArticulo.jsp
    Created on : 8/02/2015, 09:27:20 PM
    Author     : Diego Nano A
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/foundation.css"/>
        <link rel="stylesheet" href="css/foundation.min.css"/>
        <link rel="stylesheet" href="css/normalize.css"/>
        <link rel="stylesheet" href="css/main2.css"/>
        <script src="js/vendor/modernizr.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
            <!-- franja superior -->    
        <div>
        <jsp:include page="barra.jsp"/>
        </div>
                   
        <br>
        <br>         

        <div class="row">
        <div class="large-3 columns left">
          <h1><img src="http://placehold.it/400x100&text=Logo"></h1>
        </div>
        <div class="large-9 columns right">
          <form>

              <div class="large-10 small-8 columns">
                  <br><br>
                         <input type="text" placeholder="Buscar otros articulos" />
              </div>

              <div class="large-2 small-4 columns">
                  <br>
                  <br>
                <a href="#" class="postfix button expand">Search</a>
              </div>
          </form>

        </div>
        </div>
 <!-- FIN Header-->   
        
        <div class="row">
        <hr>
         <!-- primera seccion -->   
        <div class="large-2 columns">
          <img src="http://placehold.it/400x300&text=[img]">
          <br>
          <img src="http://placehold.it/400x300&text=[img]">
          <br>
          <img src="http://placehold.it/400x300&text=[img]">
          <br>
          <img src="http://placehold.it/400x300&text=[img]">
        </div>
         <!-- segunda seccion -->   
        <div class="large-5 columns">
          <img src="http://placehold.it/400x500">
        </div>
          <!-- tercera seccion -->   
        <div class="large-5 columns">
            
            <div class="off-canvas-wrap" data-offcanvas>
                  <div class="inner-wrap">
                      <nav class="tab-bar">
                         <section class="left-small">
                               <a class="left-off-canvas-toggle menu-icon" href="#"><span></span></a>
                           </section>

                           <section class="middle tab-bar-section">
                               <h1 class="title">Foundation</h1>
                          </section>
                      </nav>

              <aside class="left-off-canvas-menu">
                 <ul class="off-canvas-list">
                   <li><label>Foundation</label></li>
                     <li><a href="#">The Psychohistorians</a></li>
                     <li><a href="#">...</a></li>
                 </ul>
              </aside>

         <section class="main-section">
          <p>Descripcion del producto blablablablabla y mas blablablablabla Bacon ipsum dolor sit amet nulla ham qui sint exercitation eiusmod commodo, chuck duis velit. Aute in reprehenderit, dolore aliqua non est magna in labore pig pork biltong. Eiusmod swine spare ribs reprehenderit culpa. Boudin aliqua adipisicing rump corned beef.</p>
          <div class="panel">
            <h5>Header</h5>
          <h6 class="subheader">Praesent placerat dui tincidunt elit suscipit sed.</h6>
          <a href="#" class="small button left-off-canvas-toggle">Ofertar</a>
          </div>
          </section>

            <a class="exit-off-canvas"></a>

         </div>
         </div>
            


        </div>
        </div>
         

        <div class="row">
        <hr>
        <div class="large-2 columns">
          <img src="http://placehold.it/400x300&text=[img]">
          <br>
          <img src="http://placehold.it/400x300&text=[img]">
          <br>
          <img src="http://placehold.it/400x300&text=[img]">
          <br>
          <img src="http://placehold.it/400x300&text=[img]">
        </div>
        <div class="large-5 columns">
          <img src="http://placehold.it/400x500">
        </div>
        <div class="large-5 columns">
          <h4>This is a content section.</h4>
          <p>Bacon ipsum dolor sit amet nulla ham qui sint exercitation eiusmod commodo, chuck duis velit. Aute in reprehenderit, dolore aliqua non est magna in labore pig pork biltong. Eiusmod swine spare ribs reprehenderit culpa. Boudin aliqua adipisicing rump corned beef.</p>

          <div class="panel">
            <h5>Header</h5>
          <h6 class="subheader">Praesent placerat dui tincidunt elit suscipit sed.</h6>
         <a href="#" class="small button" data-dropdown="drop2" aria-controls="drop2" aria-expanded="false">Ofertar</a>
                <div id="drop2" data-dropdown-content class="f-dropdown content" aria-hidden="true" tabindex="-1">
                     <p>Some text that people will think is awesome! Some text that people will think is awesome! Some text that people will think is awesome!</p>
                               <form>

                                     <div class="large-4 columns">
                                     <a href="#" class="button">Enviar</a>
                                     </div>
                               </form>
                </div>
          
          </div>

        </div>
        </div>

        
        
        <div class="row">
        <hr/>
        <div class="large-6 columns">
          <h4>This is a content section.</h4>
          <p>Bacon ipsum dolor sit amet nulla ham qui sint exercitation eiusmod commodo, chuck duis velit. Aute in reprehenderit, dolore aliqua non est magna in labore pig pork biltong. Eiusmod swine spare ribs reprehenderit culpa. Boudin aliqua adipisicing rump corned beef.</p>
        </div>
        <div class="large-6 columns">
          <h4>This is a content section.</h4>
          <p>Pork drumstick turkey fugiat. Tri-tip elit turducken pork chop in. Swine short ribs meatball irure bacon nulla pork belly cupidatat meatloaf cow. Nulla corned beef sunt ball tip, qui bresaola enim jowl. Capicola short ribs minim salami nulla nostrud pastrami.</p>
        </div>
        </div>

        <div class="row">
        <hr>
        <div class="large-12 columns">
          <h4>You might also like:</h4>
          <img src="http://placehold.it/200x150&text=[img]">
          <img src="http://placehold.it/200x150&text=[img]">
          <img src="http://placehold.it/200x150&text=[img]">
          <img src="http://placehold.it/200x150&text=[img]">
        </div>
        </div>

        <footer class="row">
        <div class="large-12 columns">
          <hr>
          <div class="row">
            <div class="large-6 columns">
              <p>© Copyright no one at all. Go to town.</p>
            </div>
            <div class="large-6 columns">
              <ul class="inline-list right">
                <li><a href="#">Link 1</a></li>
                <li><a href="#">Link 2</a></li>
                <li><a href="#">Link 3</a></li>
                <li><a href="#">Link 4</a></li>
              </ul>
            </div>
          </div>
        </div>
        </footer>
    
    
        <script src="js/vendor/jquery.js"></script>
        <script src="js/vendor/fastclick.js"></script>
        <script src="js/foundation.min.js"></script>
        <script src="js/foundation/foundation.js"></script>
         <script src="js/foundation/foundation.offcanvas.js"></script>
         <script src="js/foundation/foundation.dropdown.js"></script>

<script>
  $(document).foundation();
</script>
    
    </body>
</html>
