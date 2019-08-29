package common;

public class Drink extends BasicFood {

    private boolean hasDeposit;

    public boolean isHasDeposit() {
        return hasDeposit;
    }

    public void setHasDeposit(boolean hasDeposit) {
        this.hasDeposit = hasDeposit;
    }

    public Drink(String name, String dplName, double price, boolean hasDeposit){
        super(name, dplName, price, hasDeposit);
    }

}
