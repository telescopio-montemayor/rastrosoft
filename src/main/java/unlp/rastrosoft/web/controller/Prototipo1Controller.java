package unlp.rastrosoft.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        @RequestMapping(value = "/prototiposse", method = RequestMethod.GET)
	public String prototiposse(ModelMap model) {                  
		return "prototiposse";
	}
        @RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model) {                  
		return "login";
	}
}
