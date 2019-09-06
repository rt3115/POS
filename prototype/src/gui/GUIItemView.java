package gui;

import common.*;
import database.MainDB;
import gui.CustomGUIElements.ItemButton;
import gui.CustomGUIElements.ItemToggleButton;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Main;
import main.Register;


public class GUIItemView implements Observer<Register>{

    GUIMain main;
    Pane pane;

    double width = 150;
    double length = 75;

    ScrollPane scrollPane = new ScrollPane();
    Pane itemsPane = new Pane();
    GridPane mainGrid;
    GridPane toppingsGrid;
    GridPane sidesGird;

    HBox sizesBox = new HBox();
    HBox functionRow = new HBox();

    public GUIItemView(GUIMain main, Pane pane){
        this.pane = pane;
        this.main = main;

        start();
    }

    public void start(){
        //creates the UI elements
        AnchorPane mainPane = new AnchorPane();
        mainGrid = new GridPane();
        toppingsGrid = new GridPane();
        toppingsGrid.setVisible(false);
        sidesGird = new GridPane();
        sidesGird.setVisible(false);

        scrollPane.setContent(itemsPane);
        scrollPane.setPrefSize(610, 300);
        itemsPane.getChildren().addAll(mainGrid, toppingsGrid, sidesGird);

        Button adjustOrderButton = new Button("Adjust Order");
        adjustOrderButton.setPrefSize(100, 75);
        Button addSideButton = new Button("Add Side");
        addSideButton.setPrefSize(100, 75);
        Button doneButton = new Button("Done");
        doneButton.setPrefSize(100, 75);
        doneButton.setVisible(false);
        functionRow.getChildren().addAll(adjustOrderButton, addSideButton, doneButton);

        adjustOrderButton.setOnAction(actionEvent -> {
            if(Main.register.canAddTopping() && !Main.register.isTransDone()){
                mainGrid.setVisible(false);
                toppingsGrid.setVisible(true);
                doneButton.setVisible(true);
                sizesBox.setVisible(true);
            }
        });

        addSideButton.setOnAction(actionEvent -> {
            mainGrid.setVisible(false);
            toppingsGrid.setVisible(false);
            adjustOrderButton.setVisible(false);
            doneButton.setVisible(true);
            sidesGird.setVisible(true);
        });

        doneButton.setOnAction(actionEvent -> {
            mainGrid.setVisible(true);
            toppingsGrid.setVisible(false);
            sidesGird.setVisible(false);
            doneButton.setVisible(false);
            sizesBox.setVisible(false);
            adjustOrderButton.setVisible(true);
        });

        ToggleGroup group = new ToggleGroup();
        ToggleButton lightButton = new ToggleButton("Light");
        lightButton.setPrefSize(80, 40);
        lightButton.setToggleGroup(group);
        ToggleButton normalButton = new ToggleButton("Normal");
        normalButton.setPrefSize(80, 40);
        normalButton.setToggleGroup(group);
        normalButton.setSelected(true);
        ToggleButton extraButton = new ToggleButton("Extra");
        extraButton.setPrefSize(80, 40);
        extraButton.setToggleGroup(group);
        ToggleButton sideButton = new ToggleButton("Side");
        sideButton.setPrefSize(80, 40);
        sideButton.setToggleGroup(group);
        sizesBox.getChildren().addAll(lightButton, normalButton, extraButton, sideButton);
        sizesBox.setVisible(false);

        mainPane.getChildren().addAll(scrollPane, functionRow, sizesBox);
        pane.getChildren().add(mainPane);

        //setting the anchors
        {
            AnchorPane.setTopAnchor(scrollPane, 50.00);

            AnchorPane.setTopAnchor(functionRow, 350.00);
        }

        updateItems();
        Main.register.addObserver(this);
        Main.mainDB.addObserver(this);
    }

    public void updateMainGrid(){
        //mainGrid.getChildren().remove(0, mainGrid.getChildren().size() - 1);

        int i = 0;
        for(int y = 0; y < 100 ; y++){
            for(int x = 0; x < Main.values.ITEM_VIEW_WIDTH; x++){
                if(i >= Main.mainDB.getItems().size()){
                    break;
                }
                if(mainGrid.getChildren().size() > i){
                    ItemButton temp = (ItemButton)mainGrid.getChildren().get(i);
                    if(!temp.getItem().equals(Main.mainDB.getItems().get(i))){
                        temp.setItem(Main.mainDB.getItems().get(i));
                    }
                }else{
                    ItemButton temp = new ItemButton(Main.mainDB.getItems().get(i));
                    temp.setPrefSize(width, length);
                    temp.setOnAction(ActionEvent -> {
                        Main.register.addFood(temp.getItem());
                    });
                    mainGrid.add(temp, x ,y);
                }

                i++; //the lazy var
            }
        }
    }

