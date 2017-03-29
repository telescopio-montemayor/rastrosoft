<%@page session="false"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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

<spring:url value="/resources/core/js/jquery.color.plus-names-2.1.2.min.js"
	var="jqueryColorPlus" />
<script src="${jqueryColorPlus}"></script>


<spring:url value="/resources/core/js/ajaxFunctions.js"
	var="ajaxFunctions" />
<script src="${ajaxFunctions}"></script>
<spring:url value="/resources/core/js/notifyFunctions.js"
	var="notifyFunctions" />
<script src="${notifyFunctions}"></script>

<spring:url value="/resources/core/css/jquery.datetimepicker.css" var="datetimepickerCss" />
<link href="${datetimepickerCss}" rel="stylesheet" />
<spring:url value="/resources/core/js/jquery.datetimepicker.full.js" var="datetimepickerJs" />
<script src="${datetimepickerJs}"></script>

<spring:url value="/resources/core/css/jquery-ui.css" var="jqueryuiCss" />
<link href="${jqueryuiCss}" rel="stylesheet" />
<spring:url value="/resources/core/js/jquery-ui.js" var="jqueryuiJs" />
<script src="${jqueryuiJs}"></script>

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
                getUsername();
                setInterval(function() {
                        refreshValues();
                  }, 200);

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
                success : function(result) {
                        console.log("SUCCESS: ", result);
                        displayTipo(result, tipo);
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
        
        function changeDisableDate( disableDate ){
            jQuery('#datetimepicker').datetimepicker('destroy');
            jQuery('#datetimepicker').datetimepicker({
                disabledDates: disableDate,
                minDate:0,
                format:'d-m-Y H:i',
                formatDate2:'Y-m-d H:i'
            });            
            
        }

        jQuery(document).ready(function($) {     
//            var disableDateTimeList = ['08-03-2017 15:00','09-03-2017 20:00','10-03-2017 15:00', '11-03-2017 11:00'];
//            changeDisableDate( disableDateTimeList );
            getShifts();  
            var ua = window.navigator.userAgent;
            var msie = ua.indexOf("MSIE ");
            if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))  // If Internet Explorer, return version number
            {
                alert("Usted posee un navegador en desuso, por favor instale Firefox, Chrome o Microsoft Edge para poder tilizar este sitio.");
                window.location = 'http://www.mozilla.org/firefox';
            }
        });
        

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
                <p></p>
                <span>Conectado como: <i id="username" style="color:green;">...</i></span>
                <div class="form-group">
                    <div class="form-group">
                        <div class="input-group">
                            <c:url var="logoutUrl" value="/logout"/>
                            <form action="${logoutUrl}" method="post">
                                <input class="form-control logout-icon" type="submit" value="Cerrar sesion" title="Cerrar sesion de USER"/>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form>
                            <span class="input-group-addon"><i class="fa fa-sign-out fa-fw"></i></span>
                        </div>
                    </div>                                      
                </div>                 
                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-calendar fa-fw"></i></span>
                        <input type="text" class="form-control" id="datetimepicker" placeholder="Turnos"/>
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button" onclick="" >Set</button>
                        </span>  
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
                        <input id="setRa" type="text" class="form-control" placeholder="RA"/>
                        <span class="input-group-addon">-</span>
                        <input id="setDec" type="text" class="form-control" placeholder="DEC"/>
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button" onclick="setRaDec();" >Go!</button>
                        </span>                            
                    </div>  
                    <span style="float:right; font-weight: 300; font-size: 12px; color: grey">RA: <span id="getRa">13</span> - DEC: <span id="getDec">13</span></span>
                </div>
                <div style="clear: both"></div>
                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-stop fa-fw"></i></span>
                        <button class="form-control logout-icon" type="button">Abort motion</button>
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-camera fa-fw"></i></span>
                        <button id="button-preview" class="form-control logout-icon" type="button" onclick="takePreviewImage();">Take preview</button>
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-download fa-fw"></i></span>
                        <button class="form-control logout-icon" type="button">Download</button>
                    </div>
                </div>
                <div style="float: left; padding-top: 130%;"><button onClick="fade();" class="btn btn-default">Modo escuela</button></div>
                <div style="float: right;  padding-top: 130%;"><button onClick="test();" class="btn btn-default">TEST</button></div>
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
            <div id="fade" class="fadebox"></div>
            <div id="sidebar-right-container">
                <div class="sidebar-box">
                    <label>Telescopio</label>
                    <div class="form-group">
                        <div class="input-group">
                            <button class="form-control logout-icon" type="button">Park</button>
                            <span class="input-group-addon"></span>
                            <button class="form-control logout-icon selected" type="button">Unpark</button>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <button class="form-control logout-icon selected" type="button">Track</button>
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
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="button" onclick="" >Set</button>
                            </span>   
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-file-image-o fa-fw"></i></span>
                            <input type="text" class="form-control" id="uploadPrefix" placeholder="Set prefix">
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="button" onclick="" >Set</button>
                            </span>     
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">                    
                            <input id="h-binning" type="number" class="form-control  currentNumber" placeholder="H"/>
                            <p id="help-h-binning" class="help-label">Set H for binning</p>
                            <span class="input-group-addon">-</span>
                            <input id="v-binning" type="number" class="form-control  currentNumber" placeholder="V"/>
                            <p id="help-v-binning" class="help-label">Set V for binning</p>
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="button">Binning</button>
                            </span>                            
                        </div>  
                        
                    </div>
                    <div class="form-group">
                        <select class="form-control">
                         <option disabled selected value> -- select exposure mode -- </option>   
                         <option>Light</option>
                         <option>Bias</option>  
                         <option>Dark</option>
                         <option>Flat</option>
                       </select>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-thermometer-half fa-fw"></i></span>
                            <input type="number" class="form-control  currentNumber" id="temperature" placeholder="Temperature">                    
                            <p id="help-temperature" class="help-label">Set temperature for the CCD</p>
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="button">°C</button>
                            </span>  
                        </div>
                        
                    </div>
                    <div class="form-group">
                        <div class="input-group">                    
                            <input type="number" class="form-control  currentNumber" id="" placeholder="Width"/>
                            <span class="input-group-addon">-</span>
                            <input type="number" class="form-control  currentNumber" placeholder="Height"/>
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="button">Set</button>
                                
                            </span>                            
                        </div>  
                    </div>
                    
                    <div class="form-group">
                        <p class="input-help">Exposure time</p>
                        <div class="input-group" id="exposure-time">
                            
                            <span class="input-group-addon" title="Time to exposure"><i class="fa fa-clock-o fa-fw"></i></span>
                            <input type="number" class="form-control currentNumber" id="exposureTime" title="Set time to exposure" placeholder="Exposure" value="10" readonly >
                            <input type="number" class="form-control" id="exposureTimeHidden" title="Set time to exposure" placeholder="Exposure" >
                            
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="button">Seconds</button>
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
                </div>
                <div class="sidebar-box">
                    <label>Focuser</label>
                    <div class="form-group">
                        <div class="input-group">                    
                            <input type="number" class="form-control currentNumber" placeholder="Ticks"/>
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
                            <input type="number" class="form-control currentNumber" id="exposureTime" placeholder="Focus timer">
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="button">Seconds</button>
                            </span>  
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">                    
                            <input type="number" class="form-control currentNumber" placeholder="Seeing"/>
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="button">Set</button>
                            </span>                            
                        </div>  
                    </div>
                    <div id="sidebar-preview" class="form-group">
                        <img src="/rastrosoft/resources/images/preview.jpg" id="" class="" width="100%" height="100%">
                    </div>
                </div>
            </div>
        </aside>  
    </div>


</body>
  

</html>