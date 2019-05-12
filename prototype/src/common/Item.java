package common;

public abstract class Item {
    private final String name;
    private final int id;
    public static int CURRID = 0;

    public abstract int getPrice();

    public Item(String name){
        this.name = name;
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
        return id + " " + name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Item){
            if(((Item) obj).getId() == this.id){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
}
