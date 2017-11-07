/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unlp.rastrosoft.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import unlp.rastrosoft.web.jsonview.Views;
import unlp.rastrosoft.web.model.AjaxResponse;
import unlp.rastrosoft.web.model.AjaxResponseListOfLists;
import unlp.rastrosoft.web.model.ExecuteCriteria;
import unlp.rastrosoft.web.model.ExecuteCriteriaFiveValues;
import unlp.rastrosoft.web.model.ExecuteCriteriaTwoValues;
import unlp.rastrosoft.web.model.SearchCriteria;
import unlp.rastrosoft.web.model.User;
import unlp.rastrosoft.web.model.UserDB;

/**
 *
 * @author ip300
 */
@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AjaxUser  extends HttpServlet{
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/createAccount", method=RequestMethod.POST)    
    public AjaxResponse createAccount(@RequestBody ExecuteCriteriaFiveValues execute) {
        AjaxResponse result = new AjaxResponse();        
   
        String unsafe_username = execute.getValue();
        String unsafe_name = execute.getValue2();
        String unsafe_lastname = execute.getValue3();
        String mail = execute.getValue4();
        
        String username = ScapeString.scape(unsafe_username);
        String name = ScapeString.scape(unsafe_name);
        String lastname = ScapeString.scape(unsafe_lastname);
        
        String password = execute.getValue5();
        password = (new BCryptPasswordEncoder().encode(password));
        UserDB userDB = new UserDB();
        userDB.connect();        
        User user = new User();
        user.setUsername(username);
        user.setName(name);
        user.setLastname(lastname);
        user.setPassword(password);
        user.setMail(mail);
        user.setEnabled("0");

        String hash = user.createHash();
        if(userDB.getUser(username) == null){
            userDB.insertUser(username, password, name, lastname, mail, hash);
            user.sendConfirmationMail(hash);
            result.addElemento("1"); //OK
        }else{
            result.addElemento("0"); //USUARIO EXISTENTE
        }
       
        
        return result;
    }   
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/validateMail", method=RequestMethod.GET)
    public ModelAndView validateMail(@RequestParam String hash, @RequestParam String mail, HttpServletRequest request, HttpServletResponse response) throws IOException {
      
            UserDB userDB = new UserDB();
            userDB.connect();     
            if (userDB.validateMail(hash, mail)){
                return new ModelAndView("redirect:" + "login?validation=success");
            }else{
                return new ModelAndView("redirect:" + "login?validation=error");
            }
            
            
    }
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/getProfileData", method=RequestMethod.POST)    
    public AjaxResponse getProfileData(@RequestBody ExecuteCriteriaFiveValues execute) {
        AjaxResponse result = new AjaxResponse();        
   
        String username;
        int id_user = -1;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            username= authentication.getName();
            UserDB userDB = new UserDB();
            userDB.connect();
            User user = userDB.getUser(username);
            id_user = user.getUserId();
        }
        UserDB userDB = new UserDB();
        userDB.connect();        
        User user = userDB.getUser(id_user);
        
        result.addElemento(user.getUsername());
        result.addElemento(user.getName());
        result.addElemento(user.getLastname());
        result.addElemento(user.getMail());
        
        return result;
    }
    
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/modifyAccount", method=RequestMethod.POST)    
    public AjaxResponse modifyAccount(@RequestBody ExecuteCriteriaFiveValues execute) {
        AjaxResponse result = new AjaxResponse();        
   
        String unsafe_name = execute.getValue();
        String unsafe_lastname = execute.getValue2();
        String mail = execute.getValue3();
        String password_old = execute.getValue4();
        String password_new = execute.getValue5();
   
        String name = ScapeString.scape(unsafe_name);
        String lastname = ScapeString.scape(unsafe_lastname);
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username= authentication.getName();
            UserDB userDB = new UserDB();
            userDB.connect();
            User user = userDB.getUser(username);
            if(new BCryptPasswordEncoder().matches(password_old, user.getPassword())){
                if (password_new.equals("")){
                    password_new = user.getPassword();
                }else{
                    password_new = new BCryptPasswordEncoder().encode(password_new);
                }
                userDB.modifyUser(user.getUserId(), user.getUsername(), password_new, "1", name, lastname, mail);
                result.addElemento("1");
            }else{
                result.addElemento("0");
            }
        }
        
        
        
        return result;
    }
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/deleteAccount", method=RequestMethod.POST)    
    public AjaxResponse deleteAccount(@RequestBody ExecuteCriteria execute) {
        AjaxResponse result = new AjaxResponse();        
   
        
        String password = execute.getValue();
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {            
            String username= authentication.getName();
            UserDB userDB = new UserDB();
            userDB.connect();
            User user = userDB.getUser(username);
            if(new BCryptPasswordEncoder().matches(password, user.getPassword())){
                userDB.deleteUser(user.getUserId());
                result.addElemento("1");
            }else{
                result.addElemento("0");
            }
        }
        
        
        
        return result;
    }
    @JsonView(Views.Public.class)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @RequestMapping(value = "/getAllModerationUsers", method=RequestMethod.POST)
    public AjaxResponseListOfLists getAllModerationUsers(@RequestBody SearchCriteria search) {            

        AjaxResponseListOfLists result = new AjaxResponseListOfLists();
        UserDB userDB = new UserDB();
        userDB.connect();
        result.addElementos(userDB.getAllUsers());
        return result;
    }
    @JsonView(Views.Public.class)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @RequestMapping(value = "/getBannedModerationUsers", method=RequestMethod.POST)
    public AjaxResponseListOfLists getBannedModerationUsers(@RequestBody SearchCriteria search) {            

        AjaxResponseListOfLists result = new AjaxResponseListOfLists();
        UserDB userDB = new UserDB();
        userDB.connect();
        result.addElementos(userDB.getBannedUsers());
        return result;
    }
    @JsonView(Views.Public.class)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @RequestMapping(value = "/getZeroCreditsModerationUsers", method=RequestMethod.POST)
    public AjaxResponseListOfLists getZeroCreditsModerationUsers(@RequestBody SearchCriteria search) {            

        AjaxResponseListOfLists result = new AjaxResponseListOfLists();
        UserDB userDB = new UserDB();
        userDB.connect();
        result.addElementos(userDB.getZeroCreditsUsers());
        return result;
    }
    @JsonView(Views.Public.class)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @RequestMapping(value = "/modifyRoleUser", method=RequestMethod.POST)
    public AjaxResponse modifyRoleUser(@RequestBody ExecuteCriteriaTwoValues execute) {            

        AjaxResponse result = new AjaxResponse();
        
        int id_user = Integer.valueOf(execute.getValue());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            UserDB userDB = new UserDB();
            userDB.connect();
            if(id_user != userDB.getUser(authentication.getName()).getUserId()){
                String username = userDB.getUser(id_user).getUsername();
                switch(execute.getValue2()){
                    case "advanced":
                        if(userDB.modifyRole(username, 2))result.addElemento("1");
                        break;
                    case "user":
                        if(userDB.modifyRole(username, 3))result.addElemento("1");
                        break;
                    case "spectator":
                        if(userDB.modifyRole(username, 4))result.addElemento("1");
                        break;
                    case "moderator":
                        if(userDB.modifyRole(username, 5))result.addElemento("1");
                        break;
                    default:
                        break;
                }
            }
        }
        result.addElemento("0");
        return result;
    }
    @JsonView(Views.Public.class)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @RequestMapping(value = "/modifyCreditsUser", method=RequestMethod.POST)
    public AjaxResponse modifyCreditsUser(@RequestBody ExecuteCriteriaTwoValues execute) {            

        AjaxResponse result = new AjaxResponse();
        
        int id_user = Integer.valueOf(execute.getValue());
        int credits = Integer.valueOf(execute.getValue2());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            UserDB userDB = new UserDB();
            userDB.connect();
            if(id_user != userDB.getUser(authentication.getName()).getUserId()){
                String username = userDB.getUser(id_user).getUsername();
                if(userDB.modifyCredits(id_user, credits))
                    result.addElemento("1");
            }
        }
        result.addElemento("0");
        return result;
    }
    @JsonView(Views.Public.class)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @RequestMapping(value = "/modifyEnabledUser", method=RequestMethod.POST)
    public AjaxResponse modifyEnabledUser(@RequestBody ExecuteCriteriaTwoValues execute) {            

        AjaxResponse result = new AjaxResponse();
        
        int id_user = Integer.valueOf(execute.getValue());
        int enabled = -1;
        String op = execute.getValue2();
        switch(op){
            case "1":
                enabled = 1;
                break;
            case "0":
                enabled = 2;
                break;
            default:
                break;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            UserDB userDB = new UserDB();
            userDB.connect();
            if(id_user != userDB.getUser(authentication.getName()).getUserId()){
                String username = userDB.getUser(id_user).getUsername();
                if (enabled!=-1)
                    if(userDB.modifyEnabled(id_user, enabled))
                        result.addElemento("1");
            }
        }
        result.addElemento("0");
        return result;
    }
}
