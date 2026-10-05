package net.optifine;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiCommandBlock;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.src.Config;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.ConnectedParser;
import net.optifine.config.EntityClassLocator;
import net.optifine.config.IObjectLocator;
import net.optifine.config.ItemLocator;
import net.optifine.reflect.ReflectorForge;
import net.optifine.util.PropertiesOrdered;
import recovered.unidentified.UnidentifiedClass5108;

public class DynamicLights {
   public GuiCommandBlock field_0006;
   public static Map<Class, Integer> mapEntityLightLevels = new HashMap<>();
   public static double field_0005;
   public static int field_0011;
   public static long timeUpdateMs = 239151120L & 545260032L;
   public static int field_0002;
   public static int field_0014;
   public static int field_0009;
   public static DynamicLightsMap mapDynamicLights = new DynamicLightsMap();
   public UnidentifiedClass5108 field_0015;
   public static int field_0000;
   public static int field_0007;
   public static int field_0008;
   public static double field_0004;
   public static Map<Item, Integer> mapItemLightLevels = new HashMap<>();
   public static boolean initialized;

   public static void entityAdded(Entity var0, RenderGlobal var1) {
   }

   public static double getLightLevel(BlockPos var0) {
      double var1 = 0.0;
      synchronized (mapDynamicLights) {
         List var4 = mapDynamicLights.valueList();
         int var5 = var4.size();

         for (int var6 = 0; var6 < var5; var6++) {
            DynamicLight var7 = (DynamicLight)var4.get(var6);
            int var8 = var7.getLastLightLevel();
            if (var8 > 0) {
               double var9 = var7.method_08357();
               double var11 = var7.method_08354();
               double var13 = var7.method_08349();
               double var15 = var0.getX() - var9;
               double var17 = var0.getY() - var11;
               double var19 = var0.getZ() - var13;
               double var21 = var15 * var15 + var17 * var17 + var19 * var19;
               if (var7.isUnderwater() && !Config.isClearWater()) {
                  var8 = Config.limit(var8 - 2, 0, 15);
                  var21 *= 2.0;
               }

               if (var21 <= 56.25) {
                  double var23 = Math.sqrt(var21);
                  double var25 = 1.0 - var23 / 7.5;
                  double var27 = var25 * var8;
                  if (var27 > var1) {
                     var1 = var27;
                  }
               }
            }
         }
      }

      return Config.limit(var1, 0.0, 15.0);
   }

   public static void initialize() {
      initialized = true;
      mapEntityLightLevels.clear();
      mapItemLightLevels.clear();
      String[] var0 = ReflectorForge.getForgeModIds();

      for (int var1 = 0; var1 < var0.length; var1++) {
         String var2 = var0[var1];

         try {
            ResourceLocation var3 = new ResourceLocation(var2, "optifine/dynamic_lights.properties");
            InputStream var4 = Config.getResourceStream(var3);
            loadModConfiguration(var4, var3.toString(), var2);
         } catch (IOException var5) {
         }
      }

      if (mapEntityLightLevels.size() > 0) {
         Config.dbg("DynamicLights entities: " + mapEntityLightLevels.size());
      }

      if (mapItemLightLevels.size() > 0) {
         Config.dbg("DynamicLights items: " + mapItemLightLevels.size());
      }
   }

   public static int getLightLevel(ItemStack var0) {
      if (var0 == null) {
         return 0;
      } else {
         Item var1 = var0.getItem();
         if (var1 instanceof ItemBlock) {
            ItemBlock var2 = (ItemBlock)var1;
            Block var3 = var2.getBlock();
            if (var3 != null) {
               return var3.getLightValue();
            }
         }

         if (var1 == Items.lava_bucket) {
            return Blocks.lava.getLightValue();
         } else if (var1 == Items.blaze_rod || var1 == Items.blaze_powder) {
            return 10;
         } else if (var1 == Items.glowstone_dust) {
            return 8;
         } else if (var1 == Items.prismarine_crystals) {
            return 8;
         } else if (var1 == Items.magma_cream) {
            return 8;
         } else if (var1 == Items.nether_star) {
            return Blocks.beacon.getLightValue() / 2;
         } else {
            if (!mapItemLightLevels.isEmpty()) {
               Integer var4 = mapItemLightLevels.get(var1);
               if (var4 != null) {
                  return var4;
               }
            }

            return 0;
         }
      }
   }

   public static void method_24213(RenderGlobal var0) {
      WorldClient var1 = var0.getWorld();
      if (var1 != null) {
         for (Entity var3 : var1.getLoadedEntityList()) {
            int var4 = getLightLevel(var3);
            if (var4 > 0) {
               int var5 = var3.F();
               DynamicLight var6 = mapDynamicLights.get(var5);
               if (var6 == null) {
                  var6 = new DynamicLight(var3);
                  mapDynamicLights.put(var5, var6);
               }
            } else {
               int var7 = var3.F();
               DynamicLight var9 = mapDynamicLights.remove(var7);
               if (var9 != null) {
                  var9.updateLitChunks(var0);
               }
            }
         }
      }
   }

   public static void clear() {
      synchronized (mapDynamicLights) {
         mapDynamicLights.clear();
      }
   }

   public static void method_24217(RenderGlobal var0) {
      long var1 = System.currentTimeMillis();
      if (var1 >= timeUpdateMs + (185598258L & -7447212537285696966L)) {
         timeUpdateMs = var1;
         if (!initialized) {
            initialize();
         }

         synchronized (mapDynamicLights) {
            method_24213(var0);
            if (mapDynamicLights.size() > 0) {
               List var4 = mapDynamicLights.valueList();

               for (int var5 = 0; var5 < var4.size(); var5++) {
                  DynamicLight var6 = (DynamicLight)var4.get(var5);
                  var6.method_08356(var0);
               }
            }
         }
      }
   }

