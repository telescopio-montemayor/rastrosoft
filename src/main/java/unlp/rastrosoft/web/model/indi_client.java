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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import laazotea.indi.Constants;
import laazotea.indi.Constants.SwitchStatus;
import laazotea.indi.client.*;
import unlp.rastrosoft.web.controller.SSEController;


public class indi_client implements INDIServerConnectionListener, INDIDeviceListener, INDIPropertyListener {

	private INDIServerConnection connection;

	List<INDIProperty> propiedades = new ArrayList<>();
	List<INDIDevice> dispositivos = new ArrayList<>();	
        
	public indi_client(String host, int port) {
	    connection = new INDIServerConnection(host, port);
	
	    connection.addINDIServerConnectionListener(this);  // We listen to all server events
	
	    try {
	      connection.connect();
	      connection.askForDevices();  // Ask for all the devices.
	    } catch (IOException e) {
	      System.out.println("Problem with the connection: " + host + ":" + port);
	      e.printStackTrace();
	    }
	 }

    public indi_client() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	public void newDevice(INDIServerConnection connection, INDIDevice device) {
	    // We just simply listen to this Device
		System.out.println("New device: " + device.getName());
                dispositivos.add(device);
	    //dispositivos_hashmap.put(device.getName(), device);
		try {
	      device.BLOBsEnable(Constants.BLOBEnables.ALSO); // Enable receiving BLOBs from this Device
	    } catch (IOException e) {
	    }
	    device.addINDIDeviceListener(this);
	}

	public void removeDevice(INDIServerConnection connection, INDIDevice device) {
	    // We just remove ourselves as a listener of the removed device
	    System.out.println("Device Removed: " + device.getName());
	    device.removeINDIDeviceListener(this);
	}

	public void connectionLost(INDIServerConnection connection) {
	    System.out.println("Connection lost. Bye");

	    System.exit(-1);
	}

	public void newMessage(INDIServerConnection connection, Date timestamp, String message) {
	    //System.out.println("New Server Message: " + timestamp + " - " + message);
	}
	
	public void removeProperty(INDIDevice device, INDIProperty property) {
	    // We just remove ourselves as a listener of the removed property
	    //System.out.println("Property (" + property.getName() + ") removed from device " + device.getName());
	    property.removeINDIPropertyListener(this);
	}
	
	public void messageChanged(INDIDevice device) {
	    //System.out.println("New Device Message: " + device.getName() + " - " + device.getTimestamp() + " - " + device.getLastMessage());
	}
	
	public void propertyChanged(INDIProperty property) {
	    //System.out.println("Property Changed: " + property.getNameStateAndValuesAsString()); 
            
	}	
	
	public void newProperty(INDIDevice device, INDIProperty property) {
	    // We just simply listen to this Property
	    //System.out.println("New Property (" + property.getName() + ") added to device " + device.getName());
	    property.addINDIPropertyListener(this);

	    
	    propiedades.add(property);
	    
	}

	public void verPropiedades(){
		
		for(int i=0 ; i < propiedades.size(); i++) {
	        System.out.println	(	"Dispositivo: " + propiedades.get(i).getDevice().getName() 	+ " - " +
	        						"Propiedad: "	+ propiedades.get(i).getName()  			+ " - " +
	        						"Elementos: "	+ propiedades.get(i).getElementCount()
	        					);
	        for (int x=0 ; x < propiedades.get(i).getElementCount(); x++){
	        	System.out.println	(	"	Elemento: "	+ propiedades.get(i).getElementsAsList().get(x).getName()	);
	        }
	      }

	}
	
	public void conectar(String device_name) throws IOException, INDIValueException{
		for(int i=0 ; i < propiedades.size(); i++) {
			if ( (propiedades.get(i).getName().equals("CONNECTION")) & (propiedades.get(i).getDevice().getName().equals(device_name)) ){
			
					propiedades.get(i).getElement("DISCONNECT").setDesiredValue(SwitchStatus.OFF);
					propiedades.get(i).getElement("CONNECT").setDesiredValue(SwitchStatus.ON);
					propiedades.get(i).sendChangesToDriver();
			
				
			}
		}
	}
	public void desconectar(String device_name) throws IOException, INDIValueException{
		for(int i=0 ; i < propiedades.size(); i++) {
			if ( (propiedades.get(i).getName().equals("CONNECTION")) & (propiedades.get(i).getDevice().getName().equals(device_name)) ){
			
					propiedades.get(i).getElement("DISCONNECT").setDesiredValue(SwitchStatus.ON);
					propiedades.get(i).getElement("CONNECT").setDesiredValue(SwitchStatus.OFF);
					propiedades.get(i).sendChangesToDriver();
			
				
			}
		}
	}
	
