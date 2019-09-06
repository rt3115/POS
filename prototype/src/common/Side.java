package common;

import main.Main;

import java.text.DecimalFormat;

public class Side extends Item {

    private int price;
    private int tax;

    public Side(String name, String dplName, double price){
        super(name, dplName);
        this.price = (int)price*100;
        tax = (int)(price * Main.values.TAX_RATE);
    }

    public String saveLine(){
        return(super.getName()+","+super.getDplName()+","+price);
    }

    @Override
    public int getPrice() {
        if(super.isTaxable()){
            if(super.isTaxIsPartOfPrice()){
                return price;
            }
            return price + tax;
        }
        return price;
    }

    public void setPrice(double price) {this.price = (int)(price*100); }

    public int getTax(){
        return tax;
    }

    String pattern = "##0.00";
    DecimalFormat dF = new DecimalFormat(pattern);

    @Override
    public String toString() {
        return "Side of " + super.toString() + "    " + dF.format(getPrice()/100.00);
    }
}
