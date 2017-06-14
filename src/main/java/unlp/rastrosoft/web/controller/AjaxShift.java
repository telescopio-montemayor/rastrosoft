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
import java.util.List;
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
import unlp.rastrosoft.web.model.ExecuteCriteria;
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
        
        String enabled = "2", live_key = "-1", public_val = "0";
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
        
        String minutes = datetime.substring(datetime.length()-5 , datetime.length()-3);
        
        if (minutes.equals("00") && shift.checkAvailableShift(datetime)){
            shift_id = shift.insertShift(id_user, datetime, enabled, live_key, public_val);
            result.addElemento(String.valueOf(shift_id));
            result.addElemento(live_key);
        }
        result.addElemento("-1");
        result.addElemento("-1");
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
    @RequestMapping(value = "/getAllModerationShifts", method=RequestMethod.POST)
    public AjaxResponseListOfLists getAllModerationShifts(@RequestBody SearchCriteria search) {            

        AjaxResponseListOfLists result = new AjaxResponseListOfLists();
        CalendarDB shifts = new CalendarDB();    
        shifts.connect();       
        result.addElementos(shifts.getModerationShifts(LocalDateTime.now().withMinute(0).withSecond(0).withNano(0).toString(), 0 ));        
        result.addElementos(shifts.getModerationShifts(LocalDateTime.now().withMinute(0).withSecond(0).withNano(0).toString(), 1 ));
        result.addElementos(shifts.getModerationShifts(LocalDateTime.now().withMinute(0).withSecond(0).withNano(0).toString(), 2 ));
        return result;
    }
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/getPendingShifts", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR')")
    public AjaxResponseListOfLists getPendingShifts(@RequestBody SearchCriteria search) {            

        AjaxResponseListOfLists result = new AjaxResponseListOfLists();
        CalendarDB shifts = new CalendarDB();    
        shifts.connect();       
        result.addElementos(shifts.getModerationShifts(LocalDateTime.now().withMinute(0).withSecond(0).withNano(0).toString(), 2 ));        
        return result;
    }
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/getAcceptedShifts", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR')")
    public AjaxResponseListOfLists getAcceptedShifts(@RequestBody SearchCriteria search) {            

        AjaxResponseListOfLists result = new AjaxResponseListOfLists();
        CalendarDB shifts = new CalendarDB();    
        shifts.connect();       
        result.addElementos(shifts.getModerationShifts(LocalDateTime.now().withMinute(0).withSecond(0).withNano(0).toString(), 1 ));        
        return result;
    }
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/getRejectedShifts", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR')")
    public AjaxResponseListOfLists getRejectedShifts(@RequestBody SearchCriteria search) {            

        AjaxResponseListOfLists result = new AjaxResponseListOfLists();
        CalendarDB shifts = new CalendarDB();    
        shifts.connect();       
        result.addElementos(shifts.getModerationShifts(LocalDateTime.now().withMinute(0).withSecond(0).withNano(0).toString(), 0 ));        
        return result;
    }
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/acceptShift", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR')")
    public AjaxResponse acceptShift(@RequestBody ExecuteCriteria execute) {            
        String shift_id = execute.getValue();
        
        AjaxResponse result = new AjaxResponse();
        CalendarDB shifts = new CalendarDB();    
        shifts.connect();
        String datetime = shifts.getDatetimeShift(Integer.valueOf(shift_id));
        String previous_id = shifts.getPreviousAcceptedShift(datetime);
        if(previous_id.equals("-1")){
            shifts.acceptShift(Integer.valueOf(shift_id));
            result.addElemento("1"); //ACCEPTED
        }else{
            result.addElemento("0"); //CANNOT ACCEPT SHIFT, ALREADY ONE WITH THIS datetime
            result.addElemento(previous_id);
        }        
        return result;
    }
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/rejectShift", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR')")
    public AjaxResponseListOfLists rejectShift(@RequestBody ExecuteCriteria execute) {            
        String shift_id = execute.getValue();
        
        AjaxResponseListOfLists result = new AjaxResponseListOfLists();
        CalendarDB shifts = new CalendarDB();    
        shifts.connect();
        shifts.rejectShift(Integer.valueOf(shift_id));
        return result;
    }
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/setToPendingShift", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR')")
    public AjaxResponseListOfLists setToPendingShift(@RequestBody ExecuteCriteria execute) {            
        String shift_id = execute.getValue();
        
        AjaxResponseListOfLists result = new AjaxResponseListOfLists();
        CalendarDB shifts = new CalendarDB();    
        shifts.connect();
        shifts.setToPendingShift(Integer.valueOf(shift_id));
        return result;
    }
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/getTimeleftCurrentShift", method=RequestMethod.POST)
    public AjaxResponse getTimeleftCurrentShift(@RequestBody SearchCriteria search) {            

        AjaxResponse result = new AjaxResponse();
        CalendarDB calendarDB = new CalendarDB();
            calendarDB.connect();
            ArrayList<String> shift =  calendarDB.getCurrentShift();
            int id_user = -1;
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                String username = authentication.getName();
                UserDB userDB = new UserDB();
                userDB.connect();
                User user = userDB.getUser(username);
                id_user = user.getUserId();
            }
            if(((Integer.valueOf(shift.get(0)) != id_user) && (shift.get(3).equals("0")))){
                result.addElemento("-1");
                result.addElemento("00:00:00");
                authentication.setAuthenticated(false);
                return result;
            }
            if (shift.get(0).equals("-1")){
                result.addElemento("-1");
                result.addElemento("00:00:00");                
                authentication.setAuthenticated(false);
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
    
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/cancelShift", method=RequestMethod.POST)
    public AjaxResponseListOfLists cancelShift(@RequestBody ExecuteCriteria execute) {    
    
        String shift_id = execute.getValue();
      
        int id_user = -1;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            UserDB userDB = new UserDB();
            userDB.connect();
            User user = userDB.getUser(username);
            id_user = user.getUserId();
        }
        AjaxResponseListOfLists result = new AjaxResponseListOfLists();
        CalendarDB shifts = new CalendarDB();    
        shifts.connect();
        List<String> shift = shifts.getShift(Integer.valueOf(shift_id));
        
        if(shift.get(0).equals(String.valueOf(id_user))){            
            shifts.rejectShift(Integer.valueOf(shift_id));
        }        
        return result;
    }
}