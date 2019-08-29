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
import javafx.scene.text.Text;
import main.Main;

import java.util.LinkedList;
import java.util.List;

public class GUIEditItems implements Observer<MainDB> {

    GUIMain main;
    AnchorPane pane;

    GridPane mainGrid;
    GridPane toppingsGrid;
    GridPane sidesGrid;

    private Item selectedItem;

    private boolean isEdit = false;

    private List<Topping> tempList = new LinkedList<>();
    TextField nameField = new TextField();
    TextField dplNameField = new TextField();
    TextField idField = new TextField();
    TextField descField = new TextField();
    TextField priceField = new TextField();
    TextField extraPrice = new TextField();
    CheckBox hasTax = new CheckBox("Has Tax: ");
    ToggleButton foodItemButton = new ToggleButton("Regular Food/Drink");
    ToggleButton adjustableItemButton = new ToggleButton("Deli Food/Adjustable Food");
    ToggleButton sideItemButton = new ToggleButton("Side");
    ToggleButton toppingItemButton = new ToggleButton("Topping");
    CheckBox hasDeposit = new CheckBox("Has Deposit");

    Button addAndEditButton = new Button("Add/Edit");
    Button viewAndEditToppings = new Button("View/Edit Toppings");
    Button clearSelectedButton = new Button("Clear");

    public GUIEditItems(GUIMain main, AnchorPane pane){
        this.main = main;
        this.pane = pane;
        start();
    }

