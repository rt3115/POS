package gui;

import common.BasicFood;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.Main;

import java.awt.event.ActionEvent;

public class GUIKeyPad {

    GUIMain main;
    Pane pane;

    TextField textField = new TextField("0");
    GridPane keyPadGrid = new GridPane();

    Button addButton = new Button("Add");
    Button clearButton = new Button("Clear");
    Button discountButton = new Button("Discount");
    Button dotButton = new Button(".");
    Button zeroButton = new Button("0");

    Label feedBack = new Label("");

    public GUIKeyPad(GUIMain main, Pane pane){
        this.main = main;
        this.pane = pane;
        start();
    }

    public void start(){

        Main.salesFeedBack = feedBack;

        Main.register.setGuiKeyPad(this);

        VBox vBox = new VBox();


        HBox hBox = new HBox();

        VBox functions = new VBox();
        functions.getChildren().addAll( clearButton, addButton,discountButton);

        int i = 9;
        for(int x = 3; x >= 1; x--){
            for(int y = 3; y >= 1; y--){
                Button temp = new Button("" + i);
                temp.setPrefSize(70, 70);
                keyPadGrid.add(temp, y , x);
                int tempInt = i;
                temp.setOnAction(actionEvent -> {
                    if(textField.getText().equals("0")){
                        textField.setText("" + tempInt);
                    }else{
                        textField.setText(textField.getText() + tempInt);
                    }
                });
                i--;
            }
        }

        zeroButton.setPrefSize(140, 70);
        zeroButton.setOnAction(actionEvent -> {
            textField.setText(textField.getText() + "0");
        });

        dotButton.setPrefSize(70, 70);
        dotButton.setOnAction(ActionEvent -> {
            textField.setText(textField.getText() + ".");
        });

        clearButton.setPrefSize(120, 70);
        clearButton.setOnAction(ActionEvent -> {
            clear();
        });

        addButton.setPrefSize(120, 70);
        addButton.setOnAction(ActionEvent -> {
            if(getValue() != 0) {
                Main.register.addFood(new BasicFood("Man Add", "Man Add", getValue()));
            }
            clear();
        });

        discountButton.setPrefSize(120, 70);
        discountButton.setOnAction(actionEvent -> {
            Main.register.addFood(new BasicFood("DISCOUNT", "DISCOUNT", -1 * getValue()));
            clear();
        });

        hBox.getChildren().addAll(keyPadGrid, functions);

        HBox box = new HBox();
        box.getChildren().addAll(zeroButton, dotButton);

        textField.setFont(new Font(20));

        vBox.getChildren().addAll(textField, hBox, box, feedBack);


        pane.getChildren().add(vBox);
    }
    public double getValue(){
        return  Double.parseDouble(textField.getText());
    }

    public void clear(){
        textField.setText("0");
    }

}
