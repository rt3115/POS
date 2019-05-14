package functions;

public abstract class Function {

    private AccessLevel accessLevel;
    private String name;

    public String getName(){
        return name;
    }

    public AccessLevel getAccessLevel(){
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel){
        this.accessLevel = accessLevel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Function(String name, AccessLevel accessLevel){
        this.name = name;
        this.accessLevel = accessLevel;
    }


    public abstract void execute();

}
