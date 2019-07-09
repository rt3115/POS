package gui;

import common.Item;
import common.Observer;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
            scrollBox.getChildren().add(new Label(item.toString()));
        }
    }
}

