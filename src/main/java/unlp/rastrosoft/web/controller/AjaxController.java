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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import unlp.rastrosoft.web.model.AjaxResponse;
import unlp.rastrosoft.web.model.AjaxResponseListOfLists;
import unlp.rastrosoft.web.model.CalendarDB;
import unlp.rastrosoft.web.model.Ccd;
import unlp.rastrosoft.web.model.ChatDB;
import unlp.rastrosoft.web.model.Focuser;
import unlp.rastrosoft.web.model.LiveTransmitDB;
import unlp.rastrosoft.web.model.Telescope;
import unlp.rastrosoft.web.model.User;
import unlp.rastrosoft.web.model.UserDB;
import unlp.rastrosoft.websocket.DeviceSessionHandler;

@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AjaxController  extends HttpServlet{
        
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
            result.addElementos(shifts.getShifts(LocalDateTime.now().toString()));
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
            
            
            Ccd ccd = new Ccd();
            
            result.addElemento(ccd.getUploadDirectory());
            result.addElemento(ccd.getUploadPrefix());
            result.addElemento(ccd.getHBinning());
            result.addElemento(ccd.getVBinning());
            result.addElemento(ccd.getTemperature());
            result.addElemento(ccd.getFrameLight());
            result.addElemento(ccd.getFrameBias());
            result.addElemento(ccd.getFrameDark());
            result.addElemento(ccd.getFrameFlat());
            result.addElemento(ccd.getX());
            result.addElemento(ccd.getY());
            result.addElemento(ccd.getWidth());
            result.addElemento(ccd.getHeight());
            result.addElemento(ccd.getExposureTime());
            
            
            Focuser focuser = new Focuser();
            result.addElemento(focuser.getFocusIn());
            result.addElemento(focuser.getFocusOut());
            result.addElemento(focuser.getAbsolutePosition());
            result.addElemento(focuser.getRelativePosition());
            
            result.addElemento(ccd.getFilePath());
            
            LiveTransmitDB liveTransmitDB = new LiveTransmitDB();
            liveTransmitDB.connect();
            String live;
            if(liveTransmitDB.isOnLive()){
                live="1";
            }else{
                live="0";
            }
            result.addElemento(live);
            
            ChatDB chatDB = new ChatDB();
            chatDB.connect();
            String chatEnabled;
            if(chatDB.isEnabled()){
                chatEnabled="1";
            }else{
                chatEnabled="0";
            }
            
            result.addElemento(chatEnabled);
            
            CalendarDB calendarDB = new CalendarDB();
            calendarDB.connect();
            ArrayList<String> shift =  calendarDB.getCurrentShift();
            
            if (shift.get(0).equals("-1")){
                result.addElemento("-1");
                result.addElemento("00:00:00");                
            }else{
                UserDB userDB = new UserDB();
                userDB.connect();
                User user = userDB.getUser( Integer.parseInt(shift.get(0)));
                result.addElemento(user.getUsername());
                result.addElemento(shift.get(1));                
            }
            
            return result;

        }
        
//        PARA EL FOCUSER: MICROFOCUSER LX 200
        @JsonView(Views.Public.class)
        @RequestMapping(value = "/refreshValuesMicro", method=RequestMethod.POST)
        public AjaxResponse refreshValuesMicro(@RequestBody SearchCriteria search) {
            
            
            AjaxResponse result = new AjaxResponse();

            Telescope telescope = new Telescope();
                
            result.setElementos(telescope.getRaDec());
            result.addElemento(telescope.getPark());
            result.addElemento(telescope.getUnPark());
            result.addElemento(telescope.getTrak());
            result.addElemento(telescope.getSlew());
            result.addElemento(telescope.getSync());
            
            
            Ccd ccd = new Ccd();
            
            result.addElemento(ccd.getUploadDirectory());
            result.addElemento(ccd.getUploadPrefix());
            result.addElemento(ccd.getHBinning());
            result.addElemento(ccd.getVBinning());
            result.addElemento(ccd.getTemperature());
            result.addElemento(ccd.getFrameLight());
            result.addElemento(ccd.getFrameBias());
            result.addElemento(ccd.getFrameDark());
            result.addElemento(ccd.getFrameFlat());
            result.addElemento(ccd.getX());
            result.addElemento(ccd.getY());
            result.addElemento(ccd.getWidth());
            result.addElemento(ccd.getHeight());
            result.addElemento(ccd.getExposureTime());
            
            
            Focuser focuser = new Focuser();
            result.addElemento("0");
            result.addElemento("0");
            result.addElemento("0");
            result.addElemento("0");
            
            result.addElemento(ccd.getFilePath());
            
            LiveTransmitDB liveTransmitDB = new LiveTransmitDB();
            liveTransmitDB.connect();
            String live;
            if(liveTransmitDB.isOnLive()){
                live="1";
            }else{
                live="0";
            }
            result.addElemento(live);
            
            ChatDB chatDB = new ChatDB();
            chatDB.connect();
            String chatEnabled;
            if(chatDB.isEnabled()){
                chatEnabled="1";
            }else{
                chatEnabled="0";
            }
            
            result.addElemento(chatEnabled);
            
            CalendarDB calendarDB = new CalendarDB();
            calendarDB.connect();
            ArrayList<String> shift =  calendarDB.getCurrentShift();
            
            if (shift.get(0).equals("-1")){
                result.addElemento("-1");
                result.addElemento("00:00:00");                
            }else{
                UserDB userDB = new UserDB();
                userDB.connect();
                User user = userDB.getUser( Integer.parseInt(shift.get(0)));
                result.addElemento(user.getUsername());
                result.addElemento(shift.get(1));                
            }
            
            return result;

        }
