package common;

import javafx.scene.layout.Pane;
import main.Main;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

public class BasicFood extends Item {
    private int price;
    private int qty;
    private int tax;
    private boolean isDeposit; //for drink only
    private List<Side> sides = new LinkedList<>();

    public BasicFood(String name, String dplName, double price){
        super(name, dplName);
        this.price = (int)(price * 100);
        this.qty = 1;
        tax = (int)((price * Main.values.TAX_RATE)*100);
    }

    public BasicFood(String name, String dplName, double price, boolean hasDeposit){
        this(name, dplName, price);
        this.isDeposit = hasDeposit;
    }

    public BasicFood(String name, String dplName, double price, boolean hasDeposit, boolean hasTax){
        this(name, dplName, price, hasDeposit);
        setTaxable(hasTax);
    }

    public BasicFood(BasicFood copy){
        super(copy.getName(), copy.getDplName());
        this.price = copy.getPrice();
        this.qty = 1;
        tax = (int)(price * Main.values.TAX_RATE);
    }

    public List<Side> getSides() {
        return sides;
    }

    public void addSide(Side top){
        sides.add(top);
    }

    public int getPrice() {
        if(super.isTaxable()){
            if(super.isTaxIsPartOfPrice()){
                return price;
            }
            return price + tax;
        }
        return price;
    }

    public boolean isDeposit() {
        return isDeposit;
    }

    public void setDeposit(boolean deposit) {
        isDeposit = deposit;
    }

    public void setPrice(double price){
        this.price = (int)(price*100);
    }

    public int getTax() {
        if(isTaxable())
            return tax;
        return 0;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String saveLine(){
        return super.getName()+","+super.getDplName()+",BASIC_FOOD,"+getPrice() +"," + getTax() + "," + isDeposit;
    }

    String pattern = "##0.00";
    DecimalFormat dF = new DecimalFormat(pattern);

    @Override
    public String toString(){
        if(sides.size() == 0) {
            return super.toString() + "         " + dF.format(price / 100.00);
        }else{
            String temp = super.toString() + "          " + dF.format(price/100.00);
            temp += "\n";
            for(Item top : sides){
                top = (Topping)top;
                temp += "      " + top.toString() + "\n";
            }
            temp += "SubTotal: " + dF.format(getPrice()/100.00);
            return temp;
        }
    }
}
