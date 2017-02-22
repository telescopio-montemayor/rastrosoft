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
import unlp.rastrosoft.web.model.ExecuteCriteriaTwoValues;
import unlp.rastrosoft.web.model.SearchCriteria;
import unlp.rastrosoft.web.model.Telescope;
import unlp.rastrosoft.web.model.connect_indi;
import unlp.rastrosoft.web.model.indi_client;

/**
 *
 * @author ip300
 */
@RestController
public class AjaxTelescope {
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/setRaDec", method=RequestMethod.POST)
    public AjaxResponse getValorViaAjax2(@RequestBody ExecuteCriteriaTwoValues execute) {

        AjaxResponse result = new AjaxResponse();
        
        String ra, dec;       
        ra = execute.getValue();
        dec = execute.getValue2();
        
        Telescope telescope = new Telescope();
        if (telescope.setRaDec(ra, dec)){
            result.setElementos(telescope.getRaDec());
        }
        
        //AjaxResponse will be converted into json format and send back to client.
        return result;

    }
}
