package co.atlvntis.atlantida.adapter;

public interface Adapter {

    <F, T> T adapt(F from, Class<? extends T> to);

    void registerAdapter(AdapterClass<?, ?> adapterClass);

}
