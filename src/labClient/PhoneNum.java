package labClient;

public class PhoneNum {
    private int areaCode;
    private String localNum;

    public PhoneNum(int newAreaCode, String newLocalNum) {
        areaCode = newAreaCode;
        localNum = newLocalNum;
    }
    
    //my changes
    @Override
    public boolean equals(Object otherObj) {
    	PhoneNum otherPhoneNum;
    	
    	if(!(otherObj instanceof PhoneNum))
    		return false;
    	otherPhoneNum =(PhoneNum) otherObj;
    	if(this.areaCode == otherPhoneNum.areaCode &&
    				this.localNum.equals(otherPhoneNum.localNum))
    		return true;
    	else
    	return false;
    }

    public String toString() {
        return "(" + areaCode + ")" + localNum;
    }
    
    
    @Override
    public int hashCode() {
    	int code = areaCode*31;
    	
    	code=localNum.hashCode()*31;
    	return code;
    }
}