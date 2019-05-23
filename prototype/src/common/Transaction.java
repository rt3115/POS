package common;

import java.util.LinkedList;
import java.util.List;

public class Transaction {

    public int change;
    public int entered;
    public int id;
    public static int CURRID = 0;

    public List<Item> list = new LinkedList();

    public Transaction(){
        id = CURRID++;
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

    public boolean removeLast(){
        if(list.size() == 0)
            return false;
        list.remove(list.size()-1);
        return true;
    }


}
