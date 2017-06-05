/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unlp.rastrosoft.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import unlp.rastrosoft.web.model.CalendarDB;
import unlp.rastrosoft.web.model.ExecuteCriteriaThreeValues;
import unlp.rastrosoft.web.model.SearchCriteria;
import unlp.rastrosoft.web.model.User;
import unlp.rastrosoft.web.model.UserDB;

/**
 *
 * @author ip300
 */
@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AjaxShift {
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/addShift", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponse addShift(@RequestBody ExecuteCriteriaThreeValues execute) {
        AjaxResponse result = new AjaxResponse();    
        String isLive = execute.getValue(), isPublic = execute.getValue2(), datetime = execute.getValue3();
        
        String enabled = "1", live_key = "-1", public_val = "0";
        int id_user = -1, shift_id = -1;
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            UserDB userDB = new UserDB();
            userDB.connect();
            User user = userDB.getUser(username);
            id_user = user.getUserId();
        }
        
        if (isLive.equals("true")){
            live_key = generateKey(6);
            if (isPublic.equals("true")){
                public_val = "1";
            }
        }
        
        CalendarDB shift = new CalendarDB();
        shift.connect();
        shift_id = shift.insertShift(id_user, datetime, enabled, live_key, public_val);
        result.addElemento(String.valueOf(shift_id));
        result.addElemento(live_key);
        return result;
    }
    
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/getAllShifts", method=RequestMethod.POST)
    public AjaxResponseListOfLists getAllShifts(@RequestBody SearchCriteria search) {            

        AjaxResponseListOfLists result = new AjaxResponseListOfLists();


        CalendarDB shifts = new CalendarDB();    
        shifts.connect();       
        result.addElementos(shifts.getAllShiftsWithName(LocalDateTime.now().withMinute(0).withSecond(0).withNano(0).toString()));        
        return result;
    }
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/getTimeleftCurrentShift", method=RequestMethod.POST)
    public AjaxResponse getTimeleftCurrentShift(@RequestBody SearchCriteria search) {            

        AjaxResponse result = new AjaxResponse();
        CalendarDB calendarDB = new CalendarDB();
            calendarDB.connect();
            ArrayList<String> shift =  calendarDB.getCurrentShift();
            
            if (shift.get(0).equals("-1")){
                result.addElemento("-1");
                result.addElemento("00:00:00");                
            }else{
                result.addElemento(shift.get(0));
                result.addElemento(shift.get(1));
            }
        return result;
    }
    
    public String generateKey(int lenght){
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32).substring(0, lenght);
    }
    
}