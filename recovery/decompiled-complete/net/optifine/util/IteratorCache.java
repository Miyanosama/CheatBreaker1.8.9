package net.optifine.util;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft$1;
import net.minecraft.client.resources.ResourcePackListEntryFound;
import net.minecraft.entity.passive.EntityWolf;
import recovered.unidentified.UnidentifiedClass1858;

public class IteratorCache {
   public UnidentifiedClass1858 field_0002;
   public ResourcePackListEntryFound field_0004;
   public static Deque<IteratorCache$IteratorReusable<Object>> dequeIterators = new ArrayDeque<>();
   public Minecraft$1 field_0003;
   public EntityWolf field_0000;

   static {
      for (int var0 = 0; var0 < 1000; var0++) {
         IteratorCache$IteratorReadOnly var1 = new IteratorCache$IteratorReadOnly();
         dequeIterators.add(var1);
      }
   }

   public static void finished(IteratorCache$IteratorReusable<Object> var0) {
      synchronized (dequeIterators) {
         if (dequeIterators.size() <= 1000) {
            var0.setList(null);
            dequeIterators.addLast(var0);
         }
      }
   }

   public static Iterator<Object> getReadOnly(List var0) {
      synchronized (dequeIterators) {
         Object var2 = dequeIterators.pollFirst();
         if (var2 == null) {
            var2 = new IteratorCache$IteratorReadOnly();
         }

         ((IteratorCache$IteratorReusable)var2).setList(var0);
         return (Iterator<Object>)var2;
      }
   }
}
