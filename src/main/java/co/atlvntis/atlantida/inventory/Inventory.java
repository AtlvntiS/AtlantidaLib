package co.atlvntis.atlantida.inventory;

import org.bukkit.entity.Player;

public interface Inventory {

    String getIdentifier();

    int getRows();
    int getSlots();
    String getTitle();

    void init(Player player, InventoryContent content);
    default void update(Player player, InventoryContent content) {}

    void registerPlayer(Player player, InventoryWrapper inventory);
    void unregisterPlayer(Player player);

    void openInventory(Player player);
    void openInventory(Player player, int page);

    void update();

}