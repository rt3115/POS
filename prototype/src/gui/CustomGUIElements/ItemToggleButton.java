package gui.CustomGUIElements;

import common.Item;
import javafx.scene.control.Button;

public class ItemToggleButton extends Button {

    Item item;

    public ItemToggleButton(Item item){
        super(item.getDplName());
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
