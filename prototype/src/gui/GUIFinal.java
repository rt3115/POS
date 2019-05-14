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

import java.awt.event.ActionEvent;


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

        HBox mainSpace = new HBox();
        mainSpace.setStyle(mainBackground);

        //Function Col
        VBox functionsList = new VBox();
        Button b2 = new Button("Test Button");

        functionsList.getChildren().addAll(new Button("Function Button"), b2);

        //Register Area Anchor pane
        mainGrid = new GridPane();

        //Item View
        AnchorPane itemViewRegion = new AnchorPane();
        itemViewRegion.setStyle(regionStyle);

        GridPane itemView = new GridPane();
        itemView.setStyle("" +
                "-fx-hgap: 2;" +
                "-fx-vgap: 2");
        //Displaying the Item Buttons
        { //adding some test buttons
            for(int x = 0; x < 3; x++){
                for(int y = 0; y < 6; y++){
                    Button b1 = new Button("Hello");
                    b1.setStyle(genStyle);
                    b1.setPrefSize(90, 60);
                    itemView.add(b1, x ,y);
                }
            }
        }

        //Sides

        //Sauces

        //Toppings

        HBox adjustSideArea = new HBox();
        //sides and adjustOrder Buttons
        {
            Button adjustOrderButton = new Button();
            adjustOrderButton.setStyle(genStyle);
            adjustOrderButton.setText("Adjust Order");
            adjustOrderButton.setPrefSize(120, 60);

            Button addSideButton = new Button();
            addSideButton.setStyle(genStyle);
            addSideButton.setText("Add Side");
            addSideButton.setPrefSize(120, 60);
            adjustSideArea.getChildren().addAll(adjustOrderButton, addSideButton);
            adjustSideArea.setStyle("" +
                    "-fx-spacing: 5;");
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

        itemViewRegion.getChildren().addAll(itemView, adjustSideArea, sortItemArea);
        //setting the anchors
        {
            AnchorPane.setBottomAnchor(adjustSideArea, 1.00);
            AnchorPane.setRightAnchor(adjustSideArea, 1.00);
            AnchorPane.setLeftAnchor(adjustSideArea, 1.00);

            AnchorPane.setLeftAnchor(itemView, 1.00);
            AnchorPane.setRightAnchor(itemView, 1.00);
            AnchorPane.setTopAnchor(itemView, 45.00);

            AnchorPane.setLeftAnchor(sortItemArea, 1.00);
            AnchorPane.setRightAnchor(sortItemArea, 1.00);
            AnchorPane.setTopAnchor(sortItemArea, 1.00);
        }

        //Keypad
        VBox keyPadRegion = new VBox();
        keyPadRegion.setStyle(regionStyle);
        {
            Label currValue = new Label("123456789");
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
                    b1.setStyle(genStyle);
                    b1.setPrefSize(70 , 70);
                    keyPadButtons.add(b1, y ,x);
                }
            }
            Button clearButton = new Button("Clear");
            clearButton.setStyle(genStyle);
            clearButton.setPrefSize(80, 70);
            Button addButton = new Button("Add");
            addButton.setPrefSize(80, 70);
            addButton.setStyle(genStyle);

            keyPadButtons.add(clearButton, 3, 0);
            keyPadButtons.add(addButton, 3 , 1);

            Button zeroButton = new Button("0");
            zeroButton.setStyle(genStyle);
            zeroButton.setPrefSize(142, 50);
            Button dotButton = new Button(".");
            dotButton.setStyle(genStyle);
            dotButton.setPrefSize(70, 50);
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
            Button removeItem = new Button("Remove Item");
            removeItem.setStyle(genStyle);
            removeItem.setPrefSize(145, 30);
            voidRemoveArea.getChildren().addAll(voidTrans, removeItem);

            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setPrefViewportHeight(440);
            scrollPane.setPrefViewportWidth(280);

            transViewRegion.getChildren().addAll(voidRemoveArea, scrollPane);
        }

        //Cash or credit area
        VBox paymentRegion = new VBox();
        paymentRegion.setStyle(regionStyle);
        {
            Label total = new Label("Total: ");
            total.setStyle("" +
                    "-fx-background-color: white;" +
                    "-fx-border-width: 1px;" +
                    "-fx-border-color: black");
            total.setPrefWidth(280);
            total.setFont(Font.font(30));

            Label amountEntered = new Label("Amount Entered: " );
            amountEntered.setStyle("" +
                    "-fx-background-color: white;" +
                    "-fx-border-width: 1px;" +
                    "-fx-border-color: black;");
            amountEntered.setPrefWidth(280);
            amountEntered.setFont(Font.font(15));
            Label change = new Label("Change: ");
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
        Pane newItemUI = new Pane();
        {
            newItemUI.getChildren().addAll(new Label("Adding A new Item"));
            b2.setOnAction(ActionEvent -> {
                changeView(newItemUI);
            });


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

    public static void launch(){
        Application.launch();
    }


}
