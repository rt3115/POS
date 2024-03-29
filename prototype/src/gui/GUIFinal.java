package gui;

import common.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import main.Main;
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

    Stage stage;
    public String title = "POS";

    private GridPane mainGrid;
    private Pane mainPane;

    private Label currValue;
    private String currValueString = "";

    private VBox scrollViewContent = new VBox();

    private Label total;
    private Label amountEntered;
    private Label taxAmount;
    private Label change;

    private Button doneButton;

    private GridPane foodButtons = new GridPane();
    private GridPane sideButtons = new GridPane();
    private GridPane toppingButtons = new GridPane();

    AnchorPane changeLogInPane;
    AnchorPane transactionsPane;
    AnchorPane summaryPane;

    private ArrayList<ToggleButton> toppingButton = new ArrayList<>();
//    private ArrayList<ToggleButton> sideButtons = new ArrayList<>();

    ArrayList<TextField> editFields = new ArrayList<>();

    private Topping.AMOUNT currAmount = Topping.AMOUNT.NORMAL;

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

        //the buttons will be replaced with a loop that goes through and adds all the functions based on the functions list in Main

        //Function Col
        VBox functionsList = new VBox();
        Button b2 = new Button("View/Add Items"); //view and add items
        Button changeLogin = new Button("Change Logg In"); //change the current log in
        Button salesButton = new Button("Sales"); //go back to the sales screen
        Button viewTransactionsButton = new Button("Transactions"); //view/void past transactions
        viewTransactionsButton.setOnAction(ActionEvent -> {
            changeView(transactionsPane);
            refreshTransView();
        });
        Button summaryButton = new Button("Summary");
        summaryButton.setOnAction(Action -> {
            changeView(summaryPane);
        });
        Button addViewEmployeesButton = new Button("View/Add Employees");
        Button changePermissionLevelsButton = new Button("Permission Levels");
        {

            Button refreshUI = new Button("ROOT:REFRESH UI");
            refreshUI.setOnAction(ActionEvent -> {
                /*if(Register.employee.getAccessLevel().equals(AccessLevel.ROOT)) {
                    mainSpace.getChildren().remove(0, 1);
                    try {
                        this.start(stage);
                    } catch (Exception ex) {
                        System.err.println("RAAAA ERROR");
                    }
                }else{
                    System.err.println("Not ROOT!");
                }*/
            });

            functionsList.getChildren().addAll( changeLogin ,b2,refreshUI, salesButton, viewTransactionsButton, summaryButton, addViewEmployeesButton, changePermissionLevelsButton);
        }

        //Register Area Anchor pane
        mainGrid = new GridPane();

        {
            salesButton.setOnAction(ActionEvent -> {
                changeView(mainGrid);
            });
        }

        //Item View
        AnchorPane itemViewRegion = new AnchorPane();
        itemViewRegion.setStyle(regionStyle);

        ScrollPane itemView = new ScrollPane();

        GridPane itemViewGrid = new GridPane();
        foodButtons = itemViewGrid;

        itemView.setContent(itemViewGrid);

        itemViewGrid.setStyle("" +
                "-fx-hgap: 2;" +
                "-fx-vgap: 2");

        refreshFoodsButtons(foodButtons);

        //Sides
        ScrollPane sidesView = new ScrollPane();

        GridPane sidesViewGrid = new GridPane();
        sideButtons = sidesViewGrid;
        sidesView.setContent(sidesViewGrid);

        sidesView.setVisible(false);
        refreshSidesButtons(sideButtons, true);

        //Toppings
        ScrollPane toppingsView = new ScrollPane();

        GridPane toppingsViewGrid = new GridPane();
        toppingButtons = toppingsViewGrid;

        toppingsView.setContent(toppingsViewGrid);
        toppingsViewGrid.setStyle("" +
                "-fx-hgap: 2;" +
                "-fx-vgap: 2;");

        //displaying the toppings

        toppingsView.setVisible(false);
