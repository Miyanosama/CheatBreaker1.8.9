package org.apache.log4j.jmx;

import java.util.Enumeration;
import java.util.Vector;
import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.DynamicMBean;
import javax.management.InstanceNotFoundException;
import javax.management.JMException;
import javax.management.MBeanRegistration;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.RuntimeOperationsException;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonExtension$1;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.tileentity.MobSpawnerBaseLogic$WeightedRandomMinecart;
import net.minecraft.world.gen.feature.WorldGenSand;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces$JunglePyramid;
import org.apache.log4j.Appender;
import org.apache.log4j.Logger;

public abstract class AbstractDynamicMBean implements DynamicMBean, MBeanRegistration {
   public Vector mbeanList = new Vector();
   public Block field_0007;
   public BlockPistonExtension$1 field_0003;
   public String dClassName;
   public MobSpawnerBaseLogic$WeightedRandomMinecart field_0000;
   public EntityFishHook field_0001;
   public ComponentScatteredFeaturePieces$JunglePyramid field_0008;
   public WorldGenSand field_0005;
   public MBeanServer server;

   public static String getAppenderName(Appender var0) {
      String var1 = var0.getName();
      if (var1 == null || var1.trim().length() == 0) {
         var1 = var0.toString();
      }

      return var1;
   }

   public AttributeList getAttributes(String[] var1) {
      if (var1 == null) {
         throw new RuntimeOperationsException(new IllegalArgumentException("attributeNames[] cannot be null"), "Cannot invoke a getter of " + this.dClassName);
      } else {
         AttributeList var2 = new AttributeList();
         if (var1.length == 0) {
            return var2;
         } else {
            for (int var3 = 0; var3 < var1.length; var3++) {
               try {
                  Object var4 = this.getAttribute(var1[var3]);
                  var2.add(new Attribute(var1[var3], var4));
               } catch (JMException var5) {
                  var5.printStackTrace();
               } catch (RuntimeException var6) {
                  var6.printStackTrace();
               }
            }

            return var2;
         }
      }
   }

   public void registerMBean(Object var1, ObjectName var2) {
      this.server.registerMBean(var1, var2);
      this.mbeanList.add(var2);
   }

   public ObjectName preRegister(MBeanServer var1, ObjectName var2) {
      this.getLogger().debug("preRegister called. Server=" + var1 + ", name=" + var2);
      this.server = var1;
      return var2;
   }

   public void postDeregister() {
      this.getLogger().debug("postDeregister is called.");
   }

   public void preDeregister() {
      this.getLogger().debug("preDeregister called.");
      Enumeration var1 = this.mbeanList.elements();

      while (var1.hasMoreElements()) {
         ObjectName var2 = (ObjectName)var1.nextElement();

         try {
            this.server.unregisterMBean(var2);
         } catch (InstanceNotFoundException var4) {
            this.getLogger().warn("Missing MBean " + var2.getCanonicalName());
         } catch (MBeanRegistrationException var5) {
            this.getLogger().warn("Failed unregistering " + var2.getCanonicalName());
         }
      }
   }

   public AttributeList setAttributes(AttributeList var1) {
      if (var1 == null) {
         throw new RuntimeOperationsException(
            new IllegalArgumentException("AttributeList attributes cannot be null"), "Cannot invoke a setter of " + this.dClassName
         );
      } else {
         AttributeList var2 = new AttributeList();
         if (var1.isEmpty()) {
            return var2;
         } else {
            for (Attribute var4 : var1) {
               try {
                  this.setAttribute(var4);
                  String var5 = var4.getName();
                  Object var6 = this.getAttribute(var5);
                  var2.add(new Attribute(var5, var6));
               } catch (JMException var7) {
                  var7.printStackTrace();
               } catch (RuntimeException var8) {
                  var8.printStackTrace();
               }
            }

            return var2;
         }
      }
   }

   public abstract Logger getLogger();

   public void postRegister(Boolean var1) {
   }
}
