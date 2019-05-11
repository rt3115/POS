package gui;

import javafx.application.Application;
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

    //fake stuff
    //makes it kinda work but not really
    double total = 0;
    String keyValue; //yes I know its a string
    double keyValueDouble = 0;
    ScrollPane itemList;
    VBox itemContent;
    Label keyPadValue;
    Label totalNode;

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
        for (int i = 0; i < 10; i++) {
            Button b1;
            switch (i) {
                case 0:
                    b1 = new Button("Close");
                    break;
                case 1:
                    b1 = new Button("Print Last Receipt");
                    break;
                case 2:
                    b1 = new Button("Print Receipt by Id");
                    break;
                case 3:
                    b1 = new Button("Print Totals");
                    break;
                case 4:
                    b1 = new Button("Void Trans by ID");
                    break;
                case 5:
                    b1 = new Button("No Sale");
                    break;
                case 6:
                    b1 = new Button("Manager Functions");
                    break;
                default:
                    b1 = new Button("Button " + i);
            }

            b1.setStyle(genStyle);
            b1.setPrefSize(150, 60);
            funcCol.getChildren().add(b1);
        }

        //creates the gridPane that contains the rest of the UI
        GridPane gridPane = new GridPane();

        GridPane items = new GridPane();
        items.setStyle(genStyle);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                Button b1 = new Button((i * j) + "");
                b1.setPrefSize(120, 90);
                b1.setText("Pizza");
                b1.setFont(Font.font(30));
                b1.setOnAction(ActionEvent -> {
                    addItem("1 PIZZA :        2.50");
                    total += 2.5;
                    refresgTotal();
                });

                items.add(b1, j, i);
            }
        }
        //items.add(new Button("Test"), 0 , 0);

        VBox keyPad = new VBox();
        keyPad.setStyle(genStyle);
        GridPane keyPadKeys = new GridPane();
        keyPadValue = new Label();
        keyPadValue.setStyle(genStyle);
        keyPadValue.setPrefWidth(120 * 3);
        keyPadValue.setFont(Font.font(40));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button b1 = new Button("" + ((i * 3) + j + 1));
                int x = (i * 3) + j + 1;
                b1.setOnAction(ActionEvent -> {
                    keyValue += x;
                    refreshkeyPad();
                });

                b1.setStyle(genStyle);
                b1.setPrefSize(80, 80);
                keyPadKeys.add(b1, j, i + 1);
            }
        }
        Button clear = new Button("Clear");
        clear.setOnAction(ActionEvent -> {
            keyValue = "";
            refreshkeyPad();
        });
        clear.setPrefSize(120, 80);
        keyPadKeys.add(clear, 4, 1);

        Button addMan = new Button("Add");
        addMan.setOnAction(ActionEvent -> {
            try {
                total += Double.parseDouble(keyValue);
                addItem("Item Manual: " + keyValue);
                refresgTotal();
                keyValue = "";
                refreshkeyPad();
            } catch (Exception e) {
                System.err.println("caught error in keyValue");
            }

        });
        addMan.setPrefSize(120, 80);
        keyPadKeys.add(addMan, 4, 2);

        Button totalButton = new Button("Total");
        totalButton.setOnAction(ActionEvent -> {
            try {
                total -= Double.parseDouble(keyValue);
                keyValue = "";
                refreshkeyPad();
                refresgTotal();
            } catch (Exception e) {
                System.err.println("There was an error in totalButton, ignoring it and cleaning everything");
                keyValue = "";
                total = 0;
                cleanTransNoUpdate();
                refresgTotal();
                refreshkeyPad();
            }
        });
        totalButton.setPrefSize(120, 80);
        keyPadKeys.add(totalButton, 4, 3);

        HBox extraButtons = new HBox();

        Button dotButton = new Button(".");
        dotButton.setOnAction(ActionEvent -> {
            keyValue += ".";
            refreshkeyPad();
        });
        dotButton.setPrefSize(80, 70);
        dotButton.setFont(Font.font(20));

        Button zeroButton = new Button("0");
        zeroButton.setOnAction(ActionEvent -> {
            keyValue += 0;
            refreshkeyPad();
        });
        zeroButton.setPrefSize(160, 70);
        zeroButton.setFont(Font.font(20));

        extraButtons.getChildren().addAll(zeroButton, dotButton);

        keyPad.getChildren().addAll(keyPadValue, keyPadKeys, extraButtons);

        BorderPane itemView = new BorderPane();
        itemView.setStyle(genStyle);
        HBox voidRMV = new HBox();
        Button removeItem = new Button("remove Item");
        removeItem.setPrefSize(200, 30);
        removeItem.setFont(Font.font(25));
        removeItem.setOnAction(ActionEvent -> {
            removeItem();
        });
        Button voidTrans = new Button("Void Trans");
        voidTrans.setPrefSize(200, 30);
        voidTrans.setFont(Font.font(25));
        voidTrans.setOnAction(ActionEvent -> {
            clearTrans();
        });
        voidRMV.getChildren().addAll(voidTrans, removeItem);
        itemView.setTop(voidRMV);
        itemList = new ScrollPane();
        itemList.setPrefViewportHeight(450);
        itemContent = new VBox();
        itemList.setContent(itemContent);
        itemView.setBottom(itemList);

        VBox totalCashCreditView = new VBox();
        totalCashCreditView.setStyle(genStyle);
        totalNode = new Label("Total: " + 99.99);

        totalNode.setPrefWidth(400);
        totalNode.setStyle(genStyle);
        totalNode.setFont(Font.font(60));
        Button cashButton = new Button("Cash");
        cashButton.setPrefSize(150, 80);
        cashButton.setStyle("-fx-background-color:lightGreen");
        cashButton.setFont(Font.font(30));
        Button creditButton = new Button("Credit");
        creditButton.setStyle("-fx-background-color:pink");
        creditButton.setPrefSize(150, 80);
        creditButton.setFont(Font.font(30));
        totalCashCreditView.getChildren().addAll(totalNode, cashButton, creditButton);

        gridPane.add(items, 0, 0);
        gridPane.add(keyPad, 0, 1);
        gridPane.add(itemView, 1, 0);
        gridPane.add(totalCashCreditView, 1, 1);

        hBox.getChildren().addAll(funcCol, gridPane);
        Scene main = new Scene(hBox);
        stage.setScene(main);
        stage.setTitle("POS");
        stage.show();

        refreshkeyPad();
        refresgTotal();
        keyValue = "";
    }

    public void refreshkeyPad() {
        keyPadValue.setText(keyValue);
    }

    public void refresgTotal() {
        if (total < 0) {
            totalNode.setText("Change: " + Math.abs(total));
            cleanTransNoUpdate();
            return;
        }
        totalNode.setText("Total: " + total);
    }

    public void addItem(String in) {
        Label lb = new Label(in);
        lb.setStyle(genStyle);
        lb.setFont(Font.font(20));
        lb.setPrefWidth(390);
        itemContent.getChildren().add(lb);
    }

    public void clearTrans() {
        int items = itemContent.getChildren().size();
        if (items == 0)
            return;
        for (int i = items - 1; i >= 0; i--) {
            itemContent.getChildren().remove(i);
        }
        total = 0;
        refresgTotal();
    }

    public void cleanTransNoUpdate() {
        int items = itemContent.getChildren().size();
        if (items == 0)
            return;
        for (int i = items - 1; i >= 0; i--) {
            itemContent.getChildren().remove(i);
        }
        total = 0;
    }

    public void removeItem() {
        if (itemContent.getChildren().size() == 0)
            return;
        itemContent.getChildren().remove(itemContent.getChildren().size() - 1);
    }

    public static void main(String[] args) {
        Application.launch();
    }

    public static void launch(){
        Application.launch();
    }

}
