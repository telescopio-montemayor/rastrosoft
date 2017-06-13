function alertSuccess(bool, title, msg) {
        $("#alert-label").empty();
        text = "<strong>"+ title +"</strong> " + msg;
        $("#alert-label").append(text);                
        if (bool){
            $("#alert-label").removeClass("alert-danger");
            $("#alert-label").addClass("alert-success");
        }else{
            $("#alert-label").removeClass("alert-success");
            $("#alert-label").addClass("alert-danger");
        }
        $("#alert-label").show( "slow" );
        var delay=5000; //5 segundos
        setTimeout(function() {
          $("#alert-label").hide( "slow" );
        }, delay);

}

function notify(msg, type){
    $.notify({
                    // options
                    message: msg 
            },{
                    // settings
                    type: type,
                    placement: {
                        from: "top",
                        align: "center"
                    },
                    animate: {
                        enter: 'animated fadeInDown',
                        exit: 'animated fadeOutUp'
                    }
            });
}