package co.atlvntis.atlantida.lifecycle;

import co.atlvntis.atlantida.AtlantidaPlugin;
import co.atlvntis.atlantida.abstractions.PluginDependent;

public class Lifecycle<T extends AtlantidaPlugin> extends PluginDependent<T> implements ILifecycle  {

    private final int priority;

    protected Lifecycle(T plugin, int priority) {
        super(plugin);
        this.priority = priority;
    }

    @Override
    public void load() { /* Implementation should be made by the user */ }

    @Override
    public void enable() { /* Implementation should be made by the user */ }

    @Override
    public void disable() { /* Implementation should be made by the user */ }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Lifecycle lifecycle) {
        return - Integer.compare(priority, lifecycle.getPriority());
    }
}
