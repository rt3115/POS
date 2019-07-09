package gui.CustomGUIElements;

import common.Item;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;

public class ItemToggleButton extends ToggleButton {

    Item item;

    public ItemToggleButton(Item item){
        super(item.getDplName());
        this.item = item;
    }

    @Override
    public void fire(){
        if (!this.isDisabled()) {
            this.setSelected(!this.isSelected());
            this.fireEvent(new ActionEvent());
        }
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
