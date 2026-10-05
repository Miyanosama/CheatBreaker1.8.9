package org.slf4j.helpers;

import io.netty.channel.group.CombinedIterator;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.event.EventRecodingLogger;
import org.slf4j.event.LoggingEvent;
import org.slf4j.event.SubstituteLoggingEvent;

public class SubstituteLogger implements Logger {
   public boolean createdPostInitialization;
   public volatile Logger _delegate;
   public CombinedIterator field_0005;
   public EventRecodingLogger eventRecodingLogger;
   public Queue<SubstituteLoggingEvent> eventQueue;
   public String name;
   public Method logMethodCache;
   public Boolean delegateEventAware;

   public void log(LoggingEvent var1) {
      if (this.isDelegateEventAware()) {
         try {
            this.logMethodCache.invoke(this._delegate, var1);
         } catch (IllegalAccessException var3) {
         } catch (IllegalArgumentException var4) {
         } catch (InvocationTargetException var5) {
         }
      }
   }

   @Override
   public void error(String var1, Object var2) {
      this.delegate().error(var1, var2);
   }

   @Override
   public void info(Marker var1, String var2) {
      this.delegate().info(var1, var2);
   }

   @Override
   public void error(String var1, Throwable var2) {
      this.delegate().error(var1, var2);
   }

   @Override
   public void info(Marker var1, String var2, Object var3) {
      this.delegate().info(var1, var2, var3);
   }

   @Override
   public void trace(Marker var1, String var2, Object var3) {
      this.delegate().trace(var1, var2, var3);
   }

   @Override
   public void error(Marker var1, String var2, Object var3, Object var4) {
      this.delegate().error(var1, var2, var3, var4);
   }

   @Override
   public void debug(Marker var1, String var2) {
      this.delegate().debug(var1, var2);
   }

   @Override
   public void error(Marker var1, String var2, Throwable var3) {
      this.delegate().error(var1, var2, var3);
   }

   @Override
   public void debug(String var1, Throwable var2) {
      this.delegate().debug(var1, var2);
   }

   @Override
   public void info(String var1) {
      this.delegate().info(var1);
   }

   @Override
   public boolean method_02600(Marker var1) {
      return this.delegate().method_02600(var1);
   }

   @Override
   public void warn(Marker var1, String var2) {
      this.delegate().warn(var1, var2);
   }

   @Override
   public void trace(String var1) {
      this.delegate().trace(var1);
   }

   @Override
   public void error(String var1, Object var2, Object var3) {
      this.delegate().error(var1, var2, var3);
   }

   @Override
   public void method_02650(String var1) {
      this.delegate().method_02650(var1);
   }

   @Override
   public boolean isTraceEnabled() {
      return this.delegate().isTraceEnabled();
   }

   @Override
   public void info(String var1, Object var2, Object var3) {
      this.delegate().info(var1, var2, var3);
   }

   @Override
   public boolean method_02608() {
      return this.delegate().method_02608();
   }

   @Override
   public void info(String var1, Object... var2) {
      this.delegate().info(var1, var2);
   }

   @Override
   public void warn(Marker var1, String var2, Object var3, Object var4) {
      this.delegate().warn(var1, var2, var3, var4);
   }

   @Override
   public boolean method_02637() {
      return this.delegate().method_02637();
   }

   @Override
   public void debug(Marker var1, String var2, Object var3) {
      this.delegate().debug(var1, var2, var3);
   }

   public void setDelegate(Logger var1) {
      this._delegate = var1;
   }

   @Override
   public void warn(Marker var1, String var2, Throwable var3) {
      this.delegate().warn(var1, var2, var3);
   }

   @Override
   public void trace(String var1, Object var2) {
      this.delegate().trace(var1, var2);
   }

   @Override
   public void error(Marker var1, String var2) {
      this.delegate().error(var1, var2);
   }

   @Override
   public void debug(Marker var1, String var2, Object... var3) {
      this.delegate().debug(var1, var2, var3);
   }

   @Override
   public void trace(String var1, Throwable var2) {
      this.delegate().trace(var1, var2);
   }

   @Override
   public void warn(Marker var1, String var2, Object... var3) {
      this.delegate().warn(var1, var2, var3);
   }

   @Override
   public void warn(String var1, Object var2) {
      this.delegate().warn(var1, var2);
   }

   @Override
   public void trace(Marker var1, String var2) {
      this.delegate().trace(var1, var2);
   }

   @Override
   public void info(String var1, Throwable var2) {
      this.delegate().info(var1, var2);
   }

