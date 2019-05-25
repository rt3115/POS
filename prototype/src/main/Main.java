package main;

import functions.AddItem;
import functions.Function;
import gui.GUI;
import gui.GUIFinal;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static List<Function> functions = new LinkedList<>();

    public static void main(String[] args) {

        //main runner of the program!!

        //add all the functions to the function list
        functions.add(new AddItem());

        GUIFinal.launch();
//        GUI.launch();
    }

}
