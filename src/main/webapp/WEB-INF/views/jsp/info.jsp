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

<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>

</head>
<body>
    <div class="form-group">
                    <div class="form-group">
                        <div class="input-group">
                            <c:url var="logoutUrl" value="/logout"/>
                            <form id="end_session" action="${logoutUrl}" method="post">
                                <input class="form-control logout-icon" type="submit" value="Cerrar sesion" title="Cerrar sesion de USER"/>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form>
                            <span class="input-group-addon"><i class="fa fa-sign-out fa-fw"></i></span>
                        </div>
                    </div>                                      
                </div> 
    <div class="">
        <div class="shifts-picker" >
            
            <div id="label-1">Usted no posee un turno asignado.<a href="#" onclick="showAddShift();">¿Desea agregar uno?</a></div>
            

            <div class="form-group" id="selected-shift">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-calendar fa-fw"></i> Turno otorgado</span>
                    <input type="text" class="form-control" value="2017-03-09 19:00" disabled="disabled"/>
                    <span class="input-group-btn">
                        <button class="btn btn-default" type="button" onclick="showLabelAddShift();" ><i class="fa fa-minus text-danger"></i></button>
                    </span>  
                </div>
            </div>
            <div class="form-group" id="select-shift">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-calendar fa-fw"></i></span>
                    <input type="text" class="form-control" id="datetimepicker" placeholder="Turnos"/>
                    <span class="input-group-btn">
                        <button id="addShift-btn" class="btn btn-default" type="button" onclick="showLabelRemoveShift();" ><i class="fa fa-plus text-success"></i></button>
                    </span>  
                </div>
            </div>  

        </div>
        <div class="table-shifts">
            <h4 style="color:white" class="label-primary text-center">Turnos otorgados</h4>
            <table id="shifts" class="table">
                <thead>  
                  <tr>                      
                    <th>#</th>
                    <th>Nombre</th>
                    <th>Fecha</th>
                    <th>Hora</th>
                    <th>Habilitado</th>
                    <th>Transimisión</th>
                    <th>Key</th>
                    <th>Link</th>
                  </tr>
                </thead>
                <tbody>                                  
                </tbody>
            </table>
        </div>
        <div class="table-captures">
            <h4 style="color:white" class="label-primary text-center">Mis capturas</h4>
            <table id="captures" class="table">
                <thead>
                  <tr>
                    <th>#</th>                        
                    <th>Fecha</th>
                    <th>Hora</th>
                    <th>RA</th>
                    <th>DEC</th>
                    <th>Exposicion</th>
                    <th>Filepath</th>
                    <th></th>
                    <th></th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                  
                </tbody>
            </table>
        
        </div>
    </div>
    
</body>
  
<script>
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
            $('#select-shift')
            .hide( "slide", 200, 
                function() {
                    $('#selected-shift').show("slide", { direction: "right" }, 300);
                }
            );            
        }
        
        jQuery(document).ready(function($) {  
            getCaptures();
            $("#addShift-btn").click(function(){
                addShift();
            });
        } );
        function updateTables(){            
            $('.table').DataTable( {
                scrollY:        '30vh',
                ordering: false,
                scrollCollapse: true,
                paging:         false,
                "language": {
                    "lengthMenu": "Mostrando _MENU_ registros por pagina",
                    "zeroRecords": "No se encontro ningun resultado",
//                    "info": "Mostrando pagina _PAGE_ de _PAGES_",
                    "info": "",
                    "infoEmpty": "No hay registros disponibles",
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
            
            alert(d);
            
            var search = {};
            search["value"] = "true"; //live
            search["value2"] = "true"; //public
            search["value3"] = d; //datetime
            sendAjax(search,'addShift','addShift'); 
        }    
</script>
</html>