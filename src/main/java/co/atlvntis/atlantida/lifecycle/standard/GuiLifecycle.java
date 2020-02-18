package co.atlvntis.atlantida.lifecycle.standard;

import co.atlvntis.atlantida.AtlantidaPlugin;
import co.atlvntis.atlantida.lifecycle.BaseLifecycle;
import co.atlvntis.atlantida.listener.GuiListener;

public class GuiLifecycle extends BaseLifecycle<AtlantidaPlugin> {

    protected GuiLifecycle(AtlantidaPlugin plugin) {
        super(plugin, 0);
    }

    @Override
    public void enable() {
        plugin.getServer().getPluginManager().registerEvents(new GuiListener(), plugin);
    }
}
