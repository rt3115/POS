package common;

import java.util.LinkedList;
import java.util.List;

public class AdjustableFood extends BasicFood {

    private List<Item> toppings = new LinkedList<>();
    private List<Item> normalToppings = new LinkedList<>();

    public AdjustableFood(String name, int id, double price, Item ... top){
        super(name, id, price);
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
}
