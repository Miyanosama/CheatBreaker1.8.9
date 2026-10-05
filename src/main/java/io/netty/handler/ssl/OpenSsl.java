package io.netty.handler.ssl;

import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.util.internal.NativeLibraryLoader;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import net.minecraft.command.CommandBlockData;
import org.apache.tomcat.jni.Library;
import org.apache.tomcat.jni.SSL;

public class OpenSsl {
   public static final String IGNORABLE_ERROR_PREFIX = "error:00000000:";
   public static Throwable UNAVAILABILITY_CAUSE;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(OpenSsl.class);

   public static void ensureAvailability() {
      if (UNAVAILABILITY_CAUSE != null) {
         throw (Error)new UnsatisfiedLinkError("failed to load the required native library").initCause(UNAVAILABILITY_CAUSE);
      }
   }

   public static Throwable unavailabilityCause() {
      return UNAVAILABILITY_CAUSE;
   }

   static {
      Throwable var0 = null;

      try {
         NativeLibraryLoader.load("netty-tcnative", SSL.class.getClassLoader());
         Library.initialize("provided");
         SSL.initialize(null);
      } catch (Throwable var2) {
         var0 = var2;
         logger.debug("Failed to load netty-tcnative; " + OpenSslEngine.class.getSimpleName() + " will be unavailable.", var2);
      }

      UNAVAILABILITY_CAUSE = var0;
   }

   public static boolean isAvailable() {
      return UNAVAILABILITY_CAUSE == null;
   }
}
