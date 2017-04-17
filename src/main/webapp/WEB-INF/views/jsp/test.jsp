<%-- 
    Document   : test
    Created on : 16/04/2017, 11:22:16
    Author     : ip300
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="_csrf" content="${_csrf.token}"/>
        <meta name="_csrf_header" content="${_csrf.headerName}"/>
    </head>
    <body>
            Time: <span id="foo"></span>

            <br><br>
            <button onclick="start()">Start</button>

            <script type="text/javascript">
             var eventSource = new EventSource("hola");

                    eventSource.onmessage = function(event) {

                            alert(event.data);

                    };   
            function start() {

                    

            }
            </script>
    </body>
</html>
