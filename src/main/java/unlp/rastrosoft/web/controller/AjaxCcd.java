/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unlp.rastrosoft.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import unlp.rastrosoft.web.jsonview.Views;
import unlp.rastrosoft.web.model.AjaxResponse;
import unlp.rastrosoft.web.model.AjaxResponseBodyIndiExecute;
import unlp.rastrosoft.web.model.CalendarDB;
import unlp.rastrosoft.web.model.Capture;
import unlp.rastrosoft.web.model.CaptureDB;
import unlp.rastrosoft.web.model.Ccd;
import unlp.rastrosoft.web.model.ConfigDB;
import unlp.rastrosoft.web.model.ExecuteCriteria;
import unlp.rastrosoft.web.model.ExecuteCriteriaTwoValues;
import unlp.rastrosoft.web.model.Focuser;
import unlp.rastrosoft.web.model.Telescope;
import unlp.rastrosoft.web.model.UserDB;

/**
 *
 * @author ip300
 */
@RestController
public class AjaxCcd {
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/previewImage", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponseBodyIndiExecute getPreviewImageViaAjax(@RequestBody ExecuteCriteria execute, HttpServletRequest request) {

        AjaxResponseBodyIndiExecute result = new AjaxResponseBodyIndiExecute();
        if (AccessControl.AccessControl()){
            Ccd ccd = new Ccd();
            ccd.takePreview();
        }
        return result;
    }
    
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/setUploadDirectory", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponse setUploadDirectory(@RequestBody ExecuteCriteria execute) {

        AjaxResponse result = new AjaxResponse();        
        if (AccessControl.AccessControl()){
            String dir;
            dir = execute.getValue();        
            Ccd ccd = new Ccd();
            ccd.setUploadDirectory(dir);
        }
        return result;
    }
    
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/setPrefix", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponse setPrefix(@RequestBody ExecuteCriteria execute) {

        AjaxResponse result = new AjaxResponse();        
        if (AccessControl.AccessControl()){
            String prefix;
            prefix = execute.getValue();        
            Ccd ccd = new Ccd();
            ccd.setPrefix(prefix);
        }
        return result;
    }
    
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/setBinning", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponse setBinning(@RequestBody ExecuteCriteriaTwoValues execute) {
        AjaxResponse result = new AjaxResponse();  
        if (AccessControl.AccessControl()){
            String hor, ver;       
            hor = execute.getValue();
            ver = execute.getValue2();
            Ccd ccd = new Ccd();
            ccd.setBinning(hor, ver);
        }
        return result;
    }
    
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/setFrameType", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponse setFrameType(@RequestBody ExecuteCriteriaTwoValues execute) {
        AjaxResponse result = new AjaxResponse();
        if (AccessControl.AccessControl()){
            String frameType;       
            frameType = execute.getValue();
            Ccd ccd = new Ccd();
            switch (frameType){
                case "frameLight":
                    ccd.setFrameLight();
                    break;
                case "frameBias":
                    ccd.setFrameBias();
                    break;
                case "frameDark":
                    ccd.setFrameDark();
                    break;    
                case "frameFlat":
                    ccd.setFrameFlat();
                    break;
                default:
                    break;
            }
        }
        return result;
    }
    
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/setCcdTemperature", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponse setCcdTemperature(@RequestBody ExecuteCriteria execute) {
        AjaxResponse result = new AjaxResponse();
        if (AccessControl.AccessControl()){
            String temperature;
            temperature = execute.getValue();        
            Ccd ccd = new Ccd();
            ccd.setTemperature(temperature);
        }
        return result;
    }
    
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/setFrame", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponse setFrame(@RequestBody ExecuteCriteriaTwoValues execute) {
        AjaxResponse result = new AjaxResponse();
        if (AccessControl.AccessControl()){
            String x, y;       
            x = execute.getValue();
            y = execute.getValue2();
            Ccd ccd = new Ccd();
            ccd.setFrame(x, y);
        }
        return result;
    }
    
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/setSize", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponse setSize(@RequestBody ExecuteCriteriaTwoValues execute) {
        AjaxResponse result = new AjaxResponse();
        if (AccessControl.AccessControl()){
            String width, height;       
            width = execute.getValue();
            height = execute.getValue2();
            Ccd ccd = new Ccd();
            ccd.setSize(width, height);
        }
        return result;
    }
    
  
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/setExposure", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponse setExposure(@RequestBody ExecuteCriteriaTwoValues execute) {
        
        AjaxResponse result = new AjaxResponse();        
        String time;       
        time = execute.getValue();
        Ccd ccd = new Ccd();
        
                       
        String  datetime, ra, dec, hBinning, vBinning, temperature, frameType, x, y,
                width, height, focusPosition, exposureTime, filePath;
        
        Telescope telescope = new Telescope();        
        Focuser focuser = new Focuser();
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();    
        
        datetime        =   dateFormat.format(date);
        ra              =   telescope.getRa();
        dec             =   telescope.getDec();
        hBinning        =   ccd.getHBinning();   // "-"; ------ DESCOMENTAR!! ---- (SÓLO PARA LX200)
        vBinning        =   ccd.getVBinning();   // "-"; ------ DESCOMENTAR!! ---- (SÓLO PARA LX200)
        temperature     =   ccd.getTemperature();// "-";
        frameType       =   ccd.getFrameType();
        x               =   ccd.getX();
        y               =   ccd.getY();        
        width           =   ccd.getWidth();        
        height          =   ccd.getHeight();
        focusPosition   =   focuser.getAbsolutePosition();   // "-";------ DESCOMENTAR!! ---- (SÓLO PARA LX200)
        exposureTime    =   time;
        
       
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
        if (idUserCurrentShift == idCurrentUser){
            String folderName = userDB.getUser(idUserCurrentShift).getUsername();
                ConfigDB configDB = new ConfigDB();
                configDB.connect();
            String path = configDB.getPath();
            String source= path+"/"+folderName;
            String dest = path+"/"+folderName;
            ccd.setUploadDirectory(source);
            ccd.setLocalMode();  

            ccd.setExposure(time, path, source, dest);
            filePath        =   ccd.getFilePath(); //REVISAR TIEMPO DE ESPERA
            
            CaptureDB captureDB = new CaptureDB();      
            Capture capture = new Capture("", datetime, ra, dec, hBinning, vBinning, temperature, frameType, x, y, width, height, focusPosition, exposureTime, filePath); 
            captureDB.connect();
            int id_capture = captureDB.insertCapture(capture);
            captureDB.asociateCaptureToUser(idCurrentUser, id_capture);
            
        }
        
        
        return result;
    }
    
    @JsonView(Views.Public.class)
    @RequestMapping(value = "/setAbortExposure", method=RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADVANCED','ROLE_USER')")
    public AjaxResponse setAbortExposure(@RequestBody ExecuteCriteriaTwoValues execute) {
        AjaxResponse result = new AjaxResponse();  
        if (AccessControl.AccessControl()){
            Ccd ccd = new Ccd();        
            if(Double.parseDouble(ccd.getExposureTime()) > 0){
               CaptureDB captureDB = new CaptureDB();
               captureDB.connect();
               captureDB.removeLastCapture();
            }
            ccd.abortExposure();
        }
        return result;
    }
    
  
}
