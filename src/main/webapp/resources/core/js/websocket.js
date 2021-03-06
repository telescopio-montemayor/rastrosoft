
var socket = new WebSocket("ws://"+window.location.hostname+":8080/rastrosoft/actions");
socket.onmessage = onMessage;
  
function onMessage(event) {
    var device = JSON.parse(event.data);
//    if(device.element !== "EQUATORIAL_EOD_COORD")
//        alert("Element: "+device.element+"\nValue1: "+device.value+"\nValue2: "+device.value2+"\nValue3: "+device.value3+"\nValue4: "+device.value4 );
    if (device.action === "update") {
       
        switch(device.element) {
            case "EQUATORIAL_EOD_COORD":
                updateEquatorialEodCoord(device.value, device.value2);
                break;
            case "TELESCOPE_PARK":
                updateTelescopePark(device.value);
                break;
            case "ON_COORD_SET":
                updateOnCoordSet(device.value);
                break;
            case "UPLOAD_SETTINGS":                
                updateUploadSettings(device.value, device.value2);
                break;
            case "CCD_BINNING":
                updateCcdBinning(device.value, device.value2);
                break;
            case "CCD_TEMPERATURE":                
                updateCcdTemperature(device.value);
                break;
            case "CCD_FRAME_TYPE":
                updateCcdFrameType(device.value);
                break;
            case "CCD_FRAME":                
                updateCcdFrame(device.value, device.value2, device.value3, device.value4);
                break;
            case "CCD_EXPOSURE":
                updateCcdExposure(device.value);
                break;
            case "CCD_FILE_PATH":
                updateCcdFilePath(device.value);
                break;
            case "FOCUS_MOTION":
                updateFocusMotion(device.value);
                break;
            case "ABS_FOCUS_POSITION":
                updateAbsFocusPosition(device.value);
                break;
            case "REL_FOCUS_POSITION":
                updateRelFocusPosition(device.value);
                break;
            case "newCapture":                
                updateNewImageCapture(device.value);
                //alert(device.value);
                break;
            case "newChat":                
                updateNewChat(device.value, device.value2, device.value3);
                break;
            case "clearChat":                
                $("#chatbox").empty();
                $("#chatbox").append('<p style="color: grey">0 mensajes nuevos...</p>');
                break;   
            case "FOCUS_SPEED":
                updateFocusSpeedMicro(device.value);
                break;
            case "FOCUS_TIMER":
                updateFocusTimerMicro(device.value);
                break;
            case "stepStateChanged":
                reloadSteps();
                break;
            case "enableChat":
                updateChatEnabled(device.value);
                break;
            case "liveTransmit":
                updateLiveTransmit(device.value);
                break;
            default:
                break;
        }
        
    }
    if (device.action === "feedback") {
//        alert(device.element+" + "+device.value);
        switch(device.element) {
            case "EQUATORIAL_EOD_COORD":
                showFeedback("setRaDec-btn",device.value);
                break;
            case "CCD_EXPOSURE":
                showFeedback("setExposure",device.value);
                break;
            case "ON_COORD_SET":
                showFeedback("on_coord_set_feedback",device.value);
                break;    
            case "TELESCOPE_PARK":
                showFeedback("telescope_park_feedback",device.value);
                showFeedback("setRaDec-btn",device.value);
                break;        
            case "CCD_ABORT_EXPOSURE":
                showFeedback("ccd_abort_exposure_feedback",device.value);
                break;
            case "UPLOAD_SETTINGS":
                showFeedback("upload_dir_feedback",device.value);
                showFeedback("upload_prefix_feedback",device.value);
                break;  
            case "CCD_BINNING":
                showFeedback("setBinning",device.value);
                break;
            case "CCD_FRAME_TYPE":
                showFeedback("frameType",device.value);
                break;
            case "CCD_TEMPERATURE":
                showFeedback("ccd_temperature_feedback",device.value);
                break;        
            case "CCD_FRAME":
                showFeedback("setFrame",device.value);
                showFeedback("setSize",device.value);
                break; 
            case "TELESCOPE_ABORT_MOTION":
                showFeedback("telescope_abort_motion_feedback",device.value);
                break;  
            default:
                break;
        }
    }
} 
function showFeedback(elementId, feedback){
    switch(feedback){
        case "BUSY":
            $("#"+elementId).removeClass("bg-ok");
            $("#"+elementId).removeClass("bg-idle");
            if(!$("#"+elementId).hasClass("bg-busy")){
                $("#"+elementId).addClass("bg-busy").delay(1000).queue(function(){
                    $(this).removeClass("bg-busy").dequeue();
                });
            }
            break;
        case "OK":
            $("#"+elementId).removeClass("bg-busy");
            $("#"+elementId).removeClass("bg-idle");
            if(!$("#"+elementId).hasClass("bg-ok")){
                $("#"+elementId).addClass("bg-ok").delay(1000).queue(function(){
                    $(this).removeClass("bg-ok").dequeue();
                });
            }
            break;
        case "IDLE":
            $("#"+elementId).removeClass("bg-ok");
            $("#"+elementId).removeClass("bg-busy");
            if(!$("#"+elementId).hasClass("bg-idle")){
                $("#"+elementId).addClass("bg-idle").delay(1000).queue(function(){
                    $(this).removeClass("bg-idle").dequeue();
                });
            }
            break;
        default:
            break;
    }
}
function loadSprite(src, callback) {
    var sprite = new Image();
    sprite.onload = callback;
    sprite.src = src;
}
function cargarImagen(imageSource){
   d = new Date();
   //$(".previewImageSrcContainer").empty();
   //$(".previewImageSrcContainer").html('<img src="" width="90%" height="90%" class="previewImageSrc img-rounded preview-image-image" src="'+imageSource+'?'+d.getTime()+'" onerror="imgError(this);">');
   $('.previewImageSrc').removeAttr('scr');
   $(".previewImageSrc").attr("src", imageSource+"?"+d.getTime());   
}
function loadImage(imageSource){    
    var image = new Image();
    image.src = imageSource;
    if (image.width == 0) {              
      return false;
    }else{
        return true;
    }    
}
function updateNewImageCapture(value){
    var absoluteFilePath = value;
    var res = absoluteFilePath.split("webapp");
    var imageSource = ("/rastrosoft"+res[1]);
    $("#download").attr("href", imageSource);
    $("#download").attr("download", "capture.jpg");
    cargarImagen(imageSource);
//    var myInterval = setInterval(function(){        
//        if(loadImage(imageSource)){   
//            cargarImagen(imageSource);
//            clearInterval(myInterval);
//        }   
//    },500);
}
function updateNewChat(user, message, datetime){
    if($("#chatbox").text() == "0 mensajes nuevos..."){
        $("#chatbox").empty();
    }
    $("#chatbox").append('<p style="margin: 0!important; color:'+stringToColour(user)+'; float:left">'+user+'</p>'
                            +'<p style="font-size:10px; color:grey; float: right; padding-top:5px; margin: 0!important;">'+datetime+'</p>'
                            +'<p style="margin: 0!important; clear: both">'+message+'</p>'
                            +'<hr style="margin:5px 0 0 0!important">\n');
}
function updateEquatorialEodCoord(ra, dec){
    if (($('#setRa').is(":focus")===false)&($('#setDec').is(":focus")===false)){
//        $('#setRa').val(decimalToHours(ra));
//        $('#setDec').val(decimalToDegrees(dec));
        $('#setRa').val(ra);
        $('#setDec').val(dec);
    }
}
function updateTelescopePark(value){
    switch(value){
        case "Park":
            $("#park").addClass('selected');
            $("#unpark").removeClass('selected');
            break;
        case "UnPark":
            $("#unpark").addClass('selected');
            $("#park").removeClass('selected');
            break;
        default:
            break;
    }
}
function updateOnCoordSet(value){
    switch(value){
        case "Track":
            $("#track").addClass('selected');
            $("#slew").removeClass('selected');
            $("#sync").removeClass('selected');
            break;
        case "Slew":
            $("#track").removeClass('selected');
            $("#slew").addClass('selected');
            $("#sync").removeClass('selected');
            break;
        case "Sync":
            $("#track").removeClass('selected');
            $("#slew").removeClass('selected');
            $("#sync").addClass('selected');
            break;
        default:
            break;
    }
}
function updateUploadSettings(uploadDir, uploadPrefix){
//    if (($('#uploadDir').is(":focus")===false))
        $("#uploadDir").val(uploadDir);
//    if (($('#uploadPrefix').is(":focus")===false))
        $("#uploadPrefix").val(uploadPrefix);
}
function updateCcdBinning(hBinning, vBinning){
//    if (($('#hBinning').is(":focus")===false)&($('#vBinning').is(":focus")===false)){
        $("#hBinning").val(hBinning);
        $("#vBinning").val(vBinning);
//    }
}
function updateCcdTemperature(ccdTemperature){
//    if (($('#ccdTemperature').is(":focus")===false))
        $("#ccdTemperature").val(Math.round( ccdTemperature * 100 ) / 100);
} 

