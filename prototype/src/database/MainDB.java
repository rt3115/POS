package database;


import common.*;
import gui.GUIEditItems;
import gui.GUIItemView;
import javafx.scene.layout.Pane;

import common.BasicFood;
import common.Item;
import common.Topping;
import main.Main;
import main.Register;


import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MainDB implements Runnable{

    private List<Item> items = new LinkedList<>(); //load all the items into this list from the file and save this file
    //toppings and foods are only there for easy of use
    private List<Topping> toppings = new LinkedList<>();
    private List<BasicFood> foods = new LinkedList<>();
    private List<Side> sides = new LinkedList<>();


    public GUIItemView guiItemView;
    public GUIEditItems guiEditItems;

    public Observer<Register> observer;

    public void addObserver(Observer<Register> observer){
        this.observer = observer;
    }

    public void alertObservers(){
        //guiItemView.update(this);
//        guiEditItems.update(this);
        observer.update(null);
    }

    private String fileName = "C:/Users/Public/POS/items"; //files are located in the public user folder under POS

    public MainDB(){
        //starts the DB by loading the file and calling the sort methods
        //also starts the thread that saves the dataBase
        load();
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public Topping getTopping(String name){
        for(Topping topping : toppings){
            if(topping.getName().equals(name)){
                return topping;
            }
        }
        return null;
    }

    public List<BasicFood> getFoods() {
        return foods;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Side> getSides() { return sides; }

    private void load(){
        //actually trying to load the file
        try {
            Scanner in = new Scanner(new File(fileName));

            //0 - name
            //1 - dplName
            //2 - Type
            while(in.hasNext()){
                String line = in.nextLine();
                String[] words = line.split(",");

                if(words[2].equals("BASIC_FOOD")){
                    BasicFood temp = new BasicFood(words[0], words[1], Double.parseDouble(words[3]));
                    temp.setDeposit(Boolean.parseBoolean(words[5]));
                    temp.setTaxable(Boolean.parseBoolean(words[4]));
                    items.add(temp);
                }else if(words[2].equals("ADJUSTABLE_FOOD")){
                    int x = words.length - 5;
                    List<Item> toppings = new LinkedList<>();
                    for(int i = 0; i < x; i ++){
                        toppings.add(getTopping(words[i + 5]));
                    }
                    AdjustableFood temp;
                    if(toppings.size() == 0) {
                        temp = new AdjustableFood(words[0], words[1], Double.parseDouble(words[3]));
                        //System.err.println(Double.parseDouble(words[3]));
                    }else{
                        temp = new AdjustableFood(words[0], words[1], Double.parseDouble(words[3]),toppings);
                    }
                    temp.setTaxable(Boolean.parseBoolean(words[4]));
                    items.add(temp);
                }else if(words[2].equals("TOPPING")){
                    Topping temp = new Topping(words[0], words[1], Double.parseDouble(words[3]), Double.parseDouble(words[4]));
                    toppings.add(temp);
                }else if(words[2].equals("SIDE")){
                    Side temp = new Side(words[0], words[1], Double.parseDouble(words[3]));
                    sides.add(temp);
                }else{
                    System.err.println("MainDB, line is not formatted correctly + \n" + line);
                }

            }

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    private void save(){
        //saves the main list to the file
        //print_line.printf("%s+%n", items);
        try {
            FileWriter fileWriter = new FileWriter(fileName);



        } catch (IOException e){
            e.printStackTrace();
        }
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
        //makes an edit to an item

        alertObservers();
    }

    public void addItem(int id, Item item){
        //adds a new item to the lists
        //items.add(item);
        if(item instanceof AdjustableFood){
            foods.add((BasicFood)item);
        }else if(item instanceof Topping){
            toppings.add((Topping)item);
        }else if(item instanceof Side){
            sides.add((Side)item);
        }else if(item instanceof BasicFood){
            foods.add((BasicFood)item);
            System.err.println("Added item: " + item.toString());
        }else{
            System.err.println("This was not supposed to happen ERROR in if statement: MainDB AddItem()");
        }

        alertObservers();
    }

    public boolean removeItem(Item item){
        //if an item with a matching name (or id) is in the list then remove the item
        //returns true if the item was removed, false otherwise
        if(item instanceof BasicFood) {
            if (foods.contains((BasicFood)item)) {
                foods.remove((BasicFood)item);
                System.err.println("Removed: " + item.toString());
                return true;
            }
        }
        if(sides.contains(item)){
            sides.remove(item);
            return true;
        }
        if(toppings.contains(item)){
            toppings.remove(item);
            return true;
        }

        alertObservers();
        return false;
    }

    @Override
    public void run() {

    }

    //you can make a edit food and edit topping that editItem can call if you want to



}