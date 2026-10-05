package org.slf4j;

import java.io.Closeable;
import net.minecraft.crash.CrashReport$1;

public class MDC$MDCCloseable implements Closeable {
   public CrashReport$1 field_0000;
   public String key;

   public MDC$MDCCloseable(String var1) {
      this.key = var1;
   }

   @Override
   public void close() {
      MDC.remove(this.key);
   }
}
