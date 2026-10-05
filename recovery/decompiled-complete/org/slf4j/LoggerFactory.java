package org.slf4j;

import io.netty.handler.ssl.JettyNpnSslEngine;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockSilverfish$EnumType;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.model.ModelBanner;
import net.minecraft.client.renderer.entity.layers.LayerEndermanEyes;
import net.minecraft.server.management.IPBanEntry;
import net.optifine.entity.model.ModelAdapterEndermite;
import net.optifine.shaders.gui.GuiSliderShaderOption;
import org.slf4j.event.SubstituteLoggingEvent;
import org.slf4j.helpers.NOPLoggerFactory;
import org.slf4j.helpers.SubstituteLogger;
import org.slf4j.helpers.SubstituteLoggerFactory;
import org.slf4j.helpers.Util;
import org.slf4j.impl.StaticLoggerBinder;

public class LoggerFactory {
   public static int field_0015;
   public static String field_0028;
   public IPBanEntry field_0014;
   public LayerEndermanEyes field_0025;
   public static NOPLoggerFactory NOP_FALLBACK_FACTORY = new NOPLoggerFactory();
   public static String field_0008;
   public ModelAdapterEndermite field_0029;
   public static int field_0021;
   public static String field_0009;
   public static String[] API_COMPATIBILITY_LIST = new String[]{"1.6", "1.7"};
   public static String field_0005;
   public static String field_0016;
   public static boolean DETECT_LOGGER_NAME_MISMATCH = Util.safeGetBooleanSystemProperty("slf4j.detectLoggerNameMismatch");
   public BlockFalling field_0012;
   public static String field_0023;
   public ModelBanner field_0027;
   public static int field_0003;
   public static int field_0011;
   public static String field_0017;
   public static String field_0026;
   public BlockSilverfish$EnumType field_0002;
   public static String field_0001;
   public GuiSlot field_0004;
   public static SubstituteLoggerFactory SUBST_FACTORY = new SubstituteLoggerFactory();
   public static volatile int INITIALIZATION_STATE = 0;
   public JettyNpnSslEngine field_0018;
   public static String field_0022;
   public GuiSliderShaderOption field_0013;
   public static String field_0030;
   public static String field_0020;
   public static String STATIC_LOGGER_BINDER_PATH = "org/slf4j/impl/StaticLoggerBinder.class";
   public static int field_0007;

   public static boolean nonMatchingClasses(Class<?> var0, Class<?> var1) {
      return !var1.isAssignableFrom(var0);
   }

   public static Set<URL> findPossibleStaticLoggerBinderPathSet() {
      LinkedHashSet var0 = new LinkedHashSet();

      try {
         ClassLoader var1 = LoggerFactory.class.getClassLoader();
         Enumeration var2;
         if (var1 == null) {
            var2 = ClassLoader.getSystemResources(STATIC_LOGGER_BINDER_PATH);
         } else {
            var2 = var1.getResources(STATIC_LOGGER_BINDER_PATH);
         }

         while (var2.hasMoreElements()) {
            URL var3 = (URL)var2.nextElement();
            var0.add(var3);
         }
      } catch (IOException var4) {
         Util.report("Error getting resources from path", var4);
      }

      return var0;
   }

   public static void performInitialization() {
      bind();
      if (INITIALIZATION_STATE == 3) {
         versionSanityCheck();
      }
   }

   public static boolean messageContainsOrgSlf4jImplStaticLoggerBinder(String var0) {
      if (var0 == null) {
         return false;
      } else {
         return var0.contains("org/slf4j/impl/StaticLoggerBinder") ? true : var0.contains("org.slf4j.impl.StaticLoggerBinder");
      }
   }

   public static void emitReplayWarning(int var0) {
      Util.report("A number (" + var0 + ") of logging calls during the initialization phase have been intercepted and are");
      Util.report("now being replayed. These are subject to the filtering rules of the underlying logging system.");
      Util.report("See also http://www.slf4j.org/codes.html#replay");
   }

   public static void replaySingleEvent(SubstituteLoggingEvent var0) {
      if (var0 != null) {
         SubstituteLogger var1 = var0.getLogger();
         String var2 = var1.getName();
         if (var1.isDelegateNull()) {
            throw new IllegalStateException("Delegate logger cannot be null at this state.");
         } else {
            if (!var1.isDelegateNOP()) {
               if (var1.isDelegateEventAware()) {
                  var1.log(var0);
               } else {
                  Util.report(var2);
               }
            }
         }
      }
   }

   public static void bind() {
      try {
         Set var0 = null;
         if (!isAndroid()) {
            var0 = findPossibleStaticLoggerBinderPathSet();
            reportMultipleBindingAmbiguity(var0);
         }

         StaticLoggerBinder.getSingleton();
         INITIALIZATION_STATE = 3;
         reportActualBinding(var0);
         fixSubstituteLoggers();
         replayEvents();
         SUBST_FACTORY.clear();
      } catch (NoClassDefFoundError var2) {
         String var5 = var2.getMessage();
         if (!messageContainsOrgSlf4jImplStaticLoggerBinder(var5)) {
            failedBinding(var2);
            throw var2;
         }

         INITIALIZATION_STATE = 4;
         Util.report("Failed to load class \"org.slf4j.impl.StaticLoggerBinder\".");
         Util.report("Defaulting to no-operation (NOP) logger implementation");
         Util.report("See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.");
      } catch (NoSuchMethodError var3) {
         String var1 = var3.getMessage();
         if (var1 != null && var1.contains("org.slf4j.impl.StaticLoggerBinder.getSingleton()")) {
            INITIALIZATION_STATE = 2;
            Util.report("slf4j-api 1.6.x (or later) is incompatible with this binding.");
            Util.report("Your binding is version 1.5.5 or earlier.");
            Util.report("Upgrade your binding to version 1.6.x.");
         }

         throw var3;
      } catch (Exception var4) {
         failedBinding(var4);
         throw new IllegalStateException("Unexpected initialization failure", var4);
      }
   }

