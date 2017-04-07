function displayTipo(data, tipo) {
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
        case 'initialize':
            break;
        case 'test':
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
//function previewImage() {
//    var search = {};
//    sendAjax(search,'previewImage','preview');  
//}
function test() {
    var search = {};
    sendAjax(search,'test','test');  
}
function setRaDec() {
    var search = {};
    search["value"] = hoursToDecimal($("#setRa").val());
    search["value2"] = degreesToDecimal($("#setDec").val());
//    var point = new GeoPoint($("#setDec").val(), null);
//    search["value2"] = point.getLonDec();
    sendAjax(search,'setRaDec','setRaDec');  
}
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

function initialize() {
    var search = {};
    sendAjax(search,'initialize','initialize');  
}

function updateValues(data){
    $.each(data, function(key, value) {
        var ra, dec, park, unpark, track, slew, sync, uploadDir, uploadPrefix, 
            hBinning, vBinning, ccdTemperature, frameLight, frameBias, frameDark, 
            frameFlat, x, y, width, height, exposureTime, focusIn, focusOut, 
            focusAbsolute, focusRelative;
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
        
        if (($('#setRa').is(":focus")===false)&($('#setDec').is(":focus")===false)){
            $('#setRa').val(decimalToHours(ra));
            $('#setDec').val(decimalToDegrees(dec));
        }; 
        
        
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
        if (($('#hBinning').is(":focus")===false))    
            $("#hBinning").val(hBinning);
        if (($('#vBinning').is(":focus")===false))
            $("#vBinning").val(vBinning);
          
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
            $("#ccdTemperature").val(ccdTemperature);    
        
        if (($('#frameX').is(":focus")===false)&($('#frameY').is(":focus")===false)){
            $('#frameX').val(x);
            $('#frameY').val(y);
        }; 
        
        if (($('#frameWidth').is(":focus")===false)&($('#frameHeight').is(":focus")===false)){
            $('#frameWidth').val(width);
            $('#frameHeight').val(height);
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
            $("#focusAbsolute").val(focusAbsolute);    
        
        if (($('#focusRelative').is(":focus")===false))
            $("#focusRelative").val(focusRelative);    
        
      
    });
}