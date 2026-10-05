package org.apache.log4j.helpers;

import java.io.InterruptedIOException;
import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.LoggingEvent;

public class OnlyOnceErrorHandler implements ErrorHandler {
   public boolean firstTime;
   public String WARN_PREFIX = "log4j warning: ";
   public String ERROR_PREFIX = "log4j error: ";

   public OnlyOnceErrorHandler() {
      this.firstTime = true;
   }

   public void setAppender(Appender var1) {
   }

   public void error(String var1) {
      if (this.firstTime) {
         LogLog.error(var1);
         this.firstTime = false;
      }
   }

   public void setBackupAppender(Appender var1) {
   }

   public void error(String var1, Exception var2, int var3, LoggingEvent var4) {
      if (var2 instanceof InterruptedIOException || var2 instanceof InterruptedException) {
         Thread.currentThread().interrupt();
      }

      if (this.firstTime) {
         LogLog.error(var1, var2);
         this.firstTime = false;
      }
   }

   public void activateOptions() {
   }

   public void error(String var1, Exception var2, int var3) {
      this.error(var1, var2, var3, null);
   }

   public void setLogger(Logger var1) {
   }
}
