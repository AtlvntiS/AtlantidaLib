package co.atlvntis.atlantida.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Getter
@RequiredArgsConstructor
public class ImperiumArgs {

    private final Map<String, String> args;

    public static ImperiumArgs of(String[] args, Imperium imperium) {

        Map<String, String> argMap = new TreeMap<>();

        for(int i = 0; i < args.length -1; i++) {

            if(i < imperium.getOptParameters().size() - 1) {
                argMap.put(args[i], imperium.getReqParameters().get(i));
            } else {
                argMap.put(args[i], imperium.getOptParameters().get(i));
            }

        }

        return new ImperiumArgs(argMap);

    }

}