   public static void reportActualBinding(Set<URL> var0) {
      if (var0 != null && isAmbiguousStaticLoggerBinderPathSet(var0)) {
         Util.report("Actual binding is of type [" + StaticLoggerBinder.getSingleton().getLoggerFactoryClassStr() + "]");
      }
   }

   public static Logger getLogger(Class<?> var0) {
      Logger var1 = getLogger(var0.getName());
      if (DETECT_LOGGER_NAME_MISMATCH) {
         Class var2 = Util.getCallingClass();
         if (var2 != null && nonMatchingClasses(var0, var2)) {
            Util.report(String.format("Detected logger name mismatch. Given name: \"%s\"; computed name: \"%s\".", var1.getName(), var2.getName()));
            Util.report("See http://www.slf4j.org/codes.html#loggerNameMismatch for an explanation");
         }
      }

      return var1;
   }

   public static void versionSanityCheck() {
      try {
         String var0 = StaticLoggerBinder.REQUESTED_API_VERSION;
         boolean var1 = false;

         for (String var5 : API_COMPATIBILITY_LIST) {
            if (var0.startsWith(var5)) {
               var1 = true;
            }
         }

         if (!var1) {
            Util.report("The requested version " + var0 + " by your slf4j binding is not compatible with " + Arrays.asList(API_COMPATIBILITY_LIST).toString());
            Util.report("See http://www.slf4j.org/codes.html#version_mismatch for further details.");
         }
      } catch (NoSuchFieldError var6) {
      } catch (Throwable var7) {
         Util.report("Unexpected problem occured during version sanity check", var7);
      }
   }

   public static void emitReplayOrSubstituionWarning(SubstituteLoggingEvent var0, int var1) {
      if (var0.getLogger().isDelegateEventAware()) {
         emitReplayWarning(var1);
      } else if (!var0.getLogger().isDelegateNOP()) {
         emitSubstitutionWarning();
      }
   }

   public static boolean isAmbiguousStaticLoggerBinderPathSet(Set<URL> var0) {
      return var0.size() > 1;
   }

   public static void replayEvents() {
      LinkedBlockingQueue var0 = SUBST_FACTORY.getEventQueue();
      int var1 = var0.size();
      int var2 = 0;
      short var3 = 128;
      ArrayList var4 = new ArrayList(128);

      while (true) {
         int var5 = var0.drainTo(var4, 128);
         if (var5 == 0) {
            return;
         }

         for (SubstituteLoggingEvent var7 : var4) {
            replaySingleEvent(var7);
            if (var2++ == 0) {
               emitReplayOrSubstituionWarning(var7, var1);
            }
         }

         var4.clear();
      }
   }

   public static void reset() {
      INITIALIZATION_STATE = 0;
   }

   public static Logger getLogger(String var0) {
      ILoggerFactory var1 = getILoggerFactory();
      return var1.getLogger(var0);
   }

   public static void emitSubstitutionWarning() {
      Util.report("The following set of substitute loggers may have been accessed");
      Util.report("during the initialization phase. Logging calls during this");
      Util.report("phase were not honored. However, subsequent logging calls to these");
      Util.report("loggers will work as normally expected.");
      Util.report("See also http://www.slf4j.org/codes.html#substituteLogger");
   }

   public static void reportMultipleBindingAmbiguity(Set<URL> var0) {
      if (isAmbiguousStaticLoggerBinderPathSet(var0)) {
         Util.report("Class path contains multiple SLF4J bindings.");

         for (URL var2 : var0) {
            Util.report("Found binding in [" + var2 + "]");
         }

         Util.report("See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.");
      }
   }

   public static ILoggerFactory getILoggerFactory() {
      if (INITIALIZATION_STATE == 0) {
         synchronized (LoggerFactory.class) {
            if (INITIALIZATION_STATE == 0) {
               INITIALIZATION_STATE = 1;
               performInitialization();
            }
         }
      }

      switch (INITIALIZATION_STATE) {
         case 1:
            return SUBST_FACTORY;
         case 2:
            throw new IllegalStateException(
               "org.slf4j.LoggerFactory in failed state. Original exception was thrown EARLIER. See also http://www.slf4j.org/codes.html#unsuccessfulInit"
            );
         case 3:
            return StaticLoggerBinder.getSingleton().getLoggerFactory();
         case 4:
            return NOP_FALLBACK_FACTORY;
         default:
            throw new IllegalStateException("Unreachable code");
      }
   }

   public static void failedBinding(Throwable var0) {
      INITIALIZATION_STATE = 2;
      Util.report("Failed to instantiate SLF4J LoggerFactory", var0);
   }

   public static boolean isAndroid() {
      String var0 = Util.safeGetSystemProperty("java.vendor.url");
      return var0 == null ? false : var0.toLowerCase().contains("android");
   }

   public static void fixSubstituteLoggers() {
      synchronized (SUBST_FACTORY) {
         SUBST_FACTORY.postInitialization();

         for (SubstituteLogger var2 : SUBST_FACTORY.getLoggers()) {
            Logger var3 = getLogger(var2.getName());
            var2.setDelegate(var3);
         }
      }
   }
}