function updateCcdFrameType(value){
    switch(value){
        case "Light":
            $('#frameType option[value="frameLight"]').prop('selected', true);
            break;
        case "Bias":
            $('#frameType option[value="frameBias"]').prop('selected', true);
            break;
        case "Dark":
            $('#frameType option[value="frameDark"]').prop('selected', true);
            break;
        case "Flat":
            $('#frameType option[value="frameFlat"]').prop('selected', true);
            break;
        default:
            break;
    }
}      
       
function updateCcdFrame(x, y, width, height){
    
    $('#frameX').val(Math.round( x * 1 ) / 1);
    $('#frameY').val(Math.round( y * 1 ) / 1);


    $('#frameWidth').val(Math.round( width * 1 ) / 1);
    $('#frameHeight').val(Math.round( height * 1 ) / 1);
    
}      

function updateCcdExposure(exposureTime){
    $('#exposureTimeHidden').val(Math.floor(exposureTime)).trigger('change');
}    

function updateCcdFilePath(filePath){
    $("#filePath").val(filePath);
}       

function updateFocusMotion(value){
    switch(value){
        case "FocusIn":
            $("#focusIn").addClass('selected');
            $("#focusOut").removeClass('selected');
            break;
        case "FocusOut":
            $("#focusOut").addClass('selected');
            $("#focusIn").removeClass('selected');
            break;        
        default:
            break;
    }
}  
  
