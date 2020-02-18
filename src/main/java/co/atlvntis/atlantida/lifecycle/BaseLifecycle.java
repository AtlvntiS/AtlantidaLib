package co.atlvntis.atlantida.lifecycle;

import co.atlvntis.atlantida.AtlantidaPlugin;
import co.atlvntis.atlantida.PluginDependent;

public class BaseLifecycle<T extends AtlantidaPlugin> extends PluginDependent<T> implements Lifecycle  {

    private final int priority;

    protected BaseLifecycle(T plugin, int priority) {
        super(plugin);
        this.priority = priority;
    }

    @Override
    public void load() { }

    @Override
    public void enable() { }

    @Override
    public void disable() { }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(BaseLifecycle lifecycle) {
        return - Integer.compare(priority, lifecycle.getPriority());
    }
}
