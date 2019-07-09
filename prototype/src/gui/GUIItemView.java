package gui;

import common.*;
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

import java.awt.event.ActionEvent;

public class GUIItemView implements Observer<Register> {

    GUIMain main;
    Pane pane;

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
        scrollPane.setPrefSize(400, 300);
        itemsPane.getChildren().addAll(mainGrid, toppingsGrid, sidesGird);

        Button adjustOrderButton = new Button("Adjust Order");
        Button addSideButton = new Button("Add Side");
        Button doneButton = new Button("Done");
        doneButton.setVisible(false);
        functionRow.getChildren().addAll(adjustOrderButton, addSideButton, doneButton);

        adjustOrderButton.setOnAction(actionEvent -> {
            if(Main.register.canAddTopping()){
                mainGrid.setVisible(false);
                toppingsGrid.setVisible(true);
                doneButton.setVisible(true);
                sizesBox.setVisible(true);
            }
        });

        addSideButton.setOnAction(actionEvent -> {
            mainGrid.setVisible(false);
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

        ToggleButton lightButton = new ToggleButton("Light");
        ToggleButton normalButton = new ToggleButton("Normal");
        ToggleButton extraButton = new ToggleButton("Extra");
        ToggleButton sideButton = new ToggleButton("Side");
        sizesBox.getChildren().addAll(lightButton, normalButton, extraButton, sideButton);

        mainPane.getChildren().addAll(scrollPane, functionRow, sizesBox);
        pane.getChildren().add(mainPane);

        //setting the anchors
        {
            AnchorPane.setTopAnchor(scrollPane, 30.00);

            AnchorPane.setTopAnchor(functionRow, 400.00);
        }

        updateItems();
        Main.register.addObserver(this);
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
                    temp.setOnAction(actionEvent -> {
                        if(!temp.isSelected())
                            ((Topping)temp.getItem()).setAmount(Topping.AMOUNT.NO);
                        else
                            ((Topping)temp.getItem()).setAmount(Topping.AMOUNT.NORMAL);
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
        if(register.getLast() instanceof AdjustableFood)
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

}
