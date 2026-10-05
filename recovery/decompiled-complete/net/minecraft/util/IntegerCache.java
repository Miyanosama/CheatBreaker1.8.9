package net.minecraft.util;

import io.netty.channel.nio.AbstractNioChannel;
import io.netty.handler.codec.http.DefaultHttpMessage;
import net.minecraft.block.BlockSkull$1;

public class IntegerCache {
   public AbstractNioChannel field_0002;
   public EnchantmentNameParts field_0004;
   public DefaultHttpMessage field_0001;
   public static Integer[] CACHE = new Integer[65535];
   public BlockSkull$1 field_0000;

   static {
      int var0 = 0;

      for (int var1 = CACHE.length; var0 < var1; var0++) {
         CACHE[var0] = var0;
      }
   }

   public static Integer getInteger(int var0) {
      return var0 >= 0 && var0 < CACHE.length ? CACHE[var0] : new Integer(var0);
   }
}
