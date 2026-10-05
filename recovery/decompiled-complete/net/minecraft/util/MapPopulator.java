package net.minecraft.util;

import com.google.common.collect.Maps;
import io.netty.handler.codec.http.multipart.HttpPostRequestEncoder$WrappedFullHttpRequest;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class MapPopulator {
   public HttpPostRequestEncoder$WrappedFullHttpRequest field_0000;

   public static <K, V> Map<K, V> createMap(Iterable<K> var0, Iterable<V> var1) {
      return populateMap(var0, var1, Maps.newLinkedHashMap());
   }

   public static <K, V> Map<K, V> populateMap(Iterable<K> var0, Iterable<V> var1, Map<K, V> var2) {
      Iterator var3 = var1.iterator();

      for (Object var5 : var0) {
         var2.put(var5, var3.next());
      }

      if (var3.hasNext()) {
         throw new NoSuchElementException();
      } else {
         return var2;
      }
   }
}
