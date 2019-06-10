package main;

import common.Employee;
import database.EmployeeDB;
import database.TransactionDB;
import functions.AccessLevel;
import functions.AddItem;
import functions.Function;
import functions.Sales;
import gui.GUI;
import gui.GUIFinal;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static List<Function> functions = new LinkedList<>();
    public static EmployeeDB employeeDB = new EmployeeDB();
    public static TransactionDB transactionDB = new TransactionDB();
    public static Values values = new Values();

    public static void main(String[] args) {

        //main runner of the program!!

        //set the values of the program
        values.readValues();

        //add all the functions to the function list
        functions.add(new AddItem());
        functions.add(new Sales());

        //Employee Database

        employeeDB.logIn(null);
        employeeDB.addEmployee(new Employee("Ronnie", "please", AccessLevel.ROOT));
        employeeDB.load();

//        GUIFinal guiFinal = new GUIFinal();
//        guiFinal.startUp();
        GUIFinal.launch();
    }

}
