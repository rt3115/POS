package main;

import common.Employee;
import database.EmployeeDB;
import functions.AccessLevel;
import functions.AddItem;
import functions.Function;
import gui.GUI;
import gui.GUIFinal;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static List<Function> functions = new LinkedList<>();
    public static EmployeeDB employeeDB = new EmployeeDB();

    public static void main(String[] args) {

        //main runner of the program!!

        //add all the functions to the function list
        functions.add(new AddItem());

        //Employee Database

        employeeDB.logIn(null);
        employeeDB.addEmployee(new Employee("Ronnie", "007", AccessLevel.ROOT));
        employeeDB.addEmployee(new Employee("Default", "1", AccessLevel.NORMAL));
        employeeDB.addEmployee(new Employee("Manager", "2", AccessLevel.MANAGER));
        employeeDB.addEmployee(new Employee("Owner", "3", AccessLevel.OWNER));
        employeeDB.addEmployee(new Employee("Kerry", "2019", AccessLevel.OWNER));

//        GUIFinal guiFinal = new GUIFinal();
//        guiFinal.startUp();
        GUIFinal.launch();
    }

}
