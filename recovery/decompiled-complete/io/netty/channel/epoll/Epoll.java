package io.netty.channel.epoll;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$MapReduceKeysToDoubleTask;
import net.optifine.shaders.config.ShaderPackParser$1;

public class Epoll {
   public ShaderPackParser$1 __junk246075690408741763;
   public static Throwable UNAVAILABILITY_CAUSE;
   public ConcurrentHashMapV8$MapReduceKeysToDoubleTask __junk804908437491207751;

   static {
      Throwable var0 = null;
      int var1 = -1;
      int var2 = -1;

      try {
         var1 = Native.epollCreate();
         var2 = Native.eventFd();
      } catch (Throwable var16) {
         var0 = var16;
      } finally {
         if (var1 != -1) {
            try {
               Native.close(var1);
            } catch (Exception var15) {
            }
         }

         if (var2 != -1) {
            try {
               Native.close(var2);
            } catch (Exception var14) {
            }
         }
      }

      if (var0 != null) {
         UNAVAILABILITY_CAUSE = var0;
      } else {
         UNAVAILABILITY_CAUSE = null;
      }
   }

   public static Throwable unavailabilityCause() {
      return UNAVAILABILITY_CAUSE;
   }

   public static boolean isAvailable() {
      return UNAVAILABILITY_CAUSE == null;
   }

   public static void ensureAvailability() {
      if (UNAVAILABILITY_CAUSE != null) {
         throw (Error)new UnsatisfiedLinkError("failed to load the required native library").initCause(UNAVAILABILITY_CAUSE);
      }
   }
}
