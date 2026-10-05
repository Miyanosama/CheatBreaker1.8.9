package io.netty.util;

import io.netty.channel.udt.nio.NioUdtProvider$1;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import junit.extensions.TestSetup$1;
import net.minecraft.client.gui.GuiButtonRealmsProxy;
import net.minecraft.client.model.ModelHumanoidHead;
import net.minecraft.profiler.PlayerUsageSnooper$1;
import net.minecraft.world.gen.feature.WorldGenVines;

public class UniqueName implements Comparable<UniqueName> {
   public NioUdtProvider$1 __junk2594881899661363420;
   public String name;
   public GuiButtonRealmsProxy __junk8550019603222766219;
   public TestSetup$1 __junk3862338381414576393;
   public ModelHumanoidHead __junk3105947949017239936;
   public int id;
   public PlayerUsageSnooper$1 __junk6272716531145329048;
   public static AtomicInteger nextId = new AtomicInteger();
   public WorldGenVines __junk2694992518640463747;

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
