package net.optifine.util;

import io.netty.handler.codec.spdy.SpdyHeaderBlockRawEncoder;
import java.lang.reflect.Method;
import net.minecraft.src.Config;

public class NativeMemory$1 implements LongSupplier {
   public boolean disabled;
   public SpdyHeaderBlockRawEncoder field_0003;

   public NativeMemory$1(Method var1, Object var2) {
      this.val$methodF = var1;
      this.val$objectF = var2;
      super();
      this.disabled = false;
   }

   @Override
   public long getAsLong() {
      if (this.disabled) {
         return -1L & -1L;
      } else {
         try {
            return (Long)this.val$methodF.invoke(this.val$objectF);
         } catch (Throwable var2) {
            Config.warn("" + var2.getClass().getName() + ": " + var2.getMessage());
            this.disabled = true;
            return -1L & -1L;
         }
      }
   }
}
