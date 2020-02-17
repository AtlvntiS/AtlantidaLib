package co.atlvntis.atlantida.items.impl;

import co.atlvntis.atlantida.items.GuiItem;
import co.atlvntis.atlantida.items.Item;
import org.bukkit.Material;

public class BukkitGuiItem extends BukkitItem implements GuiItem {

    private int slot = 0;

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
}
