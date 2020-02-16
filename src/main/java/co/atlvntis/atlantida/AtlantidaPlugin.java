package co.atlvntis.atlantida;

import co.atlvntis.atlantida.command.AbstractImperium;
import co.atlvntis.atlantida.command.Imperium;
import co.atlvntis.atlantida.config.ConfigHolder;
import co.atlvntis.atlantida.exceptions.StateErrorException;
import co.atlvntis.atlantida.lifecycle.Lifecycle;
import co.atlvntis.atlantida.object.BukkitObjectFactory;
import co.atlvntis.atlantida.object.ObjectFactory;
import co.atlvntis.atlantida.services.Services;
import co.atlvntis.atlantida.utils.Log;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.*;

public class AtlantidaPlugin extends JavaPlugin {

    private static AtlantidaPlugin instance;

    private Set<Lifecycle> lifecycles = new TreeSet<>();
    private List<ConfigHolder> holders = new ArrayList<>();

    @Override
    public final void onLoad() {

        Services.provide(ObjectFactory.class, new BukkitObjectFactory());

        try {

            load();

            for (Lifecycle lifecycle : lifecycles) {
                lifecycle.load();
            }

        } catch (StateErrorException e) {
            Log.error(e.getMessage());
            getServer().getPluginManager().disablePlugin(this);
        }

    }

    @Override
    public final void onEnable() {

        instance = this;

        try {

            for(ConfigHolder holder : holders) {
                holder.load();
            }

            enable();

            for (Lifecycle lifecycle : lifecycles) {
                lifecycle.enable();
            }

            registerCommands();

        } catch (StateErrorException e) {
            Log.error(e.getMessage());
            getServer().getPluginManager().disablePlugin(this);
        }

    }

    @Override
    public final void onDisable() {

        try {

            disable();

            for (Lifecycle lifecycle : ((TreeSet<Lifecycle>)lifecycles).descendingSet()) {
                lifecycle.disable();
            }

        } catch (StateErrorException e) {
            Log.error(e.getMessage());
            getServer().getPluginManager().disablePlugin(this);
        }

    }

    protected void load() throws StateErrorException { /* The user will implement this if needed */ }
    protected void enable() throws StateErrorException { /* The user will implement this if needed */ }
    protected void disable() throws StateErrorException { /* The user will implement this if needed */ }

    public void info(String message) {
        Log.info(message);
    }

    public void warning(String message) {
        Log.warning(message);
    }

    public StateErrorException error(String message) {
        return new StateErrorException(message);
    }

    protected List<Imperium> commands() { return Collections.emptyList(); }
    protected List<Listener> listeners() { return Collections.emptyList(); }

    private void registerCommands() {

        try {

            CommandMap map;
            Field field;

            field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            map = (CommandMap)field.get(Bukkit.getServer());

            for(Imperium imperium : commands()) {
                if(map.getCommand(imperium.getName()) != null) continue;
                map.register(this.getDescription().getFullName(), (AbstractImperium) imperium);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public <T extends Lifecycle> T lifecycle(T lc) {
        lifecycles.add(lc);
        return lc;
    }
    public <T extends ConfigHolder> T holder(T ch) {
        holders.add(ch);
        return ch;
    }

    public static AtlantidaPlugin getInstance() {
        if(instance == null) {
            throw new IllegalStateException("A instância da classe main é nula.");
        }
        return instance;
    }

}
