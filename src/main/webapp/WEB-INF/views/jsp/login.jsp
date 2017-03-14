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

<spring:url value="/resources/core/css/calendar.css" var="calendarCss" />
<link href="${calendarCss}" rel="stylesheet" />
<spring:url value="/resources/core/js/calendar.js"
	var="calendarJs" />
<script src="${calendarJs}"></script>

</head>

<body>

    <div class="container">
        <div class="login-form">
            
            <c:url var="loginUrl" value="/login" />
            <form action="${loginUrl}" method="post" class="form-horizontal">
                <c:if test="${param.error != null}">
                    <div class="alert alert-danger">
                        <p>Invalid username and password.</p>
                    </div>
                </c:if>
                <c:if test="${param.logout != null}">
                    <div class="alert alert-success">
                        <p>You have been logged out successfully.</p>
                    </div>
                </c:if>
                <div class="input-group input-sm">
                    <label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
                    <input type="text" class="form-control" id="username" name="ssoId" placeholder="Enter Username" required>
                </div>
                <div class="input-group input-sm">
                    <label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label> 
                    <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
                </div>
                <input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />

                <div class="form-actions">
                    <input type="submit" class="btn btn-primary btn-default button" value="Log in">
                    <input type="button" class="btn btn-success btn-default button" value="Create account">
                </div>
            </form>
           
        </div>
                
    </div>
</body>
  

</html>