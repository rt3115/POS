package gui;

import common.AdjustableFood;
import common.BasicFood;
import common.Item;
import common.Topping;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import main.Register;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class GUIFinal extends Application {

    private String genStyle = "" +
            "-fx-border-style: none;" +
            "-fx-border-width: 0px;" +
            "-fx-border-color: black;" +
            "-fx-border-radius: 0;" +
            "-fx-background-radius: 0;";

    private String mainBackground = "" +
            "-fx-background-color: #bed9f4;" +
            "-fx-padding: 5;" +
            "-fx-spacing: 10;";

    private String regionStyle = "" +
            "-fx-background-color: #e4e2e2;" +
            "-fx-padding: 5;" +
            "-fx-spacing: 5;" +
            "-fx-background-insets: 1.5 1.5 1.5 1.5;";

    private GridPane mainGrid;
    private Pane mainPane;

    private Label currValue;
    private String currValueString = "";

    private VBox scrollViewContent = new VBox();

    private Label total;
    private Label amountEntered;
    private Label change;

    private Button doneButton;

    private ArrayList<ToggleButton> toppingButton = new ArrayList<>();
    private ArrayList<ToggleButton> sideButtons = new ArrayList<>();

    private Register register;

    @Override
    public void init() throws Exception {
        super.init();

        register = new Register();

    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    @Override
    public void start(Stage stage) throws Exception {

        HBox mainSpace = new HBox();
        mainSpace.setStyle(mainBackground);

        //Function Col
        VBox functionsList = new VBox();
        Button b2 = new Button("View/Add Items");
        {
            Button changeLogin = new Button("Change Logg In");

            Button refreshUI = new Button("ROOT:REFRESH UI");
            refreshUI.setOnAction(ActionEvent -> {
                mainSpace.getChildren().remove(0, 1);
                try {
                    this.start(stage);
                }catch (Exception ex){
                    System.err.println("RAAAA ERROR");
                }
            });

            functionsList.getChildren().addAll( changeLogin ,b2,refreshUI);
        }

        //Register Area Anchor pane
        mainGrid = new GridPane();

        //Item View
        AnchorPane itemViewRegion = new AnchorPane();
        itemViewRegion.setStyle(regionStyle);

        ScrollPane itemView = new ScrollPane();

        GridPane itemViewGrid = new GridPane();

        itemView.setContent(itemViewGrid);

        itemViewGrid.setStyle("" +
                "-fx-hgap: 2;" +
                "-fx-vgap: 2");
        //Displaying the Item Buttons
        { //adding some test buttons
            for(int y = 0; y < 10; y++){
                for(int x = 0; x < 3; x++){
                    if(register.getFoods().size() > (y*3) + x) {
                        int temp = (y*3) + x;
                        Item item = register.getFoods().get(temp);
                        System.err.println(item.getId() + " : " + register.getFoods().get(temp).getId());
                        Button b1 = new Button(item.getDplName());
                        b1.setStyle(genStyle);
                        b1.setPrefSize(120, 60);

                        b1.setOnAction(ActionEvent -> {
                            if(register.getFoods().get(temp) instanceof AdjustableFood)
                                addItem(new AdjustableFood((AdjustableFood)register.getFoods().get(temp)));
                            else
                                addItem(new BasicFood((BasicFood)register.getFoods().get(temp)));
                            //refreshTransList();
                        });

                        itemViewGrid.add(b1, x ,y);
                    }
                }
            }
        }

        //Sides
        ScrollPane sidesView = new ScrollPane();

        GridPane sidesViewGrid = new GridPane();

        sidesView.setContent(sidesViewGrid);

        {
            for(int y = 0; y < 10; y++){
                for(int x = 0; x < 3; x++) {
                    if (register.toppings.size() > (y * 3) + x) {
                        int temp = (y * 3) + x;
                        Item item = register.toppings.get(temp);
                        ToggleButton b1 = new ToggleButton(item.getName());
                        b1.setStyle(genStyle);
                        b1.setPrefSize(120, 60);

                        b1.setOnAction(ActionEvent -> {
                            Topping toppingTemp = new Topping((Topping) item);
                            if (!b1.isSelected()) {
                                BasicFood food = (BasicFood) register.getLast();
                                toppingTemp.setAmount(Topping.AMOUNT.NO);
                                food.addSide(toppingTemp);
                                refreshTransList();
                            } else {
                                BasicFood food = (BasicFood) register.getLast();
                                toppingTemp.setAmount(Topping.AMOUNT.SIDE);
                                food.addSide(toppingTemp);
                                refreshTransList();
                            }
                        });

                        sidesViewGrid.add(b1, x, y);
                        sideButtons.add(b1);
                    }
                }
            }
        }
        sidesView.setVisible(false);

        //Toppings
        ScrollPane toppingsView = new ScrollPane();

        GridPane toppingsViewGrid = new GridPane();

        toppingsView.setContent(toppingsViewGrid);
        toppingsViewGrid.setStyle("" +
                "-fx-hgap: 2;" +
                "-fx-vgap: 2;");
        //displaying the toppings
        {
            for(int y = 0; y < 6; y++){
                for(int x = 0; x < 3; x++){
                    if(register.toppings.size() > (y*3) + x){
                        int temp = (y*3) + x;
                        Item item = register.toppings.get(temp);
                        ToggleButton b1 = new ToggleButton(item.getDplName());
                        b1.setStyle(genStyle);
                        b1.setPrefSize(120, 60);


                        b1.setOnAction(ActionEvent -> {
                            Topping toppingTemp = new Topping((Topping)item);
                            if(!b1.isSelected()){
                                AdjustableFood food = (AdjustableFood) register.getLast();
                                toppingTemp.setAmount(Topping.AMOUNT.NO);
                                food.addTopping(toppingTemp);
                                refreshTransList();
                            }else {
                                AdjustableFood food = (AdjustableFood) register.getLast();
                                toppingTemp.setAmount(Topping.AMOUNT.NORMAL);
                                food.addTopping(toppingTemp);
                                refreshTransList();
                            }
                        });



                        toppingButton.add(b1);
                        toppingsViewGrid.add(b1, x ,y);
                    }
                }
            }
        }
        toppingsView.setVisible(false);

        HBox adjustSideArea = new HBox();
        //sides and adjustOrder Buttons
        {
            Button adjustOrderButton = new Button();
            adjustOrderButton.setStyle(genStyle);
            adjustOrderButton.setText("Adjust Order");
            adjustOrderButton.setPrefSize(120, 60);
            adjustOrderButton.setOnAction(ActionEvent -> {
                if(register.getLast() != null && register.getLast() instanceof AdjustableFood) {
                    refreshToppingToggleButtons((AdjustableFood)register.getLast());
                    itemView.setVisible(false);
                    toppingsView.setVisible(true);
                    doneButton.setVisible(true);
                }
            });

            Button addSideButton = new Button();
            addSideButton.setStyle(genStyle);
            addSideButton.setText("Add Side");
            addSideButton.setPrefSize(120, 60);
            addSideButton.setOnAction(ActionEvent -> {
                if(register.getLast() != null){
                    sidesView.setVisible(true);
                    itemView.setVisible(false);
                    doneButton.setVisible(true);
                }
            });

            adjustSideArea.getChildren().addAll(adjustOrderButton, addSideButton);
            adjustSideArea.setStyle("" +
                    "-fx-spacing: 5;");

            doneButton = new Button("Done");
            doneButton.setStyle(genStyle);
            doneButton.setPrefSize(120, 60);
            doneButton.setVisible(false);
            doneButton.setOnAction(ActionEvent -> {
                itemView.setVisible(true);
                toppingsView.setVisible(false);
                sidesView.setVisible(false);
                doneButton.setVisible(false);
            });

            adjustSideArea.getChildren().add(doneButton);
        }

        HBox sortItemArea = new HBox();
        {
            Button allItemsButton = new Button("All");
            allItemsButton.setPrefSize(50, 40);
            allItemsButton.setStyle(genStyle);
            Button drinksButton = new Button("Drinks");
            drinksButton.setPrefSize(70, 40);
            drinksButton.setStyle(genStyle);
            Button deliButton = new Button("Deli");
            deliButton.setPrefSize(70, 40);
            deliButton.setStyle(genStyle);
            Button foodButton = new Button("Food");
            foodButton.setPrefSize(70, 40);
            foodButton.setStyle(genStyle);

            sortItemArea.getChildren().addAll(allItemsButton, drinksButton, deliButton, foodButton);
        }



        itemViewRegion.getChildren().addAll(itemView, toppingsView, sidesView, adjustSideArea, sortItemArea);
        //setting the anchors
        {
            AnchorPane.setBottomAnchor(adjustSideArea, 1.00);
            AnchorPane.setRightAnchor(adjustSideArea, 1.00);
            AnchorPane.setLeftAnchor(adjustSideArea, 1.00);

            AnchorPane.setLeftAnchor(itemView, 1.00);
            AnchorPane.setRightAnchor(itemView, 1.00);
            AnchorPane.setTopAnchor(itemView, 45.00);

            AnchorPane.setLeftAnchor(toppingsView, 1.00);
            AnchorPane.setRightAnchor(toppingsView, 1.00);
            AnchorPane.setTopAnchor(toppingsView, 45.00);

            AnchorPane.setLeftAnchor(sidesView, 1.00);
            AnchorPane.setRightAnchor(sidesView, 1.00);
            AnchorPane.setTopAnchor(sidesView, 45.00);

            AnchorPane.setLeftAnchor(sortItemArea, 1.00);
            AnchorPane.setRightAnchor(sortItemArea, 1.00);
            AnchorPane.setTopAnchor(sortItemArea, 1.00);
        }

        //Keypad
        VBox keyPadRegion = new VBox();
        keyPadRegion.setStyle(regionStyle);
        {
            currValue = new Label();
            currValue.setPrefWidth(240);
            currValue.setStyle("" +
                    "-fx-background-color: white;");
            currValue.setFont(Font.font(20));

            GridPane keyPadButtons = new GridPane();
            keyPadButtons.setStyle("" +
                    "-fx-hgap: 2;" +
                    "-fx-vgap: 2;");
            for(int x = 0; x < 3; x++){
                for(int y = 0; y < 3; y++){
                    Button b1 = new Button("" + ((x*3) + y + 1));
                    int temp = (x*3) + y + 1;
                    b1.setOnAction(ActionEvent -> {
                        currValueString += temp;
                        refreshKeyPad();
                    });
                    b1.setStyle(genStyle);
                    b1.setPrefSize(70 , 70);
                    keyPadButtons.add(b1, y ,x);
                }
            }
            Button clearButton = new Button("Clear");
            clearButton.setStyle(genStyle);
            clearButton.setPrefSize(80, 70);
            clearButton.setOnAction(ActionEvent -> {
                currValueString = "";
                refreshKeyPad();
            });
            Button addButton = new Button("Add");
            addButton.setPrefSize(80, 70);
            addButton.setStyle(genStyle);
            addButton.setOnAction(ActionEvent -> {
                addItem(new BasicFood("Item Manual", "Item Manual", Double.parseDouble(currValueString)));
                currValueString = "";
                refreshKeyPad();
            });

            keyPadButtons.add(clearButton, 3, 0);
            keyPadButtons.add(addButton, 3 , 1);

            Button zeroButton = new Button("0");
            zeroButton.setStyle(genStyle);
            zeroButton.setPrefSize(142, 50);
            zeroButton.setOnAction(ActionEvent -> {
                currValueString += "0";
                refreshKeyPad();
            });
            Button dotButton = new Button(".");
            dotButton.setStyle(genStyle);
            dotButton.setPrefSize(70, 50);
            dotButton.setOnAction(ActionEvent -> {
                currValueString += ".";
                refreshKeyPad();
            });
            HBox keyPadZeroAndDot = new HBox();
            keyPadZeroAndDot.setStyle("" +
                    "-fx-spacing: 2");
            keyPadZeroAndDot.getChildren().addAll(zeroButton, dotButton);

            keyPadRegion.getChildren().addAll(currValue, keyPadButtons, keyPadZeroAndDot);
        }

        //Trans View
        VBox transViewRegion = new VBox();
        transViewRegion.setStyle(regionStyle);
        {
            HBox voidRemoveArea = new HBox();
            Button voidTrans = new Button("Void Trans");
            voidTrans.setStyle(genStyle);
            voidTrans.setPrefSize(145, 30);
            voidTrans.setOnAction(ActionEvent -> {
                voidTransaction();
            });
            Button removeItem = new Button("Remove Item");
            removeItem.setStyle(genStyle);
            removeItem.setPrefSize(145, 30);
            removeItem.setOnAction(ActionEvent -> {
                removeLast();
            });
            voidRemoveArea.getChildren().addAll(voidTrans, removeItem);

            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setPrefViewportHeight(440);
            scrollPane.setPrefViewportWidth(280);
            scrollPane.setContent(scrollViewContent);

            transViewRegion.getChildren().addAll(voidRemoveArea, scrollPane);
        }

        //Cash or credit area
        VBox paymentRegion = new VBox();
        paymentRegion.setStyle(regionStyle);
        {
            total = new Label("Total: ");
            total.setStyle("" +
                    "-fx-background-color: white;" +
                    "-fx-border-width: 1px;" +
                    "-fx-border-color: black");
            total.setPrefWidth(280);
            total.setFont(Font.font(30));

            amountEntered = new Label("Amount Entered: " );
            amountEntered.setStyle("" +
                    "-fx-background-color: white;" +
                    "-fx-border-width: 1px;" +
                    "-fx-border-color: black;");
            amountEntered.setPrefWidth(280);
            amountEntered.setFont(Font.font(15));
            change = new Label("Change: ");
            change.setStyle("" +
                    "-fx-background-color: white;" +
                    "-fx-border-width: 1px;" +
                    "-fx-border-color: black;");
            change.setPrefWidth(280);
            change.setFont(Font.font(20));

            HBox methodsArea = new HBox();
            Button cashButton = new Button("Cash");
            cashButton.setStyle(genStyle);
            cashButton.setPrefSize(140, 120);
            cashButton.setFont(Font.font(40));
            cashButton.setOnAction(ActionEvent -> {
                if(currValueString != "") {
                    register.cashout((int) (Double.parseDouble(currValueString) * 100));
                }else{
                    register.cashout(0);
                }
                currValueString = "";
                refreshKeyPad();
                refreshTotal();
            });
            Button creditButton = new Button("Credit");
            creditButton.setStyle(genStyle);
            creditButton.setPrefSize(140 ,120);
            creditButton.setFont(Font.font(34));
            methodsArea.getChildren().addAll(cashButton, creditButton);

            paymentRegion.getChildren().addAll(total, amountEntered, change, methodsArea);
        }

        //setting the grid
        mainGrid.add(itemViewRegion, 0, 0);
        mainGrid.add(keyPadRegion, 0 ,1);
        mainGrid.add(transViewRegion, 1, 0);
        mainGrid.add(paymentRegion, 1, 1);


        //creating items UI
        //ahh this going to be such a large area ree
        Pane newItemUI = new Pane();
        {
            b2.setOnAction(ActionEvent -> {
                changeView(newItemUI);
            });

            AnchorPane itemMainRegion = new AnchorPane();
            itemMainRegion.setStyle(regionStyle);

            AnchorPane newFoodArea = new AnchorPane();
            newFoodArea.setVisible(false);

            AnchorPane newToppngArea = new AnchorPane();
            newToppngArea.setVisible(false);

            Label lb1 = new Label("Item Name: ");
            TextField itemName = new TextField();
            itemName.setStyle("" +
                    "-fx-background-color: white;");
            itemName.setPrefWidth(300);

            Label lb2 = new Label("Item Description: ");
            TextField itemDesc = new TextField();
            itemDesc.setStyle("" +
                    "-fx-background-color: white");
            itemDesc.setPrefWidth(600);

            Label dplNameLabel = new Label("Display Name");
            TextField dplName = new TextField();
            dplName.setStyle("" +
                    "-fx-background-color: white");
            dplName.setPrefWidth(200);


            Button doneButton = new Button("Add New Item");
            doneButton.setStyle(genStyle);

            HBox foodTypeButtons = new HBox();
            ToggleButton isBasicFoodButton = new ToggleButton("Food");
            ToggleButton isTopping = new ToggleButton("Topping");
            //food type buttons
            {
                isBasicFoodButton.setStyle(genStyle);

                isTopping.setStyle(genStyle);

                isBasicFoodButton.setOnAction(ActionEvent -> {
                    //isBasicFoodButton.setSelected(true);
                    newFoodArea.setVisible(true);
                    newToppngArea.setVisible(false);
                    if (isBasicFoodButton.isSelected() && isTopping.isSelected()) {
                        isTopping.setSelected(false);

                    }

                });

                isTopping.setOnAction(ActionEvent -> {
                    //isTopping.setSelected(true);
                    newToppngArea.setVisible(true);
                    newFoodArea.setVisible(false);
                    if (isTopping.isSelected() && isBasicFoodButton.isSelected()) {
                        isBasicFoodButton.setSelected(false);

                    }

                });

                foodTypeButtons.getChildren().addAll(isBasicFoodButton, isTopping);
            }

            //if its a Food
            TextField price = new TextField();
            ToggleButton isAdjustableFood = new ToggleButton("Adjustable");
            List<Item> tempAdd = new LinkedList<>();
            {

                price.setStyle("" +
                        "-fx-background-color: white;");
                price.setPrefWidth(100);
                Label priceLabel = new Label("Price");


                ScrollPane toppingsListScrollView = new ScrollPane();
                GridPane toppingsList = new GridPane();
                toppingsListScrollView.setVisible(false);

                for(int y = 0; y < 6; y++){
                    for(int x = 0; x < 5; x++){
                        if(register.toppings.size() > (y*5) + x) {
                            int temp = (y * 5) + x;
                            Item item = register.toppings.get(temp);
                            ToggleButton b1 = new ToggleButton(item.getName());
                            b1.setStyle(genStyle);
                            b1.setPrefSize(120, 60);

                            b1.setOnAction(ActionEvent -> {
                                if(b1.isSelected()){
                                    tempAdd.add(item);
                                }else{
                                    tempAdd.remove(item);
                                }
                            });

                            toppingsList.add(b1, x, y);
                        }
                    }
                }

                isAdjustableFood.setOnAction(ActionEvent -> {
                    if(isAdjustableFood.isSelected()){
                        toppingsListScrollView.setVisible(true);
                    }else{
                        toppingsListScrollView.setVisible(false);
                    }
                });

                toppingsListScrollView.setContent(toppingsList);

                //setting anchors
                {
                    AnchorPane.setTopAnchor(isAdjustableFood, 1.00);
                    AnchorPane.setLeftAnchor(isAdjustableFood, 1.00);

                    AnchorPane.setTopAnchor(price, 30.00);
                    AnchorPane.setLeftAnchor(price, 60.00);

                    AnchorPane.setTopAnchor(priceLabel, 30.00);
                    AnchorPane.setLeftAnchor(priceLabel, 1.00);

                    AnchorPane.setLeftAnchor(toppingsListScrollView, 1.00);
                    AnchorPane.setTopAnchor(toppingsListScrollView, 60.00);
                }
                newFoodArea.getChildren().addAll(isAdjustableFood, price, priceLabel, toppingsListScrollView);


            }

            //if its a Topping

            ToggleButton isToppingButton = new ToggleButton("is Topping");
            ToggleButton isSideButton = new ToggleButton("is Side");
            TextField price2 = new TextField();
            TextField extraPrice = new TextField();
            {
                isToppingButton.setStyle(genStyle);
                isToppingButton.setPrefWidth(80);

                isSideButton.setStyle(genStyle);
                isSideButton.setPrefWidth(60);

                Label priceLabel = new Label("Price");
                Label extraPriceLabel = new Label("Extra Price");

                price2.setStyle("" +
                        "-fx-background-color: white;");
                price2.setPrefWidth(40);

                extraPrice.setStyle("" +
                        "-fx-background-color: white;");
                extraPrice.setPrefWidth(40);

                //setting the anchors
                {
                    AnchorPane.setTopAnchor(isToppingButton, 1.00);
                    AnchorPane.setLeftAnchor(isToppingButton, 1.00);

                    AnchorPane.setTopAnchor(isSideButton, 1.00);
                    AnchorPane.setLeftAnchor(isSideButton, 81.00);

                    AnchorPane.setTopAnchor(price2, 30.00);
                    AnchorPane.setLeftAnchor(price2, 30.00);

                    AnchorPane.setTopAnchor(priceLabel, 30.00);
                    AnchorPane.setLeftAnchor(priceLabel, 1.00);

                    AnchorPane.setTopAnchor(extraPrice, 30.000);
                    AnchorPane.setLeftAnchor(extraPrice, 140.00);

                    AnchorPane.setTopAnchor(extraPriceLabel, 30.00);
                    AnchorPane.setLeftAnchor(extraPriceLabel, 80.00);
                }

                newToppngArea.getChildren().addAll(price2, priceLabel, extraPrice, extraPriceLabel, isSideButton, isToppingButton);
            }

            //setting the anchors
            {
                AnchorPane.setTopAnchor(itemName, 1.0);
                AnchorPane.setLeftAnchor(itemName, 100.0);

                AnchorPane.setLeftAnchor(lb1, 1.0);
                AnchorPane.setTopAnchor(lb1, 1.0);

                AnchorPane.setTopAnchor(dplNameLabel, 1.00);
                AnchorPane.setLeftAnchor(dplNameLabel, 410.0);

                AnchorPane.setTopAnchor(dplName, 1.00);
                AnchorPane.setLeftAnchor(dplName, 500.00);

                AnchorPane.setTopAnchor(lb2, 30.0);
                AnchorPane.setLeftAnchor(lb2, 1.0);

                AnchorPane.setTopAnchor(itemDesc, 30.0);
                AnchorPane.setLeftAnchor(itemDesc, 100.0);

                AnchorPane.setTopAnchor(foodTypeButtons, 60.0);
                AnchorPane.setLeftAnchor(foodTypeButtons, 1.00);

                AnchorPane.setTopAnchor(newFoodArea, 90.00);
                AnchorPane.setLeftAnchor(newFoodArea, 1.00);

                AnchorPane.setBottomAnchor(doneButton, 1.00);
                AnchorPane.setRightAnchor(doneButton, 1.00);

                AnchorPane.setTopAnchor(newToppngArea, 90.0);
                AnchorPane.setLeftAnchor(newToppngArea, 1.00);
            }

            //done buttons
            doneButton.setOnAction(ActionEvent -> {
                //not final
                if(!isTopping.isSelected()) {
                    if (!isAdjustableFood.isSelected()) {
                        String tName = itemName.getText();
                        String tDesc = itemDesc.getText();
                        Double tPrice = Double.parseDouble(price.getText());
                        register.getFoods().add(new BasicFood(tName, dplName.getText(), tPrice));
                    } else {
                        String tName = itemName.getText();
                        String tDesc = itemDesc.getText();
                        Double tPrice = Double.parseDouble(price.getText());
                        if (tempAdd.isEmpty()) {
                            register.getFoods().add(new AdjustableFood(tName, dplName.getText(), tPrice));
                        } else {
                            register.getFoods().add(new AdjustableFood(tName, dplName.getText(), tPrice, tempAdd));
                            tempAdd.removeAll(tempAdd); //this may break everything
                        }
                    }
                    itemName.setText("");
                    itemDesc.setText("");
                    price.setText("");
                    isBasicFoodButton.setSelected(false);
                    isAdjustableFood.setSelected(false);
                }else{
                    if(isToppingButton.isSelected() || isSideButton.isSelected()){
                        String tName = itemName.getText();
                        String tDesc = itemDesc.getText();
                        Double tPrice = Double.parseDouble(price2.getText());
                        register.toppings.add(new Topping(tName, dplName.getText(), tPrice, isToppingButton.isSelected(), isSideButton.isSelected()));
                        itemName.setText("");
                        itemDesc.setText("");
                        price2.setText("");
                        isTopping.setSelected(false);
                        isSideButton.setSelected(false);
                        isToppingButton.setSelected(false);
                    }
                }
            });

            itemMainRegion.getChildren().addAll(itemName, lb1, itemDesc, lb2, dplNameLabel, dplName,foodTypeButtons, newFoodArea, doneButton, newToppngArea);

            newItemUI.getChildren().add(itemMainRegion);
            newItemUI.setVisible(false);
        }

        mainPane = new Pane();
        mainPane.getChildren().addAll(mainGrid, newItemUI);

        mainSpace.getChildren().addAll(functionsList, mainPane);
        Scene scene = new Scene(mainSpace);
        stage.setTitle("POS ***Logged in as: ROOT/OWNER***");
        stage.setScene(scene);
        stage.show();
    }

    public void changeView(Node node){
        if(node.isVisible()){
            node.setVisible(false);
            mainGrid.setVisible(true);
        }else{
            mainGrid.setVisible(false);
            node.setVisible(true);
        }
    }

    //functions for updating the GUI

    public void removeItem(int index){

    }

    public void removeLast(){
        register.removeLast();
        refreshTransList();
    }

    public void refreshTotal() {
        total.setText("Total: " + register.getTotal() / 100.00);
        amountEntered.setText("Amount Entered: " + register.getEntered()/100.00);
        change.setText("Change: " + Math.abs(register.getChange()/100.00));
    }

    public void refreshKeyPad(){
        currValue.setText(currValueString);
    }

    public void refreshTransList(){
        List<Item> list = register.getList();
//        scrollViewContent.getChildren().removeAll();
        if(scrollViewContent.getChildren().size() != 0){
            for(int i = scrollViewContent.getChildren().size()-1; i >= 0; i--){
                scrollViewContent.getChildren().remove(i);
            }
        }

        System.err.println("----------");
        for(Item item : list){
            Label lb = new Label(item.toString());
            lb.setFont(Font.font(20));
            scrollViewContent.getChildren().add(lb);
            System.err.println(item.getId());
        }
        System.err.println("------------");
        refreshTotal();
    }

    public void refreshToppingToggleButtons(AdjustableFood item){
        //use this function to update the buttons so a new function is not needed
        for (ToggleButton button : toppingButton){
            for(Item top : item.getNormalToppings()){
                System.err.println(top.toString());
                if(top.getName().equals(button.getText()) || top.getDplName().equals(button.getText())){
                    button.setSelected(true);
                }
            }
        }
        if(toppingButton.size() < register.toppings.size()){
            System.err.println("Missing Toppings");
        }
    }

    public void refreshSidesToggleButtons(BasicFood item){

    }

    public void voidTransaction(){
        register.voidTransaction();
        refreshTransList();
    }

    public void addItem(Item item){
        register.addFood(item);
        refreshTransList();
    }



    public static void launch(){
        Application.launch();
    }


}
