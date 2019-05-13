package gui;

import common.AdjustableFood;
import common.BasicFood;
import common.Item;
import common.Topping;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.security.spec.ECField;
import java.util.LinkedList;
import java.util.List;

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
    List<Item> list = new LinkedList<>();
    Label keyPadValue;
    Label totalNode;
    List<BasicFood> foods = new LinkedList<>();
    List<Topping> toppingsList = new LinkedList<>();
    public String temp = "Empty";

    public GUI() {
        super();
    }

    @Override
    public void init() throws Exception {
        super.init();

        foods.add(new BasicFood("Pizza", 2.5));
        foods.add(new BasicFood("Water", 1));
        foods.add(new AdjustableFood("Plate", 10.00, new Topping("Hot Sauce", .50), new Topping("Onions", .00)));
        foods.add(new BasicFood("Arizona", .99));
        foods.add(new AdjustableFood("Sub", 6.00));
//        foods.add(new TempFood("Breakfast Sandwich", 6, 3.00, false, true));
//        foods.add(new TempFood("Gatorade", 7, 2.00, false, false));
//        foods.add(new TempFood("Soda", 8, 2.00, false, false));
//        foods.add(new TempFood("Doz Wings", 9 , 10.00, false, false));
//

        toppingsList.add(new Topping("Lettuce", .00));
        toppingsList.add(new Topping("Tomato", .00));
        toppingsList.add(new Topping("Hot Sauce", .50, 1.00));
        toppingsList.add(new Topping("Cheese", .00, .50));
//        toppingsList.add(new TempFood("Ex Cheese", 5, .50, true, false));
//        toppingsList.add(new TempFood("Ex Meat", 6, .50, true, false));
//        toppingsList.add(new TempFood("Peppers", 7, .00, true, false));
//        toppingsList.add(new TempFood("Onions", 8, .00, true, false));


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
                case 7:
                    b1 = new Button("Don't Touch!!!");
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

        Pane itemViewArea = new Pane();

        GridPane items = new GridPane();
        GridPane toppings = new GridPane();
        items.setStyle(genStyle);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j <6; j++) {

                Button b1 = new Button();
                int x = (j * 3) + i;
                if(x < foods.size()){
                    b1.setText(foods.get(x).getName());
                    b1.setPrefSize(120 , 90);
//                    b1.setFont(Font.font(20));
                    b1.setOnAction(ActionEvent -> {
                        temp = foods.get(x).toString();
                        total += foods.get(x).getPrice();
                        if(foods.get(x) instanceof AdjustableFood){
                            items.setVisible(false);
                            toppings.setVisible(true);
                        }else{
                            addItem(temp);
                            refresgTotal();
                        }
                    });
                }else{
                    break;
                }

                items.add(b1, i, j);
            }
        }


        items.setStyle(genStyle);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {

                Button b1 = new Button();
                b1.setPrefSize(120, 90);

                int x = (j * 3) + i;

                if(x < toppingsList.size()){
                    b1.setText(toppingsList.get(x).getName());
                    b1.setOnAction(ActionEvent -> {
                        temp += "\n";
                        temp += "           " + toppingsList.get(x).getName() + ":        " + toppingsList.get(x).getPrice();
                        total += toppingsList.get(x).getPrice();
                    });
                }else if(x == toppingsList.size()){
                    //stop adding toppings
                    b1.setOnAction(ActionEvent -> {
                        b1.setText("Done");
                        addItem(temp + "\n Sub Total: N/A");
                        refresgTotal();
                        toppings.setVisible(false);
                        items.setVisible(true);
                    });
                }else{
                    break;
                }

                /*
                b1.setText("Ex Cheese");
                b1.setFont(Font.font(30));
                b1.setOnAction(ActionEvent -> {
                    addItem("1 Sub              6.00" +
                            " \n            Ex Cheese :   .50");
                    total += .50;
                    refresgTotal();
                    toppings.setVisible(false);
                    items.setVisible(true);
                });*/
                toppings.add(b1 , i, j);
            }
        }

        itemViewArea.getChildren().addAll(toppings, items);
        toppings.setVisible(false);
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

//        gridPane.add(items, 0, 0);
        gridPane.add(itemViewArea, 0, 0);
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

    //returns the last item from the list
    public Item getItem(){

    }

    public Item getItem(int index){
        return itemContent.getChildren().get(index);
    }

    public void clearTrans() {

        int items = itemContent.getChildren().size();
        if (items == 0){
            total = 0;
            refresgTotal();
            return;
        }
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

    public static void launch() {
        Application.launch();
    }

}