//        refreshSidesButtons(toppingsViewGrid, false);
        refreshToppingButtons(toppingsViewGrid, true);

        HBox sortItemArea = new HBox();
        HBox adjustSideArea = new HBox();
        HBox toppingAmountArea = new HBox();
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
                    toppingAmountArea.setVisible(true);
                    sortItemArea.setVisible(false);
                    doneButton.setVisible(true);
                }
            });

            Button addSideButton = new Button();
            addSideButton.setStyle(genStyle);
            addSideButton.setText("Add Side");
            addSideButton.setPrefSize(120, 60);
            addSideButton.setOnAction(ActionEvent -> {
                //if(register.getLast() != null){
                    sidesView.setVisible(true);
                    itemView.setVisible(false);
                    toppingAmountArea.setVisible(false);
                    sortItemArea.setVisible(false);
                    doneButton.setVisible(true);
                //}
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
                toppingAmountArea.setVisible(false);
                sortItemArea.setVisible(true);
                doneButton.setVisible(false);
                refreshToppingToggleButtons(null);
            });

            adjustSideArea.getChildren().add(doneButton);
        }

        //setting up the sort item area
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


        toppingAmountArea.setVisible(false);
        {
            ToggleButton lightAmountButton = new ToggleButton("Light");
            ToggleButton normalAmountButton = new ToggleButton("Normal");
            ToggleButton extraAmountButton = new ToggleButton("Extra");
            ToggleButton sideAmountButton = new ToggleButton("Side");

            lightAmountButton.setPrefHeight(40);
            lightAmountButton.setStyle(genStyle);
            lightAmountButton.setOnAction(ActionEvent -> {
                lightAmountButton.setSelected(true);
                normalAmountButton.setSelected(false);
                extraAmountButton.setSelected(false);
                sideAmountButton.setSelected(false);
                currAmount = Topping.AMOUNT.LIGHT;
            });

            normalAmountButton.setPrefHeight(40);
            normalAmountButton.setStyle(genStyle);
            normalAmountButton.setOnAction(ActionEvent -> {
                lightAmountButton.setSelected(false);
                normalAmountButton.setSelected(true);
                extraAmountButton.setSelected(false);
                sideAmountButton.setSelected(false);
                currAmount = Topping.AMOUNT.NORMAL;
            });

            extraAmountButton.setPrefHeight(40);
            extraAmountButton.setStyle(genStyle);
            extraAmountButton.setOnAction(ActionEvent -> {
                lightAmountButton.setSelected(false);
                normalAmountButton.setSelected(false);
                extraAmountButton.setSelected(true);
                sideAmountButton.setSelected(false);
                currAmount = Topping.AMOUNT.EXTRA;
            });

            sideAmountButton.setPrefHeight(40);
            sideAmountButton.setStyle(genStyle);
            sideAmountButton.setOnAction(ActionEvent -> {
                lightAmountButton.setSelected(false);
                normalAmountButton.setSelected(false);
                extraAmountButton.setSelected(false);
                sideAmountButton.setSelected(true);
                currAmount = Topping.AMOUNT.SIDE;
            });

            toppingAmountArea.getChildren().addAll(lightAmountButton, normalAmountButton, extraAmountButton, sideAmountButton);
        }


        itemViewRegion.getChildren().addAll(itemView, toppingsView, sidesView, adjustSideArea, sortItemArea, toppingAmountArea);
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

            AnchorPane.setLeftAnchor(toppingAmountArea, 1.00);
            AnchorPane.setTopAnchor(toppingAmountArea, 1.00);
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

            Button discountButton = new Button("Discount");
            discountButton.setStyle(genStyle);
            discountButton.setPrefSize(80, 70);
            discountButton.setOnAction(ActionEvent -> {
                addItem(new BasicFood("DISCOUNT", "DISCOUNT",-Double.parseDouble(currValueString)));
                currValueString ="";
                refreshKeyPad();
            });

            keyPadButtons.add(clearButton, 3, 0);
            keyPadButtons.add(addButton, 3 , 1);
            keyPadButtons.add(discountButton, 3, 2);

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

            taxAmount = new Label("Tax: ");
            taxAmount.setStyle("" +
                    "-fx-background-color: white;" +
                    "-fx-border-width: 1px;" +
                    "-fx-border-color: black;");
            taxAmount.setPrefWidth(280);
            taxAmount.setFont(Font.font(15));

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
//                    register.cashout((int) (Double.parseDouble(currValueString) * 100));
                }else{
//                    register.cashout(0);
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

            paymentRegion.getChildren().addAll(total, amountEntered, taxAmount, change, methodsArea);
        }

        //setting the grid
        mainGrid.add(itemViewRegion, 0, 0);
        mainGrid.add(keyPadRegion, 0 ,1);
        mainGrid.add(transViewRegion, 1, 0);
        mainGrid.add(paymentRegion, 1, 1);

        //add and views items
        VBox viewAddItems = new VBox();
        viewAddItems.setVisible(false);
        b2.setOnAction(ActionEvent -> {
            //if(Main.functions.get(0).getAccessLevel().isGreateThan(Register.employee.getAccessLevel()))
                changeView(viewAddItems);
            //else
            //System.err.println("Not a : " + Main.functions.get(0).getAccessLevel());
        });

        //creating items UI
        //ahh this going to be such a large area ree

        GridPane foodItems = new GridPane();

        Pane newItemUI = new Pane();
        {

            AnchorPane itemMainRegion = new AnchorPane();
            itemMainRegion.setStyle(regionStyle);

            AnchorPane newFoodArea = new AnchorPane();
            newFoodArea.setVisible(false);

            AnchorPane newToppngArea = new AnchorPane();
            newToppngArea.setVisible(false);

            AnchorPane newSideArea = new AnchorPane();
            newSideArea.setVisible(false);

            Label lb1 = new Label("Item Name: ");
            TextField itemName = new TextField();
            itemName.setPrefWidth(300);

            Label lb2 = new Label("Item Description: ");
            TextField itemDesc = new TextField();
            itemDesc.setPrefWidth(600);

            Label dplNameLabel = new Label("Display Name");
            TextField dplName = new TextField();
            dplName.setPrefWidth(200);


            Button doneButton = new Button("Add New Item");
            doneButton.setStyle(genStyle);

            HBox foodTypeButtons = new HBox();
            ToggleButton isBasicFoodButton = new ToggleButton("Food");
            ToggleButton isTopping = new ToggleButton("Topping");
            ToggleButton isSide = new ToggleButton("Side");

            HBox taxButtons = new HBox();
            {
                CheckBox noTax = new CheckBox("No Tax  ");
                noTax.setSelected(true);
                noTax.setPrefSize(70, 30);
                CheckBox yesTax = new CheckBox("Has Tax  ");
                yesTax.setPrefSize(70, 30);
                CheckBox partOfPrice = new CheckBox("Tax is Part of price  ");
                partOfPrice.setPrefSize(140, 30);

                noTax.setOnAction(ActionEvent -> {
                    noTax.setSelected(true);
                    yesTax.setSelected(false);
                    partOfPrice.setSelected(false);
                });

                yesTax.setOnAction(ActionEvent -> {
                    if(yesTax.isSelected()){
                        noTax.setSelected(false);
                    }else{
                        noTax.setSelected(true);
                        partOfPrice.setSelected(false);
                    }
                });

                partOfPrice.setOnAction(ActionEvent -> {
                    if(partOfPrice.isSelected()){
                        yesTax.setSelected(true);
                        noTax.setSelected(false);
                    }
                });

                taxButtons.getChildren().addAll(noTax, yesTax, partOfPrice);
            }

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
                    newSideArea.setVisible(false);
                    isSide.setSelected(false);
                });

                isTopping.setOnAction(ActionEvent -> {
                    //isTopping.setSelected(true);
                    newToppngArea.setVisible(true);
                    newFoodArea.setVisible(false);
                    if (isTopping.isSelected() && isBasicFoodButton.isSelected()) {
                        isBasicFoodButton.setSelected(false);
                    }
                    newSideArea.setVisible(false);
                    isSide.setSelected(false);
                });

                isSide.setOnAction(ActionEvent -> {
                   isBasicFoodButton.setSelected(false);
                   isTopping.setSelected(false);
                   isSide.setSelected(true);
                   newToppngArea.setVisible(false);
                   newFoodArea.setVisible(false);
                   newSideArea.setVisible(true);
                });

                foodTypeButtons.getChildren().addAll(isBasicFoodButton, isTopping, isSide);
            }

            //if its a Food
            TextField price = new TextField();
            ToggleButton isAdjustableFood = new ToggleButton("Adjustable");
            List<Item> tempAdd = new LinkedList<>();
            {

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

            //if its a side
            TextField priceSide = new TextField();
            {
                Label priceLabel = new Label("Price:");
                priceSide.setPrefWidth(80);

                //settting the anchors
                {
                    AnchorPane.setTopAnchor(priceLabel, 10.00);
                    AnchorPane.setLeftAnchor(priceLabel, 5.00);

                    AnchorPane.setTopAnchor(priceSide, 10.00);
                    AnchorPane.setLeftAnchor(priceSide, 40.00);
                }
                newSideArea.getChildren().addAll(priceLabel, priceSide);
            }


            //if its a Topping

            TextField price2 = new TextField();
            TextField extraPrice = new TextField();
            TextField sidePrice = new TextField();
            {

                Label priceLabel = new Label("Price");
                Label extraPriceLabel = new Label("Extra Price");
                Label sidePricelabel = new Label("Side Price");

                price2.setPrefWidth(40);

                extraPrice.setPrefWidth(40);

                sidePrice.setPrefWidth(40);

                //setting the anchors
                {

                    AnchorPane.setTopAnchor(price2, 30.00);
                    AnchorPane.setLeftAnchor(price2, 30.00);

                    AnchorPane.setTopAnchor(priceLabel, 30.00);
                    AnchorPane.setLeftAnchor(priceLabel, 1.00);

                    AnchorPane.setTopAnchor(extraPrice, 30.000);
                    AnchorPane.setLeftAnchor(extraPrice, 140.00);

                    AnchorPane.setTopAnchor(extraPriceLabel, 30.00);
                    AnchorPane.setLeftAnchor(extraPriceLabel, 80.00);

                    AnchorPane.setTopAnchor(sidePrice, 30.00);
                    AnchorPane.setLeftAnchor(sidePrice, 250.00);

                    AnchorPane.setTopAnchor(sidePricelabel, 30.00);
                    AnchorPane.setLeftAnchor(sidePricelabel, 190.00);
                }

                newToppngArea.getChildren().addAll(price2, priceLabel, extraPrice, extraPriceLabel, sidePrice, sidePricelabel);
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

                AnchorPane.setTopAnchor(taxButtons, 60.00);
                AnchorPane.setLeftAnchor(taxButtons, 200.00);

                AnchorPane.setTopAnchor(newFoodArea, 90.00);
                AnchorPane.setLeftAnchor(newFoodArea, 1.00);

                AnchorPane.setBottomAnchor(doneButton, 1.00);
                AnchorPane.setRightAnchor(doneButton, 1.00);

                AnchorPane.setTopAnchor(newToppngArea, 90.0);
                AnchorPane.setLeftAnchor(newToppngArea, 1.00);

                AnchorPane.setTopAnchor(newSideArea, 90.00);
                AnchorPane.setLeftAnchor(newSideArea, 1.00);
            }



            //done buttons
            doneButton.setOnAction(ActionEvent -> {
                //not final
                if(isBasicFoodButton.isSelected()) {
                    if (!isAdjustableFood.isSelected()) {
                        String tName = itemName.getText();
                        String tDesc = itemDesc.getText();
                        Double tPrice = Double.parseDouble(price.getText());
                        BasicFood temp = new BasicFood(tName, dplName.getText(), tPrice);
                        temp.setTaxable(((CheckBox)(taxButtons.getChildren().get(1))).isSelected());
                        temp.setTaxIsPartOfPrice(((CheckBox)(taxButtons.getChildren().get(2))).isSelected());
                        register.getFoods().add(temp);
//                        register.getFoods().add(new BasicFood(tName, dplName.getText(), tPrice));
                    } else {
                        String tName = itemName.getText();
                        String tDesc = itemDesc.getText();
                        Double tPrice = Double.parseDouble(price.getText());
                        if (tempAdd.isEmpty()) {
                            AdjustableFood temp = new AdjustableFood(tName, dplName.getText(), tPrice);
                            temp.setTaxable(((CheckBox)(taxButtons.getChildren().get(1))).isSelected());
                            temp.setTaxIsPartOfPrice(((CheckBox)(taxButtons.getChildren().get(2))).isSelected());
                            register.getFoods().add(temp);
                        } else {
                            AdjustableFood temp = new AdjustableFood(tName, dplName.getText(), tPrice, tempAdd);
                            temp.setTaxable(((CheckBox)(taxButtons.getChildren().get(1))).isSelected());
                            temp.setTaxIsPartOfPrice(((CheckBox)(taxButtons.getChildren().get(2))).isSelected());
                            register.getFoods().add(temp);
//                            register.getFoods().add(new AdjustableFood(tName, dplName.getText(), tPrice, tempAdd));
                            tempAdd.removeAll(tempAdd); //this may break everything
                        }
                    }
                    itemName.setText("");
                    itemDesc.setText("");
                    price.setText("");
                    isBasicFoodButton.setSelected(false);
                    isAdjustableFood.setSelected(false);
//                    refreshFoodsButtons(foodItems);
                    refreshFoodsButtonsView(foodItems);
                    refreshFoodsButtons(foodButtons);
                }else if(isTopping.isSelected()){

                    String tName = itemName.getText();
                    String tDesc = itemDesc.getText();
                    Double tPrice = Double.parseDouble(price2.getText());
                    Topping temp = new Topping(tName, dplName.getText(), tPrice);
                    temp.setSidePrice(Double.parseDouble(sidePrice.getText()));
                    register.toppings.add(temp);

                    itemName.setText("");
                    itemDesc.setText("");
                    price2.setText("");
                    isTopping.setSelected(false);
                    newToppngArea.setVisible(false);
                    refreshToppingButtons(toppingButtons, true);
                    refreshToppingButtons(toppingsViewGrid, false);

                }else{
                    String tName = itemName.getText();
                    String tDesc = itemDesc.getText();
                    Double tPrice = Double.parseDouble(sidePrice.getText());
                    Side temp = new Side(tName, dplName.getText(), tPrice);
                    register.sides.add(temp);

                    itemName.setText("");
                    itemDesc.setText("");
                    sidePrice.setText("");
                    dplName.setText("");
                    newSideArea.setVisible(false);
                    isSide.setSelected(false);
                    refreshSidesButtons(sideButtons, true);
                    refreshSidesButtons(sidesViewGrid, false);
                }
            });

            itemMainRegion.getChildren().addAll(itemName, lb1, itemDesc, lb2, dplNameLabel, dplName,foodTypeButtons, taxButtons, newFoodArea, doneButton, newToppngArea, newSideArea);

            newItemUI.getChildren().add(itemMainRegion);
//            newItemUI.setVisible(false);
        }

        //views items
        Pane viewItemsUI = new Pane();
        {
            //yes i used a boolean object dont worry about it

            //stuff to view and edit item
            Label nameLabel = new Label("Name:");
            TextField nameField = new TextField();

            Label dplNamelabel = new Label("Display Name:");
            TextField dplNameField = new TextField();

            Label priceLabel = new Label("Price:");
            TextField priceField = new TextField();
            priceField.setPrefWidth(40);

            Label extraPriceLabel = new Label("Extra Price:");
            TextField extraPriceField = new TextField();
            extraPriceField.setPrefWidth(40);

            Label sidePriceLabel = new Label("Side Price:");
            TextField sidePriceField = new TextField();
            sidePriceField.setPrefWidth(40);

            Label IDLabel = new Label("ID:");
            TextField IDField = new TextField();
            IDField.setPrefWidth(50);
            IDField.setEditable(false);

            editFields.add(nameField);
            editFields.add(priceField);
            editFields.add(dplNameField);
            editFields.add(extraPriceField);
            editFields.add(sidePriceField);
            editFields.add(IDField);

            Button makeEdit = new Button("Make Edit");

            AnchorPane itemUIPane = new AnchorPane();
            itemUIPane.setStyle(regionStyle);

            GridPane allItems = new GridPane();
            allItems.setVisible(false);
            {

            }


            foodItems.setVisible(false);
            refreshFoodsButtonsView(foodItems);

            GridPane toppingItems = new GridPane();
            toppingItems.setVisible(false);
            refreshToppingButtons(toppingItems, false);

            GridPane sideItems = new GridPane();
            sideItems.setVisible(false);
            refreshSidesButtons(sideItems, false);

            HBox buttons = new HBox();

            {
                ToggleButton allItemsButton = new ToggleButton("All Items");
                allItemsButton.setStyle(genStyle);
                ToggleButton foodsButton = new ToggleButton("Foods");
                foodsButton.setStyle(genStyle);
                ToggleButton toppingsButton = new ToggleButton("Toppings");
                toppingsButton.setStyle(genStyle);
                ToggleButton sidesButton = new ToggleButton("Sides");
                sidesButton.setStyle(genStyle);

                allItemsButton.setOnAction(ActionEvent -> {
                    allItemsButton.setSelected(true);
                    foodsButton.setSelected(false);
                    toppingsButton.setSelected(false);
                    sidesButton.setSelected(false);
                    foodItems.setVisible(false);
                    toppingItems.setVisible(false);
                    allItems.setVisible(true);
                });

                foodsButton.setOnAction(ActionEvent -> {
                    allItemsButton.setSelected(false);
                    toppingsButton.setSelected(false);
                    foodsButton.setSelected(true);
                    sidesButton.setSelected(false);
                    foodItems.setVisible(true);
                    toppingItems.setVisible(false);
                    allItems.setVisible(false);
                });

                toppingsButton.setOnAction(ActionEvent -> {
                    allItemsButton.setSelected(false);
                    toppingsButton.setSelected(true);
                    foodsButton.setSelected(false);
                    sidesButton.setSelected(false);
                    foodItems.setVisible(false);
                    allItems.setVisible(false);
                    toppingItems.setVisible(true);
                });

                sidesButton.setOnAction(ActionEvent -> {
                    allItemsButton.setSelected(false);
                    toppingsButton.setSelected(false);
                    foodsButton.setSelected(false);
                    sidesButton.setSelected(true);
                    foodItems.setVisible(false);
                    allItems.setVisible(false);
                    toppingItems.setVisible(false);
                    sideItems.setVisible(true);
                });

                makeEdit.setOnAction(ActionEvent -> {
                    if(foodsButton.isSelected()){

                    }else if(toppingsButton.isSelected()){

                    }else{
                        System.err.println("NO");
                    }
                });

                buttons.getChildren().addAll(allItemsButton, foodsButton, toppingsButton, sidesButton);
            }

            itemUIPane.getChildren().addAll(buttons, foodItems, toppingItems, sideItems, nameField, nameLabel, dplNameField, dplNamelabel);
            itemUIPane.getChildren().addAll(priceLabel, priceField, sidePriceField, sidePriceLabel, extraPriceField, extraPriceLabel, IDLabel, IDField, makeEdit);
            //setting the anchors
            {
                AnchorPane.setTopAnchor(buttons, 60.00);
                AnchorPane.setLeftAnchor(buttons, 1.0);

                AnchorPane.setTopAnchor(foodItems, 100.00);
                AnchorPane.setLeftAnchor(foodItems, 1.00);

                AnchorPane.setTopAnchor(toppingItems, 100.00);
                AnchorPane.setLeftAnchor(toppingItems, 1.00);

                AnchorPane.setTopAnchor(sideItems, 100.00);
                AnchorPane.setLeftAnchor(sideItems, 1.00);

                AnchorPane.setTopAnchor(nameLabel, 1.00);
                AnchorPane.setLeftAnchor(nameLabel, 1.00);

                AnchorPane.setTopAnchor(nameField, 1.00);
                AnchorPane.setLeftAnchor(nameField, 40.00);

                AnchorPane.setTopAnchor(dplNamelabel, 1.00);
                AnchorPane.setLeftAnchor(dplNamelabel, 200.00);

                AnchorPane.setTopAnchor(dplNameField, 1.00);
                AnchorPane.setLeftAnchor(dplNameField, 280.00);

                AnchorPane.setTopAnchor(IDLabel, 1.00);
                AnchorPane.setLeftAnchor(IDLabel, 440.00);

                AnchorPane.setTopAnchor(IDField, 1.00);
                AnchorPane.setLeftAnchor(IDField, 460.00);

                AnchorPane.setTopAnchor(priceLabel, 30.00);
                AnchorPane.setLeftAnchor(priceLabel, 1.00);

                AnchorPane.setTopAnchor(priceField, 30.00);
                AnchorPane.setLeftAnchor(priceField, 35.00);

                AnchorPane.setTopAnchor(extraPriceLabel, 30.00);
                AnchorPane.setLeftAnchor(extraPriceLabel, 80.00);

                AnchorPane.setTopAnchor(extraPriceField, 30.00);
                AnchorPane.setLeftAnchor(extraPriceField, 140.00);

                AnchorPane.setTopAnchor(sidePriceLabel, 30.00);
                AnchorPane.setLeftAnchor(sidePriceLabel, 190.00);

                AnchorPane.setTopAnchor(sidePriceField, 30.00);
                AnchorPane.setLeftAnchor(sidePriceField, 250.00);

                AnchorPane.setTopAnchor(makeEdit, 1.00);
                AnchorPane.setRightAnchor(makeEdit, 1.00);
            }

            viewItemsUI.getChildren().addAll(itemUIPane);
        }
