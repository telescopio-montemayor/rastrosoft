/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unlp.rastrosoft.web.model;

import java.util.logging.Level;
import java.util.logging.Logger;

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
    public boolean takeCapture( String time ){
        return this.setExposure(time);
    }
    public boolean takePreview( ){
        try {
            String path = "/home/ip300/NetBeansProjects/rastrosoft/src/main/webapp";
            String source= path+"/resources/images/fits/";
            String dest = path+"/resources/images/";
            this.setLocalMode();
            this.setUploadDirectory("/home/ip300/NetBeansProjects/rastrosoft/src/main/webapp/resources/images/fits");
            this.setPrefix("preview");
            Thread.sleep(500);
            this.modificarDouble( "CCD_EXPOSURE", "CCD_EXPOSURE_VALUE", "1");
            Thread.sleep(1500);
            new Fits().fitsToJpg(source, dest, "preview.fits");
            Thread.sleep(1500);
            this.setPrefix("IMAGE_XXX");
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Ccd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public String getUploadDirectory(){
        cliente = connect_indi.connect(dispositivo);
        return (cliente.enviar_mensaje(dispositivo, "UPLOAD_SETTINGS", "UPLOAD_DIR"));
    }
    public String getUploadPrefix(){
        cliente = connect_indi.connect(dispositivo);
        return (cliente.enviar_mensaje(dispositivo, "UPLOAD_SETTINGS", "UPLOAD_PREFIX"));
    }
    
    public String getHBinning(){
        cliente = connect_indi.connect(dispositivo);
        return (cliente.enviar_mensaje(dispositivo, "CCD_BINNING", "HOR_BIN"));
    }
    public String getVBinning(){
        cliente = connect_indi.connect(dispositivo);
        return (cliente.enviar_mensaje(dispositivo, "CCD_BINNING", "VER_BIN"));
    }
    public String getTemperature(){
        cliente = connect_indi.connect(dispositivo);
        return (cliente.enviar_mensaje(dispositivo, "CCD_TEMPERATURE", "CCD_TEMPERATURE_VALUE"));
    }
    public String getFrameLight(){
        cliente = connect_indi.connect(dispositivo);
        return (cliente.enviar_mensaje(dispositivo, "CCD_FRAME_TYPE", "FRAME_LIGHT"));
    }
    public String getFrameBias(){
        cliente = connect_indi.connect(dispositivo);
        return (cliente.enviar_mensaje(dispositivo, "CCD_FRAME_TYPE", "FRAME_BIAS"));
    }
    public String getFrameDark(){
        cliente = connect_indi.connect(dispositivo);
        return (cliente.enviar_mensaje(dispositivo, "CCD_FRAME_TYPE", "FRAME_DARK"));
    }
    public String getFrameFlat(){
        cliente = connect_indi.connect(dispositivo);
        return (cliente.enviar_mensaje(dispositivo, "CCD_FRAME_TYPE", "FRAME_FLAT"));
    }
    
}
