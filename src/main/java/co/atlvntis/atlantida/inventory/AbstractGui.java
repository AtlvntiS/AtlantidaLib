package co.atlvntis.atlantida.inventory;

import co.atlvntis.atlantida.util.Colors;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public abstract class AbstractGui implements Gui {

    protected String name;
    protected int slots;

    protected AbstractGui(String name, int rows) {
        this.name = Colors.format(name);
        this.slots = rows * 9;
    }

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
