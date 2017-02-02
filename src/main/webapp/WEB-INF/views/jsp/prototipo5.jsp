<%@page session="false"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
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

</head>


<div class="container " style="min-height: 500px"> 
       
    <!-- Overlay for fixed sidebar -->
    <div class="sidebar-overlay"></div>
    
    <div class="col-md-2">
        <!-- Material sidebar -->
        <aside id="sidebarLeft" class="sidebar sidebar-default sidebar-stacked" role="navigation">
            <!-- Sidebar header -->
            <div class="sidebar-header header-cover" style="background-image: url(http://2.bp.blogspot.com/-2RewSLZUzRg/U-9o6SD4M6I/AAAAAAAADIE/voax99AbRx0/s1600/14%2B-%2B1%2B%281%29.jpg);">
                <!-- Top bar -->
                <div class="top-bar"></div>
                <!-- Sidebar toggle button -->
                <button type="button" class="sidebar-toggle">
                    <i class="fa fa-home fa-fw" aria-hidden="true"></i>
                </button>
                <!-- Sidebar brand name -->
                <a data-toggle="dropdown" class="sidebar-brand" href="#settings-dropdown">
                    john.doe@gmail.com
                    <b class="caret"></b>
                </a>
            </div>

            <!-- Sidebar navigation -->
            <ul class="nav sidebar-nav">
                <li class="dropdown">
                    <ul id="settings-dropdown" class="dropdown-menu">
                        <li>
                            <a href="#" tabindex="-1">
                                Profile
                            </a>
                        </li>
                        <li>
                            <a href="#" tabindex="-1">
                                Settings
                            </a>
                        </li>
                        <li>
                            <a href="#" tabindex="-1">
                                Help
                            </a>
                        </li>
                        <li>
                            <a href="#" tabindex="-1">
                                Exit
                            </a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#">
                        <i class="sidebar-icon md-inbox"></i>
                        Inbox
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="sidebar-icon md-star"></i>
                        Starred
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="sidebar-icon md-send"></i>
                        Sent Mail
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="sidebar-icon md-drafts"></i>
                        Drafts
                    </a>
                </li>
                <li class="divider"></li>
                <li class="dropdown">
                    <a class="ripple-effect dropdown-toggle" href="#" data-toggle="dropdown">
                        All Mail
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="#" tabindex="-1">
                                Social
                                <span class="sidebar-badge">12</span>
                            </a>
                        </li>
                        <li>
                            <a href="#" tabindex="-1">
                                Promo
                                <span class="sidebar-badge">0</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#">
                        Trash
                        <span class="sidebar-badge">3</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        Spam
                        <span class="sidebar-badge">456</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        Follow Up
                        <span class="sidebar-badge badge-circle">i</span>
                    </a>
                </li>
            </ul>
            <!-- Sidebar divider -->
            <!-- <div class="sidebar-divider"></div> -->

            <!-- Sidebar text -->
            <!--  <div class="sidebar-text">Text</div> -->
        </aside>
    </div>
    <div class="wrapper col-md-8">
        

        
        <div class="constructor">
            
            <div class="starter-template">
		<h1>Search Form</h1>
		<br>              
                <div id="feedback"></div>
                <!--<img src="/spring4-mvc-ajax-example-master/getImage/1" alt="myImage"/>-->
               
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
                                <input type="text" class="form-control" id="valor"  class="form-control">
			</div>
                            
			<div class="form-group">
                                <button type="button" id="bth-search"
                                        class="btn btn-primary btn-lg pull-right" onclick="searchViaAjaxValor();">Cargar</button>
                               
			</div>
                        <div class="form-group">
                                <button type="button" id="bth-search"
                                        class="btn btn-primary btn-lg pull-right" onclick="commitValor();">Commit</button>
                                <button type="button" id="bth-search"
                                        class="btn btn-success btn-lg pull-right" onclick="pushValor();">Push</button>
                                <button type="button" id="bth-search"
                                        class="btn btn-success btn-lg pull-right" onclick="previewImage();">Preview</button>
                               <button type="button" id="bth-search"
                                        class="btn btn-success btn-lg pull-right " onclick="displayPreview('data');">Refresh</button>
                                            
                                        
                                        
			</div>
                    </form>
            </div>
            <div id="previewImage">
            </div>
            
            <div class="container">
                    <footer>
                            <p>
                                    &copy; <a href="https://github.com/alexb900/rastro">Rastro</a> 2016
                            </p>
                    </footer>
            </div>
            
            
        </div>
        
        
    </div>
    
    <div class="col-md-2">
        <aside id="sidebarRight" class="sidebar sidebar-default sidebar-stacked-right" role="navigation">
            <!-- Sidebar header -->
            <div class="sidebar-header header-cover" style="background-image: url(http://2.bp.blogspot.com/-2RewSLZUzRg/U-9o6SD4M6I/AAAAAAAADIE/voax99AbRx0/s1600/14%2B-%2B1%2B%281%29.jpg);">
                <!-- Top bar -->
                <div class="top-bar"></div>
                <!-- Sidebar toggle button -->
                <button type="button" class="sidebar-toggle">
                    <i class="fa fa-home fa-fw" aria-hidden="true"></i>
                </button>
                <!-- Sidebar brand name -->
                <a data-toggle="dropdown" class="sidebar-brand" href="#settings-dropdown">
                    john.doe@gmail.com
                    <b class="caret"></b>
                </a>
            </div>

            <!-- Sidebar navigation -->
            <ul class="nav sidebar-nav">
                <li class="dropdown">
                    <ul id="settings-dropdown" class="dropdown-menu">
                        <li>
                            <a href="#" tabindex="-1">
                                Profile
                            </a>
                        </li>
                        <li>
                            <a href="#" tabindex="-1">
                                Settings
                            </a>
                        </li>
                        <li>
                            <a href="#" tabindex="-1">
                                Help
                            </a>
                        </li>
                        <li>
                            <a href="#" tabindex="-1">
                                Exit
                            </a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#">
                        <i class="sidebar-icon md-inbox"></i>
                        Inbox
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="sidebar-icon md-star"></i>
                        Starred
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="sidebar-icon md-send"></i>
                        Sent Mail
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="sidebar-icon md-drafts"></i>
                        Drafts
                    </a>
                </li>
                <li class="divider"></li>
                <li class="dropdown">
                    <a class="ripple-effect dropdown-toggle" href="#" data-toggle="dropdown">
                        All Mail
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="#" tabindex="-1">
                                Social
                                <span class="sidebar-badge">12</span>
                            </a>
                        </li>
                        <li>
                            <a href="#" tabindex="-1">
                                Promo
                                <span class="sidebar-badge">0</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#">
                        Trash
                        <span class="sidebar-badge">3</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        Spam
                        <span class="sidebar-badge">456</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        Follow Up
                        <span class="sidebar-badge badge-circle">i</span>
                    </a>
                </li>
            </ul>
            <!-- Sidebar divider -->
            <!-- <div class="sidebar-divider"></div> -->

            <!-- Sidebar text -->
            <!--  <div class="sidebar-text">Text</div> -->
        </aside>  
    </div>    

    

