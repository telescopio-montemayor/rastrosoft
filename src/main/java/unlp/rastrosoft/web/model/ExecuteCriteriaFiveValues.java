package unlp.rastrosoft.web.model;
public class ExecuteCriteriaFiveValues extends ExecuteCriteriaFourValues {

        String value5;

        public String getValue5() {
            return value5;
        }

        public void setValue5(String value5) {
            this.value5 = value5;
        }
        
	@Override
	public String toString() {
		return "SearchCriteria [device=" + device + ", property=" + property + ", element=" + element + ", value=" + value + ", value2=" + value2 + ", value3=" + value3 + ", value4=" + value4 + ", value5=" + value5 + "]";
	}

}
