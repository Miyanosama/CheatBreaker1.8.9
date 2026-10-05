package org.apache.log4j.pattern;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import org.apache.log4j.Category;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.apache.log4j.NDC;
import org.apache.log4j.Priority;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.RendererSupport;
import org.apache.log4j.spi.ThrowableInformation;

public class LogEvent implements Serializable {
   public ThrowableInformation throwableInfo;
   public C0BPacketEntityAction field_0019;
   public String threadName;
   public transient String fqnOfCategoryClass;
   public String renderedMessage;
   public static long field_0005;
   public transient Object message;
   public transient Category logger;
   public boolean mdcCopyLookupRequired;
   public static Integer[] PARAM_ARRAY = new Integer[1];
   public LocationInfo locationInfo;
   public String ndc;
   public Hashtable mdcCopy;
   public static long startTime = System.currentTimeMillis();
   public static Class[] TO_LEVEL_PARAMS = new Class[]{int.class};
   public boolean ndcLookupRequired = true;
   public static Hashtable methodCache = new Hashtable(3);
   public transient Priority level;
   public long timeStamp;
   public String categoryName;
   public static Class class$org$apache$log4j$Level;
   public static String field_0000;

   public Object getMDC(String var1) {
      if (this.mdcCopy != null) {
         Object var2 = this.mdcCopy.get(var1);
         if (var2 != null) {
            return var2;
         }
      }

      return MDC.get(var1);
   }

   public static long getStartTime() {
      return startTime;
   }

   public Object getMessage() {
      return this.message != null ? this.message : this.getRenderedMessage();
   }

   public void readObject(ObjectInputStream var1) {
      var1.defaultReadObject();
      this.readLevel(var1);
      if (this.locationInfo == null) {
         this.locationInfo = new LocationInfo(null, null);
      }
   }

   public ThrowableInformation getThrowableInformation() {
      return this.throwableInfo;
   }

   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw new NoClassDefFoundError().initCause(var2);
      }
   }

   public Set getPropertyKeySet() {
      return this.getProperties().keySet();
   }

   public LogEvent(String var1, Category var2, Priority var3, Object var4, Throwable var5) {
      this.mdcCopyLookupRequired = true;
      this.fqnOfCategoryClass = var1;
      this.logger = var2;
      this.categoryName = var2.getName();
      this.level = var3;
      this.message = var4;
      if (var5 != null) {
         this.throwableInfo = new ThrowableInformation(var5);
      }

      this.timeStamp = System.currentTimeMillis();
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

   public void getMDCCopy() {
      if (this.mdcCopyLookupRequired) {
         this.mdcCopyLookupRequired = false;
         Hashtable var1 = MDC.getContext();
         if (var1 != null) {
            this.mdcCopy = (Hashtable)var1.clone();
         }
      }
   }

   public String[] getThrowableStrRep() {
      return this.throwableInfo == null ? null : this.throwableInfo.getThrowableStrRep();
   }

   public String getProperty(String var1) {
      Object var2 = this.getMDC(var1);
      String var3 = null;
      if (var2 != null) {
         var3 = var2.toString();
      }

      return var3;
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

   public Level getLevel() {
      return (Level)this.level;
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

   public LogEvent(
      String var1, Logger var2, long var3, Level var5, Object var6, String var7, ThrowableInformation var8, String var9, LocationInfo var10, Map var11
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

   public String getLoggerName() {
      return this.categoryName;
   }

   public String getThreadName() {
      if (this.threadName == null) {
         this.threadName = Thread.currentThread().getName();
      }

      return this.threadName;
   }

   public LocationInfo getLocationInformation() {
      if (this.locationInfo == null) {
         this.locationInfo = new LocationInfo(new Throwable(), this.fqnOfCategoryClass);
      }

      return this.locationInfo;
   }

   public long getTimeStamp() {
      return this.timeStamp;
   }

   public LogEvent(String var1, Category var2, long var3, Priority var5, Object var6, Throwable var7) {
      this.mdcCopyLookupRequired = true;
      this.fqnOfCategoryClass = var1;
      this.logger = var2;
      this.categoryName = var2.getName();
      this.level = var5;
      this.message = var6;
      if (var7 != null) {
         this.throwableInfo = new ThrowableInformation(var7);
      }

      this.timeStamp = var3;
   }

   public String getNDC() {
      if (this.ndcLookupRequired) {
         this.ndcLookupRequired = false;
         this.ndc = NDC.get();
      }

      return this.ndc;
   }

   public String getFQNOfLoggerClass() {
      return this.fqnOfCategoryClass;
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

            PARAM_ARRAY[0] = new Integer(var2);
            this.level = (Level)var4.invoke(null, PARAM_ARRAY);
         }
      } catch (Exception var6) {
         LogLog.warn("Level deserialization failed, reverting to default.", var6);
         this.level = Level.toLevel(var2);
      }
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
}
