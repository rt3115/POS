package database;

import common.Employee;
import functions.AccessLevel;
import main.Register;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class EmployeeDB {

    private List<Employee> employees = new LinkedList<>();
    private Employee currEmployee;

    private String fileName = "C:\\Users\\happy\\OneDrive\\Ronnie\\Documents\\Clawsons\\POS\\prototype\\src\\database\\files\\employees";
    //private String fileName = "C:\\Users\\happy\\Desktop\\";

    public void addEmployee(Employee employee){
        employees.add(employee);
    }

    public void logIn(String logIn){
        if(logIn == null){
            Register.employee = new Employee("Not logged In", "", AccessLevel.NOTLOGGEDIN);
        }else{
            for(Employee employee : employees){
                if(employee.isLogValid(logIn))
                    Register.employee = employee;
            }
        }
    }

    public boolean load(){
        //loads the employees into the list and returns true if it worked
        try {
            Scanner in = new Scanner(new File(fileName));

            while (in.hasNext()) {
                String line = in.next();
                String[] words = line.split(",");
                System.err.println(line);

                String name = words[0];
                if (name.equals("name"))
                    continue;

                String code = words[1];
                AccessLevel accessLevel;

                if(words[2].equals("EMPLOYEE"))
                    accessLevel = AccessLevel.NORMAL;
                else if(words[2].equals("MANAGER"))
                    accessLevel = AccessLevel.MANAGER;
                else if(words[2].equals("OWNER"))
                    accessLevel = AccessLevel.OWNER;
                else
                    accessLevel = AccessLevel.NOTLOGGEDIN;

                employees.add(new Employee(name, code, accessLevel));
            }
        }catch (FileNotFoundException e){
            System.err.println(e);
        }

        return true;
    }

}
