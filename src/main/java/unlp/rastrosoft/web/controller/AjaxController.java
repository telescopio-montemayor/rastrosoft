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
import unlp.rastrosoft.web.model.AjaxResponseBodyIndiValor;
import unlp.rastrosoft.web.model.ExecuteCriteria;
import unlp.rastrosoft.web.model.SearchCriteria;
import unlp.rastrosoft.web.model.connect_indi;
import unlp.rastrosoft.web.model.indi_client;
import unlp.rastrosoft.web.model.Fits;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import laazotea.indi.client.INDIValueException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class AjaxController {

	
	// @ResponseBody, not necessary, since class is annotated with @RestController
	// @RequestBody - Convert the json data into object (SearchCriteria) mapped by field name.
	// @JsonView(Views.Public.class) - Optional, limited the json data display to client.
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/buscar", method=RequestMethod.POST)
	public AjaxResponseBodyIndi getSearchResultViaAjax(@RequestBody SearchCriteria search) {

		AjaxResponseBodyIndi result = new AjaxResponseBodyIndi();

                if (isValidSearchCriteria(search)) {
			
                    try {
                        
                        indi_client cliente = connect_indi.connect("Telescope Simulator", "CCD Simulator", "Focuser Simulator");
                                                
                        String dispositivo, propiedad, elemento, valor;
                        
                        dispositivo = search.getDispositivo();
                        propiedad = search.getPropiedad();
                        elemento = search.getElemento();          
                        valor = cliente.enviar_mensaje(dispositivo, propiedad, elemento);
                        //cliente.verPropiedades();
                        result.setDispositivo(dispositivo);
                        result.setPropiedad(propiedad);
                        result.setElemento(elemento);
                        result.setValor(valor);
                        
                    } catch (IOException | INDIValueException ex) {
                        Logger.getLogger(AjaxController.class.getName()).log(Level.SEVERE, null, ex);
                    }
			
		}

		//AjaxResponseBody will be converted into json format and send back to client.
		return result;

	}
        
        @JsonView(Views.Public.class)
	@RequestMapping(value = "/commitValor", method=RequestMethod.POST)
	public AjaxResponseBodyIndiExecute getCommitValorResultViaAjax(@RequestBody ExecuteCriteria execute) {

		AjaxResponseBodyIndiExecute result = new AjaxResponseBodyIndiExecute();

                if (isValidExecuteCriteria(execute)) {
                    try {
                        
                        indi_client cliente = connect_indi.connect("Telescope Simulator", "CCD Simulator", "Focuser Simulator");
                                                
                        String dispositivo, propiedad, elemento, valor;
                        
                        dispositivo = execute.getDispositivo();
                        propiedad = execute.getPropiedad();
                        elemento = execute.getElemento();  
                        valor = execute.getValor();
                        if(cliente.commitBooleanValor(dispositivo, propiedad, elemento, valor)){
                            result.setOperacion(Boolean.TRUE);
                        }else{
                            result.setOperacion(Boolean.FALSE);
                        }
                        result.setDispositivo(dispositivo);
                        result.setPropiedad(propiedad);
                        result.setElemento(elemento);
                        result.setValor(valor);
                        
                    } catch (IOException | INDIValueException ex) {
                        Logger.getLogger(AjaxController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
		//AjaxResponseBody will be converted into json format and send back to client.
		return result;
	}
        
        @JsonView(Views.Public.class)
	@RequestMapping(value = "/pushValor", method=RequestMethod.POST)
	public AjaxResponseBodyIndiExecute getPushValorResultViaAjax(@RequestBody ExecuteCriteria execute) {

		AjaxResponseBodyIndiExecute result = new AjaxResponseBodyIndiExecute();

                if (true) { //CAMBIAR
                    try {
                        
                        indi_client cliente = connect_indi.connect("Telescope Simulator", "CCD Simulator", "Focuser Simulator");
                                                
                        String dispositivo, propiedad;
                        
                        dispositivo = execute.getDispositivo();
                        propiedad = execute.getPropiedad();                       
                        if(cliente.pushValor(dispositivo, propiedad)){
                            result.setOperacion(Boolean.TRUE);
                        }else{
                            result.setOperacion(Boolean.FALSE);
                        }
                        result.setDispositivo(dispositivo);
                        result.setPropiedad(propiedad);
                        
                    } catch (IOException | INDIValueException ex) {
                        Logger.getLogger(AjaxController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
		//AjaxResponseBody will be converted into json format and send back to client.
		return result;
	}
        
        @JsonView(Views.Public.class)
	@RequestMapping(value = "/listaDispositivos", method=RequestMethod.POST)
	public AjaxResponseBodyIndiListaDispositivos getListaDispositivosViaAjax(@RequestBody SearchCriteria search) {

		AjaxResponseBodyIndiListaDispositivos result = new AjaxResponseBodyIndiListaDispositivos();

                if (true) {
			
                    try {
                        
                        indi_client cliente = connect_indi.connect("Telescope Simulator", "CCD Simulator", "Focuser Simulator");
                                                
                        List<String> dispositivos;
                        dispositivos = cliente.listaDispositivos();
                        result.setDispositivos(dispositivos);
                        
                        
                    } catch (IOException | INDIValueException ex) {
                        Logger.getLogger(AjaxController.class.getName()).log(Level.SEVERE, null, ex);
                    }
			
		}

		//AjaxResponseBody will be converted into json format and send back to client.
		return result;

	}
        
        @JsonView(Views.Public.class)
	@RequestMapping(value = "/listaPropiedades", method=RequestMethod.POST)
	public AjaxResponseBodyIndiListaPropiedades getListaPropiedadesViaAjax(@RequestBody SearchCriteria search) {

		AjaxResponseBodyIndiListaPropiedades result = new AjaxResponseBodyIndiListaPropiedades();

                if (true) {
			
                    try {
                        
                        indi_client cliente = connect_indi.connect("Telescope Simulator", "CCD Simulator", "Focuser Simulator");
                        List<String> propiedades;
                        propiedades = cliente.listaPropiedades(search.getDispositivo());
                        result.setPropiedades(propiedades);
                        
                        
                    } catch (IOException | INDIValueException ex) {
                        Logger.getLogger(AjaxController.class.getName()).log(Level.SEVERE, null, ex);
                    }
			
		}

		//AjaxResponseBody will be converted into json format and send back to client.
		return result;

	}
        
        @JsonView(Views.Public.class)
	@RequestMapping(value = "/listaElementos", method=RequestMethod.POST)
	public AjaxResponseBodyIndiListaElementos getListaElementosViaAjax(@RequestBody SearchCriteria search) {

		AjaxResponseBodyIndiListaElementos result = new AjaxResponseBodyIndiListaElementos();

                if (true) {
			
                    try {
                        
                        indi_client cliente = connect_indi.connect("Telescope Simulator", "CCD Simulator", "Focuser Simulator");
                        List<String> elementos;
                        elementos = cliente.listaElementos(search.getDispositivo(), search.getPropiedad());
                        result.setElementos(elementos);
                        
                        
                    } catch (IOException | INDIValueException ex) {
                        Logger.getLogger(AjaxController.class.getName()).log(Level.SEVERE, null, ex);
                    }
			
		}

		//AjaxResponseBody will be converted into json format and send back to client.
		return result;

	}
        
        @JsonView(Views.Public.class)
	@RequestMapping(value = "/buscarValor", method=RequestMethod.POST)
	public AjaxResponseBodyIndiValor getValorViaAjax(@RequestBody SearchCriteria search) {

		AjaxResponseBodyIndiValor result = new AjaxResponseBodyIndiValor();

                if (isValidSearchCriteria(search)) {
			
                    try {
                        
                        indi_client cliente = connect_indi.connect("Telescope Simulator", "CCD Simulator", "Focuser Simulator");
                                                
                        String dispositivo, propiedad, elemento, valor;
                        
                        dispositivo = search.getDispositivo();
                        propiedad = search.getPropiedad();
                        elemento = search.getElemento();          
                        valor = cliente.enviar_mensaje(dispositivo, propiedad, elemento);
                        result.setValor(valor);
                        
                    } catch (IOException | INDIValueException ex) {
                        Logger.getLogger(AjaxController.class.getName()).log(Level.SEVERE, null, ex);
                    }
			
		}

		//AjaxResponseBody will be converted into json format and send back to client.
		return result;

	}
        
        @JsonView(Views.Public.class)
	@RequestMapping(value = "/previewImage", method=RequestMethod.POST)
	public AjaxResponseBodyIndiExecute getPreviewImageViaAjax(@RequestBody ExecuteCriteria execute, HttpServletRequest request) {

		AjaxResponseBodyIndiExecute result = new AjaxResponseBodyIndiExecute();
                
                indi_client cliente = null;
                try {
                    cliente = connect_indi.connect("Telescope Simulator", "CCD Simulator", "Focuser Simulator");
                } catch (INDIValueException | IOException ex) {
                    Logger.getLogger(AjaxController.class.getName()).log(Level.SEVERE, null, ex);
                }
               
                cliente.commitBooleanValor("CCD Simulator", "UPLOAD_MODE", "UPLOAD_LOCAL", "ON");
                cliente.pushValor("CCD Simulator", "UPLOAD_MODE");                
                cliente.commitDoubleValor("CCD Simulator", "CCD_EXPOSURE", "CCD_EXPOSURE_VALUE", "1");
                cliente.pushValor("CCD Simulator", "CCD_EXPOSURE");
                
                String path = "/home/ip300/NetBeansProjects/rastrosoft/src/main/webapp";
                
                String source= path+"/resources/images/fits/"; 
                
                String dest = path+"/resources/images/";
                
                new Fits().fitsToJpg(source, dest, "IMAGE.fits");    
                
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

		if ((StringUtils.isEmpty(search.getDispositivo())) && (StringUtils.isEmpty(search.getPropiedad())) && (StringUtils.isEmpty(search.getElemento()))) {
			valid = false;
		}

		return valid;
	}
        private boolean isValidExecuteCriteria(ExecuteCriteria execute) {
            
		boolean valid = true;

		if (execute == null) {
			valid = false;
		}

		if ((StringUtils.isEmpty(execute.getDispositivo())) && (StringUtils.isEmpty(execute.getPropiedad())) && (StringUtils.isEmpty(execute.getElemento())) && (StringUtils.isEmpty(execute.getValor()))) {
			valid = false;
		}

		return valid;
	}
}
