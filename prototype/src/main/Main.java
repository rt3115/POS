package main;

import database.MainDB;
import database.TransactionDB;
import functions.AddItem;
import functions.Function;
import functions.Sales;
import gui.GUIMain;
import javafx.scene.control.Label;
import printer.testPrint;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static Values values = new Values();
    public static List<Function> functions = new LinkedList<>();
    public static Register register = new Register();
    public static TransactionDB transactionDB = new TransactionDB();
    public static MainDB mainDB = new MainDB();
    public static GUIMain guiMain = new GUIMain();

    public static boolean refund = false;
    public static final String VERSION = "A-POS-09172019-CLAW-03";
    /*
    1:DEV : A dev, B bug-tested, E experimental, D deployed
    2:CODE : POS (Piece of Shit) code name for project
    3:Date : 090319
    4:customer code : CLAW
    5:Version 01 02 03 04 11
    6 (extra) : Platform, added to the end if needed, W windows (also blank), M mac, I ios, L linux, A android
    EXAMPLE: A-POS-090319-CLAW-01
     */

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


        //print stuff

        testPrint printerService = new testPrint();


        /*
        printerService.printBytes(values.PRINTER_NAME, new byte[] {0x1b, 0x45}); //bold on
        printerService.printBytes(values.PRINTER_NAME, ("Register Online!" +
                "\nImportant Startup stuff goes here!" +
                "\nDate" +
                "\nSystem status?" +
                "\nUp time" +
                "\nPrinter name" +
                "\nall of the values" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n\n\n\n\n\n\n\n").getBytes());
        printerService.printBytes(values.PRINTER_NAME, new byte[] {0x1b, 0x46}); //bold off
        printerService.printBytes(values.PRINTER_NAME, new byte[] {0x1B,0x64,0x01});
        */


        System.out.println(printerService.getPrinters());
        /*
        ArrayList<byte[]> toPrint = new ArrayList<>();
        toPrint.add(new byte[] {0x1b, 0x1c, 0x70, 0x02, 0x00});
        toPrint.add(new byte[] {0x1b, 0x69, 0x01, 0x01});
        toPrint.add("Total: 21.00".getBytes());
        */
        //toPrint.add(new byte[] {0x1b, 0x69, 0x00, 0x00});
        //toPrint.add("\n\n\n\n".getBytes());



        /*rec = "\n\n\n\n";
        rec += "ABCDEFG/123456 - : absdegf/!@#$#$\n";
        rec += "ABCDEFG/123456 - : absdegf/!@#$#$";
        rec += "\n\n\n\n\n\n\n\n\n\n";*/
        //print some stuff. Change the printer name to your thermal printer name.

        /*
        printerService.printBytes("TSP654II - BT:COM3", new byte[] {0x1B,0x1E,0x46,0x10}); //setting the font size (largest I have found) 01 and 00 are smaller
        printerService.printBytes("TSP654II - BT:COM3", new byte[] {0x1b, 0x45}); //bold on
        printerService.printBytes("TSP654II - BT:COM3", new byte[] {0x1b, 0x46}); //bold off
        printerService.printBytes("TSP654II - BT:COM3", new byte[] {0x1b, 0x69, 0x01, 0x01}); //character expansion
        printerService.printBytes("TSP654II - BT:COM3", new byte[] {0x1b, 0x69, 0x00, 0x00}); //Cancel Character Expansion
        */
//        printerService.printBytes("TSP654II - BT:COM3", new byte[] {0x1b, 0x1c, 0x70, 0x01, 0x00}); //prints logo



        //printerService.printBytes("TSP654II - BT:COM3", new byte[] {0x1B,0x32});
        //printerService.printBytes("TSP654II - BT:COM3", new byte[] {0x1B,0x32});

//        printerService.printString("TSP654II - BT:COM3", rec);

//        printerService.printSetOfBytes("TSP654II - BT:COM3", toPrint);
//        printerService.printString("TSP654II - BT:COM3", rec);
        //0x1d, 'V', 1
        // cut that paper!
//        byte[] cutP = new byte[] { 0x1B,0x64,0x01}; //00 full cut 01 partial cut 02 feed and full cut
//
//        printerService.printBytes("TSP654II - BT:COM3", cutP);



        //load in all of the foods


        //add all the functions to the function list
        functions.add(new AddItem());
        functions.add(new Sales());

//        GUIFinal guiFinal = new GUIFinal();
//        guiFinal.startUp();
     //   GUIFinal.launch();
//           GUIMain.launch();

        //transactionDB.loadCurr();
//        guiMain.guiCloseRegister.update();

        guiMain.launch();

        //startup


    }

    public static Label closeFeedBack;

    public static void closeFeedBack(String feedback){
        closeFeedBack.setText(feedback);
    }

    public static Label salesFeedBack;

    public static void setSalesFeedBack(String feedBack){
        salesFeedBack.setText(feedBack);
    }

}
