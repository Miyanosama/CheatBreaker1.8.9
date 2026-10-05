package io.netty.util.collection;

public interface IntObjectMap<V> {
   int size();

   int[] keys();

   boolean containsKey(int var1);

   V put(int var1, V var2);

   V get(int var1);

   boolean containsValue(V var1);

   V remove(int var1);

   void clear();

   Iterable<IntObjectMap$Entry<V>> entries();

   V[] values(Class<V> var1);

   void putAll(IntObjectMap<V> var1);

   boolean isEmpty();
}
