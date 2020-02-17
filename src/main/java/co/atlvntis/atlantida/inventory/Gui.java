package co.atlvntis.atlantida.inventory;

import org.bukkit.entity.Player;

public interface Gui {

    String getName();
    int getSlots();

    void openInventory(Player player);

}
