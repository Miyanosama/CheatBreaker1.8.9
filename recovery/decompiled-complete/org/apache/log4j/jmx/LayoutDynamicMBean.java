package org.apache.log4j.jmx;

import java.beans.BeanInfo;
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
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanConstructorInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanNotificationInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.RuntimeOperationsException;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$Crossing3;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.OptionHandler;

public class LayoutDynamicMBean extends AbstractDynamicMBean {
   public StructureNetherBridgePieces$Crossing3 field_0012;
   public MBeanConstructorInfo[] dConstructors = new MBeanConstructorInfo[1];
   public Hashtable dynamicProps;
   public static Class class$org$apache$log4j$jmx$LayoutDynamicMBean;
   public String dClassName;
   public MBeanOperationInfo[] dOperations;
   public static Class class$java$lang$String;
   public Vector dAttributes = new Vector();
   public Layout layout;
   public static Logger cat = Logger.getLogger(
      class$org$apache$log4j$jmx$LayoutDynamicMBean == null
         ? (class$org$apache$log4j$jmx$LayoutDynamicMBean = class$("org.apache.log4j.jmx.LayoutDynamicMBean"))
         : class$org$apache$log4j$jmx$LayoutDynamicMBean
   );
   public static Class class$org$apache$log4j$Priority;
   public String dDescription;
   public static Class class$org$apache$log4j$Level;

   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw new NoClassDefFoundError().initCause(var2);
      }
   }

   public boolean isSupportedType(Class var1) {
      if (var1.isPrimitive()) {
         return true;
      } else {
         return var1 == (class$java$lang$String == null ? (class$java$lang$String = class$("java.lang.String")) : class$java$lang$String)
            ? true
            : var1.isAssignableFrom(
               class$org$apache$log4j$Level == null ? (class$org$apache$log4j$Level = class$("org.apache.log4j.Level")) : class$org$apache$log4j$Level
            );
      }
   }

   public Object invoke(String var1, Object[] var2, String[] var3) {
      if (var1.equals("activateOptions") && this.layout instanceof OptionHandler) {
         Layout var4 = this.layout;
         var4.activateOptions();
         return "Options activated.";
      } else {
         return null;
      }
   }

   public Logger getLogger() {
      return cat;
   }

   public LayoutDynamicMBean(Layout var1) {
      this.dClassName = this.getClass().getName();
      this.dynamicProps = new Hashtable(5);
      this.dOperations = new MBeanOperationInfo[1];
      this.dDescription = "This MBean acts as a management facade for log4j layouts.";
      this.layout = var1;
      this.buildDynamicMBeanInfo();
   }

   public Object getAttribute(String var1) {
      if (var1 == null) {
         throw new RuntimeOperationsException(
            new IllegalArgumentException("Attribute name cannot be null"), "Cannot invoke a getter of " + this.dClassName + " with null attribute name"
         );
      } else {
         MethodUnion var2 = (MethodUnion)this.dynamicProps.get(var1);
         cat.debug("----name=" + var1 + ", mu=" + var2);
         if (var2 != null && var2.readMethod != null) {
            try {
               return var2.readMethod.invoke(this.layout, null);
            } catch (InvocationTargetException var4) {
               if (var4.getTargetException() instanceof InterruptedException || var4.getTargetException() instanceof InterruptedIOException) {
                  Thread.currentThread().interrupt();
               }

               return null;
            } catch (IllegalAccessException var5) {
               return null;
            } catch (RuntimeException var6) {
               return null;
            }
         } else {
            throw new AttributeNotFoundException("Cannot find " + var1 + " attribute in " + this.dClassName);
         }
      }
   }

   public void buildDynamicMBeanInfo() {
      Constructor[] var1 = this.getClass().getConstructors();
      this.dConstructors[0] = new MBeanConstructorInfo("LayoutDynamicMBean(): Constructs a LayoutDynamicMBean instance", var1[0]);
      BeanInfo var2 = Introspector.getBeanInfo(this.layout.getClass());
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
                  class$org$apache$log4j$Level == null ? (class$org$apache$log4j$Level = class$("org.apache.log4j.Level")) : class$org$apache$log4j$Level
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
      this.dOperations[0] = new MBeanOperationInfo("activateOptions", "activateOptions(): add an layout", var11, "void", 1);
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
                  var4.writeMethod.invoke(this.layout, var5);
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
            } else {
               throw new AttributeNotFoundException("Attribute " + var2 + " not found in " + this.getClass().getName());
            }
         }
      }
   }

   public MBeanInfo getMBeanInfo() {
      cat.debug("getMBeanInfo called.");
      MBeanAttributeInfo[] var1 = new MBeanAttributeInfo[this.dAttributes.size()];
      this.dAttributes.toArray(var1);
      return new MBeanInfo(this.dClassName, this.dDescription, var1, this.dConstructors, this.dOperations, new MBeanNotificationInfo[0]);
   }
}
