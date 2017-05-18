function beforeAjax (tipo){
    switch(tipo) {
        case 'setExposure':
            $("#setExposure").prop("disabled", "disabled");
            break;
        default:
            break;
    }
}
function errorAjax(tipo) {
    switch(tipo) {
        case 'setExposure':
            $("#setExposure").prop("disabled", "");
            break;
        default:
            break;
    }
}
function doneAjax(tipo) {
    switch(tipo) {
        case 'setExposure':
            $("#setExposure").prop("disabled", "");
            break;
        default:
            break;
    }
}
function successAjax(data, tipo) {
    switch(tipo) {
        case 'dispositivos':
            $.each(data, function(key, value) {
                $.each(value, function(key2, value2) {
                    $('#dispositivo')
                        .append($("<option></option>")
                        .attr("value",value2)
                        .text(value2));
               });
            });
            break;
        case 'propiedades':
            $('#propiedad').empty();
            $.each(data, function(key, value) {
                $.each(value, function(key2, value2) {
                    $('#propiedad')
                        .append($("<option></option>")
                        .attr("value",value2)
                        .text(value2));
               });
            });
            break;
        case 'elementos':
            $('#elemento').empty();
            $.each(data, function(key, value) {
                $.each(value, function(key2, value2) {
                    $('#elemento')
                        .append($("<option></option>")
                        .attr("value",value2)
                        .text(value2));
               });
            });
            break;
        case 'valor':
            $('#valor').empty();
            $.each(data, function(key, value) {
                $('#valor').val(value);
            });  
            break;
        case 'commitValor':
            if (confirm('Esta seguro de querer cambiar el valor?')) {
                pushValor();
            } else {
                // Do nothing!
            }
            break;
        case 'pushValor':
            if ( (data['operacion']).toString() === 'true' ){
                searchViaAjaxValor();
                notify('Se ha cambiado el valor exitosamente.', 'success');
            }else{
                notify('Se ha producido un error al intentar cambiar el valor.', 'danger');
            } 
            break;
        case 'preview':
            var img = $("<img />").attr('src', '<c:url value="/resources/images/preview.jpg"/>').attr('width','20%').attr('height','20%')
            .on('load', function() {
                if (!this.complete || typeof this.naturalWidth === "undefined" || this.naturalWidth === 0) {
                    alert('broken image!');
                } else {
                    $("#previewImage").empty();
                    $("#previewImage").append(img);
                }
            });
            break; 
        case 'setRaDec':
            $('#getRa').empty();
            $('#getDec').empty();
            $.each(data, function(key, value) {
                $('#getRa').append(value[0]);
                $('#getDec').append(value[1]);
            });
            break;
        case 'refreshValues':
            updateValues(data);
            break;
        case 'getUsername':
            $('#username').empty();            
            $.each(data, function(key, value) {
                $('#username').append(value[0]);                
            });
            break;
        case 'getShifts':
            var shifts = [];           
            $.each(data, function(key, value) {
                for (i=0;i<value.length;i++){                    
                    shifts.push(value[i]);
                }
            });
            changeDisableDate( shifts );
            break;
        case 'getAllShifts':
            $("#shifts tBody").empty();
            $.each(data, function(key, value) {
                $.each(value, function(key2, c) {
                    var name="", class_label="";
                    if(c[1]=="0"){
                        name = '<td><span tkey="you">You</span></td>';
                        class_label='class="label-success"';
                    }else{
                        name = '<td>'+c[1]+'</td>';
                    }
                    if (c[3] == "0"){
                        $("#shifts tBody").append('<tr class="label-danger"><th scope="row">'+c[0]+'</th>'
                        +name
                        +'<td>'+c[2].slice(0, 10)+'</td>'
                        +'<td>'+c[2].slice(10, 16)+'</td>'
                        +'<td><span tkey="canceled">Canceled</span></td>'                        
                        +'<td>-</td>'
                        +'<td>-</td>'
                        +'<td>-</td>'
                        +'</tr>');
                    }else{
                        var transmition = '<td><span tkey="private">Private</span></td>', key = "-", link = "-";
                        if (c[4]!="-1"){
                            if(c[5]=="1"){
                                transmition = '<td><span tkey="public">Public</span></td>';
                                key = c[4];
                                link = '<a href="/rastrosoft/live?key='+key+'" tkey="link">link</a>';
                            }else{
                                transmition = '<td><span tkey="private">Private</span></td>';
                            }
                        }
                        $("#shifts tBody").append('<tr '+class_label+'><th scope="row">'+c[0]+'</th>'
                        +name
                        +'<td>'+c[2].slice(0, 10)+'</td>'
                        +'<td>'+c[2].slice(10, 16)+'</td>'
                        +'<td><span tkey="yes">Yes</td>'                        
                        +transmition
                        +'<td>'+key+'</td>'
                        +'<td>'+link+'</td>'
                        +'</tr>');
                    }                    
                });
            });            
            break;
        case 'setExposure':
            $("#setExposure").prop("disabled", "");
            break;
        case 'getCaptures':
            $("#captures tBody").empty();
            $.each(data, function(key, value) {
                $.each(value, function(key2, c) {
                    $("#captures tBody").append('<tr><th scope="row">'+c[0]+'</th>'
                        +'<td>'+c[1].slice(0, 10)+'</td>'
                        +'<td>'+c[1].slice(11, 19)+'</td>'
                        +'<td>'+decimalToHours(c[2])+'</td>'
                        +'<td>'+decimalToDegrees(c[3])+'</td>'
                        +'<td>'+c[13]+' segundos</td>'
                        +'<td>'+c[14].split("captures")[1]+'</td>'
                        +'<td><a href="#"><i class="fa fa-download" aria-hidden="true"></i></a></td><td><a href="#"><i class="fa fa-file-text-o text-success" aria-hidden="true"></i></a></td><td><a href="#"><i class="fa fa-minus text-danger" aria-hidden="true"></i></a></td>'+
                        +'</tr>');
               });
            });   
//            updateTables();            
            break;
        case 'getChat':  
            $("#chatbox").empty();
            $.each(data, function(key, value) {
                $.each(value, function(key2, c) {                        
                    $("#chatbox").append('<p style="margin: 0!important; color:'+stringToColour(c[1])+'; float:left">'+c[1]+'</p>'
                        +'<p style="font-size:10px; color:grey; float: right; padding-top:5px; margin: 0!important;">'+c[3]+'</p>'
                        +'<p style="margin: 0!important; clear: both">'+c[2]+'</p>'
                        +'<hr style="margin:5px 0 0 0!important">\n');
               });
            });      
            if ($("#chatbox").is(':empty')){
                $("#chatbox").append('<p style="color: grey">0 mensajes nuevos...</p>');
            }
            break;             
        case 'checkLive':
            var isOn = false;  
            var public = "0";
            $.each(data, function(key, value) {
                isOn    =   value[0];
                public  =   value[1];
            });
            if (isOn == "true"){
                liveOn(true, public);
            }else{
                liveOn(false, public);
            }
            break;
        case 'addShift':
            var shift_id = "-1";  
            var live_key = "-1";
            $.each(data, function(key, value) {
                shift_id  =   value[0];
                live_key  =   value[1];
            });
            //alert("Turno: "+shift_id+" - Key: "+live_key);
            notify('Shift added successfully with id: '+shift_id+' and key: '+live_key, 'success');
            break;
        case 'addSequence':
            var sequence_id = "-1";  
            var name = "-1";
            $.each(data, function(key, value) {
                sequence_id  =   value[0];
                name  =   value[1];
            });
            $("#sequence").append('<option value='+sequence_id+'>'+name+'</option>');
            $("#sequence").val(sequence_id);            
            break;   
        case 'removeSequence':
            getSequences();
            break; 
        case 'modifySequence':  
            var sequence_id = "-1";  
            var name = "-1";
            $.each(data, function(key, value) {
                sequence_id  =   value[0];
                name  =   value[1];
            });
            getSequences();                      
            break; 
        case 'getSequences':
            $("#sequence").empty();
            $.each(data, function(key, value) {
                $.each(value, function(key2, c) {
                    $("#sequence").append('<option value='+c[0]+'>'+c[1]+'</option>');
               });
            });  
            reloadSteps();
            break;        
        case 'getSteps':            
            $("#tbody-automatization").empty();
            $("#step-number").val(0);
            $.each(data, function(key, value) {
                $.each(value, function(key2, c) {
                    $("#tbody-automatization").append('<tr id="step_'+c[0]+'" stepid="'+c[0]+'" sequenceid="'+c[1]+'" state="'+c[15]+'" onclick="select_row(\'step_'+c[0]+'\');"><th scope="row">'+c[2]+'</th>'
                        +'<td>'+c[3]+'</td>'
                        +'<td>'+c[4]+'</td>'
                        +'<td>'+c[5]+'</td>'
                        +'<td>'+c[6]+'</td>'
                        +'<td>'+c[7]+'</td>'
                        +'<td>'+c[8]+'</td>'
                        +'<td>'+c[9]+'</td>'
                        +'<td>'+c[10]+'</td>'
                        +'<td>'+c[11]+'</td>'       
                        +'<td>'+c[12]+'</td>'
                        +'<td>'+c[13]+'</td>'
                        +'<td>'+c[14]+'</td>'
                        +'</tr>');
                    $("#step-number").val(c[2]);
               });
            });
            break;
        case 'addStep':
            getSteps($("#sequence").val());
            break;
        default:
            break;
    } 


}

