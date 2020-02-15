package co.atlvntis.atlantida.commands;

import co.atlvntis.atlantida.utils.Colors;
import org.bukkit.command.CommandSender;

public interface ImperiumUtilities extends BaseImperium {

    default void message(String message, Object... objects) {
        message(getSender(), message, objects);
    }

    default void message(CommandSender commandSender, String message, Object... objects) {
        commandSender.sendMessage(String.format(Colors.format(message), objects));
    }

}
