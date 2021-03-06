<%@page session="false"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>RastroSoft</title>

<c:url var="home" value="/" scope="request" />

<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />

<spring:url value="/resources/core/css/shifts.css" var="shiftsCss" />
<link href="${shiftsCss}" rel="stylesheet" />

<spring:url value="/resources/core/js/jquery.1.10.2.min.js"
	var="jqueryJs" />
<script src="${jqueryJs}"></script>

<spring:url value="/resources/core/js/bootstrap-notify.min.js"
	var="bootstrapNotify" />
<script src="${bootstrapNotify}"></script>


<link rel="stylesheet" href="/font-awesome/css/font-awesome.min.css">
<spring:url value="/resources/core/font-awesome-4.7.0/css/font-awesome.min.css" var="fontawesomeCss" />
<link href="${fontawesomeCss}" rel="stylesheet" />

<spring:url value="/resources/core/js/jquery.color.plus-names-2.1.2.min.js"
	var="jqueryColorPlus" />
<script src="${jqueryColorPlus}"></script>

<spring:url value="/resources/core/js/ajaxFunctions.js"
	var="ajaxFunctions" />
<script src="${ajaxFunctions}"></script>


<spring:url value="/resources/core/css/jquery-ui.css" var="jqueryuiCss" />
<link href="${jqueryuiCss}" rel="stylesheet" />
<spring:url value="/resources/core/js/jquery-ui.js" var="jqueryuiJs" />
<script src="${jqueryuiJs}"></script>

<spring:url value="/resources/core/css/jquery.datetimepicker.css" var="datetimepickerCss" />
<link href="${datetimepickerCss}" rel="stylesheet" />
<spring:url value="/resources/core/js/jquery.datetimepicker.full.js" var="datetimepickerJs" />
<script src="${datetimepickerJs}"></script>



<spring:url value="/resources/core/js/datatables.js" var="datatablesJs" />
<script src="${datatablesJs}"></script>
<spring:url value="/resources/core/css/datatables.css" var="datatablesCss" />
<link href="${datatablesCss}" rel="stylesheet" />

<spring:url value="/resources/core/js/convertionFunctions.js"
	var="convertionFunctions" />
<script src="${convertionFunctions}"></script>

<spring:url value="/resources/core/js/ajaxFunctions.js"
	var="ajaxFunctions" />
<script src="${ajaxFunctions}"></script>

<spring:url value="/resources/core/css/colors.css" var="colorsCss" />
<link href="${colorsCss}" rel="stylesheet" />

<spring:url value="/resources/core/js/bootstrap.min.js"
	var="bootstrapJs" />
<script src="${bootstrapJs}"></script>

<spring:url value="/resources/core/js/notifyFunctions.js"
	var="notifyFunctions" />
<script src="${notifyFunctions}"></script>

<spring:url value="/resources/core/css/menu.css" var="menuCss" />
<link href="${menuCss}" rel="stylesheet" />

<spring:url value="/resources/core/css/info.css" var="infoCss" />
<link href="${infoCss}" rel="stylesheet" />

<spring:url value="/resources/core/js/jquery.mask.js" var="jquerymaskJs" />
<script src="${jquerymaskJs}"></script>

<spring:url value="/resources/core/js/websocket.js"
	var="websocket" />
<script src="${websocket}"></script>

<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>

