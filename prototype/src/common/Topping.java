package common;

public class Topping extends Item {
    private int price;
    private int extraPrice;
    private AMOUNT amount;
    private boolean isTopping;
    private boolean isSide;

    public enum AMOUNT {
        NO,
        NORMAL,
        LIGHT,
        EXTRA;
    }


    public Topping(String name, double price){
        super(name);
        this.price = (int)(price*100);
        this.extraPrice = 0;
        this.isTopping = true;
        this.isSide = false;
    }

    public Topping(String name, double price, double extraPrice){
        this(name, price);
        this.extraPrice = (int)(extraPrice*100);
    }

    public Topping(String name, double price, boolean isTopping, boolean isSide){
        this(name, price);
        this.extraPrice = 0;
        this.isSide = isSide;
        this.isTopping = isTopping;
    }

    public Topping(String name, double price, double extraPrice, boolean isTopping, boolean isSide){
        this(name, price, extraPrice);
        this.isTopping = isTopping;
        this.isSide = isSide;
    }

    public Topping(Topping copy){
        this(copy.getName(), copy.getPrice(), copy.getExtraPrice());
    }

    public int getPrice() {
        return price;
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

    @Override
    public String toString() {
        if(amount == AMOUNT.EXTRA){
            return "EXTRA " + super.getName() + "   " + getExtraPrice();
        }else if(amount == AMOUNT.LIGHT){
            return "LIGHT " + super.getName() + "   " + getPrice();
        }else if(amount == AMOUNT.NO){
            return "NO " + super.getName();
        }
        return super.getName() + "      " + getPrice()/100.00;
    }
}
