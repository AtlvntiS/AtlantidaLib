package co.atlvntis.atlantida.object;

import java.lang.reflect.ParameterizedType;

public interface AdapterClass<F, T> {

    T adapt(F from);

    default Class<F> from() {
        return (Class<F>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    default Class<T> to() {
        return (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];
    }

}
