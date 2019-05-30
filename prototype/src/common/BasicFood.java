package common;

import javafx.scene.layout.Pane;

import java.util.LinkedList;
import java.util.List;

public class BasicFood extends Item {
    private final int price;
    private int qty;
    private List<Side> sides = new LinkedList<>();

    public BasicFood(String name, String dplName, double price){
        super(name, dplName);
        this.price = (int)(price * 100);
        this.qty = 1;
    }

    public BasicFood(BasicFood copy){
        super(copy.getName(), copy.getDplName());
        this.price = copy.getPrice();
        this.qty = 1;
    }

    public List<Side> getSides() {
        return sides;
    }

    public void addSide(Side top){
        sides.add(top);
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

    public String saveLine(){return super.getName()+","+super.getDplName()+","+getPrice();}

    @Override
    public String toString(){
        if(sides.size() == 0) {
            return super.toString() + "    " + qty + "     " + (price / 100.00);
        }else{
            String temp = super.toString() + "    " + qty + "      " + (price/100.00);
            temp += "\n";
            for(Item top : sides){
                top = (Topping)top;
                temp += "      " + top.toString() + "\n";
            }
            temp += "SubTotal: " + getPrice()/100.00;
            return temp;
        }
    }
}
