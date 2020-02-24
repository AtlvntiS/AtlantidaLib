package co.atlvntis.atlantida.inventory;

import org.bukkit.entity.Player;

public abstract class BukkitInventory extends BaseInventory {

    public BukkitInventory(int slots, String title) {
        super(slots, title);
    }

    public BukkitInventory(String identifier, int slots, String title) {
        super(identifier, slots, title);
    }

    @Override
    public void init(Player player, InventoryContent content) {

    }

}
