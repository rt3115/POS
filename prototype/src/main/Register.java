package main;

import common.AdjustableFood;
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
    public List<Item> foods = new LinkedList<>(); //fill this list with foods

    public List<Item> getFoods(){
        return foods;
    }

    public List<Item> list = new LinkedList<>();

    public List<Item> getList() {
        return list;
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
        if(list.size() == 0)
            return null;
        return list.get(list.size()-1);
    }

    public int getTotal(){
        int total=0;
        for(Item item : list){
            total += item.getPrice();
        }
        return total;

    }
    //getprice, total price
}
