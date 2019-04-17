/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unlp.rastrosoft.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import unlp.rastrosoft.web.jsonview.Views;
import unlp.rastrosoft.web.model.AjaxResponse;
import unlp.rastrosoft.web.model.AjaxResponseListOfLists;
import unlp.rastrosoft.web.model.CaptureDB;
import unlp.rastrosoft.web.model.ExecuteCriteria;
import unlp.rastrosoft.web.model.User;
import unlp.rastrosoft.web.model.UserDB;

/**
 *
 * @author ip300
 */
@RestController
public class AjaxLog {
    
    
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/getCaptures", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponseListOfLists getCaptures(@RequestBody ExecuteCriteria execute) {
        AjaxResponseListOfLists result = new AjaxResponseListOfLists();        
   
        CaptureDB captureDB = new CaptureDB();
        captureDB.connect();
        
        int id_user_val = -1;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            UserDB userDB = new UserDB();
            userDB.connect();
            User user = userDB.getUser(username);
            id_user_val = user.getUserId();
        }
        
        result.addElementos(captureDB.getCapturesAsList(id_user_val));
        
        return result;
    }
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/getCapture", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponse getCapture(@RequestBody ExecuteCriteria execute) {
        AjaxResponse result = new AjaxResponse();        
        String id_capture;       
        id_capture = execute.getValue();
        
        CaptureDB captureDB = new CaptureDB();
        captureDB.connect();
        
        int id_user_val = -1;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            UserDB userDB = new UserDB();
            userDB.connect();
            User user = userDB.getUser(username);
            id_user_val = user.getUserId();
        }
        
        result.addElementos(captureDB.getCaptureAsList(id_user_val, Integer.valueOf(id_capture)));
        
        return result;
    }
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/removeCapture", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponse removeCapture(@RequestBody ExecuteCriteria execute) {
        AjaxResponse result = new AjaxResponse();        
        String id_capture;       
        id_capture = execute.getValue();
        
        CaptureDB captureDB = new CaptureDB();
        captureDB.connect();
        
        int id_user_val = -1;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            UserDB userDB = new UserDB();
            userDB.connect();
            User user = userDB.getUser(username);
            id_user_val = user.getUserId();
        }
        
        result.addElemento(String.valueOf(captureDB.removeCapture(id_user_val, Integer.valueOf(id_capture))));
        
        return result;
    }
}
