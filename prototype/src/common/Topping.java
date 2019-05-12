package common;

public class Topping extends Item {
    private int price;
    private int extraPrice;
    private int amount; //0 is normal, -1 LIGHT, 1 EXTRA, -2 is no topping

    public Topping(String name, double price){
        super(name);
        this.price = (int)(price*100);
        this.extraPrice = 0;
    }

    public Topping(String name, double price, double extraPrice){
        this(name, price);
        this.extraPrice = (int)(extraPrice*100);
    }

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getExtraPrice() {
        return extraPrice;
    }

    @Override
    public String toString() {
        if(amount == 1){
            return "EXTRA " + super.getName() + "   " + getExtraPrice();
        }else if(amount == -1){
            return "LIGHT " + super.getName() + "   " + getPrice();
        }else if(amount == -2){
            return "NO " + super.getName();
        }
        return super.getName() + "      " + getPrice();
    }
}
