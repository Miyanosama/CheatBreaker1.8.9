package org.apache.log4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Map.Entry;
import org.apache.log4j.config.PropertySetter;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.or.RendererMap;
import org.apache.log4j.spi.Configurator;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.OptionHandler;
import org.apache.log4j.spi.RendererSupport;
import org.apache.log4j.spi.ThrowableRenderer;
import org.apache.log4j.spi.ThrowableRendererSupport;

public class PropertyConfigurator implements Configurator {
   public static final String recoveredField2404 = "log4j.loggerFactory";
   public static final String recoveredField2405 = "log4j.rootLogger";
   public static final String recoveredField2406 = "root-ref";
   public static final String recoveredField2407 = "log4j.logger.";
   public static final String recoveredField2408 = "log4j.appender.";
   public static final String recoveredField2409 = "root";
   public static final String recoveredField2410 = "log4j.throwableRenderer";
   public static final String recoveredField2411 = "log4j.renderer.";
   public static Class class$org$apache$log4j$spi$ErrorHandler;
   public static final String recoveredField2412 = "appender-ref";
   public static Class class$org$apache$log4j$spi$LoggerFactory;
   public static final String recoveredField2413 = "log4j.factory";
   public static final String recoveredField2414 = "log4j.threshold";
   public static Class class$org$apache$log4j$Appender;
   public static final String recoveredField2415 = "log4j.rootCategory";
   public static Class class$org$apache$log4j$Layout;
   public LoggerFactory loggerFactory;
   public Hashtable registry = new Hashtable(11);
   public LoggerRepository repository;
   public static final String recoveredField2416 = "log4j.category.";
   public static final String recoveredField2417 = "log4j.additivity.";
   public static Class class$org$apache$log4j$spi$Filter;
   public static final String recoveredField2418 = "logger-ref";
   public static final String recoveredField2419 = "log4j.reset";
   public static Class class$org$apache$log4j$spi$ThrowableRenderer;

   public void parseErrorHandler(ErrorHandler var1, String var2, Properties var3, LoggerRepository var4) {
      boolean var5 = OptionConverter.toBoolean(OptionConverter.findAndSubst(var2 + "root-ref", var3), false);
      if (var5) {
         var1.setLogger(var4.getRootLogger());
      }

      String var6 = OptionConverter.findAndSubst(var2 + "logger-ref", var3);
      if (var6 != null) {
         Logger var7 = this.loggerFactory == null ? var4.getLogger(var6) : var4.getLogger(var6, this.loggerFactory);
         var1.setLogger(var7);
      }

      String var9 = OptionConverter.findAndSubst(var2 + "appender-ref", var3);
      if (var9 != null) {
         Appender var8 = this.parseAppender(var3, var9);
         if (var8 != null) {
            var1.setBackupAppender(var8);
         }
      }
   }

   public Appender registryGet(String var1) {
      return (Appender)this.registry.get(var1);
   }

   public void configureLoggerFactory(Properties var1) {
      String var2 = OptionConverter.findAndSubst("log4j.loggerFactory", var1);
      if (var2 != null) {
         LogLog.debug("Setting category factory to [" + var2 + "].");
         this.loggerFactory = (LoggerFactory)OptionConverter.instantiateByClassName(
            var2,
            class$org$apache$log4j$spi$LoggerFactory == null
               ? (class$org$apache$log4j$spi$LoggerFactory = class$("org.apache.log4j.spi.LoggerFactory"))
               : class$org$apache$log4j$spi$LoggerFactory,
            this.loggerFactory
         );
         PropertySetter.setProperties(this.loggerFactory, var1, "log4j.factory.");
      }
   }

   public void parseAdditivityForLogger(Properties var1, Logger var2, String var3) {
      String var4 = OptionConverter.findAndSubst("log4j.additivity." + var3, var1);
      LogLog.debug("Handling log4j.additivity." + var3 + "=[" + var4 + "]");
      if (var4 != null && !var4.equals("")) {
         boolean var5 = OptionConverter.toBoolean(var4, true);
         LogLog.debug("Setting additivity for \"" + var3 + "\" to " + var5);
         var2.setAdditivity(var5);
      }
   }

