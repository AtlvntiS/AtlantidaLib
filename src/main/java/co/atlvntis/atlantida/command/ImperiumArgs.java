package co.atlvntis.atlantida.command;

import co.atlvntis.atlantida.object.BukkitObjectFactory;
import co.atlvntis.atlantida.object.ObjectFactory;
import co.atlvntis.atlantida.services.Services;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import javafx.scene.control.Tab;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class ImperiumArgs {

    private final List<ImperiumArg> args;

    public boolean hasArg(int index) {
        return args.get(index) != null;
    }

    public boolean hasArg(String paramName) {
        for(ImperiumArg arg : args) {
            if(arg.getName().equals(paramName)) return true;
        }
        return false;
    }

    public String getArg(int index) {
        ImperiumArg arg = args.get(index);
        return arg == null ? null : arg.getArg();
    }

    public String getArg(String paramName) {
        for(ImperiumArg arg : args) {
            if(arg.getName().equals(paramName)) return arg.getArg();
        }
        return null;
    }

    public String getArgOrDefault(int index, String def) {
        ImperiumArg arg = args.get(index);
        return arg == null ? def : arg.getArg();
    }

    public String getArgOrDefault(String paramName, String def) {
        for(ImperiumArg arg : args) {
            if(arg.getName().equals(paramName)) return arg.getArg();
        }
        return def;
    }

    public <T> T getAdaptedArg(int index, Class<T> adaptClass) {
        ImperiumArg arg = args.get(index);
        return arg == null ? null : Services.getProvider(ObjectFactory.class).adapt(arg.getArg(), adaptClass);
    }

    public <T> T getAdaptedArg(String paramName, Class<T> adaptClass) {
        for(ImperiumArg arg : args) {
            if(arg.getName().equals(paramName)) return Services.getProvider(ObjectFactory.class).adapt(arg.getArg(), adaptClass);
        }
        return null;
    }

    public static ImperiumArgs of(String[] args, Imperium imperium) {

        List<ImperiumArg> argMap = new ArrayList<>();

        for(int i = 0; i < args.length -1; i++) {

            if(i < imperium.getOptParameters().size() - 1) {
                argMap.add(new ImperiumArg(imperium.getOptParameters().get(i), args[i]));
            } else {
                argMap.add(new ImperiumArg(imperium.getReqParameters().get(i), args[i]));
            }

        }

        return new ImperiumArgs(argMap);

    }

}
