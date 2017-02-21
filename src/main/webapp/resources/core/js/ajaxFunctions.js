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
    search["dispositivo"] = $("#dispositivo").val();
    sendAjax(search,'listaPropiedades','propiedades');
}
function listaElementos(){
    var search = {};
    search["dispositivo"] = $("#dispositivo").val();
    search["propiedad"] = $("#propiedad").val();
    sendAjax(search,'listaElementos','elementos');
}

function searchViaAjaxValor() {
    var search = {};
    search["dispositivo"] = $("#dispositivo").val();
    search["propiedad"] = $("#propiedad").val();
    search["elemento"] = $("#elemento").val();
    sendAjax(search,'buscarValor','valor');
}

function commitValor() {
    var search = {};
    search["dispositivo"] = $("#dispositivo").val();
    search["propiedad"] = $("#propiedad").val();
    search["elemento"] = $("#elemento").val();
    search["valor"] = $("#valor").val();
    sendAjax(search,'commitValor','commitValor');  
}
function pushValor() {
    var search = {};
    search["dispositivo"] = $("#dispositivo").val();
    search["propiedad"] = $("#propiedad").val();
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
