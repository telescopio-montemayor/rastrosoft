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

<spring:url value="/resources/core/css/colors.css" var="colorsCss" />
<link href="${colorsCss}" rel="stylesheet" />

<spring:url value="/resources/core/js/bootstrap.min.js"
	var="bootstrapJs" />
<script src="${bootstrapJs}"></script>

<spring:url value="/resources/core/js/notifyFunctions.js"
	var="notifyFunctions" />
<script src="${notifyFunctions}"></script>

<spring:url value="/resources/core/js/lang.js"
	var="langJs" />
<script src="${langJs}"></script>

<spring:url value="/resources/core/css/menu.css" var="menuCss" />
<link href="${menuCss}" rel="stylesheet" />

<spring:url value="/resources/core/css/info.css" var="infoCss" />
<link href="${infoCss}" rel="stylesheet" />

<spring:url value="/resources/core/js/jquery.mask.js" var="jquerymaskJs" />
<script src="${jquerymaskJs}"></script>

<spring:url value="/resources/core/js/websocket.js"
	var="websocket" />
<script src="${websocket}"></script>

<spring:url value="/resources/core/js/ajaxFunctionsModeration.js"
	var="ajaxFunctionsModeration" />
<script src="${ajaxFunctionsModeration}"></script>

<spring:url value="/resources/core/css/moderation.css" var="moderationCss" />
<link href="${moderationCss}" rel="stylesheet" />

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
                  <li ><a href="#" onclick="showAllModerationShifts();"><spring:message code="menu.view_shifts"/></a></li>
                  <li role="separator" class="divider"></li>
                  <li class="dropdown-header"><spring:message code="menu.filtered_shifts"/></li>
                  <li ><a href="#" onclick="showPendingShifts();" ><spring:message code="menu.pending_shifts"/></a></li>
                  <li ><a href="#" onclick="showAcceptedShifts();"><spring:message code="menu.accepted_shifts"/></a></li>
                  <li ><a href="#" onclick="showRejectedShifts();"><spring:message code="menu.rejected_shifts"/></a></li>
              </ul>             
            </li>
            <li class="dropdown-users">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><spring:message code="menu.users"/></a>
              <ul class="dropdown-menu">
                  <li><a href="#" onclick="showAllUsers();" ><spring:message code="menu.view_users"/></a></li>
                  <li><a href="#" onclick="showBannedUsers();" ><spring:message code="menu.banned_users"/></a></li>
                  <li><a href="#" onclick="showZeroCreditsUsers();" ><spring:message code="menu.zero_credits_users"/></a></li>
<!--                  <li role="separator" class="divider"></li>
                  <li class="dropdown-header">Solicitations</li>
                  <li><a href="#" onclick="">Upgrade solicitations</a></li>
                  <li><a href="#" onclick="">Credits solicitations</a></li>-->
              </ul>             
            </li>         
            <li id="live">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i id="live-sign" class="fa fa-circle" aria-hidden="true"></i> <spring:message code="menu.on_live"/></a>
              <ul class="dropdown-menu">
                  <input type="hidden" id="key">
                  <li id="get-link"><a href="#" onclick="showLink();"><spring:message code="menu.get_link"/></a></li>
                  <li id="view-live"><a href="#" onclick="viewLive();" ><spring:message code="menu.view_live_stream"/></a></li>
              </ul>             
            </li>  
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span id="username" ><spring:message code="menu.guest"/></span> <span class="caret"></span></a>
              <ul class="dropdown-menu">
<!--                  <li><a href="#"><i class="fa fa-user fa-fw" aria-hidden="true"></i> <span><spring:message code="menu.profile"/></span></a></li>
                <li class="divider"></li>-->
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
        <div class="intro">
            <div class="intro-container">
                <p>Rastrosoft es </p>
                <b>
                  <span1>
                    r�pido<br /> 
                    elegante<br />
                    eficiente<br />
                    simple.
                    </span1>
                </b>
            </div>
        </div>        
        <div id="tableShifts" class="table-shifts">
            <h4 style="color:white" class="label-primary text-center"><spring:message code="label.shift_administration"/></h4>
            <table id="shifts" class="table">
                <thead>  
                  <tr>                      
                    <th>#</th>
                    <th><span><spring:message code="label.user"/></span></th>
                    <th><span><spring:message code="label.name"/></span></th>
                    <th><span><spring:message code="label.lastname"/></span></th>
                    <th><span><spring:message code="label.mail"/></span></th>
                    <th><span><spring:message code="label.date"/></span></th>
                    <th><span><spring:message code="label.time"/></span></th>
                    <th><span><spring:message code="label.operation"/></span></th>
                  </tr>
                </thead>
                <tbody>                                  
                </tbody>
            </table>
        </div> 
        <div class="table-users">
            <h4 style="color:white" class="label-primary text-center"><spring:message code="label.users_administration"/></h4>
            <table id="users" class="table">
                <thead>  
                  <tr>                      
                    <th>#</th>
                    <th><span><spring:message code="label.user"/></span></th>
                    <th><span><spring:message code="label.name"/></span></th>
                    <th><span><spring:message code="label.lastname"/></span></th>
                    <th><span><spring:message code="label.mail"/></span></th>
                    <th><span><spring:message code="label.credits"/></span></th>
                    <th><span><spring:message code="label.available"/></span></th>
                    <th><span><spring:message code="label.rol"/></span></th>
                  </tr>
                </thead>
                <tbody>                                  
                </tbody>
            </table>
        </div>   
    </div>
    <div class="footer-msg">Planetario Ciudad de La Plata</div>
</body>
  
<script>
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
   
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
 

        jQuery(document).ready(function($) {          
            getShifts();  
            getAllShifts(); 
            getUsername();
         
            $( "input" ).focusin(function() {
                $( this ).next( ".help-label" ).show();
            });
            $( "input" ).focusout(function() {
                $( this ).next( ".help-label" ).hide();
            });

            var ua = window.navigator.userAgent;
            var msie = ua.indexOf("MSIE ");
            if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))  // If Internet Explorer, return version number
            {
                alert("Usted posee un navegador en desuso, por favor instale Firefox, Chrome o Microsoft Edge para poder utilizar este sitio.");
                window.location = 'http://www.mozilla.org/firefox';
            }
        });

 
        var tableShifts;
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
        }
        var tableUsers;
        function updateTableUsers(){
            if ( !$.fn.dataTable.isDataTable( '#users' ) ) {
                tableUsers = 
                $('#users').DataTable( {
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
        }

        function logout(){            
            $("#end_session").submit();
        }
        

      
</script>
</html>