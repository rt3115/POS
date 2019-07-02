package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Register;

public class GUIMain extends Application {

    public GUIMain(){
        //Application.launch();
    }

    Pane functionsPane = new Pane();
    GUIFunctions functions;

    Pane itemViewPane = new Pane();
    GUIItemView guiItemView;

    Pane keyPadPane = new Pane();
    GUIKeyPad guiKeyPad;

    Pane currTransViewPane = new Pane();
    GUICurrTransView guiCurrTransView;

    Pane paymentMethods = new Pane();
    GUIPaymentMethods guiPaymentMethods;

    AnchorPane salesValueAnchorPane = new AnchorPane();

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

            AnchorPane.setBottomAnchor(keyPadPane, 1.00);
            AnchorPane.setLeftAnchor(keyPadPane, 1.00);

            AnchorPane.setBottomAnchor(paymentMethods, 1.00);
            AnchorPane.setRightAnchor(paymentMethods, 1.00);
        }

        mainPane.getChildren().addAll(salesValueAnchorPane);
        Scene scene = new Scene(mainBox);
        stage.setScene(scene);
        stage.show();
    }
    //main runner for the GUI

    public static void launch(){
        Application.launch();
    }

}
