package net.minecraft.util;

import java.io.OutputStream;
import java.io.PrintStream;
import net.optifine.reflect.ReflectorResolver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingPrintStream extends PrintStream {
   public String domain;
   public ReflectorResolver field_0002;
   public static Logger LOGGER = LogManager.getLogger();

   public void logString(String var1) {
      StackTraceElement[] var2 = Thread.currentThread().getStackTrace();
      StackTraceElement var3 = var2[Math.min(3, var2.length)];
      LOGGER.info("[{}]@.({}:{}): {}", new Object[]{this.domain, var3.getFileName(), var3.getLineNumber(), var1});
   }

   @Override
   public void println(Object var1) {
      this.logString(String.valueOf(var1));
   }

   @Override
   public void println(String var1) {
      this.logString(var1);
   }

   public LoggingPrintStream(String var1, OutputStream var2) {
      super(var2);
      this.domain = var1;
   }
}