function listaDispositivos(){
    var search = {};
    sendAjax(search,'listaDispositivos','dispositivos');
}
function listaPropiedades(){
    var search = {};
    search["device"] = $("#dispositivo").val();
    sendAjax(search,'listaPropiedades','propiedades');
}
function listaElementos(){
    var search = {};
    search["device"] = $("#dispositivo").val();
    search["property"] = $("#propiedad").val();
    sendAjax(search,'listaElementos','elementos');
}

function searchViaAjaxValor() {
    var search = {};
    search["device"] = $("#dispositivo").val();
    search["property"] = $("#propiedad").val();
    search["element"] = $("#elemento").val();
    sendAjax(search,'buscarValor','valor');
}

function commitValor() {
    var search = {};
    search["device"] = $("#dispositivo").val();
    search["property"] = $("#propiedad").val();
    search["element"] = $("#elemento").val();
    search["value"] = $("#valor").val();
    sendAjax(search,'commitValor','commitValor');  
}
function pushValor() {
    var search = {};
    search["device"] = $("#dispositivo").val();
    search["property"] = $("#propiedad").val();
    sendAjax(search,'pushValor','pushValor');  		

}
function test() {
    var search = {};
    sendAjax(search,'test','test');  
}
//Telescope functions
function setRaDec() {
    var search = {};
    search["value"] = hoursToDecimal($("#setRa").val());
//    search["value2"] = degreesToDecimal($("#setDec").val());
    search["value2"] = hoursToDecimal($("#setDec").val());
    sendAjax(search,'setRaDec','setRaDec');  
}
function setPark() {
    var search = {};
    sendAjax(search,'setPark','setPark');  
}
function setUnPark() {
    var search = {};
    sendAjax(search,'setUnPark','setUnPark');  
}
function setTrack() {
    var search = {};
    sendAjax(search,'setTrack','setTrack');  
}
function setSlew() {
    var search = {};
    sendAjax(search,'setSlew','setSlew');  
}
function setSync() {
    var search = {};
    sendAjax(search,'setSync','setSync');  
}
function setAbortMotion() {
    var search = {};
    sendAjax(search,'setAbortMotion','setAbortMotion');  
}
//CCD functions
function setUploadDirectory() {
    var search = {};
    search["value"] = $("#uploadDir").val();
    sendAjax(search,'setUploadDirectory','setUploadDirectory');  
}
function setPrefix() {
    var search = {};
    search["value"] = $("#uploadPrefix").val();
    sendAjax(search,'setPrefix','setPrefix');  
}
function setBinning() {
    var search = {};
    search["value"] = $("#hBinning").val();
    search["value2"] = $("#vBinning").val();
    sendAjax(search,'setBinning','setBinning');  
}
function setFrameType() {
    var search = {};
    search["value"] = $("#frameType").val();
    sendAjax(search,'setFrameType','setFrameType');  
}
function setCcdTemperature() {
    var search = {};
    search["value"] = $("#ccdTemperature").val();
    sendAjax(search,'setCcdTemperature','setCcdTemperature');  
}
function setFrame() {
    var search = {};
    search["value"] = $("#frameX").val();
    search["value2"] = $("#frameY").val();
    sendAjax(search,'setFrame','setFrame');  
}
function setSize() {
    var search = {};
    search["value"] = $("#frameWidth").val();
    search["value2"] = $("#frameHeight").val();
    sendAjax(search,'setSize','setSize');  
}
function setExposure() {
    var search = {};
    search["value"] = $("#exposureTime").val();
    sendAjax(search,'setExposure','setExposure');  
}
function setAbortExposure() {
    var search = {};
    sendAjax(search,'setAbortExposure','setAbortExposure');  
}
//Focuser
function setFocusAbsolute() {
    var search = {};
    search["value"] = $("#focusAbsolute").val();
    sendAjax(search,'setFocusAbsolute','setFocusAbsolute');  
}
function focusIn() {
    var search = {};
    search["value"] = $("#focusRelative").val();
    sendAjax(search,'focusIn','focusIn');  
}
function focusOut() {
    var search = {};
    search["value"] = $("#focusRelative").val();
    sendAjax(search,'focusOut','focusOut');  
}
//
function refreshValues() {
    var search = {};
    sendAjax(search,'refreshValues','refreshValues');  
}

