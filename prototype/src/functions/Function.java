package functions;

public abstract class Function {


    private String name;

    public String getName(){
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }

    public Function(String name){
        this.name = name;
    }
}
