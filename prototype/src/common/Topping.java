package common;

import java.text.DecimalFormat;

public class Topping extends Item {
    private int price;
    private int extraPrice;
    private int sidePrice;
    private AMOUNT amount;
    private boolean isTopping;
    private boolean isSide;


    public enum AMOUNT {
        NO,
        NORMAL,
        LIGHT,
        EXTRA,
        SIDE,
        OPTIONAL;
    }


    public Topping(String name, String dplName, double price){
        super(name, dplName);
        this.price = (int)(price*100);
        this.extraPrice = 0;
        this.sidePrice = 0;
    }

    public Topping(String name, String dplName, double price, double extraPrice){
        this(name, dplName, price);
        this.extraPrice = (int)(extraPrice*100);
    }

    public Topping(String name, String dplName, double price, double extraPrice, double sidePrice){
        this(name, dplName, price, extraPrice);
        this.sidePrice = (int)(sidePrice*100);
    }


    public Topping(Topping copy){
        this(copy.getName(), copy.getDplName(), copy.getPrice(), copy.getExtraPrice());
    }

    public int getPrice() {
        if(amount.equals(AMOUNT.EXTRA))
            return extraPrice;
        if(amount.equals(AMOUNT.OPTIONAL))
            return 0;
        return price;
    }

    public void setPrice(double price) {
        this.price = (int) (price * 100);
    }

    public void setExtraPrice(double extraPrice){
        this.price = (int)(extraPrice*100);
    }

    public void setSidePrice(double sidePrice){
        this.price = (int)(sidePrice*100);
    }

    public int getSidePrice(){
        return sidePrice;
    }

    public void setSide(boolean isSide){
        this.isSide = isSide;
    }

    public void setTopping(boolean isTopping){
        this.isTopping = isTopping;
    }

    public AMOUNT getAmount() {
        return amount;
    }

    public boolean isTopping(){
        return isTopping;
    }

    public boolean isSide(){
        return isSide;
    }

    public void setAmount(AMOUNT amount) {
        this.amount = amount;
    }

    public int getExtraPrice() {
        return extraPrice;
    }

    public int getTax(){
        return 0;
    }

    public String saveLine(){return super.getName()+","+super.getDplName()+","+getPrice();}

    String pattern = "##0.00";
    DecimalFormat dF = new DecimalFormat(pattern);

    @Override
    public String toString() {
        if(amount == AMOUNT.EXTRA){
            if(price != 0)
                return "EXTRA " + super.toString() + "   " + dF.format(getExtraPrice()/100.00);
            else
                return "EXTRA " + super.toString();
        }else if(amount == AMOUNT.LIGHT){
            if(price != 0)
                return "LIGHT " + super.toString()+ "   " + dF.format(getPrice()/100.00);
            else
                return "LIGHT " + super.toString();
        }else if(amount == AMOUNT.NO){
            return "NO " + super.toString();
        }else if(amount == AMOUNT.SIDE){
            if(price != 0){
                return "SIDE " + super.toString() + "   " +dF.format(getPrice()/100.00);
            }else {
                return "SIDE " + super.toString();
            }
        }else if(amount == AMOUNT.OPTIONAL){
            return super.toString();
        }
        return super.toString() + "      " + dF.format(getPrice()/100.00);
    }
}
