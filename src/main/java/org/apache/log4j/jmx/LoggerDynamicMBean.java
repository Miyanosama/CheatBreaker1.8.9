package org.apache.log4j.jmx;

import java.beans.IntrospectionException;
import java.lang.reflect.Constructor;
import java.util.Enumeration;
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
import javax.management.MalformedObjectNameException;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.management.RuntimeOperationsException;
import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.OptionConverter;

public class LoggerDynamicMBean extends AbstractDynamicMBean implements NotificationListener {
   public Logger logger;
   public MBeanOperationInfo[] dOperations;
   public MBeanConstructorInfo[] dConstructors = new MBeanConstructorInfo[1];
   public String dClassName;
   public static Logger cat = Logger.getLogger(
      LoggerDynamicMBean.class$org$apache$log4j$jmx$LoggerDynamicMBean == null
         ? (LoggerDynamicMBean.class$org$apache$log4j$jmx$LoggerDynamicMBean = class$("org.apache.log4j.jmx.LoggerDynamicMBean"))
         : LoggerDynamicMBean.class$org$apache$log4j$jmx$LoggerDynamicMBean
   );
   public String dDescription;
   public Vector dAttributes;
   public static Class class$org$apache$log4j$jmx$LoggerDynamicMBean;
   public static Class class$org$apache$log4j$Appender;

   public void handleNotification(Notification var1, Object var2) {
      cat.debug("Received notification: " + var1.getType());
      this.registerAppenderMBean((Appender)var1.getUserData());
   }

   public void addAppender(String var1, String var2) {
      cat.debug("addAppender called with " + var1 + ", " + var2);
      Appender var3 = (Appender)OptionConverter.instantiateByClassName(
         var1,
         class$org$apache$log4j$Appender == null ? (class$org$apache$log4j$Appender = class$("org.apache.log4j.Appender")) : class$org$apache$log4j$Appender,
         null
      );
      var3.setName(var2);
      this.logger.addAppender(var3);
   }

   public void appenderMBeanRegistration() {
      Enumeration var1 = this.logger.getAllAppenders();

      while (var1.hasMoreElements()) {
         Appender var2 = (Appender)var1.nextElement();
         this.registerAppenderMBean(var2);
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

   public MBeanInfo getMBeanInfo() {
      MBeanAttributeInfo[] var1 = new MBeanAttributeInfo[this.dAttributes.size()];
      this.dAttributes.toArray(var1);
      return new MBeanInfo(this.dClassName, this.dDescription, var1, this.dConstructors, this.dOperations, new MBeanNotificationInfo[0]);
   }

   public Object invoke(String var1, Object[] var2, String[] var3) throws javax.management.MBeanException, javax.management.ReflectionException {
      if (var1.equals("addAppender")) {
         this.addAppender((String)var2[0], (String)var2[1]);
         return "Hello world.";
      } else {
         return null;
      }
   }

   public Logger getLogger() {
      return this.logger;
   }

   public LoggerDynamicMBean(Logger var1) {
      this.dOperations = new MBeanOperationInfo[1];
      this.dAttributes = new Vector();
      this.dClassName = this.getClass().getName();
      this.dDescription = "This MBean acts as a management facade for a org.apache.log4j.Logger instance.";
      this.logger = var1;
      this.buildDynamicMBeanInfo();
   }

   public Object getAttribute(String var1) throws javax.management.AttributeNotFoundException, javax.management.MBeanException, javax.management.ReflectionException {
      if (var1 == null) {
         throw new RuntimeOperationsException(
            new IllegalArgumentException("Attribute name cannot be null"), "Cannot invoke a getter of " + this.dClassName + " with null attribute name"
         );
      } else if (var1.equals("name")) {
         return this.logger.getName();
      } else if (var1.equals("priority")) {
         Level var2 = this.logger.getLevel();
         return var2 == null ? null : var2.toString();
      } else {
         if (var1.startsWith("appender=")) {
            try {
               return new ObjectName("log4j:" + var1);
            } catch (MalformedObjectNameException var3) {
               cat.error("Could not create ObjectName" + var1);
            } catch (RuntimeException var4) {
               cat.error("Could not create ObjectName" + var1);
            }
         }

         throw new AttributeNotFoundException("Cannot find " + var1 + " attribute in " + this.dClassName);
      }
   }

   public void postRegister(Boolean var1) {
      this.appenderMBeanRegistration();
   }

   public void registerAppenderMBean(Appender var1) {
      String var2 = getAppenderName(var1);
      cat.debug("Adding AppenderMBean for appender named " + var2);
      Object var3 = null;

      try {
         AppenderDynamicMBean var4 = new AppenderDynamicMBean(var1);
         var3 = new ObjectName("log4j", "appender", var2);
         if (!this.server.isRegistered((ObjectName)var3)) {
            this.registerMBean(var4, (ObjectName)var3);
            this.dAttributes.add(new MBeanAttributeInfo("appender=" + var2, "javax.management.ObjectName", "The " + var2 + " appender.", true, true, false));
         }
      } catch (JMException var5) {
         cat.error("Could not add appenderMBean for [" + var2 + "].", var5);
      } catch (IntrospectionException var6) {
         cat.error("Could not add appenderMBean for [" + var2 + "].", var6);
      } catch (RuntimeException var7) {
         cat.error("Could not add appenderMBean for [" + var2 + "].", var7);
      }
   }

   public void buildDynamicMBeanInfo() {
      Constructor[] var1 = this.getClass().getConstructors();
      this.dConstructors[0] = new MBeanConstructorInfo("HierarchyDynamicMBean(): Constructs a HierarchyDynamicMBean instance", var1[0]);
      this.dAttributes.add(new MBeanAttributeInfo("name", "java.lang.String", "The name of this Logger.", true, false, false));
      this.dAttributes.add(new MBeanAttributeInfo("priority", "java.lang.String", "The priority of this logger.", true, true, false));
      MBeanParameterInfo[] var2 = new MBeanParameterInfo[]{
         new MBeanParameterInfo("class name", "java.lang.String", "add an appender to this logger"),
         new MBeanParameterInfo("appender name", "java.lang.String", "name of the appender")
      };
      this.dOperations[0] = new MBeanOperationInfo("addAppender", "addAppender(): add an appender", var2, "void", 1);
   }

   public void setAttribute(Attribute var1) throws javax.management.AttributeNotFoundException, javax.management.InvalidAttributeValueException, javax.management.MBeanException, javax.management.ReflectionException {
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
         } else if (var2.equals("priority")) {
            if (var3 instanceof String) {
               String var4 = (String)var3;
               Level var5 = this.logger.getLevel();
               if (var4.equalsIgnoreCase("NULL")) {
                  var5 = null;
               } else {
                  var5 = OptionConverter.toLevel(var4, var5);
               }

               this.logger.setLevel(var5);
            }
         } else {
            throw new AttributeNotFoundException("Attribute " + var2 + " not found in " + this.getClass().getName());
         }
      }
   }
}
