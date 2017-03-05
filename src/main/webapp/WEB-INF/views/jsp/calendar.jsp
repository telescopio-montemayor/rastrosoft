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

<%--<spring:url value="/resources/core/css/calendar.css" var="calendarCss" />--%>
<!--<link href="${calendarCss}" rel="stylesheet" />-->
<%--<spring:url value="/resources/core/js/calendar.js"
	var="calendarJs" />--%>
<!--<script src="${calendarJs}"></script>-->

<spring:url value="/resources/core/css/jquery.datetimepicker.css" var="datetimepickerCss" />
<link href="${datetimepickerCss}" rel="stylesheet" />
<spring:url value="/resources/core/js/jquery.datetimepicker.full.js" var="datetimepickerJs" />
<script src="${datetimepickerJs}"></script>



</head>

<body>

    <div class="container">
        <div class="login-form">
            
<!--            <div id="my-calendar"></div>-->
            <h3>Set options runtime DateTimePicker</h3>
            <input type="text" id="datetimepicker"/>
            <input type="button" onclick="changeDisableDate('[\'05-03-2017 16:00\',\'05-03-2017 15:00\']');"
        </div>
                
    </div>
</body>
  
<script>


var logic = function( currentDateTime ){
	if (currentDateTime && currentDateTime.getDay() == 6){
		this.setOptions({
			minTime:'11:00'
		});
	}else
		this.setOptions({
			minTime:'8:00'
		});
};

var disableDateTimeList = ['05-03-2017 15:00','05-03-2017 20:00','06-03-2017 15:00' ];
function changeDisableDate( disableDate ){
    jQuery('#datetimepicker').datetimepicker('destroy');
//    disableDate = ['05-03-2017 16:00'];
    disableDateTimeList = disableDate;
    jQuery('#datetimepicker').datetimepicker({
	onChangeDateTime:logic,
	onShow:logic,
        disabledDates: disableDateTimeList,
        formatDate:'d-m-Y H:i'
        });
}

$('#datetimepicker').datetimepicker({
	onChangeDateTime:logic,
	onShow:logic,
        disabledDates: disableDateTimeList,
        formatDate:'d-m-Y H:i'
});



</script>
</html>