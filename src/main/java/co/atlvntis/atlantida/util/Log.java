package co.atlvntis.atlantida.util;

import co.atlvntis.atlantida.AtlantidaPlugin;
import org.bukkit.Bukkit;

public class Log {

    public static void info(String message) {
        Bukkit.getConsoleSender().sendMessage(String.format("§a[%s - v%s] - info", AtlantidaPlugin.getInstance().getDescription().getName(), AtlantidaPlugin.getInstance().getDescription().getVersion()));
        Bukkit.getConsoleSender().sendMessage("§a" + Colors.format(message));
    }

    public static void warning(String message) {
        Bukkit.getConsoleSender().sendMessage(String.format("§e[%s - v%s] - aviso", AtlantidaPlugin.getInstance().getDescription().getName(), AtlantidaPlugin.getInstance().getDescription().getVersion()));
        Bukkit.getConsoleSender().sendMessage("§e" + Colors.format(message));
    }

    public static void error(String message) {
        Bukkit.getConsoleSender().sendMessage(String.format("§c[%s - v%s] - erro", AtlantidaPlugin.getInstance().getDescription().getName(), AtlantidaPlugin.getInstance().getDescription().getVersion()));
        Bukkit.getConsoleSender().sendMessage("§c" + Colors.format(message));
    }

}
