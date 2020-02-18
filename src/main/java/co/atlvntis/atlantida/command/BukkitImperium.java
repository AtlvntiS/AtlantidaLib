package co.atlvntis.atlantida.command;

import co.atlvntis.atlantida.AtlantidaPlugin;
import lombok.Setter;
import org.apache.commons.lang.NotImplementedException;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.stream.Collectors;

public class BukkitImperium extends AbstractImperium {

    @Setter
    private boolean automaticArgCheck = true;

    protected BukkitImperium(AtlantidaPlugin plugin, String commandLine) {
        super(plugin, commandLine);
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {

        ImperiumSender sender = ImperiumSender.of(commandSender);
        ImperiumArgs args = ImperiumArgs.of(strings, this);

        if(getPermission() != null && !commandSender.hasPermission(getPermission())) {
            sender.message(permissionMessage());
            return false;
        }

        if(!automaticArgCheck) {
            exec(sender, args);
            return false;
        }

        if(strings.length < minArgs() || (strings.length > maxArgs() && useChild)) {
            sender.message(usageMessage());
            return false;
        }

        exec(sender, args);
        return true;
    }

    public final void useChild(String arg, ImperiumSender sender, ImperiumArgs args) {
        for(Imperium imperium : childs) {
            if(imperium.getName().equalsIgnoreCase(arg)) {
                imperium.execute(sender.getCommandSender(), "",
                        Arrays.copyOfRange((String[]) args.getArgs().stream().map(ImperiumArg::getArg).toArray(), minArgs(), args.getArgs().size()));
                return;
            }
        }
    }

    @Override
    public void exec(ImperiumSender sender, ImperiumArgs args) {
        throw new NotImplementedException("The command '" + getName() + "' isn't implemented.");
    }

}
