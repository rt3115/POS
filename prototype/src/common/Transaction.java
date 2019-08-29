package common;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Transaction {

    public int change;
    public int entered;
    public int tax;
    public int id;
    public static int CURRID = 0;
    public Date date;
    private boolean isVoid = false;

    public enum PAYMENT_TYPE {
        CREDIT,
        CASH;
    }

    class PAYMENT {
        PAYMENT_TYPE type;
        double value;
        public PAYMENT(PAYMENT_TYPE type, double value){
            this.type = type;
            this.value = value;
        }
    }

    private List<PAYMENT> payments = new LinkedList<>();

    public List<Item> list = new LinkedList();

    public Transaction(){
        id = CURRID++;
        Calendar cal = Calendar.getInstance();
        date = cal.getTime();
    }

    public Item getLast(){
        if(list.size() == 0){
            return null;
        }
        return list.get(list.size()-1);
    }

    public Item ItemGetIndex(int index){
        return list.get(index);
    }

    public boolean cashOut(int ent, PAYMENT_TYPE type){

        //if 0 was entered then exact change was given
        if(ent == 0){
            entered += getTotal() - entered; //this prevents weird things from happening when, you entered a partial amount and then exact change
            change = 0;
            payments.add(new PAYMENT(type,entered));
            return true;
        }
        entered += ent;
        payments.add(new PAYMENT(type,ent));
         if(getTotal() - entered <= 0){
             change = entered - getTotal();
             return true;
         }
         return false;
    }

    public int getTotal(){
        int temp = 0;
        for(Item item : list){
            temp += item.getPrice();
        }
        return temp;
    }

    public String getTotalString(){
        String temp = "";
        if(payments.size() == 0)
            temp = "Amt Owed: " + getTotal()/100.00;
        else{
            temp = "Amt Owed: " + (getTotal() - entered)/100.00;
        }
        return temp;
    }

    public int getTax(){
        int temp = 0;
        for(Item item : list){
            temp += item.getTax();
        }
        return temp;
    }

    public boolean removeLast(){
        if(list.size() == 0)
            return false;
        list.remove(list.size()-1);
        return true;
    }

    public void voidTrans(){
        this.isVoid = true;
    }

    public boolean isVoid() {
        return isVoid;
    }

    @Override
    public String toString() {
        //the short hand string
        return id + "  -  " + getTotal()/100.00 + "  -  " + date.getHours() +":"+date.getMinutes();
    }

    public String saveString(){
        //returns the String to be saved in the transaction file
        String temp = "" + id + "," + date.toString() + "," + isVoid;


        return temp;
    }

    public String descString(){
        String temp  = id + " - " + date.toString();

        for(Item item : list){
            temp += '\n';
            temp += "\t\t" + item.toString();
        }
        temp += '\n' + "Total: " + getTotal()/100.00 + '\n' + "Entered: " + entered/100.00 + '\n' + "Change: " + change/100.00;
        return temp;
    }

    public String getReceiptString(){
        String temp = "";

        for(Item item : list){
            temp += "\n" + item.toString();
        }

        temp += '\n' + "Total: " + getTotal()/100.00 + "\n Tax: " + getTax()/100.00 + "\n Entered: " + entered/100.00 + "\n Change: " + change/100.00;

        return temp;
    }
}
