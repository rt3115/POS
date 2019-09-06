package gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import main.Main;
import printer.Receipt;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

public class GUISummary {

    AnchorPane pane;
    GUIMain main;

    String currSum = "";

    public double length = 100.0;
    public double width = 50.0;

    public GUISummary(GUIMain main, AnchorPane pane){
        this.main = main;
        this.pane = pane;
        start();
    }

    public void start(){
        HBox sumButtons = new HBox();

        Button generalSum = new Button("Summary");
        Button salesSummary = new Button("Sales Summary");
        Button detailedSummary = new Button("Detailed Summary");
        Button custumSummary = new Button("Custom Summary");

        detailedSummary.setDisable(true);
        custumSummary.setDisable(true);

        generalSum.setPrefSize(length, width);
        salesSummary.setPrefSize(length, width);
        detailedSummary.setPrefSize(length, width);
        custumSummary.setPrefSize(length, width);

        sumButtons.getChildren().addAll(generalSum, salesSummary, detailedSummary, custumSummary);

        ScrollPane scrollPane = new ScrollPane();
        Label scrollView = new Label("Please Select A Summary");
        scrollView.setPrefSize(250, 700);
        scrollPane.setContent(scrollView);

        Button printButton = new Button("Print");

        printButton.setPrefSize(length, width);

        {
            //Button stuff
            custumSummary.setOnAction(actionEvent -> {
                scrollView.setText("You must configure the Custom Summary");
            });
            generalSum.setOnAction(actionEvent -> {
                currSum = makeGenSummary();
                scrollView.setText(currSum);
            });
            salesSummary.setOnAction(actionEvent -> {
                currSum = makeSalesSummary();
                scrollView.setText(currSum);
            });

            printButton.setOnAction(actionEvent -> {
                print(currSum);
            });
        }

        //Setting the Anchors
        {
            AnchorPane.setLeftAnchor(sumButtons, 10.00);
            AnchorPane.setTopAnchor(sumButtons, 10.00);

            AnchorPane.setTopAnchor(scrollPane, 70.00);
            AnchorPane.setLeftAnchor(scrollPane, 10.00);

            AnchorPane.setLeftAnchor(printButton, 300.00);
            AnchorPane.setTopAnchor(printButton, 500.00);
        }

        pane.getChildren().addAll(sumButtons, scrollPane, printButton);
    }

    public String makeGenSummary(){
        //makes and returns the general summary string for the current day
        String rt = "";

        ArrayList<Double> temp = Main.transactionDB.getSummary(Main.transactionDB.getStartIdCurr());
        rt += "Total Sales: " + temp.get(0) + "\n";
        rt += "Total Deposit: " + temp.get(1)/100.00 + "\n";
        rt += "No Sales: " + temp.get(2) + "\n";
        rt += "Items Entered Manually: " + temp.get(3) + "\n";
        rt += "Total Bottle Deposit: " + temp.get(6) + "\n";
        rt += "ID Range: " + temp.get(4) + " - " + temp.get(5) + "\n";

        return rt;
    }

    String pattern = "##0.00";
    DecimalFormat dF = new DecimalFormat(pattern);

    public String makeSalesSummary(){
        Date date = new Date();
        ArrayList<Double> temp = Main.transactionDB.getSummary(Main.transactionDB.getStartIdCurr());
        String rt = "";

        rt += "Food Truck Register 1# Sales Summary  \n" +
                "" + date.toString() + "\n\n";

        rt += "Number Of Sales:                 " + temp.get(0).intValue() + "\n";
        rt += "Start ID:                        " + temp.get(4).intValue() + "\n";
        rt += "End ID:                          " + temp.get(5).intValue() + "\n";
        rt += "Average Sale:                    " + dF.format((int)(temp.get(7)*1000)/100000.00) + "\n";
        rt += "Number of Voids:                 " + temp.get(8).intValue() + "\n";
        rt += "Dollar Value of Void:            " + dF.format(temp.get(9)/100.00) + "\n";
        rt += "Number of No Sales:              " + temp.get(2).intValue() + "\n\n";

        rt += "Starting Cash in Drawer:         " + dF.format(temp.get(10)) + "\n";
        rt += "******\n\n";

        rt += "Deposits Collected:              " + temp.get(6)/100.00 + "\n\n";

        rt += "Number Of Payments Received:     " + temp.get(11).intValue() + "\n";
        rt += "Cash Payments:                   " + temp.get(12).intValue() + "\n";
        rt += "Credit Payments:                 " + temp.get(13).intValue() + "\n";
        rt += "******\n";
        rt += "Total Payments:                  " + temp.get(14)/100.00 + "\n\n";

        rt += "Total Taxable Sales:             " + temp.get(20)/100.00 + "\n";
        rt += "Total NonTaxable Sales:          " + temp.get(21)/100.00 + "\n";
        rt += "******\n";
        rt += "Total Sales:                     " + temp.get(1)/100.00 + "\n";
        rt += "Total Tax:                       " + temp.get(17)/100.00 + "\n";
        rt += "******\n";
        rt += "Total Sales - Total Tax:         " + (temp.get(1)-temp.get(17))/100.00 + "\n";
        rt += "Sales - Tax - Deposit:           " + (temp.get(1)-temp.get(17)-temp.get(6))/100.00 + "\n\n";

        rt += "Total Cash:                      " + temp.get(15)/100.00 + "\n";
        rt += "Total Credit Cards:              " + temp.get(16)/100.00 + "\n";
        rt += "******\n";
        rt += "Deposit Total:                   " + temp.get(1)/100.00 + "\n\n";

        rt += "Total Discount Items:            " + temp.get(18).intValue() + "\n";
        rt += "Total Discount Dollars:          " + temp.get(19)/100.00 + "\n";
        rt += "\n\n\n\n\n\n\n\n\n\n";
        return rt;
    }

    public void print(String pr){
        //prints the given string to the printer
        Receipt.print(pr.getBytes(), Main.values.PRINTER_NAME);
    }

}
