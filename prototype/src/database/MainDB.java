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

    private void load(){
        //reads in the file
        //while()

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