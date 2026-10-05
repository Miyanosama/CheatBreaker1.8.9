package io.netty.handler.codec.serialization;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Map;

public class WeakReferenceMap<K, V> extends ReferenceMap<K, V> {

   public WeakReferenceMap(Map<K, Reference<V>> var1) {
      super(var1);
   }

   @Override
   public Reference<V> fold(V var1) {
      return new WeakReference<>((V)var1);
   }
}
