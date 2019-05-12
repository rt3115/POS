package gui;

public class TempFood {

    private int id;
    private String name;
    private double price;

    private boolean hasTopping;
    private boolean isTopping;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean isHasTopping() {
        return hasTopping;
    }

    public boolean isTopping() {
        return isTopping;
    }

    public TempFood(String name, int ID, double price){
        this.name = name;
        this.id = ID;
        this.price = price;
        hasTopping = false;
        isTopping = false;
    }

    public TempFood(String name, int ID, double price, boolean isTopping, boolean hasTopping){
        this(name, ID, price);
        this.hasTopping = hasTopping;
        this.isTopping = isTopping;
    }

}
