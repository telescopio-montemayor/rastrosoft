/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unlp.rastrosoft.web.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author ip300
 */
public class client {
    private indi_client cliente = null;
    private final String telescope = "Telescope Simulator";
    private final String ccd = "CCD Simulator";
    private final String focuser = "Focuser Simulator";
    
    public boolean modificarString(String dispositivo, String propiedad,String elemento,String valor){        
        cliente = connect_indi.connect(telescope, ccd, focuser); 
        if (false==(cliente.commitStringValor(dispositivo, propiedad, elemento, valor)))
            return false;
        return false != (cliente.pushValor(dispositivo, propiedad));
    }
    public boolean modificarBoolean(String dispositivo, String propiedad, String elemento,String valor){        
        cliente = connect_indi.connect(telescope, ccd, focuser); 
        if(false==(cliente.commitBooleanValor(dispositivo, propiedad, elemento, valor)))
            return false;
        return false != (cliente.pushValor(dispositivo, propiedad));  
    }
    public boolean modificarDouble(String dispositivo,String propiedad,String elemento,String valor){
        cliente = connect_indi.connect(telescope, ccd, focuser);
        if (false==(cliente.commitDoubleValor(dispositivo, propiedad, elemento, valor)))
            return false;
        return false != (cliente.pushValor(dispositivo, propiedad));  
    }
    
    public boolean park(){
        return this.modificarBoolean(telescope, "TELESCOPE_PARK", "PARK", "ON");
    }
    
    public boolean unPark(){
        return this.modificarBoolean(telescope, "TELESCOPE_PARK", "UNPARK", "ON");
    }
    
    public boolean track( ){
        return this.modificarBoolean(telescope, "ON_COORD_SET", "TRACK", "ON");
    }
    
    public boolean slew( ){
        return this.modificarBoolean(telescope, "ON_COORD_SET", "SLEW", "ON");
    }
    
    public boolean sync( ){
        return this.modificarBoolean(telescope, "ON_COORD_SET", "SYNC", "ON");
    }
   
    public boolean setExposure( String time ){
        return this.modificarDouble(ccd, "CCD_EXPOSURE", "CCD_EXPOSURE_VALUE", time);
        
    }
    public boolean setLocalMode( ){
        return this.modificarBoolean(ccd, "UPLOAD_MODE", "UPLOAD_LOCAL", "ON");
    }
    public boolean setUploadDirectory( String dir ){
        return this.modificarString(ccd, "UPLOAD_SETTINGS", "UPLOAD_DIR", dir);        
    }
    public boolean setPrefix( String prefix ){
        return this.modificarString(ccd, "UPLOAD_SETTINGS", "UPLOAD_PREFIX", prefix);        
    }
    public boolean abortExposure( ){
        return this.modificarBoolean(ccd, "CCD_ABORT_EXPOSURE", "ABORT", "ON");
    }
    public boolean setSize( String width, String height ){
        if( !(this.modificarDouble(ccd, "CCD_FRAME", "WIDTH", width)))
            return false;
        return false != (this.modificarDouble(ccd, "CCD_FRAME", "HEIGHT", height));
    }
    
    public boolean setRaDec( String ra , String dec ){
        cliente = connect_indi.connect(telescope, ccd, focuser);
        cliente.commitDoubleValor(telescope, "EQUATORIAL_EOD_COORD", "RA", ra);
        cliente.commitDoubleValor(telescope, "EQUATORIAL_EOD_COORD", "DEC", dec);
        cliente.pushValor(telescope, "EQUATORIAL_EOD_COORD");
        return true;
    }
    
    
    //VERIFICAR
    private String decimalToTime (String decimal){
        double finalBuildTime = new Double (decimal);
        int hours = (int) finalBuildTime;
        int minutes = (int) (finalBuildTime * 60) % 60;
        int seconds = (int) (finalBuildTime * (60*60)) % 60;
        return ( hours +":" + minutes + ":" + seconds ); 
    }
    //VERIFICAR
    private String timeToDecimal (String time){
       
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");

        Date dt = null;
        try {
            dt = formatter.parse(time);
        } catch (ParseException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        double hr = cal.get(Calendar.HOUR_OF_DAY);
        double min = cal.get(Calendar.MINUTE)*(0.01);
        double sec = cal.get(Calendar.SECOND)*(0.001);
        double timeInDecimal = hr+min+sec;
        String decimal = String.valueOf(timeInDecimal);
       
        return decimal;
    }
        
}