</head>
<body style="padding-top: 70px;">
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
            <a class="navbar-brand" href="/rastrosoft/rastrosoft"><i class="fa fa-tint"></i> Rastrosoft</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><spring:message code="menu.shifts"/></a>
              <ul class="dropdown-menu">
                  <li id="shifts-menu"><a href="#" onclick="showShifts();"><spring:message code="menu.view_shifts"/></a></li>
                  <li id="shifts-menu"><a href="#" onclick="showAddShiftNew();"><spring:message code="menu.add_shift"/></a></li>
              </ul>             
            </li>       
            <li id="captures-menu"><a href="#" onclick="showCaptures();"><spring:message code="menu.captures"/></a></li>
            <li id="automatization-menu"><a href="#" onclick="showAutomatization();"><spring:message code="menu.automatization"/></a></li>            
            <li id="live">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i id="live-sign" class="fa fa-circle" aria-hidden="true"></i> <spring:message code="menu.on_live"/></a>
              <ul class="dropdown-menu">
                  <input type="hidden" id="key">
                  <li id="get-link"><a href="#" onclick="showLink();" ><spring:message code="menu.get_link"/></a></li>
                  <li id="view-live"><a href="#" onclick="viewLive();" ><spring:message code="menu.view_live_stream"/></a></li>
              </ul>             
            </li>  
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span id="username" ><spring:message code="menu.guest"/></span> <span class="caret"></span></a>
              <ul class="dropdown-menu">
                  <li><a href="#" onclick="showProfile();"><i class="fa fa-user fa-fw" aria-hidden="true"></i> <span><spring:message code="menu.profile"/></span></a></li>                  
                  <!--<li><a href="#"><i class="fa fa-graduation-cap fa-fw" aria-hidden="true"></i> <span><spring:message code="menu.upgrade"/></span></a></li>-->
                <li class="divider"></li>
                <li><a href="#" onclick="logout();"><i class="fa fa-sign-out fa-fw" aria-hidden="true"></i> <span><spring:message code="menu.logout"/></span></a></li>  
                <c:url var="logoutUrl" value="/logout"/>
                <form id="end_session" action="${logoutUrl}" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
              </ul>             
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="fa fa-globe" aria-hidden="true"></i></a>
              <ul class="dropdown-menu">
                  <li><a href="?locale=es"><img src="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/2.8.0/flags/4x3/ar.svg" width="20px" height="15px"> <spring:message code="lang.spanish"/></a></li>
                  <li><a href="?locale=en"><img src="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/2.8.0/flags/4x3/us.svg" width="20px" height="15px"> <spring:message code="lang.english"/></a></li>
              </ul>             
            </li> 
          </ul>
                 
        </div><!--/.nav-collapse -->
      </div>
    </nav>
    <div class="container">        
        <div class="form-group" id="select-shift">
            <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-calendar fa-fw"></i></span>
                <input type="text" class="form-control" id="datetimepicker" placeholder="Turnos"/>
                <span class="input-group-btn">
                    <button id="addShift-btn" class="btn btn-default" type="button" onclick="showLabelRemoveShift();" ><i class="fa fa-plus text-success"></i></button>
                </span>  
            </div>
        </div>        
        <div class="intro">