   // $VF: synthetic method
   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw (NoClassDefFoundError)new NoClassDefFoundError().initCause(var2);
      }
   }

   public PropertyConfigurator() {
      this.loggerFactory = new DefaultCategoryFactory();
   }

   public void configureRootCategory(Properties var1, LoggerRepository var2) {
      String var3 = "log4j.rootLogger";
      String var4 = OptionConverter.findAndSubst("log4j.rootLogger", var1);
      if (var4 == null) {
         var4 = OptionConverter.findAndSubst("log4j.rootCategory", var1);
         var3 = "log4j.rootCategory";
      }

      if (var4 == null) {
         LogLog.debug("Could not find root logger information. Is this OK?");
      } else {
         Logger var5 = var2.getRootLogger();
         synchronized (var5) {
            this.parseCategory(var1, var5, var3, "root", var4);
         }
      }
   }

   public void parseAppenderFilters(Properties var1, String var2, Appender var3) {
      String var4 = "log4j.appender." + var2 + ".filter.";
      int var5 = var4.length();
      Hashtable var6 = new Hashtable();
      Enumeration var7 = var1.keys();
      String var8 = "";

      while (var7.hasMoreElements()) {
         String var9 = (String)var7.nextElement();
         if (var9.startsWith(var4)) {
            int var10 = var9.indexOf(46, var5);
            String var11 = var9;
            if (var10 != -1) {
               var11 = var9.substring(0, var10);
               var8 = var9.substring(var10 + 1);
            }

            Vector var12 = (Vector)var6.get(var11);
            if (var12 == null) {
               var12 = new Vector();
               var6.put(var11, var12);
            }

            if (var10 != -1) {
               String var13 = OptionConverter.findAndSubst(var9, var1);
               var12.add(new NameValue(var8, var13));
            }
         }
      }

      SortedKeyEnumeration var17 = new SortedKeyEnumeration(var6);

      while (var17.hasMoreElements()) {
         String var18 = (String)var17.nextElement();
         String var19 = var1.getProperty(var18);
         if (var19 != null) {
            LogLog.debug("Filter key: [" + var18 + "] class: [" + var1.getProperty(var18) + "] props: " + var6.get(var18));
            Filter var20 = (Filter)OptionConverter.instantiateByClassName(
               var19,
               class$org$apache$log4j$spi$Filter == null
                  ? (class$org$apache$log4j$spi$Filter = class$("org.apache.log4j.spi.Filter"))
                  : class$org$apache$log4j$spi$Filter,
               null
            );
            if (var20 != null) {
               PropertySetter var21 = new PropertySetter(var20);
               Vector var14 = (Vector)var6.get(var18);
               Enumeration var15 = var14.elements();

               while (var15.hasMoreElements()) {
                  NameValue var16 = (NameValue)var15.nextElement();
                  var21.setProperty(var16.key, var16.value);
               }

               var21.activate();
               LogLog.debug("Adding filter of type [" + var20.getClass() + "] to appender named [" + var3.getName() + "].");
               var3.addFilter(var20);
            }
         } else {
            LogLog.warn("Missing class definition for filter: [" + var18 + "]");
         }
      }
   }

   public Appender parseAppender(Properties var1, String var2) {
      Appender var3 = this.registryGet(var2);
      if (var3 != null) {
         LogLog.debug("Appender \"" + var2 + "\" was already parsed.");
         return var3;
      } else {
         String var4 = "log4j.appender." + var2;
         String var5 = var4 + ".layout";
         var3 = (Appender)OptionConverter.instantiateByKey(
            var1,
            var4,
            class$org$apache$log4j$Appender == null ? (class$org$apache$log4j$Appender = class$("org.apache.log4j.Appender")) : class$org$apache$log4j$Appender,
            null
         );
         if (var3 == null) {
            LogLog.error("Could not instantiate appender named \"" + var2 + "\".");
            return null;
         } else {
            var3.setName(var2);
            if (var3 instanceof OptionHandler) {
               if (var3.requiresLayout()) {
                  Layout var6 = (Layout)OptionConverter.instantiateByKey(
                     var1,
                     var5,
                     class$org$apache$log4j$Layout == null
                        ? (class$org$apache$log4j$Layout = class$("org.apache.log4j.Layout"))
                        : class$org$apache$log4j$Layout,
                     null
                  );
                  if (var6 != null) {
                     var3.setLayout(var6);
                     LogLog.debug("Parsing layout options for \"" + var2 + "\".");
                     PropertySetter.setProperties(var6, var1, var5 + ".");
                     LogLog.debug("End of parsing for \"" + var2 + "\".");
                  }
               }

               String var15 = var4 + ".errorhandler";
               String var7 = OptionConverter.findAndSubst(var15, var1);
               if (var7 != null) {
                  ErrorHandler var8 = (ErrorHandler)OptionConverter.instantiateByKey(
                     var1,
                     var15,
                     class$org$apache$log4j$spi$ErrorHandler == null
                        ? (class$org$apache$log4j$spi$ErrorHandler = class$("org.apache.log4j.spi.ErrorHandler"))
                        : class$org$apache$log4j$spi$ErrorHandler,
                     null
                  );
                  if (var8 != null) {
                     var3.setErrorHandler(var8);
                     LogLog.debug("Parsing errorhandler options for \"" + var2 + "\".");
                     this.parseErrorHandler(var8, var15, var1, this.repository);
                     Properties var9 = new Properties();
                     String[] var10 = new String[]{var15 + "." + "root-ref", var15 + "." + "logger-ref", var15 + "." + "appender-ref"};

                     for (Entry var12 : var1.entrySet()) {
                        int var13 = 0;

                        while (var13 < var10.length && !var10[var13].equals(var12.getKey())) {
                           var13++;
                        }

                        if (var13 == var10.length) {
                           var9.put(var12.getKey(), var12.getValue());
                        }
                     }

                     PropertySetter.setProperties(var8, var9, var15 + ".");
                     LogLog.debug("End of errorhandler parsing for \"" + var2 + "\".");
                  }
               }

               PropertySetter.setProperties(var3, var1, var4 + ".");
               LogLog.debug("Parsed \"" + var2 + "\" options.");
            }

            this.parseAppenderFilters(var1, var2, var3);
            this.registryPut(var3);
            return var3;
         }
      }
   }

   public void registryPut(Appender var1) {
      this.registry.put(var1.getName(), var1);
   }

   public void doConfigure(URL var1, LoggerRepository var2) {
      Properties var3 = new Properties();
      LogLog.debug("Reading configuration from URL " + var1);
      InputStream var4 = null;
      URLConnection var5 = null;

      label87: {
         try {
            var5 = var1.openConnection();
            var5.setUseCaches(false);
            var4 = var5.getInputStream();
            var3.load(var4);
            break label87;
         } catch (Exception var24) {
            if (var24 instanceof InterruptedIOException || var24 instanceof InterruptedException) {
               Thread.currentThread().interrupt();
            }

            LogLog.error("Could not read configuration file from URL [" + var1 + "].", var24);
            LogLog.error("Ignoring configuration file [" + var1 + "].");
         } finally {
            if (var4 != null) {
               try {
                  var4.close();
               } catch (InterruptedIOException var21) {
                  Thread.currentThread().interrupt();
               } catch (IOException var22) {
               } catch (RuntimeException var23) {
               }
            }
         }

         return;
      }

      this.doConfigure(var3, var2);
   }

   public static void configure(URL var0) {
      new PropertyConfigurator().doConfigure(var0, LogManager.getLoggerRepository());
   }

   public static void configure(String var0) {
      new PropertyConfigurator().doConfigure(var0, LogManager.getLoggerRepository());
   }

   public void doConfigure(String var1, LoggerRepository var2) {
      Properties var3 = new Properties();
      FileInputStream var4 = null;

      label81: {
         try {
            var4 = new FileInputStream(var1);
            var3.load(var4);
            var4.close();
            break label81;
         } catch (Exception var19) {
            if (var19 instanceof InterruptedIOException || var19 instanceof InterruptedException) {
               Thread.currentThread().interrupt();
            }

            LogLog.error("Could not read configuration file [" + var1 + "].", var19);
            LogLog.error("Ignoring configuration file [" + var1 + "].");
         } finally {
            if (var4 != null) {
               try {
                  var4.close();
               } catch (InterruptedIOException var17) {
                  Thread.currentThread().interrupt();
               } catch (Throwable var18) {
               }
            }
         }

         return;
      }

      this.doConfigure(var3, var2);
   }

   public void parseCategory(Properties var1, Logger var2, String var3, String var4, String var5) {
      LogLog.debug("Parsing for [" + var4 + "] with value=[" + var5 + "].");
      StringTokenizer var6 = new StringTokenizer(var5, ",");
      if (!var5.startsWith(",") && !var5.equals("")) {
         if (!var6.hasMoreTokens()) {
            return;
         }

         String var7 = var6.nextToken();
         LogLog.debug("Level token is [" + var7 + "].");
         if (!"inherited".equalsIgnoreCase(var7) && !"null".equalsIgnoreCase(var7)) {
            var2.setLevel(OptionConverter.toLevel(var7, Level.DEBUG));
         } else if (var4.equals("root")) {
            LogLog.warn("The root logger cannot be set to null.");
         } else {
            var2.setLevel(null);
         }

         LogLog.debug("Category " + var4 + " set to " + var2.getLevel());
      }

      var2.removeAllAppenders();

      while (var6.hasMoreTokens()) {
         String var8 = var6.nextToken().trim();
         if (var8 != null && !var8.equals(",")) {
            LogLog.debug("Parsing appender named \"" + var8 + "\".");
            Appender var9 = this.parseAppender(var1, var8);
            if (var9 != null) {
               var2.addAppender(var9);
            }
         }
      }
   }

   public static void configureAndWatch(String var0) {
      configureAndWatch(var0, 60000L);
   }

   public static void configureAndWatch(String var0, long var1) {
      PropertyWatchdog var3 = new PropertyWatchdog(var0);
      var3.setDelay(var1);
      var3.start();
   }

   public void parseCatsAndRenderers(Properties var1, LoggerRepository var2) {
      Enumeration var3 = var1.propertyNames();

      while (var3.hasMoreElements()) {
         String var4 = (String)var3.nextElement();
         if (var4.startsWith("log4j.category.") || var4.startsWith("log4j.logger.")) {
            String var12 = null;
            if (var4.startsWith("log4j.category.")) {
               var12 = var4.substring("log4j.category.".length());
            } else if (var4.startsWith("log4j.logger.")) {
               var12 = var4.substring("log4j.logger.".length());
            }

            String var14 = OptionConverter.findAndSubst(var4, var1);
            Logger var7 = var2.getLogger(var12, this.loggerFactory);
            synchronized (var7) {
               this.parseCategory(var1, var7, var4, var12, var14);
               this.parseAdditivityForLogger(var1, var7, var12);
            }
         } else if (var4.startsWith("log4j.renderer.")) {
            String var5 = var4.substring("log4j.renderer.".length());
            String var6 = OptionConverter.findAndSubst(var4, var1);
            if (var2 instanceof RendererSupport) {
               RendererMap.addRenderer((RendererSupport)var2, var5, var6);
            }
         } else if (var4.equals("log4j.throwableRenderer") && var2 instanceof ThrowableRendererSupport) {
            ThrowableRenderer var11 = (ThrowableRenderer)OptionConverter.instantiateByKey(
               var1,
               "log4j.throwableRenderer",
               class$org$apache$log4j$spi$ThrowableRenderer == null
                  ? (class$org$apache$log4j$spi$ThrowableRenderer = class$("org.apache.log4j.spi.ThrowableRenderer"))
                  : class$org$apache$log4j$spi$ThrowableRenderer,
               null
            );
            if (var11 == null) {
               LogLog.error("Could not instantiate throwableRenderer.");
            } else {
               PropertySetter var13 = new PropertySetter(var11);
               var13.setProperties(var1, "log4j.throwableRenderer.");
               ((ThrowableRendererSupport)var2).setThrowableRenderer(var11);
            }
         }
      }
   }

   public static void configure(Properties var0) {
      new PropertyConfigurator().doConfigure(var0, LogManager.getLoggerRepository());
   }

   public void doConfigure(InputStream var1, LoggerRepository var2) {
      Properties var3 = new Properties();

      try {
         var3.load(var1);
      } catch (IOException var5) {
         if (var5 instanceof InterruptedIOException) {
            Thread.currentThread().interrupt();
         }

         LogLog.error("Could not read configuration file from InputStream [" + var1 + "].", var5);
         LogLog.error("Ignoring configuration InputStream [" + var1 + "].");
         return;
      }

      this.doConfigure(var3, var2);
   }

   public static void configure(InputStream var0) {
      new PropertyConfigurator().doConfigure(var0, LogManager.getLoggerRepository());
   }

   public void doConfigure(Properties var1, LoggerRepository var2) {
      this.repository = var2;
      String var3 = var1.getProperty("log4j.debug");
      if (var3 == null) {
         var3 = var1.getProperty("log4j.configDebug");
         if (var3 != null) {
            LogLog.warn("[log4j.configDebug] is deprecated. Use [log4j.debug] instead.");
         }
      }

      if (var3 != null) {
         LogLog.setInternalDebugging(OptionConverter.toBoolean(var3, true));
      }

      String var4 = var1.getProperty("log4j.reset");
      if (var4 != null && OptionConverter.toBoolean(var4, false)) {
         var2.resetConfiguration();
      }

      String var5 = OptionConverter.findAndSubst("log4j.threshold", var1);
      if (var5 != null) {
         var2.setThreshold(OptionConverter.toLevel(var5, Level.ALL));
         LogLog.debug("Hierarchy threshold set to [" + var2.getThreshold() + "].");
      }

      this.configureRootCategory(var1, var2);
      this.configureLoggerFactory(var1);
      this.parseCatsAndRenderers(var1, var2);
      LogLog.debug("Finished configuring.");
      this.registry.clear();
   }
}
