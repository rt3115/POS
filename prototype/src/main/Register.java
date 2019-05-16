package main;

import common.*;

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
    public List<Item> foods = new LinkedList<>(); //fill this list with foods

    public List<Item> getFoods(){
        return foods;
    }

    public List<Transaction> list = new LinkedList<>(); //this is now a list of transactions (will be moved to logger at some point)
    private Transaction transaction = new Transaction(); //the current transaction

    public List<Item> getList() {
        //returns the current
        return transaction.list;
    }

    public Register(){

        toppings.add(new Topping("HomeFries", .00));
        toppings.add(new Topping("Mac Salad", .00));
        toppings.add(new Topping("Hot Sauce", .00, .50));
        toppings.add(new Topping("Lettuce" , .00));
        toppings.add(new Topping("Tomato", .00));
        toppings.add(new Topping("Onions", .00));
        toppings.add(new Topping("Cheese", .00, .50));

        //make debug list
        foods.add(new BasicFood("Pizza", 2.5));
        foods.add(new BasicFood("Candy", 1.00));
        foods.add(new BasicFood("Ice Cream", 5.00));
        foods.add(new AdjustableFood("Plate", 10.00, new Topping("HomeFries", .00), new Topping("Mac Salad", 00), new Topping("Hot Sauce", 00)));

    }



    private boolean transDone = false;

    public void cashout(int ent){
        transDone = transaction.cashOut(ent);
    }

    public void addTopping(Item newItem){
        //go back change food
        //don do
        //return new copy of itslef
    }

    public void addFood(Item item){
        if(isTransDone()){
            transDone = false;
            list.add(transaction);
            transaction = new Transaction();
        }
        transaction.list.add(item);
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
        transaction.removeLast();
        return true;
    }

    public Item getIndex(int index){
        return transaction.ItemGetIndex(index);
    }

    public Item getLast(){
        return transaction.getLast();
    }

    public void voidTransaction(){
        //this should void the transaction and make a new one
        transaction = new Transaction();
    }

    public int getTotal(){
       return transaction.getTotal();
    }

    public int getEntered(){
        return transaction.entered;
    }

    public int getChange(){
        return transaction.change;
    }

    public boolean isTransDone() {
        return transDone;
    }

}
