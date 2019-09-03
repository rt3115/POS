package gui;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GUIMain extends Application {

    public GUIMain(){
        //Application.launch();
    }

    Pane functionsPane = new Pane();
    public GUIFunctions functions;

    Pane itemViewPane = new Pane();
    public GUIItemView guiItemView;

    Pane keyPadPane = new Pane();
    public GUIKeyPad guiKeyPad;

    Pane currTransViewPane = new Pane();
    public GUICurrTransView guiCurrTransView;

    Pane paymentMethods = new Pane();
    public GUIPaymentMethods guiPaymentMethods;

    AnchorPane salesValueAnchorPane = new AnchorPane();

    AnchorPane summaryAnchorPane = new AnchorPane();
    public GUISummary guiSummary;

    AnchorPane transactionsAnchorPane = new AnchorPane();
    public GUIViewTransactions guiViewTransactions;

    AnchorPane editItemsAnchorPane = new AnchorPane();
    public GUIEditItems guiEditItems;

    AnchorPane closeRegisterAnchorPane = new AnchorPane();
    public GUICloseRegister guiCloseRegister;

    //the main pane that holds every other pane
    Pane mainPane = new Pane();
    HBox mainBox = new HBox();

    @Override
    public void init() throws Exception {
        super.init();
        guiItemView = new GUIItemView(this, itemViewPane);
        guiCurrTransView = new GUICurrTransView(this, currTransViewPane);
        guiKeyPad = new GUIKeyPad(this, keyPadPane);
        guiPaymentMethods = new GUIPaymentMethods(this, paymentMethods);
        functions = new GUIFunctions(this, functionsPane);
        guiSummary = new GUISummary(this, summaryAnchorPane);
        guiViewTransactions = new GUIViewTransactions(this, transactionsAnchorPane);
        guiEditItems = new GUIEditItems(this, editItemsAnchorPane);
        guiCloseRegister = new GUICloseRegister(this, closeRegisterAnchorPane);
    }

    @Override
    public void start(Stage stage) throws Exception {


        mainBox.getChildren().addAll(functionsPane, mainPane);

        //setting up the sales view
        salesValueAnchorPane.getChildren().addAll(itemViewPane, keyPadPane, currTransViewPane, paymentMethods);
        //setting the anchors
        {
            AnchorPane.setTopAnchor(itemViewPane, 1.00);
            AnchorPane.setLeftAnchor(itemViewPane, 1.00);

            AnchorPane.setTopAnchor(currTransViewPane, 1.00);
            AnchorPane.setRightAnchor(currTransViewPane, 1.00);
            AnchorPane.setLeftAnchor(currTransViewPane, 650.0);

//            AnchorPane.setBottomAnchor(keyPadPane, 1.00);
            AnchorPane.setLeftAnchor(keyPadPane, 1.00);
            AnchorPane.setTopAnchor(keyPadPane, 425.00);

            AnchorPane.setTopAnchor(paymentMethods, 425.00);
            AnchorPane.setLeftAnchor(paymentMethods, 330.00);
//            AnchorPane.setBottomAnchor(paymentMethods, 1.00);
//            AnchorPane.setRightAnchor(paymentMethods, 1.00);
        }

        mainPane.getChildren().addAll(salesValueAnchorPane, summaryAnchorPane, editItemsAnchorPane, transactionsAnchorPane, closeRegisterAnchorPane);
//        stage.setMinWidth(1920);
//        stage.setMinHeight(1080);
        Scene scene = new Scene(mainBox);
        stage.setScene(scene);
        stage.show();
        changeView('c');
    }
    //main runner for the GUI

    public void launch() {Application.launch();}

    public void changeView(char c){
        for(Node node : mainPane.getChildren()){
            node.setVisible(false);
        }
        switch (c){
            case 's': salesValueAnchorPane.setVisible(true); break;
            case 'a': summaryAnchorPane.setVisible(true); break;
            case 't': transactionsAnchorPane.setVisible(true); break;
            case 'i': editItemsAnchorPane.setVisible(true); break;
            case 'v': break;
            case 'p': break;
            case 'c': closeRegisterAnchorPane.setVisible(true); break;
        }
    }

}
