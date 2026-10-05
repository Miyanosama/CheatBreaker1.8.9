package org.slf4j.event;

import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.SubstituteLogger;

public class EventRecodingLogger implements Logger {
   public Queue<SubstituteLoggingEvent> eventQueue;
   public SubstituteLogger logger;
   public String name;

   @Override
   public void info(String var1, Object... var2) {
      this.recordEvent(Level.INFO, var1, var2, null);
   }

   @Override
   public boolean method_02608() {
      return true;
   }

   @Override
   public void trace(Marker var1, String var2, Object var3) {
      this.recordEvent(Level.TRACE, var1, var2, new Object[]{var3}, null);
   }

   @Override
   public boolean method_02637() {
      return true;
   }

   @Override
   public void trace(String var1) {
      this.recordEvent(Level.TRACE, var1, null, null);
   }

   @Override
   public void info(Marker var1, String var2, Object var3, Object var4) {
      this.recordEvent(Level.INFO, var1, var2, new Object[]{var3, var4}, null);
   }

   @Override
   public void error(Marker var1, String var2, Object var3) {
      this.recordEvent(Level.ERROR, var1, var2, new Object[]{var3}, null);
   }

   @Override
   public void trace(Marker var1, String var2, Object var3, Object var4) {
      this.recordEvent(Level.TRACE, var1, var2, new Object[]{var3, var4}, null);
   }

   @Override
   public void warn(String var1, Throwable var2) {
      this.recordEvent(Level.WARN, var1, null, var2);
   }

   @Override
   public void debug(Marker var1, String var2) {
      this.recordEvent(Level.DEBUG, var1, var2, null, null);
   }

   @Override
   public void trace(String var1, Object var2, Object var3) {
      this.recordEvent(Level.TRACE, var1, new Object[]{var2, var3}, null);
   }

   @Override
   public void debug(String var1, Object var2, Object var3) {
      this.recordEvent(Level.DEBUG, var1, new Object[]{var2, var3}, null);
   }

   @Override
   public void error(Marker var1, String var2, Object... var3) {
      this.recordEvent(Level.ERROR, var1, var2, var3, null);
   }

   @Override
   public void warn(Marker var1, String var2, Object var3, Object var4) {
      this.recordEvent(Level.WARN, var1, var2, new Object[]{var3, var4}, null);
   }

   @Override
   public void info(String var1, Throwable var2) {
      this.recordEvent(Level.INFO, var1, null, var2);
   }

   @Override
   public void trace(String var1, Object var2) {
      this.recordEvent(Level.TRACE, var1, new Object[]{var2}, null);
   }

   @Override
   public void debug(Marker var1, String var2, Object... var3) {
      this.recordEvent(Level.DEBUG, var1, var2, var3, null);
   }

   @Override
   public boolean method_02614(Marker var1) {
      return true;
   }

   @Override
   public void warn(String var1, Object var2) {
      this.recordEvent(Level.WARN, var1, new Object[]{var2}, null);
   }

   @Override
   public void error(String var1, Throwable var2) {
      this.recordEvent(Level.ERROR, var1, null, var2);
   }

   @Override
   public boolean method_02655(Marker var1) {
      return true;
   }

   @Override
   public void trace(Marker var1, String var2, Throwable var3) {
      this.recordEvent(Level.TRACE, var1, var2, null, var3);
   }

   @Override
   public void trace(Marker var1, String var2) {
      this.recordEvent(Level.TRACE, var1, var2, null, null);
   }

   @Override
   public void debug(String var1, Object... var2) {
      this.recordEvent(Level.DEBUG, var1, var2, null);
   }

   public EventRecodingLogger(SubstituteLogger var1, Queue<SubstituteLoggingEvent> var2) {
      this.logger = var1;
      this.name = var1.getName();
      this.eventQueue = var2;
   }

   @Override
   public void debug(Marker var1, String var2, Object var3) {
      this.recordEvent(Level.DEBUG, var1, var2, new Object[]{var3}, null);
   }

   @Override
   public void trace(Marker var1, String var2, Object... var3) {
      this.recordEvent(Level.TRACE, var1, var2, var3, null);
   }

   @Override
   public void info(Marker var1, String var2, Object var3) {
      this.recordEvent(Level.INFO, var1, var2, new Object[]{var3}, null);
   }

   @Override
   public void debug(Marker var1, String var2, Throwable var3) {
      this.recordEvent(Level.DEBUG, var1, var2, null, var3);
   }

   @Override
   public void warn(String var1) {
      this.recordEvent(Level.WARN, var1, null, null);
   }

   @Override
   public void error(Marker var1, String var2, Throwable var3) {
      this.recordEvent(Level.ERROR, var1, var2, null, var3);
   }

