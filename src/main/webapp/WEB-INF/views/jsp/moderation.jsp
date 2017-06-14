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
            <a class="navbar-brand" href="/rastrosoft/prototipo10"><i class="fa fa-tint"></i> Rastrosoft</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" tkey="shifts">Shifts</a>
              <ul class="dropdown-menu">
                  <li ><a href="#" onclick="showAllModerationShifts();" tkey="view-shifts">View all shifts</a></li>
                  <li role="separator" class="divider"></li>
                  <li class="dropdown-header">Filtered shifts</li>
                  <li ><a href="#" onclick="showPendingShifts();" tkey="pendig-shift">Pending shifts</a></li>
                  <li ><a href="#" onclick="showAcceptedShifts();" tkey="accepted-shift">Accepted shifts</a></li>
                  <li ><a href="#" onclick="showRejectedShifts();" tkey="rejected-shift">Rejected shifts</a></li>
              </ul>             
            </li>
            <li class="dropdown-users">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" tkey="users">Users</a>
              <ul class="dropdown-menu">
                  <li><a href="#" onclick="" tkey="">View users</a></li>
                  <li><a href="#" onclick="" tkey="">Banned users</a></li>
                  <li><a href="#" onclick="" tkey="">Zero credits users</a></li>
                  <li role="separator" class="divider"></li>
                  <li class="dropdown-header">Solicitations</li>
                  <li><a href="#" onclick="" tkey="">Upgrade solicitations</a></li>
                  <li><a href="#" onclick="" tkey="">Credits solicitations</a></li>
              </ul>             
            </li>         
            <li id="live">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" tkey="on-live"><i id="live-sign" class="fa fa-circle" aria-hidden="true"></i> On live</a>
              <ul class="dropdown-menu">
                  <input type="hidden" id="key">
                  <li id="get-link"><a href="#" onclick="showLink();" tkey="get-link">Get link</a></li>
                  <li id="view-live"><a href="#" onclick="viewLive();" tkey="view-live">View live stream</a></li>
              </ul>             
            </li>  
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span id="username" >Guest</span> <span class="caret"></span></a>
              <ul class="dropdown-menu">
                  <li><a href="#"><i class="fa fa-user fa-fw" aria-hidden="true"></i> <span tkey="profile">Profile</span></a></li>
                <li class="divider"></li>
                <li><a href="#" onclick="logout();"><i class="fa fa-sign-out fa-fw" aria-hidden="true"></i> <span tkey="logout">Logout</span></a></li>  
                <c:url var="logoutUrl" value="/logout"/>
                <form id="end_session" action="${logoutUrl}" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
              </ul>             
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="fa fa-globe" aria-hidden="true"></i></a>
              <ul class="dropdown-menu">
                  <li><a href="?locale=es"><spring:message code="lang.spanish"/></a></li>
                  <li><a href="?locale=en"><spring:message code="lang.english"/></a></li>
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
                    rápido<br /> 
                    elegante<br />
                    eficiente<br />
                    simple.
                    </span1>
                </b>
            </div>
        </div>        
        <div id="tableShifts" class="table-shifts">
            <h4 style="color:white" class="label-primary text-center" tkey="shifts-administration">Administraci&oacute;n de turnos</h4>
            <table id="shifts" class="table">
                <thead>  
                  <tr>                      
                    <th>#</th>
                    <th><span tkey="username">User</span></th>
                    <th><span tkey="name">Name</span></th>
                    <th><span tkey="lastname">Lastname</span></th>
                    <th><span tkey="mail">Mail</span></th>
                    <th><span tkey="date">Date</span></th>
                    <th><span tkey="hour">Hour</span></th>
                    <th><span tkey="operation">Operation</span></th>
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
            do_translation();
        }


        function logout(){            
            $("#end_session").submit();
        }
        

      
</script>
</html>