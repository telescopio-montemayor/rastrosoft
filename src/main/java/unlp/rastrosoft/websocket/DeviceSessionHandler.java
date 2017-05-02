package unlp.rastrosoft.websocket;

import java.io.IOException;
import javax.enterprise.context.ApplicationScoped;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ApplicationScoped
@ServerEndpoint("/actions")
public class DeviceSessionHandler {

    private static final Set<Session> sessions = new HashSet<>();
    
    @OnOpen
    public void open(Session session) {
        addSession(session);
    }

    @OnClose
    public void close(Session session) {
        removeSession(session);
    }

    @OnError
    public void onError(Throwable error) {
        
    }

    @OnMessage
    public void handleMessage(String message, Session session) {

    }
    
    public void addSession(Session session) {
        sessions.add(session);
    }

    public void removeSession(Session session) {
        sessions.remove(session);
    }
    


    public void updateElement(String element, String value) {
        Set<String> prop = new HashSet<>();
        
        prop.add("EQUATORIAL_EOD_COORD");
        prop.add("TELESCOPE_PARK");
        prop.add("ON_COORD_SET");
        
       
        prop.add("UPLOAD_SETTINGS");
        prop.add("CCD_BINNING");
        prop.add("CCD_TEMPERATURE");
        prop.add("CCD_FRAME_TYPE");
        prop.add("CCD_FRAME");
        prop.add("CCD_EXPOSURE");
        prop.add("CCD_FILE_PATH");
        
        prop.add("FOCUS_MOTION");
        prop.add("ABS_FOCUS_POSITION");
        prop.add("REL_FOCUS_POSITION");
        
        prop.add("newCapture");
        prop.add("newChat");
        prop.add("clearChat");
        
        if (prop.contains(element)){
            sendElement(element, value);
        }
            
    }

    private void sendElement(String element, String value){

        String[] values = value.replaceAll("\\[", "").replaceAll("\\]", "").split(",");
        JsonProvider provider = JsonProvider.provider();
        JsonObject message;
        switch(values.length){
            case 1:
                message = provider.createObjectBuilder()
                        .add("action", "update")
                        .add("size", "1")
                        .add("element", element)
                        .add("value", values[0])
                        .build();
                sendToAllConnectedSessions(message);
                break;
            case 2:
                message = provider.createObjectBuilder()
                        .add("action", "update")
                        .add("size", "2")
                        .add("element", element)
                        .add("value", values[0])
                        .add("value2", values[1])
                        .build();
                sendToAllConnectedSessions(message);
                break;
            case 3:
                message = provider.createObjectBuilder()
                        .add("action", "update")
                        .add("size", "3")
                        .add("element", element)
                        .add("value", values[0])
                        .add("value2", values[1])
                        .add("value3", values[2])
                        .build();
                sendToAllConnectedSessions(message);
                break;
            case 4:
                message = provider.createObjectBuilder()
                        .add("action", "update")
                        .add("size", "4")
                        .add("element", element)
                        .add("value", values[0])
                        .add("value2", values[1])
                        .add("value3", values[2])
                        .add("value4", values[3])
                        .build();
                sendToAllConnectedSessions(message);
                break;
            default:
                break;
        }
    }
 

    public void sendToAllConnectedSessions(JsonObject message) {
        for (Session session : sessions) {
            sendToSession(session, message);
        }
    }

    private void sendToSession(Session session, JsonObject message) {
        try {
            session.getBasicRemote().sendText(message.toString());
        } catch (IOException ex) {
            Logger.getLogger(DeviceSessionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}