<div class="alert alert-success" style="display: none" id="alert-label"></div> 

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
              
        function alertSuccess(bool, title, msg) {
                $("#alert-label").empty();
                text = "<strong>"+ title +"</strong> " + msg;
                $("#alert-label").append(text);                
                if (bool){
                    $("#alert-label").removeClass("alert-danger");
                    $("#alert-label").addClass("alert-success");
                }else{
                    $("#alert-label").removeClass("alert-success");
                    $("#alert-label").addClass("alert-danger");
                }
                $("#alert-label").show( "slow" );
                var delay=5000; //5 segundos
                setTimeout(function() {
                  $("#alert-label").hide( "slow" );
                }, delay);
                
	}
        
        function notify(msg, type){
            $.notify({
                    // options
                    message: msg 
            },{
                    // settings
                    type: type
            });
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
        function displayCommitValor(data) {
                
                if (confirm('Esta seguro de querer cambiar el valor?')) {
                    pushValor();
                } else {
                    // Do nothing!
                }
                
                
	}
        function displayPushValor(data) {
                if ( (data['operacion']).toString() === 'true' ){
                    searchViaAjaxValor();
                    notify('Se ha cambiado el valor exitosamente.', 'success');
                }else{
                    notify('Se ha producido un error al intentar cambiar el valor.', 'danger');
                }   
	}
        function displayPreview(data){
            var img = $("<img />").attr('src', '<c:url value="/resources/images/preview.jpg"/>').attr('width','20%').attr('height','20%')
            .on('load', function() {
                if (!this.complete || typeof this.naturalWidth === "undefined" || this.naturalWidth === 0) {
                    alert('broken image!');
                } else {
                    $("#previewImage").empty();
                    $("#previewImage").append(img);
                }
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
        
        function commitValor() {

		var search = {};
		search["dispositivo"] = $("#dispositivo").val();
		search["propiedad"] = $("#propiedad").val();
                search["elemento"] = $("#elemento").val();
                search["valor"] = $("#valor").val();

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${home}commitValor",
			data : JSON.stringify(search),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				displayCommitValor(data);
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
        function pushValor() {

		var search = {};
		search["dispositivo"] = $("#dispositivo").val();
		search["propiedad"] = $("#propiedad").val();

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${home}pushValor",
			data : JSON.stringify(search),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				displayPushValor(data);
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
        function previewImage() {

		var search = {};

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${home}previewImage",
			data : JSON.stringify(search),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				displayPreview(data);
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