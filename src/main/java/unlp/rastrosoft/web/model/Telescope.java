/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unlp.rastrosoft.web.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ip300
 */

public class Telescope extends Device {
    public Telescope (){
        super("LX200 GPS"); //CAMBIAR SEGUN TELESCOPIO: Telescope Simulator / LX200 GPS
    }
    
    public boolean setPark(){
        return this.modificarBoolean("TELESCOPE_PARK", "PARK", "ON");
    }
    
    public boolean setUnPark(){
        return this.modificarBoolean("TELESCOPE_PARK", "UNPARK", "ON");
    }
    
    public boolean setTrack( ){
        return this.modificarBoolean("ON_COORD_SET", "TRACK", "ON");
    }
    
    public boolean setSlew( ){
        return this.modificarBoolean("ON_COORD_SET", "SLEW", "ON");
    }
    
    public boolean setSync( ){
        return this.modificarBoolean("ON_COORD_SET", "SYNC", "ON");
    }
    
    public boolean setAbortMotion(){
        return this.modificarBoolean("TELESCOPE_ABORT_MOTION", "ABORT", "ON");
    }
    
    public boolean setRaDec( String ra , String dec ){
        cliente = connect_indi.connect(dispositivo);
        cliente.commitDoubleValor(dispositivo, "EQUATORIAL_EOD_COORD", "RA", ra);
        cliente.commitDoubleValor(dispositivo, "EQUATORIAL_EOD_COORD", "DEC", dec);
        cliente.pushValor(dispositivo, "EQUATORIAL_EOD_COORD");
        return true;
    }
    public List<String> getRaDec(){        
        List<String> result = new ArrayList<>();
        cliente = connect_indi.connect(dispositivo);
        String ra = cliente.enviar_mensaje(dispositivo, "EQUATORIAL_EOD_COORD", "RA");
        String dec = cliente.enviar_mensaje(dispositivo, "EQUATORIAL_EOD_COORD", "DEC");
        result.add(ra);
        result.add(dec);
        return result;
    }
    public String getPark(){
        cliente = connect_indi.connect(dispositivo);
        return (cliente.enviar_mensaje(dispositivo, "TELESCOPE_PARK", "PARK"));
    }
    public String getUnPark(){
        cliente = connect_indi.connect(dispositivo);
        return (cliente.enviar_mensaje(dispositivo, "TELESCOPE_PARK", "UNPARK"));
    }
    public String getTrak(){
        cliente = connect_indi.connect(dispositivo);
        return (cliente.enviar_mensaje(dispositivo, "ON_COORD_SET", "TRACK"));
    }
    public String getSlew(){
        cliente = connect_indi.connect(dispositivo);
        return (cliente.enviar_mensaje(dispositivo, "ON_COORD_SET", "SLEW"));
    }
    public String getSync(){
        cliente = connect_indi.connect(dispositivo);
        return (cliente.enviar_mensaje(dispositivo, "ON_COORD_SET", "SYNC"));
    }
    public String getRa(){
        cliente = connect_indi.connect(dispositivo);
        return(cliente.enviar_mensaje(dispositivo, "EQUATORIAL_EOD_COORD", "RA"));
    }
    public String getDec(){
        cliente = connect_indi.connect(dispositivo);
        return(cliente.enviar_mensaje(dispositivo, "EQUATORIAL_EOD_COORD", "DEC"));
    }
}
