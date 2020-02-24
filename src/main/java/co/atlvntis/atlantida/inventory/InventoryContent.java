package co.atlvntis.atlantida.inventory;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

@Getter
public class InventoryContent {

    private final Player player;
    private final InventoryWrapper wrapper;

    private ClickableItem[][] items;
    private final Pagination pagination;

    private final Map<String, Object> metadata;

    public InventoryContent(Player player, InventoryWrapper wrapper) {
        this.player = player;
        this.wrapper = wrapper;
        this.items = new ClickableItem[wrapper.getParent().getRows()][9];
        this.pagination = new Pagination(this);
        this.metadata = new HashMap<>();
    }

    public InventoryContent set(Slot slot, ClickableItem clickableItem) {
        set(slot.getRow(), slot.getColumn(), clickableItem);
        return this;
    }

    public InventoryContent set(int row, int column, ClickableItem clickableItem) {
        items[row][column] = clickableItem;
        return this;
    }

    public void handleClick(int slot) {

        int row = slot / 9;
        int column = slot % 9;

        ClickableItem clickableItem = items[row][column];
        if(clickableItem != null) {
            clickableItem.getAction().run(player);
        }

    }

    public void clear() {
        this.items = new ClickableItem[wrapper.getParent().getRows()][9];
    }

    public void populate(Inventory inventory) {
        for (int i = 0; i < items.length; i++) {
            for (int i1 = 0; i1 < 9; i1++) {
                inventory.setItem(i + i1, items[i][i1].getItemStack());
            }
        }
    }

}
