package unlp.rastrosoft.web.model;
public class SearchCriteria {

	String device;
	String property;
        String element;

        public String getDevice() {
            return device;
        }

        public void setDevice(String device) {
            this.device = device;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }

        public String getElement() {
            return element;
        }

        public void setElement(String element) {
            this.element = element;
        }
                
	@Override
	public String toString() {
		return "SearchCriteria [device=" + device + ", property=" + property + ", element=" + element + "]";
	}

}
