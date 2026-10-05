package net.optifine;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiEnchantment;
import net.minecraft.client.gui.GuiHopper;
import net.minecraft.client.gui.GuiMerchant;
import net.minecraft.client.gui.GuiRepair;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiBeacon;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.gui.inventory.GuiDispenser;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.gui.inventory.GuiScreenHorseInventory;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.stream.BroadcastController$3;
import net.minecraft.entity.Entity;
import net.minecraft.src.Config;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.optifine.override.PlayerControllerOF;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.ResUtils;
import recovered.unidentified.UnidentifiedClass1216;

public class CustomGuis {
   public static CustomGuiProperties[][] guiProperties = (CustomGuiProperties[][])null;
   public static boolean isChristmas = isChristmas();
   public static Minecraft mc = Config.getMinecraft();
   public BroadcastController$3 field_0003;
   public static PlayerControllerOF playerControllerOF = null;

   public static ResourceLocation getTextureLocation(ResourceLocation var0) {
      if (guiProperties == null) {
         return var0;
      } else {
         GuiScreen var1 = mc.currentScreen;
         if (!(var1 instanceof GuiContainer)) {
            return var0;
         } else if (!var0.getResourceDomain().equals("minecraft") || !var0.getResourcePath().startsWith("textures/gui/")) {
            return var0;
         } else if (playerControllerOF == null) {
            return var0;
         } else {
            WorldClient var2 = mc.theWorld;
            if (var2 == null) {
               return var0;
            } else if (var1 instanceof GuiContainerCreative) {
               return getTexturePos(CustomGuiProperties$EnumContainer.CREATIVE, mc.thePlayer.getPosition(), var2, var0, var1);
            } else if (var1 instanceof GuiInventory) {
               return getTexturePos(CustomGuiProperties$EnumContainer.INVENTORY, mc.thePlayer.getPosition(), var2, var0, var1);
            } else {
               BlockPos var3 = playerControllerOF.getLastClickBlockPos();
               if (var3 != null) {
                  if (var1 instanceof GuiRepair) {
                     return getTexturePos(CustomGuiProperties$EnumContainer.ANVIL, var3, var2, var0, var1);
                  }

                  if (var1 instanceof GuiBeacon) {
                     return getTexturePos(CustomGuiProperties$EnumContainer.BEACON, var3, var2, var0, var1);
                  }

                  if (var1 instanceof UnidentifiedClass1216) {
                     return getTexturePos(CustomGuiProperties$EnumContainer.BREWING_STAND, var3, var2, var0, var1);
                  }

                  if (var1 instanceof GuiChest) {
                     return getTexturePos(CustomGuiProperties$EnumContainer.CHEST, var3, var2, var0, var1);
                  }

                  if (var1 instanceof GuiCrafting) {
                     return getTexturePos(CustomGuiProperties$EnumContainer.CRAFTING, var3, var2, var0, var1);
                  }

                  if (var1 instanceof GuiDispenser) {
                     return getTexturePos(CustomGuiProperties$EnumContainer.DISPENSER, var3, var2, var0, var1);
                  }

                  if (var1 instanceof GuiEnchantment) {
                     return getTexturePos(CustomGuiProperties$EnumContainer.ENCHANTMENT, var3, var2, var0, var1);
                  }

                  if (var1 instanceof GuiFurnace) {
                     return getTexturePos(CustomGuiProperties$EnumContainer.FURNACE, var3, var2, var0, var1);
                  }

                  if (var1 instanceof GuiHopper) {
                     return getTexturePos(CustomGuiProperties$EnumContainer.HOPPER, var3, var2, var0, var1);
                  }
               }

               Entity var4 = playerControllerOF.getLastClickEntity();
               if (var4 != null) {
                  if (var1 instanceof GuiScreenHorseInventory) {
                     return getTextureEntity(CustomGuiProperties$EnumContainer.HORSE, var4, var2, var0);
                  }

                  if (var1 instanceof GuiMerchant) {
                     return getTextureEntity(CustomGuiProperties$EnumContainer.VILLAGER, var4, var2, var0);
                  }
               }

               return var0;
            }
         }
      }
   }

   public static boolean isChristmas() {
      Calendar var0 = Calendar.getInstance();
      return var0.get(2) + 1 == 12 && var0.get(5) >= 24 && var0.get(5) <= 26;
   }

