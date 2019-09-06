package main;

import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Values {

    public Values(){
        this.readValues();
    }

    //in order for the system to run every value must be filled
    public static double TAX_RATE;
    public static double DEBUG_MODE;
    public static double ITEM_VIEW_WIDTH;
    public static String PRINTER_NAME;
    public static double BOTTLE_DEPOSIT;
    public static double STARTING_CASH;

    private String fileLocation = "C:\\Users\\Public\\POS\\Values";

    public boolean readValues(){
        //reads in the values from the file
        try{

            Scanner in = new Scanner(new File(fileLocation));
            while (in.hasNext()){
                String line = in.nextLine();
                String[] words = line.split(",");
                System.err.println(line);
                if(words[0].equals("TAX_RATE"))
                    TAX_RATE = Double.parseDouble(words[1]);
                if(words[0].equals("DEBUG_MODE"))
                    DEBUG_MODE = Double.parseDouble(words[1]);
                if(words[0].equals("ITEM_VIEW_WIDTH"))
                    ITEM_VIEW_WIDTH = Double.parseDouble(words[1]);
                if(words[0].equals("PRINTER_NAME"))
                    PRINTER_NAME = words[1];
                if(words[0].equals("BOTTLE_DEPOSIT"))
                    BOTTLE_DEPOSIT = Double.parseDouble(words[1]);
                if(words[0].equals("START_CASH"))
                    STARTING_CASH = Double.parseDouble(words[1]);

            }

        }catch (Exception e){
            System.err.println(e);
        }

        return true;
    }

    public boolean saveValues(){
        //saves the values to the values file


        return true;
    }
}
