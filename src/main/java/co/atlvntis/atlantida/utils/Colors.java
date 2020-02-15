package co.atlvntis.atlantida.utils;

import org.bukkit.ChatColor;

public class Colors {

    public static String format(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
