package org.apache.log4j.or;

import java.util.Hashtable;
import net.minecraft.world.biome.BiomeGenMushroomIsland;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.RendererSupport;
import recovered.unidentified.UnidentifiedClass0606;

public class RendererMap {
   public Hashtable map = new Hashtable();
   public BiomeGenMushroomIsland field_0004;
   public static Class class$org$apache$log4j$or$ObjectRenderer;
   public UnidentifiedClass0606 field_0003;
   public static ObjectRenderer defaultRenderer = new DefaultRenderer();

   public ObjectRenderer getDefaultRenderer() {
      return defaultRenderer;
   }

   public ObjectRenderer get(Class var1) {
      ObjectRenderer var2 = null;

      for (Class var3 = var1; var3 != null; var3 = var3.getSuperclass()) {
         var2 = (ObjectRenderer)this.map.get(var3);
         if (var2 != null) {
            return var2;
         }

         var2 = this.searchInterfaces(var3);
         if (var2 != null) {
            return var2;
         }
      }

      return defaultRenderer;
   }

   public ObjectRenderer get(Object var1) {
      return var1 == null ? null : this.get(var1.getClass());
   }

   public void clear() {
      this.map.clear();
   }

   public static void addRenderer(RendererSupport var0, String var1, String var2) {
      LogLog.debug("Rendering class: [" + var2 + "], Rendered class: [" + var1 + "].");
      ObjectRenderer var3 = (ObjectRenderer)OptionConverter.instantiateByClassName(
         var2,
         class$org$apache$log4j$or$ObjectRenderer == null
            ? (class$org$apache$log4j$or$ObjectRenderer = class$("org.apache.log4j.or.ObjectRenderer"))
            : class$org$apache$log4j$or$ObjectRenderer,
         null
      );
      if (var3 == null) {
         LogLog.error("Could not instantiate renderer [" + var2 + "].");
      } else {
         try {
            Class var4 = Loader.loadClass(var1);
            var0.setRenderer(var4, var3);
         } catch (ClassNotFoundException var5) {
            LogLog.error("Could not find class [" + var1 + "].", var5);
         }
      }
   }

   public ObjectRenderer searchInterfaces(Class var1) {
      ObjectRenderer var2 = (ObjectRenderer)this.map.get(var1);
      if (var2 != null) {
         return var2;
      } else {
         Class[] var3 = var1.getInterfaces();

         for (int var4 = 0; var4 < var3.length; var4++) {
            var2 = this.searchInterfaces(var3[var4]);
            if (var2 != null) {
               return var2;
            }
         }

         return null;
      }
   }

   public String findAndRender(Object var1) {
      return var1 == null ? null : this.get(var1.getClass()).doRender(var1);
   }

   public void put(Class var1, ObjectRenderer var2) {
      this.map.put(var1, var2);
   }

   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw new NoClassDefFoundError().initCause(var2);
      }
   }
}
