package io.netty.util.internal;

import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import net.minecraft.client.renderer.block.model.ModelBlockDefinition$MissingVariantException;
import sun.misc.Cleaner;

public class Cleaner0 {
   public ModelBlockDefinition$MissingVariantException __junk7147469873286608109;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(Cleaner0.class);
   public static long CLEANER_FIELD_OFFSET;

   static {
      ByteBuffer var0 = ByteBuffer.allocateDirect(1);
      long var2 = -1L & -1L;
      if (PlatformDependent0.hasUnsafe()) {
         try {
            Field var1 = var0.getClass().getDeclaredField("cleaner");
            var1.setAccessible(true);
            Cleaner var4 = (Cleaner)var1.get(var0);
            var4.clean();
            var2 = PlatformDependent0.objectFieldOffset(var1);
         } catch (Throwable var5) {
            var2 = -1L & -1L;
         }
      }

      logger.debug("java.nio.ByteBuffer.cleaner(): {}", var2 != (-1L & -1L) ? "available" : "unavailable");
      CLEANER_FIELD_OFFSET = var2;
      freeDirectBuffer(var0);
   }

   public static void freeDirectBuffer(ByteBuffer var0) {
      if (CLEANER_FIELD_OFFSET != (-1L & -1L) && var0.isDirect()) {
         try {
            Cleaner var1 = (Cleaner)PlatformDependent0.getObject(var0, CLEANER_FIELD_OFFSET);
            if (var1 != null) {
               var1.clean();
            }
         } catch (Throwable var2) {
         }
      }
   }
}
