/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unlp.rastrosoft.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import unlp.rastrosoft.web.jsonview.Views;
import unlp.rastrosoft.web.model.AjaxResponse;
import unlp.rastrosoft.web.model.ExecuteCriteria;
import unlp.rastrosoft.web.model.Focuser;

/**
 *
 * @author ip300
 */
@RestController
public class AjaxFocuser {
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/setFocusAbsolute", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponse setFocusAbsolute(@RequestBody ExecuteCriteria execute) {
        AjaxResponse result = new AjaxResponse();
        if (AccessControl.AccessControl()){
            String position;       
            position = execute.getValue();
            Focuser focuser = new Focuser();
            focuser.setAbsolutePosition(position);
        }
        return result;
    }
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/focusIn", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponse focusIn(@RequestBody ExecuteCriteria execute) {
        AjaxResponse result = new AjaxResponse();        
        if (AccessControl.AccessControl()){
            String ticks;
            ticks = execute.getValue();
            Focuser focuser = new Focuser();
            focuser.setFocusIn(ticks);
        }
        return result;
    }
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/focusOut", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponse focusOut(@RequestBody ExecuteCriteria execute) {
        AjaxResponse result = new AjaxResponse();        
        if (AccessControl.AccessControl()){
            String ticks;
            ticks = execute.getValue();
            Focuser focuser = new Focuser();
            focuser.setFocusOut(ticks);
        }
        return result;
    }
//    PARA EL FOCUSER: MICROFOCUSER LX 200
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/setFocusSpeedMicro", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponse setFocusSpeedMicro(@RequestBody ExecuteCriteria execute) {
        AjaxResponse result = new AjaxResponse();        
        if (AccessControl.AccessControl()){
            String speed;       
            speed = execute.getValue();
            Focuser focuser = new Focuser();
            focuser.setFocusSpeedMicro(speed);
        }
        return result;
    }
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/focusInMicro", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponse focusInMicro(@RequestBody ExecuteCriteria execute) {
        AjaxResponse result = new AjaxResponse();
        if (AccessControl.AccessControl()){
            String timer;       
            timer = execute.getValue();
            Focuser focuser = new Focuser();
            focuser.setFocusTimerMicro(timer);
            focuser.setFocusInMicro();
        }
        return result;
    }
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/focusOutMicro", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponse focusOutMicro(@RequestBody ExecuteCriteria execute) {
        AjaxResponse result = new AjaxResponse();        
        if (AccessControl.AccessControl()){
            String timer;       
            timer = execute.getValue();
            Focuser focuser = new Focuser();
            focuser.setFocusTimerMicro(timer);
            focuser.setFocusOutMicro();
        }
        return result;
    }
//    ...
}
