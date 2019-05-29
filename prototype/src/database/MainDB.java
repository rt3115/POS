package database;

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
    private FileWriter write;
    PrintWriter print_line;
    private Scanner in;

    public MainDB(){
        //starts the DB by loading the file and calling the sort methods
        //also starts the thread that saves the dataBase
        try {
            write= new FileWriter("files/items",true);
            print_line = new PrintWriter( write );
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private void forceSave(){
        //forces a save
    }

    private void forceLoad(){
        //forces a load
    }

    private void load(){
        //reads in the file
        //while()

        in = new Scanner("files/items.txt");
        String [] line;
        while (in.hasNext()) {
            line = in.next().split(",");
            //create new object line[0]
            if(line[3]=="BasicFood"){
//                BasicFood food= new BasicFood("common?");//line[0]
//                foods.add(food);
//                items.add(food);
            }else if(line[3]=="Topping"){
//                Topping topping= new Topping("common?");
//                toppings.add(topping);
//                items.add(topping);
            }
            //Item item= new
        }
    }

    private void save(){
        //saves the main list to the file
        //print_line.printf("%s+%n", items);
        for(int i=0; i> items.size()-1; i++){
            print_line.printf("%s+%n", items.get(i));//print the actual line.
        }
        print_line.close();
    }

    private void sortToppings(){
        //puts the toppings objects into their list
    }

    private void sortFoods(){
        //puts the foods into their list
    }

    public Item getItem(int id){
        //returns a item given the id
        //not sure how important this method will be

        return null;
    }

    public void editItem(Item edit){
        //makes an edit to a item
    }

    @Override
    public void run() {

    }

    //you can make a edit food and edit topping that editItem can call if you want to



}
//list of foods, can be anything list array list costom?
//list of toppings list of sides. sides and toppings are same object
//grab list of everything.
//how store? may only need one class
//add food, add topping. toppings vs sides.
//needs to be able to editItem- one function has to check one list.

//list food, topping, side, everything getlists
//addItem/Addfoodtopping- just adds to list
//editeItem/Editfoodtopping
//constructor, can have start
//start read in file database
//periodically save
//load, reload by request. changes made outside of program, csv-c  can open files in excel