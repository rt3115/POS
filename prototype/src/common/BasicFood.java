package common;

import java.util.LinkedList;
import java.util.List;

public class BasicFood extends Item {
    private final int price;
    private int qty;

    public BasicFood(String name, double price){
        super(name);
        this.price = (int)(price * 100);
        this.qty = 1;
    }

    public BasicFood(BasicFood copy){
        super(copy.getName());
        this.price = copy.getPrice();
        this.qty = 1;
    }

    public int getPrice() {
        return price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString(){
        return super.toString() + "    " + qty + "     " + (price/100.00);
    }
}
