package common;

public abstract class Item {

    private String name;
    private String dplName;
    private String description;
    private int overidePrice = 0;
    private final int id;
    public static int CURRID = 0;
    private boolean isTaxable = false;
    private boolean taxIsPartOfPrice = true;

    public void setTaxable(boolean taxable) {
        isTaxable = taxable;
    }

    public void setTaxIsPartOfPrice(boolean taxIsPartOfPrice) {
        this.taxIsPartOfPrice = taxIsPartOfPrice;
    }

    public boolean isTaxable() {
        return isTaxable;
    }

    public boolean isTaxIsPartOfPrice() {
        return taxIsPartOfPrice;
    }

    public abstract int getPrice();
    public abstract void setPrice(double price);
    public abstract String saveLine();
    public abstract int getTax();

    public Item(String name, String dplName){
        this.name = name;
        this.dplName = dplName;
        this.id = Item.CURRID++;
        description = "";
        System.err.println("*"+CURRID+"*" + name);
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

    public void setName(String name) {this.name = name;}

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

    public String getActDplName(){
        return dplName;
    }

    public void setDplName(String name) {this.dplName = name;}

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
