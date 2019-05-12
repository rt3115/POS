package common;

import java.util.LinkedList;
import java.util.List;

public class AdjustableFood extends BasicFood {

    private List<Item> toppings = new LinkedList<>();
    private List<Item> normalToppings = new LinkedList<>();

    public AdjustableFood(String name, double price, Item ... top){
        super(name, price);
        for(Item t : top){
            normalToppings.add(t);
        }
    }

    public void addTopping(Item top){
        toppings.add(top);
    }

    public void removeTopping(Item top){
        toppings.remove(top);
    }

    public List<Item> getToppings() {
        return toppings;
    }

    public List<Item> getNormalToppings() {
        return normalToppings;
    }

    @Override
    public int getPrice() {
        int temp = super.getPrice();
        for(Item top : toppings){
            temp += top.getPrice();
        }
        return temp;
    }

    @Override
    public String toString() {
        if(toppings.size() == 0){
            return super.toString();
        }
        String temp = super.toString();
        temp += "/n";
        for(Item top : toppings){
            top = (Topping)top;
            temp += "   " + top.toString() + "\n";
        }
        temp = "SubTotal: " + getPrice();
        return temp;
    }
}
