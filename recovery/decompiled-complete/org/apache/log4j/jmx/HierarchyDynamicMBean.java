package org.apache.log4j.jmx;

import com.cheatbreaker.client.ui.element.type.custom.KeybindElement;
import io.netty.handler.codec.compression.Snappy;
import java.lang.reflect.Constructor;
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
import javax.management.Notification;
import javax.management.NotificationBroadcaster;
import javax.management.NotificationBroadcasterSupport;
import javax.management.NotificationFilter;
import javax.management.NotificationFilterSupport;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.RuntimeOperationsException;
import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.enchantment.EnchantmentDigging;
import org.apache.log4j.Appender;
import org.apache.log4j.Category;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.HierarchyEventListener;
import org.apache.log4j.spi.LoggerRepository;
import org.java_websocket.framing.PingFrame;
import org.slf4j.LoggerFactory;

public class HierarchyDynamicMBean extends AbstractDynamicMBean implements HierarchyEventListener, NotificationBroadcaster {
   public String dDescription;
   public ClippingHelper field_0005;
   public static Class class$org$apache$log4j$jmx$HierarchyDynamicMBean;
   public NotificationBroadcasterSupport nbs;
   public LoggerRepository hierarchy;
   public static String field_0012;
   public EnchantmentDigging field_0015;
   public LoggerFactory field_0003;
   public KeybindElement field_0006;
   public String dClassName;
   public static Logger log = Logger.getLogger(
      class$org$apache$log4j$jmx$HierarchyDynamicMBean == null
         ? (class$org$apache$log4j$jmx$HierarchyDynamicMBean = class$("org.apache.log4j.jmx.HierarchyDynamicMBean"))
         : class$org$apache$log4j$jmx$HierarchyDynamicMBean
   );
   public PingFrame field_0002;
   public Snappy field_0001;
   public static String field_0004;
   public MBeanConstructorInfo[] dConstructors = new MBeanConstructorInfo[1];
   public Vector vAttributes;
   public MBeanOperationInfo[] dOperations = new MBeanOperationInfo[1];