<!--            <i class="fa fa-tint" style="font-size:240px"></i>-->
            <div class="intro-container">
                <p><spring:message code="banner.rastrosoft_is"/> </p>
                <b>
                  <span1>
                    <spring:message code="banner.fast"/><br /> 
                    <spring:message code="banner.elegant"/><br />
                    <spring:message code="banner.efficient"/><br />
                    <spring:message code="banner.simple"/>
                    </span1>
                </b>
            </div>
        </div>        
        <div id="tableShifts" class="table-shifts">
            <h4 style="color:white" class="label-primary text-center"><spring:message code="label.shifts_awarded"/></h4>
            <table id="shifts" class="table">
                <thead>  
                  <tr>                      
                    <th>#</th>
                    <th><span><spring:message code="label.name"/></span></th>
                    <th><span><spring:message code="label.date"/></span></th>
                    <th><span><spring:message code="label.time"/></span></th>
                    <th><span><spring:message code="label.available"/></span></th>
                    <th><span><spring:message code="label.transmition"/></span></th>
                    <th><span><spring:message code="label.key"/></span></th>
                    <th><span><spring:message code="label.link"/></span></th>
                  </tr>
                </thead>
                <tbody>                                  
                </tbody>
            </table>
        </div>
        <div class="table-captures">
            <div id="dialog-message-capture-info" title="Capture information">
                
                <table class="capture_info_table">                   
                </table>
            </div>
            <h4 style="color:white" class="label-primary text-center"><spring:message code="label.my_captures"/></h4>
            <div><span id="capturesQuantity"></span> <spring:message code="label.captures_found"/></div>
            <table id="captures" class="table" >
                <thead>
                  <tr>
                    <th>#</th>                        
                    <th><span><spring:message code="label.date"/></span></th>
                    <th><span><spring:message code="label.time"/></span></th>
                    <th><span><spring:message code="label.ra"/></span></th>
                    <th><span><spring:message code="label.dec"/></span></th>
                    <th><span><spring:message code="label.exposure"/></span></th>
                    <th><span><spring:message code="label.filepath"/></span></th>
                    <th><i class="fa fa-download" aria-hidden="true"></i></th>
                    <th></th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
        <div class="automatization">            
            <div class="automatization-values col-md-3 col-sm-5">
                <h4 style="color:white" class="label-primary text-center"><spring:message code="label.settings"/></h4> 
                <div class="form-group">
                    <p class="input-help"><spring:message code="label.quick_access_targets"/></p>
                    <select class="form-control" id="quickAccessTargets" onchange="setQuickAccessTargetAutomatization();">                                         
                    </select>
                </div>
                <div id="setRaDec" class="form-group" >
                    <p class="input-help"><spring:message code="label.right_ascension_&_declination"/></p>
                    <div class="input-group">                                                                
                        <input id="setRaAu" type="text" class="form-control radec-input"  placeholder="00:00:00">
                        <p class="help-label"><spring:message code="help.set_ra"/></p>
                        <span class="input-group-addon">-</span>
                        <input id="setDecAu" type="text" class="form-control radec-input" placeholder="00:00:00">
                        <p class="help-label"><spring:message code="help.set_dec"/></p>                                                     
                    </div>
                </div>
                <div class="form-group">
                    <p class="input-help"><spring:message code="label.exposure_time"/></p>
                    <div class="input-group">
                        <span class="input-group-addon" title="Time to exposure"><i class="fa fa-clock-o fa-fw"></i></span>
                        <input id="exposureTime" type="number" class="form-control" placeholder="Exposure" min="1">                            
                        <p class="help-label"><spring:message code="help.set_exposure_time"/></p>
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
                    </div>
                </div>
                <div class="form-group">
                    <p class="input-help"><spring:message code="label.frame_type"/></p>
                    <select class="form-control" id="frameType">
                     <option disabled selected value><spring:message code="label.select_ccd_frame_type"/></option>   
                     <option value="light">Light</option>
                     <option value="bias">Bias</option>  
                     <option value="dark">Dark</option>
                     <option value="flat" disabled="disabled">Flat</option>
                   </select>
                </div>
                <div class="form-group">
                    <p class="input-help"><spring:message code="label.frame_(x_&_y)"/></p>
                    <div class="input-group">                    
                        <input id="frameX" type="text" class="form-control " placeholder="X" />
                        <p class="help-label"><spring:message code="help.set_frame_x"/></p>
                        <span class="input-group-addon">x</span>
                        <input id="frameY" type="text" class="form-control " placeholder="Y" />
                        <p class="help-label"><spring:message code="help.set_frame_y"/></p>                          
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
                    </div>  
                </div>
                <div class="form-group">
                    <p class="input-help"><spring:message code="label.absolute_focus_position"/></p>
                    <div class="input-group" style="width:100%">                    
                        <input id="focusAbsolute" type="text" class="form-control" placeholder="Ticks"/>
                        <p class="help-label"><spring:message code="help.absolute_focus_position"/></p>                           
                    </div>  
                </div>
                <div class="form-group">
                    <p class="input-help"><spring:message code="label.delay_time"/></p>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-clock-o fa-fw"></i></span>
                        <input id="delayTime" type="number" class="form-control" placeholder="Delay" min="0">                            
                        <p class="help-label"><spring:message code="help.delay_time"/></p>
                    </div>
                </div>
                <div class="form-group">
                    <p class="input-help"><spring:message code="label.quantity"/></p>
                    <div class="input-group" style="width:100%">                    
                        <input id="quantity" type="number" min="1" max="100" class="form-control" placeholder="Quantity"/>
                        <p class="help-label"><spring:message code="help.quantity"/></p>                           
                    </div>  
                </div> 
                <input type="hidden" id="step-number" value="0">
                <input type="hidden" id="selected-step-id" value="0">
                <!--<button onclick="tableAutomatization.draw();">Update table</button>-->
            </div>
            <div class="automatization-list col-md-9 col-sm-12">
                <h4 style="color:white" class="label-primary text-center"><spring:message code="label.sequence_queue"/></h4> 
                <div class="form-group">
                    <p class="input-help"><spring:message code="label.sequence"/></p>
                    <div class="input-group">                        
                        <select class="form-control" id="sequence" onchange="reloadSteps();">                         
                        </select>
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button" id="add-sequence-btn" onclick="addSequenceBtn();"><i class="fa fa-plus" aria-hidden="true"></i></button>
                            <button class="btn btn-default" type="button" id="modify-sequence-btn" onclick="modifySequenceBtn();"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button>
                            <button class="btn btn-default" type="button" id="remove-sequence-btn" onclick="removeSequenceBtn();"><i class="fa fa-minus" aria-hidden="true"></i></button>
                        </span>
                    </div>
                    
                </div>                
                <div class="controls" style="float:left">
                    <button onclick="addStepBtn();"><i class="fa fa-plus" aria-hidden="true" style="color:green"></i></button>
                    <button onclick="removeStepBtn();"><i class="fa fa-minus" aria-hidden="true" style="color:red"></i></button>
                    <button onclick="goUpStepBtn();"><i class="fa fa-caret-up" aria-hidden="true"></i></button>
                    <button onclick="goDownStepBtn();"><i class="fa fa-caret-down" aria-hidden="true"></i></button>
                    <button onclick="resetSequenceBtn();"><i class="fa fa-history" aria-hidden="true" style="color:green"></i></button>
                </div>
                <div class="table-automatization">
                    <table id="automatization" class="table select-feel">
                        <thead>
                          <tr>
                            <th>#</th>
                            <th><span><spring:message code="label.ra"/></span></th>
                            <th><span><spring:message code="label.dec"/></span></th>
                            <th><span min="1"><spring:message code="label.exposure"/></span></th>
                            <th><span><spring:message code="label.h"/></span></th>
                            <th><span><spring:message code="label.v"/></span></th>
                            <th><span><spring:message code="label.frame"/></span></th>
                            <th><span><spring:message code="label.x"/></span></th>
                            <th><span><spring:message code="label.y"/></span></th>
                            <th><span><spring:message code="label.width"/></span></th>
                            <th><span><spring:message code="label.height"/></span></th>
                            <th><span><spring:message code="label.focus"/></span></th>
                            <th><span><spring:message code="label.quantity"/></span></th>
                            <th><span><spring:message code="label.delay"/></span></th>
                            <th><span><spring:message code="label.state"/></span></th>
                          </tr>
                        </thead>
                        <tbody id="tbody-automatization">
                            
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="profile" title="Perfil">
            <form>
                    <div class="input-group input-sm" style="float:right">
                        <button type="button" class="btn btn-danger" onclick="deleteProfile();"><i class="fa fa-trash" aria-hidden="true"></i></button>
                    </div>
                    <div id="usernameModifyDiv" class="input-group input-sm">
                        <label class="input-group-addon"><i class="fa fa-user-o"></i></label>
                        <input type="text" class="form-control" id="usernameModify" name="usernameModify" placeholder="Nombre de usuario" disabled>
                    </div>
                    <div id="nameModifyDiv" class="input-group input-sm">
                        <label class="input-group-addon"><i class="fa fa-id-card-o"></i></label>
                        <input type="text" class="form-control" id="nameModify" name="nameModify" placeholder="Nombre" required>
                        <label class="input-group-addon"><i class="fa fa-address-card-o"></i></label>
                        <input type="text" class="form-control" id="lastnameModify" name="lastnameModify" placeholder="Apellido" required>
                    </div>
                    <div id="mailModifyDiv" class="input-group input-sm">
                        <label class="input-group-addon"><i class="fa fa-envelope-o"></i></label>
                        <input type="text" class="form-control" id="mailModify" name="mailModify" placeholder="Correo electr�nico" required>
                    </div>
                    <div id="passwordModifyOldDiv" class="input-group input-sm">
                        <label class="input-group-addon"><i class="fa fa-lock"></i></label> 
                        <input type="password" class="form-control" id="passwordModifyOld" name="passwordModifyOld" placeholder="Contrase�a actual" required>
                    </div>                    
                    <hr/>
                    <div id="passwordModifyNewDiv" class="input-group input-sm">
                        <label class="input-group-addon"><i class="fa fa-lock"></i></label> 
                        <input type="password" class="form-control" id="passwordModifyNew" name="passwordModifyNew" placeholder="Contrase�a nueva" required>
                    </div>
                    <div id="passwordModifyNew_reDiv" class="input-group input-sm">
                        <label class="input-group-addon"><i class="fa fa-lock"></i></label> 
                        <input type="password" class="form-control" id="passwordModifyNew_re" name="passwordModifyNew_re" placeholder="Confirmar contrase�a nueva" required>
                    </div>      
                    
            </form>
        </div>
    </div>
    <div class="footer-msg">Planetario Ciudad de La Plata</div>
