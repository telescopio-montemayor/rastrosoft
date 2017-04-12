package unlp.rastrosoft.web.model;

import com.fasterxml.jackson.annotation.JsonView;
import java.util.ArrayList;
import unlp.rastrosoft.web.jsonview.Views;
import java.util.List;

public class AjaxResponseListOfLists {

	@JsonView(Views.Public.class)
	List<List<String>> elementos;

        public AjaxResponseListOfLists(){
            this.elementos = new ArrayList<>();
        }

        public List<List<String>> getElementos() {
            return elementos;
        }

        public void setElementos(List<List<String>> elementos) {
            this.elementos = elementos;
        }
        
        public void addElemento(List<String> e){
            this.elementos.add(e); 
        }
        
        public void addElementos(List<List<String>> elementos){
            elementos.forEach((e) -> {
                this.elementos.add(e);
            });           
        }

	@Override
	public String toString() {
            String resultado = "AjaxResponseResult [";    
            
            for (List<String> i: elementos) {
                String result = "";
                for (String x: i) {
                    result = result + x + ", ";
                }
                resultado = resultado + " [" + i + "] " + ", ";
            }
            resultado = resultado + "]";
            
            return resultado;
	}

}
