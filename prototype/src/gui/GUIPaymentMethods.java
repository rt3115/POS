package gui;

import common.Observer;
import common.Transaction;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import main.Main;
import main.Register;

import java.awt.event.ActionEvent;

public class GUIPaymentMethods implements Observer<Register> {

    private String genStyle = "" +
            "-fx-border-style: none;" +
            "-fx-border-width: 1px;" +
            "-fx-border-color: black;" +
            "-fx-border-radius: 0;" +
            "-fx-background-radius: 0;";

    GUIMain main;
    Pane pane;

    VBox vBox = new VBox();

    Label total = new Label("Total: " );
    Label tax = new Label("Tax: " );
    Label amountEntered = new Label("Amount Entered: ");
    Label change = new Label("Change: ");
    Label amountStillOwed = new Label("Amt Owed: ");

    HBox hBox = new HBox();
    Button cash = new Button("Cash");
    Button credit = new Button("Credit");

    public GUIPaymentMethods(GUIMain main, Pane pane){
        this.main = main;
        this.pane = pane;
        start();
    }

    public void start(){
        vBox.getChildren().addAll(total, amountStillOwed, tax, amountEntered, change, hBox);
        hBox.getChildren().addAll(cash, credit);
        pane.getChildren().add(vBox);

        total.setStyle(genStyle);
        total.setPrefWidth(300);
        change.setStyle(genStyle);
        change.setPrefWidth(300);
        tax.setStyle(genStyle);
        tax.setPrefWidth(300);
        amountEntered.setStyle(genStyle);
        amountEntered.setPrefWidth(300);
        amountStillOwed.setStyle(genStyle);
        amountStillOwed.setPrefWidth(300);

        amountEntered.setFont(new Font(20));
        cash.setFont(new Font(25));
        credit.setFont(new Font(25));
        tax.setFont(new Font(20));
        total.setFont(new Font(30));
        change.setFont(new Font(30));
        amountStillOwed.setFont(new Font(30));

        cash.setPrefSize(130, 70);
        credit.setPrefSize(130, 70);

        cash.setOnAction(ActionEvent -> {
            if(!Main.register.isTransDone())
                Main.register.cashout(Transaction.PAYMENT_TYPE.CASH);
        });
        credit.setOnAction(ActionEvent -> {
            if(!Main.register.isTransDone())
                Main.register.cashout(Transaction.PAYMENT_TYPE.CREDIT);
        });

        Main.register.addObserver(this);
    }

    public void update(Register register){
        main.guiKeyPad.clear();
        total.setText("Total: " + register.getTotal()/100.00);
//        total.setText(register.getTotalString());
        amountStillOwed.setText(register.getTotalString());
        tax.setText("Tax: " + register.getTax()/100.0);
        amountEntered.setText("Amount entered: " + register.getEntered()/100.0);
        change.setText("Change: " + register.getChange()/100.0);
    }

}
