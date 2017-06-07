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

<spring:url value="/resources/core/css/login.css" var="loginCss" />
<link href="${loginCss}" rel="stylesheet" />

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

<spring:url value="/resources/core/css/jquery-ui.css" var="jqueryuiCss" />
<link href="${jqueryuiCss}" rel="stylesheet" />
<spring:url value="/resources/core/js/jquery-ui.js" var="jqueryuiJs" />
<script src="${jqueryuiJs}"></script>

<spring:url value="/resources/core/js/ajaxFunctions.js"
	var="ajaxFunctions" />
<script src="${ajaxFunctions}"></script>

<spring:url value="/resources/core/js/menu.js"
	var="menuJs" />
<script src="${menuJs}"></script>

<spring:url value="/resources/core/js/jquery.validate.js"
	var="validateJs" />
<script src="${validateJs}"></script>

<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>

</head>

<body>   
<!--    <div id="header" style="background-color: white">
    </div>-->
    
    <video id="bgvid" autoplay="autoplay" loop="loop" poster="/rastrosoft/resources/images/background/Sunset-Lapse.jpg">        
        <source type="video/mp4" src="/rastrosoft/resources/images/background/Sunset-Lapse.mp4">
        <source type="video/webm" src="/rastrosoft/resources/images/background/Sunset-Lapse.webm">
        <source type="video/ogg" src="/rastrosoft/resources/images/background/Sunset-Lapse.ogv">
    </video>
    
    <div class="container">         
        <div class="login-form">
            <h2><i class="fa fa-tint"></i> Rastrosoft</h2>
            
            <div id="login">                
                <c:url var="loginUrl" value="/login" />
                <form action="${loginUrl}" method="post" class="form-horizontal">
                    <c:if test="${param.validation == 'success'}">
                        <div class="alert alert-success">
                            <p>Ha validado la cuenta correctamente.</p>
                        </div>
                    </c:if>
                    <c:if test="${param.validation == 'error'}">
                        <div class="alert alert-danger">
                            <p>No se ha podido validar la cuenta.</p>
                        </div>
                    </c:if>
                    <c:if test="${param.error != null}">
                        <div class="alert alert-danger">
                            <p>Usuario y contraseña incorrectos.</p>
                        </div>
                    </c:if>
                    <c:if test="${param.logout != null}">
                        <div class="alert alert-success">
                            <p>Has cerrado sesion correctamente.</p>
                        </div>
                    </c:if>

                    <div class="input-group input-sm">
                        <label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
                        <input type="text" class="form-control" id="username" name="ssoId" placeholder="Nombre de usuario" required>
                    </div>
                    <div class="input-group input-sm">
                        <label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label> 
                        <input type="password" class="form-control" id="password" name="password" placeholder="Contraseña" required>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />

                    <div class="form-actions">
                        <input type="submit" class="btn btn-primary btn-default button button-rounded button-left" value="Ingresar">
                        <input type="button" onclick="showSignup();" class="btn btn-success btn-default button button-rounded button-right" value="Crear cuenta">
                    </div>
                    <div class="message-forget">
                        <p>¿Olvidaste tu contraseña? <a href="#" onclick="showForget();">Restaurar ahora</a></p>
                    </div>
                
                    <div id="live">
                        <div class="live-stream clickable" onclick="showLiveKey()">
                            <div style="font-size: 24px"><i id="live-sign" class="fa fa-circle" aria-hidden="true"></i> EN DIRECTO</div>
                        </div>
                        <div id="live-key">
                             <div class="input-group input-sm">
                                <label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label> 
                                <input id="key" type="text" class="form-control" name="key" placeholder="Key">
                                <label id="enter-key" class="input-group-addon clickable"><i class="fa fa-sign-in"></i></label> 
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div id="signup">
                <form id="signupForm">
                    <div id="usernameCreateDiv" class="input-group input-sm">
                        <label class="input-group-addon"><i class="fa fa-user-o"></i></label>
                        <input type="text" class="form-control" id="usernameCreate" name="usernameCreate" placeholder="Nombre de usuario" required>                    
                    </div>
                    <div id="nameCreateDiv" class="input-group input-sm">
                        <label class="input-group-addon"><i class="fa fa-id-card-o"></i></label>
                        <input type="text" class="form-control" id="nameCreate" name="nameCreate" placeholder="Nombre" required>
                        <label class="input-group-addon"><i class="fa fa-address-card-o"></i></label>
                        <input type="text" class="form-control" id="lastnameCreate" name="lastnameCreate" placeholder="Apellido" required>
                    </div>
                    <div id="mailCreateDiv" class="input-group input-sm">
                        <label class="input-group-addon"><i class="fa fa-envelope-o"></i></label>
                        <input type="text" class="form-control" id="mailCreate" name="mailCreate" placeholder="Correo electrónico" required>
                    </div>
                    <div id="passwordCreateDiv" class="input-group input-sm">
                        <label class="input-group-addon"><i class="fa fa-lock"></i></label> 
                        <input type="password" class="form-control" id="passwordCreate" name="passwordCreate" placeholder="Contraseña" required>
                    </div>
                    <div id="passwordCreate_reDiv" class="input-group input-sm">
                        <label class="input-group-addon"><i class="fa fa-lock"></i></label> 
                        <input type="password" class="form-control" id="passwordCreate_re" name="passwordCreate_re" placeholder="Confirmar contraseña" required>
                    </div>                
                    <div class="form-actions">
                        <input id="createAccountBtn" type="button" class="btn btn-success btn-default btn-block button-rounded" value="Crear cuenta">                    
                    </div>
                </form>    
                <div class="message-login">
                    <p>¿Ya tienes una cuenta? <a href="#" onclick="showLogin();">Iniciar sesión</a></p>
                </div>
            </div>
            <div id="forget"> 
                <div id="forget-header">
                    <h4>Restablecimiento de contraseña</h4>
                    <h6>Pon la dirección de correo electrónico que usaste para registrarte. Te enviaremos un mensaje con un enlace para restablecer tu contraseña.</h6>
                </div> 
                <div class="input-group input-sm">
                    <label class="input-group-addon"><i class="fa fa-envelope-o"></i></label>
                    <input type="text" class="form-control" id="mail" name="mail" placeholder="Correo electrónico" required>
                </div>                
                <div class="form-actions">
                    <input type="button" onclick="alert();" class="btn btn-success btn-default btn-block button-rounded" value="Enviar">                    
                </div>
                <div class="message-login">
                    <p>¿Ya recuerdas la contraseña? <a href="#" onclick="showLogin();">Iniciar sesión</a></p>
                </div>
                 
            </div>
        </div>
                
    </div>
    <div class="footer-msg">Desarrollado por <a href="https://www.linkedin.com/in/alexboette">Alex Boette <i class="fa fa-linkedin-square" aria-hidden="true"></i></a></div>
