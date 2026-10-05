package net.optifine;

import java.util.ArrayList;
import java.util.Properties;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.ConnectedParser;

public class RandomEntityProperties {
   public ResourceLocation[] resourceLocations;
   public String basePath;
   public String name = null;
   public RandomEntityRule[] rules;

   public boolean isValid(String var1) {
      if (this.resourceLocations == null && this.rules == null) {
         Config.warn("No skins specified: " + var1);
         return false;
      } else {
         if (this.rules != null) {
            for (int var2 = 0; var2 < this.rules.length; var2++) {
               RandomEntityRule var3 = this.rules[var2];
               if (!var3.isValid(var1)) {
                  return false;
               }
            }
         }

         if (this.resourceLocations != null) {
            for (int var4 = 0; var4 < this.resourceLocations.length; var4++) {
               ResourceLocation var5 = this.resourceLocations[var4];
               if (!Config.hasResource(var5)) {
                  Config.warn("Texture not found: " + var5.getResourcePath());
                  return false;
               }
            }
         }

         return true;
      }
   }

   public RandomEntityRule[] parseRules(Properties var1, String var2, ResourceLocation var3, ConnectedParser var4) {
      ArrayList var5 = new ArrayList();
      int var6 = var1.size();

      for (int var7 = 0; var7 < var6; var7++) {
         int var8 = var7 + 1;
         String var9 = var1.getProperty("textures." + var8);
         if (var9 == null) {
            var9 = var1.getProperty("skins." + var8);
         }

         if (var9 != null) {
            RandomEntityRule var10 = new RandomEntityRule(var1, var2, var3, var8, var9, var4);
            if (var10.isValid(var2)) {
               var5.add(var10);
            }
         }
      }

      return (net.optifine.RandomEntityRule[])var5.toArray(new RandomEntityRule[var5.size()]);
   }

   public RandomEntityProperties(String var1, ResourceLocation[] var2) {
      this.basePath = null;
      this.resourceLocations = null;
      this.rules = null;
      ConnectedParser var3 = new ConnectedParser("RandomEntities");
      this.name = var3.parseName(var1);
      this.basePath = var3.parseBasePath(var1);
      this.resourceLocations = var2;
   }

   public RandomEntityProperties(Properties var1, String var2, ResourceLocation var3) {
      this.basePath = null;
      this.resourceLocations = null;
      this.rules = null;
      ConnectedParser var4 = new ConnectedParser("RandomEntities");
      this.name = var4.parseName(var2);
      this.basePath = var4.parseBasePath(var2);
      this.rules = this.parseRules(var1, var2, var3, var4);
   }

   public boolean isDefault() {
      return this.rules != null ? false : this.resourceLocations == null;
   }

   public ResourceLocation getTextureLocation(ResourceLocation var1, IRandomEntity var2) {
      if (this.rules != null) {
         for (int var3 = 0; var3 < this.rules.length; var3++) {
            RandomEntityRule var4 = this.rules[var3];
            if (var4.matches(var2)) {
               return var4.getTextureLocation(var1, var2.getId());
            }
         }
      }

      if (this.resourceLocations != null) {
         int var5 = var2.getId();
         int var6 = var5 % this.resourceLocations.length;
         return this.resourceLocations[var6];
      } else {
         return var1;
      }
   }
}
