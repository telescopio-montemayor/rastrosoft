
var socket = new WebSocket("ws://"+window.location.hostname+":8080/rastrosoft/actions");
socket.onmessage = onMessage;
  
function onMessage(event) {
    var device = JSON.parse(event.data);
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
                break;
            case "newChat":                
                updateNewChat(device.value, device.value2, device.value3);
                break;
            default:
                break;
        }
        
    }
} 
function loadSprite(src, callback) {
    var sprite = new Image();
    sprite.onload = callback;
    sprite.src = src;
}
function cargarImagen(imageSource){
   d = new Date();
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
    var myInterval = setInterval(function(){
        if(loadImage(imageSource)){   
            cargarImagen(imageSource);
            clearInterval(myInterval);
        }   
    },500);
}
function updateNewChat(user, message, datetime){
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
     