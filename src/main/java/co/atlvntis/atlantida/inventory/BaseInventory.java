package co.atlvntis.atlantida.inventory;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public abstract class BaseInventory implements Inventory {

    private final String identifier;
    private final int rows;
    private final String title;
    private final Map<Player, InventoryWrapper> players;

    public BaseInventory(int rows, String title) {
        this("default", rows, title);
    }

    public BaseInventory(String identifier, int rows, String title) {
        this.identifier = identifier;
        this.rows = rows;
        this.title = title;
        this.players = new HashMap<>();
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getSlots() {
        return rows * 9;
    }

    @Override
    public String getTitle() {
        return title;
    }


    @Override
    public void registerPlayer(Player player, InventoryWrapper inventory) {
        this.players.put(player, inventory);
    }

    @Override
    public void unregisterPlayer(Player player) {
        this.players.remove(player);
    }

    @Override
    public void openInventory(Player player) {
        openInventory(player, 0);
    }

    @Override
    public void openInventory(Player player, int page) {
        InventoryWrapper wrapper = new InventoryWrapper(player, this);
        wrapper.getInventoryContent().getPagination().setPage(page);
        wrapper.open();
        players.put(player, wrapper);
    }

    @Override
    public void update() {
        for(Map.Entry<Player, InventoryWrapper> entry : players.entrySet()) {
            entry.getValue().recreate();
            entry.getKey().updateInventory();
        }
    }

}
