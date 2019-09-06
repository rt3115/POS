package printer;

import common.Item;
import common.Transaction;
import main.Main;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Arrays;

public class Receipt {

    public static void print(byte[] bytes, String printerName){
        //sends the information to the printer to print
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();

        PrintService printService[] = PrintServiceLookup.lookupPrintServices(
                flavor, pras);
        PrintService service = findPrintService(printerName, printService);

        DocPrintJob job = service.createPrintJob();

        try {

            Doc doc = new SimpleDoc(bytes, flavor, null);

            job.print(doc, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static PrintService findPrintService(String printerName,
                                          PrintService[] services) {
        for (PrintService service : services) {
            if (service.getName().equalsIgnoreCase(printerName)) {
                return service;
            }
        }

        return null;
    }

    public static void print_Receipt_Without_Logo(Transaction transaction){

        try{
            byte[] data;
            ArrayList<Byte> list = new ArrayList<Byte>();
            Byte[] tempList;

            data = ("Clawson's on the GO! \n Data: " + transaction.date.toString() + "\n Order Number: \n").getBytes();
            tempList = new Byte[data.length];
            copyArray(data, tempList);
            list.addAll(Arrays.asList(tempList));

            data = new byte[] {0x1b, 0x69, 0x01, 0x01}; //expand the chars
            tempList = new Byte[data.length];
            copyArray(data, tempList);
            list.addAll(Arrays.asList(tempList));

            //TODO: make it so that trans id is only the last two digits
            data = ("" + transaction.id).getBytes(); //prints out the current id for the transaction
            tempList = new Byte[data.length];
            copyArray(data, tempList);
            list.addAll(Arrays.asList(tempList));

            data = new byte[] {0x1b, 0x69, 0x00, 0x00}; //back to normal
            tempList = new Byte[data.length];
            copyArray(data, tempList);
            list.addAll(Arrays.asList(tempList));

            data = ("" + transaction.getReceiptString() + "\n\n\n\n\n\n").getBytes(); //prints the items and total
            tempList = new Byte[data.length];
            copyArray(data, tempList);
            list.addAll(Arrays.asList(tempList));


            data = new byte[] {0x1B,0x64,0x01}; //cut the paper
            tempList = new Byte[data.length];
            copyArray(data, tempList);
            list.addAll(Arrays.asList(tempList));



            data = new byte[list.size()];
            for (int index = 0; index < data.length; index++) {
                data[index] = (Byte) list.get(index);
            }

            print(data, Main.values.PRINTER_NAME);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void print_Receipt(Transaction transaction){
        //prints the given transaction onto a receipt
        try{
            byte[] data;
            ArrayList<Byte> list = new ArrayList<Byte>();
            Byte[] tempList;

            data = new byte[] {0x1b, 0x1c, 0x70, 0x02, 0x00};
            tempList = new Byte[data.length];
            copyArray(data, tempList);
            list.addAll(Arrays.asList(tempList));

            data = ("Clawson's on the GO! \n Data: " + transaction.date.toString() + "\n Order Number: \n").getBytes();
            tempList = new Byte[data.length];
            copyArray(data, tempList);
            list.addAll(Arrays.asList(tempList));

            data = new byte[] {0x1b, 0x69, 0x01, 0x01}; //expand the chars
            tempList = new Byte[data.length];
            copyArray(data, tempList);
            list.addAll(Arrays.asList(tempList));

            //TODO: make it so that trans id is only the last two digits
            data = ("" + transaction.id).getBytes(); //prints out the current id for the transaction
            tempList = new Byte[data.length];
            copyArray(data, tempList);
            list.addAll(Arrays.asList(tempList));

            data = new byte[] {0x1b, 0x69, 0x00, 0x00}; //back to normal
            tempList = new Byte[data.length];
            copyArray(data, tempList);
            list.addAll(Arrays.asList(tempList));

            data = ("" + transaction.getReceiptString() + "\n\n\n\n\n\n").getBytes(); //prints the items and total
            tempList = new Byte[data.length];
            copyArray(data, tempList);
            list.addAll(Arrays.asList(tempList));


            data = new byte[] {0x1B,0x64,0x01}; //cut the paper
            tempList = new Byte[data.length];
            copyArray(data, tempList);
            list.addAll(Arrays.asList(tempList));



            data = new byte[list.size()];
            for (int index = 0; index < data.length; index++) {
                data[index] = (Byte) list.get(index);
            }

            print(data, Main.values.PRINTER_NAME);

        }catch (Exception e){
            e.printStackTrace();
        }
    }



    /*
            data = ("Within " + "\u001b\u002d\u0001" + "30 days\u001b\u002d\u0000" + " with receipt\r\n").getBytes();  //Specify/Cancel Underline Printing
            tempList = new Byte[data.length];
            CopyArray(data, tempList);
            list.addAll(Arrays.asList(tempList));

            data = new byte[list.size()];
            for (int index = 0; index < data.length; index++) {
                data[index] = (Byte) list.get(index);
            }

            byte[] data;
            ArrayList<Byte> list = new ArrayList<Byte>();
     */

    private static void copyArray(byte[] array1, Byte[] array2) {

        int index = 0;
        while(index < array2.length)
        {
            array2[index] = array1[index];
            index++;
        }
    }

}
