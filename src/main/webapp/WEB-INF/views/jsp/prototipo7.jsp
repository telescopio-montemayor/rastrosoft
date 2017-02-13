<%@page session="false"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>RastroSoft Test</title>

<c:url var="home" value="/" scope="request" />


<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />

<spring:url value="/resources/core/css/menu.css" var="menuCss" />
<link href="${menuCss}" rel="stylesheet" />

<spring:url value="/resources/core/js/jquery.1.10.2.min.js"
	var="jqueryJs" />
<script src="${jqueryJs}"></script>

<spring:url value="/resources/core/js/bootstrap-notify.min.js"
	var="bootstrapNotify" />
<script src="${bootstrapNotify}"></script>

<spring:url value="/resources/core/js/menu.js"
	var="menuJs" />
<script src="${menuJs}"></script>

<link rel="stylesheet" href="/font-awesome/css/font-awesome.min.css">
<spring:url value="/resources/core/font-awesome-4.7.0/css/font-awesome.min.css" var="fontawesomeCss" />
<link href="${fontawesomeCss}" rel="stylesheet" />

<script src="https://code.jquery.com/color/jquery.color.plus-names-2.1.2.min.js" integrity="sha256-Wp3wC/dKYQ/dCOUD7VUXXp4neLI5t0uUEF1pg0dFnAE=" crossorigin="anonymous"></script>

<spring:url value="/resources/core/js/ajaxFunctions.js"
	var="ajaxFunctions" />
<script src="${ajaxFunctions}"></script>
<spring:url value="/resources/core/js/notifyFunctions.js"
	var="notifyFunctions" />
<script src="${notifyFunctions}"></script>

<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>

<script>
	jQuery(document).ready(function($) {                
                listaDispositivos();
		$("#search-form").submit(function(event) {

			// Disble the search button
			enableSearchButton(false);

			// Prevent the form from submitting via the browser.
			event.preventDefault();

			searchViaAjax();

		});

	});
        
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
    
        function sendAjax(search, funcion, tipo){
            $.ajax({
                type : "POST",
                contentType : "application/json",
                url : "${home}"+funcion+"",
                data : JSON.stringify(search),
                dataType : 'json',
                timeout : 100000,
                 beforeSend: function(xhr) {
                    // here it is
                    xhr.setRequestHeader(header, token);
                },
                success : function(list) {
                        console.log("SUCCESS: ", list);
                        displayTipo(list, tipo);
                },
                error : function(e) {
                        console.log("ERROR: ", e);
                },
                done : function(e) {
                        console.log("DONE");
                        enableSearchButton(true);
                }
            });
        }

        function cine(){
            $('body').toggleClass('cine');
            $('#sidebarLeft').toggleClass('open');
            $('#sidebarRight').toggleClass('open');
            $('#cine-button').toggleClass('white');
        }
        

</script>

</head>

