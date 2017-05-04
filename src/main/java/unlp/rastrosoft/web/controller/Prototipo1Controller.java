package unlp.rastrosoft.web.controller;

import java.util.logging.Level;
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

	@RequestMapping(value = "/prototipo1", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		return "prototipo1";
	}
        
        @RequestMapping(value = "/prototipo2", method = RequestMethod.GET)
	public String printWelcome2(ModelMap model) {
		return "prototipo2";
	}
        
        @RequestMapping(value = "/prototipo3", method = RequestMethod.GET)
	public String printWelcome3(ModelMap model) {                  
		return "prototipo3";
	}
        
        @RequestMapping(value = "/prototipo4", method = RequestMethod.GET)
	public String printWelcome4(ModelMap model) {                  
		return "prototipo4";
	}
        
        @RequestMapping(value = "/prototipo5", method = RequestMethod.GET)
	public String printWelcome5(ModelMap model) {                  
		return "prototipo5";
	}
        @RequestMapping(value = "/prototipo6", method = RequestMethod.GET)
	public String printWelcome6(ModelMap model) {                  
		return "prototipo6";
	}
        @RequestMapping(value = "/prototipo7", method = RequestMethod.GET)
	public String printWelcome7(ModelMap model) {                  
		return "prototipo7";
	}
        @RequestMapping(value = "/prototipo8", method = RequestMethod.GET)
	public String printWelcome8(ModelMap model) {                  
		return "prototipo8";
	}
        @RequestMapping(value = "/prototipo9", method = RequestMethod.GET)
	public String printWelcome9(ModelMap model) {                  
		return "prototipo9";
	}
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
	public String info(ModelMap model) {                  
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
}
