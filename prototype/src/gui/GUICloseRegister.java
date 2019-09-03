package gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import main.Main;

public class GUICloseRegister {

    //for closing and opening the register

    GUIMain main;
    AnchorPane pane;

    Label status = new Label("Register Status: Not Open");
    Button openRegister = new Button("Open");
    Button closeRegister = new Button("Close/Summary");

    public GUICloseRegister(GUIMain main, AnchorPane pane){
        this.main = main;
        this.pane = pane;
        start();
    }

    public void update(){
        String temp = "";
        if(Main.register.isOpen())
            temp = "Register Status: Open";
        else
            temp = "Register Status: Close";

        status.setText(temp);
    }

    public void start(){
        update();

        openRegister.setPrefSize(200, 75);
        closeRegister.setPrefSize(200, 75);

        openRegister.setOnAction(actionEvent -> {
            Main.transactionDB.open();
            update();
        });

        closeRegister.setOnAction(actionEvent -> {
            Main.transactionDB.close();
            update();
        });


        pane.getChildren().addAll(status, openRegister, closeRegister);

        //setting the anchors
        {
            AnchorPane.setLeftAnchor(status, 50.00);
            AnchorPane.setTopAnchor(status, 20.00);

            AnchorPane.setLeftAnchor(openRegister, 50.00);
            AnchorPane.setTopAnchor(openRegister, 100.00);

            AnchorPane.setLeftAnchor(closeRegister, 50.00);
            AnchorPane.setTopAnchor(closeRegister, 175.00);
        }
    }

}
