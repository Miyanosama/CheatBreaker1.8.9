package org.apache.log4j.jmx;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.InterruptedIOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Vector;
import javax.management.Attribute;
import javax.management.AttributeNotFoundException;
import javax.management.JMException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanConstructorInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanNotificationInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.RuntimeOperationsException;
import net.minecraft.client.Minecraft$1;
import net.minecraft.entity.passive.EntityRabbit$AIAvoidEntity;
import net.minecraft.world.storage.MapData$MapInfo;
import org.apache.log4j.Appender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.OptionHandler;

public class AppenderDynamicMBean extends AbstractDynamicMBean {
   public Appender appender;
   public static Class class$org$apache$log4j$Layout;
   public Minecraft$1 field_0008;
   public static Class class$org$apache$log4j$jmx$AppenderDynamicMBean;
   public String dDescription;
   public MapData$MapInfo field_0011;
   public static Class class$java$lang$String;
   public Vector dAttributes;
   public String dClassName;
   public Hashtable dynamicProps;
   public MBeanConstructorInfo[] dConstructors = new MBeanConstructorInfo[1];
   public EntityRabbit$AIAvoidEntity field_0002;
   public static Logger cat = Logger.getLogger(
      class$org$apache$log4j$jmx$AppenderDynamicMBean == null
         ? (class$org$apache$log4j$jmx$AppenderDynamicMBean = class$("org.apache.log4j.jmx.AppenderDynamicMBean"))
         : class$org$apache$log4j$jmx$AppenderDynamicMBean
   );
   public static Class class$org$apache$log4j$Priority;
   public MBeanOperationInfo[] dOperations;

   public Object getAttribute(String var1) {
      if (var1 == null) {
         throw new RuntimeOperationsException(
            new IllegalArgumentException("Attribute name cannot be null"), "Cannot invoke a getter of " + this.dClassName + " with null attribute name"
         );
      } else {
         cat.debug("getAttribute called with [" + var1 + "].");
         if (var1.startsWith("appender=" + this.appender.getName() + ",layout")) {
            try {
               return new ObjectName("log4j:" + var1);
            } catch (MalformedObjectNameException var4) {
               cat.error("attributeName", var4);
            } catch (RuntimeException var5) {
               cat.error("attributeName", var5);
            }
         }

         MethodUnion var2 = (MethodUnion)this.dynamicProps.get(var1);
         if (var2 != null && var2.readMethod != null) {
            try {
               return var2.readMethod.invoke(this.appender, null);
            } catch (IllegalAccessException var6) {
               return null;
            } catch (InvocationTargetException var7) {
               if (var7.getTargetException() instanceof InterruptedException || var7.getTargetException() instanceof InterruptedIOException) {
                  Thread.currentThread().interrupt();
               }

               return null;
            } catch (RuntimeException var8) {
               return null;
            }
         } else {
            throw new AttributeNotFoundException("Cannot find " + var1 + " attribute in " + this.dClassName);
         }
      }
   }

   public MBeanInfo getMBeanInfo() {
      cat.debug("getMBeanInfo called.");
      MBeanAttributeInfo[] var1 = new MBeanAttributeInfo[this.dAttributes.size()];
      this.dAttributes.toArray(var1);
      return new MBeanInfo(this.dClassName, this.dDescription, var1, this.dConstructors, this.dOperations, new MBeanNotificationInfo[0]);
   }

   public boolean isSupportedType(Class var1) {
      if (var1.isPrimitive()) {
         return true;
      } else {
         return var1 == (class$java$lang$String == null ? (class$java$lang$String = class$("java.lang.String")) : class$java$lang$String)
            ? true
            : var1.isAssignableFrom(
               class$org$apache$log4j$Priority == null
                  ? (class$org$apache$log4j$Priority = class$("org.apache.log4j.Priority"))
                  : class$org$apache$log4j$Priority
            );
      }
   }

   public Object invoke(String var1, Object[] var2, String[] var3) {
      if (var1.equals("activateOptions") && this.appender instanceof OptionHandler) {
         OptionHandler var5 = (OptionHandler)this.appender;
         var5.activateOptions();
         return "Options activated.";
      } else {
         if (var1.equals("setLayout")) {
            Layout var4 = (Layout)OptionConverter.instantiateByClassName(
               (String)var2[0],
               class$org$apache$log4j$Layout == null ? (class$org$apache$log4j$Layout = class$("org.apache.log4j.Layout")) : class$org$apache$log4j$Layout,
               null
            );
            this.appender.setLayout(var4);
            this.registerLayoutMBean(var4);
         }

         return null;
      }
   }

   public AppenderDynamicMBean(Appender var1) {
      this.dAttributes = new Vector();
      this.dClassName = this.getClass().getName();
      this.dynamicProps = new Hashtable(5);
      this.dOperations = new MBeanOperationInfo[2];
      this.dDescription = "This MBean acts as a management facade for log4j appenders.";
      this.appender = var1;
      this.buildDynamicMBeanInfo();
   }

