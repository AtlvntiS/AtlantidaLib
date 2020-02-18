package co.atlvntis.atlantida.item;

import co.atlvntis.atlantida.inventory.GuiAction;

public interface GuiItem extends Item {

    void setSlot(int slot);
    int getSlot();

    void setAction(GuiAction action);
    GuiAction getAction();

}
