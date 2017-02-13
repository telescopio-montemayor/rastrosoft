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
    private String telescope = "Telescope Simulator";
    private String ccd = "CCD Simulator";
    private String focuser = "Focuser Simulator";
    
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
}