//        ...
        
        @JsonView(Views.Public.class)
        @RequestMapping(value = "/initialize", method=RequestMethod.POST)
        public AjaxResponse initialize(@RequestBody SearchCriteria search) {
            
            
            AjaxResponse result = new AjaxResponse();
            
            Ccd ccd = new Ccd();
            
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentUserName = "default";
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                currentUserName = authentication.getName();        
            }
            //String path = "/home/ip300/NetBeansProjects/rastrosoft/src/main/webapp/captures";
            String path = "/home/ip300/webapp/captures";
            String source= path+"/"+currentUserName+"/";
            String dest = path+"/"+currentUserName;
            ccd.setLocalMode();
            ccd.setUploadDirectory(source);
            
            //ccd.setTemperature("-15"); -----------DESCOMENTAR!
            ccd.setExposure("1", path, source, dest);
            return result;

        }
    
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/getChat", method=RequestMethod.POST)
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponseListOfLists getChat(@RequestBody ExecuteCriteria execute) {
        AjaxResponseListOfLists result = new AjaxResponseListOfLists();        
        String id_user;       
        id_user = execute.getValue();
        ChatDB chatDB = new ChatDB();
        chatDB.connect();
        
        result.addElementos(chatDB.getChatsAsList());
        
        return result;
    }    
    

    @JsonView(Views.Public.class)
    @RequestMapping(value = "/addMessageChat", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponseListOfLists addMessageChat(@RequestBody ExecuteCriteria execute) {
        AjaxResponseListOfLists result = new AjaxResponseListOfLists();        
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
        
        String message;       
        message = execute.getValue();
        ChatDB chatDB = new ChatDB();
        chatDB.connect();
        DeviceSessionHandler sessionHandler;
        sessionHandler = new DeviceSessionHandler();
        
        if (message.equals("/clear_all_msg")){
            chatDB.deleteChat();
            sessionHandler.updateElement("clearChat", "");
            return result;
        }
        List<String> inserted_message = chatDB.insertMessage(id_user, message);
        
        String user = inserted_message.get(0);
        message = inserted_message.get(1);
        String datetime = inserted_message.get(2);
        
        sessionHandler.updateElement("newChat", "["+user+", "+message+", "+datetime+"]");

        return result;
    }  
 
    
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/liveTransmit", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponseListOfLists liveTransmit(@RequestBody ExecuteCriteria execute) {
        AjaxResponseListOfLists result = new AjaxResponseListOfLists();   
        if(AccessControl.AccessControl()){
            DeviceSessionHandler sessionHandler;
            sessionHandler = new DeviceSessionHandler();
            LiveTransmitDB liveTransmitDB = new LiveTransmitDB();
            liveTransmitDB.connect();
            String live, value;     
            live = execute.getValue();
            if (live.equals("true")){
                liveTransmitDB.startLiveTransmit();
                value = "1";
            }else{
                liveTransmitDB.stopLiveTransmit();
                value = "0";
            }
            sessionHandler.updateElement("liveTransmit", "["+value+"]");
        }
              
        return result;
    }   
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/enableChat", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponseListOfLists enableChat(@RequestBody ExecuteCriteria execute) {
        AjaxResponseListOfLists result = new AjaxResponseListOfLists();  
        if(AccessControl.AccessControl()){
            DeviceSessionHandler sessionHandler;
            sessionHandler = new DeviceSessionHandler();
            ChatDB chatDB = new ChatDB();
            chatDB.connect();
            String enableChat, value;       
            enableChat = execute.getValue();
            if (enableChat.equals("true")){
                chatDB.enableChat();
                value = "1";
            }else{
                chatDB.disableChat();
                value = "0";
            }        
            sessionHandler.updateElement("enableChat", "["+value+"]");
        }
        return result;
    }
    
    @RequestMapping(value="/exit", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
    
    @RequestMapping(value="/salir", method = RequestMethod.GET)
    public void logoutPage2 (HttpServletRequest request, HttpServletResponse response) {
        SecurityController sec = new SecurityController();
        sec.logout();
    }
    
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/checkLive", method=RequestMethod.POST)
    public AjaxResponse checkLive(@RequestBody SearchCriteria search) {            

        AjaxResponse result = new AjaxResponse();
        
        LiveTransmitDB liveTransmitDB = new LiveTransmitDB();
        liveTransmitDB.connect();
        CalendarDB shifts = new CalendarDB();    
        shifts.connect();
        ArrayList<String> shift = shifts.getCurrentShift();
        if (!shift.get(2).equals("-1") && liveTransmitDB.isOnLive() ){
            result.addElemento("true");
            if(shift.get(3).equals("1")){                
                result.addElemento(shift.get(2));
            }else{
                result.addElemento("0");
            }
            
        }else{
            result.addElemento("false");
            result.addElemento("0");
        }            
        
        return result;
    }
    
    
}
