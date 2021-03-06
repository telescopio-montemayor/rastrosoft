function successAjax(data, tipo) {
    switch(tipo) {
        case 'getUsername':
            $('#username').empty();            
            $.each(data, function(key, value) {
                $('#username').append(value[0]);                
            });
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
        case 'getModerationShifts':
            $("#shifts tBody").empty();
            $.each(data, function(key, value) {
                $.each(value, function(key2, c) {
                    var state = c[7], pending="", accepted="", rejected="", actual_class="";
                    switch(state){
                        case "0":
                            rejected = "selected";
                            actual_class = "bg-danger-m";
                            break;
                        case "1":
                            accepted = "selected";
                            actual_class = "bg-success-m";
                            break;
                        case "2":
                            pending = "selected";
                            actual_class = "bg-warning-m";
                            break;
                        default:
                            break;
                    }
                    $("#shifts tBody").append('<tr><th scope="row">'+c[0]+'</th>'
                        +'<td>'+c[1]+'</td>'
                        +'<td>'+c[2]+'</td>'
                        +'<td>'+c[3]+'</td>'
                        +'<td>'+c[4]+'</td>'
                        +'<td>'+c[6].slice(0, 10)+'</td>'
                        +'<td>'+c[6].slice(10, 16)+'</td>'
                        +'<td><select id="operation-shift-'+c[0]+'" onchange="operationShift(\''+c[0]+'\');" class=" '+actual_class+' form-control input-sm"><option value="pending" '+pending+' class="bg-warning">Pending</option><option value="accepted" '+accepted+' class="bg-success">Accepted</option><option value="rejected" '+rejected+' class="bg-danger">Rejected</option></select></td>'
                        +'</tr>');
                });
            });            
            break;    
        case 'setToPendingShift':
            getPendingShifts();
            break;
        case 'acceptShift':
            $.each(data, function(key, c) {
                if (c[0] == "1"){
                    getAcceptedShifts();
                    notify('Shift accepted successfuly.', 'success');
                }else if (c[0] == "0"){
                    alert("Error accepting shift. There is already a  shift in that date and time, you need to cancel the previous shift with id = "+c[1]);
                }
            });
            
            break;
        case 'rejectShift':
            getRejectedShifts();
            break;
        case 'getModerationUsers':
            $("#users tBody").empty();
            $.each(data, function(key, value) {
                $.each(value, function(key2, c) {
                    var role = c[6], advanced="", user="", spectator="", moderator="", actual_class="", credits="-";
                    switch(role){
                        case "2":
                            credits=c[5];
                            advanced = "selected";
                            actual_class = "bg-advanced-m";
                            break;
                        case "3":                            
                            user = "selected";
                            actual_class = "bg-user-m";
                            break;
                        case "4":
                            spectator = "selected";
                            actual_class = "bg-spectator-m";
                            break;
                        case "5":
                            moderator = "selected";
                            actual_class = "bg-moderator-m";
                            break;    
                        default:
                            break;
                    }
                    var enabled = (c[7]=="1")?"<a href=\"#\" onclick=\"operationEnabled('0','"+c[0]+"');\"><i class=\"fa fa-toggle-on\" aria-hidden=\"true\" style=\"color:\"></i></a>":"<a href=\"#\" onclick=\"operationEnabled('1','"+c[0]+"');\"><i class=\"fa fa-toggle-off\" aria-hidden=\"true\" style=\"color:\"></i></a>";
                    $("#users tBody").append('<tr><th scope="row">'+c[0]+'</th>'
                        +'<td>'+c[1]+'</td>'
                        +'<td>'+c[2]+'</td>'
                        +'<td>'+c[3]+'</td>'
                        +'<td>'+c[4]+'</td>'
                        +'<td><div class="input-group" style="width:90px"><input class="form-control " id="operation-credits-'+c[0]+'" placeholder="Credits" type="text" value="'+credits+'"><span class="input-group-btn"><button class="btn btn-default" type="button" onclick="operationCredits(\''+c[0]+'\');"><i aria-hidden="true" class="fa fa-ticket"></i></button></span></div></td>'
                        +'<td>'+enabled+'</td>'
                        +'<td><select id="operation-role-'+c[0]+'" onchange="operationRole(\''+c[0]+'\');" class="'+actual_class+' form-control input-sm"><option value="advanced" '+advanced+' class="bg-advanced-m">Advanced</option><option value="user" '+user+' class="bg-user-m">Basic user</option><option value="spectator" '+spectator+' class="bg-spectator-m">Spectator</option><option value="moderator" '+moderator+' class="bg-moderator-m">Moderator</option></select></td>'
                        +'</tr>');
                });
            });            
            break; 
        case 'modifyRoleUser':
            $.each(data, function(key, c) {
                if (c[0]=="1"){
                    alert("Se ha modificado el rol del usuario correctamente.");
                }else{
                    alert("Ha ocurrido un error al modificar el rol del usuario.");
                }
            });
            getAllModerationUsers();
            break;
        case 'modifyCreditsUser':
            $.each(data, function(key, c) {
                if (c[0]=="1"){
                    alert("Se han modificado los creditos del usuario correctamente.");
                }else{
                    alert("Ha ocurrido un error al modificar los creditos del usuario.");
                }
            });
            getAllModerationUsers();
            break;
        case 'modifyEnabledUser':
            $.each(data, function(key, c) {
                if (c[0]=="1"){
                    alert("Se han modificado al usuario correctamente.");
                }else{
                    alert("Ha ocurrido un error al modificar el usuario.");
                }
            });
            getAllModerationUsers();
            break;
        default:
            break;
    }
}
function operationEnabled(op, id){
    var msg = (op=="1")?"Seguro que desea desbloquear al usuario #"+id+"?":"Seguro que desea bloquear al usuario #"+id+"?";
    if (confirm(msg)){
        var search = {};
        search["value"] = id;
        search["value2"] = op;
        sendAjax(search,'modifyEnabledUser','modifyEnabledUser'); 
    }
}
function operationCredits(id){
    var selectId = "operation-credits-"+id;
    var credits = $("#"+selectId).val();
    if(!$.isNumeric(credits))
        alert("Ingrese un valor numerico por favor.");
    else{
        var search = {};
        search["value"] = id;
        search["value2"] = credits;
        sendAjax(search,'modifyCreditsUser','modifyCreditsUser'); 
    }
}
function operationShift(id){
    var selectId = "operation-shift-"+id;
    var operation = $("#"+selectId).val();
    var search = {};
    search["value"] = id;
    switch (operation){
        case "pending":
            sendAjax(search,'setToPendingShift','setToPendingShift'); 
            break;
        case "accepted":
            sendAjax(search,'acceptShift','acceptShift'); 
            break;
        case "rejected":
            sendAjax(search,'rejectShift','rejectShift'); 
            break;
        default:
            break;
    }
}
function operationRole(id){
    if (confirm("Esta seguro que desea cambiar el rol del usuario: #"+id+"?")){
        var selectId = "operation-role-"+id;
        var operation = $("#"+selectId).val();
        var search = {};
        search["value"] = id;
        search["value2"]= $("#"+selectId).val();
        sendAjax(search,'modifyRoleUser','modifyRoleUser'); 
    }
}
function getUsername() {
    var search = {};
    sendAjax(search,'getUsername','getUsername');  
}

