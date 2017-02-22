package unlp.rastrosoft.web.model;
public class ExecuteCriteria extends SearchCriteria {

        String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
        
	@Override
	public String toString() {
		return "SearchCriteria [device=" + device + ", property=" + property + ", element=" + element + ", value=" + value + "]";
	}

}