<body>
    <div class="sidebar-toggle-left">
    <button class="btn btn-primary left-btn-toggle">
        <i class="fa fa-bars" aria-hidden="true"></i>
    </button>
    </div>    
    <div class="col-md-2">
       <!-- Material sidebar -->
        <aside id="sidebarLeft" class="sidebar sidebar-default sidebar-stacked" role="navigation">
            <!-- Sidebar header -->
           
                <!-- Top bar -->
                <div class="top-bar label-primary">
                    <button class="icon left-btn-toggle-inside" onclick="toggle_left();">
                        <i class="fa fa-bars" aria-hidden="true" ></i>
                    </button>                    
                    <span>Opciones</span>
                </div>
                <div class="logout">
                    <c:url var="logoutUrl" value="/logout"/>
                    <form class="form-inline" action="${logoutUrl}" method="post">                      
                      <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-sign-out fa-fw"></i></span>
                        <input class="form-control logout-icon" type="submit" value="Cerrar sesion"/>
                      </div>
                      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                    <button onClick="test();" class="btn btn-default">TEST</button>
                </div>                       
                <form class="small sidebar-inside">
                    <div class="form-group">
                      <label for="exampleInputEmail1">Email address</label>
                      <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email">
                    </div>
                    <div class="form-group">
                      <label for="exampleInputPassword1">Password</label>
                      <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                    </div>
                    <div class="form-group">
                      <label for="exampleInputFile">File input</label>
                      <input type="file" id="exampleInputFile">
                      <p class="help-block">Example block-level help text here.</p>
                    </div>
                    <div class="checkbox">
                      <label>
                        <input type="checkbox"> Check me out
                      </label>
                    </div>
                     <div class="form-group">
                      <label for="exampleInputEmail1">Email address</label>
                      <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email">
                    </div>
                    <div class="form-group">
                      <label for="exampleInputPassword1">Password</label>
                      <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                    </div>
                    <div class="form-group">
                      <label for="exampleInputFile">File input</label>
                      <input type="file" id="exampleInputFile">
                      <p class="help-block">Example block-level help text here.</p>
                    </div>
                    <div class="checkbox">
                      <label>
                        <input type="checkbox"> Check me out
                      </label>
                    </div>
                    
                            
                    <div class="form-group">
                       <select class="form-control input-sm">
                        <option disabled selected value> -- select an option -- </option>   
                        <option>Luna</option>
                        <option>Júpiter</option>
                        <option>Marte</option>
                        <option>Saturno</option>
                        <option>Plutón</option>
                        <option>Luna</option>
                        <option>Júpiter</option>
                        <option>Marte</option>
                        <option>Saturno</option>
                        <option>Plutón</option>
                        <option>Luna</option>
                        <option>Júpiter</option>
                        <option>Marte</option>
                        <option>Saturno</option>
                        <option>Plutón</option>
                        <option>Luna</option>
                        <option>Júpiter</option>
                        <option>Marte</option>
                        <option>Saturno</option>
                        <option>Plutón</option>
                        <option>Luna</option>
                        <option>Júpiter</option>
                        <option>Marte</option>
                        <option>Saturno</option>
                        <option>Plutón</option>
                        <option>Luna</option>
                        <option>Júpiter</option>
                        <option>Marte</option>
                        <option>Saturno</option>
                        <option>Plutón</option>
                      </select>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon joystick">
                                    <button class="icon joystick-left ">
                                        <i class="fa fa-angle-left" aria-hidden="true" ></i>
                                    </button>
                                    <button class="icon joystick-up">
                                        <i class="fa fa-angle-up" aria-hidden="true" ></i>
                                    </button>
                                    <button class="icon joystick-right">
                                        <i class="fa fa-angle-right" aria-hidden="true" ></i>
                                    </button>
                                    <button class="icon joystick-down">
                                        <i class="fa fa-angle-down" aria-hidden="true" ></i>
                                    </button>
                            </div>
                            <input type="text" class="form-control" placeholder="RA"/>
                            <span class="input-group-addon" onclick="loading_effect_preview(false);">-</span>
                            <input type="text" class="form-control" placeholder="DEC"/>
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="button" onclick="loading_effect_preview(true);">Go!</button>
                            </span>                            
                        </div>                        
                    </div>
                    
                </form>    
                
        </aside>
    </div>
    <div class="col-md-8">  
        <div id="previewImage" class="preview-image">
            <div id="preview">
                <img src="<c:url value="/resources/images/preview.jpg"/>" width="90%" height="90%" class="img-rounded preview-image-image">
                <div style="clear:both; margin-top: -2px"><button id="cine-button" class="icons" onclick="cine();"><i class="fa fa-television"></i></button></div>
            </div>
            <div id="loading" style="display: none;">
                 <i class="fa fa-circle-o-notch fa-spin fa-fw"></i>
                <span class="sr-only">Loading...</span>
            </div>    
            
        </div>
    </div>
    <div class="sidebar-toggle-right">
    <button class="btn btn-danger right-btn-toggle">
        <i class="fa fa-bars" aria-hidden="true"></i>
    </button>
    </div>        
    <div class="col-md-2" >
        <aside id="sidebarRight" class="sidebar sidebar-default sidebar-stacked-right" role="navigation" >
            <!-- Top bar -->
            <div class="top-bar label-danger">
                <button class="icon right-btn-toggle-inside-size" onclick="toggle_right_size();">
                    <i id="size-btn-toggle-right" class="fa fa-angle-left" aria-hidden="true" ></i>
                </button>
                <button class="icon right-btn-toggle-inside" onclick="toggle_right();">
                    <i class="fa fa-bars" aria-hidden="true" ></i>
                </button>
                <span>Opciones avanzadas</span>
            </div>
            <fieldset disabled>
            <form class="small sidebar-inside" onclick="notify('Usted no posee los privilegios para operar estas funciones.', 'danger');">
                <div class="form-group">
                  <label for="exampleInputEmail1">Email address</label>
                  <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email">
                </div>
                <div class="form-group">
                  <label for="exampleInputPassword1">Password</label>
                  <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                </div>
                <div class="form-group">
                  <label for="exampleInputFile">File input</label>
                  <input type="file" id="exampleInputFile">
                  <p class="help-block">Example block-level help text here.</p>
                </div>
                <div class="checkbox">
                  <label>
                    <input type="checkbox"> Check me out
                  </label>
                </div>
                 <div class="input-group margin-bottom-sm">
                    <span class="input-group-addon input-sm"><i class="fa fa-envelope-o fa-fw"></i></span>
                    <input class="form-control input-sm" type="text" placeholder="Email address">
                  </div>
                  <div class="input-group">
                    <span class="input-group-addon input-sm"><i class="fa fa-key fa-fw"></i></span>
                    <input class="form-control input-sm" type="password" placeholder="Password">
                  </div>
                <div class="form-group">
                  <label for="exampleInputFile">File input</label>
                  <input type="file" id="exampleInputFile">
                  <p class="help-block">Example block-level help text here.</p>
                </div>
                <div class="checkbox">
                  <label>
                    <input type="checkbox"> Check me out
                  </label>
                </div>
                <select class="form-control input-sm">
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                    <option>5</option>
                  </select>
                <div class="form-group">
                  <label for="exampleInputEmail1">Email address</label>
                  <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email">
                </div>
                <div class="form-group">
                  <label for="exampleInputPassword1">Password</label>
                  <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                </div>
                <div class="form-group">
                  <label for="exampleInputFile">File input</label>
                  <input type="file" id="exampleInputFile">
                  <p class="help-block">Example block-level help text here.</p>
                </div>
                <div class="checkbox">
                  <label>
                    <input type="checkbox"> Check me out
                  </label>
                </div>
                 <div class="input-group margin-bottom-sm">
                    <span class="input-group-addon input-sm"><i class="fa fa-envelope-o fa-fw"></i></span>
                    <input class="form-control input-sm" type="text" placeholder="Email address">
                  </div>
                  <div class="input-group">
                    <span class="input-group-addon input-sm"><i class="fa fa-key fa-fw"></i></span>
                    <input class="form-control input-sm" type="password" placeholder="Password">
                  </div>
                <div class="form-group">
                  <label for="exampleInputFile">File input</label>
                  <input type="file" id="exampleInputFile">
                  <p class="help-block">Example block-level help text here.</p>
                </div>
                <div class="checkbox">
                  <label>
                    <input type="checkbox"> Check me out
                  </label>
                </div>
                <select class="form-control input-sm">
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                    <option>5</option>
                  </select>
                <button type="submit" class="btn btn-sm ">Submit</button>
              </form>
            </fieldset>
        </aside>  
    </div>


</body>
  

</html>