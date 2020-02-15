package co.atlvntis.atlantida.abstractions;

import co.atlvntis.atlantida.AtlantidaPlugin;

public class PluginDependent<T extends AtlantidaPlugin> {

    protected final T plugin;

    protected PluginDependent(T plugin) {
        this.plugin = plugin;
    }

}
