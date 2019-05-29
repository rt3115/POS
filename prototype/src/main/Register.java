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
    public List<Side> sides = new LinkedList<>();

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

        toppings.add(new Topping("HomeFries", "HomeFries", .00));
        toppings.add(new Topping("Mac Salad","Mac Salad", .00));
        toppings.add(new Topping("Hot Sauce", "Hot Sauce", .00, .50));
        toppings.add(new Topping("Lettuce" , "Lettuce",  .00));
        toppings.add(new Topping("Tomato", "Tomato", .00));
        toppings.add(new Topping("Onions", "Onions",.00));
        toppings.add(new Topping("Cheese", "Cheese",.00, .50));

        //make debug list
        foods.add(new BasicFood("Pizza", "Pizza", 2.5));
        foods.add(new BasicFood("Candy", "Candy",1.00));
        foods.add(new BasicFood("Ice Cream", "Ice Cream",5.00));
        foods.add(new AdjustableFood("Plate", "Plate", 10.00, new Topping("HomeFries", "HomeFries", .00), new Topping("Mac Salad", "Mac Salad", 00), new Topping("Hot Sauce", "Hot Sauce",00)));
//        foods.add(new AdjustableFood("Sub", 8.00));

        sides.add(new Side("Home Fries", "Home Fries", 2.00));
        sides.add(new Side("Mac Salad", "Mac Salad", 1.50));
        sides.add(new Side("Fries", "Fries", 3.00));
    }



    private boolean transDone = false;

    public void cashout(int ent){
        transDone = transaction.cashOut(ent);
    }

    public void addTopping(Item newItem){
        //need to do this, register should handle adding a topping not the gui
        AdjustableFood temp = (AdjustableFood)getLast();
        temp.addTopping((Topping)newItem);
    }

    public void addSide(Item newItem){
        //if no item is selected then the item is added right to the list
        transaction.list.add(newItem);
    }

    public void addSide(Item newItem, Item selected){
        //if an item is selected then it will add the side to that item
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
        if(transaction.removeLast())
            return true;
        return false;
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
