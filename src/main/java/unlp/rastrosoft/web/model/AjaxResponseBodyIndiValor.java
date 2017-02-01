package unlp.rastrosoft.web.model;

import com.fasterxml.jackson.annotation.JsonView;
import unlp.rastrosoft.web.jsonview.Views;

public class AjaxResponseBodyIndiValor {

	@JsonView(Views.Public.class)
	String valor;

        public String getValor() {
            return valor;
        }

        public void setValor(String valor) {
            this.valor = valor;
        }

	@Override
	public String toString() {
            String resultado = "AjaxResponseResult [";    
            
            resultado = resultado + valor + ", ";
            
            resultado = resultado + "]";
            
            return resultado;
	}

}
