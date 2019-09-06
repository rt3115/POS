package database;

import common.Transaction;
import main.Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TransactionManager {
    //support class to the TransactionDB
    //handles the sorting organizing the files
    //only summary data will be saved, all else will be ignored

    String currTrnasLocation = "C:/Users/Public/POS/currTrans"; //if the register was closed then the currTransFile will start with Register,close
    //if the register was not closed then it starts with Register,open
    String totalsLocation = "C:/Usets/Public/POS/Totals"; //location of the file that contains all of the totals of previous closes

    public ArrayList<Double> currTotals = new ArrayList<>();

    public TransactionManager(){
        System.err.println("Transaction Manager HAS AWOKEN! Be careful lazy employees");

        for(int i = 0;i <= 21; i++) {
            currTotals.add(0.0);
        }

    }



    public void save(){
        ArrayList<Double> temp = Main.transactionDB.getSummary(Main.transactionDB.getStartIdCurr());

        String fileContent = "Register," + Main.register.isOpen() +"\n"; //add lines to be saved into the CurrTotals
        for(int i = 0; i <= 21; i++) {
            fileContent += temp.get(i) + "\n";
        }

        try {
            FileWriter fileWriter = new FileWriter(currTrnasLocation);
            fileWriter.write(fileContent);
            fileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Double> loadCurr(){
        //returns an ArrayList of doubles that contain the values of the last instance of the open, only used if the program closes without being closed
        ArrayList<Double> temp = new ArrayList<>();
        System.err.println("Loading Curr");
        try {
            Scanner reader = new Scanner(new File(currTrnasLocation));
            String tempString = reader.nextLine();
            String[] tempArr = tempString.split(",");
            if(tempArr[0].equals("Register")) {
                boolean bol = Boolean.parseBoolean(tempArr[1]);
                if (bol){
                    //the register was not closed
                    System.err.println("The Register was not closed, loading in prev totals");
                    Main.closeFeedBack("The Register was not closed, loading in the previous totals. \n" +
                            "If this was not intentional please close the register and restart the program");
                }else{
                    //the register was closed
                    Main.closeFeedBack("The register has opened normally");
                    return temp;
                }
            }

            System.err.println("CurrTotal is being set");
            for(int i = 0; i <= 21; i++) {
                tempString = reader.nextLine();
                temp.add(Double.parseDouble(tempString));
                currTotals.set(i, Double.parseDouble(tempString));
            }




        }catch (IOException e){
            e.printStackTrace();
        }
        //Main.guiMain.guiCloseRegister.update();
        return temp;
    }

}
