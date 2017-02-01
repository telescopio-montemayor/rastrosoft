package unlp.rastrosoft.web.model;

import com.fasterxml.jackson.annotation.JsonView;
import unlp.rastrosoft.web.jsonview.Views;
import java.util.List;

public class AjaxResponseBodyIndiListaDispositivos {

	@JsonView(Views.Public.class)
	List<String> dispositivos;
	
        public List<String> getDispositivos() {
            return dispositivos;
        }

        public void setDispositivos(List<String> dispositivos) {
            this.dispositivos = dispositivos;
        }

	@Override
	public String toString() {
            String resultado = "AjaxResponseResult [";    
            
            for (String i: dispositivos) {
                resultado = resultado + i + ", ";
            }
            resultado = resultado + "]";
            
            return resultado;
	}

}