function getAllModerationUsers() {
    var search = {};
    sendAjax(search,'getAllModerationUsers','getModerationUsers');  
}
function getBannedModerationUsers() {
    var search = {};
    sendAjax(search,'getBannedModerationUsers','getModerationUsers');  
}
function getZeroCreditsModerationUsers() {
    var search = {};
    sendAjax(search,'getZeroCreditsModerationUsers','getModerationUsers');  
}
function getAllModerationShifts() {
    var search = {};
    sendAjax(search,'getAllModerationShifts','getModerationShifts');  
}
function getPendingShifts() {
    var search = {};
    sendAjax(search,'getPendingShifts','getModerationShifts');  
}
function getAcceptedShifts() {
    var search = {};
    sendAjax(search,'getAcceptedShifts','getModerationShifts');  
}
function getRejectedShifts() {
    var search = {};
    sendAjax(search,'getRejectedShifts','getModerationShifts');  
}
function showUsers(){
    $("#users-menu").addClass("active");        
    $('.table-users').show();
    $('.table-shifts').hide();
    $('.intro').hide();
    
}
function showAllUsers(){
    showUsers();
    getAllModerationUsers();
    updateTableUsers();
}
function showBannedUsers(){
    showUsers();
    getBannedModerationUsers();
    updateTableUsers();
}
function showZeroCreditsUsers(){
    showUsers();
    getZeroCreditsModerationUsers();
    updateTableUsers();
}
function showShifts(){
    $("#shifts-menu").addClass("active");        
    $('.table-users').hide();
    $('.table-shifts').show();
    $('.intro').hide();
}
function showAllModerationShifts(){
    showShifts();
    getAllModerationShifts();
    updateTableShifts();
}
function showPendingShifts(){
    showShifts();
    getPendingShifts();
    updateTableShifts();
}
function showAcceptedShifts(){
    showShifts();
    getAcceptedShifts();
    updateTableShifts();
}
function showRejectedShifts(){
    showShifts();
    getRejectedShifts();
    updateTableShifts();
}