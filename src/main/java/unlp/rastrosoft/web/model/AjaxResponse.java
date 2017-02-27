package unlp.rastrosoft.web.model;

import com.fasterxml.jackson.annotation.JsonView;
import unlp.rastrosoft.web.jsonview.Views;
import java.util.List;

public class AjaxResponse {

	@JsonView(Views.Public.class)
	List<String> elementos;

        public List<String> getElementos() {
            return elementos;
        }

        public void setElementos(List<String> elementos) {
            this.elementos = elementos;
        }
        
        public void addElemento(String e){
            this.elementos.add(e); 
        }
        
        public void addElementos(List<String> elementos){
            elementos.forEach((e) -> {
                this.elementos.add(e);
            });           
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
