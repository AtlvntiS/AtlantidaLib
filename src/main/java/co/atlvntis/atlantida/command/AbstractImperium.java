package co.atlvntis.atlantida.command;

import co.atlvntis.atlantida.AtlantidaPlugin;
import co.atlvntis.atlantida.exceptions.MalformedCommandLineException;
import co.atlvntis.atlantida.utils.Log;
import lombok.Setter;
import org.bukkit.command.defaults.BukkitCommand;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractImperium extends BukkitCommand implements Imperium {

    protected final AtlantidaPlugin plugin;

    protected Imperium parent;
    protected List<String> reqParameters;
    protected List<String> optParameters;
    protected boolean useChild;
    protected List<AbstractImperium> childs;

    protected AbstractImperium(AtlantidaPlugin plugin, String commandLine) {

        super(commandLine.contains(" ") ? commandLine.trim().split(" ")[0] : commandLine.trim());

        this.plugin = plugin;

        this.parent = null;
        this.reqParameters = new ArrayList<>();
        this.optParameters = new ArrayList<>();
        this.useChild = false;
        this.childs = new ArrayList<>();

        resolve(commandLine);

    }

    private void resolve(String commandLine) {

        String trimmedCommandLine = commandLine.trim();

        if(trimmedCommandLine.contains(" ")) {

            String[] trimmedSplitCommandLine = trimmedCommandLine.split(" ");

            for(int i = 1; i < trimmedSplitCommandLine.length; i++) {
                Log.info("Resolving param " + trimmedSplitCommandLine[i]);
                resolveParam(trimmedSplitCommandLine[i]);
            }

        }

    }

    private void resolveParam(String param) {

        if(useChild) throw new MalformedCommandLineException("The command line can't have any parameter after a child one.");

        if(param.startsWith(":")) {

            if(!optParameters.isEmpty()) throw new MalformedCommandLineException("The command line can't have a child parameter after an optional one.");
            reqParameters.add(param.replace(":", ""));

        } else {

            if(param.endsWith("?")) {
                optParameters.add(param.replace("?", ""));
            } else {
                if(!optParameters.isEmpty()) throw new MalformedCommandLineException("The command line can't have a required parameter after an optional one.");
                reqParameters.add(param);
            }

        }

    }

    public int minArgs() {
        return (parent != null ? parent.minArgs() : 0) + reqParameters.size();
    }

    public int maxArgs() {
        return minArgs() + optParameters.size();
    }

    public String usageMessage() {
        return "&cUtilize " + usage() + ".";
    }

    public String permissionMessage() {
        return "&cVocê não tem permissão.";
    }

    @Override
    public void setParent(Imperium parent) {
        this.parent = parent;
    }

    @Override
    public List<String> getReqParameters() {
        return reqParameters;
    }

    @Override
    public List<String> getOptParameters() {
        return optParameters;
    }

    @Override
    public final String usage() {

        StringBuilder stringBuilder = new StringBuilder(parent != null ? parent.usage()  + " " : "/");
        stringBuilder.append(getName());
        for(String parameter : reqParameters) {
            stringBuilder.append(" <").append(parameter).append(">");
        }
        for(String parameter : optParameters) {
            stringBuilder.append(" (").append(parameter).append(")");
        }
        return stringBuilder.toString();

    }

}
