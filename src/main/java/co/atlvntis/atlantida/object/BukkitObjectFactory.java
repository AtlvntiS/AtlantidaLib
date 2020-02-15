package co.atlvntis.atlantida.object;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BukkitObjectFactory implements ObjectFactory {

    private final Table<Class<?>, Class<?>, AdapterClass<?, ?>> adapters;

    public BukkitObjectFactory() {
        this.adapters = HashBasedTable.create();
    }

    @Override
    public <F, T> T adapt(F from, Class<T> to) {

        if(from.getClass().equals(to)) return (T) from;

        if(!this.adapters.contains(from.getClass(), to)) {
            throw new IllegalArgumentException("No adapters found for " + from.getClass() + " -> " + to + ".");
        }

        try {
            return ((AdapterClass<F, T>)this.adapters.get(from.getClass(), to)).adapt(from);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void registerAdapter(AdapterClass<?, ?> adapterClass) {
        this.adapters.put(adapterClass.from(), adapterClass.to(), adapterClass);
    }
}