function getUsername() {
    var search = {};
    sendAjax(search,'getUsername','getUsername');  
}

function getShifts() {
    var search = {};
    sendAjax(search,'getShifts','getShifts');  
}
function getAllShifts() {
    var search = {};
    sendAjax(search,'getAllShifts','getAllShifts');  
}

function initialize() {
    var search = {};
    sendAjax(search,'initialize','initialize');  
}

function getCaptures() {    
    var search = {};
    search["value"] = 1;
    sendAjax(search,'getCaptures','getCaptures');  
}
function getChat() {    
    var search = {};
    search["value"] = 1;
    sendAjax(search,'getChat','getChat');  
}
function addMessageChat() {    
    var search = {};
    search["value"] = $("#msgbox").val();
    $("#msgbox").val('');
    sendAjax(search,'addMessageChat','addMessageChat');     
}

function startLiveTransmit() {    
    var search = {};
    search["value"] = "true";    
    sendAjax(search,'liveTransmit','liveTransmit');     
}
function stopLiveTransmit() {    
    var search = {};
    search["value"] = "false";    
    sendAjax(search,'liveTransmit','liveTransmit');     
}
function enableChat() {    
    var search = {};
    search["value"] = "true";    
    sendAjax(search,'enableChat','enableChat');     
}
function disableChat() {    
    var search = {};
    search["value"] = "false";    
    sendAjax(search,'enableChat','enableChat');     
}
// Sequences
function addSequence(name) {    
    var search = {};
    search["value"] = name;    
    sendAjax(search,'addSequence','addSequence');     
}
function removeSequence(id) {    
    var search = {};
    search["value"] = id;    
    sendAjax(search,'removeSequence','removeSequence');     
}
function modifySequence(id, name) {    
    var search = {};
    search["value"] = id;    
    search["value2"] = name;
    sendAjax(search,'modifySequence','modifySequence');     
}
function getSequences() {    
    var search = {};
    search["value"] = 1;
    sendAjax(search,'getSequences','getSequences');
}
function removeStep(id) {    
    var search = {};
    search["value"] = id;    
    sendAjax(search,'removeStep','removeStep');     
}
function getSteps(sequence_id) {    
    var search = {};
    search["value"] = sequence_id;
    sendAjax(search,'getSteps','getSteps');
}
function addStep(id_sequence, number, ra, declination, exposureTime, hBinning, vBinning, frameType, x, y, width, height, focusPosition, quantity) {    
    var search = {};
    search["id_sequence"] = id_sequence;
    search["number"] = number;
    search["ra"] = ra;
    search["declination"] = declination;
    search["exposureTime"] = exposureTime;
    search["hBinning"] = hBinning;
    search["vBinning"] = vBinning;
    search["frameType"] = frameType;
    search["x"] = x;
    search["y"] = y;
    search["width"] = width;
    search["height"] = height;
    search["focusPosition"] = focusPosition;
    search["quantity"] = quantity;
    sendAjax(search,'addStep','addStep');
}
//
function updateValues(data){
    $.each(data, function(key, value) {
        var ra, dec, park, unpark, track, slew, sync, uploadDir, uploadPrefix, 
            hBinning, vBinning, ccdTemperature, frameLight, frameBias, frameDark, 
            frameFlat, x, y, width, height, exposureTime, focusIn, focusOut, 
            focusAbsolute, focusRelative, filePath, isOnLive, chatEnabled, currentShiftUser, timeleft;
        ra              = value[0];
        dec             = value[1];
        park            = value[2];
        unpark          = value[3];
        track           = value[4];
        slew            = value[5];
        sync            = value[6];
        uploadDir       = value[7];
        uploadPrefix    = value[8];
        hBinning        = value[9];
        vBinning        = value[10];
        ccdTemperature  = value[11];        
        frameLight      = value[12];
        frameBias       = value[13];
        frameDark       = value[14];
        frameFlat       = value[15];
        x               = value[16];
        y               = value[17];
        width           = value[18];
        height          = value[19];
        exposureTime    = value[20];
        focusIn         = value[21];
        focusOut        = value[22];
        focusAbsolute   = value[23];
        focusRelative   = value[24];
        filePath        = value[25];
        isOnLive        = value[26];
        chatEnabled     = value[27];
        currentShiftUser= value[28];
        timeleft        = value[29];
        
        if (($('#setRa').is(":focus")===false)&($('#setDec').is(":focus")===false)){
            $('#setRa').val(decimalToHours(ra));
            $('#setDec').val(decimalToDegrees(dec));
        }
        
        
        if (park==="ON"){
            $("#park").addClass('selected');
            $("#unpark").removeClass('selected');
        }else if (unpark==="ON"){
            $("#unpark").addClass('selected');
            $("#park").removeClass('selected');
        }
        
        
        if (track==="ON"){
            $("#track").addClass('selected');
            $("#slew").removeClass('selected');
            $("#sync").removeClass('selected');
        }else if (slew==="ON"){
            $("#track").removeClass('selected');
            $("#slew").addClass('selected');
            $("#sync").removeClass('selected');
        }else if (sync==="ON"){
            $("#track").removeClass('selected');
            $("#slew").removeClass('selected');
            $("#sync").addClass('selected');
        }
        
        if (($('#uploadDir').is(":focus")===false))
            $("#uploadDir").val(uploadDir);
        if (($('#uploadPrefix').is(":focus")===false))
            $("#uploadPrefix").val(uploadPrefix);
        
        if (($('#hBinning').is(":focus")===false)&($('#vBinning').is(":focus")===false)){
            $("#hBinning").val(hBinning);
            $("#vBinning").val(vBinning);
        }    
            
          
        if (($('#frameType').is(":focus")===false)){
            if (frameLight==="ON"){
                $('#frameType option[value="frameLight"]').prop('selected', true);
            }else if(frameBias==="ON"){
                $('#frameType option[value="frameBias"]').prop('selected', true);
            }else if(frameDark==="ON"){
                $('#frameType option[value="frameDark"]').prop('selected', true);
            }else if(frameFlat==="ON"){
                $('#frameType option[value="frameFlat"]').prop('selected', true);

            }
        }
                
        if (($('#ccdTemperature').is(":focus")===false))
            $("#ccdTemperature").val(Math.round( ccdTemperature * 100 ) / 100);    
        
        if (($('#frameX').is(":focus")===false)&($('#frameY').is(":focus")===false)){
            $('#frameX').val(Math.round( x * 1 ) / 1);
            $('#frameY').val(Math.round( y * 1 ) / 1);
        }; 
        
        if (($('#frameWidth').is(":focus")===false)&($('#frameHeight').is(":focus")===false)){
            $('#frameWidth').val(Math.round( width * 1 ) / 1);
            $('#frameHeight').val(Math.round( height * 1 ) / 1);
        }; 
        
        
        $('#exposureTimeHidden').val(Math.floor(exposureTime)).trigger('change');
        
        
        if (focusIn==="ON"){
            $("#focusIn").addClass('selected');
            $("#focusOut").removeClass('selected');
        }else if (focusOut==="ON"){
            $("#focusOut").addClass('selected');
            $("#focusIn").removeClass('selected');
        }
        
        if (($('#focusAbsolute').is(":focus")===false))
            $("#focusAbsolute").val(Math.round( focusAbsolute * 1 ) / 1);    
        
        if (($('#focusRelative').is(":focus")===false))
            $("#focusRelative").val(Math.round( focusRelative * 1 ) / 1);
        
        $("#filePath").val(filePath);
//        showCapture();
        updateNewImageCapture(filePath+".jpg");
        
        if(isOnLive == "1"){
            $("#liveTransmitSpan").css("color","rgb(255, 0, 0)");
        }else{
            $("#liveTransmitSpan").css("color","rgb(0, 0, 0)");
        }
        
        if(chatEnabled == "1"){
            $("#sendMsgChat").prop('disabled', false);
            $("#msgbox").prop('disabled', false);
        }else{
            $("#sendMsgChat").prop('disabled', true);
            $("#msgbox").prop('disabled', true);
        }
        
        $( "#timeleft" ).empty();
        $( "#timeleft" ).append(timeleft);
        startTimer(timeleft);
        
        if( parseInt(($("#timeleft").text()).substring(3, 5)) < 10 ){
            $("#timeleft").removeClass("label-success").addClass("label-danger");
            if( $("#timeleft").text() == "00:00:00" ){
                $("#end_session").submit();
            }
        }else{
            $("#timeleft").removeClass("label-danger").addClass("label-success");
        }
        
    });
}  
   
function startTimer(duration) {
    var aux = duration.split(":");
    duration = ((parseInt(aux[0])*3600) + (parseInt(aux[1])*60) + (parseInt(aux[2])));
    
    var timer = duration, minutes, seconds;
    setInterval(function () {
        minutes = parseInt(timer / 60, 10);
        seconds = parseInt(timer % 60, 10);

        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;

        $( "#timeleft" ).empty();
        $( "#timeleft" ).append("00:" + minutes + ":" + seconds);
        
        if( parseInt(($("#timeleft").text()).substring(3, 5)) < 10 ){
            if ( new String($("#timeleft").text()).valueOf() == new String("00:09:59").valueOf() ){
                notify('You have 10 minutes until the session ends!', 'danger');
            }
            $("#timeleft").removeClass("label-success").addClass("label-danger");
            if( $("#timeleft").text() == "00:00:00" ){
                $("#end_session").submit();
            }
        }else{
            $("#timeleft").removeClass("label-danger").addClass("label-success");
        }
        
        if (--timer < 0) {
            timer = duration;
        }
    }, 1000);
}