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
import org.springframework.security.authentication.AnonymousAuthenticationToken;
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
import unlp.rastrosoft.web.model.ExecuteCriteriaFiveValues;
import unlp.rastrosoft.web.model.User;
import unlp.rastrosoft.web.model.UserDB;

/**
 *
 * @author ip300
 */
@RestController

public class AjaxUser  extends HttpServlet{
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/createAccount", method=RequestMethod.POST)    
    public AjaxResponse createAccount(@RequestBody ExecuteCriteriaFiveValues execute) {
        AjaxResponse result = new AjaxResponse();        
   
        String username = execute.getValue();
        String name = execute.getValue2();
        String lastname = execute.getValue3();
        String mail = execute.getValue4();
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
   
        String name = execute.getValue();
        String lastname = execute.getValue2();
        String mail = execute.getValue3();
        String password_old = execute.getValue4();
        String password_new = execute.getValue5();
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username= authentication.getName();
            UserDB userDB = new UserDB();
            userDB.connect();
            User user = userDB.getUser(username);
            System.err.println(password_old);
            System.err.println(user.getPassword());
            if(new BCryptPasswordEncoder().matches(password_old, user.getPassword())){
                System.err.println("entra");
                if (password_new.equals("")){
                    password_new = user.getPassword();
                }else{
                    password_new = new BCryptPasswordEncoder().encode(password_new);
                }
                userDB.modifyUser(user.getUserId(), user.getUsername(), password_new, "1", name, lastname, mail);
            }
        }
        
        
        
        return result;
    }
}
