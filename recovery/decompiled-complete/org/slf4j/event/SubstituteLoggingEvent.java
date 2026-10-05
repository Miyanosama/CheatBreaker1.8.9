package org.slf4j.event;

import net.minecraft.command.server.CommandStop;
import org.apache.log4j.PatternLayout;
import org.slf4j.Marker;
import org.slf4j.helpers.SubstituteLogger;
import recovered.unidentified.UnidentifiedClass5123;

public class SubstituteLoggingEvent implements LoggingEvent {
   public CommandStop field_0005;
   public Throwable throwable;
   public String field_0004;
   public long timeStamp;
   public SubstituteLogger logger;
   public PatternLayout field_0002;
   public UnidentifiedClass5123 field_0010;
   public Marker marker;
   public String field_0003;
   public String field_0011;
   public Object[] argArray;
   public Level level;

   public void setThreadName(String var1) {
      this.field_0004 = var1;
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
      return this.field_0004;
   }

   @Override
   public Level getLevel() {
      return this.level;
   }

   @Override
   public String method_07031() {
      return this.field_0003;
   }

   public void setLoggerName(String var1) {
      this.field_0003 = var1;
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
      this.field_0011 = var1;
   }

   public SubstituteLogger getLogger() {
      return this.logger;
   }

   @Override
   public String method_07025() {
      return this.field_0011;
   }

   @Override
   public long getTimeStamp() {
      return this.timeStamp;
   }
}
