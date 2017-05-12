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

<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>

</head>
<body>
        <div class="col-md-6">
            <div class="row">
                <div id="col1" class="col-md-12">                    
                    
                    <h4 style="color:white" class="label-primary text-center">Opciones</h4>         
                    <div class="form-group" id="select-shift">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-calendar fa-fw"></i></span>
                            <input type="text" class="form-control" id="datetimepicker" placeholder="Turnos"/>
                            <span class="input-group-btn">
                                <button id="addShift-btn" class="btn btn-default" type="button" onclick="showLabelRemoveShift();" ><i class="fa fa-plus text-success"></i></button>
                            </span>  
                        </div>
                    </div>  
                    <div class="row">
                        <div class="col-md-4 col-sm-4">
                            <a class="btn btn-lg btn-blue btn-block btn-huge" href="#"><i class="fa fa-tasks fa-2x "></i><br>Automatization</a>
                        </div>
                        <div class="col-md-4 col-sm-4">
                            <a class="btn btn-lg btn-light-blue btn-block btn-huge" href="#"><i class="fa fa-user-circle-o fa-2x"></i><br>Profile</a>
                        </div>
                        <div class="col-md-4 col-sm-4">
                            <a class="btn btn-lg btn-light-green btn-block btn-huge" href="#"  onclick="showAddShiftNew();"><i class="fa fa-plus-square fa-2x "></i><br>Add shift</a>
                        </div>
                    </div> 
                    <hr/>            
                    <div class="row">
                        <div class="col-md-4 col-sm-4">
                            <a class="btn btn-lg btn-pink btn-block btn-huge" href="#"><i class="fa fa-tasks fa-2x "></i><br>Automatization</a>
                        </div>
                        <div class="col-md-4 col-sm-4">
                            <a class="btn btn-lg btn-deep-purple btn-block btn-huge" href="#"><i class="fa fa-user-circle-o fa-2x"></i><br>Profile</a>
                        </div>
                        <div class="col-md-4 col-sm-4">
                            <a class="btn btn-lg btn-indigo btn-block btn-huge" href="#"><i class="fa fa-graduation-cap fa-2x "></i><br>Upgrade</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div id="tableShifts" class="table-shifts">
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
                </div>
            </div>
        </div>            
        <div class="col-md-6">
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
            $('#shifts').DataTable( {
                scrollY:        '55vh',
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
            $('#captures').DataTable( {
                scrollY:        '80vh',
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