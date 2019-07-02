package gui;

import common.BasicFood;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Main;

public class GUIItemView{

    GUIMain main;
    Pane pane;

    GridPane mainGrid;
    GridPane toppingsGrid;
    GridPane sidesGird;

    HBox sizesBox = new HBox();
    HBox functionRow = new HBox();

    public GUIItemView(GUIMain main, Pane pane){
        this.pane = pane;
        this.main = main;

        start();
    }

    public void start(){
        //creates the UI elements
        AnchorPane mainPane = new AnchorPane();
        mainGrid = new GridPane();
        toppingsGrid = new GridPane();
        sidesGird = new GridPane();

        Button adjustOrderButton = new Button("Adjust Order");
        Button addSideButton = new Button("Add Side");
        Button doneButton = new Button("Done");
        doneButton.setVisible(false);
        functionRow.getChildren().addAll(adjustOrderButton, addSideButton, doneButton);

    }

    public void updateMainGrid(){
        mainGrid.getChildren().remove(0, mainGrid.getChildren().size() - 1);

        for(int x = 0; x < 4; x++){
            for(int y = 0; y < 100 ; y++){

            }
        }
    }

    public void updateToppingsGrid(){

    }

    public void updateSidesGrid(){

    }

    public void updateItems(){
        //updates the changes to the items
    }

    public void refreshToppings(){

    }

}
