package co.atlvntis.atlantida.commands;

import co.atlvntis.atlantida.AtlantidaPlugin;
import co.atlvntis.atlantida.exceptions.MalformedCommandLineException;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
abstract class AbstractImperium extends BukkitCommand implements BaseImperium {

    protected final AtlantidaPlugin plugin;

    @Setter
    protected AbstractImperium parent;

    protected List<String> aliases;
    protected List<String> reqParameters;
    protected List<String> optParameters;
    protected String childrenName;
    protected List<AbstractImperium> children;

    protected boolean useChildren = false;

    protected AbstractImperium(AtlantidaPlugin plugin, String commandLine) {

        super(getName(commandLine));

        this.plugin = plugin;

        this.parent = null;

        this.reqParameters = new ArrayList<>();
        this.optParameters = new ArrayList<>();
        this.children = new ArrayList<>();

        resolve(commandLine);

        setAliases(aliases.subList(1, aliases.size()));

    }

    private void resolve(String commandLine) {

        String processedCommandLine = commandLine.trim();

        if(!processedCommandLine.contains(" ")) {

            resolveAliases(processedCommandLine);

        } else {

            String[] processedCommandLineSplit = processedCommandLine.split(" ");

            resolveAliases(processedCommandLineSplit[0]);

            for(String string : Arrays.copyOfRange(processedCommandLineSplit, 1, processedCommandLine.length())) {
                resolveArgument(string);
            }

        }

    }

    private void resolveAliases(String string) {

        if(string.contains("|")) {

            this.aliases = Arrays.stream(string.split("\\|")).filter(str -> (!str.isEmpty())).collect(Collectors.toList());

        } else {

            this.aliases = Collections.singletonList(string);

        }

    }

    private void resolveArgument(String arg) {

        if(arg.startsWith("p:")) {

            if(arg.endsWith("?")) {
                optParameters.add(arg.replace("?", "").replace("p:", ""));
            } else {
                if(!optParameters.isEmpty()) throw new MalformedCommandLineException("The command line can't have a required parameter after an optional one.");
                reqParameters.add(arg.replace("p:", ""));
            }

        } else if(arg.startsWith("c:")) {

            if(!optParameters.isEmpty()) throw new MalformedCommandLineException("The command line can't have a required parameter after an optional one.");
            if(useChildren) throw new MalformedCommandLineException("The command line can't have more than one child reference.");

            childrenName = arg.replace("c:", "");
            useChildren = true;

        } else throw new MalformedCommandLineException("The command line failed to resolve the argument '" + arg + "'.");

    }

    protected int getMinArgs() {
        return reqParameters.size() + (useChildren ? 1 : 0);
    }

    protected int getMaxArgs() {
        return reqParameters.size() + optParameters.size();
    }

    public final String getUsage() {
        StringBuilder stringBuilder = new StringBuilder(parent != null ? parent.getUsage()  + " " : "/");
        stringBuilder.append(aliases.get(0));
        for(String parameter : reqParameters) {
            stringBuilder.append(" <").append(parameter).append(">");
        }
        for(String parameter : optParameters) {
            stringBuilder.append(" (").append(parameter).append(")");
        }
        if(useChildren) stringBuilder.append(" <").append(childrenName).append(">");
        return stringBuilder.toString();
    }

    public String getUsageMessage() {
        return "&cUse " + getUsage() + ".";
    }

    public void addChild(AbstractImperium abstractImperium) {
        abstractImperium.setParent(this);
        children.add(abstractImperium);
    }

    @Override
    public abstract boolean execute(CommandSender commandSender, String s, String[] args);

    private static String getName(String commandLine) {
        String processedCommandLine = commandLine.trim();

        if(!processedCommandLine.contains(" ")) {

            if(!processedCommandLine.contains("|")) return processedCommandLine;
            else return processedCommandLine.split("\\|")[0];

        } else {

            String processedCommandLineSplit = processedCommandLine.split(" ")[0];

            if(!processedCommandLineSplit.contains("|")) return processedCommandLine;
            else return processedCommandLineSplit.split("\\|")[0];

        }
    }

}
