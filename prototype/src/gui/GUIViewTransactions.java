package gui;

import common.Observer;
import common.Transaction;
import database.TransactionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Main;
import printer.Receipt;

import java.awt.event.ActionEvent;

public class GUIViewTransactions implements Observer<TransactionDB> {

    AnchorPane pane;
    GUIMain main;

    private Button printButton = new Button("RePrint");
    private Button upButton = new Button("Up");
    private Button downButton = new Button("Down");
    private Button voidTransButton = new Button("Void Trans");

    ObservableList<Transaction> transactions = FXCollections.<Transaction>observableArrayList();
    ListView<Transaction> transList = new ListView<Transaction>();
    ScrollPane transView = new ScrollPane();
    VBox transViewBox = new VBox();

    public double height = 75;
    public double width = 150;
    public double startY = 100;
    public double spacing = 80;

    public GUIViewTransactions(GUIMain main, AnchorPane pane){
        this.main = main;
        this.pane = pane;
        start();
    }

    public void start(){

        Main.transactionDB.setObserver(this);

        transList.getItems().addAll(transactions);

        printButton.setPrefSize(width, height);
        upButton.setPrefSize(width,height);
        downButton.setPrefSize(width,height);
        voidTransButton.setPrefSize(width,height);

        transView.setContent(transViewBox);

        transList.setOnMouseClicked(ActionEvent -> {
            refreshTransList();
        });


        upButton.setOnAction(ActionEvent ->{
            transList.getSelectionModel().selectPrevious();
            refreshTransList();
        });

        downButton.setOnAction(ActionEvent -> {
            transList.getSelectionModel().selectNext();
            refreshTransList();
        });

        voidTransButton.setOnAction(ActionEvent -> {
            transList.getSelectionModel().getSelectedItem().voidTrans();
            refreshTransList();
            Main.transactionDB.save();
        });

        printButton.setOnAction(actionEvent -> {
            //should reprint the selected receipt
            System.err.println("I should have printed a receipt");
            if(transList.getSelectionModel().getSelectedItem() != null)
                Receipt.print_Receipt(transList.getSelectionModel().getSelectedItem());
        });

        pane.getChildren().addAll(transView, transList, upButton, downButton, voidTransButton, printButton);

        //setting the anchors
        {
            AnchorPane.setLeftAnchor(transView, 500.00);
            AnchorPane.setTopAnchor(transView, 50.00);

            AnchorPane.setLeftAnchor(transList, 10.0);
            AnchorPane.setTopAnchor(transList, 50.00);

            AnchorPane.setLeftAnchor(upButton, 300.0);
            AnchorPane.setTopAnchor(upButton, startY);

            AnchorPane.setLeftAnchor(downButton, 300.00);
            AnchorPane.setTopAnchor(downButton, startY + spacing);

            AnchorPane.setLeftAnchor(voidTransButton, 300.0);
            AnchorPane.setTopAnchor(voidTransButton, startY + spacing * 2);

            AnchorPane.setLeftAnchor(printButton, 300.00);
            AnchorPane.setTopAnchor(printButton, startY + spacing * 3);
        }
    }

    public void refreshTransList(){
        if(transList.getItems().size() != Main.transactionDB.getCurrList().size()) {
            for (int i = transList.getItems().size() - 1; i >= 0; i--) {
                transList.getItems().remove(i);
            }
            for (Transaction transaction : Main.transactionDB.getCurrList()) {
                transList.getItems().add(transaction);
            }
            refreshTransList();
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

    @Override
    public void update(TransactionDB transactionDB) {
        refreshTransList();
    }

    /*
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
     */

}
