package co.atlvntis.atlantida.commands;

import co.atlvntis.atlantida.AtlantidaPlugin;
import co.atlvntis.atlantida.object.ObjectFactory;
import co.atlvntis.atlantida.services.Services;
import co.atlvntis.atlantida.utils.Colors;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import java.util.Arrays;
import java.util.NoSuchElementException;

public abstract class Imperium extends AbstractImperium implements ImperiumUtilities {

    protected CommandSender commandSender;
    protected String[] args = null;

    private final String permission;
    private final boolean isPlayerExclusive;

    public Imperium(AtlantidaPlugin plugin, String commandLine) {
        this(plugin, commandLine, false);
    }

    public Imperium(AtlantidaPlugin plugin, String commandLine, String permission) {
        this(plugin, commandLine, permission, false);
    }

    public Imperium(AtlantidaPlugin plugin, String commandLine, boolean isPlayerExclusive) {
        this(plugin, commandLine, null, isPlayerExclusive);
    }

    public Imperium(AtlantidaPlugin plugin, String commandLine, String permission, boolean isPlayerExclusive) {
        super(plugin, commandLine);
        this.permission = permission;
        this.isPlayerExclusive = isPlayerExclusive;
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {

        if(isPlayerExclusive && commandSender instanceof ConsoleCommandSender) {
           message(getExclusivityMessage());
            return false;
        }

        if(permission != null && !commandSender.hasPermission(permission)) {
            message(getPermissionMessage());
            return false;
        }

        if(args.length < getMinArgs() || (!useChildren && args.length > getMaxArgs())) {
            commandSender.sendMessage(Colors.format(getUsageMessage()));
            return false;
        }

        this.commandSender = commandSender;
        this.args = args;
        run(commandSender, args);

        if(useChildren) {
            String childArg = args[getMinArgs()];
            for(AbstractImperium imperium : children) {
                if(imperium.getAliases().get(0).equalsIgnoreCase(childArg)) {
                    args = Arrays.copyOfRange(args, getMinArgs(), args.length);
                    imperium.execute(commandSender, s, args);
                    return false;
                }
            }
            message(getInvalidChildMessage());
        }


        return false;
    }

    public String getParameter(String parameter) {

        if(!reqParameters.contains(parameter) && !optParameters.contains(parameter))
            throw new NoSuchElementException("There is no parameter with the name " + parameter);

        if(reqParameters.contains(parameter)) {
            return args[reqParameters.indexOf(parameter)];
        } else {
            return args[reqParameters.size() + reqParameters.indexOf(parameter)];
        }

    }

    public String getParameter(String parameter, String def) {
        try {
            return getParameter(parameter);
        } catch (NoSuchElementException e) {
            return def;
        }
    }

    public <T> T getParameter(String parameter, Class<T> adapter) {
        return Services.getProvider(ObjectFactory.class).adapt(getParameter(parameter), adapter);
    }

    public String getExclusivityMessage() {
        return "&cSomente jogadores podem utilizar este comando.";
    }

    public String getPermissionMessage() {
        return "&cVocê não tem permissão para utilizar este comando.";
    }

    public String getInvalidChildMessage() {
        return "&cSubComando não encontrado.";
    }

    @Override
    public CommandSender getSender() {
        return commandSender;
    }

    public abstract void run(CommandSender commandSender, String[] args);

}
