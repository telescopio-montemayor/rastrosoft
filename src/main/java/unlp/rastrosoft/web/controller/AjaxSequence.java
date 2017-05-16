/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unlp.rastrosoft.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import unlp.rastrosoft.web.jsonview.Views;
import unlp.rastrosoft.web.model.AjaxResponse;
import unlp.rastrosoft.web.model.AjaxResponseListOfLists;
import unlp.rastrosoft.web.model.ExecuteCriteria;
import unlp.rastrosoft.web.model.ExecuteCriteriaTwoValues;
import unlp.rastrosoft.web.model.SequenceDB;
import unlp.rastrosoft.web.model.User;
import unlp.rastrosoft.web.model.UserDB;

/**
 *
 * @author ip300
 */
@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AjaxSequence {
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/addSequence", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponse addSequence(@RequestBody ExecuteCriteria execute) {
        AjaxResponse result = new AjaxResponse();    
        String name = execute.getValue();
        
        int id_user = -1, sequence_id = -1;
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            UserDB userDB = new UserDB();
            userDB.connect();
            User user = userDB.getUser(username);
            id_user = user.getUserId();
        }
        
        if (id_user != -1){
            SequenceDB sequence = new SequenceDB();
            sequence.connect();
            sequence_id = sequence.insertSequence(id_user, name);
        }
        result.addElemento(String.valueOf(sequence_id));
        result.addElemento(name);
        return result;
    }    
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/removeSequence", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponse removeSequence(@RequestBody ExecuteCriteria execute) {
        AjaxResponse result = new AjaxResponse();    
        int sequence_id = Integer.parseInt(execute.getValue());
        
        int id_user = -1;
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            UserDB userDB = new UserDB();
            userDB.connect();
            User user = userDB.getUser(username);
            id_user = user.getUserId();
        }
        
        if (id_user != -1){
            SequenceDB sequence = new SequenceDB();
            sequence.connect();
            sequence.removeSequence(sequence_id, id_user);
        }
        
        return result;
    }  
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/modifySequence", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponse modifySequence(@RequestBody ExecuteCriteriaTwoValues execute) {
        AjaxResponse result = new AjaxResponse();    
        int sequence_id = Integer.parseInt(execute.getValue());
        String name = execute.getValue2();
        
        int id_user = -1;
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            UserDB userDB = new UserDB();
            userDB.connect();
            User user = userDB.getUser(username);
            id_user = user.getUserId();
        }
        
        if (id_user != -1){
            SequenceDB sequence = new SequenceDB();
            sequence.connect();
            sequence.modifySequence(sequence_id, id_user, name);
        }
        result.addElemento(String.valueOf(sequence_id));
        result.addElemento(name);
        return result;
    }  
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/getSequences", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponseListOfLists  getSequences(@RequestBody ExecuteCriteria execute) {
        AjaxResponseListOfLists result = new AjaxResponseListOfLists();  
       
        int id_user = -1;
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            UserDB userDB = new UserDB();
            userDB.connect();
            User user = userDB.getUser(username);
            id_user = user.getUserId();
        }
        
        if (id_user != -1){
            SequenceDB sequence = new SequenceDB();
            sequence.connect();
            result.addElementos(sequence.getSequencesAsList(id_user));
        }
        
        return result;
    }  
}
