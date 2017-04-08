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

<spring:url value="/resources/core/js/jquery.1.10.2.min.js"
	var="jqueryJs" />
<script src="${jqueryJs}"></script>


<spring:url value="/resources/core/js/bootstrap-notify.min.js"
	var="bootstrapNotify" />
<script src="${bootstrapNotify}"></script>

<spring:url value="/resources/core/js/ajaxFunctions.js"
	var="ajaxFunctions" />
<script src="${ajaxFunctions}"></script>
<spring:url value="/resources/core/js/notifyFunctions.js"
	var="notifyFunctions" />
<script src="${notifyFunctions}"></script>

<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>

<nav class="navbar navbar-inverse">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">RastroSoft - Telescope Remote System</a>
		</div>
	</div>
</nav>

<div class="container" style="min-height: 500px">

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
                                <button type="button" id="bth-search"
                                        class="btn btn-success btn-lg pull-right " onclick="modificarString();">Modificar String</button>
                                            
                                        
                                        
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
    

<div class="alert alert-success" style="display: none" id="alert-label"></div> 

<script>
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    jQuery(document).ready(function($) {
        listaDispositivos();
        $("#search-form").submit(function(event) {
            // Disble the search button
            enableSearchButton(false);
            // Prevent the form from submitting via the browser.
            event.preventDefault();
        });

    });
              
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
            success : function(list) {
                    console.log("SUCCESS: ", list);
                    successAjax(list, tipo);
            },
            error : function(e) {
                    console.log("ERROR: ", e);
                    errorAjax(e, tipo);
            },
            done : function(e) {
                    console.log("DONE");
                    doneAjax(e, tipo);
            }
        });
    }
        
</script>

</body>
</html>