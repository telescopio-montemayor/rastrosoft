/**
 * Created by Kupletsky Sergey on 17.10.14.
 *
 * Material Sidebar (Profile menu)
 * Tested on Win8.1 with browsers: Chrome 37, Firefox 32, Opera 25, IE 11, Safari 5.1.7
 * You can use this sidebar in Bootstrap (v3) projects. HTML-markup like Navbar bootstrap component will make your work easier.
 * Dropdown menu and sidebar toggle button works with JQuery and Bootstrap.min.js
 */

// Sidebar toggle
//
// -------------------
    
    function toggle_left(){
        windowsize = $('#sidebarLeft').width();
        if (windowsize < 1) {
          $('#sidebarLeft').css("min-width","260px");
          $('#sidebarLeft').css("width","260px");
          $('.sidebar-toggle-left').css("visibility","hidden");
          $('.left-btn-toggle').css("width", "0px");
        }else{
          $('#sidebarLeft').css("min-width","0px");
          $('#sidebarLeft').css("width","0px");  
          $('.sidebar-toggle-left').css("visibility","visible"); 
          $('.left-btn-toggle').css("width", "50px");
        }
    }
    function toggle_right(){
        windowsize = $('#sidebarRight').width();
        if (windowsize < 1) {
          $('#sidebarRight').css("min-width","260px");
          $('#sidebarRight').css("width","260px");
          $('.sidebar-toggle-right').css("visibility","hidden");
          $('.right-btn-toggle').css("width", "0px");
        }else{
          $('#sidebarRight').css("min-width","0px");
          $('#sidebarRight').css("width","0px");  
          $('.sidebar-toggle-right').css("visibility","visible"); 
          $('.right-btn-toggle').css("width", "50px");
        }
    }
    function toggle_right_size(){
        windowsize = $('#sidebarRight').width();
        if (windowsize > 260) {
          $('#sidebarRight').css("min-width","260px");
          $('#sidebarRight').css("width","260px");
          $('#size-btn-toggle-right').removeClass('fa-angle-right').addClass('fa-angle-left');
        }else{
          $('#size-btn-toggle-right').removeClass('fa-angle-left').addClass('fa-angle-right');
          $('#sidebarRight').css("min-width","720px");
          $('#sidebarRight').css("width","100%");
        }
    }
    $(document).ready(function() {
//        $('#sidebarLeft').change(function() {
//            windowsize = $('#sidebarLeft').width();
//            if (windowsize < 1) {
//                alert();
//              $('.sidebar-toggle-left').css("visibility","visible");
//            }else{
//              $('.sidebar-toggle-left').css("visibility","hidden");  
//            }
//        });
    $('.sidebar-toggle-left').on('click', function() {
        toggle_left();
    });
    $('.sidebar-toggle-right').on('click', function() {
        toggle_right();
    });

});

// Sidebar toggle old
//
// -------------------
$(document).ready(function() {
    var overlay = $('.sidebar-overlay');
    
    $('.sidebar-toggle').on('click', function() {
        var sidebar = $('#sidebar');
        sidebar.toggleClass('open');
//        $('#sidebar-open-button').toggleClass('hidden');
//        $('#sidebar-open-button').toggleClass('visible');
        if ((sidebar.hasClass('sidebar-fixed-left') || sidebar.hasClass('sidebar-fixed-right')) && sidebar.hasClass('open')) {
            overlay.addClass('active');
        } else {
            overlay.removeClass('active');
        }
    });

    overlay.on('click', function() {
        $(this).removeClass('active');
        $('#sidebar').removeClass('open');
    });

});

// Sidebar constructor
//
// -------------------
$(document).ready(function() {
    var sidebar = $('#sidebar');
    var sidebarHeader = $('#sidebar .sidebar-header');
    var sidebarImg = sidebarHeader.css('background-image');
    var toggleButtons = $('.sidebar-toggle');

    // Hide toggle buttons on default position
    toggleButtons.css('display', 'none');
    $('body').css('display', 'table');

    
    // Sidebar position
    $('#sidebar-position').change(function() {
        var value = $( this ).val();
        sidebar.removeClass('sidebar-fixed-left sidebar-fixed-right sidebar-stacked').addClass(value).addClass('open');
        if (value == 'sidebar-fixed-left' || value == 'sidebar-fixed-right') {
            $('.sidebar-overlay').addClass('active');
        }
        // Show toggle buttons
        if (value != '') {
            toggleButtons.css('display', 'initial');
            $('body').css('display', 'initial');
        } else {
            // Hide toggle buttons
            toggleButtons.css('display', 'none');
            $('body').css('display', 'table');
        }
    });

    // Sidebar theme
    $('#sidebar-theme').change(function() {
        var value = $( this ).val();
        sidebar.removeClass('sidebar-default sidebar-inverse sidebar-colored sidebar-colored-inverse').addClass(value);
    });

    // Header cover
    $('#sidebar-header').change(function() {
        var value = $(this).val();

        $('.sidebar-header').removeClass('header-cover').addClass(value);

        if (value == 'header-cover') {
            sidebarHeader.css('background-image', sidebarImg);
        } else {
            sidebarHeader.css('background-image', '');
        }
    });
init_menu();
});

(function($) {
    var dropdown = $('.dropdown');

    // Add slidedown animation to dropdown
    dropdown.on('show.bs.dropdown', function(e){
        $(this).find('.dropdown-menu').first().stop(true, true).slideDown();
    });

    // Add slideup animation to dropdown
    dropdown.on('hide.bs.dropdown', function(e){
        $(this).find('.dropdown-menu').first().stop(true, true).slideUp();
    });
})(jQuery);



(function(removeClass) {

    jQuery.fn.removeClass = function( value ) {
		if ( value && typeof value.test === "function" ) {
			for ( var i = 0, l = this.length; i < l; i++ ) {
				var elem = this[i];
				if ( elem.nodeType === 1 && elem.className ) {
					var classNames = elem.className.split( /\s+/ );

					for ( var n = classNames.length; n--; ) {
						if ( value.test(classNames[n]) ) {
							classNames.splice(n, 1);
						}
					}
					elem.className = jQuery.trim( classNames.join(" ") );
				}
			}
		} else {
			removeClass.call(this, value);
		}
		return this;
	}

})(jQuery.fn.removeClass);


function init_menu(){
    $('#sidebarLeft').addClass('open');
    $('#sidebarRight').addClass('open');
    $('body').css('display', 'initial');
    $('.sidebar-toggle').css('display', 'none');
}

function loading_effect_preview(bool){
    $('#previewImage').toggleClass('loading-icon');    
    if (bool){
        $('#preview').css('display','none');
        $('#loading').fadeIn( "slow" );
       
    }else{
        $('#loading').css('display','none');
        $('#preview').fadeIn( "slow" );        
    }
}
var imagePreviewSource;
$(document).ready(function() {
    imagePreviewSource = $('#previewImageSrc').attr('src');
});
function update_preview_image(){
    var imageSource = imagePreviewSource + '?' +  new Date().getTime();
    $("#previewImageSrc").attr("src", imageSource);
}
