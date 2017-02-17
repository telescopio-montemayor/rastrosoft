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
public class Ccd extends Device {
    
    public Ccd (){
        super("CCD Simulator");
    }
    
    public boolean setExposure( String time ){
        return this.modificarDouble( "CCD_EXPOSURE", "CCD_EXPOSURE_VALUE", time);
        
    }
    public boolean setLocalMode( ){
        return this.modificarBoolean("UPLOAD_MODE", "UPLOAD_LOCAL", "ON");
    }
    public boolean setUploadDirectory( String dir ){
        return this.modificarString("UPLOAD_SETTINGS", "UPLOAD_DIR", dir);        
    }
    public boolean setPrefix( String prefix ){
        return this.modificarString("UPLOAD_SETTINGS", "UPLOAD_PREFIX", prefix);        
    }
    public boolean abortExposure( ){
        return this.modificarBoolean("CCD_ABORT_EXPOSURE", "ABORT", "ON");
    }
    public boolean setSize( String width, String height ){
        if( !(this.modificarDouble("CCD_FRAME", "WIDTH", width)))
            return false;
        return false != (this.modificarDouble("CCD_FRAME", "HEIGHT", height));
    }
}
