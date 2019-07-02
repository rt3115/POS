package main;

import database.MainDB;
import database.TransactionDB;
import functions.AddItem;
import functions.Function;
import functions.Sales;
import gui.GUIMain;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static List<Function> functions = new LinkedList<>();
    public static TransactionDB transactionDB = new TransactionDB();
    public static Values values = new Values();
    public static MainDB mainDB = new MainDB();
    public static Register register = new Register();

    public static void main(String[] args) {

        //notes
        /*
        total sales
        deposit
        value of sales
        drink sales
        food sales
        prepared foods
        sodas
        tax
         */

        //main runner of the program!!

        //set the values of the program
        values.readValues();

        //load in all of the foods


        //add all the functions to the function list
        functions.add(new AddItem());
        functions.add(new Sales());

//        GUIFinal guiFinal = new GUIFinal();
//        guiFinal.startUp();
     //   GUIFinal.launch();
           GUIMain.launch();
    }

}
