package co.atlvntis.atlantida.cache;


import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

public final class Lazy<T> {

   private volatile T value = null;

   public boolean isAbsent() {
       return value == null;
   }

   public T get() {
       if(value == null) throw new NullPointerException("value instance is null.");
       return value;
   }

   public synchronized void compute(Supplier<T> supplier) {
       value = requireNonNull(supplier.get());
   }

}