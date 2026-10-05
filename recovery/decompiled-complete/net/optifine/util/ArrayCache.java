package net.optifine.util;

import com.cheatbreaker.client.util.worldborder.WorldBorderManager;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import net.optifine.entity.model.ModelAdapterArmorStand;
import recovered.unidentified.UnidentifiedClass1187;

public class ArrayCache {
   public Class elementClass = null;
   public int maxCacheSize = 0;
   public UnidentifiedClass1187 field_0002;
   public WorldBorderManager field_0004;
   public ModelAdapterArmorStand field_0000;
   public ArrayDeque cache = new ArrayDeque();

   public synchronized Object allocate(int var1) {
      Object var2 = this.cache.pollLast();
      if (var2 == null || Array.getLength(var2) < var1) {
         var2 = Array.newInstance(this.elementClass, var1);
      }

      return var2;
   }

   public ArrayCache(Class var1, int var2) {
      this.elementClass = var1;
      this.maxCacheSize = var2;
   }

   public synchronized void free(Object var1) {
      if (var1 != null) {
         Class var2 = var1.getClass();
         if (var2.getComponentType() != this.elementClass) {
            throw new IllegalArgumentException("Wrong component type");
         }

         if (this.cache.size() < this.maxCacheSize) {
            this.cache.add(var1);
         }
      }
   }
}
