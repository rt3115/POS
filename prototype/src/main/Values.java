package main;

import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Values {

    //in order for the system to run every value must be filled
    public double TAX_RATE;
    public double DEBUG_MODE;
    public double ITEM_VIEW_WIDTH;

    private String fileLocation = "C:\\Users\\happy\\OneDrive\\Ronnie\\Documents\\Clawsons\\POS\\prototype\\src\\main\\Values";

    public boolean readValues(){
        //reads in the values from the file
        try{

            Scanner in = new Scanner(new File(fileLocation));
            while (in.hasNext()){
                String line = in.next();
                String[] words = line.split(",");
                System.err.println(line);
                switch (words[0]){
                    case "TAX_RATE":
                        TAX_RATE = Double.parseDouble(words[1]);
                        break;
                    case "DEBUG_MODE":
                        DEBUG_MODE = Double.parseDouble(words[1]);
                    case "ITEM_VIEW_WIDTH":
                        ITEM_VIEW_WIDTH = Double.parseDouble(words[1]);
                        default:
                            System.err.println("Not a proper value");
                }
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
