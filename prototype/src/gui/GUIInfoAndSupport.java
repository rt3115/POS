package gui;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import main.Main;


public class GUIInfoAndSupport {

    //When the user is brought to this screen, phone number and website are displayed
    //Also troubleshooting information, and self diagnostics

    private GUIMain main;
    private AnchorPane pane;

    Label infomation = new Label();

    public GUIInfoAndSupport(GUIMain main, AnchorPane pane){
        this.main = main;
        this.pane = pane;
        start();
    }

    public void start(){
        Label label = new Label("Call (585) 210-9544 \n When prompted state name and why you are calling (Register is Broken)");
        pane.getChildren().addAll(label, infomation);

        String temp = "Food Truck POS \n" +
                "Version Code: " + Main.VERSION;


        infomation.setText(temp);
        //setting the anchors
        {
            AnchorPane.setTopAnchor(infomation, 50.00);
        }
    }


}