</body>
  
<script>
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        
        function modifyProfile(){
            var name, lastname, mail, password_old, password_new;            
            name = $("#nameModify").val();
            lastname = $("#lastnameModify").val();
            mail = $("#mailModify").val();
            password_old = $("#passwordModifyOld").val();
            password_new = $("#passwordModifyNew").val();
            if (password_old == ""){
                alert("Debe ingresar la contrase�a actual para poder realizar esta acci�n.");
            }else{
                modifyAccount(name, lastname, mail, password_old, password_new);
            }
            
        }
        function deleteProfile(){
            var password = $("#passwordModifyOld").val();
            if (password == ""){
                alert("Debe ingresar la contrase\u00F1a actual para poder realizar esta acci\u00F3n.");
            }else{
                if (confirm("Seguro que desea eliminar su cuenta?")){
                    if (confirm("Realmente quiere eliminar su cuenta? (Esta accion no puede revertirse)")){
                        deleteAccount(password);
                    }
                }
            }
        }
        function showCaptureInfo(){
            $( "#dialog-message-capture-info" ).dialog({
                modal: true,
                show: {
                    effect: "drop",
                    duration: 100
                },
                hide: {
                    effect: "drop",
                    duration: 50
                },
                buttons: {
                  Ok: function() {
                    $( this ).dialog( "close" );
                  }
              }
            });
        }
        function showProfile(){
            getProfileData();
            $( ".profile" ).dialog({
                modal: true,
                show: {
                    effect: "drop",
                    duration: 100
                },
                hide: {
                    effect: "drop",
                    duration: 50
                },
                buttons: {
                    "Modificar": modifyProfile,
                    Cancel: function() {
                      $( this ).dialog( "close" );
                    }
              }
            });
        }
        
        jQuery(document).ready(function($) {         
            var myInterval = setInterval(function(){        
                checkLive();
            },1000);

        });
        var myInterval1 = setInterval(function(){        
            $("#live-sign").toggleClass("live-on");
        },1000);
        var myInterval2 = setInterval(function(){
            $(".live-stream").toggle( "fade" );
            $(".live-stream").toggle( "fade" );
        },5000);
        function checkLive(){
            var search = {};
            sendAjax(search,'checkLive','checkLive'); 
        }
        function liveOn(bool, public){
            if(bool){
                $("#live").show();
                $("#key").val(public);
            }else{
                $("#live").hide();            
            }    
        }
        function showLink(){
            var key = $("#key").val();
            if (key == "0"){
                alert("The link of this live streaming is private.");
            }else{
                var path = window.location.href;
                path = path.substring(0, path.lastIndexOf('/'));
                alert("The link of the live streaming is:\n\n "+ path + "/live?key=" + $("#key").val() );
            }
            
        }
        function viewLive(){
            var key = $("#key").val();
            if (key == "0"){
                key = prompt("This live stream is private. Please enter the key to access: ");
            }
            var path = window.location.href;
            path = path.substring(0, path.lastIndexOf('/'));
            window.location.replace(path + "/live?key=" + key);            
        }
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
            getShifts();  
            getAllShifts(); 
            getUsername();
            getSequences();
            getQuickAccessTargets();
            $( "input" ).focusin(function() {
                $( this ).next( ".help-label" ).show();
            });
            $( "input" ).focusout(function() {
                $( this ).next( ".help-label" ).hide();
            });
            $('#setRaAu').mask('00:00:00');
            $('#setDecAu').mask('00:00:00');
            var ua = window.navigator.userAgent;
            var msie = ua.indexOf("MSIE ");
            if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))  // If Internet Explorer, return version number
            {
                alert("Usted posee un navegador en desuso, por favor instale Firefox, Chrome o Microsoft Edge para poder utilizar este sitio.");
                window.location = 'http://www.mozilla.org/firefox';
            }
        });

        function showAddShift(){
            $('#label-1')
            .hide( "slide", 200, 
                function() {
                    $('#select-shift').show("slide", { direction: "right" }, 300);
                }
            );
        } 
        function showAddShiftNew(){
            $('#select-shift').show("slide", { direction: "right" }, 300);    
        }    
        function showLabelAddShift(){
            if (confirm ("Esta seguro que desea eliminar su turno?")){                
                $('#selected-shift')
                .hide( "slide", 200, 
                    function() {
                        $('#label-1').show("slide", { direction: "right" }, 300);
                    }
                );
            }
        } 
        
        function showLabelRemoveShift(){
            $('#select-shift').hide();
        }
        
        jQuery(document).ready(function($) {  
            getCaptures();
            $("#addShift-btn").click(function(){
                addShift();
            });
        } );
        
        var tableShifts, tableCaptures, tableAutomatization;
        function updateTableShifts(){
            if ( !$.fn.dataTable.isDataTable( '#shifts' ) ) {
                tableShifts = 
                $('#shifts').DataTable( {
                    scrollY:        '75vh',
                    ordering: false,
                    scrollCollapse: true,
                    paging:         false,
                    "language": {
                        "lengthMenu": "Mostrando _MENU_ registros por pagina",
                        "zeroRecords": "No se encontro ningun resultado",
                        "info": "",
                        "infoEmpty": "",
                        "infoFiltered": "(filtrado de un total de _MAX_ registros)",
                        "search": "Buscar",
                        "paginate": {
                            "next": "Siguiente",
                            "previous": "Anterior"
                        }

                    },
                    "lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "Todos"]]
                } ); 
            } 
//            do_translation();
        }
        function updateTableCaptures(){
            if ( !$.fn.dataTable.isDataTable( '#captures' ) ) {
                tableCaptures =
                $('#captures').DataTable( {
                    scrollY:        '75vh',
                    ordering: false,
                    scrollCollapse: true,
                    paging:         false,
                    "language": {
                        "lengthMenu": "Mostrando _MENU_ registros por pagina",
                        "zeroRecords": "No se encontro ningun resultado",
                        "info": "",
                        "infoEmpty": "",
                        "infoFiltered": "(filtrado de un total de _MAX_ registros)",
                        "search": "Buscar",
                        "paginate": {
                            "next": "Siguiente",
                            "previous": "Anterior"
                        }

                    },
                    "lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "Todos"]]
                } ); 
            } 
//            do_translation();
        }
        function updateTableAutomatization(){
            if ( !$.fn.dataTable.isDataTable( '#automatization' ) ) {
                tableAutomatization =
                $('#automatization').DataTable( {
                    scrollY:        '65vh',
                    ordering: false,
                    scrollCollapse: true,
                    paging:         false,
                    searching:         false,
                    "language": {
                        "lengthMenu": "Mostrando _MENU_ registros por pagina",
                        "zeroRecords": "No se encontro ningun resultado",
                        "info": "",
                        "infoEmpty": "",
                        "infoFiltered": "(filtrado de un total de _MAX_ registros)",
                        "search": "Buscar",
                        "paginate": {
                            "next": "Siguiente",
                            "previous": "Anterior"
                        }
                    },
                    "lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "Todos"]]
                } ); 
            }
//            do_translation();
        }
        function reloadSteps(){
            var sequence_id = $("#sequence").val();
            getSteps(sequence_id);
        }    
     
        function addShift(){
            var d = $("#datetimepicker").val();
            var year ,month , day, time, hour, minute, second;
            d = d.substr(0, 16).split("-");
            year = d[2].substr(0, 4);
            month = d[1];
            day = d[0];
            
            time    = d[2].split(" ")[1].split(":");
            hour    = time[0];
            minute  = time[1]; 
            second  = "00";
                    
            d = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
            var live = false;
            var public = false;
            if (confirm("Desea que su turno sea transmitido en directo?")){
                live = true;
                if (confirm("Desea que su turno sea p\u00FAblico?")){
                    public = true;
                }
            }
            var search = {};
            search["value"] = live; //live
            search["value2"] = public; //public
            search["value3"] = d; //datetime
            sendAjax(search,'addShift','addShift'); 
        }    
        
     
        function showShifts(){
            $("#shifts-menu").addClass("active");
            $("#captures-menu").removeClass("active");
            $('.table-captures').hide();           
            $('.table-shifts').show();   
            $('.automatization').hide();
            $('.intro').hide();
            updateTableShifts();
        }
        function showCaptures(){            
            $("#captures-menu").addClass("active");
            $("#shifts-menu").removeClass("active");
            $('.table-shifts').hide();      
            $('.table-captures').show();
            $('.automatization').hide();
            $('.intro').hide();
            updateTableCaptures();
        }
        function showAutomatization(){            
            $("#captures-menu").removeClass("active");
            $("#shifts-menu").removeClass("active");
            $('.table-shifts').hide();      
            $('.table-captures').hide();
            $('.automatization').show();
            $('.intro').hide();
            updateTableAutomatization();
        }
        function logout(){            
            $("#end_session").submit();
        }
        
        function select_row(id){
            $("#tbody-automatization > tr").removeClass('row-selected');
            $("#"+id+"").addClass('row-selected');
        }
        function generate_data_automatization(cant){
            for (i = 0; i < cant; i++) {
                $("#tbody-automatization").append("<tr id=\""+i+"\" stepid=\""+i+"\" onclick=\"select_row("+i+");\"><th>"+i+"</th><td>10:00:00</td><td>90:00:00</td><td>15</td><td>1</td><td>1</td><td>Light</td><td>0</td><td>0</td><td>1024</td><td>1280</td><td>50000</td><td>1</td></tr>");    
            } 
        }
        
        function addSequenceBtn(){
            var name = prompt('Please enter the new squence name');
            if (name!=='null' && name!==''){
                addSequence(name);
            }                
        }
        function modifySequenceBtn(){
            var old_name = $("#sequence").find('option:selected').text();
            var name = prompt('Please enter the new squence name', old_name);
            var sequence_id = $("#sequence").val();
            if (name!=='null' && name!==''){
                modifySequence(sequence_id, name);
            }  
        }
        function removeSequenceBtn(){
            var id, name;
            id = $("#sequence").val();
            name = $("#sequence").find('option:selected').text();
            if (confirm("Are you sure you want to remove the sequence '"+name+"'?") === true) {
                removeSequence(id);
            }           
        }
        function goDownStepBtn(){
            var id_step;
            id_step = $("#automatization>tbody").find(".row-selected").attr("stepid");
            if (id_step!=null){
                goDownStep(id_step);
            }    
        }
        function goUpStepBtn(){
            var id_step;
            id_step = $("#automatization>tbody").find(".row-selected").attr("stepid");
            if (id_step!=null){
                goUpStep(id_step);
            }    
        }
        function removeStepBtn(){
            var id_step;
            id_step = $("#automatization>tbody").find(".row-selected").attr("stepid");
            if (id_step!=null){
                if (confirm("Are you sure you want to remove this step?")){
                    removeStep(id_step);
                }
            }    
        }
        function addStepBtn(){
            var id_sequence, number, ra, declination, exposureTime, hBinning, vBinning, frameType, x, y, width, height, focusPosition, quantity, delay;
            id_sequence = $("#sequence").val();            
            number      = Number($("#step-number").val())+1+'';
            ra          = hoursToDecimal($("#setRaAu").val());  
            declination = hoursToDecimal($("#setDecAu").val());
            exposureTime= $("#exposureTime").val();
            hBinning    = $("#hBinning").val();
            vBinning    = $("#vBinning").val();
            frameType   = $("#frameType").val();
            x           = $("#frameX").val();
            y           = $("#frameY").val();
            width       = $("#frameWidth").val();
            height      = $("#frameHeight").val();
            focusPosition=$("#focusAbsolute").val();
            quantity    = $("#quantity").val();
            delay       = $("#delayTime").val();
            addStep(id_sequence, number, ra, declination, exposureTime, hBinning, vBinning, frameType, x, y, width, height, focusPosition, quantity, delay);
        }
        function resetSequenceBtn(){
            if (confirm("�Seguro que desea reiniciar el estado de todos los trabajos?")){
                var id_sequence = $("#sequence").val(); 
                resetSequence(id_sequence);
            }
        }    
       
//    $(function () {
//        var resizeDiv = function (object) {
//            object.height($(window).height() - $('#col1').height() - 20);
//        };
//
//
//        $(window).ready(function () {
//            resizeDiv($('#tableShifts'));
//        });
//
//        $(window).bind("resize", function () {
//            resizeDiv($('#tableShifts'));
//        });
//
//    });
</script>
</html>