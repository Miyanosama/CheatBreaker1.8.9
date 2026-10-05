package org.apache.log4j.config;

import com.cheatbreaker.client.module.type.SaturationModule;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.InterruptedIOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Properties;
import net.minecraft.block.state.pattern.FactoryBlockPattern;
import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.OptionHandler;

public class PropertySetter {
   public FactoryBlockPattern field_0004;
   public PropertyDescriptor[] props;
   public static Class class$org$apache$log4j$spi$ErrorHandler;
   public static Class class$org$apache$log4j$spi$OptionHandler;
   public static Class class$org$apache$log4j$Priority;
   public Object obj;
   public SaturationModule field_0008;
   public static Class class$java$lang$String;
   public LogManager field_0002;

   public PropertyDescriptor getPropertyDescriptor(String var1) {
      if (this.props == null) {
         this.introspect();
      }

      for (int var2 = 0; var2 < this.props.length; var2++) {
         if (var1.equals(this.props[var2].getName())) {
            return this.props[var2];
         }
      }

      return null;
   }

   public static void setProperties(Object var0, Properties var1, String var2) {
      new PropertySetter(var0).setProperties(var1, var2);
   }

   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw new NoClassDefFoundError().initCause(var2);
      }
   }

   public void setProperty(PropertyDescriptor var1, String var2, String var3) {
      Method var4 = var1.getWriteMethod();
      if (var4 == null) {
         throw new PropertySetterException("No setter for property [" + var2 + "].");
      } else {
         Class[] var5 = var4.getParameterTypes();
         if (var5.length != 1) {
            throw new PropertySetterException("#params for setter != 1");
         } else {
            Object var6;
            try {
               var6 = this.convertArg(var3, var5[0]);
            } catch (Throwable var8) {
               throw new PropertySetterException("Conversion to type [" + var5[0] + "] failed. Reason: " + var8);
            }

            if (var6 == null) {
               throw new PropertySetterException("Conversion to type [" + var5[0] + "] failed.");
            } else {
               LogLog.debug("Setting property [" + var2 + "] to [" + var6 + "].");

               try {
                  var4.invoke(this.obj, var6);
               } catch (IllegalAccessException var9) {
                  throw new PropertySetterException(var9);
               } catch (InvocationTargetException var10) {
                  if (var10.getTargetException() instanceof InterruptedException || var10.getTargetException() instanceof InterruptedIOException) {
                     Thread.currentThread().interrupt();
                  }

                  throw new PropertySetterException(var10);
               } catch (RuntimeException var11) {
                  throw new PropertySetterException(var11);
               }
            }
         }
      }
   }

   public void setProperty(String var1, String var2) {
      if (var2 != null) {
         var1 = Introspector.decapitalize(var1);
         PropertyDescriptor var3 = this.getPropertyDescriptor(var1);
         if (var3 == null) {
            LogLog.warn("No such property [" + var1 + "] in " + this.obj.getClass().getName() + ".");
         } else {
            try {
               this.setProperty(var3, var1, var2);
            } catch (PropertySetterException var5) {
               LogLog.warn("Failed to set property [" + var1 + "] to value \"" + var2 + "\". ", var5.rootCause);
            }
         }
      }
   }

   public void activate() {
      if (this.obj instanceof OptionHandler) {
         ((OptionHandler)this.obj).activateOptions();
      }
   }

   public void setProperties(Properties var1, String var2) {
      int var3 = var2.length();
      Enumeration var4 = var1.propertyNames();

      while (var4.hasMoreElements()) {
         String var5 = (String)var4.nextElement();
         if (var5.startsWith(var2) && var5.indexOf(46, var3 + 1) <= 0) {
            String var6 = OptionConverter.findAndSubst(var5, var1);
            var5 = var5.substring(var3);
            if (!"layout".equals(var5) && !"errorhandler".equals(var5) || !(this.obj instanceof Appender)) {
               PropertyDescriptor var7 = this.getPropertyDescriptor(Introspector.decapitalize(var5));
               if (var7 != null
                  && (class$org$apache$log4j$spi$OptionHandler == null
                        ? (class$org$apache$log4j$spi$OptionHandler = class$("org.apache.log4j.spi.OptionHandler"))
                        : class$org$apache$log4j$spi$OptionHandler)
                     .isAssignableFrom(var7.getPropertyType())
                  && var7.getWriteMethod() != null) {
                  OptionHandler var8 = (OptionHandler)OptionConverter.instantiateByKey(var1, var2 + var5, var7.getPropertyType(), null);
                  PropertySetter var9 = new PropertySetter(var8);
                  var9.setProperties(var1, var2 + var5 + ".");

                  try {
                     var7.getWriteMethod().invoke(this.obj, var8);
                  } catch (IllegalAccessException var11) {
                     LogLog.warn("Failed to set property [" + var5 + "] to value \"" + var6 + "\". ", var11);
                  } catch (InvocationTargetException var12) {
                     if (var12.getTargetException() instanceof InterruptedException || var12.getTargetException() instanceof InterruptedIOException) {
                        Thread.currentThread().interrupt();
                     }

                     LogLog.warn("Failed to set property [" + var5 + "] to value \"" + var6 + "\". ", var12);
                  } catch (RuntimeException var13) {
                     LogLog.warn("Failed to set property [" + var5 + "] to value \"" + var6 + "\". ", var13);
                  }
               } else {
                  this.setProperty(var5, var6);
               }
            }
         }
      }

      this.activate();
   }

   public PropertySetter(Object var1) {
      this.obj = var1;
   }

   public Object convertArg(String var1, Class var2) {
      if (var1 == null) {
         return null;
      } else {
         String var3 = var1.trim();
         if ((class$java$lang$String == null ? (class$java$lang$String = class$("java.lang.String")) : class$java$lang$String).isAssignableFrom(var2)) {
            return var1;
         } else if (int.class.isAssignableFrom(var2)) {
            return new Integer(var3);
         } else if (long.class.isAssignableFrom(var2)) {
            return new Long(var3);
         } else {
            if (boolean.class.isAssignableFrom(var2)) {
               if ("true".equalsIgnoreCase(var3)) {
                  return Boolean.TRUE;
               }

               if ("false".equalsIgnoreCase(var3)) {
                  return Boolean.FALSE;
               }
            } else {
               if ((class$org$apache$log4j$Priority == null
                     ? (class$org$apache$log4j$Priority = class$("org.apache.log4j.Priority"))
                     : class$org$apache$log4j$Priority)
                  .isAssignableFrom(var2)) {
                  return OptionConverter.toLevel(var3, Level.DEBUG);
               }

               if ((class$org$apache$log4j$spi$ErrorHandler == null
                     ? (class$org$apache$log4j$spi$ErrorHandler = class$("org.apache.log4j.spi.ErrorHandler"))
                     : class$org$apache$log4j$spi$ErrorHandler)
                  .isAssignableFrom(var2)) {
                  return OptionConverter.instantiateByClassName(
                     var3,
                     class$org$apache$log4j$spi$ErrorHandler == null
                        ? (class$org$apache$log4j$spi$ErrorHandler = class$("org.apache.log4j.spi.ErrorHandler"))
                        : class$org$apache$log4j$spi$ErrorHandler,
                     null
                  );
               }
            }

            return null;
         }
      }
   }

   public void introspect() {
      try {
         BeanInfo var1 = Introspector.getBeanInfo(this.obj.getClass());
         this.props = var1.getPropertyDescriptors();
      } catch (IntrospectionException var2) {
         LogLog.error("Failed to introspect " + this.obj + ": " + var2.getMessage());
         this.props = new PropertyDescriptor[0];
      }
   }
}
