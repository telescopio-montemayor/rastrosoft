/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unlp.rastrosoft.web.model;


import java.io.File;
import static java.lang.Integer.parseInt;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Timer;
import java.util.TimerTask;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import unlp.rastrosoft.websocket.DeviceSessionHandler;

/**
 *
 * @author ip300
 */
public class Ccd extends Device {
    
    public Ccd (){
        super("CCD Simulator");
    }
    public boolean setBinning(String hor, String ver){
        cliente = connect_indi.connect(dispositivo);
        cliente.commitDoubleValor(dispositivo, "CCD_BINNING", "HOR_BIN", hor);
        cliente.commitDoubleValor(dispositivo, "CCD_BINNING", "VER_BIN", ver);
        cliente.pushValor(dispositivo, "CCD_BINNING");
        return true;
    }
    public boolean setFrame(String x, String y){
        if( !(this.modificarDouble("CCD_FRAME", "X", x)))
            return false;
        return false != (this.modificarDouble("CCD_FRAME", "Y", y));
    }    
    String olderFilePath = this.getFilePath();
    public boolean setExposure( String time ){
        this.modificarDouble( "CCD_EXPOSURE", "CCD_EXPOSURE_VALUE", time);        
        try {
            Thread.sleep(parseInt(time) * 1000);
            } catch (InterruptedException ex) {            
            }
        return (this.convertFits());        
    }    
    public boolean setFrameLight(){        
        return this.modificarBoolean("CCD_FRAME_TYPE", "FRAME_LIGHT", "ON");
    }
    public boolean setFrameBias(){        
        return this.modificarBoolean("CCD_FRAME_TYPE", "FRAME_BIAS", "ON");
    }
    public boolean setFrameDark(){        
        return this.modificarBoolean("CCD_FRAME_TYPE", "FRAME_DARK", "ON");
    }
    public boolean setFrameFlat(){        
        return this.modificarBoolean("CCD_FRAME_TYPE", "FRAME_FLAT", "ON");
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
    public boolean setSize( String width, String height ){
        if( !(this.modificarDouble("CCD_FRAME", "WIDTH", width)))
            return false;
        return false != (this.modificarDouble("CCD_FRAME", "HEIGHT", height));
    }
    public boolean setTemperature( String temperature){
        return this.modificarDouble("CCD_TEMPERATURE", "CCD_TEMPERATURE_VALUE", temperature);
    }
    
    
    
    public boolean abortExposure( ){
        return this.modificarBoolean("CCD_ABORT_EXPOSURE", "ABORT", "ON");
    }
    public boolean takeCapture( String time ){
        this.setExposure(time);
        return true;
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
    public String getFrameType(){
        if (this.getFrameLight().equals("ON")){
            return "light";
        }
        if (this.getFrameBias().equals("ON")){
            return "bias";
        }
        if (this.getFrameDark().equals("ON")){
            return "dark";
        }
        if (this.getFrameFlat().equals("ON")){
            return "flat";
        }
        return null;
    }
    public String getX(){
        cliente = connect_indi.connect(dispositivo);
        return (cliente.enviar_mensaje(dispositivo, "CCD_FRAME", "X"));
    }
    public String getY(){
        cliente = connect_indi.connect(dispositivo);
        return (cliente.enviar_mensaje(dispositivo, "CCD_FRAME", "Y"));
    }
    public String getWidth(){
        cliente = connect_indi.connect(dispositivo);
        return (cliente.enviar_mensaje(dispositivo, "CCD_FRAME", "WIDTH"));
    }
    public String getHeight(){
        cliente = connect_indi.connect(dispositivo);
        return (cliente.enviar_mensaje(dispositivo, "CCD_FRAME", "HEIGHT"));
    }    
    public String getExposureTime(){
        cliente = connect_indi.connect(dispositivo);
        return (cliente.enviar_mensaje(dispositivo, "CCD_EXPOSURE", "CCD_EXPOSURE_VALUE"));
    }   
    public String getFilePath(){
        cliente = connect_indi.connect(dispositivo);
        return (cliente.enviar_mensaje(dispositivo, "CCD_FILE_PATH", "FILE_PATH"));
    }  
    
    
    
    
    public boolean convertFits(){
            try {
                Thread.sleep(1500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Ccd.class.getName()).log(Level.SEVERE, null, ex);
            }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = "default";
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();            
        }
        String path = "/home/ip300/NetBeansProjects/rastrosoft/src/main/webapp/captures";
        String source= path+"/"+currentUserName;
        String dest = path+"/"+currentUserName;
        this.setUploadDirectory(source);
        this.setLocalMode();        
        File fits = null;
        int count=0;
        String newFilePath = this.getFilePath();
        while (olderFilePath.equals(newFilePath)){
            newFilePath = this.getFilePath();
            count++;
            if (count>10000){  //block infinite loop
                return false;
            }
        }
        olderFilePath = newFilePath;
//            try {
//                Thread.sleep(1500);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Ccd.class.getName()).log(Level.SEVERE, null, ex);
//            }
            waitUntilFileIsAvailable(newFilePath);
            
        fits = findFile(newFilePath);
        new Fits().fitsToJpg(source+"/", dest+"/", fits.getName());
//            try {
//                Thread.sleep(1500);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Ccd.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            waitUntilFileIsAvailable(source+"/"+fits.getName());
            alertWhenFileIsAvailable(source+"/"+fits.getName()+".jpg");
//        count=0;
//        String newJpg = newFilePath+".jpg";
//        while (findFile(newJpg)==null){
//            count++;
//            if (count>10000){  //block infinite loop
//                return false;
//            }
//        }            
        return true;
    }
    public static File findFile(String absolutePath){
        Path p = Paths.get(absolutePath);
        if (p!=null){
            return (new File(p.toString()));
        }else{
            return null;
        }
    }   
    
    public static boolean isFileAvailable(String filePathString){
        File f = new File(filePathString);
        if(f.exists() && !f.isDirectory()) { 
            return true;
        }
        return false;
    }
    public void waitUntilFileIsAvailable(String filePathString){
        boolean wait = !isFileAvailable(filePathString); 
        while(wait){
            try {
                Thread.sleep(500);
                wait = !isFileAvailable(filePathString);
            } catch (InterruptedException ex) {
                Logger.getLogger(Ccd.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void alertWhenFileIsAvailable(String filePathString){
        TimerTask task;
        Timer timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                if(isFileAvailable(filePathString)){
                    DeviceSessionHandler sessionHandler;
                    sessionHandler = new DeviceSessionHandler();
                    sessionHandler.updateElement("newCapture", filePathString);
                    this.cancel();
                    timer.cancel();
                }
            }
        };
        timer.schedule(task, 0,00500);
    }
}
