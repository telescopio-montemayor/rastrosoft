package unlp.rastrosoft.web.model;

import com.fasterxml.jackson.annotation.JsonView;
import unlp.rastrosoft.web.jsonview.Views;

public class AjaxResponseBodyIndi {

	@JsonView(Views.Public.class)
	String dispositivo;
	@JsonView(Views.Public.class)
	String propiedad;
        @JsonView(Views.Public.class)
	String elemento;
        @JsonView(Views.Public.class)
	String valor;

        public String getDispositivo() {
            return dispositivo;
        }

        public void setDispositivo(String dispositivo) {
            this.dispositivo = dispositivo;
        }

        public String getPropiedad() {
            return propiedad;
        }

        public void setPropiedad(String propiedad) {
            this.propiedad = propiedad;
        }

        public String getElemento() {
            return elemento;
        }

        public void setElemento(String elemento) {
            this.elemento = elemento;
        }

        public String getValor() {
            return valor;
        }

        public void setValor(String valor) {
            this.valor = valor;
        }

	@Override
	public String toString() {
		return "AjaxResponseResult [dispositivo=" + dispositivo + ", propiedad=" + propiedad + ", elemento=" + elemento + ", valor=" + valor + "]";
	}

}
