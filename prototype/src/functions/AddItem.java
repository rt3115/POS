package functions;

public class AddItem extends Function {

    //function to add items to the database

    public AddItem(){
        super("addItem", AccessLevel.MANAGER);
    }

    public AddItem(AccessLevel accessLevel){
        super("addItem", accessLevel);
    }

}
