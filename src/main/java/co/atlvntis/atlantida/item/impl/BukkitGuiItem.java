package co.atlvntis.atlantida.item.impl;

import co.atlvntis.atlantida.inventory.GuiAction;
import co.atlvntis.atlantida.item.GuiItem;
import co.atlvntis.atlantida.item.Item;
import org.bukkit.Material;

public class BukkitGuiItem extends BukkitItem implements GuiItem {

    private int slot = 0;
    private GuiAction action = player -> {};

    public BukkitGuiItem(Item item) {
        super(item);
    }

    public BukkitGuiItem() {
        super();
    }

    public BukkitGuiItem(Material material) {
        super(material);
    }

    public BukkitGuiItem(Material material, int amount) {
        super(material, amount);
    }

    @Override
    public void setSlot(int slot) {
        this.slot = slot;
    }

    @Override
    public int getSlot() {
        return slot;
    }

    @Override
    public void setAction(GuiAction action) {
        this.action = action;
    }

    @Override
    public GuiAction getAction() {
        return action;
    }
}
