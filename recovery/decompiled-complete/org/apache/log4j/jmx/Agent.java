package org.apache.log4j.jmx;

import java.io.InterruptedIOException;
import java.lang.reflect.InvocationTargetException;
import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import net.minecraft.block.BlockTrapDoor$DoorHalf;
import net.minecraft.client.renderer.block.model.BreakingFour$1;
import net.minecraft.entity.item.EntityPainting$EnumArt;
import net.minecraft.world.storage.WorldInfo$7;
import org.apache.log4j.Logger;

public class Agent {
   public BreakingFour$1 field_0003;
   public static Logger log = Logger.getLogger(
      Agent.class$org$apache$log4j$jmx$Agent == null
         ? (Agent.class$org$apache$log4j$jmx$Agent = class$("org.apache.log4j.jmx.Agent"))
         : Agent.class$org$apache$log4j$jmx$Agent
   );
   public BlockTrapDoor$DoorHalf field_0002;
   public WorldInfo$7 field_0004;
   public static Class class$org$apache$log4j$jmx$Agent;
   public EntityPainting$EnumArt field_0001;

   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw new NoClassDefFoundError().initCause(var2);
      }
   }

   public static void startServer(Object var0) {
      try {
         var0.getClass().getMethod("start").invoke(var0);
      } catch (InvocationTargetException var3) {
         Throwable var2 = var3.getTargetException();
         if (var2 instanceof RuntimeException) {
            throw (RuntimeException)var2;
         } else if (var2 == null) {
            throw new RuntimeException();
         } else {
            if (var2 instanceof InterruptedException || var2 instanceof InterruptedIOException) {
               Thread.currentThread().interrupt();
            }

            throw new RuntimeException(var2.toString());
         }
      } catch (NoSuchMethodException var4) {
         throw new RuntimeException(var4.toString());
      } catch (IllegalAccessException var5) {
         throw new RuntimeException(var5.toString());
      }
   }

   public void start() {
      MBeanServer var1 = MBeanServerFactory.createMBeanServer();
      Object var2 = createServer();

      try {
         log.info("Registering HtmlAdaptorServer instance.");
         var1.registerMBean(var2, new ObjectName("Adaptor:name=html,port=8082"));
         log.info("Registering HierarchyDynamicMBean instance.");
         HierarchyDynamicMBean var3 = new HierarchyDynamicMBean();
         var1.registerMBean(var3, new ObjectName("log4j:hiearchy=default"));
      } catch (JMException var4) {
         log.error("Problem while registering MBeans instances.", var4);
         return;
      } catch (RuntimeException var5) {
         log.error("Problem while registering MBeans instances.", var5);
         return;
      }

      startServer(var2);
   }

   public static Object createServer() {
      Object var0 = null;

      try {
         return Class.forName("com.sun.jdmk.comm.HtmlAdapterServer").newInstance();
      } catch (ClassNotFoundException var2) {
         throw new RuntimeException(var2.toString());
      } catch (InstantiationException var3) {
         throw new RuntimeException(var3.toString());
      } catch (IllegalAccessException var4) {
         throw new RuntimeException(var4.toString());
      }
   }
}