//        viewItemsUI.setVisible(false);

        viewAddItems.getChildren().addAll(newItemUI, viewItemsUI);


        //change log in UI
        AnchorPane changeLogInPane = new AnchorPane();
        this.changeLogInPane = changeLogInPane;
        changeLogInPane.setVisible(false);
        changeLogInPane.setStyle(regionStyle);

        changeLogin.setOnAction(ActionEvent -> {
            changeView(changeLogInPane);
        });

        {

            Label logInLabel = new Label("Employee code:");
            TextField logInField = new TextField();

            Label feedBack = new Label();

            Button logInButton = new Button("Log in");
            logInButton.setStyle(genStyle);
            logInButton.setOnAction(ActionEvent -> {
                //Main.employeeDB.logIn(logInField.getText());
                logInField.setText("");
                updateTitle();
            });
            logInField.requestFocus();
            logInField.setOnAction(ActionEvent -> {
                //Main.employeeDB.logIn(logInField.getText());
                logInField.setText("");
                updateTitle();
            });

            changeLogInPane.getChildren().addAll(logInLabel, logInField, logInButton, feedBack);
            {
                AnchorPane.setTopAnchor(logInLabel, 100.00);
                AnchorPane.setLeftAnchor(logInLabel, 100.00);
                AnchorPane.setBottomAnchor(logInLabel, 100.00);
//                AnchorPane.setRightAnchor(logInLabel, 100.00);

                AnchorPane.setTopAnchor(logInField, 100.00);
                AnchorPane.setLeftAnchor(logInField, 190.00);
                AnchorPane.setBottomAnchor(logInField, 100.00);
                AnchorPane.setRightAnchor(logInField, 190.00);

                AnchorPane.setTopAnchor(logInButton, 100.00);
                AnchorPane.setLeftAnchor(logInButton, 320.00);
                AnchorPane.setBottomAnchor(logInButton, 100.00);
//                AnchorPane.setRightAnchor(logInButton, 90);

                AnchorPane.setTopAnchor(feedBack, 150.00);
                AnchorPane.setLeftAnchor(feedBack, 200.00);
            }
        }

        //transaction view
        transactionsPane = new AnchorPane();
        transactionsPane.setStyle(regionStyle);
        transactionsPane.setVisible(false);

        ObservableList<Transaction> transactions = FXCollections.<Transaction>observableArrayList();
        transList = new ListView<Transaction>();
        transList.getItems().addAll(transactions);


        ScrollPane transView = new ScrollPane();
        transViewBox = new VBox();
        transView.setContent(transViewBox);

        transList.setOnMouseClicked(ActionEvent -> {
            refreshTransView();
        });


        {
            Button moveUp = new Button("UP");
            moveUp.setOnAction(ActionEvent ->{
                transList.getSelectionModel().selectPrevious();
                refreshTransView();
            });

            Button moveDown = new Button("Down");
            moveDown.setOnAction(ActionEvent -> {
                transList.getSelectionModel().selectNext();
                refreshTransView();
            });

            Button voidButton = new Button("Void");
            voidButton.setOnAction(ActionEvent -> {
                System.err.println("I should probably do something");
            });


            refreshTransView();
            transactionsPane.getChildren().addAll(transList, transView, moveUp, moveDown, voidButton);
            //setting the anchors
            {
                AnchorPane.setLeftAnchor(transView, 300.00);
                AnchorPane.setTopAnchor(transView, 10.00);

                AnchorPane.setLeftAnchor(moveUp, 250.00);
                AnchorPane.setTopAnchor(moveUp, 10.00);

                AnchorPane.setLeftAnchor(moveDown, 250.00);
                AnchorPane.setTopAnchor(moveDown, 50.00);

                AnchorPane.setLeftAnchor(voidButton, 250.00);
                AnchorPane.setTopAnchor(voidButton, 80.00);
            }
        }

        //getting a summary
        summaryPane = new AnchorPane();
        summaryPane.setVisible(false);
        summaryPane.setStyle(regionStyle);

        {
            HBox buttons = new HBox();

            Button fullSummaryButton = new Button("Full Summary");
            fullSummaryButton.setStyle(genStyle);
            //not sure what other summaries are needed but they can be done

            buttons.getChildren().addAll(fullSummaryButton);

            Button printButton = new Button("Print");

            //summary view
            ScrollPane scrollPane = new ScrollPane();
            Label label = new Label();
            scrollPane.setContent(label);

            fullSummaryButton.setOnAction(ActionEvent -> {
                String temp = "";
                ArrayList<Double> arrayList = Main.transactionDB.getSummary(Main.transactionDB.getStartIdCurr());
                temp += "Total Sales: " + arrayList.get(0) + "\n";
                temp += "Total Value of Sales: " + arrayList.get(1)/100.00 + "\n";
                temp += "No Sales: " + arrayList.get(2) + "\n";
                label.setText(temp);
            });

            summaryPane.getChildren().addAll(buttons, scrollPane, printButton);
            {
                AnchorPane.setTopAnchor(buttons, 1.00);
                AnchorPane.setLeftAnchor(buttons, 1.00);

                AnchorPane.setTopAnchor(scrollPane, 100.00);
                AnchorPane.setLeftAnchor(scrollPane, 1.00);

                AnchorPane.setTopAnchor(printButton, 70.00);
                AnchorPane.setLeftAnchor(printButton, 1.00);
            }
        }


        mainPane = new Pane();
        mainPane.getChildren().addAll(mainGrid, viewAddItems, changeLogInPane, transactionsPane, summaryPane);

        mainSpace.getChildren().addAll(functionsList, mainPane);
        Scene scene = new Scene(mainSpace);
        this.stage = stage;
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
        startUp();
    }

    public void changeView(Node node){
        for(Node nod2 : mainPane.getChildren()){
            nod2.setVisible(false);
        }
        node.setVisible(true);
        refreshFoodsButtons(foodButtons);
        /*
        if(node.isVisible()){
            node.setVisible(false);
            mainGrid.setVisible(true);
        }else{
            mainGrid.setVisible(false);
            node.setVisible(true);
        }
        */
    }

    ListView transList;
    VBox transViewBox;

    public void refreshTransView(){
        System.err.println("Hello");
        if(transList.getItems().size() != Main.transactionDB.getCurrList().size()) {
            for (int i = transList.getItems().size() - 1; i >= 0; i--) {
                transList.getItems().remove(i);
            }
            for (Transaction transaction : Main.transactionDB.getCurrList()) {
                transList.getItems().add(transaction);
            }
            refreshTransView();
            return;
        }
        for(int i = transViewBox.getChildren().size() -1; i >= 0; i--){
            transViewBox.getChildren().remove(i);
        }
        ObservableList observableList = transList.getSelectionModel().getSelectedItems();

        for(Object o : observableList){
            transViewBox.getChildren().add(new Label("" + ((Transaction)o).descString()));
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
        taxAmount.setText("Tax: " + register.getTax()/100.00);
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

    public void refreshFoodsButtons(GridPane gridPane){
        if(register.getFoods().size() > gridPane.getChildren().size()){
            System.err.println("Missing Foods");
            //int dif = register.getFoods().size() - foodButtons.getChildren().size();
            int start = gridPane.getChildren().size();
            int col = gridPane.getColumnCount();
            if(col != 0){
                col = 3;
            }
            int row = gridPane.getRowCount();
            Item item = register.getFoods().get(start);
            Button b1 = new Button(item.getDplName());
            b1.setPrefSize(120, 60);

            b1.setOnAction(ActionEvent -> {
                if (register.getFoods().get(start) instanceof AdjustableFood) {
                    addItem(new AdjustableFood((AdjustableFood) register.getFoods().get(start)));
                    refreshToppingToggleButtons((AdjustableFood)register.getFoods().get(start));
                }else
                        addItem(new BasicFood((BasicFood) register.getFoods().get(start)));
            });

            try {
                gridPane.add(b1, (start % col), (start / col));
            }catch (Exception e){
                gridPane.add(b1, (0), (0));
            }
//            foodButtons.getChildren()
            refreshFoodsButtons(gridPane);//recursive functions :)
        }
    }

    public void refreshToppingButtons(GridPane gridPane, boolean isMainView){
        if(register.toppings.size() > gridPane.getChildren().size()){
            System.err.println("Missing Toppings");
            int start = gridPane.getChildren().size();
            int col;
            if(isMainView)
                col = 3;
            else
                col = 6;
            Item item = register.toppings.get(start);
            ToggleButton b1 = new ToggleButton(item.getDplName());
            b1.setPrefSize(120, 60);

            b1.setOnAction(ActionEvent -> {
                if(isMainView) {
                    if (b1.isSelected()) {
                        ((Topping) item).setAmount(currAmount);
                    } else {
                        ((Topping) item).setAmount(Topping.AMOUNT.NO);
                    }
                    register.addTopping(item);
                    refreshTransList();
                }else{
                    b1.setSelected(false);
                    editFields.get(0).setText(item.getName());
                    editFields.get(1).setText("" + item.getPrice()/100.00);
                    editFields.get(2).setText(item.getDplName());
                    editFields.get(3).setText("" + ((Topping) item).getExtraPrice()/100.00);
                    editFields.get(4).setText("" + ((Topping) item).getSidePrice()/100.00);
                    editFields.get(5).setText("" + item.getId());
                }
            });

            try {
                gridPane.add(b1, (start % col), (start / col));
            }catch (Exception e){
                gridPane.add(b1, 0, 0);
            }

            toppingButton.add(b1);
            refreshToppingButtons(gridPane, isMainView);
        }
    }

    public void refreshToppingButtonsNewItemView(GridPane gridPane){
        if(register.toppings.size() > gridPane.getChildren().size()){
            System.err.println("Missing Toppings");
            int start = gridPane.getChildren().size();
            int col = 6;
            Item item = register.toppings.get(start);
            ToggleButton b1 = new ToggleButton(item.getDplName());

            try {
                gridPane.add(b1, start % col, start / col);
            } catch (Exception e){
                gridPane.add(b1, 0, 0);
            }

            refreshToppingButtonsNewItemView(gridPane);
        }
    }

    public void refreshSidesButtons(GridPane gridPane, boolean isMainView){
        //need to change this
        if(register.sides.size() > gridPane.getChildren().size()){
            System.err.println("Missing Sides");
            int start = gridPane.getChildren().size();
            int col;
            if(isMainView)
                col = 3;
            else
                col = 6;
            Item item = register.sides.get(start);
            Button b1 = new Button(item.getDplName());
            b1.setPrefSize(120, 60);

            b1.setOnAction(ActionEvent -> {
                if(isMainView) {
                    register.addSide(item);
//                register.addTopping(item);
                    refreshTransList();
                }else{
                    editFields.get(0).setText(item.getName());
                    editFields.get(1).setText("" + item.getPrice()/100.00);
                    editFields.get(2).setText(item.getDplName());
                    editFields.get(5).setText("" + item.getId());
                }
            });

            try {
                gridPane.add(b1, (start % col), (start / col));
            }catch (Exception e){
                gridPane.add(b1, (0), (0));
            }

            refreshSidesButtons(gridPane, isMainView);
        }
    }

    public void refreshFoodsButtonsView(GridPane gridPane){
        if(register.getFoods().size() > gridPane.getChildren().size()){//all the foods that exist. diplay grid item
            System.err.println("Missing Foods");
            int start = gridPane.getChildren().size();
            int col = gridPane.getColumnCount();
            if(col != 6)
                col = 6;
            Item item = register.getFoods().get(start);
            Button b1 = new Button(item.getDplName());
            b1.setPrefSize(120, 60);

            b1.setOnAction(ActionEvent -> {
                editFields.get(0).setText(item.getName());
                editFields.get(1).setText("" + item.getPrice()/100.00);
                editFields.get(2).setText(item.getDplName());
                editFields.get(5).setText("" + item.getId());
            });

            gridPane.add(b1, (start%col), (start/col));

            refreshFoodsButtonsView(gridPane);
        }
    }

    public void refreshToppingToggleButtons(AdjustableFood item){
        //use this function to update the buttons so a new function is not needed
        if(item != null) {
            for (ToggleButton button : toppingButton) {
                for (Item top : item.getNormalToppings()) {
                    System.err.println(top.toString());
                    if (top.getName().equals(button.getText()) || top.getDplName().equals(button.getText())) {
                        button.setSelected(true);
                    }
                }
            }
        }else{
            for(ToggleButton button : toppingButton){
                button.setSelected(false);
            }
        }
    }

    public void startUp(){
        //Application.launch();
        changeView(changeLogInPane);
        //title = "POS ***Logged in as: " + Register.employee.getName() + " - Access Level: " + Register.employee.getAccessLevel()+  "***";
        stage.setTitle(title);
    }

    public void updateTitle(){
        //title = "POS ***Logged in as: " + Register.employee.getName() + " - Access Level: " + Register.employee.getAccessLevel()+  "***";
        stage.setTitle(title);
    }

    public void startUpFirst(){


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
