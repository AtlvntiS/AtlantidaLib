package co.atlvntis.atlantida.util;

import org.bukkit.ChatColor;

public class Colors {

    public static String format(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
