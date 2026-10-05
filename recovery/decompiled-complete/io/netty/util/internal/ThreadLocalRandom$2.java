package io.netty.util.internal;

import io.netty.util.internal.chmv8.ForkJoinPool$WorkQueue;
import java.lang.Thread.UncaughtExceptionHandler;
import net.minecraft.init.Bootstrap$4;

public class ThreadLocalRandom$2 implements UncaughtExceptionHandler {
   public Bootstrap$4 __junk6993250408803294583;
   public ForkJoinPool$WorkQueue __junk4596798097230484844;

   @Override
   public void uncaughtException(Thread var1, Throwable var2) {
      ThreadLocalRandom.access$000().debug("An exception has been raised by {}", var1.getName(), var2);
   }
}