    public void updateToppingsGrid(){
        int i = 0;

        for(int y = 0; y < 100; y++){
            for(int x = 0; x < Main.values.ITEM_VIEW_WIDTH; x++){

                if(i >= Main.mainDB.getToppings().size())
                    break;
                if(toppingsGrid.getChildren().size() > i) {
                    ItemToggleButton temp = (ItemToggleButton) toppingsGrid.getChildren().get(i);
                    if (!temp.getItem().equals(Main.mainDB.getToppings().get(i))) {
                        temp.setItem(Main.mainDB.getToppings().get(i));
                    }
                }else {
                    ItemToggleButton temp = new ItemToggleButton(Main.mainDB.getToppings().get(i));
                    temp.setPrefSize(width, length);
                    temp.setOnAction(actionEvent -> {
                        if(!temp.isSelected())
                            ((Topping)temp.getItem()).setAmount(Topping.AMOUNT.NO);
                        else {
                            if (((ToggleButton) (sizesBox.getChildren().get(0))).isSelected()) {
                                ((Topping) temp.getItem()).setAmount(Topping.AMOUNT.LIGHT);
                                ((ToggleButton) (sizesBox.getChildren().get(1))).fire();
                                System.err.println("I got called");
                            }
                            else if (((ToggleButton) (sizesBox.getChildren().get(1))).isSelected()) {
                                ((Topping) temp.getItem()).setAmount(Topping.AMOUNT.NORMAL);
                                System.err.println("I got called 2");
                            }
                            else if (((ToggleButton) (sizesBox.getChildren().get(2))).isSelected()) {
                                ((Topping) temp.getItem()).setAmount(Topping.AMOUNT.EXTRA);
                                ((ToggleButton) (sizesBox.getChildren().get(1))).fire();
                            }
                            else if (((ToggleButton) (sizesBox.getChildren().get(3))).isSelected()) {
                                ((Topping) temp.getItem()).setAmount(Topping.AMOUNT.SIDE);
                                ((ToggleButton) (sizesBox.getChildren().get(1))).fire();
                            }
                        }
                        Main.register.addTopping(temp.getItem());
                    });
                    toppingsGrid.add(temp, x,y);
                }


                i++;
            }
        }
    }

    public void updateSidesGrid(){
        int i = 0;

        for(int y =0; y < 100; y++){
            for(int x = 0; x < Main.values.ITEM_VIEW_WIDTH; x++){

                if(i >= Main.mainDB.getSides().size())
                    break;
                if(sidesGird.getChildren().size() > i){
                    ItemButton temp = (ItemButton) sidesGird.getChildren().get(i);
                    if(!temp.getItem().equals(Main.mainDB.getSides().get(i))){
                        temp.setItem(Main.mainDB.getSides().get(i));
                    }
                }else{
                    ItemButton temp = new ItemButton(Main.mainDB.getSides().get(i));
                    temp.setPrefSize(width, length);
                    temp.setOnAction(actionEvent -> {
                        Main.register.addSide(temp.getItem());
                    });
                    sidesGird.add(temp,x,y);
                }

            }
        }
    }

    public void updateItems(){
        //updates the changes to the items
        updateMainGrid();
        updateSidesGrid();
        updateToppingsGrid();
    }

    public void update(Register register){
        if(register == null){
            updateItems();
        }else if(register.getLast() instanceof AdjustableFood)
            refreshToppings((AdjustableFood)register.getLast());
    }

    public void refreshToppings(AdjustableFood food){
        if(food == null){
            for(Node node : toppingsGrid.getChildren()){
                ((ItemToggleButton)node).setSelected(false);
            }
        }else{
            for(Node node : toppingsGrid.getChildren()){
                int temp = food.getToppings().indexOf(((ItemToggleButton)node).getItem());
                if(food.getNormalToppings().contains(((ItemToggleButton)node).getItem()) ||
                        food.getToppings().contains(((ItemToggleButton)node).getItem())){
                    if(temp == -1 || !((Topping)food.getToppings().get(temp)).getAmount().equals(Topping.AMOUNT.NO))
                        ((ItemToggleButton)node).setSelected(true);
                    else
                        ((ItemToggleButton)node).setSelected(false);
                }else{
                    ((ItemToggleButton)node).setSelected(false);
                }
            }
        }
    }

    public void update(MainDB mainDB) {
        updateItems();
    }
}