   public void setAttribute(Attribute var1) {
      if (var1 == null) {
         throw new RuntimeOperationsException(
            new IllegalArgumentException("Attribute cannot be null"), "Cannot invoke a setter of " + this.dClassName + " with null attribute"
         );
      } else {
         String var2 = var1.getName();
         Object var3 = var1.getValue();
         if (var2 == null) {
            throw new RuntimeOperationsException(
               new IllegalArgumentException("Attribute name cannot be null"), "Cannot invoke the setter of " + this.dClassName + " with null attribute name"
            );
         } else {
            MethodUnion var4 = (MethodUnion)this.dynamicProps.get(var2);
            if (var4 != null && var4.writeMethod != null) {
               Object[] var5 = new Object[1];
               Class[] var6 = var4.writeMethod.getParameterTypes();
               if (var6[0]
                  == (
                     class$org$apache$log4j$Priority == null
                        ? (class$org$apache$log4j$Priority = class$("org.apache.log4j.Priority"))
                        : class$org$apache$log4j$Priority
                  )) {
                  var3 = OptionConverter.toLevel((String)var3, (Level)this.getAttribute(var2));
               }

               var5[0] = var3;

               try {
                  var4.writeMethod.invoke(this.appender, var5);
               } catch (InvocationTargetException var8) {
                  if (var8.getTargetException() instanceof InterruptedException || var8.getTargetException() instanceof InterruptedIOException) {
                     Thread.currentThread().interrupt();
                  }

                  cat.error("FIXME", var8);
               } catch (IllegalAccessException var9) {
                  cat.error("FIXME", var9);
               } catch (RuntimeException var10) {
                  cat.error("FIXME", var10);
               }
            } else if (!var2.endsWith(".layout")) {
               throw new AttributeNotFoundException("Attribute " + var2 + " not found in " + this.getClass().getName());
            }
         }
      }
   }

   public Logger getLogger() {
      return cat;
   }

   public ObjectName preRegister(MBeanServer var1, ObjectName var2) {
      cat.debug("preRegister called. Server=" + var1 + ", name=" + var2);
      this.server = var1;
      this.registerLayoutMBean(this.appender.getLayout());
      return var2;
   }

   public void buildDynamicMBeanInfo() {
      Constructor[] var1 = this.getClass().getConstructors();
      this.dConstructors[0] = new MBeanConstructorInfo("AppenderDynamicMBean(): Constructs a AppenderDynamicMBean instance", var1[0]);
      BeanInfo var2 = Introspector.getBeanInfo(this.appender.getClass());
      PropertyDescriptor[] var3 = var2.getPropertyDescriptors();
      int var4 = var3.length;

      for (int var5 = 0; var5 < var4; var5++) {
         String var6 = var3[var5].getName();
         Method var7 = var3[var5].getReadMethod();
         Method var8 = var3[var5].getWriteMethod();
         if (var7 != null) {
            Class var9 = var7.getReturnType();
            if (this.isSupportedType(var9)) {
               String var10;
               if (var9.isAssignableFrom(
                  class$org$apache$log4j$Priority == null
                     ? (class$org$apache$log4j$Priority = class$("org.apache.log4j.Priority"))
                     : class$org$apache$log4j$Priority
               )) {
                  var10 = "java.lang.String";
               } else {
                  var10 = var9.getName();
               }

               this.dAttributes.add(new MBeanAttributeInfo(var6, var10, "Dynamic", true, var8 != null, false));
               this.dynamicProps.put(var6, new MethodUnion(var7, var8));
            }
         }
      }

      MBeanParameterInfo[] var11 = new MBeanParameterInfo[0];
      this.dOperations[0] = new MBeanOperationInfo("activateOptions", "activateOptions(): add an appender", var11, "void", 1);
      var11 = new MBeanParameterInfo[]{new MBeanParameterInfo("layout class", "java.lang.String", "layout class")};
      this.dOperations[1] = new MBeanOperationInfo("setLayout", "setLayout(): add a layout", var11, "void", 1);
   }

   public void registerLayoutMBean(Layout var1) {
      if (var1 != null) {
         String var2 = getAppenderName(this.appender) + ",layout=" + var1.getClass().getName();
         cat.debug("Adding LayoutMBean:" + var2);
         Object var3 = null;

         try {
            LayoutDynamicMBean var4 = new LayoutDynamicMBean(var1);
            var3 = new ObjectName("log4j:appender=" + var2);
            if (!this.server.isRegistered((ObjectName)var3)) {
               this.registerMBean(var4, (ObjectName)var3);
               this.dAttributes.add(new MBeanAttributeInfo("appender=" + var2, "javax.management.ObjectName", "The " + var2 + " layout.", true, true, false));
            }
         } catch (JMException var5) {
            cat.error("Could not add DynamicLayoutMBean for [" + var2 + "].", var5);
         } catch (IntrospectionException var6) {
            cat.error("Could not add DynamicLayoutMBean for [" + var2 + "].", var6);
         } catch (RuntimeException var7) {
            cat.error("Could not add DynamicLayoutMBean for [" + var2 + "].", var7);
         }
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
