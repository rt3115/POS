package gui;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.Main;

public class GUIFunctions {

    GUIMain main;
    Pane pane;

    double length = 150;
    double height = 75;

    VBox box = new VBox();

    boolean isActive = false; //prevents accidentally pressing the buttons at the wrong time

    Button sales = new Button("Sales");
    Button viewItems = new Button("Items");
    Button viewTransactions = new Button("Transactions");
    Button summary = new Button("Summary");
    Button infoAndSupport = new Button("Info/Support");
    Button values = new Button("Values");
    ToggleButton isActiveToggle = new ToggleButton("Are Functions Active");

    public GUIFunctions(GUIMain main, Pane pane){
        this.main = main;
        this.pane = pane;
        start();
    }

    public void start(){

        isActiveToggle.setPrefSize(length, height);

        sales.setPrefSize(length, height);
        viewItems.setPrefSize(length, height);
        viewTransactions.setPrefSize(length, height);
        summary.setPrefSize(length, height);
        infoAndSupport.setPrefSize(length, height);
        values.setPrefSize(length, height);

        box.getChildren().addAll(isActiveToggle, sales, viewItems, viewTransactions, summary, values, infoAndSupport);
        pane.getChildren().add(box);

        isActiveToggle.setOnAction(actionEvent -> {
            if(isActiveToggle.isSelected()){
                sales.setDisable(false);
                viewItems.setDisable(false);
                viewTransactions.setDisable(false);
                summary.setDisable(false);
                infoAndSupport.setDisable(false);
                values.setDisable(false);
            }else{
                sales.setDisable(true);
                viewItems.setDisable(true);
                viewTransactions.setDisable(true);
                summary.setDisable(true);
                infoAndSupport.setDisable(true);
                values.setDisable(true);
            }
        });

        sales.setOnAction(actionEvent -> {
            main.changeView('s');
        });

        viewItems.setOnAction(actionEvent -> {
            main.changeView('i');
        });

        viewTransactions.setOnAction(actionEvent -> {
            main.changeView('t');
        });

        summary.setOnAction(actionEvent -> {
            main.changeView('a');
        });

        values.setOnAction(actionEvent -> {
            main.changeView('v');
        });

        infoAndSupport.setOnAction(actionEvent -> {
            main.changeView('p');
        });


        isActiveToggle.fire();
    }

}
