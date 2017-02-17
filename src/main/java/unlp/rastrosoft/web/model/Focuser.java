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
    
    public boolean park(){
        return this.modificarBoolean("TELESCOPE_PARK", "PARK", "ON");
    }
    
    public boolean unPark(){
        return this.modificarBoolean("TELESCOPE_PARK", "UNPARK", "ON");
    }
    
    public boolean track( ){
        return this.modificarBoolean("ON_COORD_SET", "TRACK", "ON");
    }
    
    public boolean slew( ){
        return this.modificarBoolean("ON_COORD_SET", "SLEW", "ON");
    }
    
    public boolean sync( ){
        return this.modificarBoolean("ON_COORD_SET", "SYNC", "ON");
    }
}