    public void start() {
        clearSelectedButton.setVisible(false);

        //constructing the gridpanes
        mainGrid = new GridPane();
        toppingsGrid = new GridPane();
        toppingsGrid.setVisible(false);
        sidesGrid = new GridPane();
        sidesGrid.setVisible(false);

        //creating all of the labels and text fields, yay me
        AnchorPane editAreaPane = new AnchorPane();
        {
            editAreaPane.setStyle("" +
                    "-fx-background-color: #e4e2e2;" +
                    "-fx-padding: 5;" +
                    "-fx-spacing: 5;" +
                    "-fx-background-insets: 1.5 1.5 1.5 1.5;");
            ToggleGroup typeOfItemGroup = new ToggleGroup();
            HBox typeOfItemBox = new HBox();
            typeOfItemBox.getChildren().addAll(foodItemButton, adjustableItemButton, sideItemButton, toppingItemButton);
            foodItemButton.setToggleGroup(typeOfItemGroup);
            adjustableItemButton.setToggleGroup(typeOfItemGroup);
            sideItemButton.setToggleGroup(typeOfItemGroup);
            toppingItemButton.setToggleGroup(typeOfItemGroup);
            editAreaPane.getChildren().addAll(nameField, dplNameField, descField, idField, priceField, extraPrice, hasTax, hasDeposit,typeOfItemBox);

            toppingItemButton.setOnAction(actionEvent -> {
                extraPrice.setVisible(true);
                hasDeposit.setVisible(false);
            });
            foodItemButton.setOnAction(actionEvent -> {
                extraPrice.setVisible(false);
                hasDeposit.setVisible(true);
            });
            adjustableItemButton.setOnAction(actionEvent -> {
                extraPrice.setVisible(false);
                hasDeposit.setVisible(false);
            });
            sideItemButton.setOnAction(actionEvent -> {
                extraPrice.setVisible(false);
                hasDeposit.setVisible(false);
            });

            //make all of the useless labels...so much fun
            Label nameLabel = new Label("Name:");
            Label dplNameLabel = new Label("Dpl Name:");
            Label idLabel = new Label("ID:");
            Label descLabel = new Label("Desc:");
            Label priceLabel = new Label("Price:");
            Label extraPriceLabel = new Label("Extra Price:");
            editAreaPane.getChildren().addAll(nameLabel, dplNameLabel, idLabel, descLabel, priceLabel, extraPriceLabel, addAndEditButton, clearSelectedButton);

            viewAndEditToppings.setVisible(false);

            editAreaPane.getChildren().addAll(viewAndEditToppings);



            idField.setPrefWidth(20);
            dplNameField.setPrefWidth(80);
            nameField.setPrefWidth(150);
            priceField.setPrefWidth(30);
            extraPrice.setPrefWidth(30);

            //setting the anchors...yay
            {
                AnchorPane.setTopAnchor(typeOfItemBox, 100.00);
                AnchorPane.setLeftAnchor(typeOfItemBox, 10.00);

                AnchorPane.setTopAnchor(descField, 40.00);
                AnchorPane.setLeftAnchor(descField, 35.00);

                AnchorPane.setTopAnchor(descLabel, 40.00);
                AnchorPane.setLeftAnchor(descLabel, 5.00);

                AnchorPane.setTopAnchor(nameField, 15.00);
                AnchorPane.setLeftAnchor(nameField, 40.00);

                AnchorPane.setTopAnchor(nameLabel, 15.00);
                AnchorPane.setLeftAnchor(nameLabel, 5.00);

                AnchorPane.setTopAnchor(dplNameField, 15.00);
                AnchorPane.setLeftAnchor(dplNameField, 260.00);

                AnchorPane.setTopAnchor(dplNameLabel, 15.00);
                AnchorPane.setLeftAnchor(dplNameLabel, 200.00);

                AnchorPane.setTopAnchor(idField, 15.00);
                AnchorPane.setLeftAnchor(idField, 360.00);

                AnchorPane.setTopAnchor(idLabel, 15.00);
                AnchorPane.setLeftAnchor(idLabel, 345.0);

                AnchorPane.setTopAnchor(priceField, 160.00);
                AnchorPane.setLeftAnchor(priceField, 40.00);

                AnchorPane.setTopAnchor(priceLabel, 160.00);
                AnchorPane.setLeftAnchor(priceLabel, 5.00);

                AnchorPane.setTopAnchor(extraPrice, 160.00);
                AnchorPane.setLeftAnchor(extraPrice, 150.00);

                AnchorPane.setTopAnchor(extraPriceLabel, 160.00);
                AnchorPane.setLeftAnchor(extraPriceLabel, 80.00);

                AnchorPane.setTopAnchor(hasTax, 140.00);
                AnchorPane.setLeftAnchor(hasTax, 10.00);

                AnchorPane.setTopAnchor(hasDeposit, 140.00);
                AnchorPane.setLeftAnchor(hasDeposit, 90.00);

                AnchorPane.setTopAnchor(addAndEditButton, 180.00);
                AnchorPane.setLeftAnchor(addAndEditButton, 350.00);

                AnchorPane.setTopAnchor(viewAndEditToppings, 180.00);
                AnchorPane.setLeftAnchor(viewAndEditToppings, 250.00);

                AnchorPane.setTopAnchor(clearSelectedButton, 180.00);
                AnchorPane.setLeftAnchor(clearSelectedButton, 150.00);
            }
        }

        HBox typeButtons = new HBox();
        //type button stuff
        {
            ToggleButton allItemsButton = new ToggleButton("All Items");
            ToggleButton foodItemsButton = new ToggleButton("Food & Drink");
            ToggleButton toppingItemsButton = new ToggleButton("Toppings");
            ToggleButton sideItemsButton = new ToggleButton("Sides");
            ToggleGroup group = new ToggleGroup();
            allItemsButton.setToggleGroup(group);
            foodItemsButton.setToggleGroup(group);
            toppingItemsButton.setToggleGroup(group);
            sideItemsButton.setToggleGroup(group);

           allItemsButton.setOnAction(actionEvent -> {
                //does nothing at the moment
           });

           foodItemsButton.setOnAction(actionEvent -> {
                mainGrid.setVisible(true);
                toppingsGrid.setVisible(false);
                sidesGrid.setVisible(false);
           });

           toppingItemsButton.setOnAction(actionEvent -> {
               mainGrid.setVisible(false);
               toppingsGrid.setVisible(true);
               sidesGrid.setVisible(false);
           });

           sideItemsButton.setOnAction(actionEvent -> {
               mainGrid.setVisible(false);
               toppingsGrid.setVisible(false);
               sidesGrid.setVisible(true);
           });

            typeButtons.getChildren().addAll(allItemsButton, foodItemsButton, sideItemsButton, toppingItemsButton);


            viewAndEditToppings.setOnAction(actionEvent -> {
                if(!toppingItemsButton.isSelected()) {
                    toppingItemsButton.fire();
                    refreshToppings((AdjustableFood) selectedItem);
                }else{
                    foodItemsButton.fire();
                    refreshToppings(null);
                }
                isEdit = !isEdit;
            });
        }

        //setting the anchors
        {
            AnchorPane.setTopAnchor(mainGrid, 50.00);

            AnchorPane.setTopAnchor(toppingsGrid, 50.00);

            AnchorPane.setTopAnchor(sidesGrid, 50.00);

            AnchorPane.setLeftAnchor(editAreaPane, 400.00);
        }

        //edit and add button
        addAndEditButton.setOnAction(actionEvent -> {
            if(selectedItem == null){
                //make a new item
                Item temp = null;
                if(foodItemButton.isSelected()){
                    temp = new BasicFood(nameField.getText(), dplNameField.getText(), Double.parseDouble(priceField.getText()), hasDeposit.isSelected());
                    temp.setTaxable(hasTax.isSelected());
                }
                if(adjustableItemButton.isSelected()){
                    temp = new AdjustableFood(nameField.getText(), dplNameField.getText(), Double.parseDouble(priceField.getText()));
                    temp.setTaxable(hasTax.isSelected());
                }
                if(sideItemButton.isSelected()){
                    temp = new Side(nameField.getText(), dplNameField.getText(), Double.parseDouble(priceField.getText()));
                    temp.setTaxable(hasTax.isSelected());
                }
                if(toppingItemButton.isSelected()){
                    temp = new Topping(nameField.getText(), dplNameField.getText(), Double.parseDouble(priceField.getText()), Double.parseDouble(extraPrice.getText()));
                }
                try {
                    Main.mainDB.addItem(temp.getId(), temp);
                    updateMainGrid();
                    updateSidesGrid();
                    updateToppingsGrid();
                }catch (Exception e){
                    System.err.println("ERROR in GUIEditItems, AddAndEditButton.setOnAction");
                    e.printStackTrace();
                }
            }else{
                //edit an existing item
                if(foodItemButton.isSelected()){
                    selectedItem.setTaxable(hasTax.isSelected());
                    selectedItem.setPrice(Double.parseDouble(priceField.getText()));
                    selectedItem.setName(nameField.getText());
                    selectedItem.setDplName(dplNameField.getText());
                    ((BasicFood)selectedItem).setDeposit(hasDeposit.isSelected());
                }
                if(adjustableItemButton.isSelected()){
                    //raaaaaa, this will be more complicated
                    selectedItem.setTaxable(hasTax.isSelected());
                    selectedItem.setPrice(Double.parseDouble(priceField.getText()));
                    selectedItem.setName(priceField.getText());
                    selectedItem.setDplName(dplNameField.getText());
                }
                if(sideItemButton.isSelected()){
                    selectedItem.setTaxable(hasTax.isSelected());
                    selectedItem.setPrice(Double.parseDouble(priceField.getText()));
                    selectedItem.setName(priceField.getText());
                    selectedItem.setDplName(dplNameField.getText());

                }
                if(toppingItemButton.isSelected()){
                    selectedItem.setTaxable(hasTax.isSelected());
                    selectedItem.setPrice(Double.parseDouble(priceField.getText()));
                    selectedItem.setName(priceField.getText());
                    selectedItem.setDplName(dplNameField.getText());
                    ((Topping)selectedItem).setExtraPrice(Double.parseDouble(extraPrice.getText()));
                }

            }
            clearSelectedButton.setVisible(false);
            selectedItem = null;
        });

        clearSelectedButton.setOnAction(actionEvent -> {
            selectedItem = null;
            idField.setText("");
            clearSelectedButton.setVisible(false);
        });

        pane.getChildren().addAll(mainGrid, toppingsGrid, sidesGrid, typeButtons, editAreaPane);

        updateMainGrid();
        updateSidesGrid();
        updateToppingsGrid();
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
                        priceField.setText("" + ((BasicFood)temp.getItem()).getPrice()/100.00);
                        idField.setText("" + temp.getItem().getId());
                        nameField.setText(temp.getItem().getName());
                        dplNameField.setText(temp.getItem().getDplName());
                        hasTax.setSelected(temp.getItem().isTaxable());
                        extraPrice.setVisible(false);
                        if(temp.getItem() instanceof AdjustableFood){
                            //show the toppings button
                            adjustableItemButton.setSelected(true);
                            viewAndEditToppings.setVisible(true);
                            selectedItem = ((AdjustableFood)temp.getItem());
                            clearSelectedButton.setVisible(true);
                        }else{
                            selectedItem = ((BasicFood)temp.getItem());
                            foodItemButton.setSelected(true);
                            viewAndEditToppings.setVisible(false);
                            clearSelectedButton.setVisible(true);
                        }
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
                        //adds itself to the tempList for the adjustable foods
                        if(selectedItem == null) {
                            temp.setSelected(false);
                            selectedItem = (Topping)temp.getItem();
                            clearSelectedButton.setVisible(true);
                            idField.setText("" + temp.getItem().getId());
                            extraPrice.setVisible(true);
                            extraPrice.setText(((Topping) temp.getItem()).getExtraPrice() / 100.00 + "");
                            priceField.setText(((Topping) temp.getItem()).getPrice() / 100.00 + "");
                            hasTax.setSelected(temp.getItem().isTaxable());
                            dplNameField.setText(temp.getItem().getDplName());
                            sideItemButton.setSelected(true);
                            nameField.setText(temp.getItem().getName());
                        }else{
                            //for edditing the toppings on a adjustable food
                        }
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
                if(sidesGrid.getChildren().size() > i){
                    ItemButton temp = (ItemButton) sidesGrid.getChildren().get(i);
                    if(!temp.getItem().equals(Main.mainDB.getSides().get(i))){
                        temp.setItem(Main.mainDB.getSides().get(i));
                    }
                }else{
                    ItemButton temp = new ItemButton(Main.mainDB.getSides().get(i));
                    temp.setOnAction(actionEvent -> {
                        selectedItem = (Side)temp.getItem();
                        clearSelectedButton.setVisible(true);
                        priceField.setText("" + ((Side)temp.getItem()).getPrice()/100.00);
                        idField.setText("" + temp.getItem().getId());
                        nameField.setText(temp.getItem().getName());
                        dplNameField.setText(temp.getItem().getDplName());
                        hasTax.setSelected(temp.getItem().isTaxable());
                        extraPrice.setVisible(false);
                    });
                    sidesGrid.add(temp,x,y);
                }

            }
        }
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

    @Override
    public void update(MainDB mainDB) {
        //goes through the list and updates every item
        updateToppingsGrid();
        updateSidesGrid();
        updateMainGrid();
    }

}
