package co.atlvntis.atlantida.inventory;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

@Getter
@RequiredArgsConstructor
public class GuiHolder implements InventoryHolder {

    private final String name;
    private final Gui gui;

    @Override
    public Inventory getInventory() {
        return null;
    }
}
