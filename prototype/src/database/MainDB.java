package database;


import common.*;
import javafx.scene.layout.Pane;

import common.BasicFood;
import common.Item;
import common.Topping;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MainDB implements Runnable{

    private List<Item> items = new LinkedList<>(); //load all the items into this list from the file and save this file
    //toppings and foods are only there for easy of use
    private List<Topping> toppings = new LinkedList<>();
    private List<BasicFood> foods = new LinkedList<>();
    private List<Side> sides = new LinkedList<>();
    private List<Drink> drinks = new LinkedList<>();

    private String fileName;

    public MainDB(){
        //starts the DB by loading the file and calling the sort methods
        //also starts the thread that saves the dataBase
        load();
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public List<BasicFood> getFoods() {
        return foods;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Side> getSides() { return sides; }

    private void load(){
        //temp stuff to get it all working
        toppings.add(new Topping("Lettuce", "Lettuce", 0));
        toppings.add(new Topping("Onion", "Onion", 0));
        toppings.add(new Topping("Hot Sauce", "Hot Sauce", 0));
        toppings.add(new Topping("Mac Salad", "Mac Salad", 0));
        toppings.add(new Topping("Home Fries", "Home Fries", 0));

        foods.add(new BasicFood("Ice Cream", "Ice Cream" , 5));
        foods.add(new BasicFood("Pizza", "Pizza", 2.50));
        foods.add(new BasicFood("Hot Dog", "Hot Dog" , 3));
        foods.add(new AdjustableFood("Plate", "Plate", 10.00, toppings.get(2), toppings.get(3), toppings.get(4)));
        foods.add(new AdjustableFood("HamBurger", "HamBurger", 6.00));

        drinks.add(new Drink("Water", "Water", 1));
        drinks.add(new Drink("Soda", "Soda", 2));

        items.addAll(foods);
        items.addAll(drinks);

        sides.add(new Side("Fries", "Fries", 2));
        sides.add(new Side("Home Fries", "Home Fries", 3));
    }

    private void save(){
        //saves the main list to the file
        //print_line.printf("%s+%n", items);

    }

    private void sortToppings(){
        //puts the toppings objects into their list
    }

    private void sortFoods(){
        //puts the foods into their list
    }

    private void sortSides(){

    }

    public Item getItem(int id){
        //returns a item given the id
        //not sure how important this method will be

        return null;
    }

    public void editItem(int id,Item edit){
        //makes an edit to a item
    }

    @Override
    public void run() {

    }

    //you can make a edit food and edit topping that editItem can call if you want to



}