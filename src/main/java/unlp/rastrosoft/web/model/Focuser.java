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
public class Focuser extends Device {
    
    public Focuser (){
        super("Focuser Simulator");
    }
    public boolean setFocusIn(){
        return this.modificarBoolean("FOCUS_MOTION", "FOCUS_INWARD", "ON");
    }
    public boolean setFocusOut(){
        return this.modificarBoolean("FOCUS_MOTION", "FOCUS_OUTWARD", "ON");
    }    
    public boolean setFocusIn(String ticks){
        this.modificarBoolean("FOCUS_MOTION", "FOCUS_INWARD", "ON");
        return this.setRelativePosition( ticks );
    }
    public boolean setFocusOut(String ticks){
        this.modificarBoolean("FOCUS_MOTION", "FOCUS_OUTWARD", "ON");
        return this.setRelativePosition( ticks );
    }
    public boolean setAbsolutePosition( String position ){
        return this.modificarDouble("ABS_FOCUS_POSITION", "FOCUS_ABSOLUTE_POSITION", position);
    }
    public boolean setRelativePosition( String ticks ){
        return this.modificarDouble("REL_FOCUS_POSITION", "FOCUS_RELATIVE_POSITION", ticks);
    }
    
    
    
    public String getFocusIn(){
        cliente = connect_indi.connect(dispositivo);
        return (cliente.enviar_mensaje(dispositivo, "FOCUS_MOTION", "FOCUS_INWARD"));
    }
    public String getFocusOut(){
        cliente = connect_indi.connect(dispositivo);
        return (cliente.enviar_mensaje(dispositivo, "FOCUS_MOTION", "FOCUS_OUTWARD"));
    }
    public String getAbsolutePosition(){
        cliente = connect_indi.connect(dispositivo);
        return (cliente.enviar_mensaje(dispositivo, "ABS_FOCUS_POSITION", "FOCUS_ABSOLUTE_POSITION"));
    }
    public String getRelativePosition(){
        cliente = connect_indi.connect(dispositivo);
        return (cliente.enviar_mensaje(dispositivo, "REL_FOCUS_POSITION", "FOCUS_RELATIVE_POSITION"));
    }
}
