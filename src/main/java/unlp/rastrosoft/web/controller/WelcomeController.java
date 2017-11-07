package unlp.rastrosoft.web.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import unlp.rastrosoft.web.model.CalendarDB;
import unlp.rastrosoft.web.model.UserDB;

@Controller
public class WelcomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
            CalendarDB shift = new CalendarDB();
            shift.connect();
            String user_id = shift.getCurrentShift().get(0);            
            UserDB user = new UserDB();
            user.connect();          
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                if (user_id.equals(String.valueOf(user.getUser(authentication.getName()).getUserId()))){
                    return "rastrosoft";
                }
            }
            return "info";
            
	}

}