function updateAbsFocusPosition(focusAbsolute){
//    if (($('#focusAbsolute').is(":focus")===false))
          $("#focusAbsolute").val(Math.round( focusAbsolute * 1 ) / 1);    
} 

function updateRelFocusPosition(focusRelative){
//    if (($('#focusRelative').is(":focus")===false))
        $("#focusRelative").val(Math.round( focusRelative * 1 ) / 1);   
}     
     
function updateFocusSpeedMicro(value){
    switch(value){
        case "1":
            $('#focusSpeedMicro option[value="1"]').prop('selected', true);
            break;
        case "2":
            $('#focusSpeedMicro option[value="2"]').prop('selected', true);
            break;
        case "3":
            $('#focusSpeedMicro option[value="3"]').prop('selected', true);
            break;
        case "4":
            $('#focusSpeedMicro option[value="4"]').prop('selected', true);
            break;
        default:
            break;
    }
}
function updateFocusTimerMicro(value){
    $("#focusTimerMicro").val(value);   
} 

function updateChatEnabled(chatEnabled){
    if(chatEnabled == "1"){
        $("#sendMsgChat").prop('disabled', false);
        $("#msgbox").prop('disabled', false);
    }else{
        $("#sendMsgChat").prop('disabled', true);
        $("#msgbox").prop('disabled', true);
    }   
}

function updateLiveTransmit(liveTransmit){
    if(liveTransmit == "1"){
        $("#liveTransmitSpan").css("color","rgb(255, 0, 0)");
    }else{
        $("#liveTransmitSpan").css("color","rgb(0, 0, 0)");
    } 
}