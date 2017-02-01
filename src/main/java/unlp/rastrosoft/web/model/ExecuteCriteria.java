package unlp.rastrosoft.web.model;
public class ExecuteCriteria {

	String dispositivo;
	String propiedad;
        String elemento;
        String valor;

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

	@Override
	public String toString() {
		return "SearchCriteria [dispositivo=" + dispositivo + ", propiedad=" + propiedad + ", elemento=" + elemento + ", valor=" + valor + "]";
	}

}
