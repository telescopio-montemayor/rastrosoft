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
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import unlp.rastrosoft.web.model.SendMailTLS;
import unlp.rastrosoft.web.model.Telescope;
import unlp.rastrosoft.web.model.User;
import unlp.rastrosoft.web.model.UserDB;

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
	@RequestMapping(value = "/test", method=RequestMethod.POST)
	public AjaxResponseBodyIndiExecute test(@RequestBody ExecuteCriteria execute, HttpServletRequest request) {

		AjaxResponseBodyIndiExecute result = new AjaxResponseBodyIndiExecute();
                
//                UserDB db = new UserDB();
//                db.connect();
//                //System.out.println(db.getUser("alex").getUserId());
//                User user = db.getUser("alex");
//                db.modifyUser(user.getUserId(), user.getUsername(), "00001", user.getRole());
                  SendMailTLS mail = new SendMailTLS();
                  String to,subject,content;
                  to = "alexboette@gmail.com";
                  subject = "Solicitud de registro en rastrosoft";
                  //content = "Bienvenido," + "\n\n Por favor haga <a href=\"http://www.google.com\">click aqui</a> para verificar su cuenta y poder acceder.";
                  content = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                            "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                            "<head>\n" +
                            "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                            "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n" +
                            "  <title>Bienvenido a rastrosoft</title>\n" +
                            "\n" +
                            "  <style type=\"text/css\">\n" +
                            "  @import url(http://fonts.googleapis.com/css?family=Droid+Sans);\n" +
                            "\n" +
                            "  /* Take care of image borders and formatting */\n" +
                            "\n" +
                            "  img {\n" +
                            "    max-width: 600px;\n" +
                            "    outline: none;\n" +
                            "    text-decoration: none;\n" +
                            "    -ms-interpolation-mode: bicubic;\n" +
                            "  }\n" +
                            "\n" +
                            "  a {\n" +
                            "    text-decoration: none;\n" +
                            "    border: 0;\n" +
                            "    outline: none;\n" +
                            "    color: #bbbbbb;\n" +
                            "  }\n" +
                            "\n" +
                            "  a img {\n" +
                            "    border: none;\n" +
                            "  }\n" +
                            "\n" +
                            "  /* General styling */\n" +
                            "\n" +
                            "  td, h1, h2, h3  {\n" +
                            "    font-family: Helvetica, Arial, sans-serif;\n" +
                            "    font-weight: 400;\n" +
                            "  }\n" +
                            "\n" +
                            "  td {\n" +
                            "    text-align: center;\n" +
                            "  }\n" +
                            "\n" +
                            "  body {\n" +
                            "    -webkit-font-smoothing:antialiased;\n" +
                            "    -webkit-text-size-adjust:none;\n" +
                            "    width: 100%;\n" +
                            "    height: 100%;\n" +
                            "    color: #37302d;\n" +
                            "    background: #ffffff;\n" +
                            "    font-size: 16px;\n" +
                            "  }\n" +
                            "\n" +
                            "   table {\n" +
                            "    border-collapse: collapse !important;\n" +
                            "  }\n" +
                            "\n" +
                            "  .headline {\n" +
                            "    color: #ffffff;\n" +
                            "    font-size: 36px;\n" +
                            "  }\n" +
                            "\n" +
                            " .force-full-width {\n" +
                            "  width: 100% !important;\n" +
                            " }\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "  </style>\n" +
                            "\n" +
                            "  <style type=\"text/css\" media=\"screen\">\n" +
                            "      @media screen {\n" +
                            "         /*Thanks Outlook 2013! http://goo.gl/XLxpyl*/\n" +
                            "        td, h1, h2, h3 {\n" +
                            "          font-family: 'Droid Sans', 'Helvetica Neue', 'Arial', 'sans-serif' !important;\n" +
                            "        }\n" +
                            "      }\n" +
                            "  </style>\n" +
                            "\n" +
                            "  <style type=\"text/css\" media=\"only screen and (max-width: 480px)\">\n" +
                            "    /* Mobile styles */\n" +
                            "    @media only screen and (max-width: 480px) {\n" +
                            "\n" +
                            "      table[class=\"w320\"] {\n" +
                            "        width: 320px !important;\n" +
                            "      }\n" +
                            "\n" +
                            "\n" +
                            "    }\n" +
                            "  </style>\n" +
                            "</head>\n" +
                            "<body class=\"body\" style=\"padding:0; margin:0; display:block; background:#ffffff; -webkit-text-size-adjust:none\" bgcolor=\"#ffffff\">\n" +
                            "<table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"100%\" >\n" +
                            "  <tr>\n" +
                            "    <td align=\"center\" valign=\"top\" bgcolor=\"#ffffff\"  width=\"100%\">\n" +
                            "      <center>\n" +
                            "        <table style=\"margin: 0 auto;\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" class=\"w320\">\n" +
                            "          <tr>\n" +
                            "            <td align=\"center\" valign=\"top\">\n" +
                            "\n" +
                            "                <table style=\"margin: 0 auto;\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"margin:0 auto;\">\n" +
                            "                  <tr>\n" +
                            "                    <td style=\"font-size: 30px; text-align:center;\">\n" +
                            "                      <br>\n" +
                            "                        Rastrosoft\n" +
                            "                      <br>\n" +
                            "                      <br>\n" +
                            "                    </td>\n" +
                            "                  </tr>\n" +
                            "                </table>\n" +
                            "\n" +
                            "                <table style=\"margin: 0 auto;\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgcolor=\"#4dbfbf\">\n" +
                            "                  <tr>\n" +
                            "                    <td>\n" +
                            "                    <br>\n" +
                            "                      <img src=\"https://www.filepicker.io/api/file/Pv8CShvQHeBXdhYu9aQE\" width=\"216\" height=\"189\" alt=\"robot picture\">\n" +
                            "                    </td>\n" +
                            "                  </tr>\n" +
                            "                  <tr>\n" +
                            "                    <td class=\"headline\">\n" +
                            "                      Bienvenido!\n" +
                            "                    </td>\n" +
                            "                  </tr>\n" +
                            "                  <tr>\n" +
                            "                    <td>\n" +
                            "\n" +
                            "                      <center>\n" +
                            "                        <table style=\"margin: 0 auto;\" cellpadding=\"0\" cellspacing=\"0\" width=\"60%\">\n" +
                            "                          <tr>\n" +
                            "                            <td style=\"color:#187272;\">\n" +
                            "                            <br>\n" +
                            "                             Al asombroso mundo de la astronomía moderna! Estamos seguros que te sentirás como en casa con Rastrosoft.\n" +
                            "                            <br>\n" +
                            "                            <br>\n" +
                            "                            </td>\n" +
                            "                          </tr>\n" +
                            "                        </table>\n" +
                            "                      </center>\n" +
                            "\n" +
                            "                    </td>\n" +
                            "                  </tr>\n" +
                            "                  <tr>\n" +
                            "                    <td>\n" +
                            "                      <div><!--[if mso]>\n" +
                            "                        <v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" href=\"http://www.google.com\" style=\"height:50px;v-text-anchor:middle;width:200px;\" arcsize=\"8%\" stroke=\"f\" fillcolor=\"#178f8f\">\n" +
                            "                          <w:anchorlock/>\n" +
                            "                          <center>\n" +
                            "                        <![endif]-->\n" +
                            "                            <a href=\"http://www.google.com\"\n" +
                            "                      style=\"background-color:#178f8f;border-radius:4px;color:#ffffff;display:inline-block;font-family:Helvetica, Arial, sans-serif;font-size:16px;font-weight:bold;line-height:50px;text-align:center;text-decoration:none;width:200px;-webkit-text-size-adjust:none;\">Activar cuenta!</a>\n" +
                            "                        <!--[if mso]>\n" +
                            "                          </center>\n" +
                            "                        </v:roundrect>\n" +
                            "                      <![endif]--></div>\n" +
                            "                      <br>\n" +
                            "                      <br>\n" +
                            "                    </td>\n" +
                            "                  </tr>\n" +
                            "                </table>\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "            </td>\n" +
                            "          </tr>\n" +
                            "        </table>\n" +
                            "    </center>\n" +
                            "    </td>\n" +
                            "  </tr>\n" +
                            "</table>\n" +
                            "</body>\n" +
                            "</html>";
                mail.sendMail(to,subject,content);
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
            String path = "/home/ip300/NetBeansProjects/rastrosoft/src/main/webapp/captures";
            String source= path+"/"+currentUserName+"/";
            ccd.setLocalMode();
            ccd.setUploadDirectory(source);
            
            ccd.setTemperature("-15");
            ccd.setExposure("1");
            return result;

        }
