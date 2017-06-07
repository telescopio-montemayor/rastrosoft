/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unlp.rastrosoft.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import unlp.rastrosoft.web.jsonview.Views;
import unlp.rastrosoft.web.model.AjaxResponse;
import unlp.rastrosoft.web.model.AjaxResponseBodyIndiExecute;
import unlp.rastrosoft.web.model.AjaxResponseListOfLists;
import unlp.rastrosoft.web.model.ExecuteCriteria;
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
    @RequestMapping(value = "/validateMail", method=RequestMethod.POST)
    public AjaxResponseBodyIndiExecute validateMail(@RequestBody ExecuteCriteria execute, HttpServletRequest request) {

            AjaxResponseBodyIndiExecute result = new AjaxResponseBodyIndiExecute();
            String hash = execute.getValue();
            
            //VALIDAR
            
            return result;

    }
}
