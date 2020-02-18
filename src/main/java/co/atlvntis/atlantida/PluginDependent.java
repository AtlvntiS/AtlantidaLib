package co.atlvntis.atlantida;

import co.atlvntis.atlantida.AtlantidaPlugin;

public class PluginDependent<T extends AtlantidaPlugin> {

    protected final T plugin;

    protected PluginDependent(T plugin) {
        this.plugin = plugin;
    }

}
