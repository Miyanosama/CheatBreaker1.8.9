package net.minecraft.util;

import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class MapPopulator {
   public static <K, V> Map<K, V> createMap(Iterable<K> var0, Iterable<V> var1) {
      return populateMap(var0, var1, Maps.newLinkedHashMap());
   }

   public static <K, V> Map<K, V> populateMap(Iterable<K> var0, Iterable<V> var1, Map<K, V> var2) {
      Iterator var3 = var1.iterator();

      for (Object var5 : var0) {
         var2.put((K)var5, (V)var3.next());
      }

      if (var3.hasNext()) {
         throw new NoSuchElementException();
      } else {
         return var2;
      }
   }
}
