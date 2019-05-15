package main;

import common.BasicFood;
import common.Item;
import common.Topping;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Register {
    //create layout?
    //make the classes
    //constructor

    //debug lists
    public List<Topping> toppings = new LinkedList<>(); //fill this list with toppings
    public List<BasicFood> foods = new LinkedList<>(); //fill this list with foods

    public List<Item> list = new LinkedList<>();

    public Register(){
        //make debug list
    }


    public void cashout(){

    }

    public void addTopping(Item newItem){
        //go back change food
        //don do
        //return new copy of itslef
    }

    public void addFood(Item item){
        list.add(item);
        //food also has changable food
    }

    public boolean removeIndex(int index){
        if(list.size()==0){
            return false;
        }
        list.remove(index);
        return true;
    } //48sec

    public boolean removeLast() {
        if (list.size()==0){
            return false;
        }
        list.remove(list.size()-1);
        return true;
    }

    public Item getIndex(int index){
        return list.get(index);
    }

    public Item getLast(){
        return list.get(list.size()-1);
    }

    public int getTotal(){
        int total=0;
        for(int i=0;i<list.size()-1;i++){
            total+= list.get(i).getPrice();
        }
        return total;
    }
    //getprice, total price
}
