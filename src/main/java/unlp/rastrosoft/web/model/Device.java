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
public class Device {
    protected indi_client cliente = null;    
    protected String dispositivo = null;

    public Device (String dispositivo){
        this.dispositivo = dispositivo;
    }
    
    public boolean modificarString(String propiedad,String elemento,String valor){        
        cliente = connect_indi.connect(dispositivo); 
        if (false==(cliente.commitStringValor(dispositivo, propiedad, elemento, valor)))
            return false;
        return false != (cliente.pushValor(dispositivo, propiedad));
    }
    public boolean modificarBoolean(String propiedad, String elemento,String valor){        
        cliente = connect_indi.connect(dispositivo); 
        if(false==(cliente.commitBooleanValor(dispositivo, propiedad, elemento, valor)))
            return false;
        return false != (cliente.pushValor(dispositivo, propiedad));  
    }
    public boolean modificarDouble(String propiedad,String elemento,String valor){
        cliente = connect_indi.connect(dispositivo);
        if (false==(cliente.commitDoubleValor(dispositivo, propiedad, elemento, valor)))
            return false;
        return false != (cliente.pushValor(dispositivo, propiedad));  
    }
    
    
    //VERIFICAR
    protected String decimalToTime (String decimal){
        double finalBuildTime = new Double (decimal);
        int hours = (int) finalBuildTime;
        int minutes = (int) (finalBuildTime * 60) % 60;
        int seconds = (int) (finalBuildTime * (60*60)) % 60;
        return ( hours +":" + minutes + ":" + seconds ); 
    }
    //VERIFICAR
    protected String timeToDecimal (String time){
       
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");

        Date dt = null;
        try {
            dt = formatter.parse(time);
        } catch (ParseException ex) {
            Logger.getLogger(Device.class.getName()).log(Level.SEVERE, null, ex);
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
