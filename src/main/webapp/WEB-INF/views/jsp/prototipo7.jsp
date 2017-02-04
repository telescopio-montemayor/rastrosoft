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
        function cine(){
            $('body').toggleClass('cine');
            $('#sidebarLeft').toggleClass('open');
            $('#sidebarRight').toggleClass('open');
            $('#cine-button').toggleClass('white');
        }
//        $(window).resize(function() {
//            if ($(window).width() < 1250) {
//               $('.open').removeClass('open');
//            }
//        });
</script>

</head>

<body>
    
    <div class="sidebar-toggle-left">
    <button class="btn btn-primary left-btn-toggle">
        <i class="fa fa-bars" aria-hidden="true"></i>
    </button>
    </div>    
    <div class="col-md-2">
       <!-- Material sidebar -->
        <aside id="sidebarLeft" class="sidebar sidebar-default sidebar-stacked" role="navigation">
            <!-- Sidebar header -->
           
                <!-- Top bar -->
                <div class="top-bar label-primary">
                    <button class="icon left-btn-toggle-inside" onclick="toggle_left();">
                        <i class="fa fa-bars" aria-hidden="true" ></i>
                    </button>
                    <span>Opciones</span>
                </div>
                
                <form class="small sidebar-inside">
                    <div class="form-group">
                      <label for="exampleInputEmail1">Email address</label>
                      <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email">
                    </div>
                    <div class="form-group">
                      <label for="exampleInputPassword1">Password</label>
                      <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                    </div>
                    <div class="form-group">
                      <label for="exampleInputFile">File input</label>
                      <input type="file" id="exampleInputFile">
                      <p class="help-block">Example block-level help text here.</p>
                    </div>
                    <div class="checkbox">
                      <label>
                        <input type="checkbox"> Check me out
                      </label>
                    </div>
                     <div class="form-group">
                      <label for="exampleInputEmail1">Email address</label>
                      <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email">
                    </div>
                    <div class="form-group">
                      <label for="exampleInputPassword1">Password</label>
                      <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                    </div>
                    <div class="form-group">
                      <label for="exampleInputFile">File input</label>
                      <input type="file" id="exampleInputFile">
                      <p class="help-block">Example block-level help text here.</p>
                    </div>
                    <div class="checkbox">
                      <label>
                        <input type="checkbox"> Check me out
                      </label>
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                  </form>
        </aside>
    </div>
    <div class="col-md-8">  
        <div id="previewImage" class="preview-image">
            <img src="<c:url value="/resources/images/preview.jpg"/>" width="90%" height="90%" class="img-rounded preview-image-image">
            <div style="clear:both; margin-top: -2px"><button id="cine-button" class="icons" onclick="cine();"><i class="fa fa-television"></i></button></div>
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
            <fieldset disabled>
            <form class="small sidebar-inside" onclick="notify('Usted no posee los privilegios para operar estas funciones.', 'danger');">
                <div class="form-group">
                  <label for="exampleInputEmail1">Email address</label>
                  <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email">
                </div>
                <div class="form-group">
                  <label for="exampleInputPassword1">Password</label>
                  <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                </div>
                <div class="form-group">
                  <label for="exampleInputFile">File input</label>
                  <input type="file" id="exampleInputFile">
                  <p class="help-block">Example block-level help text here.</p>
                </div>
                <div class="checkbox">
                  <label>
                    <input type="checkbox"> Check me out
                  </label>
                </div>
                 <div class="input-group margin-bottom-sm">
                    <span class="input-group-addon input-sm"><i class="fa fa-envelope-o fa-fw"></i></span>
                    <input class="form-control input-sm" type="text" placeholder="Email address">
                  </div>
                  <div class="input-group">
                    <span class="input-group-addon input-sm"><i class="fa fa-key fa-fw"></i></span>
                    <input class="form-control input-sm" type="password" placeholder="Password">
                  </div>
                <div class="form-group">
                  <label for="exampleInputFile">File input</label>
                  <input type="file" id="exampleInputFile">
                  <p class="help-block">Example block-level help text here.</p>
                </div>
                <div class="checkbox">
                  <label>
                    <input type="checkbox"> Check me out
                  </label>
                </div>
                <select class="form-control input-sm">
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                    <option>5</option>
                  </select>
                <div class="form-group">
                  <label for="exampleInputEmail1">Email address</label>
                  <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email">
                </div>
                <div class="form-group">
                  <label for="exampleInputPassword1">Password</label>
                  <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                </div>
                <div class="form-group">
                  <label for="exampleInputFile">File input</label>
                  <input type="file" id="exampleInputFile">
                  <p class="help-block">Example block-level help text here.</p>
                </div>
                <div class="checkbox">
                  <label>
                    <input type="checkbox"> Check me out
                  </label>
                </div>
                 <div class="input-group margin-bottom-sm">
                    <span class="input-group-addon input-sm"><i class="fa fa-envelope-o fa-fw"></i></span>
                    <input class="form-control input-sm" type="text" placeholder="Email address">
                  </div>
                  <div class="input-group">
                    <span class="input-group-addon input-sm"><i class="fa fa-key fa-fw"></i></span>
                    <input class="form-control input-sm" type="password" placeholder="Password">
                  </div>
                <div class="form-group">
                  <label for="exampleInputFile">File input</label>
                  <input type="file" id="exampleInputFile">
                  <p class="help-block">Example block-level help text here.</p>
                </div>
                <div class="checkbox">
                  <label>
                    <input type="checkbox"> Check me out
                  </label>
                </div>
                <select class="form-control input-sm">
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                    <option>5</option>
                  </select>
                <button type="submit" class="btn btn-sm ">Submit</button>
              </form>
            </fieldset>
        </aside>  
    </div>


</body>
  

</html>