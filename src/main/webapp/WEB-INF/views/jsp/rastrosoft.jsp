<%@page session="false"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

<spring:url value="/resources/core/js/websocket.js"
	var="websocket" />
<script src="${websocket}"></script>

<spring:url value="/resources/core/css/feedback.css" var="feedbackCss" />
<link href="${feedbackCss}" rel="stylesheet" />

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
                getChat();
                getSequences();
                getQuickAccessTargets();
                //refreshValues();
                refreshValuesMicro();


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
                    beforeAjax (tipo);                   
                },
                success : function(result) {
                        console.log("SUCCESS: ", result);
                        successAjax(result, tipo);
                },
                error : function(e) {
                        console.log("ERROR: ", e);
                        errorAjax(tipo);
                },
                done : function(result) {
                        console.log("DONE");
                        doneAjax(tipo);
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
//        jQuery(document).ready(function($) {
//                var eventSource = new EventSource("rastrosoft/newchat");
//                eventSource.onmessage = function(event) {
//                    getChat();
//                };
//        });  
       
       
</script>

 
</head>

<body>
    <c:url var="logoutUrl" value="/logout"/>
    <form id="end_session" action="${logoutUrl}" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form> 
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
                    <span><spring:message code="label.options"/></span>
                </div>  
        <div id="normalScreenContainer">
            <div id="normalScreen">
                
                <p></p>
                <div style="margin: 0 auto; text-align:center"><span><i onClick="fade();" class="fa fa-user-o" aria-hidden="true"></i> <i id="username" style="color:green;" onClick="initialize();"><spring:message code="menu.guest"/></i><div style="float:right"><i class="fa fa-clock-o" aria-hidden="true"></i> <spring:message code="label.timeleft"/>: <i id="timeleft" class="label label-success">...</i></div></span></div>
                 <div class="input-group">
                    <span class="input-group-btn">
                        <button id="end_session_button" class="btn btn-default" type="button" style="width:50%; border-bottom-left-radius: 5px; border-top-left-radius: 5px; outline: none;" ><i class="fa fa-sign-out fa-fw"></i><spring:message code="button.sign_out"/></button> 
                        <button id="panel_button" class="btn btn-default" type="button" style="width:51%; border-bottom-right-radius: 5px; border-top-right-radius: 5px; outline: none;"><i class="fa fa-columns fa-fw"></i><spring:message code="button.panel"/></button>
                    </span>
                </div> 
                <div class="form-group">
                    <p class="input-help"><spring:message code="label.quick_access_targets"/></p>
                    <select class="form-control" id="quickAccessTargets" onchange="setQuickAccessTarget();">                                         
                    </select>
                 </div>
                         
                <div id="setRaDecNormal">
                    <div id="setRaDec" class="form-group">
                        <p class="input-help"><spring:message code="label.right_ascension_&_declination"/></p>
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
                            <p class="help-label"><spring:message code="help.set_ra"/></p>
                            <span class="input-group-addon">-</span>
                            <input id="setDec" type="text" class="form-control radec-input"  >
                            <p class="help-label"><spring:message code="help.set_dec"/></p>
                            <span class="input-group-btn">
                                <button id="setRaDec-btn" class="btn btn-default" type="button" onclick="setRaDec();"><spring:message code="button.go"/></button>
                            </span>                            
                        </div>
                    </div>
                </div>
                <div style="clear: both"></div>                
                <div class="form-group">
                    <div class="input-group">
                        <span id="telescope_abort_motion_feedback" class="input-group-addon"><i class="fa fa-stop fa-fw"></i></span>
                        <button id="abortMotion" class="form-control logout-icon" type="button"><spring:message code="button.abort_motion"/></button>
                    </div>
                </div>
<!--                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-camera fa-fw"></i></span>
                        <button id="button-preview" class="form-control logout-icon" type="button" onclick="takePreviewImage();" disabled="disabled">Take preview</button>
                    </div>
                </div>-->
                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-download fa-fw"></i></span>
<!--                        <button class="form-control logout-icon" type="button" id="download">Download</button>-->
                        <a class="form-control logout-icon download-button" id="download" href=""><spring:message code="button.download"/></a>
                    </div>
                </div>
            </div>  
<!--            <div style="float: left;"><button onClick="fade();" class="btn btn-default"><i class="fa fa-graduation-cap fa-fw"></i></button></div>
            <div style="float: right;"><button onClick="initialize();" class="btn btn-default"><i class="fa fa-free-code-camp fa-fw"></i></button></div> -->
        </div>       
                            
        <div class="input-group">
            <span class="input-group-btn">
                <button id="liveTransmit" class="btn btn-default selected" type="button" style="width:50%; border-top-left-radius: 5px; outline: none;" ><span id="liveTransmitSpan"><i class="fa fa-globe fa-fw"></i><spring:message code="button.live_transmit"/></span></button> 
                <button id="enableChat" class="btn btn-default" type="button" style="width:51%; border-top-right-radius: 5px; outline: none;"><i class="fa fa-comments-o fa-fw"></i><spring:message code="button.enable_chat"/></button>
            </span> 
        </div> 
        <div id="chat" class="form-group">
            <div  id="chatbox" class="form-control" style="overflow-y: scroll; height: 100%"></div>
            <div class="form-group">
                <div class="input-group" >                            
                    <input id="msgbox" type="text" class="form-control" placeholder="<spring:message code="label.message"/>" >
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
                <div class="previewImageSrcContainer"><img src="" width="90%" height="90%" class="previewImageSrc img-rounded preview-image-image" onerror="this.src='/rastrosoft/resources/images/loading.gif';" ></div>
                <div style="clear:both; margin-top: -2px"><button id="cine-button" class="icons" onclick="cine();"><i class="fa fa-television"></i></button></div>
            </div>
            <div class="loading">                
                 <i class="fa fa-circle-o-notch fa-spin fa-fw"></i>
                <span class="sr-only"><spring:message code="label.loading"/></span>                  
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
<!--                <button class="icon right-btn-toggle-inside-size" onclick="toggle_right_size();">
                    <i id="size-btn-toggle-right" class="fa fa-angle-left" aria-hidden="true" ></i>
                </button>-->
                <button class="icon right-btn-toggle-inside" onclick="toggle_right();">
                    <i class="fa fa-bars" aria-hidden="true" ></i>
                </button>
                <span><spring:message code="label.advanced_options"/></span>
            </div>
            <div id="fade" class="fadebox"></div>
            <div id="sidebar-right-container">   
                <div class="sidebar-box">                    
                    <label><spring:message code="label.telescope"/></label>
                    <div class="form-group">
                        <div class="input-group">
                            <button id="park" class="form-control logout-icon" type="button"><spring:message code="button.park"/></button>
                            <span id="telescope_park_feedback" class="input-group-addon"></span>
                            <button id="unpark" class="form-control logout-icon" type="button"><spring:message code="button.unpark"/></button>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <button id="track" class="form-control logout-icon" type="button"><spring:message code="button.track"/></button>
                            <span id="on_coord_set_feedback" class="input-group-addon"></span>
                            <button id="slew" class="form-control logout-icon"  type="button"><spring:message code="button.slew"/></button>
                            <span class="input-group-addon"></span>
                            <button id="sync" class="form-control logout-icon"  type="button"><spring:message code="button.sync"/></button>                            
                        </div>
                    </div>
                    <div id="setRaDecFullscreen"></div>
                    <label><spring:message code="label.ccd"/></label>
                    <div class="form-group">
                        <p class="input-help"><spring:message code="label.exposure_time"/></p>
                        <div class="input-group">
                            
                            <span class="input-group-addon" title="Time to exposure"><i class="fa fa-clock-o fa-fw"></i></span>
                            <input id="exposureTime" type="number" class="form-control" placeholder="<spring:message code="label.exposure"/>">                            
                            <p class="help-label"><spring:message code="help.set_exposure_time"/></p>
                            <input id="exposureTimeHidden" type="hidden" class="form-control" value="0">                            
                            <span class="input-group-btn">
                                <button id="setExposure" class="btn btn-default" type="button"><spring:message code="button.capture"/> <i class="fa fa-play fa-fw"></i></button>
                            </span> 
                        </div>  
                        <div class="progress">
                            <div id="progressExposure" class="progress-bar progress-bar-success progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="0" style="width: 0%">                        
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <span id="ccd_abort_exposure_feedback" class="input-group-addon"><i class="fa fa-stop fa-fw"></i></span>
                            <button id="setAbortExposure" class="form-control logout-icon" type="button"><spring:message code="button.abort_exposure"/></button>
                        </div>
                    </div>
                    <div class="form-group">
                        <p class="input-help"><spring:message code="label.directory_to_upload_files"/></p>
                        <div class="input-group">
                            <span id="upload_dir_feedback" class="input-group-addon"><i class="fa fa-folder-o fa-fw"></i></span>
                            <input type="text" class="form-control" id="uploadDir" placeholder="Set upload directory">
                            <p class="help-label"><spring:message code="help.set_upload_directory"/></p>
                            <span class="input-group-btn">
                                <button id="setUploadDirectory" class="btn btn-default" type="button"><spring:message code="button.set"/></button>
                            </span>   
                        </div>
                    </div>
                    <div class="form-group">
                        <p class="input-help"><spring:message code="label.prefix_for_saven_files"/></p>
                        <div class="input-group">
                            <span id="upload_prefix_feedback" class="input-group-addon"><i class="fa fa-file-image-o fa-fw"></i></span>
                            <input type="text" class="form-control" id="uploadPrefix" placeholder="Set prefix">
                            <p class="help-label"><spring:message code="help.set_prefix"/></p>
                            <span class="input-group-btn">
                                <button id="setPrefix" class="btn btn-default" type="button"><spring:message code="button.set"/></button>
                            </span>     
                        </div>
                    </div>
                    <div class="form-group">
                        <p class="input-help"><spring:message code="label.ccd_binning_(horizontal_&_vertical)"/></p>
                        <div class="input-group">                    
                            <input id="hBinning" type="text" class="form-control " placeholder="H"/>
                            <p class="help-label"><spring:message code="help.set_horizontal_binning"/></p>
                            <span class="input-group-addon">x</span>
                            <input id="vBinning" type="text" class="form-control " placeholder="V"/>
                            <p class="help-label"><spring:message code="help.set_vertical_binning"/></p>
                            <span class="input-group-btn">
                                <button id="setBinning" class="btn btn-default" type="button"><spring:message code="button.binning"/></button>
                            </span>                            
                        </div>
                    </div>
                    <div class="form-group">
                        <p class="input-help"><spring:message code="label.frame_type"/></p>
                        <select class="form-control" id="frameType">
                         <option disabled selected value><spring:message code="label.select_ccd_frame_type"/></option>   
                         <option value="frameLight">Light</option>
                         <option value="frameBias">Bias</option>  
                         <option value="frameDark">Dark</option>
                         <option value="frameFlat" disabled="disabled">Flat</option>
                       </select>
                    </div>
                    <div class="form-group">
                        <p class="input-help"><spring:message code="label.ccd_temperature"/></p>
                        <div class="input-group">
                            <span id="ccd_temperature_feedback" class="input-group-addon"><i class="fa fa-thermometer-half fa-fw"></i></span>
                            <input type="text" class="form-control " id="ccdTemperature" placeholder="Temperature" value="-15">                    
                            <p class="help-label"><spring:message code="help.set_ccd_temperature"/></p>
                            <span class="input-group-btn">
                                <button id="setCcdTemperature" class="btn btn-default" type="button"><spring:message code="button.temperature"/></button>
                            </span>  
                        </div>
                        
                    </div>
                    <div class="form-group">
                        <p class="input-help"><spring:message code="label.frame_(x_&_y)"/></p>
                        <div class="input-group">                    
                            <input id="frameX" type="text" class="form-control " placeholder="X" />
                            <p class="help-label"><spring:message code="help.set_frame_x"/></p>
                            <span class="input-group-addon">x</span>
                            <input id="frameY" type="text" class="form-control " placeholder="Y" />
                            <p class="help-label"><spring:message code="help.set_frame_y"/></p>
                            <span class="input-group-btn">
                                <button id="setFrame" class="btn btn-default" type="button"><spring:message code="button.set"/></button>
                                
                            </span>                            
                        </div>  
                    </div>
                    <div class="form-group">
                        <p class="input-help"><spring:message code="label.image_size_(width_&_height)"/></p>
                        <div class="input-group">                    
                            <input id="frameWidth" type="text" class="form-control " placeholder="Width" />
                            <p class="help-label"><spring:message code="help.set_width"/></p>
                            <span class="input-group-addon">x</span>
                            <input id="frameHeight" type="text" class="form-control " placeholder="Height" />
                            <p class="help-label"><spring:message code="help.set_height"/></p>
                            <span class="input-group-btn">
                                <button id="setSize" class="btn btn-default" type="button"><spring:message code="button.pixels"/></button>
                                
                            </span>                            
                        </div>  
                    </div>
                    
                    
                </div>
                <label><spring:message code="label.focuser"/></label>
<!--                    PARA EL FOCUSER: MICROFOCUSER DEL LX 200-->
                     <div class="form-group">
                        <p class="input-help"><spring:message code="label.focus_speed"/></p>
                        <select class="form-control" id="focusSpeedMicro">
                         <option disabled selected value><spring:message code="label.select_focus_speed"/></option>   
                         <option value="1">Halt</option>
                         <option value="2">Slow</option>  
                         <option value="3">Medium</option>
                         <option value="4">Fast</option>
                       </select>
                    </div>
                    <div class="form-group">
                        <p class="input-help"><spring:message code="label.focus_motion"/></p>                        
                        <div class="input-group">
                            <input id="focusTimerMicro" type="text" class="form-control" placeholder="<spring:message code="label.milliseconds"/>"/>
                            <p class="help-label"><spring:message code="help.set_focus_motion"/></p>  
                            <span class="input-group-btn">
                                <button id="focusInMicro"  class="btn btn-default" type="button"><spring:message code="button.focus_in"/></button> 
                                <button id="focusOutMicro" class="btn btn-default" type="button"><spring:message code="button.focus_out"/></button>
                            </span> 
                        </div> 
                    </div                    
                    <div class="form-group">
                        <p class="input-help"><spring:message code="label.sequence"/></p>
                        <div class="input-group">                        
                            <select class="form-control" id="sequence">                         
                            </select>
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="button" id="executeSequenceBtn"><i class="fa fa-play" aria-hidden="true"></i></button>
                                <!--<button class="btn btn-default" type="button" id="stopSequenceBtn"><i class="fa fa-stop" aria-hidden="true"></i></button>-->
                            </span>
                        </div>
                    </div>          
                    
<!--                    ...-->
<!--                    
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
                        
                    </div>          -->
                    <div class="form-group previewImage sidebar-fullscreen previewImageFullscreen">            
                        <div class="preview">
                            <img src="<c:url value="/resources/images/preview.jpg"/>" width="100%" height="100%" class="previewImageSrc">                            
                        </div>
                        <div class="loading">                
                             <i class="fa fa-circle-o-notch fa-spin fa-fw"></i>
                            <span class="sr-only"><spring:message code="label.loading"/></span>                  
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