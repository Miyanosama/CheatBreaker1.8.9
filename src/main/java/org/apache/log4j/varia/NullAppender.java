package org.apache.log4j.varia;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

public class NullAppender extends AppenderSkeleton {
   public static NullAppender instance = new NullAppender();

   public void activateOptions() {
   }

   public void append(LoggingEvent var1) {
   }

   public boolean requiresLayout() {
      return false;
   }

   public void close() {
   }

   public NullAppender method_08782() {
      return instance;
   }

   public static NullAppender method_08784() {
      return instance;
   }

   public void doAppend(LoggingEvent var1) {
   }
}
