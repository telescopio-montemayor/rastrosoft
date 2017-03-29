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
                </form>
            </div>
            <div id="signup">
                <div class="input-group input-sm">
                    <label class="input-group-addon"><i class="fa fa-id-card-o"></i></label>
                    <input type="text" class="form-control" id="username" name="name" placeholder="Nombre" required>
                    <label class="input-group-addon"><i class="fa fa-address-card-o"></i></label>
                    <input type="text" class="form-control" id="username" name="lastname" placeholder="Apellido" required>
                </div>
                <div class="input-group input-sm">
                    <label class="input-group-addon"><i class="fa fa-envelope-o"></i></label>
                    <input type="text" class="form-control" id="mail" name="mail" placeholder="Correo electrónico" required>
                </div>
                <div class="input-group input-sm">
                    <label class="input-group-addon"><i class="fa fa-lock"></i></label> 
                    <input type="password" class="form-control" id="password" name="password" placeholder="Contraseña" required>
                </div>
                <div class="input-group input-sm">
                    <label class="input-group-addon"><i class="fa fa-lock"></i></label> 
                    <input type="password" class="form-control" id="password_re" name="password_re" placeholder="Confirmar contraseña" required>
                </div>                
                <div class="form-actions">
                    <input type="button" onclick="alert();" class="btn btn-success btn-default btn-block button-rounded" value="Crear cuenta">                    
                </div>
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

</body>
  
<script>
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
    document.querySelector('video').playbackRate = 1;
</script>
</html>