   @Override
   public void warn(Marker var1, String var2, Object var3) {
      this.recordEvent(Level.WARN, var2, new Object[]{var3}, null);
   }

   @Override
   public void warn(String var1, Object... var2) {
      this.recordEvent(Level.WARN, var1, var2, null);
   }

   @Override
   public boolean method_02643(Marker var1) {
      return true;
   }

   @Override
   public void info(String var1, Object var2) {
      this.recordEvent(Level.INFO, var1, new Object[]{var2}, null);
   }

   @Override
   public void warn(Marker var1, String var2, Object... var3) {
      this.recordEvent(Level.WARN, var1, var2, var3, null);
   }

   @Override
   public void warn(Marker var1, String var2, Throwable var3) {
      this.recordEvent(Level.WARN, var1, var2, null, var3);
   }

   @Override
   public void trace(String var1, Object... var2) {
      this.recordEvent(Level.TRACE, var1, var2, null);
   }

   @Override
   public void error(String var1, Object var2, Object var3) {
      this.recordEvent(Level.ERROR, var1, new Object[]{var2, var3}, null);
   }

   @Override
   public void debug(String var1, Throwable var2) {
      this.recordEvent(Level.DEBUG, var1, null, var2);
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public boolean method_02649() {
      return true;
   }

   @Override
   public boolean method_02600(Marker var1) {
      return true;
   }

   @Override
   public void info(Marker var1, String var2) {
      this.recordEvent(Level.INFO, var1, var2, null, null);
   }

   @Override
   public void info(Marker var1, String var2, Object... var3) {
      this.recordEvent(Level.INFO, var1, var2, var3, null);
   }

   @Override
   public void info(String var1) {
      this.recordEvent(Level.INFO, var1, null, null);
   }

   @Override
   public void info(String var1, Object var2, Object var3) {
      this.recordEvent(Level.INFO, var1, new Object[]{var2, var3}, null);
   }

   @Override
   public void debug(String var1, Object var2) {
      this.recordEvent(Level.DEBUG, var1, new Object[]{var2}, null);
   }

   @Override
   public void error(String var1, Object var2) {
      this.recordEvent(Level.ERROR, var1, new Object[]{var2}, null);
   }

   @Override
   public boolean method_02622() {
      return true;
   }

   public void recordEvent(Level var1, Marker var2, String var3, Object[] var4, Throwable var5) {
      SubstituteLoggingEvent var6 = new SubstituteLoggingEvent();
      var6.setTimeStamp(System.currentTimeMillis());
      var6.setLevel(var1);
      var6.setLogger(this.logger);
      var6.setLoggerName(this.name);
      var6.setMarker(var2);
      var6.setMessage(var3);
      var6.setArgumentArray(var4);
      var6.setThrowable(var5);
      var6.setThreadName(Thread.currentThread().getName());
      this.eventQueue.add(var6);
   }

   @Override
   public void warn(Marker var1, String var2) {
      this.recordEvent(Level.WARN, var2, null, null);
   }

   @Override
   public void trace(String var1, Throwable var2) {
      this.recordEvent(Level.TRACE, var1, null, var2);
   }

   public void recordEvent(Level var1, String var2, Object[] var3, Throwable var4) {
      this.recordEvent(var1, null, var2, var3, var4);
   }

   @Override
   public void error(Marker var1, String var2) {
      this.recordEvent(Level.ERROR, var1, var2, null, null);
   }

   @Override
   public boolean method_02630(Marker var1) {
      return true;
   }

   @Override
   public void error(String var1, Object... var2) {
      this.recordEvent(Level.ERROR, var1, var2, null);
   }

   @Override
   public void debug(Marker var1, String var2, Object var3, Object var4) {
      this.recordEvent(Level.DEBUG, var1, var2, new Object[]{var3, var4}, null);
   }

   @Override
   public void error(Marker var1, String var2, Object var3, Object var4) {
      this.recordEvent(Level.ERROR, var1, var2, new Object[]{var3, var4}, null);
   }

   @Override
   public boolean isTraceEnabled() {
      return true;
   }

   @Override
   public void method_02650(String var1) {
      this.recordEvent(Level.TRACE, var1, null, null);
   }

   @Override
   public void warn(String var1, Object var2, Object var3) {
      this.recordEvent(Level.WARN, var1, new Object[]{var2, var3}, null);
   }

   @Override
   public void error(String var1) {
      this.recordEvent(Level.ERROR, var1, null, null);
   }

   @Override
   public void info(Marker var1, String var2, Throwable var3) {
      this.recordEvent(Level.INFO, var1, var2, null, var3);
   }
}
