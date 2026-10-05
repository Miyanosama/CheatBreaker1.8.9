package net.minecraft.util;

public interface IRegistry<K, V> extends Iterable<V> {
   void putObject(K var1, V var2);

   V getObject(K var1);
}
