package org.apache.log4j.varia;

import java.io.InterruptedIOException;
import java.util.Vector;
import net.minecraft.block.BlockAir;
import net.optifine.CrashReporter;
import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.LoggingEvent;

public class FallbackErrorHandler implements ErrorHandler {
   public Appender primary;
   public Vector loggers;
   public CrashReporter field_0001;
   public BlockAir field_0003;
   public Appender backup;

   public void setBackupAppender(Appender var1) {
      LogLog.debug("FB: Setting backup appender to [" + var1.getName() + "].");
      this.backup = var1;
   }

   public void setAppender(Appender var1) {
      LogLog.debug("FB: Setting primary appender to [" + var1.getName() + "].");
      this.primary = var1;
   }

   public void error(String var1, Exception var2, int var3, LoggingEvent var4) {
      if (var2 instanceof InterruptedIOException) {
         Thread.currentThread().interrupt();
      }

      LogLog.debug("FB: The following error reported: " + var1, var2);
      LogLog.debug("FB: INITIATING FALLBACK PROCEDURE.");
      if (this.loggers != null) {
         for (int var5 = 0; var5 < this.loggers.size(); var5++) {
            Logger var6 = (Logger)this.loggers.elementAt(var5);
            LogLog.debug("FB: Searching for [" + this.primary.getName() + "] in logger [" + var6.getName() + "].");
            LogLog.debug("FB: Replacing [" + this.primary.getName() + "] by [" + this.backup.getName() + "] in logger [" + var6.getName() + "].");
            var6.removeAppender(this.primary);
            LogLog.debug("FB: Adding appender [" + this.backup.getName() + "] to logger " + var6.getName());
            var6.addAppender(this.backup);
         }
      }
   }

   public void setLogger(Logger var1) {
      LogLog.debug("FB: Adding logger [" + var1.getName() + "].");
      if (this.loggers == null) {
         this.loggers = new Vector();
      }

      this.loggers.addElement(var1);
   }

   public void error(String var1, Exception var2, int var3) {
      this.error(var1, var2, var3, null);
   }

   public void activateOptions() {
   }

   public void error(String var1) {
   }
}
