<%@page session="false"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Rastro Test</title>

<c:url var="home" value="/" scope="request" />


<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />

<spring:url value="/resources/core/js/jquery.1.10.2.min.js"
	var="jqueryJs" />
<script src="${jqueryJs}"></script>


</head>

<nav class="navbar navbar-inverse">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Rastro - Telescope Remote System</a>
		</div>
	</div>
</nav>

<div class="container" style="min-height: 500px">

	<div class="starter-template">
		<h1>Search Form</h1>
		<br>

		<div id="feedback"></div>
                

		<form class="form-vertical" id="search-form">
			<div class="form-group form-group-lg">
				<label class="col-sm-2 control-label">Dispositivo</label>
                                <select id="dispositivo" name="dispositivo" onchange="listaPropiedades();"  class="form-control">
                                    <option disabled selected value> -- seleccione un dispositivo -- </option>  
                                </select>
			</div>
                    
			<div class="form-group form-group-lg">
				<label class="col-sm-2 control-label">Propiedad</label>
                                <select id="propiedad" name="propiedad" onchange="listaElementos();"  class="form-control">
                                    <option disabled selected value> -- seleccione una propiedad -- </option>  
                                </select>
			</div>
                        <div class="form-group form-group-lg">
				<label class="col-sm-2 control-label">Elemento</label>
                                <select id="elemento" name="elemento"  class="form-control" onchange="searchViaAjaxValor();">
                                    <option disabled selected value> -- seleccione un elemento -- </option>  
                                </select>
			</div>
                        <div class="form-group form-group-lg">
                                <label class="col-sm-2 control-label">Valor</label>
                                <input type="text" class="form-control" id="valor" disabled="disabled" class="form-control">
			</div>
                            
                    <div class="form-group form-group-lg" id="elemento2div" style="display: none">
				<label class="col-sm-2 control-label">Elemento nuevo</label>
                                <input type="text" class="form-control" id="elemento2" disabled="disabled" class="form-control">
			</div>
                        <div class="form-group" id="valor2div" style="display: none" >
                                <label class="col-sm-2 control-label">Valor nuevo</label>
				<input type="text" class="form-control" id="valor2" disabled="disabled">
				
			</div>
                    
			<div class="form-group">
					<button type="submit" id="bth-search"
						class="btn btn-primary btn-lg">Buscar</button>
                                        <button type="button" id="bth-search"
                                                class="btn btn-primary btn-lg" onclick="searchViaAjaxValor();">Cargar</button>
                                        <button type="button" id="bth-search-test"
                                                class="btn btn-success btn-lg" onclick="loadExample();">Test</button>
                                        <button type="button" id="bth-search-execute"
                                                class="btn btn-success btn-lg" disabled="disabled" onclick="executeViaAjax();" >Ejecutar</button>
                                        <input type="checkbox" onclick="toggleValor();" title="Modificar valor">				
                          
			</div>
                       
		</form>
                <div><h4>Log</h4><pre id="log"></pre></div>
	</div>

</div>

<div class="container">
	<footer>
		<p>
			&copy; <a href="http://www.google.com">Rastro</a> 2016
		</p>
	</footer>
