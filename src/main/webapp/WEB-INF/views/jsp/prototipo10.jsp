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

<spring:url value="/resources/core/js/geopoint.js" var="geopointJs" />
<script src="${geopointJs}"></script>
<spring:url value="/resources/core/js/jquery.mask.js" var="jquerymaskJs" />
<script src="${jquerymaskJs}"></script>

<spring:url value="/resources/core/js/convertionFunctions.js"
	var="convertionFunctions" />
<script src="${convertionFunctions}"></script>

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
                        getChat();
                  }, 500);
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
                    xhr.setRequestHeader(header, token);
                },
                success : function(result) {
                        console.log("SUCCESS: ", result);
                        successAjax(result, tipo);
                },
                error : function(e) {
                        console.log("ERROR: ", e);
                        errorAjax(result, tipo);
                },
                done : function(result) {
                        console.log("DONE");
                        doneAjax(result, tipo);
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
                alert("Usted posee un navegador en desuso, por favor instale Firefox, Chrome o Microsoft Edge para poder utilizar este sitio.");
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
       
        <aside id="sidebarLeft" class="sidebar sidebar-default sidebar-stacked" role="navigation">
            <!-- Sidebar header -->
           
                <!-- Top bar -->
                <div id="topBarLabelLeft" class="top-bar label-primary">
                    <button class="icon left-btn-toggle-inside" onclick="toggle_left();">
                        <i class="fa fa-bars" aria-hidden="true" ></i>
                    </button>                    
                    <span>Opciones</span>
                </div>  
        <div id="normalScreenContainer">
            <div id="normalScreen">
                <p></p>
                <span>Connected as: <i id="username" style="color:green;">...</i></span>
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
                    <p class="input-help">Quick access targets</p>
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
                         
                <div id="setRaDecNormal">
                    <div id="setRaDec" class="form-group">
                        <p class="input-help">Right Ascension & Declination</p>
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
                            <input id="setRa" type="text" class="form-control radec-input"  >
                            <p class="help-label">Set right ascension</p>
                            <span class="input-group-addon">-</span>
                            <input id="setDec" type="text" class="form-control radec-input"  >
                            <p class="help-label">Set declination</p>
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="button" onclick="setRaDec();" >Go!</button>
                            </span>                            
                        </div>
                    </div>
                </div>
                <div style="clear: both"></div>                
                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-stop fa-fw"></i></span>
                        <button id="abortMotion" class="form-control logout-icon" type="button">Abort motion</button>
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
            </div>  
            <div style="float: left;"><button onClick="fade();" class="btn btn-default"><i class="fa fa-graduation-cap fa-fw"></i></button></div>
            <div style="float: right;"><button onClick="initialize();" class="btn btn-default"><i class="fa fa-free-code-camp fa-fw"></i></button></div> 
        </div>       

         
        <div id="chat" class="form-group" style="height: 400px">
    <!--                    <div><button style="float:left" class="btn btn-default"><span style="color:red"><i class="fa fa-globe fa-fw"></i>Live transmit</span></button></div>
            <div><button style="float:left" class="btn btn-default" disabled="disabled"><i class="fa fa-comments-o fa-fw"></i>Enable chat</button></div>
            -->
            <!--<textarea id="chatbox" class="form-control" style="height:200px; resize: none;" placeholder="Message"></textarea>-->
            <div  id="chatbox" class="form-control" style="overflow-y: scroll; height: 100%"></div>
            <div class="form-group">
                <div class="input-group">                            
                    <input id="msgbox" type="text" class="form-control" placeholder="Message...">
                    <span class="input-group-btn">
                        <button id="sendMsgChat" class="btn btn-default" type="button"><i class="fa fa-paper-plane fa-fw"></i></button>
                    </span>  
                </div>

            </div>
        </div>
            
        
                
                
        </aside>
    </div>
    <div class="col-md-8">         
        <div class="previewImage preview-image">            
            <div class="preview">
                <img src="" width="90%" height="90%" class="previewImageSrc img-rounded preview-image-image" onerror="imgError(this);">
                <div style="clear:both; margin-top: -2px"><button id="cine-button" class="icons" onclick="cine();"><i class="fa fa-television"></i></button></div>
            </div>
            <div class="loading">                
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
                    <label>Telescope</label>
                    <div class="form-group">
                        <div class="input-group">
                            <button id="park" class="form-control logout-icon" type="button">Park</button>
                            <span class="input-group-addon"></span>
                            <button id="unpark" class="form-control logout-icon" type="button">Unpark</button>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <button id="track" class="form-control logout-icon" type="button">Track</button>
                            <span class="input-group-addon"></span>
                            <button id="slew" class="form-control logout-icon"  type="button">Slew</button>
                            <span class="input-group-addon"></span>
                            <button id="sync" class="form-control logout-icon"  type="button">Sync</button>                            
                        </div>
                    </div>
                    <div id="setRaDecFullscreen"></div>
                    <label>CCD</label>
                    <div class="form-group">
                        <p class="input-help">Exposure time</p>
                        <div class="input-group">
                            
                            <span class="input-group-addon" title="Time to exposure"><i class="fa fa-clock-o fa-fw"></i></span>
                            <input id="exposureTime" type="number" class="form-control" placeholder="Exposure">                            
                            <p class="help-label">Set time to exposure (seconds)</p>
                            <input id="exposureTimeHidden" type="hidden" class="form-control" value="0">                            
                            <span class="input-group-btn">
                                <button id="setExposure" class="btn btn-default" type="button">Capture <i class="fa fa-play fa-fw"></i></button>
                            </span> 
                        </div>  
                        <div class="progress">
                            <div id="progressExposure" class="progress-bar progress-bar-success progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="0" style="width: 0%">                        
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-stop fa-fw"></i></span>
                            <button id="setAbortExposure" class="form-control logout-icon" type="button">Abort exposure</button>
                        </div>
                    </div>
                    <div class="form-group">
                        <p class="input-help">Directory to upload files</p>
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-folder-o fa-fw"></i></span>
                            <input type="text" class="form-control" id="uploadDir" placeholder="Set upload directory">
                            <p class="help-label">Set upload directory</p>
                            <span class="input-group-btn">
                                <button id="setUploadDirectory" class="btn btn-default" type="button">Set</button>
                            </span>   
                        </div>
                    </div>
                    <div class="form-group">
                        <p class="input-help">Prefix for saven files</p>
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-file-image-o fa-fw"></i></span>
                            <input type="text" class="form-control" id="uploadPrefix" placeholder="Set prefix">
                            <p class="help-label">Set prefix for saven files</p>
                            <span class="input-group-btn">
                                <button id="setPrefix" class="btn btn-default" type="button">Set</button>
                            </span>     
                        </div>
                    </div>
                    <div class="form-group">
                        <p class="input-help">Ccd binning (horizontal & vertical)</p>
                        <div class="input-group">                    
                            <input id="hBinning" type="text" class="form-control " placeholder="H"/>
                            <p class="help-label">Set horizontal binning</p>
                            <span class="input-group-addon">x</span>
                            <input id="vBinning" type="text" class="form-control " placeholder="V"/>
                            <p class="help-label">Set vertical binning</p>
                            <span class="input-group-btn">
                                <button id="setBinning" class="btn btn-default" type="button">Binning</button>
                            </span>                            
                        </div>  
                        
                    </div>
                    <div class="form-group">
                        <p class="input-help">Frame type</p>
                        <select class="form-control" id="frameType">
                         <option disabled selected value> -- select ccd frame type -- </option>   
                         <option value="frameLight">Light</option>
                         <option value="frameBias">Bias</option>  
                         <option value="frameDark">Dark</option>
                         <option value="frameFlat" disabled="disabled">Flat</option>
                       </select>
                    </div>
                    <div class="form-group">
                        <p class="input-help">CCD temperature</p>
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-thermometer-half fa-fw"></i></span>
                            <input type="text" class="form-control " id="ccdTemperature" placeholder="Temperature" value="-15">                    
                            <p class="help-label">Set temperature for the CCD</p>
                            <span class="input-group-btn">
                                <button id="setCcdTemperature" class="btn btn-default" type="button">°C</button>
                            </span>  
                        </div>
                        
                    </div>
                    <div class="form-group">
                        <p class="input-help">Frame (X & Y)</p>
                        <div class="input-group">                    
                            <input id="frameX" type="text" class="form-control " placeholder="X" />
                            <p class="help-label">Set image X origin</p>
                            <span class="input-group-addon">x</span>
                            <input id="frameY" type="text" class="form-control " placeholder="Y" />
                            <p class="help-label">Set image Y origin</p>
                            <span class="input-group-btn">
                                <button id="setFrame" class="btn btn-default" type="button">Set</button>
                                
                            </span>                            
                        </div>  
                    </div>
                    <div class="form-group">
                        <p class="input-help">Image size (width & height)</p>
                        <div class="input-group">                    
                            <input id="frameWidth" type="text" class="form-control " placeholder="Width" />
                            <p class="help-label">Set image width</p>
                            <span class="input-group-addon">x</span>
                            <input id="frameHeight" type="text" class="form-control " placeholder="Height" />
                            <p class="help-label">Set image height</p>
                            <span class="input-group-btn">
                                <button id="setSize" class="btn btn-default" type="button">Pixels</button>
                                
                            </span>                            
                        </div>  
                    </div>
                    
                    
                </div>
                <div class="sidebar-box">
                    <label>Focuser</label>
                    <div class="form-group">
                        <p class="input-help">Current absolute focus position</p>
                        <div class="input-group">                    
                            <input id="focusAbsolute" type="text" class="form-control" placeholder="Ticks"/>
                            <p class="help-label">Set ticks for absolute focus position</p>
                            <span class="input-group-btn">
                                <button id="setFocusAbsolute" class="btn btn-default" type="button">Set</button>
                            </span>                            
                        </div>  
                    </div>
                    
                    <div class="form-group">
                        <p class="input-help">Step</p>
                        
                        <div class="input-group">
                            <input id="focusRelative" type="text" class="form-control" placeholder="Ticks"/>
                            <p class="help-label">Set ticks for step</p>  
                            <span class="input-group-btn">
                                <button id="focusIn"  class="btn btn-default" type="button">Focus in</button> 
                                <button id="focusOut" class="btn btn-default" type="button">Focus out</button>
                            </span> 
                        </div> 
                        
                    </div>                    
                    <div class="form-group previewImage sidebar-fullscreen previewImageFullscreen">            
                        <div class="preview">
                            <img src="<c:url value="/resources/images/preview.jpg"/>" width="100%" height="100%" class="previewImageSrc">                            
                        </div>
                        <div class="loading">                
                             <i class="fa fa-circle-o-notch fa-spin fa-fw"></i>
                            <span class="sr-only">Loading...</span>                  
                        </div>  
                    </div>
                    
                    <div id="fullscreenContainer"></div>   
                    
                </div>
            </div>
        </aside>  
    </div>
    <input type="hidden" id="filePath">
</body>
  

</html>