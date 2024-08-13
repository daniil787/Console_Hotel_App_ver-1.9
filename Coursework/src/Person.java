import java.io.*;
public abstract class Person implements Serializable {
        private String fullName;
    private String phoneNumber;
        private String address;
public Person(String fullName, String phoneNumber, String address)
    {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    public String getFullName() {
        return fullName;
    }

    public String getAddress() { return address;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setFullName(String fullName){
        this.fullName = fullName;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
}
