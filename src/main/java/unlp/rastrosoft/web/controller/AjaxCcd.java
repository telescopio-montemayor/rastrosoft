/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unlp.rastrosoft.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import unlp.rastrosoft.web.jsonview.Views;
import unlp.rastrosoft.web.model.AjaxResponseBodyIndiExecute;
import unlp.rastrosoft.web.model.Ccd;
import unlp.rastrosoft.web.model.ExecuteCriteria;

/**
 *
 * @author ip300
 */
@RestController
public class AjaxCcd {
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/previewImage", method=RequestMethod.POST)
    public AjaxResponseBodyIndiExecute getPreviewImageViaAjax(@RequestBody ExecuteCriteria execute, HttpServletRequest request) {

        AjaxResponseBodyIndiExecute result = new AjaxResponseBodyIndiExecute();

        Ccd cliente = new Ccd();
        cliente.takePreview();

        return result;
    }
}
