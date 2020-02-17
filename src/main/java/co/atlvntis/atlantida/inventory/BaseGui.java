package co.atlvntis.atlantida.inventory;

import co.atlvntis.atlantida.items.GuiItem;
import co.atlvntis.atlantida.items.Item;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Collections;
import java.util.List;

public class BaseGui extends AbstractGui {

    private final List<GuiItem> items;

    protected BaseGui(String name, int rows) {
        super(name, rows);
        this.items = contents();
    }

    @Override
    public List<GuiItem> contents() {
        return Collections.emptyList();
    }

    @Override
    public Inventory setup(Player player) {
        Inventory inventory = Bukkit.createInventory(new GuiHolder(player.getName(), this), slots, name);
        for(GuiItem item : items) inventory.setItem(item.getSlot(), item.get());
        return inventory;
    }

}
