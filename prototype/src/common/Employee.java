package common;

import functions.AccessLevel;
import functions.Function;

public class Employee {
    private final String name;
    private final String logIn; //this is really insecure but I dont care
    private AccessLevel accessLevel;

    public Employee(String name, String logIn, AccessLevel accessLevel){
        this.logIn = logIn;
        this.name = name;
        this.accessLevel = accessLevel;
    }

    public AccessLevel getAccessLevel(){
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getName() {
        return name;
    }

    public boolean isLogValid(String logIn){
        return this.logIn.equals(logIn);
    }
}
