package unlp.rastrosoft.web.model;
public class ExecuteCriteriaThreeValues extends ExecuteCriteriaTwoValues {

        String value3;

        public String getValue3() {
            return value3;
        }

        public void setValue3(String value3) {
            this.value3 = value3;
        }
        
	@Override
	public String toString() {
		return "SearchCriteria [device=" + device + ", property=" + property + ", element=" + element + ", value=" + value + ", value2=" + value2 + ", value3=" + value3 + "]";
	}

}
