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
import unlp.rastrosoft.web.model.ExecuteCriteriaStep;
import unlp.rastrosoft.web.model.SequenceDB;
import unlp.rastrosoft.web.model.StepDB;
import unlp.rastrosoft.web.model.User;
import unlp.rastrosoft.web.model.UserDB;

/**
 *
 * @author ip300
 */
@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AjaxStep {
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/addStep", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponse addStep(@RequestBody ExecuteCriteriaStep execute) {
        AjaxResponse result = new AjaxResponse();    
        
        String id_sequence, number, ra, declination, exposureTime, hBinning, vBinning, frameType, x, y, width, height, focusPosition, quantity;
        
        id_sequence = execute.getId_sequence();
        number = execute.getNumber();
        ra = execute.getRa();
        declination = execute.getDeclination();
        exposureTime = execute.getExposureTime();
        hBinning = execute.gethBinning();
        vBinning = execute.getvBinning();
        frameType = execute.getFrameType();
        x = execute.getX();
        y = execute.getY();
        width = execute.getWidth();
        height = execute.getHeight();
        focusPosition = execute.getFocusPosition();
        quantity = execute.getQuantity();
                if (quantity == null || quantity.equals("") )   quantity        =   "1";
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
            if(sequence.getUserId(Integer.parseInt(id_sequence)) == id_user ){ // Check if is the owner
                StepDB step = new StepDB();
                step.connect();
                step.insertStep(Integer.parseInt(id_sequence), Integer.parseInt(number), ra, declination, exposureTime, hBinning, vBinning, frameType, x, y, width, height, focusPosition, Integer.parseInt(quantity));
            }
        }
        return result;
    }    
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/removeStep", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponse removeStep(@RequestBody ExecuteCriteria execute) {
        AjaxResponse result = new AjaxResponse();    
        int step_id = Integer.parseInt(execute.getValue());
        
        int id_user = -1;
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            UserDB userDB = new UserDB();
            userDB.connect();
            User user = userDB.getUser(username);
            id_user = user.getUserId();
        }
        
        int sequence_id = -1;
        
        if (id_user != -1){
            StepDB step = new StepDB();
            step.connect();
            sequence_id = step.getSequenceId(step_id);
            
            SequenceDB sequence = new SequenceDB();
            sequence.connect();
            if(sequence.getUserId(sequence_id) == id_user ){ // Check if is the owner
                step.removeStep(step_id);
            }
        }
        
        return result;
    }
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/getSteps", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponseListOfLists  getSteps(@RequestBody ExecuteCriteria execute) {
        AjaxResponseListOfLists result = new AjaxResponseListOfLists();  
       
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
            if(sequence.getUserId(sequence_id) == id_user ){ // Check if is the owner
                StepDB step = new StepDB();
                step.connect();
                result.addElementos(step.getStepsAsList(sequence_id));
            }
        }
        
        return result;
    }  
}
