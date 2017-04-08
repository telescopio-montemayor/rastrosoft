/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unlp.rastrosoft.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import unlp.rastrosoft.web.jsonview.Views;
import unlp.rastrosoft.web.model.AjaxResponse;
import unlp.rastrosoft.web.model.AjaxResponseBodyIndiExecute;
import unlp.rastrosoft.web.model.Ccd;
import unlp.rastrosoft.web.model.ExecuteCriteria;
import unlp.rastrosoft.web.model.ExecuteCriteriaTwoValues;

/**
 *
 * @author ip300
 */
@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AjaxCcd {
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/previewImage", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponseBodyIndiExecute getPreviewImageViaAjax(@RequestBody ExecuteCriteria execute, HttpServletRequest request) {

        AjaxResponseBodyIndiExecute result = new AjaxResponseBodyIndiExecute();

        Ccd ccd = new Ccd();
        ccd.takePreview();

        return result;
    }
    
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/setUploadDirectory", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponse setUploadDirectory(@RequestBody ExecuteCriteria execute) {

        AjaxResponse result = new AjaxResponse();        
        String dir;       
        dir = execute.getValue();        
        Ccd ccd = new Ccd();
        ccd.setUploadDirectory(dir);
        return result;
    }
    
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/setPrefix", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponse setPrefix(@RequestBody ExecuteCriteria execute) {

        AjaxResponse result = new AjaxResponse();        
        String prefix;       
        prefix = execute.getValue();        
        Ccd ccd = new Ccd();
        ccd.setPrefix(prefix);
        return result;
    }
    
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/setBinning", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponse setBinning(@RequestBody ExecuteCriteriaTwoValues execute) {
        AjaxResponse result = new AjaxResponse();        
        String hor, ver;       
        hor = execute.getValue();
        ver = execute.getValue2();
        Ccd ccd = new Ccd();
        ccd.setBinning(hor, ver);
        return result;
    }
    
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/setFrameType", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponse setFrameType(@RequestBody ExecuteCriteriaTwoValues execute) {
        AjaxResponse result = new AjaxResponse();        
        String frameType;       
        frameType = execute.getValue();
        Ccd ccd = new Ccd();
        switch (frameType){
            case "frameLight":
                ccd.setFrameLight();
                break;
            case "frameBias":
                ccd.setFrameBias();
                break;
            case "frameDark":
                ccd.setFrameDark();
                break;    
            case "frameFlat":
                ccd.setFrameFlat();
                break;
            default:
                break;
        }
        return result;
    }
    
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/setCcdTemperature", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponse setCcdTemperature(@RequestBody ExecuteCriteria execute) {
        AjaxResponse result = new AjaxResponse();        
        String temperature;       
        temperature = execute.getValue();        
        Ccd ccd = new Ccd();
        ccd.setTemperature(temperature);
        return result;
    }
    
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/setFrame", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponse setFrame(@RequestBody ExecuteCriteriaTwoValues execute) {
        AjaxResponse result = new AjaxResponse();        
        String x, y;       
        x = execute.getValue();
        y = execute.getValue2();
        Ccd ccd = new Ccd();
        ccd.setFrame(x, y);
        return result;
    }
    
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/setSize", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponse setSize(@RequestBody ExecuteCriteriaTwoValues execute) {
        AjaxResponse result = new AjaxResponse();        
        String width, height;       
        width = execute.getValue();
        height = execute.getValue2();
        Ccd ccd = new Ccd();
        ccd.setSize(width, height);
        return result;
    }
    
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/setExposure", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponse setExposure(@RequestBody ExecuteCriteriaTwoValues execute) {
        AjaxResponse result = new AjaxResponse();        
        String time;       
        time = execute.getValue();
        Ccd ccd = new Ccd();
        ccd.setExposure(time);
        return result;
    }
    
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/setAbortExposure", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponse setAbortExposure(@RequestBody ExecuteCriteriaTwoValues execute) {
        AjaxResponse result = new AjaxResponse();        
        String time;       
        time = execute.getValue();
        Ccd ccd = new Ccd();
        ccd.abortExposure();
        return result;
    }
    
  
}
