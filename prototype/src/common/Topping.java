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
        EXTRA,
        SIDE;
    }


    public Topping(String name, String dplName, double price){
        super(name, dplName);
        this.price = (int)(price*100);
        this.extraPrice = 0;
        this.isTopping = true;
        this.isSide = false;
    }

    public Topping(String name, String dplName, double price, double extraPrice){
        this(name, dplName, price);
        this.extraPrice = (int)(extraPrice*100);
    }

    public Topping(String name, String dplName,  double price, boolean isTopping, boolean isSide){
        this(name, dplName, price);
        this.extraPrice = 0;
        this.isSide = isSide;
        this.isTopping = isTopping;
    }

    public Topping(String name, String dplName, double price, double extraPrice, boolean isTopping, boolean isSide){
        this(name, dplName, price, extraPrice);
        this.isTopping = isTopping;
        this.isSide = isSide;
    }

    public Topping(Topping copy){
        this(copy.getName(), copy.getDplName(), copy.getPrice(), copy.getExtraPrice());
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
            return "EXTRA " + super.toString() + "   " + getExtraPrice();
        }else if(amount == AMOUNT.LIGHT){
            return "LIGHT " + super.toString()+ "   " + getPrice()/100.00;
        }else if(amount == AMOUNT.NO){
            return "NO " + super.toString();
        }else if(amount == AMOUNT.SIDE){
            if(price != 0){
                return "SIDE " + super.toString() + "   " +getPrice()/100.00;
            }else {
                return "SIDE " + super.toString();
            }
        }
        return super.toString() + "      " + getPrice()/100.00;
    }
}
