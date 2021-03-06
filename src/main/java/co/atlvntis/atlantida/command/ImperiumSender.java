package co.atlvntis.atlantida.command;

import co.atlvntis.atlantida.util.Colors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandSender;

@Getter
@RequiredArgsConstructor
public class ImperiumSender {

    private final CommandSender commandSender;

    public void message(String message) {
        if(message == null) return;
        commandSender.sendMessage(Colors.format(message));
    }

    public static ImperiumSender of(CommandSender commandSender) {
        return new ImperiumSender(commandSender);
    }

}
