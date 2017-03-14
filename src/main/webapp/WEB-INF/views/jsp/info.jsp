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

<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>

</head>
<body>

    <div class="container">
        <div class="shifts-picker" >
            
            <div id="label-1">Usted no posee un turno asignado.<a href="#" onclick="showAddShift();">¿Desea agregar uno?</a></div>
            

            <div class="form-group" id="selected-shift" style="">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-calendar fa-fw"></i> Turno otorgado</span>
                    <input type="text" class="form-control" value="2017-03-09 19:00" disabled="disabled"/>
                    <span class="input-group-btn">
                        <button class="btn btn-default" type="button" onclick="showLabelAddShift();" ><i class="fa fa-minus text-danger"></i></button>
                    </span>  
                </div>
            </div>
            <div class="form-group" id="select-shift" style="display: none">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-calendar fa-fw"></i></span>
                    <input type="text" class="form-control" id="datetimepicker" placeholder="Turnos"/>
                    <span class="input-group-btn">
                        <button class="btn btn-default" type="button" onclick="" ><i class="fa fa-plus text-success"></i></button>
                    </span>  
                </div>
            </div>  

        </div>
        <div id="accordion">
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
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <th scope="row">1</th>
                    <td>Alex</td>
                    <td>2017-03-08</td>
                    <td>17:00</td>
                    <td>Si</td>
                  </tr>
                  <tr>
                    <th scope="row">2</th>
                    <td>Gonzalo</td>
                    <td>2017-03-09</td>
                    <td>19:00</td>
                    <td>Si</td>
                  </tr>
                  <tr>
                    <th scope="row">3</th>
                    <td>Pedro</td>
                    <td>2017-03-07</td>
                    <td>05:00</td>
                    <td>Si</td>
                  </tr>
                  <tr class="label-danger">
                    <th scope="row">1</th>
                    <td>Alex</td>
                    <td>2017-03-08</td>
                    <td>17:00</td>
                    <td>No</td>
                  </tr>
                  <tr>
                    <th scope="row">2</th>
                    <td>Gonzalo</td>
                    <td>2017-03-09</td>
                    <td>19:00</td>
                    <td>Si</td>
                  </tr>
                  <tr>
                    <th scope="row">1</th>
                    <td>Alex</td>
                    <td>2017-03-08</td>
                    <td>17:00</td>
                    <td>Si</td>
                  </tr>
                  <tr>
                    <th scope="row">2</th>
                    <td>Gonzalo</td>
                    <td>2017-03-09</td>
                    <td>19:00</td>
                    <td>Si</td>
                  </tr>
                  <tr>
                    <th scope="row">3</th>
                    <td>Pedro</td>
                    <td>2017-03-07</td>
                    <td>05:00</td>
                    <td>Si</td>
                  </tr><tr>
                    <th scope="row">1</th>
                    <td>Alex</td>
                    <td>2017-03-08</td>
                    <td>17:00</td>
                    <td>Si</td>
                  </tr>
                  <tr>
                    <th scope="row">2</th>
                    <td>Gonzalo</td>
                    <td>2017-03-09</td>
                    <td>19:00</td>
                    <td>Si</td>
                  </tr>
                  <tr>
                    <th scope="row">2</th>
                    <td>Gonzalo</td>
                    <td>2017-03-09</td>
                    <td>19:00</td>
                    <td>Si</td>
                  </tr>
                  <tr>
                    <th scope="row">2</th>
                    <td>Gonzalo</td>
                    <td>2017-03-09</td>
                    <td>19:00</td>
                    <td>Si</td>
                  </tr>
                  <tr>
                    <th scope="row">2</th>
                    <td>Gonzalo</td>
                    <td>2017-03-09</td>
                    <td>19:00</td>
                    <td>Si</td>
                  </tr>
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
                    <th></th>
                    <th></th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <th scope="row">1</th>                        
                    <td>2017-03-08</td>
                    <td>17:00</td>
                    <td>13:00</td>
                    <td>20:00</td>
                    <td>30 segundos</td>
                    <td><a href="#"><i class="fa fa-download" aria-hidden="true"></i></a></td>
                    <td><a href="#"><i class="fa fa-file-text-o text-success" aria-hidden="true"></i></a></td>
                    <td><a href="#"><i class="fa fa-minus text-danger" aria-hidden="true"></i></a></td>
                  </tr> 
                  <tr>
                    <th scope="row">1</th>                        
                    <td>2017-03-08</td>
                    <td>17:15</td>
                    <td>10:00</td>
                    <td>21:00</td>
                    <td>10 segundos</td>
                    <td><a href="#"><i class="fa fa-download" aria-hidden="true"></i></a></td>
                    <td><a href="#"><i class="fa fa-file-text-o text-success" aria-hidden="true"></i></a></td>
                    <td><a href="#"><i class="fa fa-minus text-danger" aria-hidden="true"></i></a></td>
                  </tr> 
                  <tr>
                    <th scope="row">1</th>                        
                    <td>2017-03-08</td>
                    <td>17:20</td>
                    <td>12:00</td>
                    <td>21:00</td>
                    <td>120 segundos</td>
                    <td><a href="#"><i class="fa fa-download" aria-hidden="true"></i></a></td>
                    <td><a href="#"><i class="fa fa-file-text-o text-success" aria-hidden="true"></i></a></td>
                    <td><a href="#"><i class="fa fa-minus text-danger" aria-hidden="true"></i></a></td>
                  </tr> 
                  <tr>
                    <th scope="row">1</th>                        
                    <td>2017-03-08</td>
                    <td>17:34</td>
                    <td>15:00</td>
                    <td>22:00</td>
                    <td>360 segundos</td>
                    <td><a href="#"><i class="fa fa-download text-primary" aria-hidden="true"></i></a></td>
                    <td><a href="#"><i class="fa fa-file-text-o text-success" aria-hidden="true"></i></a></td>
                    <td><a href="#"><i class="fa fa-minus text-danger" aria-hidden="true"></i></a></td>
                  </tr>
                  <tr>
                    <th scope="row">1</th>                        
                    <td>2017-03-08</td>
                    <td>17:34</td>
                    <td>15:00</td>
                    <td>22:00</td>
                    <td>360 segundos</td>
                    <td><a href="#"><i class="fa fa-download text-primary" aria-hidden="true"></i></a></td>
                    <td><a href="#"><i class="fa fa-file-text-o text-success" aria-hidden="true"></i></a></td>
                    <td><a href="#"><i class="fa fa-minus text-danger" aria-hidden="true"></i></a></td>
                  </tr>
                  <tr>
                    <th scope="row">1</th>                        
                    <td>2017-03-08</td>
                    <td>17:34</td>
                    <td>15:00</td>
                    <td>22:00</td>
                    <td>360 segundos</td>
                    <td><a href="#"><i class="fa fa-download text-primary" aria-hidden="true"></i></a></td>
                    <td><a href="#"><i class="fa fa-file-text-o text-success" aria-hidden="true"></i></a></td>
                    <td><a href="#"><i class="fa fa-minus text-danger" aria-hidden="true"></i></a></td>
                  </tr>
                  <tr>
                    <th scope="row">1</th>                        
                    <td>2017-03-08</td>
                    <td>17:34</td>
                    <td>15:00</td>
                    <td>22:00</td>
                    <td>360 segundos</td>
                    <td><a href="#"><i class="fa fa-download text-primary" aria-hidden="true"></i></a></td>
                    <td><a href="#"><i class="fa fa-file-text-o text-success" aria-hidden="true"></i></a></td>
                    <td><a href="#"><i class="fa fa-minus text-danger" aria-hidden="true"></i></a></td>
                  </tr>
                </tbody>
            </table>
        </div>
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
            $( "#accordion" ).accordion();
            getShifts();  
            var ua = window.navigator.userAgent;
            var msie = ua.indexOf("MSIE ");
            if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))  // If Internet Explorer, return version number
            {
                alert("Usted posee un navegador en desuso, por favor instale Firefox, Chrome o Microsoft Edge para poder tilizar este sitio.");
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
        
        $(document).ready(function() {
            $('.table').DataTable( {
                scrollY:        '30vh',
                ordering: false,
                scrollCollapse: true,
                paging:         true,
                "language": {
                    "lengthMenu": "Mostrando _MENU_ registros por pagina",
                    "zeroRecords": "No se encontro ningun resultado",
                    "info": "Mostrando pagina _PAGE_ de _PAGES_",
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
//            $('#captures').DataTable( {
//                scrollY:        '30vh',
//                scrollCollapse: true,
//                paging:         false
//            } );
        } );
</script>
</html>