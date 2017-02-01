package unlp.rastrosoft.web.model;

import com.fasterxml.jackson.annotation.JsonView;
import unlp.rastrosoft.web.jsonview.Views;
import java.util.List;

public class AjaxResponseBodyIndiListaElementos {

	@JsonView(Views.Public.class)
	List<String> elementos;

        public List<String> getElementos() {
            return elementos;
        }

        public void setElementos(List<String> elementos) {
            this.elementos = elementos;
        }

	@Override
	public String toString() {
            String resultado = "AjaxResponseResult [";    
            
            for (String i: elementos) {
                resultado = resultado + i + ", ";
            }
            resultado = resultado + "]";
            
            return resultado;
	}

}
