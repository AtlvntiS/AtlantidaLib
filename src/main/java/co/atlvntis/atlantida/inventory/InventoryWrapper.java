package co.atlvntis.atlantida.inventory;

import co.atlvntis.atlantida.util.Colors;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

@Getter
public class InventoryWrapper implements InventoryHolder {

    private final Player player;
    private final co.atlvntis.atlantida.inventory.Inventory parent;
    private final Inventory inventory;

    private final InventoryContent inventoryContent;

    public InventoryWrapper(Player player, co.atlvntis.atlantida.inventory.Inventory parent) {

        this.player = player;
        this.parent = parent;
        this.inventory = Bukkit.createInventory(this, parent.getSlots(), Colors.format(parent.getTitle()));


        this.inventoryContent = new InventoryContent(player, this);
        parent.init(player, inventoryContent);
        inventoryContent.populate(inventory);

    }

    public void open() {
        player.openInventory(inventory);
    }

    public void update() {
        parent.update(player, inventoryContent);
        inventoryContent.populate(inventory);
        player.updateInventory();
    }

    public void recreate() {
        inventoryContent.clear();
        parent.init(player, inventoryContent);
        inventoryContent.populate(inventory);
        inventoryContent.getPagination().populate();
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

}
