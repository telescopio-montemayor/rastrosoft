function decimalToDegrees(D){
    return [0|D, '\xB0 ', 0|(D<0?D=-D:D)%1*60, "' ", 0|(Math.round(((D<0?D=-D:D)*60%1*60)* 1 )/1 ), '" '].join('');
}
function decimalToHours(D){
    var n = new Date(0,0);
    n.setSeconds(Math.round( (+D * 60 * 60) * 1 )/1 );
    return(n.toTimeString().slice(0, 8));
}
function degreesToDecimal(d) {    

    var coord = d.split("\xB0");
    var degrees = parseFloat(coord[0]);
    var minutes = parseFloat(coord[1].split("'")[0]);
    var seconds = parseFloat(coord[1].split("'")[1].split('"')[0]);
    
    if (d.charAt(0)==='-'){
        degrees = (degrees *(-1));        
        return (((parseFloat(degrees) + (parseFloat(minutes)/60.0) + (parseFloat(seconds)/3600.0)))*(-1));
    }else{
        return ((parseFloat(degrees) + (parseFloat(minutes)/60.0) + (parseFloat(seconds)/3600.0)));
    }

}
function hoursToDecimal(H){
    var a = H.split(':'); 
    return ((parseFloat(a[0]) + (parseFloat(a[1])/60.0) + (parseFloat(a[2])/3600.0)));
}