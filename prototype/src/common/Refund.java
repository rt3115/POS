package common;

public class Refund extends Transaction{

    public Refund(){
        super();
    }

    @Override
    public boolean cashOut(int ent, PAYMENT_TYPE type){
        return true;
    }

    @Override
    public int getTax(){
        return super.getTax() * -1;
    }

    @Override
    public int getTotal(){
        return super.getTotal() * -1;
    }

    @Override
    public String descString(){
        String temp = "\nREFUND\n";
        temp += super.descString();
        return temp;
    }

    @Override
    public String toString(){
        String temp = "REFUND: " + super.toString();
        return temp;
    }

    @Override
    public String getReceiptString(){
        String temp = super.getReceiptString();
        temp += "\n\n---REFUND---\n\n";
        return temp;
    }

}
