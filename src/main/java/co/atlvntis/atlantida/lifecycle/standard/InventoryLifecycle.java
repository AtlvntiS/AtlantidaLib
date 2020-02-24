package co.atlvntis.atlantida.lifecycle.standard;

import co.atlvntis.atlantida.AtlantidaPlugin;
import co.atlvntis.atlantida.lifecycle.BaseLifecycle;
import co.atlvntis.atlantida.listener.InventoryListener;
import co.atlvntis.atlantida.thread.InventoryThread;
import org.bukkit.scheduler.BukkitTask;

public class InventoryLifecycle extends BaseLifecycle<AtlantidaPlugin> {

    public InventoryLifecycle(AtlantidaPlugin plugin) {
        super(plugin, 0);
    }

    BukkitTask inventoryTask;

    @Override
    public void enable() {
        plugin.getServer().getPluginManager().registerEvents(new InventoryListener(), plugin);
        inventoryTask = new InventoryThread().runTaskTimerAsynchronously(plugin, 1, 1);
    }

    @Override
    public void disable() {
        inventoryTask.cancel();
    }
}
