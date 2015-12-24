package labClient;

public class Name {
    private String lastName;
    private String firstName;

    public Name(String lName, String fName) {
        lastName = lName;
        firstName = fName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }


    public String toString() {
        return lastName + ", " + firstName;
    }
    
    @Override 
    public boolean equals(Object otherName) {
    	Name tempName;
    	if(!(otherName instanceof Name)) {
    		return false;
    }
    	
    	tempName =(Name) otherName;
    	
    	if(this.getFirstName().equals(tempName.getFirstName())
    			&& this.getLastName().equals(tempName.getLastName())) {
    	return true;
    	}
    	else {
    	return false;
    	}
    }
    
    @Override
    public int hashCode() {
    	int code=0;
    	
    	code+= lastName.hashCode() *31;
    	code+=firstName.hashCode()*31;
    	
    	return code;
    }
}