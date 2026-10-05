package org.apache.log4j.spi;

import io.netty.util.internal.chmv8.ForkJoinPool$Submitter;
import java.io.InterruptedIOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Category;
import org.apache.log4j.Level;
import org.apache.log4j.MDC;
import org.apache.log4j.NDC;
import org.apache.log4j.Priority;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.helpers.LogLog;

public class LoggingEvent implements Serializable {
   public Hashtable mdcCopy;
   public static long field_0019;
   public transient Category logger;
   public transient String fqnOfCategoryClass;
   public transient Priority level;
   public ThrowableInformation throwableInfo;
   public String ndc;
   public String categoryName;
   public ForkJoinPool$Submitter field_0006;
   public static String field_0021;
   public boolean mdcCopyLookupRequired;
   public String threadName;
   public String renderedMessage;
   public transient Object message;
   public static Class[] TO_LEVEL_PARAMS = new Class[]{int.class};
   public long timeStamp;
   public static long startTime = System.currentTimeMillis();
   public LocationInfo locationInfo;
   public static Hashtable methodCache = new Hashtable(3);
   public boolean ndcLookupRequired = true;
   public static Class class$org$apache$log4j$Level;
   public static Integer[] PARAM_ARRAY = new Integer[1];

   public String getLoggerName() {
      return this.categoryName;
   }

   public ThrowableInformation getThrowableInformation() {
      return this.throwableInfo;
   }

   public void getMDCCopy() {
      if (this.mdcCopyLookupRequired) {
         this.mdcCopyLookupRequired = false;
         Hashtable var1 = MDC.getContext();
         if (var1 != null) {
            this.mdcCopy = (Hashtable)var1.clone();
         }
      }
   }

   public long getTimeStamp() {
      return this.timeStamp;
   }

   public void writeObject(ObjectOutputStream var1) {
      this.getThreadName();
      this.getRenderedMessage();
      this.getNDC();
      this.getMDCCopy();
      this.getThrowableStrRep();
      var1.defaultWriteObject();
      this.writeLevel(var1);
   }

   public String getRenderedMessage() {
      if (this.renderedMessage == null && this.message != null) {
         if (this.message instanceof String) {
            this.renderedMessage = (String)this.message;
         } else {
            LoggerRepository var1 = this.logger.getLoggerRepository();
            if (var1 instanceof RendererSupport) {
               RendererSupport var2 = (RendererSupport)var1;
               this.renderedMessage = var2.getRendererMap().findAndRender(this.message);
            } else {
               this.renderedMessage = this.message.toString();
            }
         }
      }

      return this.renderedMessage;
   }

   public boolean locationInformationExists() {
      return this.locationInfo != null;
   }

   public void readObject(ObjectInputStream var1) {
      var1.defaultReadObject();
      this.readLevel(var1);
      if (this.locationInfo == null) {
         this.locationInfo = new LocationInfo(null, null);
      }
   }

   public LoggingEvent(String var1, Category var2, long var3, Priority var5, Object var6, Throwable var7) {
      this.mdcCopyLookupRequired = true;
      this.fqnOfCategoryClass = var1;
      this.logger = var2;
      this.categoryName = var2.getName();
      this.level = var5;
      this.message = var6;
      if (var7 != null) {
         this.throwableInfo = new ThrowableInformation(var7, var2);
      }

      this.timeStamp = var3;
   }

   public void readLevel(ObjectInputStream var1) {
      int var2 = var1.readInt();

      try {
         String var3 = (String)var1.readObject();
         if (var3 == null) {
            this.level = Level.toLevel(var2);
         } else {
            Method var4 = (Method)methodCache.get(var3);
            if (var4 == null) {
               Class var5 = Loader.loadClass(var3);
               var4 = var5.getDeclaredMethod("toLevel", TO_LEVEL_PARAMS);
               methodCache.put(var3, var4);
            }

            this.level = (Level)var4.invoke(null, new Integer(var2));
         }
      } catch (InvocationTargetException var6) {
         if (var6.getTargetException() instanceof InterruptedException || var6.getTargetException() instanceof InterruptedIOException) {
            Thread.currentThread().interrupt();
         }

         LogLog.warn("Level deserialization failed, reverting to default.", var6);
         this.level = Level.toLevel(var2);
      } catch (NoSuchMethodException var7) {
         LogLog.warn("Level deserialization failed, reverting to default.", var7);
         this.level = Level.toLevel(var2);
      } catch (IllegalAccessException var8) {
         LogLog.warn("Level deserialization failed, reverting to default.", var8);
         this.level = Level.toLevel(var2);
      } catch (RuntimeException var9) {
         LogLog.warn("Level deserialization failed, reverting to default.", var9);
         this.level = Level.toLevel(var2);
      }
   }

