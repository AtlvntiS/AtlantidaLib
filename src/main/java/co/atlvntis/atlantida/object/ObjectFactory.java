package co.atlvntis.atlantida.object;

public interface ObjectFactory {

    <F, T> T adapt(F from, Class<T> to);

    void registerAdapter(AdapterClass<?, ?> adapterClass);

}
