package common;

public abstract class Item {

    private final String name;
    private final String dplName;
    private final String description;
    private int overidePrice = 0;
    private final int id;
    public static int CURRID = 0;

    public abstract int getPrice();

    public Item(String name, String dplName){
        this.name = name;
        this.dplName = dplName;
        this.id = Item.CURRID++;
        description = "";
        System.err.println("*"+CURRID+"*");
    }

    public Item(String name, String dplName, String description){
        this.name = name;
        this.dplName = dplName;
        this.description = description;
        this.id = Item.CURRID++;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setOveridePrice(int overidePrice){
        this.overidePrice = overidePrice;
    }

    public int getOveridePrice() {
        return overidePrice;
    }

    public String getDplName(){
        if(name.length() > 15)
            return dplName;
        return name;
    }

    @Override
    public String toString() {
        if(name.length() > 15){
            return dplName;
        }
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
