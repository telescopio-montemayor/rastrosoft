package unlp.rastrosoft.web.controller;



import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import unlp.rastrosoft.web.jsonview.Views;
import unlp.rastrosoft.web.model.AjaxResponseBodyIndi;
import unlp.rastrosoft.web.model.AjaxResponseBodyIndiExecute;
import unlp.rastrosoft.web.model.AjaxResponseBodyIndiListaDispositivos;
import unlp.rastrosoft.web.model.AjaxResponseBodyIndiListaElementos;
import unlp.rastrosoft.web.model.AjaxResponseBodyIndiListaPropiedades;
import unlp.rastrosoft.web.model.ExecuteCriteria;
import unlp.rastrosoft.web.model.SearchCriteria;
import unlp.rastrosoft.web.model.connect_indi;
import unlp.rastrosoft.web.model.indi_client;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import unlp.rastrosoft.web.model.AjaxResponse;
import unlp.rastrosoft.web.model.CalendarDB;
import unlp.rastrosoft.web.model.Telescope;
import unlp.rastrosoft.web.model.User;
import unlp.rastrosoft.web.model.UserDB;

@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AjaxController {

	
	// @ResponseBody, not necessary, since class is annotated with @RestController
	// @RequestBody - Convert the json data into object (SearchCriteria) mapped by field name.
	// @JsonView(Views.Public.class) - Optional, limited the json data display to client.
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/buscar", method=RequestMethod.POST)
	public AjaxResponseBodyIndi getSearchResultViaAjax(@RequestBody SearchCriteria search) {

		AjaxResponseBodyIndi result = new AjaxResponseBodyIndi();

                if (isValidSearchCriteria(search)) {
                        
                    indi_client cliente = connect_indi.connect("Telescope Simulator", "CCD Simulator", "Focuser Simulator");

                    String device, property, element, value;

                    device = search.getDevice();
                    property = search.getProperty();
                    element = search.getElement();          
                    value = cliente.enviar_mensaje(device, property, element);
                    //cliente.verPropiedades();
                    result.setDispositivo(device);
                    result.setPropiedad(property);
                    result.setElemento(element);
                    result.setValor(value);                   
                  
			
		}

		//AjaxResponseBody will be converted into json format and send back to client.
		return result;

	}
        
        @JsonView(Views.Public.class)
	@RequestMapping(value = "/commitValor", method=RequestMethod.POST)
	public AjaxResponseBodyIndiExecute getCommitValorResultViaAjax(@RequestBody ExecuteCriteria execute) {

		AjaxResponseBodyIndiExecute result = new AjaxResponseBodyIndiExecute();

                if (isValidExecuteCriteria(execute)) {
                        
                    indi_client cliente = connect_indi.connect("Telescope Simulator", "CCD Simulator", "Focuser Simulator");

                    String device, property, element, value;

                    device = execute.getDevice();
                    property = execute.getProperty();
                    element = execute.getElement();  
                    value = execute.getValue();
                    if(cliente.commitBooleanValor(device, property, element, value)){
                        result.setOperacion(Boolean.TRUE);
                    }else{
                        result.setOperacion(Boolean.FALSE);
                    }
                    result.setDispositivo(device);
                    result.setPropiedad(property);
                    result.setElemento(element);
                    result.setValor(value);
                  
                } 
		//AjaxResponseBody will be converted into json format and send back to client.
		return result;
	}
        
        @JsonView(Views.Public.class)
	@RequestMapping(value = "/pushValor", method=RequestMethod.POST)
	public AjaxResponseBodyIndiExecute getPushValorResultViaAjax(@RequestBody ExecuteCriteria execute) {

		AjaxResponseBodyIndiExecute result = new AjaxResponseBodyIndiExecute();

                indi_client cliente = connect_indi.connect("Telescope Simulator", "CCD Simulator", "Focuser Simulator");

                String device, property;

                device = execute.getDevice();
                property = execute.getProperty();                       
                if(cliente.pushValor(device, property)){
                    result.setOperacion(Boolean.TRUE);
                }else{
                    result.setOperacion(Boolean.FALSE);
                }
                result.setDispositivo(device);
                result.setPropiedad(property);
                
		//AjaxResponseBody will be converted into json format and send back to client.
		return result;
	}
        
        @JsonView(Views.Public.class)
	@RequestMapping(value = "/listaDispositivos", method=RequestMethod.POST)
	public AjaxResponseBodyIndiListaDispositivos getListaDispositivosViaAjax(@RequestBody SearchCriteria search) {

		AjaxResponseBodyIndiListaDispositivos result = new AjaxResponseBodyIndiListaDispositivos();
     
                indi_client cliente = connect_indi.connect("Telescope Simulator", "CCD Simulator", "Focuser Simulator");

                List<String> dispositivos;
                dispositivos = cliente.listaDispositivos();
                result.setDispositivos(dispositivos);
                 
		//AjaxResponseBody will be converted into json format and send back to client.
		return result;

	}
        
        @JsonView(Views.Public.class)
	@RequestMapping(value = "/listaPropiedades", method=RequestMethod.POST)
	public AjaxResponseBodyIndiListaPropiedades getListaPropiedadesViaAjax(@RequestBody SearchCriteria search) {

		AjaxResponseBodyIndiListaPropiedades result = new AjaxResponseBodyIndiListaPropiedades();
                        
                indi_client cliente = connect_indi.connect("Telescope Simulator", "CCD Simulator", "Focuser Simulator");
                List<String> propiedades;
                propiedades = cliente.listaPropiedades(search.getDevice());
                result.setPropiedades(propiedades);

		//AjaxResponseBody will be converted into json format and send back to client.
		return result;

	}
        
        @JsonView(Views.Public.class)
	@RequestMapping(value = "/listaElementos", method=RequestMethod.POST)
	public AjaxResponseBodyIndiListaElementos getListaElementosViaAjax(@RequestBody SearchCriteria search) {

		AjaxResponseBodyIndiListaElementos result = new AjaxResponseBodyIndiListaElementos();

                indi_client cliente = connect_indi.connect("Telescope Simulator", "CCD Simulator", "Focuser Simulator");
                List<String> elementos;
                elementos = cliente.listaElementos(search.getDevice(), search.getProperty());
                result.setElementos(elementos);

		//AjaxResponseBody will be converted into json format and send back to client.
		return result;

	}
        
//        @JsonView(Views.Public.class)
//	@RequestMapping(value = "/buscarValor", method=RequestMethod.POST)
//	public AjaxResponseBodyIndiValor getValorViaAjax(@RequestBody SearchCriteria search) {
//
//		AjaxResponseBodyIndiValor result = new AjaxResponseBodyIndiValor();
//
//                if (isValidSearchCriteria(search)) {
//			
//                    indi_client cliente = connect_indi.connect("Telescope Simulator", "CCD Simulator", "Focuser Simulator");
//
//                    String device, property, element, value;
//
//                    device = search.getDevice();
//                    property = search.getProperty();
//                    element = search.getElement();            
//                    value = cliente.enviar_mensaje(device, property, element);
//                    result.setValor(value);
//			
//		}
//
//		//AjaxResponseBody will be converted into json format and send back to client.
//		return result;
//
//	}
//        
        @JsonView(Views.Public.class)
	@RequestMapping(value = "/buscarValor", method=RequestMethod.POST)
	public AjaxResponse getValorViaAjax(@RequestBody SearchCriteria search) {

            AjaxResponse result = new AjaxResponse();

            if (isValidSearchCriteria(search)) {

                indi_client cliente = connect_indi.connect("Telescope Simulator", "CCD Simulator", "Focuser Simulator");

                String device, property, element, value;

                device = search.getDevice();
                property = search.getProperty();
                element = search.getElement();            
                value = cliente.enviar_mensaje(device, property, element);

                List<String> elementos = new ArrayList<>();
                elementos.add(value);
                result.setElementos(elementos);                    

            }

            //AjaxResponse will be converted into json format and send back to client.
            return result;

	}
                
        @RequestMapping(value = "/getImage/{imageId}")
        @ResponseBody
        public byte[] getImage(@PathVariable long imageId, HttpServletRequest request)  {
            String rpath=request.getRealPath("/");
            rpath=rpath+"/resources/images/"+imageId; // whatever path you used for storing the file
            Path path = Paths.get(rpath);
            byte[] data = null; 
            try {
                data = Files.readAllBytes(path);
            } catch (IOException ex) {
                Logger.getLogger(AjaxController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return data;
        }
        
	private boolean isValidSearchCriteria(SearchCriteria search) {

		boolean valid = true;

		if (search == null) {
			valid = false;
		}

		if ((StringUtils.isEmpty(search.getDevice())) && (StringUtils.isEmpty(search.getProperty())) && (StringUtils.isEmpty(search.getElement()))) {
			valid = false;
		}

		return valid;
	}
        private boolean isValidExecuteCriteria(ExecuteCriteria execute) {
            
		boolean valid = true;

		if (execute == null) {
			valid = false;
		}

		if ((StringUtils.isEmpty(execute.getDevice())) && (StringUtils.isEmpty(execute.getProperty())) && (StringUtils.isEmpty(execute.getElement())) && (StringUtils.isEmpty(execute.getValue()))) {
			valid = false;
		}

		return valid;
	}
        
        @JsonView(Views.Public.class)
	@RequestMapping(value = "/test", method=RequestMethod.POST)
	public AjaxResponseBodyIndiExecute test(@RequestBody ExecuteCriteria execute, HttpServletRequest request) {

		AjaxResponseBodyIndiExecute result = new AjaxResponseBodyIndiExecute();
                
                UserDB db = new UserDB();
                db.connect();
                //System.out.println(db.getUser("alex").getUserId());
                User user = db.getUser("alex");
                db.modifyUser(user.getUserId(), user.getUsername(), "00001", user.getRole());
                
		return result;

	}
        
        @JsonView(Views.Public.class)
        @RequestMapping(value = "/getUsername", method=RequestMethod.POST)
        public AjaxResponse getUsername(@RequestBody SearchCriteria search) {            
            
            AjaxResponse result = new AjaxResponse();

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                String currentUserName = authentication.getName();
                result.addElemento(currentUserName);            
            }
            return result;
        }
        
        @JsonView(Views.Public.class)
        @RequestMapping(value = "/getShifts", method=RequestMethod.POST)
        public AjaxResponse getShifts(@RequestBody SearchCriteria search) {            
            
            AjaxResponse result = new AjaxResponse();

            
            CalendarDB shifts = new CalendarDB();    
            shifts.connect();
            result.addElementos(shifts.getShifts());
            return result;
        }
        
        
        @JsonView(Views.Public.class)
        @RequestMapping(value = "/refreshValues", method=RequestMethod.POST)
        public AjaxResponse refreshValues(@RequestBody SearchCriteria search) {
            
            
            AjaxResponse result = new AjaxResponse();

            Telescope telescope = new Telescope();
                
            result.setElementos(telescope.getRaDec());
            result.addElemento(telescope.getPark());
            result.addElemento(telescope.getUnPark());
            result.addElemento(telescope.getTrak());
            result.addElemento(telescope.getSlew());
            result.addElemento(telescope.getSync());
            return result;

        }
        
}
