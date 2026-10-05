package org.apache.log4j.net;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import org.apache.log4j.helpers.LogLog;

public class ZeroConfSupport {
   public static Class serviceInfoClass;
   public static Class jmDNSClass;
   public static Object jmDNS = initializeJMDNS();
   public static Class class$java$util$Hashtable;
   public Object serviceInfo;
   public static Class class$java$util$Map;
   public static Class class$java$lang$String;

   public void advertise() {
      try {
         Method var1 = jmDNSClass.getMethod("registerService", serviceInfoClass);
         var1.invoke(jmDNS, this.serviceInfo);
         LogLog.debug("registered serviceInfo: " + this.serviceInfo);
      } catch (IllegalAccessException var2) {
         LogLog.warn("Unable to invoke registerService method", var2);
      } catch (NoSuchMethodException var3) {
         LogLog.warn("No registerService method", var3);
      } catch (InvocationTargetException var4) {
         LogLog.warn("Unable to invoke registerService method", var4);
      }
   }

   public ZeroConfSupport(String var1, int var2, String var3) {
      this(var1, var2, var3, new HashMap());
   }

   public static Object createJmDNSVersion1() {
      try {
         return jmDNSClass.newInstance();
      } catch (InstantiationException var1) {
         LogLog.warn("Unable to instantiate JMDNS", var1);
      } catch (IllegalAccessException var2) {
         LogLog.warn("Unable to instantiate JMDNS", var2);
      }

      return null;
   }

   public static Object initializeJMDNS() {
      try {
         jmDNSClass = Class.forName("javax.jmdns.JmDNS");
         serviceInfoClass = Class.forName("javax.jmdns.ServiceInfo");
      } catch (ClassNotFoundException var3) {
         LogLog.warn("JmDNS or serviceInfo class not found", var3);
      }

      boolean var0 = false;

      try {
         jmDNSClass.getMethod("create", null);
         var0 = true;
      } catch (NoSuchMethodException var2) {
      }

      return var0 ? createJmDNSVersion3() : createJmDNSVersion1();
   }

   public static Object createJmDNSVersion3() {
      try {
         Method var0 = jmDNSClass.getMethod("create", null);
         return var0.invoke(null, null);
      } catch (IllegalAccessException var1) {
         LogLog.warn("Unable to instantiate jmdns class", var1);
      } catch (NoSuchMethodException var2) {
         LogLog.warn("Unable to access constructor", var2);
      } catch (InvocationTargetException var3) {
         LogLog.warn("Unable to call constructor", var3);
      }

      return null;
   }

   public ZeroConfSupport(String var1, int var2, String var3, Map var4) {
      boolean var5 = false;

      try {
         jmDNSClass.getMethod("create", null);
         var5 = true;
      } catch (NoSuchMethodException var7) {
      }

      if (var5) {
         LogLog.debug("using JmDNS version 3 to construct serviceInfo instance");
         this.serviceInfo = this.buildServiceInfoVersion3(var1, var2, var3, var4);
      } else {
         LogLog.debug("using JmDNS version 1.0 to construct serviceInfo instance");
         this.serviceInfo = this.buildServiceInfoVersion1(var1, var2, var3, var4);
      }
   }

   public void unadvertise() {
      try {
         Method var1 = jmDNSClass.getMethod("unregisterService", serviceInfoClass);
         var1.invoke(jmDNS, this.serviceInfo);
         LogLog.debug("unregistered serviceInfo: " + this.serviceInfo);
      } catch (IllegalAccessException var2) {
         LogLog.warn("Unable to invoke unregisterService method", var2);
      } catch (NoSuchMethodException var3) {
         LogLog.warn("No unregisterService method", var3);
      } catch (InvocationTargetException var4) {
         LogLog.warn("Unable to invoke unregisterService method", var4);
      }
   }

   public Object buildServiceInfoVersion3(String var1, int var2, String var3, Map var4) {
      try {
         Class[] var5 = new Class[]{
            class$java$lang$String == null ? (class$java$lang$String = class$("java.lang.String")) : class$java$lang$String,
            class$java$lang$String == null ? (class$java$lang$String = class$("java.lang.String")) : class$java$lang$String,
            int.class,
            int.class,
            int.class,
            class$java$util$Map == null ? (class$java$util$Map = class$("java.util.Map")) : class$java$util$Map
         };
         Method var6 = serviceInfoClass.getMethod("create", var5);
         Object[] var7 = new Object[]{var1, var3, new Integer(var2), new Integer(0), new Integer(0), var4};
         Object var8 = var6.invoke(null, var7);
         LogLog.debug("created serviceinfo: " + var8);
         return var8;
      } catch (IllegalAccessException var9) {
         LogLog.warn("Unable to invoke create method", var9);
      } catch (NoSuchMethodException var10) {
         LogLog.warn("Unable to find create method", var10);
      } catch (InvocationTargetException var11) {
         LogLog.warn("Unable to invoke create method", var11);
      }

      return null;
   }

   public Object buildServiceInfoVersion1(String var1, int var2, String var3, Map var4) {
      Hashtable var5 = new Hashtable(var4);

      try {
         Class[] var6 = new Class[]{
            class$java$lang$String == null ? (class$java$lang$String = class$("java.lang.String")) : class$java$lang$String,
            class$java$lang$String == null ? (class$java$lang$String = class$("java.lang.String")) : class$java$lang$String,
            int.class,
            int.class,
            int.class,
            class$java$util$Hashtable == null ? (class$java$util$Hashtable = class$("java.util.Hashtable")) : class$java$util$Hashtable
         };
         Constructor var7 = serviceInfoClass.getConstructor(var6);
         Object[] var8 = new Object[]{var1, var3, new Integer(var2), new Integer(0), new Integer(0), var5};
         Object var9 = var7.newInstance(var8);
         LogLog.debug("created serviceinfo: " + var9);
         return var9;
      } catch (IllegalAccessException var10) {
         LogLog.warn("Unable to construct ServiceInfo instance", var10);
      } catch (NoSuchMethodException var11) {
         LogLog.warn("Unable to get ServiceInfo constructor", var11);
      } catch (InstantiationException var12) {
         LogLog.warn("Unable to construct ServiceInfo instance", var12);
      } catch (InvocationTargetException var13) {
         LogLog.warn("Unable to construct ServiceInfo instance", var13);
      }

      return null;
   }

   // $VF: synthetic method
   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw (NoClassDefFoundError)new NoClassDefFoundError().initCause(var2);
      }
   }

   public static Object getJMDNSInstance() {
      return jmDNS;
   }
}
