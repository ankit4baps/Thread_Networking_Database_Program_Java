package labClient;

public class Customer {
    private Name name;
    private PhoneNum phone;

    public Customer(Name cusName, PhoneNum cusPhone) {
        name = cusName;
        phone = cusPhone;
    }

    public Name getName() {
        return name;
    }

    public PhoneNum getPhone() {
        return phone;
    }
    
    public Customer getCustomer(PhoneNum pNumber) {
    	return this;
    }

    public String toString() {
        return "Customer: " + name + "  " + phone;
    }

    public void print() {
        System.out.println(this);
    }
    

    public boolean equals(Object otherObj) {
    	Customer tempCustomer;
    	
    	if(!(otherObj instanceof Customer))
    		return false;
    	
    	tempCustomer = (Customer) otherObj;
    	
    	if(this.name.equals(tempCustomer.name) &&
    			this.phone.equals(tempCustomer.phone))
    		return true;
    	else
    		return false;
    }
    
    public int hashCode() {
    	
    	return name.hashCode()+phone.hashCode();
    }
  
}