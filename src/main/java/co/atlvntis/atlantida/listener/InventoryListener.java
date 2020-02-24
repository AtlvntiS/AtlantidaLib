package co.atlvntis.atlantida.listener;

import co.atlvntis.atlantida.inventory.InventoryWrapper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        if(!(event.getInventory().getHolder() instanceof InventoryWrapper)) return;
        if(event.getClickedInventory() != event.getView().getTopInventory()) return;

        event.setCancelled(true);

        InventoryWrapper wrapper = (InventoryWrapper) event.getInventory().getHolder();
        wrapper.getInventoryContent().handleClick(event.getSlot());

    }

    @EventHandler
    public void onInventoryCLose(InventoryCloseEvent event) {

        if(!(event.getInventory().getHolder() instanceof InventoryWrapper)) return;
        InventoryWrapper wrapper = (InventoryWrapper) event.getInventory().getHolder();
        wrapper.getParent().unregisterPlayer((Player) event.getPlayer());

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        if(player.getOpenInventory() == null || player.getOpenInventory().getTopInventory() == null) return;
        if(!(player.getOpenInventory().getTopInventory().getHolder() instanceof InventoryWrapper)) return;
        InventoryWrapper wrapper = (InventoryWrapper) player.getOpenInventory().getTopInventory().getHolder();
        wrapper.getParent().unregisterPlayer(player);


    }

}
