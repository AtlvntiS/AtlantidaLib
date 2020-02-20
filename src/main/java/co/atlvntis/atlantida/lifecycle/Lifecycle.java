package co.atlvntis.atlantida.lifecycle;

import co.atlvntis.atlantida.exception.StateErrorException;

public interface Lifecycle extends Comparable<BaseLifecycle> {

    void load() throws StateErrorException;
    void enable() throws StateErrorException;
    void disable() throws StateErrorException;

    int getPriority();

}
