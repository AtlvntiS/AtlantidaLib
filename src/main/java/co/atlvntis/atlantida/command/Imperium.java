package co.atlvntis.atlantida.command;

import org.bukkit.command.CommandSender;

import java.util.List;

public interface Imperium {

    String getName();

    void setParent(Imperium parent);
    List<String> getReqParameters();
    List<String> getOptParameters();

    int minArgs();
    int maxArgs();
    String usage();

    boolean execute(CommandSender commandSender, String s, String[] strings);
    void exec(ImperiumSender sender, ImperiumArgs args);

}