//    private Map<String, AsyncContext> asyncContexts = new ConcurrentHashMap<String, AsyncContext>();      
    private BlockingQueue<String> messageQueue;
    
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/getChat", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponseListOfLists getChat(@RequestBody ExecuteCriteria execute) {
        AjaxResponseListOfLists result = new AjaxResponseListOfLists();        
        String id_user;       
        id_user = execute.getValue();
        ChatDB chatDB = new ChatDB();
        chatDB.connect();
        
        result.addElementos(chatDB.getChatsAsList());
        
        return result;
    }    
    
    //Object syncObject;
    Object mon;
    private Runnable notifierAll;
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
        chatDB.insertMessage(id_user, message);
        notifierAll = () -> {
            synchronized (mon) {
                mon.notifyAll();
            }
        };
        notifierAll.run();
//            try {
//                messageQueue.put("1");
//            } catch (InterruptedException ex) {
//                Logger.getLogger(AjaxController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        synchronized(syncObject) {
//            syncObject.notify();
//        }
        return result;
    }  
    
//    private Thread notifier = new Thread(new Runnable() {
//
//        @Override
//        public void run() {
//            while (true){
////               if(chatDB.hasNewMessage()){
//                System.out.println("--------------------------------------------HOLAAAAAAA RUN-------------------------------------------------");//               try {
//                //                        Thread.sleep(1000);
//                //                } catch (InterruptedException e) {
//                //                        e.printStackTrace();
//                //                }
//                System.out.println("--------------------------------------------"+messageQueue.toString()+"-------------------------------------------------");
//                System.out.println("--------------------------------------------HOLAAAAAAA TOMO MENSAJE-------------------------------------------------");
//                String newmessage = null;
//                try {
//                    newmessage = messageQueue.take();
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(AjaxController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                System.out.println("--------------------------------------------HOLAAAAAAA TOMO MENSAJE"+newmessage+"-------------------------------------------------");
//                //
//                //                    writer.write(resultado);
//                for (AsyncContext asyncContext : asyncContexts.values()) {
//                    try {
//                        ChatDB chatDB = new ChatDB();
//                        chatDB.connect();
//                        List<List<String>> elementos = chatDB.getChatsAsList();
//
//                        String resultado = "data: [";
//                        for (List<String> i: elementos) {
//                            String result = "";
//                            for (String x: i) {
//                                result = result + x + ", ";
//                            }
//                            resultado = resultado + "" + i + "" + ", ";
//                        }
//                        resultado = resultado + "] \n\n";
//                        asyncContext.getResponse().getWriter().write(resultado);
//                    } catch (Exception e) {
//                        // In case of exception remove context from map
//                        asyncContexts.values().remove(asyncContext);
//                    }
//                }
//                //messageQueue.clear();
//           }
//        }
//    });
    
    
//    boolean initia = true;

    
//    @JsonView(Views.Public.class)
//    @RequestMapping(value = "/hola", method=RequestMethod.GET)
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")    
//    public void doGet(HttpServletRequest request, HttpServletResponse response)
//                    throws ServletException, IOException {
//          System.out.println("--------------------------------------------HOLAAAAAAA DOGET-------------------------------------------------");  
//        if ("text/event-stream".equals(request.getHeader("Accept"))) {
//            if (initia){
//                notifier.start();
//                initia=false;
//            }
//            System.out.println("--------------------------------------------HOLAAAAAAA TEXT EVENT-------------------------------------------------");
//            response.setContentType("text/event-stream");
//            response.setHeader("Cache-Control", "no-cache");
//            response.setHeader("Connection", "keep-alive");
//            response.setCharacterEncoding("UTF-8");
//            
//            // Generate some unique identifier used to store context in map
//            final String id = UUID.randomUUID().toString();
//
//            // Start asynchronous context and add listeners to remove it in case of errors
//            final AsyncContext ac = request.startAsync();
//            ac.addListener(new AsyncListener() {
//
//                @Override
//                public void onComplete(AsyncEvent event) throws IOException {
//                        asyncContexts.remove(id);
//                }
//
//                @Override
//                public void onError(AsyncEvent event) throws IOException {
//                        asyncContexts.remove(id);
//                }
//
//                @Override
//                public void onStartAsync(AsyncEvent event) throws IOException {
//                        // Do nothing
//                }
//
//                @Override
//                public void onTimeout(AsyncEvent event) throws IOException {
//                        asyncContexts.remove(id);
//                }
//
//               
//            });
//            
//            // Put context in a map
//            asyncContexts.put(id, ac);
//            System.out.println("--------------------------------------------HOLAAAAAAA PUT-------------------------------------------------");
//        }    
//            PrintWriter writer = response.getWriter();
//            writer.close();
            
//            ChatDB chatDB = new ChatDB();
//            chatDB.connect();
            
            //writer.close();
//            --------------------------------------------------------------
////            while (true){
//               synchronized(syncObject) {
//                    try {
//                        // Calling wait() will block this thread until another thread
//                        // calls notify() on the object.
//                        syncObject.wait();
//                        
//                        List<List<String>> elementos = chatDB.getChatsAsList();
//                        String resultado = "data: [";    
//                        for (List<String> i: elementos) {
//                            String result = "";
//                            for (String x: i) {
//                                result = result + x + ", ";
//                            }
//                            resultado = resultado + " [" + i + "] " + ", ";
//                        }
//                        resultado = resultado + "] \n\n"; 
//                        writer.write(resultado);
//                    } catch (InterruptedException e) {
//                        // Happens if someone interrupts your thread.
//                    }
//                }
                
//            }
//    }
    
    PrintWriter writer;
    boolean initia = true;
    String newmessage = null;
    String resultado = "data: 1\n\n";
    private Thread notifier = new Thread(new Runnable() {

        @Override
        public void run() {
            while (true){
                synchronized (mon) {
                    try {
                        mon.wait();
                        writer.write(resultado);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(AjaxController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
//                try {
//                    newmessage = messageQueue.take();
//                    if (newmessage!= null){
//                        writer.write(resultado);
//                        newmessage = null;
//                        messageQueue.clear();
//                    }
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(AjaxController.class.getName()).log(Level.SEVERE, null, ex);
//                }
            }
            
        }
    });  
    
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/newchat", method=RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public void doGet(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {

            //content type must be set to text/event-stream
            response.setContentType("text/event-stream");	

            //encoding must be set to UTF-8
            response.setCharacterEncoding("UTF-8");

            
           
            if(initia){
                mon = new Object();
                writer = response.getWriter();
                messageQueue = new LinkedBlockingQueue<>();
                initia=false;
                notifier.start();                
            }
            
            
            
    }
    
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/liveTransmit", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponseListOfLists liveTransmit(@RequestBody ExecuteCriteria execute) {
        AjaxResponseListOfLists result = new AjaxResponseListOfLists();        
        LiveTransmitDB liveTransmitDB = new LiveTransmitDB();
        liveTransmitDB.connect();
        String live;       
        live = execute.getValue();
        if (live.equals("true")){
            liveTransmitDB.startLiveTransmit();
        }else{
            liveTransmitDB.stopLiveTransmit();
        }        
        return result;
    }   
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/enableChat", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponseListOfLists enableChat(@RequestBody ExecuteCriteria execute) {
        AjaxResponseListOfLists result = new AjaxResponseListOfLists();        
        ChatDB chatDB = new ChatDB();
        chatDB.connect();
        String enableChat;       
        enableChat = execute.getValue();
        if (enableChat.equals("true")){
            chatDB.enableChat();
        }else{
            chatDB.disableChat();
        }        
        return result;
    }
}
