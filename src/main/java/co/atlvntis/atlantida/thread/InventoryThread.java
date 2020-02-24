package co.atlvntis.atlantida.thread;

import co.atlvntis.atlantida.inventory.InventoryWrapper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class InventoryThread extends BukkitRunnable {

    @Override
    public void run() {

        for(Player player : Bukkit.getOnlinePlayers()) {

            if(player.getOpenInventory() == null) continue;
            if(!(player.getOpenInventory().getTopInventory().getHolder() instanceof InventoryWrapper)) continue;

            InventoryWrapper wrapper = (InventoryWrapper) player.getOpenInventory().getTopInventory().getHolder();
            wrapper.update();

        }
    }

}
