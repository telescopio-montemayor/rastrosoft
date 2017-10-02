/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unlp.rastrosoft.web.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import unlp.rastrosoft.web.model.CalendarDB;
import unlp.rastrosoft.web.model.UserDB;

/**
 *
 * @author ip300
 */
public class AccessControl {
    public static boolean AccessControl(){
        String currentUserName = null;        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();         
        }
        
        UserDB userDB = new UserDB();
        userDB.connect();
        CalendarDB calendarDB = new CalendarDB();
        calendarDB.connect();
        int idUserCurrentShift = Integer.valueOf(calendarDB.getCurrentShift().get(0));
        int idCurrentUser      = userDB.getUser(currentUserName).getUserId();
        return idUserCurrentShift == idCurrentUser;
    }
}
