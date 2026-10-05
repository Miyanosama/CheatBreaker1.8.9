package io.netty.util;

public interface Attribute<T> {
   AttributeKey<T> key();

   T get();

   void remove();

   void set(T var1);

   T setIfAbsent(T var1);

   T getAndSet(T var1);

   boolean compareAndSet(T var1, T var2);

   T getAndRemove();
}
