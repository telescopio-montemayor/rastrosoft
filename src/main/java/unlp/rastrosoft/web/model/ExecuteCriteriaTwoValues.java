package unlp.rastrosoft.web.model;
public class ExecuteCriteriaTwoValues extends ExecuteCriteria {

        String value2;

        public String getValue2() {
            return value2;
        }

        public void setValue2(String value2) {
            this.value2 = value2;
        }
        
	@Override
	public String toString() {
		return "SearchCriteria [device=" + device + ", property=" + property + ", element=" + element + ", value=" + value + ", value2=" + value2 + "]";
	}

}
