package org.apache.log4j;

import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OnlyOnceErrorHandler;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.OptionHandler;

public abstract class AppenderSkeleton implements OptionHandler, Appender {
   public ErrorHandler errorHandler = new OnlyOnceErrorHandler();
   public Priority threshold;
   public Filter headFilter;
   public boolean closed = false;
   public String name;
   public Filter tailFilter;
   public Layout layout;

   public Filter method_23191() {
      return this.headFilter;
   }

   public AppenderSkeleton(boolean var1) {
   }

   public void clearFilters() {
      this.headFilter = this.tailFilter = null;
   }

   public ErrorHandler getErrorHandler() {
      return this.errorHandler;
   }

   public void addFilter(Filter var1) {
      if (this.headFilter == null) {
         this.headFilter = this.tailFilter = var1;
      } else {
         this.tailFilter.setNext(var1);
         this.tailFilter = var1;
      }
   }

   public void finalize() {
      if (!this.closed) {
         LogLog.debug("Finalizing appender named [" + this.name + "].");
         this.close();
      }
   }

   public Layout getLayout() {
      return this.layout;
   }

   public boolean isAsSevereAsThreshold(Priority var1) {
      return this.threshold == null || var1.isGreaterOrEqual(this.threshold);
   }

   public abstract void append(LoggingEvent var1);

   public synchronized void setErrorHandler(ErrorHandler var1) {
      if (var1 == null) {
         LogLog.warn("You have tried to set a null error-handler.");
      } else {
         this.errorHandler = var1;
      }
   }

   public void setLayout(Layout var1) {
      this.layout = var1;
   }

   public void activateOptions() {
   }

   public String getName() {
      return this.name;
   }

   public Filter getFilter() {
      return this.headFilter;
   }

   public void setThreshold(Priority var1) {
      this.threshold = var1;
   }

   public Priority getThreshold() {
      return this.threshold;
   }

   public synchronized void doAppend(LoggingEvent var1) {
      if (this.closed) {
         LogLog.error("Attempted to append to closed appender named [" + this.name + "].");
      } else if (this.isAsSevereAsThreshold(var1.getLevel())) {
         Filter var2 = this.headFilter;

         label26:
         while (var2 != null) {
            switch (var2.decide(var1)) {
               case -1:
                  return;
               case 0:
                  var2 = var2.getNext();
                  break;
               case 1:
                  break label26;
            }
         }

         this.append(var1);
         return;
      }
   }

   public AppenderSkeleton() {
   }

   public void setName(String var1) {
      this.name = var1;
   }
}
