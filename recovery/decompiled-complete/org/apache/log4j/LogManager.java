package org.apache.log4j;

import com.cheatbreaker.client.ui.mainmenu.AccountList;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.traffic.ChannelTrafficShapingHandler$1;
import io.netty.util.internal.UnpaddedInternalThreadLocalMap;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import net.minecraft.network.play.server.S1EPacketRemoveEntityEffect;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.DefaultRepositorySelector;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.NOPLoggerRepository;
import org.apache.log4j.spi.RepositorySelector;
import org.apache.log4j.spi.RootLogger;
import recovered.unidentified.UnidentifiedClass4000;

public class LogManager {
   public static String field_0005;
   public UnpaddedInternalThreadLocalMap field_0010;
   public static String field_0004;
   public static Object guard = null;
   public static RepositorySelector repositorySelector;
   public static String field_0002;
   public S1EPacketRemoveEntityEffect field_0011;
   public UnidentifiedClass4000 field_0008;
   public static String field_0003;
   public static String field_0012;
   public IdleStateEvent field_0000;
   public ChannelTrafficShapingHandler$1 field_0006;
   public AccountList field_0007;

   public static void shutdown() {
      getLoggerRepository().shutdown();
   }

   public static Logger getLogger(String var0, LoggerFactory var1) {
      return getLoggerRepository().getLogger(var0, var1);
   }

   public static Logger exists(String var0) {
      return getLoggerRepository().exists(var0);
   }

   public static Logger getLogger(Class var0) {
      return getLoggerRepository().getLogger(var0.getName());
   }

   public static Enumeration getCurrentLoggers() {
      return getLoggerRepository().getCurrentLoggers();
   }

   static {
      Hierarchy var0 = new Hierarchy(new RootLogger(Level.DEBUG));
      repositorySelector = new DefaultRepositorySelector(var0);
      String var1 = OptionConverter.getSystemProperty("log4j.defaultInitOverride", null);
      if (var1 != null && !"false".equalsIgnoreCase(var1)) {
         LogLog.debug("Default initialization of overridden by log4j.defaultInitOverrideproperty.");
      } else {
         String var2 = OptionConverter.getSystemProperty("log4j.configuration", null);
         String var3 = OptionConverter.getSystemProperty("log4j.configuratorClass", null);
         Object var4 = null;
         if (var2 == null) {
            var4 = Loader.getResource("log4j.xml");
            if (var4 == null) {
               var4 = Loader.getResource("log4j.properties");
            }
         } else {
            try {
               var4 = new URL(var2);
            } catch (MalformedURLException var7) {
               var4 = Loader.getResource(var2);
            }
         }

         if (var4 != null) {
            LogLog.debug("Using URL [" + var4 + "] for automatic log4j configuration.");

            try {
               OptionConverter.selectAndConfigure((URL)var4, var3, getLoggerRepository());
            } catch (NoClassDefFoundError var6) {
               LogLog.warn("Error during default initialization", var6);
            }
         } else {
            LogLog.debug("Could not find resource: [" + var2 + "].");
         }
      }
   }

   public static Logger getRootLogger() {
      return getLoggerRepository().getRootLogger();
   }

   public static void resetConfiguration() {
      getLoggerRepository().resetConfiguration();
   }

   public static boolean isLikelySafeScenario(Exception var0) {
      StringWriter var1 = new StringWriter();
      var0.printStackTrace(new PrintWriter(var1));
      String var2 = var1.toString();
      return var2.indexOf("org.apache.catalina.loader.WebappClassLoader.stop") != -1;
   }

   public static void setRepositorySelector(RepositorySelector var0, Object var1) {
      if (guard != null && guard != var1) {
         throw new IllegalArgumentException("Attempted to reset the LoggerFactory without possessing the guard.");
      } else if (var0 == null) {
         throw new IllegalArgumentException("RepositorySelector must be non-null.");
      } else {
         guard = var1;
         repositorySelector = var0;
      }
   }

   public static LoggerRepository getLoggerRepository() {
      if (repositorySelector == null) {
         repositorySelector = new DefaultRepositorySelector(new NOPLoggerRepository());
         guard = null;
         IllegalStateException var0 = new IllegalStateException("Class invariant violation");
         String var1 = "log4j called after unloading, see http://logging.apache.org/log4j/1.2/faq.html#unload.";
         if (isLikelySafeScenario(var0)) {
            LogLog.debug(var1, var0);
         } else {
            LogLog.error(var1, var0);
         }
      }

      return repositorySelector.getLoggerRepository();
   }

   public static Logger getLogger(String var0) {
      return getLoggerRepository().getLogger(var0);
   }
}
