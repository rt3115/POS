package common;

public abstract class Item {

    private final String name;
    private final String description;
    private final int id;
    public static int CURRID = 0;

    public abstract int getPrice();

    public Item(String name){
        this.name = name;
        this.id = Item.CURRID++;
        description = "";
    }

    public Item(String name, String description){
        this.name = name;
        this.description = description;
        this.id = Item.CURRID++;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Item){
            if(((Item) obj).getId() == this.id || ((Item) obj).getName().equals(this.name)){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
}
