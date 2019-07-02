package common;

public class Drink extends BasicFood {

    boolean hasDeposit;

    public enum TYPE{
        SODA,
        TEA,
        WATER,
        OTHER;
    }

    TYPE type;

    public Drink(String name, String dplName, double price){
        super(name, dplName, price);
    }

}
