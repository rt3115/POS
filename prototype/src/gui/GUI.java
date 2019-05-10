package gui;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUI extends Application {

    private String genStyle = "" +
            "-fx-border-style: none;" +
            "-fx-border-width: 1px;" +
            "-fx-border-color: black;";

    public GUI() {
        super();
    }

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    @Override
    public void start(Stage stage) throws Exception {

        //creates the main hbox that contains the entire UI
        HBox hBox = new HBox();

        //creates the vbox for the functions
        VBox funcCol = new VBox();
        for(int i = 0; i < 10; i++){
            Button  b1 = new Button("Button " + i);
            b1.setStyle(genStyle);
            b1.setPrefSize(150, 60);
            funcCol.getChildren().add(b1);
        }

        //creates the gridPane that contains the rest of the UI
        GridPane gridPane = new GridPane();

        GridPane items = new GridPane();
        items.setStyle(genStyle);
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 3; j++){
                Button b1 = new Button((i * j) + "");
                b1.setPrefSize(120, 70);
                items.add( b1 ,j , i);
            }
        }
        //items.add(new Button("Test"), 0 , 0);

        VBox keyPad = new VBox();
        keyPad.setStyle(genStyle);
        GridPane keyPadKeys = new GridPane();
        Label keyPadValue = new Label("0123456789.99");
        keyPadValue.setStyle(genStyle);
        keyPadValue.setPrefWidth(120 * 3);
        keyPadValue.setFont(Font.font(20));
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                Button b1 = new Button("" + i + 1);
                b1.setStyle(genStyle);
                b1.setPrefSize(80, 80);
                keyPadKeys.add(b1, j , i + 1 );
            }
        }
        Button clear = new Button("Clear");
        clear.setPrefSize(120, 80);
        keyPadKeys.add(clear, 4, 1);

        Button addMan = new Button("Add");
        addMan.setPrefSize(120, 80);
        keyPadKeys.add(addMan, 4, 2);



        keyPad.getChildren().addAll(keyPadValue, keyPadKeys);

        BorderPane itemView = new BorderPane();
        itemView.setStyle(genStyle);
        HBox voidRMV = new HBox();
        voidRMV.getChildren().addAll(new Button("Void Trans"), new Button("Remove Item"));
        itemView.setTop(voidRMV);
        itemView.setBottom(new ScrollPane());

        VBox totalCashCreditView = new VBox();
        totalCashCreditView.setStyle(genStyle);
        Label total = new Label("Total: " + 99.99);
        total.setPrefWidth(150);
        total.setStyle(genStyle);
        Button cashButton = new Button("Cash");
        cashButton.setPrefSize(150, 80);
        cashButton.setStyle("-fx-background-color:lightGreen");
        cashButton.setFont(Font.font(30));
        Button creditButton = new Button("Credit");
        creditButton.setStyle("-fx-background-color:pink");
        creditButton.setPrefSize(150, 80);
        creditButton.setFont(Font.font(30));
        totalCashCreditView.getChildren().addAll(total, cashButton, creditButton);

        gridPane.add(items, 0, 0);
        gridPane.add(keyPad, 0, 1);
        gridPane.add(itemView, 1 ,0);
        gridPane.add(totalCashCreditView, 1, 1);

        hBox.getChildren().addAll(funcCol, gridPane);
        Scene main = new Scene(hBox);
        stage.setScene(main);
        stage.setTitle("POS");
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch();
    }

}
