package gui;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.Main;

public class GUIFunctions {

    GUIMain main;
    Pane pane;

    VBox box = new VBox();

    Button sales = new Button("Sales");
    Button viewItems = new Button("Items");
    Button viewTransactions = new Button("Transactions");
    Button summary = new Button("Summary");
    Button infoAndSupport = new Button("Info/Support");
    Button values = new Button("Values");

    public GUIFunctions(GUIMain main, Pane pane){
        this.main = main;
        this.pane = pane;
        start();
    }

    public void start(){

        box.getChildren().addAll(sales, viewItems, viewTransactions, summary, values, infoAndSupport);
        pane.getChildren().add(box);

        sales.setOnAction(actionEvent -> {
            Main.guiMain.changeView('s');
        });


    }

}
