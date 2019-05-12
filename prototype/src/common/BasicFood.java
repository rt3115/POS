package common;

public class BasicFood extends Item {
    private final int price;
    private int qty;

    public BasicFood(String name, int id, double price){
        super(name, id);
        this.price = (int)(price * 100);
        this.qty = 1;
    }

    public int getPrice() {
        return price;
    }

    public int getQty() {
        return qty;
    }

    @Override
    public String toString(){
        return super.toString() + "    " + qty + "     " + (price/100.00);
    }
}
