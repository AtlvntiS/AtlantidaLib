package co.atlvntis.atlantida.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ImperiumArg {

    private final String name;
    private final String arg;

    @Override
    public String toString() {
        return String.format("ImperiumArg {name: %s, value: %s}", name, arg);
    }
}
