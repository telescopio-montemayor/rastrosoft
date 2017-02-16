/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unlp.rastrosoft.web.model;



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
    
}
