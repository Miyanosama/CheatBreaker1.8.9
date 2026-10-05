package org.slf4j.event;

import org.slf4j.Marker;
import org.slf4j.helpers.SubstituteLogger;

public class SubstituteLoggingEvent implements LoggingEvent {
   public Throwable throwable;
   public String recoveredField2379;
   public long timeStamp;
   public SubstituteLogger logger;
   public Marker marker;
   public String recoveredField2380;
   public String recoveredField2381;
   public Object[] argArray;
   public Level level;

   public void setThreadName(String var1) {
      this.recoveredField2379 = var1;
   }

   @Override
   public Marker getMarker() {
      return this.marker;
   }

   public void setLogger(SubstituteLogger var1) {
      this.logger = var1;
   }

   public void setThrowable(Throwable var1) {
      this.throwable = var1;
   }

   public void setArgumentArray(Object[] var1) {
      this.argArray = var1;
   }

   public void setTimeStamp(long var1) {
      this.timeStamp = var1;
   }

   public void setLevel(Level var1) {
      this.level = var1;
   }

   @Override
   public String method_07030() {
      return this.recoveredField2379;
   }

   @Override
   public Level getLevel() {
      return this.level;
   }

   @Override
   public String method_07031() {
      return this.recoveredField2380;
   }

   public void setLoggerName(String var1) {
      this.recoveredField2380 = var1;
   }

   @Override
   public Throwable getThrowable() {
      return this.throwable;
   }

   public void setMarker(Marker var1) {
      this.marker = var1;
   }

   @Override
   public Object[] getArgumentArray() {
      return this.argArray;
   }

   public void setMessage(String var1) {
      this.recoveredField2381 = var1;
   }

   public SubstituteLogger getLogger() {
      return this.logger;
   }

   @Override
   public String method_07025() {
      return this.recoveredField2381;
   }

   @Override
   public long getTimeStamp() {
      return this.timeStamp;
   }
}
