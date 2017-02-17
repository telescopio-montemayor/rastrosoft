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

        function takePreviewImage(){
            var search = {};
            var funcion = "previewImage";
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
                    loading_effect_preview(true);
                },
                success : function(list) {
                        console.log("SUCCESS: ", list);
                        update_preview_image();
                        loading_effect_preview(false);
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
                <button onClick="test();" class="btn btn-default">TEST</button>
                <div class="form-group">
                    <div class="logout">
                        <c:url var="logoutUrl" value="/logout"/>
                        <form class="form-inline" action="${logoutUrl}" method="post">                      
                          <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-sign-out fa-fw"></i></span>
                            <input class="form-control logout-icon" type="submit" value="Cerrar sesion"/>
                          </div>
                          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                    </div>                       
                </div>
                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-calendar fa-fw"></i></span>
                        <input type="date" class="form-control" id="turno" placeholder="01/01/2017">
                    </div>
                </div>  
                <div class="form-group">
                    <select class="form-control">
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
                        <span class="input-group-addon">-</span>
                        <input type="text" class="form-control" placeholder="DEC"/>
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button" onclick="takePreviewImage();">Go!</button>
                        </span>                            
                    </div>                        
                </div>
                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-stop fa-fw"></i></span>
                        <button class="form-control logout-icon" type="button">Abort motion</button>
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-camera fa-fw"></i></span>
                        <button class="form-control logout-icon" type="button">Take preview</button>
                    </div>
                </div>
        </aside>
    </div>
    <div class="col-md-8">  
        <div id="previewImage" class="preview-image">
            <div id="preview">
                <img id="previewImageSrc" src="<c:url value="/resources/images/preview.jpg"/>" width="90%" height="90%" class="img-rounded preview-image-image">
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
            
            <label>Telescopio</label>
            <div class="form-group">
                <div class="input-group">
                    <button class="form-control logout-icon" type="button">Park</button>
                    <span class="input-group-addon"></span>
                    <button class="form-control logout-icon" type="button">Unpark</button>
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <button class="form-control logout-icon" type="button">Track</button>
                    <span class="input-group-addon"></span>
                    <button class="form-control logout-icon" type="button">Slew</button>
                    <span class="input-group-addon"></span>
                    <button class="form-control logout-icon" type="button">Sync</button>
                </div>
            </div>
            
            <label>CCD</label>
            
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-folder-o fa-fw"></i></span>
                    <input type="text" class="form-control" id="uploadDirectory" placeholder="Set upload directory">
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-file-image-o fa-fw"></i></span>
                    <input type="text" class="form-control" id="uploadPrefix" placeholder="Set prefix">
                </div>
            </div>                
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-clock-o fa-fw"></i></span>
                    <input type="number" class="form-control" id="exposureTime" placeholder="Exposure">
                    <span class="input-group-addon">Seconds</span>
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">                    
                    <input type="number" class="form-control" placeholder="H"/>
                    <span class="input-group-addon">-</span>
                    <input type="number" class="form-control" placeholder="V"/>
                    <span class="input-group-btn">
                        <button class="btn btn-default" type="button">Binning</button>
                    </span>                            
                </div>  
            </div>
            <div class="form-group">
                <select class="form-control">
                 <option disabled selected value> -- select type -- </option>   
                 <option>Light</option>
                 <option>Bias</option>  
                 <option>Dark</option>
                 <option>Flat</option>
               </select>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-thermometer-half fa-fw"></i></span>
                    <input type="number" class="form-control" id="exposureTime" placeholder="Temperature">
                    <span class="input-group-addon">°C</span>
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">                    
                    <input type="number" class="form-control" placeholder="Width"/>
                    <span class="input-group-addon">-</span>
                    <input type="number" class="form-control" placeholder="Height"/>
                    <span class="input-group-btn">
                        <button class="btn btn-default" type="button">Set</button>
                    </span>                            
                </div>  
            </div>
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-stop fa-fw"></i></span>
                    <button class="form-control logout-icon" type="button">Abort exposure</button>
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-play fa-fw"></i></span>
                    <button class="form-control logout-icon" type="button">Capture</button>
                </div>
            </div>
            <label>Focuser</label>
            <div class="form-group">
                <div class="input-group">                    
                    <input type="number" class="form-control" placeholder="Ticks"/>
                    <span class="input-group-btn">
                        <button class="btn btn-default" type="button">Set</button>
                    </span>                            
                </div>  
            </div>
            <div class="form-group">
                <div class="input-group">
                    <button class="form-control logout-icon" type="button">Focus in</button>
                    <span class="input-group-addon"></span>
                    <button class="form-control logout-icon" type="button">Focus out</button>
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-clock-o fa-fw"></i></span>
                    <input type="number" class="form-control" id="exposureTime" placeholder="Focus timer">
                    <span class="input-group-addon">Seconds</span>
                </div>
            </div>
        </aside>  
    </div>


</body>
  

</html>