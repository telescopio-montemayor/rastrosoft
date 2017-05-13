var langs = ['en', 'es'];
var langCode = '';
var langJS = null;

var translate = function (jsdata)
{	
	$("[tkey]").each (function (index)
	{
		var strTr = jsdata [$(this).attr ('tkey')];
	    $(this).html (strTr);
	});
}
langCode = navigator.language.substr (0, 2);
jQuery(document).ready(function($) {  
    	do_translation();
} );
function do_translation(){
	if (jQuery.inArray( langCode, langs )){
		$.getJSON('/rastrosoft/resources/core/js/lang/'+langCode+'.json', translate);
	}else{
		$.getJSON('/rastrosoft/resources/core/js/lang/en.json', translate);
	}
}



