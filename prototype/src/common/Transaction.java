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

    public boolean cashOut(int ent){
        if(ent == 0){
            entered = getTotal();
            change = 0;
            return true;
        }
        entered += ent;
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

    @Override
    public String toString() {
        return id + "  -  " + getTotal()/100.00 + "  -  " + date.getHours() +":"+date.getMinutes();
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
}