        public boolean commitStringValor(String dispositivo, String propiedad, String elemento, String valor){
		for(int i=0 ; i < propiedades.size(); i++) {
			if ( (propiedades.get(i).getName().equals(propiedad)) & (propiedades.get(i).getDevice().getName().equals(dispositivo)) ){
				
                            try {
                                if (propiedades.get(i).getElement(elemento).checkCorrectValue(valor)==false){
                                    System.out.println("ERROR EN EL VALOR!");
                                    return false;
                                }
                                propiedades.get(i).getElement(elemento).setDesiredValue(valor);
                                                                
                                return true;
                            } catch (INDIValueException ex) {
                                Logger.getLogger(indi_client.class.getName()).log(Level.SEVERE, null, ex);
                            }
			}
		}
		return false;
	}
        

        public boolean commitDoubleValor(String dispositivo, String propiedad, String elemento, String valor){
		for(int i=0 ; i < propiedades.size(); i++) {
			if ( (propiedades.get(i).getName().equals(propiedad)) & (propiedades.get(i).getDevice().getName().equals(dispositivo)) ){
				
                            try {
                                if (propiedades.get(i).getElement(elemento).checkCorrectValue(new Double(valor))==false){
                                    System.out.println("ERROR EN EL VALOR!");
                                    return false;
                                }
                                propiedades.get(i).getElement(elemento).setDesiredValue(new Double(valor));
                                
                                
                                
                                return true;
                            } catch (INDIValueException ex) {
                                Logger.getLogger(indi_client.class.getName()).log(Level.SEVERE, null, ex);
                            }
			}
		}
		return false;
	}
        

        public boolean commitBooleanValor(String dispositivo, String propiedad, String elemento, String valor){
            SwitchStatus value ;
//          value = Constants.parseSwitchStatus("On");
            switch (valor) {
                case "ON":
                    value = SwitchStatus.ON;
                    break;
                case "OFF":
                    value = SwitchStatus.OFF;
                    break;
                default:
                    return false;
            }     
            for(int i=0 ; i < propiedades.size(); i++) {
			if ( (propiedades.get(i).getName().equals(propiedad)) & (propiedades.get(i).getDevice().getName().equals(dispositivo)) ){
				
                            try {
                                if (propiedades.get(i).getElement(elemento).checkCorrectValue(value)==false){
                                    System.out.println("ERROR EN EL VALOR!");
                                    return false;
                                }
                                propiedades.get(i).getElement(elemento).setDesiredValue(value);
                                
                                return true;
                            } catch (INDIValueException ex) {
                                Logger.getLogger(indi_client.class.getName()).log(Level.SEVERE, null, ex);
                            }
			}
		}
		return false;
        }
        
        public boolean pushValor(String dispositivo, String propiedad){
            for(int i=0 ; i < dispositivos.size(); i++) {
                if (dispositivos.get(i).getName().equals(dispositivo)){
                    List<INDIProperty> propiedadesDispositivo = dispositivos.get(i).getPropertiesAsList();
                    for(int j=0 ; j < propiedadesDispositivo.size(); j++) {
                        if(propiedadesDispositivo.get(j).getName().equals(propiedad)){
                            try {	 
                                propiedadesDispositivo.get(j).sendChangesToDriver();
                                return true;
                            } catch (INDIValueException | IOException ex) {
                                Logger.getLogger(indi_client.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    
                }
            }
            return false;
        }
	public String enviar_mensaje(String dispositivo, String propiedad, String elemento){
		for(int i=0 ; i < propiedades.size(); i++) {
			if ( (propiedades.get(i).getName().equals(propiedad)) & (propiedades.get(i).getDevice().getName().equals(dispositivo)) ){
				
				return propiedades.get(i).getElement(elemento).getValue().toString();
				
			}
		}
		return "No se encontro el elemento";
	}
        
        public List<String> listaDispositivos(){
            List<String> listaDispositivos = new ArrayList<>();
            for(int i=0 ; i < dispositivos.size(); i++) {
                listaDispositivos.add(dispositivos.get(i).getName());
            }
            return listaDispositivos;  
	}
   
        public List<String> listaPropiedades(String dispositivo){
		List<String> listaPropiedades = new ArrayList<>();
		for(int i=0 ; i < propiedades.size(); i++) {
                    if (propiedades.get(i).getDevice().getName().equals(dispositivo)){
                        listaPropiedades.add(propiedades.get(i).getName());
                    }
	        }
                return listaPropiedades;
	}
        
        public List<String> listaElementos(String dispositivo, String propiedad){
		List<String> listaElementos = new ArrayList<>();
                List<INDIElement> listaElementosAux = new ArrayList<>();
		for(int i=0 ; i < propiedades.size(); i++) {
                    if ((propiedades.get(i).getDevice().getName().equals(dispositivo)) && (propiedades.get(i).getName().equals(propiedad))){
                        listaElementosAux = propiedades.get(i).getElementsAsList();
                    }
	        }
                for (INDIElement i: listaElementosAux) {
                    listaElementos.add(i.getName());
                }
                return listaElementos;
	}
       
        
}
