package org.apache.log4j.config;

import io.netty.handler.codec.http.HttpContentCompressor;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.InterruptedIOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import net.minecraft.util.BlockPos$2;
import org.apache.log4j.helpers.LogLog;

public class PropertyGetter {
   public BlockPos$2 field_0003;
   public static Class class$java$lang$String;
   public PropertyDescriptor[] props;
   public static Object[] NULL_ARG = new Object[0];
   public Object obj;
   public static Class class$org$apache$log4j$Priority;
   public HttpContentCompressor field_0006;

   public PropertyGetter(Object var1) {
      BeanInfo var2 = Introspector.getBeanInfo(var1.getClass());
      this.props = var2.getPropertyDescriptors();
      this.obj = var1;
   }

   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw new NoClassDefFoundError().initCause(var2);
      }
   }

   public static void getProperties(Object var0, PropertyGetter$PropertyCallback var1, String var2) {
      try {
         new PropertyGetter(var0).getProperties(var1, var2);
      } catch (IntrospectionException var4) {
         LogLog.error("Failed to introspect object " + var0, var4);
      }
   }

   public void getProperties(PropertyGetter$PropertyCallback var1, String var2) {
      for (int var3 = 0; var3 < this.props.length; var3++) {
         Method var4 = this.props[var3].getReadMethod();
         if (var4 != null && this.isHandledType(var4.getReturnType())) {
            String var5 = this.props[var3].getName();

            try {
               Object var6 = var4.invoke(this.obj, NULL_ARG);
               if (var6 != null) {
                  var1.foundProperty(this.obj, var2, var5, var6);
               }
            } catch (IllegalAccessException var7) {
               LogLog.warn("Failed to get value of property " + var5);
            } catch (InvocationTargetException var8) {
               if (var8.getTargetException() instanceof InterruptedException || var8.getTargetException() instanceof InterruptedIOException) {
                  Thread.currentThread().interrupt();
               }

               LogLog.warn("Failed to get value of property " + var5);
            } catch (RuntimeException var9) {
               LogLog.warn("Failed to get value of property " + var5);
            }
         }
      }
   }

   public boolean isHandledType(Class var1) {
      return (class$java$lang$String == null ? (class$java$lang$String = class$("java.lang.String")) : class$java$lang$String).isAssignableFrom(var1)
         || int.class.isAssignableFrom(var1)
         || long.class.isAssignableFrom(var1)
         || boolean.class.isAssignableFrom(var1)
         || (class$org$apache$log4j$Priority == null
               ? (class$org$apache$log4j$Priority = class$("org.apache.log4j.Priority"))
               : class$org$apache$log4j$Priority)
            .isAssignableFrom(var1);
   }
}
