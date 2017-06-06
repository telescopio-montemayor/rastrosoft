package unlp.rastrosoft.web.model;
public class ExecuteCriteriaFourValues extends ExecuteCriteriaThreeValues {

        String value4;

        public String getValue4() {
            return value4;
        }

        public void setValue4(String value4) {
            this.value4 = value4;
        }
        
	@Override
	public String toString() {
		return "SearchCriteria [device=" + device + ", property=" + property + ", element=" + element + ", value=" + value + ", value2=" + value2 + ", value3=" + value3 + ", value4=" + value4 + "]";
	}

}
