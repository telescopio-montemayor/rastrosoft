package unlp.rastrosoft.web.model;
public class SearchCriteria {

	String dispositivo;
	String propiedad;
        String elemento;

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

	@Override
	public String toString() {
		return "SearchCriteria [dispositivo=" + dispositivo + ", propiedad=" + propiedad + ", elemento=" + elemento + "]";
	}

}
