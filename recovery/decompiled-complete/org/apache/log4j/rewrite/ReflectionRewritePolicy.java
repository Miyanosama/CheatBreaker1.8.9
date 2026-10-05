package org.apache.log4j.rewrite;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import net.optifine.shaders.config.ShaderOptionSwitchConst;
import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

public class ReflectionRewritePolicy implements RewritePolicy {
   public ShaderOptionSwitchConst field_0000;
   public static Class class$java$lang$Object;

   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw new NoClassDefFoundError().initCause(var2);
      }
   }

   public LoggingEvent rewrite(LoggingEvent var1) {
      Object var2 = var1.getMessage();
      if (!(var2 instanceof String)) {
         Object var3 = var2;
         HashMap var4 = new HashMap(var1.getProperties());

         try {
            PropertyDescriptor[] var5 = Introspector.getBeanInfo(
                  var2.getClass(), class$java$lang$Object == null ? (class$java$lang$Object = class$("java.lang.Object")) : class$java$lang$Object
               )
               .getPropertyDescriptors();
            if (var5.length > 0) {
               for (int var6 = 0; var6 < var5.length; var6++) {
                  try {
                     Object var7 = var5[var6].getReadMethod().invoke(var2, (Object[])null);
                     if ("message".equalsIgnoreCase(var5[var6].getName())) {
                        var3 = var7;
                     } else {
                        var4.put(var5[var6].getName(), var7);
                     }
                  } catch (Exception var8) {
                     LogLog.warn("Unable to evaluate property " + var5[var6].getName(), var8);
                  }
               }

               return new LoggingEvent(
                  var1.getFQNOfLoggerClass(),
                  (Category)(var1.getLogger() != null ? var1.getLogger() : Logger.getLogger(var1.getLoggerName())),
                  var1.getTimeStamp(),
                  var1.getLevel(),
                  var3,
                  var1.getThreadName(),
                  var1.getThrowableInformation(),
                  var1.getNDC(),
                  var1.getLocationInformation(),
                  var4
               );
            }
         } catch (Exception var9) {
            LogLog.warn("Unable to get property descriptors", var9);
         }
      }

      return var1;
   }
}
