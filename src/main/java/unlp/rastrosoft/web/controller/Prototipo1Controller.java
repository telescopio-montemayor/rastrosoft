package unlp.rastrosoft.web.controller;

import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import unlp.rastrosoft.web.model.CalendarDB;
import unlp.rastrosoft.web.model.UserDB;

@Controller
public class Prototipo1Controller {

	@RequestMapping(value = "/prototipo10", method = RequestMethod.GET)
	public String printWelcome10(ModelMap model) {
            CalendarDB shift = new CalendarDB();
            shift.connect();
            String user_id = shift.getCurrentShift().get(0);            
            UserDB user = new UserDB();
            user.connect();      
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {                
                if (user_id.equals(String.valueOf(user.getUser(authentication.getName()).getUserId()))){
                    return "prototipo10";
                }
            }
            return "info";
	}
        
        @RequestMapping(value = "/prototiposse", method = RequestMethod.GET)
	public String prototiposse(ModelMap model) {                  
		return "prototiposse";
	}
        @RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model) { 
                java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
                java.util.logging.Logger.getLogger("org.spring").setLevel(Level.OFF);
		return "login";
	}
        @RequestMapping(value = "/calendar", method = RequestMethod.GET)
	public String calendar(ModelMap model) {                  
		return "calendar";
	}
        @RequestMapping(value = "/info", method = RequestMethod.GET)
	public String info(ModelMap model, HttpServletRequest request) {
            if(request.isUserInRole("ROLE_MODERATOR")){
                return "moderation";
            }
            return "info";
	}
        @RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(ModelMap model) {                  
		return "test";
	}
        @RequestMapping("/live")
	public String live(@RequestParam("key") String key) {
            CalendarDB shift = new CalendarDB();
            shift.connect();
            String live_key = shift.getCurrentShift().get(2).toUpperCase();
            if ( !(live_key.equals("-1")) && (key.toUpperCase().equals(live_key))){
                return "prototipo10";
            }
            return "info";
	}
        @RequestMapping(value = "/moderation", method = RequestMethod.GET)
        @PreAuthorize("hasAnyRole('ROLE_MODERATOR')")
	public String moderation(ModelMap model) {                  
		return "moderation";
	}
        @RequestMapping("/activate")
	public String activate(@RequestParam("hash") String hash, @RequestParam("mail") String mail) {
            UserDB userdb = new UserDB();
            userdb.connect();
            userdb.validateMail(hash, mail);
            return "login";
	}
}
