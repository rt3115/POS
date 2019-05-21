package common;

import java.util.LinkedList;
import java.util.List;

public class AdjustableFood extends BasicFood {

    private List<Item> toppings = new LinkedList<>();
    private List<Item> normalToppings = new LinkedList<>();

    public AdjustableFood(String name, String dplName,  double price, Item ... top){
        super(name, dplName,price/100);
        for(Item t : top){
            normalToppings.add(t);
        }
    }

    public AdjustableFood(String name, String dplName, double price, List<Item> top){
        super(name, dplName,price/100);
        normalToppings.addAll(top);
    }

    public AdjustableFood(AdjustableFood copy){
        super(copy.getName(), copy.getDplName(), copy.getPrice());
        normalToppings = copy.getNormalToppings();
        toppings = new LinkedList<>();
    }

    public void addTopping(Topping top){
        if(toppings.contains(top)){
            int temp = toppings.indexOf(top);
            Topping topTemp = (Topping)toppings.get(temp);
            if((topTemp.getAmount()!= Topping.AMOUNT.NO && top.getAmount() == Topping.AMOUNT.NO) ||
                    (top.getAmount() != Topping.AMOUNT.NO && topTemp.getAmount() == Topping.AMOUNT.NO)){
                toppings.remove(top);
            }
            return;
        }
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
        temp += "\n";
        for(Item top : toppings){
            top = (Topping)top;
            temp += "      " + top.toString() + "\n";
        }
        temp += "SubTotal: " + getPrice()/100.00;
        return temp;
    }
}