</body>
  
<script>
    jQuery(document).ready(function($) {         
        var myInterval = setInterval(function(){        
            checkLive();
        },1000);
       
    });
    
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
                beforeAjax (tipo);                   
            },
            success : function(result) {
                    console.log("SUCCESS: ", result);
                    successAjax(result, tipo);
            },
            error : function(e) {
                    console.log("ERROR: ", e);
                    errorAjax(tipo);
            },
            done : function(result) {
                    console.log("DONE");
                    doneAjax(tipo);
            }
        });
    }
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
            if(public != "0"){
                $("#key").val(public);
            }    
        }else{
            $("#live").hide();            
        }    
    }
    function showSignup(){
        $('#login')
        .hide( "slide", 200, 
            function() {
                $('#signup').show("slide", { direction: "right" }, 300);
            }
        );
    }    
    function showLogin(){ 
        if ($('#forget').css('display') !== 'none'){
            $('#forget')
            .hide( "slide", 200, 
                function() {
                    $('#login').show("slide", { direction: "right" }, 300);
                }
            );     
        }else{
            $('#signup')
            .hide( "slide", 200, 
                function() {
                    $('#login').show("slide", { direction: "right" }, 300);
                }
            ); 
        }
               
    }
    function showForget(){
        $('#login')
        .hide( "slide", 200, 
            function() {
                $('#forget').show("slide", { direction: "right" }, 300);
            }
        );
        
    } 
    function showLiveKey(){        
        $('#live-key').toggle("slide", { direction: "right" }, 300);
        $('#key').focus();        
    }   
    $('#enter-key').click(function(){
        window.location.href = "/rastrosoft/live?key="+$('#key').val();
    });
    $('#key').keydown(function (e){
        if(e.keyCode == 13){
            $('#enter-key').click();
        }
    });
    document.querySelector('video').playbackRate = 1;
    
     $("#signupForm").validate({
            rules: {
                mailCreate: {
                    required: true,
                    email: true
                },

                passwordCreate: { 
                    required: true,
                    minlength: 6,
                    maxlength: 20
                } , 

                 passwordCreate_re: { 
                    equalTo: "#passwordCreate",
                    minlength: 6,
                    maxlength: 20
                }
            },
//            messages:{
//                mailCreate:{
//                    required:"the mail is required",
//                    email:"insert a correct email"
//                },    
//                passwordCreate: { 
//                    required:"the password is required"
//                }
//            },
            errorPlacement: function(error, element) {
                switch(element.attr("name")){
                    case 'usernameCreate':                        
                        error.insertAfter("#usernameCreateDiv");
                        break;
                    case 'nameCreate':
                        error.insertAfter("#nameCreateDiv");
                        break;
                    case 'lastnameCreate':
                        error.insertAfter("#nameCreateDiv");
                        break;
                    case 'mailCreate':
                        error.insertAfter("#mailCreateDiv");
                        break;    
                    case 'passwordCreate':
                        error.insertAfter("#passwordCreateDiv");
                        break;
                    case 'passwordCreate_re':
                        error.insertAfter("#passwordCreate_reDiv");
                        break;
                    default:
                        error.insertAfter(element); 
                        break;
                }
            }    
        });
    
</script>
</html>