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
    Label feedBack = new Label();
    Button openRegister = new Button("Open");
    Button closeRegister = new Button("Close/Summary");

    public GUICloseRegister(GUIMain main, AnchorPane pane){
        this.main = main;
        this.pane = pane;
        start();
    }

    public void update(){
        String temp = "";
        if(Main.register.isOpen()) {
            temp = "Register Status: Open";
            openRegister.setDisable(true);
            closeRegister.setDisable(false);
        }else {
            temp = "Register Status: Closed";
            openRegister.setDisable(false);
            closeRegister.setDisable(true);

        }

        status.setText(temp);
    }

    public void start(){
        update();

        openRegister.setPrefSize(200, 75);
        closeRegister.setPrefSize(200, 75);

        openRegister.setOnAction(actionEvent -> {
            Main.transactionDB.open();
            update();
            openRegister.setDisable(true);
            closeRegister.setDisable(false);
        });

        closeRegister.setOnAction(actionEvent -> {
            Main.transactionDB.close();
            update();
            openRegister.setDisable(false);
            closeRegister.setDisable(true);
        });


        pane.getChildren().addAll(status, openRegister, closeRegister, feedBack);

        Main.closeFeedBack = feedBack;

        //setting the anchors
        {
            AnchorPane.setLeftAnchor(status, 50.00);
            AnchorPane.setTopAnchor(status, 20.00);

            AnchorPane.setLeftAnchor(openRegister, 50.00);
            AnchorPane.setTopAnchor(openRegister, 100.00);

            AnchorPane.setLeftAnchor(closeRegister, 50.00);
            AnchorPane.setTopAnchor(closeRegister, 175.00);

            AnchorPane.setTopAnchor(feedBack, 200.00);
            AnchorPane.setLeftAnchor(feedBack, 300.00);
        }
    }

}
