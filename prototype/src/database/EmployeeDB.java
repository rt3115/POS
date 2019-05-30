package database;

import common.Employee;
import functions.AccessLevel;
import main.Register;

import java.util.LinkedList;
import java.util.List;

public class EmployeeDB {

    private List<Employee> employees = new LinkedList<>();
    private Employee currEmployee;

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

}
