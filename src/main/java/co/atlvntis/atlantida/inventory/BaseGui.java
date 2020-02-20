package co.atlvntis.atlantida.inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BaseGui extends AbstractGui {

    private final Map<Integer, GuiItem<?>> items;

    protected BaseGui(String name, int rows) {
        super(name, rows);
        this.items = contents().stream().collect(Collectors.toMap(GuiItem::getSlot, item -> item));
    }

    public List<GuiItem<?>> contents() {
        return Collections.emptyList();
    }

    @Override
    public void handleClick(InventoryClickEvent event) {

        event.setCancelled(true);

        if(event.getRawSlot() > event.getInventory().getSize()) return;

        if(items.containsKey(event.getSlot())) {
            items.get(event.getSlot()).getAction().run((Player)event.getWhoClicked());
        }

    }

    @Override
    public Inventory setup(Player player) {
        Inventory inventory = Bukkit.createInventory(new GuiHolder(player.getName(), this), slots, name);
        for(GuiItem<?> item : items.values()) {
            inventory.setItem(item.getSlot(), item.buildItem(player).get());
            item.reset();
        }
        return inventory;
    }

}
