package gui;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GUI extends Application {

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
            funcCol.getChildren().add(new Button("button " + i));
        }

        //creates the gridPane that contains the rest of the UI
        GridPane gridPane = new GridPane();

        GridPane items = new GridPane();
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 2; j++){
                items.add(new Button((i * j) + "" ),j , i);
            }
        }
        //items.add(new Button("Test"), 0 , 0);

        VBox keyPad = new VBox();
        GridPane keyPadKeys = new GridPane();
        Label keyPadValue = new Label("0123456789.99");
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                keyPadKeys.add(new Button("1"), j , i + 1 );
            }
        }
        keyPad.getChildren().addAll(keyPadValue, keyPadKeys);

        BorderPane itemView = new BorderPane();
        HBox voidRMV = new HBox();
        voidRMV.getChildren().addAll(new Button("Void Trans"), new Button("Remove Item"));
        itemView.setTop(voidRMV);
        itemView.setBottom(new ScrollPane());

        BorderPane totalCashCreditView = new BorderPane();
        totalCashCreditView.setCenter(new Label("this is temp"));

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
