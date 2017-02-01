package unlp.rastrosoft.web.model;

import com.fasterxml.jackson.annotation.JsonView;
import unlp.rastrosoft.web.jsonview.Views;
import java.util.List;

public class AjaxResponseBodyIndiListaPropiedades {

	@JsonView(Views.Public.class)
	List<String> propiedades;
	
        public List<String> getPropiedades() {
            return propiedades;
        }

        public void setPropiedades(List<String> propiedades) {
            this.propiedades = propiedades;
        }

	@Override
	public String toString() {
            String resultado = "AjaxResponseResult [";    
            
            for (String i: propiedades) {
                resultado = resultado + i + ", ";
            }
            resultado = resultado + "]";
            
            return resultado;
	}

}
