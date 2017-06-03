/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unlp.rastrosoft.web.model;

import java.util.List;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author ip300
 */
public class Step {
    private int id, id_sequence, number, quantity, delay, state;
    private String ra, declination, exposureTime, hBinning, vBinning, frameType, x, y, width, height, focusPosition;

    public Step(int id, int id_sequence, int number, String ra, String declination, String exposureTime, String hBinning, String vBinning, String frameType, String x, String y, String width, String height, String focusPosition, int quantity, int delay, int state) {
        this.id = id;
        this.id_sequence = id_sequence;
        this.number = number;
        this.quantity = quantity;
        this.delay = delay;
        this.state = state;
        this.ra = ra;
        this.declination = declination;
        this.exposureTime = exposureTime;
        this.hBinning = hBinning;
        this.vBinning = vBinning;
        this.frameType = frameType;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.focusPosition = focusPosition;
    }

    public int durationTime(){
        return (
                    (
                        Integer.parseInt(this.getExposureTime()) + 
                        this.getDelay()
                    )*   
                    this.getQuantity()
                );
    }
    
    public Boolean isExecutable(){
        int id_user = -1;
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            UserDB userDB = new UserDB();
            userDB.connect();
            User user = userDB.getUser(username);
            id_user = user.getUserId();
        }
        
        CalendarDB shift = new CalendarDB();
        shift.connect();
        List<String> currentShift = shift.getCurrentShift();
        int timeLeft = 0;
        if (currentShift.size()>0 && currentShift.get(0).equals(String.valueOf(id_user))){
            String timeLeftHHmmss =currentShift.get(1);
            String[] tokens = timeLeftHHmmss.split(":");
            int hours = Integer.parseInt(tokens[0]);
            int minutes = Integer.parseInt(tokens[1]);
            int seconds = Integer.parseInt(tokens[2]);
            timeLeft = 3600 * hours + 60 * minutes + seconds;
            
        }
        return timeLeft >= this.durationTime();
    }
    public static final Object lock_key = new Object();
    public static final Object lock_key_exposure = new Object();
    public void awake_lock(){
        synchronized(lock_key) {
            lock_key.notifyAll();
        }
    }
    public void awake_lock_exposure(){
        synchronized(lock_key_exposure) {
            lock_key_exposure.notifyAll();
        }
    }
    public Step(){
        
    }
    public void execute(){
        Ccd ccd = new Ccd();
        Focuser focuser = new Focuser();
        Telescope telescope = new Telescope();
        
        if (!this.gethBinning().equals("-") && !this.getvBinning().equals("-")) {
            ccd.setBinning(this.gethBinning(), this.getvBinning());
        }
        if (!this.getX().equals("-") && !this.getY().equals("-")) {
            ccd.setFrame(this.getX(), this.getY());
        }        
        if (!this.getFrameType().equals("-")) {
            switch(this.getFrameType()){
                case "light":
                    ccd.setFrameLight();
                    break;
                case "bias":
                    ccd.setFrameBias();
                    break;
                case "dark":
                    ccd.setFrameDark();
                    break;
                case "flat":
                    ccd.setFrameFlat();
                    break;
                default:
                    break;                
            }
        } 
        if (!this.getWidth().equals("-") && !this.getHeight().equals("-")) {
            ccd.setSize(this.getWidth(), this.getHeight());
        } 
        
        if (!this.getFocusPosition().equals("-")) {
            focuser.setAbsolutePosition(this.getFocusPosition());
        } 
        
        if (!this.getRa().equals("-") && !this.getDeclination().equals("-")) {
            synchronized(lock_key) {
                //EJECUTAR
                System.err.println("EJECUTANDO MOVIMIENTO");            
                telescope.setRaDec(this.getRa(), this.getDeclination());
                try {
                        lock_key.wait();
                    } catch (InterruptedException ex) {                    
                    }
            }
        } 
        
        
        System.err.println("EJECUTANDO CAPTURA");
        String currentUserName = null;        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();         
        }
        String path = "/home/ip300/webapp/captures";
        String source= path+"/"+currentUserName;
        String dest = path+"/"+currentUserName;
        String time = this.getExposureTime();
        
        ccd.setExposure(time, path, source, dest);
        synchronized(lock_key_exposure) {
            try {
                    lock_key_exposure.wait();
                } catch (InterruptedException ex) {                    
                }
        }
    
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_sequence() {
        return id_sequence;
    }

    public void setId_sequence(int id_sequence) {
        this.id_sequence = id_sequence;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getDeclination() {
        return declination;
    }

    public void setDeclination(String declination) {
        this.declination = declination;
    }

    public String getExposureTime() {
        return exposureTime;
    }

    public void setExposureTime(String exposureTime) {
        this.exposureTime = exposureTime;
    }

    public String gethBinning() {
        return hBinning;
    }

    public void sethBinning(String hBinning) {
        this.hBinning = hBinning;
    }

    public String getvBinning() {
        return vBinning;
    }

    public void setvBinning(String vBinning) {
        this.vBinning = vBinning;
    }

    public String getFrameType() {
        return frameType;
    }

    public void setFrameType(String frameType) {
        this.frameType = frameType;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getFocusPosition() {
        return focusPosition;
    }

    public void setFocusPosition(String focusPosition) {
        this.focusPosition = focusPosition;
    }
    
    
    
}
