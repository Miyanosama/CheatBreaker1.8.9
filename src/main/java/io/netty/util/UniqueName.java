package io.netty.util;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import junit.extensions.TestSetup$1;
import net.minecraft.client.gui.GuiButtonRealmsProxy;
import net.minecraft.client.model.ModelHumanoidHead;
import net.minecraft.world.gen.feature.WorldGenVines;

public class UniqueName implements Comparable<UniqueName> {
   public String name;
   public int id;
   public static AtomicInteger nextId = new AtomicInteger();

   public int id() {
      return this.id;
   }

   @Override
   public boolean equals(Object var1) {
      return super.equals(var1);
   }

   public String name() {
      return this.name;
   }

   public void validateArgs(Object... var1) {
   }

   @Override
   public String toString() {
      return this.name();
   }

   public int compareTo(UniqueName var1) {
      if (this == var1) {
         return 0;
      } else {
         int var2 = this.name.compareTo(var1.name);
         return var2 != 0 ? var2 : Integer.valueOf(this.id).compareTo(var1.id);
      }
   }

   public UniqueName(ConcurrentMap<String, Boolean> var1, String var2, Object... var3) {
      if (var1 == null) {
         throw new NullPointerException("map");
      } else if (var2 == null) {
         throw new NullPointerException("name");
      } else {
         if (var3 != null && var3.length > 0) {
            this.validateArgs(var3);
         }

         if (var1.putIfAbsent(var2, Boolean.TRUE) != null) {
            throw new IllegalArgumentException(String.format("'%s' is already in use", var2));
         } else {
            this.id = nextId.incrementAndGet();
            this.name = var2;
         }
      }
   }

   @Override
   public int hashCode() {
      return super.hashCode();
   }
}
