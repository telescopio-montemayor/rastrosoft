/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unlp.rastrosoft.web.model;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import laazotea.indi.client.INDIValueException;
import unlp.rastrosoft.web.controller.AjaxController;

/**
 *
 * @author ip300
 */
public class client {
    public void modificarString(String dispositivo,String propiedad,String elemento,String valor){
        indi_client cliente = null;
        try {
            cliente = connect_indi.connect("Telescope Simulator", "CCD Simulator", "Focuser Simulator");
        } catch (INDIValueException | IOException ex) {
            Logger.getLogger(AjaxController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        cliente.commitStringValor(dispositivo, propiedad, elemento, valor);
        cliente.pushValor(dispositivo, propiedad);
    }
}