   @Override
   public void debug(Marker var1, String var2, Throwable var3) {
      this.delegate().debug(var1, var2, var3);
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public boolean method_02643(Marker var1) {
      return this.delegate().method_02643(var1);
   }

   @Override
   public void error(String var1, Object... var2) {
      this.delegate().error(var1, var2);
   }

   @Override
   public void debug(String var1, Object var2) {
      this.delegate().debug(var1, var2);
   }

   @Override
   public void debug(Marker var1, String var2, Object var3, Object var4) {
      this.delegate().debug(var1, var2, var3, var4);
   }

   @Override
   public void error(String var1) {
      this.delegate().error(var1);
   }

   @Override
   public void warn(Marker var1, String var2, Object var3) {
      this.delegate().warn(var1, var2, var3);
   }

   @Override
   public void error(Marker var1, String var2, Object var3) {
      this.delegate().error(var1, var2, var3);
   }

   @Override
   public void trace(String var1, Object... var2) {
      this.delegate().trace(var1, var2);
   }

   @Override
   public void error(Marker var1, String var2, Object... var3) {
      this.delegate().error(var1, var2, var3);
   }

   @Override
   public void info(Marker var1, String var2, Object... var3) {
      this.delegate().info(var1, var2, var3);
   }

   @Override
   public boolean method_02649() {
      return this.delegate().method_02649();
   }

   @Override
   public boolean method_02622() {
      return this.delegate().method_02622();
   }

   @Override
   public boolean method_02630(Marker var1) {
      return this.delegate().method_02630(var1);
   }

   @Override
   public void trace(Marker var1, String var2, Object... var3) {
      this.delegate().trace(var1, var2, var3);
   }

   @Override
   public void warn(String var1, Object... var2) {
      this.delegate().warn(var1, var2);
   }

   public Logger delegate() {
      if (this._delegate != null) {
         return this._delegate;
      } else {
         return (Logger)(this.createdPostInitialization ? NOPLogger.NOP_LOGGER : this.getEventRecordingLogger());
      }
   }

   @Override
   public boolean method_02655(Marker var1) {
      return this.delegate().method_02655(var1);
   }

   @Override
   public void trace(String var1, Object var2, Object var3) {
      this.delegate().trace(var1, var2, var3);
   }

   @Override
   public void info(String var1, Object var2) {
      this.delegate().info(var1, var2);
   }

   @Override
   public void info(Marker var1, String var2, Throwable var3) {
      this.delegate().info(var1, var2, var3);
   }

   @Override
   public void trace(Marker var1, String var2, Object var3, Object var4) {
      this.delegate().trace(var1, var2, var3, var4);
   }

   @Override
   public void trace(Marker var1, String var2, Throwable var3) {
      this.delegate().trace(var1, var2, var3);
   }

   public boolean isDelegateNull() {
      return this._delegate == null;
   }

   @Override
   public void debug(String var1, Object var2, Object var3) {
      this.delegate().debug(var1, var2, var3);
   }

   @Override
   public void warn(String var1, Object var2, Object var3) {
      this.delegate().warn(var1, var2, var3);
   }

   @Override
   public void warn(String var1) {
      this.delegate().warn(var1);
   }

   public SubstituteLogger(String var1, Queue<SubstituteLoggingEvent> var2, boolean var3) {
      this.name = var1;
      this.eventQueue = var2;
      this.createdPostInitialization = var3;
   }

   @Override
   public void warn(String var1, Throwable var2) {
      this.delegate().warn(var1, var2);
   }

   public boolean isDelegateEventAware() {
      if (this.delegateEventAware != null) {
         return this.delegateEventAware;
      } else {
         try {
            this.logMethodCache = this._delegate.getClass().getMethod("log", LoggingEvent.class);
            this.delegateEventAware = Boolean.TRUE;
         } catch (NoSuchMethodException var2) {
            this.delegateEventAware = Boolean.FALSE;
         }

         return this.delegateEventAware;
      }
   }

   public Logger getEventRecordingLogger() {
      if (this.eventRecodingLogger == null) {
         this.eventRecodingLogger = new EventRecodingLogger(this, this.eventQueue);
      }

      return this.eventRecodingLogger;
   }

   @Override
   public void info(Marker var1, String var2, Object var3, Object var4) {
      this.delegate().info(var1, var2, var3, var4);
   }

   @Override
   public boolean method_02614(Marker var1) {
      return this.delegate().method_02614(var1);
   }

   @Override
   public int hashCode() {
      return this.name.hashCode();
   }

   public boolean isDelegateNOP() {
      return this._delegate instanceof NOPLogger;
   }

   @Override
   public void debug(String var1, Object... var2) {
      this.delegate().debug(var1, var2);
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         SubstituteLogger var2 = (SubstituteLogger)var1;
         return this.name.equals(var2.name);
      } else {
         return false;
      }
   }
}
