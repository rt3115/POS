package gui;

import common.Item;
import common.Observer;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import main.Main;
import main.Register;


public class GUICurrTransView implements Observer<Register> {

    GUIMain main;
    Pane pane;

    VBox scrollBox = new VBox();
    ScrollPane scrollPane = new ScrollPane();

    VBox vBox = new VBox();

    HBox hBox = new HBox();
    Button voidItem = new Button("Void Item");
    Button voidTrans = new Button("Void Trans");

    public GUICurrTransView(GUIMain main, Pane pane){
        this.main = main;
        this.pane = pane;

        Main.register.addObserver(this);

        start();
    }

    public void start(){
        scrollPane.setContent(scrollBox);
        hBox.getChildren().addAll(voidItem, voidTrans);

        voidItem.setPrefSize(200, 50);
        voidTrans.setPrefSize(200, 50);

        scrollPane.setPrefHeight(800);
        scrollPane.setMaxHeight(800);

        voidItem.setOnAction(ActionEvent -> {
            Main.register.removeLast();
        });
        voidTrans.setOnAction(actionEvent -> {
            Main.register.voidTransaction();
        });
        vBox.getChildren().addAll(hBox, scrollPane);
        pane.getChildren().add(vBox);
    }

    public void update(Register register){
        //when a change occurs update the view
        scrollBox.getChildren().remove(0, scrollBox.getChildren().size());
        for(Item item : Main.register.getList()){
            Label label = new Label(item.toString());
            label.setFont(new Font(30));
            scrollBox.getChildren().add(label);
        }
        if(register.isTransDone()){
            scrollPane.setStyle("-fx-border-style: none;" +
                    "-fx-border-width: 2px;" +
                    "-fx-border-color: red;" +
                    "-fx-border-radius: 0;" +
                    "-fx-background-radius: 0;");
        }else{
            scrollPane.setStyle("-fx-border-style: none;" +
                    "-fx-border-width: 2px;" +
                    "-fx-border-color: black;" +
                    "-fx-border-radius: 0;" +
                    "-fx-background-radius: 0;");
        }
    }
}

