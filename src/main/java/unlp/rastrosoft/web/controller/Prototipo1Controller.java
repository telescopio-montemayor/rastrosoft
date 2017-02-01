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

}
