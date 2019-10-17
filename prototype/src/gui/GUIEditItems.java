package gui;

import common.*;
import database.MainDB;
import gui.CustomGUIElements.ItemButton;
import gui.CustomGUIElements.ItemToggleButton;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.Main;

import java.util.LinkedList;
import java.util.List;

public class GUIEditItems implements Observer<MainDB> {

    GUIMain main;
    AnchorPane pane;

    ScrollPane foodsList = new ScrollPane();
    ScrollPane deliFoodsList = new ScrollPane();
    ScrollPane toppingList = new ScrollPane();
    ScrollPane sideList = new ScrollPane();
    ScrollPane drinkList = new ScrollPane();

    VBox foodsBox = new VBox();
    VBox deliFoodsBox = new VBox();
    VBox toppingBox = new VBox();
    VBox sidesBox = new VBox();
    VBox drinkBox = new VBox();

    public GUIEditItems(GUIMain main, AnchorPane pane){
        this.main = main;
        this.pane = pane;
        start();
    }

    public void start() {

        foodsList.setContent(foodsBox);
        deliFoodsList.setContent(deliFoodsBox);
        toppingList.setContent(toppingBox);
        sideList.setContent(sidesBox);
        drinkList.setContent(drinkBox);

    }


    public void updateMainGrid(){

    }

    public void updateToppingsGrid(){

    }

    public void updateSidesGrid(){

    }

    public void refreshToppings(AdjustableFood food){

    }

    @Override
    public void update(MainDB mainDB) {
        //goes through the list and updates every item
        updateToppingsGrid();
        updateSidesGrid();
        updateMainGrid();
    }

}