   public Object getMessage() {
      return this.message != null ? this.message : this.getRenderedMessage();
   }

   public LoggingEvent(String var1, Category var2, Priority var3, Object var4, Throwable var5) {
      this.mdcCopyLookupRequired = true;
      this.fqnOfCategoryClass = var1;
      this.logger = var2;
      this.categoryName = var2.getName();
      this.level = var3;
      this.message = var4;
      if (var5 != null) {
         this.throwableInfo = new ThrowableInformation(var5, var2);
      }

      this.timeStamp = System.currentTimeMillis();
   }

   public LocationInfo getLocationInformation() {
      if (this.locationInfo == null) {
         this.locationInfo = new LocationInfo(new Throwable(), this.fqnOfCategoryClass);
      }

      return this.locationInfo;
   }

   public Level getLevel() {
      return (Level)this.level;
   }

   public static long getStartTime() {
      return startTime;
   }

   public void setProperty(String var1, String var2) {
      if (this.mdcCopy == null) {
         this.getMDCCopy();
      }

      if (this.mdcCopy == null) {
         this.mdcCopy = new Hashtable();
      }

      this.mdcCopy.put(var1, var2);
   }

   public Map getProperties() {
      this.getMDCCopy();
      Object var1;
      if (this.mdcCopy == null) {
         var1 = new HashMap();
      } else {
         var1 = this.mdcCopy;
      }

      return Collections.unmodifiableMap((Map)var1);
   }

   public String getNDC() {
      if (this.ndcLookupRequired) {
         this.ndcLookupRequired = false;
         this.ndc = NDC.get();
      }

      return this.ndc;
   }

   public LoggingEvent(
      String var1, Category var2, long var3, Level var5, Object var6, String var7, ThrowableInformation var8, String var9, LocationInfo var10, Map var11
   ) {
      this.mdcCopyLookupRequired = true;
      this.fqnOfCategoryClass = var1;
      this.logger = var2;
      if (var2 != null) {
         this.categoryName = var2.getName();
      } else {
         this.categoryName = null;
      }

      this.level = var5;
      this.message = var6;
      if (var8 != null) {
         this.throwableInfo = var8;
      }

      this.timeStamp = var3;
      this.threadName = var7;
      this.ndcLookupRequired = false;
      this.ndc = var9;
      this.locationInfo = var10;
      this.mdcCopyLookupRequired = false;
      if (var11 != null) {
         this.mdcCopy = new Hashtable(var11);
      }
   }

   public String getThreadName() {
      if (this.threadName == null) {
         this.threadName = Thread.currentThread().getName();
      }

      return this.threadName;
   }

   public Set getPropertyKeySet() {
      return this.getProperties().keySet();
   }

   public String getFQNOfLoggerClass() {
      return this.fqnOfCategoryClass;
   }

   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw new NoClassDefFoundError().initCause(var2);
      }
   }

   public void writeLevel(ObjectOutputStream var1) {
      var1.writeInt(this.level.toInt());
      Class var2 = this.level.getClass();
      if (var2 == (class$org$apache$log4j$Level == null ? (class$org$apache$log4j$Level = class$("org.apache.log4j.Level")) : class$org$apache$log4j$Level)) {
         var1.writeObject(null);
      } else {
         var1.writeObject(var2.getName());
      }
   }

   public Object getMDC(String var1) {
      if (this.mdcCopy != null) {
         Object var2 = this.mdcCopy.get(var1);
         if (var2 != null) {
            return var2;
         }
      }

      return MDC.get(var1);
   }

   public Object removeProperty(String var1) {
      if (this.mdcCopy == null) {
         this.getMDCCopy();
      }

      if (this.mdcCopy == null) {
         this.mdcCopy = new Hashtable();
      }

      return this.mdcCopy.remove(var1);
   }

   public String getProperty(String var1) {
      Object var2 = this.getMDC(var1);
      String var3 = null;
      if (var2 != null) {
         var3 = var2.toString();
      }

      return var3;
   }

   public String[] getThrowableStrRep() {
      return this.throwableInfo == null ? null : this.throwableInfo.getThrowableStrRep();
   }

   public Category getLogger() {
      return this.logger;
   }
}