   public static void setPlayerControllerOF(PlayerControllerOF var0) {
   }

   public static PlayerControllerOF getPlayerControllerOF() {
      return playerControllerOF;
   }

   public static ResourceLocation getTextureEntity(CustomGuiProperties$EnumContainer var0, Entity var1, IBlockAccess var2, ResourceLocation var3) {
      CustomGuiProperties[] var4 = guiProperties[var0.ordinal()];
      if (var4 == null) {
         return var3;
      } else {
         for (int var5 = 0; var5 < var4.length; var5++) {
            CustomGuiProperties var6 = var4[var5];
            if (var6.matchesEntity(var0, var1, var2)) {
               return var6.getTextureLocation(var3);
            }
         }

         return var3;
      }
   }

   public static void addToList(CustomGuiProperties var0, List<List<CustomGuiProperties>> var1) {
      if (var0.getContainer() == null) {
         warn("Invalid container: " + var0.getContainer());
      } else {
         int var2 = var0.getContainer().ordinal();

         while (var1.size() <= var2) {
            var1.add(null);
         }

         Object var3 = (List)var1.get(var2);
         if (var3 == null) {
            var3 = new ArrayList();
            var1.set(var2, var3);
         }

         var3.add(var0);
      }
   }

   public static void update() {
      guiProperties = (CustomGuiProperties[][])null;
      if (Config.isCustomGuis()) {
         ArrayList var0 = new ArrayList();
         IResourcePack[] var1 = Config.getResourcePacks();

         for (int var2 = var1.length - 1; var2 >= 0; var2--) {
            IResourcePack var3 = var1[var2];
            update(var3, var0);
         }

         guiProperties = propertyListToArray(var0);
      }
   }

   public static ResourceLocation getTexturePos(CustomGuiProperties$EnumContainer var0, BlockPos var1, IBlockAccess var2, ResourceLocation var3, GuiScreen var4) {
      CustomGuiProperties[] var5 = guiProperties[var0.ordinal()];
      if (var5 == null) {
         return var3;
      } else {
         for (int var6 = 0; var6 < var5.length; var6++) {
            CustomGuiProperties var7 = var5[var6];
            if (var7.matchesPos(var0, var1, var2, var4)) {
               return var7.getTextureLocation(var3);
            }
         }

         return var3;
      }
   }

   public static void warn(String var0) {
      Config.warn("[CustomGuis] " + var0);
   }

   public static CustomGuiProperties[][] propertyListToArray(List<List<CustomGuiProperties>> var0) {
      if (var0.isEmpty()) {
         return (CustomGuiProperties[][])null;
      } else {
         CustomGuiProperties[][] var1 = new CustomGuiProperties[CustomGuiProperties$EnumContainer.VALUES.length][];

         for (int var2 = 0; var2 < var1.length; var2++) {
            if (var0.size() > var2) {
               List var3 = (List)var0.get(var2);
               if (var3 != null) {
                  CustomGuiProperties[] var4 = var3.toArray(new CustomGuiProperties[var3.size()]);
                  var1[var2] = var4;
               }
            }
         }

         return var1;
      }
   }

   public static void update(IResourcePack var0, List<List<CustomGuiProperties>> var1) {
      String[] var2 = ResUtils.collectFiles(var0, "optifine/gui/container/", ".properties", (String[])null);
      Arrays.sort(var2);

      for (int var3 = 0; var3 < var2.length; var3++) {
         String var4 = var2[var3];
         Config.dbg("CustomGuis: " + var4);

         try {
            ResourceLocation var5 = new ResourceLocation(var4);
            InputStream var6 = var0.getInputStream(var5);
            if (var6 == null) {
               Config.warn("CustomGuis file not found: " + var4);
            } else {
               PropertiesOrdered var7 = new PropertiesOrdered();
               var7.load(var6);
               var6.close();
               CustomGuiProperties var8 = new CustomGuiProperties(var7, var4);
               if (var8.isValid(var4)) {
                  addToList(var8, var1);
               }
            }
         } catch (FileNotFoundException var9) {
            Config.warn("CustomGuis file not found: " + var4);
         } catch (Exception var10) {
            var10.printStackTrace();
         }
      }
   }
}