   public void addNotificationListener(NotificationListener var1, NotificationFilter var2, Object var3) {
      this.nbs.addNotificationListener(var1, var2, var3);
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
            if (var2.equals("threshold")) {
               Level var4 = OptionConverter.toLevel((String)var3, this.hierarchy.getThreshold());
               this.hierarchy.setThreshold(var4);
            }
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

   public void addAppenderEvent(Category var1, Appender var2) {
      log.debug("addAppenderEvent called: logger=" + var1.getName() + ", appender=" + var2.getName());
      Notification var3 = new Notification("addAppender." + var1.getName(), this, 127189353859975170L & 2139705L);
      var3.setUserData(var2);
      log.debug("sending notification.");
      this.nbs.sendNotification(var3);
   }

   public ObjectName addLoggerMBean(String var1) {
      Logger var2 = LogManager.exists(var1);
      return var2 != null ? this.addLoggerMBean(var2) : null;
   }

   public HierarchyDynamicMBean() {
      this.vAttributes = new Vector();
      this.dClassName = this.getClass().getName();
      this.dDescription = "This MBean acts as a management facade for org.apache.log4j.Hierarchy.";
      this.nbs = new NotificationBroadcasterSupport();
      this.hierarchy = LogManager.getLoggerRepository();
      this.buildDynamicMBeanInfo();
   }

   public void removeNotificationListener(NotificationListener var1) {
      this.nbs.removeNotificationListener(var1);
   }

   public Logger getLogger() {
      return log;
   }

   public MBeanInfo getMBeanInfo() {
      MBeanAttributeInfo[] var1 = new MBeanAttributeInfo[this.vAttributes.size()];
      this.vAttributes.toArray(var1);
      return new MBeanInfo(this.dClassName, this.dDescription, var1, this.dConstructors, this.dOperations, new MBeanNotificationInfo[0]);
   }

   public void removeAppenderEvent(Category var1, Appender var2) {
      log.debug("removeAppenderCalled: logger=" + var1.getName() + ", appender=" + var2.getName());
   }

   public void buildDynamicMBeanInfo() {
      Constructor[] var1 = this.getClass().getConstructors();
      this.dConstructors[0] = new MBeanConstructorInfo("HierarchyDynamicMBean(): Constructs a HierarchyDynamicMBean instance", var1[0]);
      this.vAttributes.add(new MBeanAttributeInfo("threshold", "java.lang.String", "The \"threshold\" state of the hiearchy.", true, true, false));
      MBeanParameterInfo[] var2 = new MBeanParameterInfo[]{new MBeanParameterInfo("name", "java.lang.String", "Create a logger MBean")};
      this.dOperations[0] = new MBeanOperationInfo("addLoggerMBean", "addLoggerMBean(): add a loggerMBean", var2, "javax.management.ObjectName", 1);
   }

   public void postRegister(Boolean var1) {
      log.debug("postRegister is called.");
      this.hierarchy.addHierarchyEventListener(this);
      Logger var2 = this.hierarchy.getRootLogger();
      this.addLoggerMBean(var2);
   }

   public Object invoke(String var1, Object[] var2, String[] var3) {
      if (var1 == null) {
         throw new RuntimeOperationsException(
            new IllegalArgumentException("Operation name cannot be null"), "Cannot invoke a null operation in " + this.dClassName
         );
      } else if (var1.equals("addLoggerMBean")) {
         return this.addLoggerMBean((String)var2[0]);
      } else {
         throw new ReflectionException(new NoSuchMethodException(var1), "Cannot find the operation " + var1 + " in " + this.dClassName);
      }
   }

   public Object getAttribute(String var1) {
      if (var1 == null) {
         throw new RuntimeOperationsException(
            new IllegalArgumentException("Attribute name cannot be null"), "Cannot invoke a getter of " + this.dClassName + " with null attribute name"
         );
      } else {
         log.debug("Called getAttribute with [" + var1 + "].");
         if (var1.equals("threshold")) {
            return this.hierarchy.getThreshold();
         } else {
            if (var1.startsWith("logger")) {
               int var2 = var1.indexOf("%3D");
               String var3 = var1;
               if (var2 > 0) {
                  var3 = var1.substring(0, var2) + '=' + var1.substring(var2 + 3);
               }

               try {
                  return new ObjectName("log4j:" + var3);
               } catch (JMException var5) {
                  log.error("Could not create ObjectName" + var3);
               } catch (RuntimeException var6) {
                  log.error("Could not create ObjectName" + var3);
               }
            }

            throw new AttributeNotFoundException("Cannot find " + var1 + " attribute in " + this.dClassName);
         }
      }
   }

   public MBeanNotificationInfo[] getNotificationInfo() {
      return this.nbs.getNotificationInfo();
   }

   public ObjectName addLoggerMBean(Logger var1) {
      String var2 = var1.getName();
      ObjectName var3 = null;

      try {
         LoggerDynamicMBean var4 = new LoggerDynamicMBean(var1);
         var3 = new ObjectName("log4j", "logger", var2);
         if (!this.server.isRegistered(var3)) {
            this.registerMBean(var4, var3);
            NotificationFilterSupport var5 = new NotificationFilterSupport();
            var5.enableType("addAppender." + var1.getName());
            log.debug("---Adding logger [" + var2 + "] as listener.");
            this.nbs.addNotificationListener(var4, var5, null);
            this.vAttributes.add(new MBeanAttributeInfo("logger=" + var2, "javax.management.ObjectName", "The " + var2 + " logger.", true, true, false));
         }
      } catch (JMException var6) {
         log.error("Could not add loggerMBean for [" + var2 + "].", var6);
      } catch (RuntimeException var7) {
         log.error("Could not add loggerMBean for [" + var2 + "].", var7);
      }

      return var3;
   }
}
