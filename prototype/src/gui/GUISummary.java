package gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import main.Main;

import java.util.ArrayList;

public class GUISummary {

    AnchorPane pane;
    GUIMain main;

    String currSum = "";

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

        sumButtons.getChildren().addAll(generalSum, salesSummary, detailedSummary, custumSummary);

        ScrollPane scrollPane = new ScrollPane();
        Label scrollView = new Label("Please Select A Summary");
        scrollView.setPrefSize(250, 400);
        scrollPane.setContent(scrollView);

        Button printButton = new Button("Print");

        {
            //Button stuff
            custumSummary.setOnAction(actionEvent -> {
                scrollView.setText("You must configure the Custom Summary");
            });
            generalSum.setOnAction(actionEvent -> {
                currSum = makeGenSummary();
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

            AnchorPane.setLeftAnchor(printButton, 10.00);
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

    public void print(String pr){
        //prints the given string to the printer
    }

}
