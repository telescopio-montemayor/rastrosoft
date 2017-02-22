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

<script src="https://code.jquery.com/color/jquery.color.plus-names-2.1.2.min.js" integrity="sha256-Wp3wC/dKYQ/dCOUD7VUXXp4neLI5t0uUEF1pg0dFnAE=" crossorigin="anonymous"></script>

<spring:url value="/resources/core/js/ajaxFunctions.js"
	var="ajaxFunctions" />
<script src="${ajaxFunctions}"></script>
<spring:url value="/resources/core/js/notifyFunctions.js"
	var="notifyFunctions" />
<script src="${notifyFunctions}"></script>

<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>

<script>
	
        function test() {
                var source = new EventSource('SSEController');
                source.onopen = function(event) {
                    console.log("eventsource opened!");
                };

                source.onmessage = function(event) {
                    var data = event.data;
                    console.log(data);
                    document.getElementById('sse').innerHTML += 
                        event.data + "<br />";
                };
            }
            window.addEventListener("load", test);
</script>

</head>

<body>
    <div id="sse"></div>


</body>
  

</html>