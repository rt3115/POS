package gui;

import common.Observer;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.Main;
import main.Register;

import java.awt.event.ActionEvent;

public class GUIPaymentMethods implements Observer<Register> {

    GUIMain main;
    Pane pane;

    VBox vBox = new VBox();

    Label total = new Label("Total: " );
    Label tax = new Label("Tax: " );
    Label amountEntered = new Label("Amount Entered: ");
    Label change = new Label("Change: ");

    HBox hBox = new HBox();
    Button cash = new Button("Cash");
    Button credit = new Button("Credit");

    public GUIPaymentMethods(GUIMain main, Pane pane){
        this.main = main;
        this.pane = pane;
        start();
    }

    public void start(){
        vBox.getChildren().addAll(total, tax, amountEntered, change, hBox);
        hBox.getChildren().addAll(cash, credit);
        pane.getChildren().add(vBox);

        cash.setOnAction(ActionEvent -> {
            Main.register.cashout();
        });
        credit.setOnAction(ActionEvent -> {
            Main.register.cashout(Main.register.getTotal());
        });

        Main.register.addObserver(this);
    }

    public void update(Register register){
        total.setText("Total: " + register.getTotal()/100.00);
        tax.setText("Tax: " + register.getTax()/100.0);
        amountEntered.setText("Amount entered: " + register.getEntered()/100.0);
        change.setText("Change: " + register.getChange()/100.0);
    }

}
