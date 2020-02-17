package co.atlvntis.atlantida.inventory;

import co.atlvntis.atlantida.items.GuiItem;
import co.atlvntis.atlantida.items.Item;
import co.atlvntis.atlantida.utils.Colors;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public abstract class AbstractGui implements Gui {

    protected String name;
    protected int slots;

    protected AbstractGui(String name, int rows) {
        this.name = Colors.format(name);
        this.slots = rows * 9;
    }

    public abstract List<GuiItem> contents();
    public abstract Inventory setup(Player player);

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSlots() {
        return slots;
    }

    @Override
    public void openInventory(Player player) {
        player.openInventory(setup(player));
    }
}