   public static ItemStack getItemStack(EntityItem var0) {
      return var0.H().getWatchableObjectItemStack(10);
   }

   public static int getCombinedLight(Entity var0, int var1) {
      double var2 = getLightLevel(var0);
      return getCombinedLight(var2, var1);
   }

   public static void loadModConfiguration(InputStream var0, String var1, String var2) {
      if (var0 != null) {
         try {
            PropertiesOrdered var3 = new PropertiesOrdered();
            var3.load(var0);
            var0.close();
            Config.dbg("DynamicLights: Parsing " + var1);
            ConnectedParser var4 = new ConnectedParser("DynamicLights");
            method_24212(var3.getProperty("entities"), mapEntityLightLevels, new EntityClassLocator(), var4, var1, var2);
            method_24212(var3.getProperty("items"), mapItemLightLevels, new ItemLocator(), var4, var1, var2);
         } catch (IOException var5) {
            Config.warn("DynamicLights: Error reading " + var1);
         }
      }
   }

   public static void entityRemoved(Entity var0, RenderGlobal var1) {
      synchronized (mapDynamicLights) {
         DynamicLight var3 = mapDynamicLights.remove(var0.F());
         if (var3 != null) {
            var3.updateLitChunks(var1);
         }
      }
   }

   public static int getCount() {
      synchronized (mapDynamicLights) {
         return mapDynamicLights.size();
      }
   }

   public static int getLightLevel(Entity var0) {
      if (var0 == Config.getMinecraft().getRenderViewEntity() && !Config.method_04060()) {
         return 0;
      } else {
         if (var0 instanceof EntityPlayer) {
            EntityPlayer var1 = (EntityPlayer)var0;
            if (var1.isSpectator()) {
               return 0;
            }
         }

         if (var0.isBurning()) {
            return 15;
         } else {
            if (!mapEntityLightLevels.isEmpty()) {
               Integer var6 = mapEntityLightLevels.get(var0.getClass());
               if (var6 != null) {
                  return var6;
               }
            }

            if (var0 instanceof EntityFireball) {
               return 15;
            } else if (var0 instanceof EntityTNTPrimed) {
               return 15;
            } else if (var0 instanceof EntityBlaze) {
               EntityBlaze var11 = (EntityBlaze)var0;
               return var11.func_70845_n() ? 15 : 10;
            } else if (var0 instanceof EntityMagmaCube) {
               EntityMagmaCube var10 = (EntityMagmaCube)var0;
               return var10.squishFactor > 0.6 ? 13 : 8;
            } else {
               if (var0 instanceof EntityCreeper) {
                  EntityCreeper var7 = (EntityCreeper)var0;
                  if (var7.getCreeperFlashIntensity(0.0F) > 0.001) {
                     return 15;
                  }
               }

               if (var0 instanceof EntityLivingBase) {
                  EntityLivingBase var9 = (EntityLivingBase)var0;
                  ItemStack var12 = var9.getHeldItem();
                  int var3 = getLightLevel(var12);
                  ItemStack var4 = var9.getEquipmentInSlot(4);
                  int var5 = getLightLevel(var4);
                  return Math.max(var3, var5);
               } else if (var0 instanceof EntityItem) {
                  EntityItem var8 = (EntityItem)var0;
                  ItemStack var2 = getItemStack(var8);
                  return getLightLevel(var2);
               } else {
                  return 0;
               }
            }
         }
      }
   }

   public static void method_24212(String var0, Map var1, IObjectLocator var2, ConnectedParser var3, String var4, String var5) {
      if (var0 != null) {
         String[] var6 = Config.tokenize(var0, " ");

         for (int var7 = 0; var7 < var6.length; var7++) {
            String var8 = var6[var7];
            String[] var9 = Config.tokenize(var8, ":");
            if (var9.length != 2) {
               var3.warn("Invalid entry: " + var8 + ", in:" + var4);
            } else {
               String var10 = var9[0];
               String var11 = var9[1];
               String var12 = var5 + ":" + var10;
               ResourceLocation var13 = new ResourceLocation(var12);
               Object var14 = var2.getObject(var13);
               if (var14 == null) {
                  var3.warn("Object not found: " + var12);
               } else {
                  int var15 = var3.parseInt(var11, -1);
                  if (var15 >= 0 && var15 <= 15) {
                     var1.put(var14, new Integer(var15));
                  } else {
                     var3.warn("Invalid light level: " + var8);
                  }
               }
            }
         }
      }
   }

   public static int getCombinedLight(double var0, int var2) {
      if (var0 > 0.0) {
         int var3 = (int)(var0 * 16.0);
         int var4 = var2 & 0xFF;
         if (var3 > var4) {
            var2 &= -256;
            var2 |= var3;
         }
      }

      return var2;
   }

   public static int getCombinedLight(BlockPos var0, int var1) {
      double var2 = getLightLevel(var0);
      return getCombinedLight(var2, var1);
   }

   public static void removeLights(RenderGlobal var0) {
      synchronized (mapDynamicLights) {
         List var2 = mapDynamicLights.valueList();

         for (int var3 = 0; var3 < var2.size(); var3++) {
            DynamicLight var4 = (DynamicLight)var2.get(var3);
            var4.updateLitChunks(var0);
         }

         mapDynamicLights.clear();
      }
   }
}
