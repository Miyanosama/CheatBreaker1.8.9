package org.apache.log4j.lf5;

import io.netty.handler.codec.http.HttpHeaderDateFormat$HttpHeaderDateFormatObsolete1;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import net.minecraft.block.BlockOldLog$2;
import net.optifine.expr.ExpressionFloatArrayCached;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.pattern.IntegerPatternConverter;
import org.apache.log4j.spi.Configurator;
import org.apache.log4j.spi.LoggerRepository;

public class DefaultLF5Configurator implements Configurator {
   public ExpressionFloatArrayCached field_0002;
   public BlockOldLog$2 field_0004;
   public IntegerPatternConverter field_0001;
   public HttpHeaderDateFormat$HttpHeaderDateFormatObsolete1 field_0003;
   public static Class class$org$apache$log4j$lf5$DefaultLF5Configurator;

   public void doConfigure(URL var1, LoggerRepository var2) {
      throw new IllegalStateException("This class should NOT be instantiated!");
   }

   public void doConfigure(InputStream var1, LoggerRepository var2) {
      throw new IllegalStateException("This class should NOT be instantiated!");
   }

   public static void configure() {
      String var0 = "/org/apache/log4j/lf5/config/defaultconfig.properties";
      URL var1 = (class$org$apache$log4j$lf5$DefaultLF5Configurator == null
            ? (class$org$apache$log4j$lf5$DefaultLF5Configurator = class$("org.apache.log4j.lf5.DefaultLF5Configurator"))
            : class$org$apache$log4j$lf5$DefaultLF5Configurator)
         .getResource(var0);
      if (var1 != null) {
         PropertyConfigurator.configure(var1);
      } else {
         throw new IOException("Error: Unable to open the resource" + var0);
      }
   }

   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw new NoClassDefFoundError().initCause(var2);
      }
   }
}
