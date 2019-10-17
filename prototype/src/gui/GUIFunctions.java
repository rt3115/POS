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
    Button close = new Button("Open/Close");
    ToggleButton isActiveToggle = new ToggleButton("Are Functions Active");
    public ToggleButton isRefundActive = new ToggleButton("Refund");

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
        close.setPrefSize(length, height);
        isRefundActive.setPrefSize(length, height);

        viewItems.setDisable(true);

        box.getChildren().addAll(isActiveToggle, sales, viewItems, viewTransactions, summary, values, infoAndSupport, close, isRefundActive);
        pane.getChildren().add(box);

        isActiveToggle.setOnAction(actionEvent -> {
            if(isActiveToggle.isSelected()){
                sales.setDisable(false);
                viewItems.setDisable(false); //this function is disabled
                viewTransactions.setDisable(false);
                summary.setDisable(false);
                infoAndSupport.setDisable(false);
                values.setDisable(false);
                close.setDisable(false);
                isRefundActive.setDisable(false);
            }else{
                sales.setDisable(true);
                viewItems.setDisable(true);
                viewTransactions.setDisable(true);
                summary.setDisable(true);
                infoAndSupport.setDisable(true);
                values.setDisable(true);
                close.setDisable(true);
                isRefundActive.setDisable(true);
            }
        });

        sales.setOnAction(actionEvent -> {
            if(Main.register.isOpen()) {
                main.changeView('s');
                isActiveToggle.fire();
            }else{
                //feedback goes here, Can not make sales with out register being open
                //unless debug is on
                Main.closeFeedBack("Must Open Register to go to Sales");
            }
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

        close.setOnAction(actionEvent -> {
            main.guiCloseRegister.update();
            main.changeView('c');
        });

        isRefundActive.setOnAction(actionEvent -> {
            //main.refund = isRefundActive.isSelected();
            Main.refund = isRefundActive.isSelected();
            //System.err.println("Should have updated refund bool: " + main.refund);
        });


        isActiveToggle.fire();
    }

}
