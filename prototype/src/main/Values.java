package main;

import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Scanner;

public class Values {

    //in order for the system to run every value must be filled
    public double TAX_RATE;

    private String fileLocation = "main/Values.txt";

    public boolean readValues(){
        //reads in the values from the file
        try{

            Scanner in = new Scanner(fileLocation);
            while (in.hasNext()){
                String line = in.next();
                String[] words = line.split(",");
                switch (words[0]){
                    case "TAX_RATE":
                        TAX_RATE = Double.parseDouble(words[1]);
                        break;
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
