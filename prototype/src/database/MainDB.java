package database;

import common.Topping;

import java.util.LinkedList;
import java.util.List;

public class MainDB {
    public List<Topping> toppings = new LinkedList<>();
    public MainDB(){
        toppings.add(new Topping("HomeFries", "HomeFries", .00));
        toppings.add(new Topping("Mac Salad","Mac Salad", .00));
        toppings.add(new Topping("Hot Sauce", "Hot Sauce", .00, .50, true, true));
        toppings.add(new Topping("Lettuce" , "Lettuce",  .00));
        toppings.add(new Topping("Tomato", "Tomato", .00));
        toppings.add(new Topping("Onions", "Onions",.00));
        toppings.add(new Topping("Cheese", "Cheese",.00, .50));
    }
    public List<Topping> getTopping(){
        //i need to read in the database? or load it in or something?
        return toppings;
    }
    public void getFood(){//cant have food List?

    }

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