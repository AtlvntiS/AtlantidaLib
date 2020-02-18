package co.atlvntis.atlantida.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public interface Gui {

    String getName();
    int getSlots();

    void handleClick(InventoryClickEvent event);

    void openInventory(Player player);

}
