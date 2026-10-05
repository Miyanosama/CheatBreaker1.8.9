package net.optifine.shaders;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.ConnectedParser;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorForge;
import net.optifine.shaders.config.MacroProcessor;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.StrUtils;

public class EntityAliases {
   public static int[] entityAliases = null;
   public static boolean updateOnResourcesReloaded;

   public static void update(IShaderPack var0) {
      method_22569();
      if (var0 != null) {
         if (Reflector.Loader_getActiveModList.exists() && Config.getResourceManager() == null) {
            Config.dbg("[Shaders] Delayed loading of entity mappings after resources are loaded");
            updateOnResourcesReloaded = true;
         } else {
            ArrayList var1 = new ArrayList();
            String var2 = "/shaders/entity.properties";
            InputStream var3 = var0.getResourceAsStream(var2);
            if (var3 != null) {
               loadEntityAliases(var3, var2, var1);
            }

            loadModEntityAliases(var1);
            if (var1.size() > 0) {
               entityAliases = toArray(var1);
            }
         }
      }
   }

   public static int getEntityAliasId(int var0) {
      if (entityAliases == null) {
         return -1;
      } else {
         return var0 >= 0 && var0 < entityAliases.length ? entityAliases[var0] : -1;
      }
   }

   public static int[] toArray(List<Integer> var0) {
      int[] var1 = new int[var0.size()];

      for (int var2 = 0; var2 < var1.length; var2++) {
         var1[var2] = (Integer)var0.get(var2);
      }

      return var1;
   }

   public static void addToList(List<Integer> var0, int var1, int var2) {
      while (var0.size() <= var1) {
         var0.add(-1);
      }

      var0.set(var1, var2);
   }

   public static void resourcesReloaded() {
      if (updateOnResourcesReloaded) {
         updateOnResourcesReloaded = false;
         update(Shaders.getShaderPack());
      }
   }

   public static void method_22569() {
      entityAliases = null;
   }

   public static void loadModEntityAliases(List<Integer> var0) {
      String[] var1 = ReflectorForge.getForgeModIds();

      for (int var2 = 0; var2 < var1.length; var2++) {
         String var3 = var1[var2];

         try {
            ResourceLocation var4 = new ResourceLocation(var3, "shaders/entity.properties");
            InputStream var5 = Config.getResourceStream(var4);
            loadEntityAliases(var5, var4.toString(), var0);
         } catch (IOException var6) {
         }
      }
   }

   public static void loadEntityAliases(InputStream var0, String var1, List<Integer> var2) {
      if (var0 != null) {
         try {
            var0 = MacroProcessor.process(var0, var1);
            PropertiesOrdered var3 = new PropertiesOrdered();
            var3.load(var0);
            var0.close();
            Config.dbg("[Shaders] Parsing entity mappings: " + var1);
            ConnectedParser var4 = new ConnectedParser("Shaders");

            for (Object var6 : var3.keySet()) {
               String var7 = (String)var6;
               String var8 = var3.getProperty(var7);
               String var9 = "entity.";
               if (!var7.startsWith(var9)) {
                  Config.warn("[Shaders] Invalid entity ID: " + var7);
               } else {
                  String var10 = StrUtils.removePrefix(var7, var9);
                  int var11 = Config.parseInt(var10, -1);
                  if (var11 < 0) {
                     Config.warn("[Shaders] Invalid entity alias ID: " + var11);
                  } else {
                     int[] var12 = var4.method_07375(var8);
                     if (var12 != null && var12.length >= 1) {
                        for (int var13 = 0; var13 < var12.length; var13++) {
                           int var14 = var12[var13];
                           addToList(var2, var14, var11);
                        }
                     } else {
                        Config.warn("[Shaders] Invalid entity ID mapping: " + var7 + "=" + var8);
                     }
                  }
               }
            }
         } catch (IOException var15) {
            Config.warn("[Shaders] Error reading: " + var1);
         }
      }
   }
}