</div>

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
        
        function loadExample(){
            $("#dispositivo").val("Telescope Simulator");
            $("#propiedad").val("EQUATORIAL_EOD_COORD");
            $("#elemento").val("RA");
            
            searchViaAjax();
        }
        
	function searchViaAjax() {

		var search = {};
		search["dispositivo"] = $("#dispositivo").val();
		search["propiedad"] = $("#propiedad").val();
                search["elemento"] = $("#elemento").val();

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${home}buscar",
			data : JSON.stringify(search),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				display(data);
			},
			error : function(e) {
				console.log("ERROR: ", e);
				display(e);
			},
			done : function(e) {
				console.log("DONE");
				enableSearchButton(true);
			}
		});

	}
        function executeViaAjax() {
                
		var execute = {};
		
                execute["dispositivo"] = $("#dispositivo").val();
		execute["propiedad"] = $("#propiedad").val();
                execute["elemento1"] = $("#elemento").val();
                execute["valor1"] = $("#valor").val();
                execute["elemento2"] = $("#elemento2").val();
                execute["valor2"] = $("#valor2").val();

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${home}ejecutar",
			data : JSON.stringify(execute),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				display(data);
			},
			error : function(e) {
				console.log("ERROR: ", e);
				display(e);
			},
			done : function(e) {
				console.log("DONE");
				enableSearchButton(true);
			}
		});

	}
        function toggleValor(){
            if ($( "#valor" ).is(':disabled')){
                $( "#valor" ).prop( "disabled", false );
                $( "#valor2" ).prop( "disabled", false );
                $( "#elemento2" ).prop( "disabled", false );
                $( "#valor2div" ).show();
                $( "#elemento2div" ).show();
                $( "#bth-search-execute" ).prop( "disabled", false );
            }else{
                $( "#valor" ).prop( "disabled", true );
                $( "#valor2" ).prop( "disabled", true );
                $( "#elemento2" ).prop( "disabled", true );
                $( "#valor2div" ).hide();
                $( "#elemento2div" ).hide();
                $( "#bth-search-execute" ).prop( "disabled", true );
            };            
        }
	function enableSearchButton(flag) {
		$("#btn-search").prop("disabled", flag);
	}
        
        function display(data){
        	var json    =   JSON.stringify(data, null, 4);
                var jsonmod =   "<h4>Ajax Response</h4><pre>"
				+ json + "</pre>"; 
		$('#feedback').html(jsonmod);
                $('#log').append("<br>").append(new Date($.now())).append(json); 
                
        }    
        
	function displayDispositivos(data) {
                $.each(data, function(key, value) {
                    $.each(value, function(key2, value2) {
                        $('#dispositivo')
                            .append($("<option></option>")
                            .attr("value",value2)
                            .text(value2));
                   });
               });
               
	}
        
        function displayPropiedades(data) {
                $('#propiedad').empty();
                $.each(data, function(key, value) {
                    $.each(value, function(key2, value2) {
                        $('#propiedad')
                            .append($("<option></option>")
                            .attr("value",value2)
                            .text(value2));
                   });
               });
               
	}
        
        function displayElementos(data) {
                $('#elemento').empty();
                $.each(data, function(key, value) {
                    $.each(value, function(key2, value2) {
                        $('#elemento')
                            .append($("<option></option>")
                            .attr("value",value2)
                            .text(value2));
                   });
               });
               
	}
        
        function displayValor(data) {
                $('#valor').empty();
                $.each(data, function(key, value) {
                    $('#valor').val(value);
                });  
	}
        
        function listaDispositivos(){
            var lista = [];
            var search = {};
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${home}listaDispositivos",
			data : JSON.stringify(search),
			dataType : 'json',
			timeout : 100000,
			success : function(list) {
				console.log("SUCCESS: ", list);
				displayDispositivos(list);
                                //lista = list;
                                //agregarDispositivos()
			},
			error : function(e) {
				console.log("ERROR: ", e);
				display(e);
			},
			done : function(e) {
				console.log("DONE");
				enableSearchButton(true);
			}
		});
            return lista;
        }
        
        function agregarDispositivos(){
            var lista = listaDispositivos();
            
            $.each(lista, function(value) {
                alert(value);
           });
            
        }
              
        function listaPropiedades(){
            var lista = [];
            var search = {};
            search["dispositivo"] = $("#dispositivo").val();
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${home}listaPropiedades",
			data : JSON.stringify(search),
			dataType : 'json',
			timeout : 100000,
			success : function(list) {
				console.log("SUCCESS: ", list);
				displayPropiedades(list);
                                //lista = list;
                                //agregarDispositivos()
			},
			error : function(e) {
				console.log("ERROR: ", e);
				display(e);
			},
			done : function(e) {
				console.log("DONE");
				enableSearchButton(true);
			}
		});
            return lista;
        }
        
        function listaElementos(){
            var lista = [];
            var search = {};
            search["dispositivo"] = $("#dispositivo").val();
            search["propiedad"] = $("#propiedad").val();
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${home}listaElementos",
			data : JSON.stringify(search),
			dataType : 'json',
			timeout : 100000,
			success : function(list) {
				console.log("SUCCESS: ", list);
				displayElementos(list);
                                //lista = list;
                                //agregarDispositivos()
			},
			error : function(e) {
				console.log("ERROR: ", e);
				display(e);
			},
			done : function(e) {
				console.log("DONE");
				enableSearchButton(true);
			}
		});
            return lista;
        }
        
        function searchViaAjaxValor() {

		var search = {};
		search["dispositivo"] = $("#dispositivo").val();
		search["propiedad"] = $("#propiedad").val();
                search["elemento"] = $("#elemento").val();

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${home}buscarValor",
			data : JSON.stringify(search),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				displayValor(data);
			},
			error : function(e) {
				console.log("ERROR: ", e);
				display(e);
			},
			done : function(e) {
				console.log("DONE");
				enableSearchButton(true);
			}
		});

        }
</script>

</body>
</html>