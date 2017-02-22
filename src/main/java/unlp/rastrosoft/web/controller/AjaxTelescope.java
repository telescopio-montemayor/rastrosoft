/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unlp.rastrosoft.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import unlp.rastrosoft.web.jsonview.Views;
import unlp.rastrosoft.web.model.AjaxResponse;
import unlp.rastrosoft.web.model.SearchCriteria;
import unlp.rastrosoft.web.model.connect_indi;
import unlp.rastrosoft.web.model.indi_client;

/**
 *
 * @author ip300
 */
@RestController
public class AjaxTelescope {
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/buscarValor2", method=RequestMethod.POST)
    public AjaxResponse getValorViaAjax2(@RequestBody SearchCriteria search) {

        AjaxResponse result = new AjaxResponse();

        indi_client cliente = connect_indi.connect("Telescope Simulator", "CCD Simulator", "Focuser Simulator");

        String device, property, element, value;

        device = search.getDevice();
        property = search.getProperty();
        element = search.getElement();            
        value = cliente.enviar_mensaje(device, property, element);

        List<String> elementos = new ArrayList<>();
        elementos.add(value);
        result.setElementos(elementos);                    



        //AjaxResponse will be converted into json format and send back to client.
        return result;

    }
}
