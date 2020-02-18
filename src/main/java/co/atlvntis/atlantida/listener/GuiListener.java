package co.atlvntis.atlantida.listener;

import co.atlvntis.atlantida.inventory.Gui;
import co.atlvntis.atlantida.inventory.GuiHolder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GuiListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        if(!(event.getInventory().getHolder() instanceof GuiHolder)) return;

        GuiHolder guiHolder = (GuiHolder) event.getInventory().getHolder();
        Gui gui = guiHolder.getGui();

    }

}
