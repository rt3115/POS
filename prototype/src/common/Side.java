package common;

public class Side extends Item {

    private int price;

    public Side(String name, String dplName, double price){
        super(name, dplName);
        this.price = (int)price*100;
    }

    public String saveLine(){
        return(super.getName()+","+super.getDplName()+","+price);
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Side of " + super.toString() + "    " + getPrice()/100.00;
    }
}
