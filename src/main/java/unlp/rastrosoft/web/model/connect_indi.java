/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unlp.rastrosoft.web.model;

import java.io.IOException;
import laazotea.indi.client.INDIValueException;

/**
 *
 * @author ip300
 */
public class connect_indi {

    /**
     *
     * @return
     * @throws INDIValueException
     * @throws IOException
     */
    private static indi_client cliente;
    public static indi_client connect(String telescopio, String ccd, String focuser){
        if (cliente == null){
            try {
                cliente = new indi_client("localhost", 7624);
                Thread.sleep(500);
                cliente.conectar(telescopio);
                cliente.conectar(ccd);
                cliente.conectar(focuser);
                Thread.sleep(500);
            } catch (InterruptedException | IOException | INDIValueException ex) {
            }
        }
        return cliente;
    }
    
//    public static indi_client connect(String dispositivo){
//        if (cliente == null){
//            try {
//                cliente = new indi_client("localhost", 7624);
//                Thread.sleep(500);
//                cliente.conectar(dispositivo);
//                Thread.sleep(500);
//            } catch (InterruptedException | IOException | INDIValueException ex) {
//            }
//        }
//        return cliente;
//    }
    public static indi_client connect(String dispositivo){ 
        if (cliente == null || cliente.connected == false){
            cliente = new indi_client("localhost", 7624);        
        }
        return cliente;
    }
}
