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
  
}
