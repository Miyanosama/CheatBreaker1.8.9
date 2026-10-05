package net.optifine;

import io.netty.util.concurrent.SingleThreadEventExecutor$3;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemModelGenerator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World$2;
import net.optifine.config.NbtTagValue;
import net.optifine.render.Blender;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.ShadersRender;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.ResUtils;
import net.optifine.util.StrUtils;
import org.apache.log4j.jdbc.JDBCAppender;
import recovered.unidentified.UnidentifiedClass3676;

public class CustomItems {
   public static String field_0010;
   public static String field_0019;
   public static int[][] field_0009 = new int[0][];
   public static boolean useGlint = true;
   public static ItemModelGenerator itemModelGenerator = new ItemModelGenerator();
   public SingleThreadEventExecutor$3 field_0005;
   public static boolean renderOffHand = false;
   public static String field_0014;
   public static int field_0006;
   public static String field_0021;
   public static CustomItemProperties[][] enchantmentProperties = (CustomItemProperties[][])null;
   public static CustomItemProperties[][] itemProperties = (CustomItemProperties[][])null;
   public static int field_0013;
   public static String field_0008;
   public static String field_0015;
   public World$2 field_0018;
   public static Map mapPotionIds = null;
   public static String field_0007;
   public JDBCAppender field_0012;
   public static int field_0017;
   public static String field_0001;
   public static String field_0000;

   public static CustomItemProperties getCustomItemProperties(ItemStack var0, int var1) {
      if (itemProperties == null) {
         return null;
      } else if (var0 == null) {
         return null;
      } else {
         Item var2 = var0.getItem();
         int var3 = Item.getIdFromItem(var2);
         if (var3 >= 0 && var3 < itemProperties.length) {
            CustomItemProperties[] var4 = itemProperties[var3];
            if (var4 != null) {
               for (int var5 = 0; var5 < var4.length; var5++) {
                  CustomItemProperties var6 = var4[var5];
                  if (var6.type == var1 && matchesProperties(var6, var0, (int[][])null)) {
                     return var6;
                  }
               }
            }
         }

         return null;
      }
   }

   public static void addToList(CustomItemProperties var0, List var1, int var2) {
      while (var2 >= var1.size()) {
         var1.add(null);
      }

      Object var3 = (List)var1.get(var2);
      if (var3 == null) {
         var3 = new ArrayList();
         var1.set(var2, var3);
      }

      var3.add(var0);
   }

   public static boolean isUseGlint() {
      return useGlint;
   }

   public static IBakedModel getCustomItemModel(ItemStack var0, IBakedModel var1, ResourceLocation var2, boolean var3) {
      if (!var3 && var1.isGui3d()) {
         return var1;
      } else if (itemProperties == null) {
         return var1;
      } else {
         CustomItemProperties var4 = getCustomItemProperties(var0, 1);
         if (var4 == null) {
            return var1;
         } else {
            IBakedModel var5 = var4.getBakedModel(var2, var3);
            return var5 != null ? var5 : var1;
         }
      }
   }

   public static List<CustomItemProperties> getAllProperties() {
      ArrayList var0 = new ArrayList();
      addAll(itemProperties, var0);
      addAll(enchantmentProperties, var0);
      return var0;
   }

   public static Properties makePotionProperties(String var0, String var1, int var2, String var3) {
      if (StrUtils.endsWith(var0, new String[]{"_n", "_s"})) {
         return null;
      } else if (var0.equals("empty") && var1.equals("normal")) {
         var2 = Item.getIdFromItem(Items.glass_bottle);
         PropertiesOrdered var9 = new PropertiesOrdered();
         var9.put("type", "item");
         var9.put("items", "" + var2);
         return var9;
      } else {
         int[] var4 = (int[])getMapPotionIds().get(var0);
         if (var4 == null) {
            Config.warn("Potion not found for image: " + var3);
            return null;
         } else {
            StringBuffer var5 = new StringBuffer();

            for (int var6 = 0; var6 < var4.length; var6++) {
               int var7 = var4[var6];
               if (var1.equals("splash")) {
                  var7 |= 16384;
               }

               if (var6 > 0) {
                  var5.append(" ");
               }

               var5.append(var7);
            }

            int var10 = 16447;
            if (var0.equals("water") || var0.equals("mundane")) {
               var10 |= 64;
            }

            PropertiesOrdered var11 = new PropertiesOrdered();
            var11.put("type", "item");
            var11.put("items", "" + var2);
            var11.put("damage", "" + var5.toString());
            var11.put("damageMask", "" + var10);
            if (var1.equals("splash")) {
               var11.put("texture.potion_bottle_splash", var0);
            } else {
               var11.put("texture.potion_bottle_drinkable", var0);
            }

            return var11;
         }
      }
   }

   public static List makePropertyList(CustomItemProperties[][] var0) {
      ArrayList var1 = new ArrayList();
      if (var0 != null) {
         for (int var2 = 0; var2 < var0.length; var2++) {
            CustomItemProperties[] var3 = var0[var2];
            ArrayList var4 = null;
            if (var3 != null) {
               var4 = new ArrayList<>(Arrays.asList(var3));
            }

            var1.add(var4);
         }
      }

      return var1;
   }

   public static void readCitProperties(String var0) {
      try {
         ResourceLocation var1 = new ResourceLocation(var0);
         InputStream var2 = Config.getResourceStream(var1);
         if (var2 == null) {
            return;
         }

         Config.dbg("CustomItems: Loading " + var0);
         PropertiesOrdered var3 = new PropertiesOrdered();
         var3.load(var2);
         var2.close();
         useGlint = Config.parseBoolean(var3.getProperty("useGlint"), true);
      } catch (FileNotFoundException var4) {
         return;
      } catch (IOException var5) {
         var5.printStackTrace();
      }
   }

   public static void method_05228() {
      itemProperties = (CustomItemProperties[][])null;
      enchantmentProperties = (CustomItemProperties[][])null;
      useGlint = true;
      if (Config.isCustomItems()) {
         readCitProperties("mcpatcher/cit.properties");
         IResourcePack[] var0 = Config.getResourcePacks();

         for (int var1 = var0.length - 1; var1 >= 0; var1--) {
            IResourcePack var2 = var0[var1];
            update(var2);
         }

         update(Config.getDefaultResourcePack());
         if (itemProperties.length <= 0) {
            itemProperties = (CustomItemProperties[][])null;
         }

         if (enchantmentProperties.length <= 0) {
            enchantmentProperties = (CustomItemProperties[][])null;
         }
      }
   }

   public static Map makePotionImageProperties(IResourcePack var0, String var1, int var2) {
      HashMap var3 = new HashMap();
      String var4 = var1 + "/";
      String[] var5 = new String[]{"mcpatcher/cit/potion/" + var4, "mcpatcher/cit/Potion/" + var4};
      String[] var6 = new String[]{".png"};
      String[] var7 = ResUtils.collectFiles(var0, var5, var6);

      for (int var8 = 0; var8 < var7.length; var8++) {
         String var9 = var7[var8];
         String var10 = StrUtils.removePrefixSuffix(var9, var5, var6);
         Properties var11 = makePotionProperties(var10, var1, var2, var9);
         if (var11 != null) {
            String var12 = StrUtils.removeSuffix(var9, var6) + ".properties";
            CustomItemProperties var13 = new CustomItemProperties(var11, var12);
            var3.put(var12, var13);
         }
      }

      return var3;
   }

   public static Comparator getPropertiesComparator() {
      return new CustomItems$1();
   }

   public static CustomItemProperties[][] propertyListToArray(List var0) {
      CustomItemProperties[][] var1 = new CustomItemProperties[var0.size()][];

      for (int var2 = 0; var2 < var0.size(); var2++) {
         List var3 = (List)var0.get(var2);
         if (var3 != null) {
            CustomItemProperties[] var4 = var3.toArray(new CustomItemProperties[var3.size()]);
            Arrays.sort(var4, new UnidentifiedClass3676());
            var1[var2] = var4;
         }
      }

      return var1;
   }

   public static Map getMapPotionIds() {
      if (mapPotionIds == null) {
         mapPotionIds = new LinkedHashMap();
         mapPotionIds.put("water", getPotionId(0, 0));
         mapPotionIds.put("awkward", getPotionId(0, 1));
         mapPotionIds.put("thick", getPotionId(0, 2));
         mapPotionIds.put("potent", getPotionId(0, 3));
         mapPotionIds.put("regeneration", getPotionIds(1));
         mapPotionIds.put("movespeed", getPotionIds(2));
         mapPotionIds.put("fireresistance", getPotionIds(3));
         mapPotionIds.put("poison", getPotionIds(4));
         mapPotionIds.put("heal", getPotionIds(5));
         mapPotionIds.put("nightvision", getPotionIds(6));
         mapPotionIds.put("clear", getPotionId(7, 0));
         mapPotionIds.put("bungling", getPotionId(7, 1));
         mapPotionIds.put("charming", getPotionId(7, 2));
         mapPotionIds.put("rank", getPotionId(7, 3));
         mapPotionIds.put("weakness", getPotionIds(8));
         mapPotionIds.put("damageboost", getPotionIds(9));
         mapPotionIds.put("moveslowdown", getPotionIds(10));
         mapPotionIds.put("leaping", getPotionIds(11));
         mapPotionIds.put("harm", getPotionIds(12));
         mapPotionIds.put("waterbreathing", getPotionIds(13));
         mapPotionIds.put("invisibility", getPotionIds(14));
         mapPotionIds.put("thin", getPotionId(15, 0));
         mapPotionIds.put("debonair", getPotionId(15, 1));
         mapPotionIds.put("sparkling", getPotionId(15, 2));
         mapPotionIds.put("stinky", getPotionId(15, 3));
         mapPotionIds.put("mundane", getPotionId(0, 4));
         mapPotionIds.put("speed", mapPotionIds.get("movespeed"));
         mapPotionIds.put("fire_resistance", mapPotionIds.get("fireresistance"));
         mapPotionIds.put("instant_health", mapPotionIds.get("heal"));
         mapPotionIds.put("night_vision", mapPotionIds.get("nightvision"));
         mapPotionIds.put("strength", mapPotionIds.get("damageboost"));
         mapPotionIds.put("slowness", mapPotionIds.get("moveslowdown"));
         mapPotionIds.put("instant_damage", mapPotionIds.get("harm"));
         mapPotionIds.put("water_breathing", mapPotionIds.get("waterbreathing"));
      }

      return mapPotionIds;
   }

   public static int[] getPotionId(int var0, int var1) {
      return new int[]{var0 + var1 * 16};
   }

   public static void method_05233(CustomItemProperties var0, List var1) {
      if (var0.items != null) {
         for (int var2 = 0; var2 < var0.items.length; var2++) {
            int var3 = var0.items[var2];
            if (var3 <= 0) {
               Config.warn("Invalid item ID: " + var3);
            } else {
               addToList(var0, var1, var3);
            }
         }
      }
   }

   public static int getPotionNameDamage(String var0) {
      String var1 = "potion." + var0;
      Potion[] var2 = Potion.potionTypes;

      for (int var3 = 0; var3 < var2.length; var3++) {
         Potion var4 = var2[var3];
         if (var4 != null) {
            String var5 = var4.getName();
            if (var1.equals(var5)) {
               return var4.getId();
            }
         }
      }

      return -1;
   }

   public static boolean renderCustomEffect(RenderItem var0, ItemStack var1, IBakedModel var2) {
      if (enchantmentProperties == null) {
         return false;
      } else if (var1 == null) {
         return false;
      } else {
         int[][] var3 = getEnchantmentIdLevels(var1);
         if (var3.length <= 0) {
            return false;
         } else {
            HashSet var4 = null;
            boolean var5 = false;
            TextureManager var6 = Config.getTextureManager();

            for (int var7 = 0; var7 < var3.length; var7++) {
               int var8 = var3[var7][0];
               if (var8 >= 0 && var8 < enchantmentProperties.length) {
                  CustomItemProperties[] var9 = enchantmentProperties[var8];
                  if (var9 != null) {
                     for (int var10 = 0; var10 < var9.length; var10++) {
                        CustomItemProperties var11 = var9[var10];
                        if (var4 == null) {
                           var4 = new HashSet();
                        }

                        if (var4.add(var8) && matchesProperties(var11, var1, var3) && var11.textureLocation != null) {
                           var6.bindTexture(var11.textureLocation);
                           float var12 = var11.getTextureWidth(var6);
                           if (!var5) {
                              var5 = true;
                              GlStateManager.depthMask(false);
                              GlStateManager.depthFunc(514);
                              GlStateManager.disableLighting();
                              GlStateManager.matrixMode(5890);
                           }

                           Blender.setupBlend(var11.blend, 1.0F);
                           GlStateManager.pushMatrix();
                           GlStateManager.scale(var12 / 2.0F, var12 / 2.0F, var12 / 2.0F);
                           float var13 = var11.speed * (float)(Minecraft.getSystemTime() % (7424982173181415419L & -7424982175319024708L)) / 3000.0F / 8.0F;
                           GlStateManager.translate(var13, 0.0F, 0.0F);
                           GlStateManager.rotate(var11.rotation, 0.0F, 0.0F, 1.0F);
                           var0.renderModel(var2, -1);
                           GlStateManager.popMatrix();
                        }
                     }
                  }
               }
            }

            if (var5) {
               GlStateManager.enableAlpha();
               GlStateManager.enableBlend();
               GlStateManager.blendFunc(770, 771);
               GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
               GlStateManager.matrixMode(5888);
               GlStateManager.enableLighting();
               GlStateManager.depthFunc(515);
               GlStateManager.depthMask(true);
               var6.bindTexture(TextureMap.locationBlocksTexture);
            }

            return var5;
         }
      }
   }

   public static void updateIcons(TextureMap var0) {
      for (CustomItemProperties var2 : getAllProperties()) {
         var2.updateIcons(var0);
      }
   }

   public static void update(IResourcePack var0) {
      String[] var1 = ResUtils.collectFiles(var0, "mcpatcher/cit/", ".properties", (String[])null);
      Map var2 = makeAutoImageProperties(var0);
      if (var2.size() > 0) {
         Set var3 = var2.keySet();
         String[] var4 = var3.toArray(new String[var3.size()]);
         var1 = (String[])Config.addObjectsToArray(var1, var4);
      }

      Arrays.sort(var1);
      List var13 = makePropertyList(itemProperties);
      List var14 = makePropertyList(enchantmentProperties);

      for (int var5 = 0; var5 < var1.length; var5++) {
         String var6 = var1[var5];
         Config.dbg("CustomItems: " + var6);

         try {
            CustomItemProperties var7 = null;
            if (var2.containsKey(var6)) {
               var7 = (CustomItemProperties)var2.get(var6);
            }

            if (var7 == null) {
               ResourceLocation var8 = new ResourceLocation(var6);
               InputStream var9 = var0.getInputStream(var8);
               if (var9 == null) {
                  Config.warn("CustomItems file not found: " + var6);
                  continue;
               }

               PropertiesOrdered var10 = new PropertiesOrdered();
               var10.load(var9);
               var9.close();
               var7 = new CustomItemProperties(var10, var6);
            }

            if (var7.isValid(var6)) {
               method_05233(var7, var13);
               method_05252(var7, var14);
            }
         } catch (FileNotFoundException var11) {
            Config.warn("CustomItems file not found: " + var6);
         } catch (Exception var12) {
            var12.printStackTrace();
         }
      }

      itemProperties = propertyListToArray(var13);
      enchantmentProperties = propertyListToArray(var14);
      Comparator var15 = getPropertiesComparator();

      for (int var16 = 0; var16 < itemProperties.length; var16++) {
         CustomItemProperties[] var18 = itemProperties[var16];
         if (var18 != null) {
            Arrays.sort(var18, var15);
         }
      }

      for (int var17 = 0; var17 < enchantmentProperties.length; var17++) {
         CustomItemProperties[] var19 = enchantmentProperties[var17];
         if (var19 != null) {
            Arrays.sort(var19, var15);
         }
      }
   }

   public static boolean matchesProperties(CustomItemProperties var0, ItemStack var1, int[][] var2) {
      Item var3 = var1.getItem();
      if (var0.damage != null) {
         int var4 = var1.getItemDamage();
         if (var0.damageMask != 0) {
            var4 &= var0.damageMask;
         }

         if (var0.damagePercent) {
            int var5 = var3.getMaxDamage();
            var4 = (int)((double)(var4 * 100) / var5);
         }

         if (!var0.damage.isInRange(var4)) {
            return false;
         }
      }

      if (var0.stackSize != null && !var0.stackSize.isInRange(var1.stackSize)) {
         return false;
      } else {
         int[][] var8 = var2;
         if (var0.enchantmentIds != null) {
            if (var2 == null) {
               var8 = getEnchantmentIdLevels(var1);
            }

            boolean var9 = false;

            for (int var6 = 0; var6 < var8.length; var6++) {
               int var7 = var8[var6][0];
               if (var0.enchantmentIds.isInRange(var7)) {
                  var9 = true;
                  break;
               }
            }

            if (!var9) {
               return false;
            }
         }

         if (var0.enchantmentLevels != null) {
            if (var8 == null) {
               var8 = getEnchantmentIdLevels(var1);
            }

            boolean var10 = false;

            for (int var12 = 0; var12 < var8.length; var12++) {
               int var14 = var8[var12][1];
               if (var0.enchantmentLevels.isInRange(var14)) {
                  var10 = true;
                  break;
               }
            }

            if (!var10) {
               return false;
            }
         }

         if (var0.nbtTagValues != null) {
            NBTTagCompound var11 = var1.getTagCompound();

            for (int var13 = 0; var13 < var0.nbtTagValues.length; var13++) {
               NbtTagValue var15 = var0.nbtTagValues[var13];
               if (!var15.matches(var11)) {
                  return false;
               }
            }
         }

         if (var0.hand != 0) {
            if (var0.hand == 1 && renderOffHand) {
               return false;
            }

            if (var0.hand == 2 && !renderOffHand) {
               return false;
            }
         }

         return true;
      }
   }

   public static ResourceLocation getCustomArmorLocation(ItemStack var0, int var1, String var2) {
      CustomItemProperties var3 = getCustomItemProperties(var0, 3);
      if (var3 == null) {
         return null;
      } else if (var3.mapTextureLocations == null) {
         return var3.textureLocation;
      } else {
         Item var4 = var0.getItem();
         if (!(var4 instanceof ItemArmor)) {
            return null;
         } else {
            ItemArmor var5 = (ItemArmor)var4;
            String var6 = var5.getArmorMaterial().getName();
            StringBuffer var7 = new StringBuffer();
            var7.append("texture.");
            var7.append(var6);
            var7.append("_layer_");
            var7.append(var1);
            if (var2 != null) {
               var7.append("_");
               var7.append(var2);
            }

            String var8 = var7.toString();
            ResourceLocation var9 = (ResourceLocation)var3.mapTextureLocations.get(var8);
            return var9 == null ? var3.textureLocation : var9;
         }
      }
   }

   public static void addAll(CustomItemProperties[][] var0, List<CustomItemProperties> var1) {
      if (var0 != null) {
         for (int var2 = 0; var2 < var0.length; var2++) {
            CustomItemProperties[] var3 = var0[var2];
            if (var3 != null) {
               for (int var4 = 0; var4 < var3.length; var4++) {
                  CustomItemProperties var5 = var3[var4];
                  if (var5 != null) {
                     var1.add(var5);
                  }
               }
            }
         }
      }
   }

   public static void method_05227() {
      for (CustomItemProperties var1 : getAllProperties()) {
         if (var1.type == 1) {
            TextureMap var2 = Minecraft.getMinecraft().getTextureMapBlocks();
            var1.updateModelTexture(var2, itemModelGenerator);
            var1.updateModelsFull();
         }
      }
   }

   public static void loadModels(ModelBakery var0) {
      for (CustomItemProperties var2 : getAllProperties()) {
         var2.loadModels(var0);
      }
   }

   public static void method_05252(CustomItemProperties var0, List var1) {
      if (var0.type == 2 && var0.enchantmentIds != null) {
         for (int var2 = 0; var2 < 256; var2++) {
            if (var0.enchantmentIds.isInRange(var2)) {
               addToList(var0, var1, var2);
            }
         }
      }
   }

   public static Map makeAutoImageProperties(IResourcePack var0) {
      HashMap var1 = new HashMap();
      var1.putAll(makePotionImageProperties(var0, "normal", Item.getIdFromItem(Items.potionitem)));
      var1.putAll(makePotionImageProperties(var0, "splash", Item.getIdFromItem(Items.potionitem)));
      var1.putAll(makePotionImageProperties(var0, "linger", Item.getIdFromItem(Items.potionitem)));
      return var1;
   }

   public static boolean renderCustomArmorEffect(
      EntityLivingBase var0, ItemStack var1, ModelBase var2, float var3, float var4, float var5, float var6, float var7, float var8, float var9
   ) {
      if (enchantmentProperties == null) {
         return false;
      } else if (Config.isShaders() && Shaders.isShadowPass) {
         return false;
      } else if (var1 == null) {
         return false;
      } else {
         int[][] var10 = getEnchantmentIdLevels(var1);
         if (var10.length <= 0) {
            return false;
         } else {
            HashSet var11 = null;
            boolean var12 = false;
            TextureManager var13 = Config.getTextureManager();

            for (int var14 = 0; var14 < var10.length; var14++) {
               int var15 = var10[var14][0];
               if (var15 >= 0 && var15 < enchantmentProperties.length) {
                  CustomItemProperties[] var16 = enchantmentProperties[var15];
                  if (var16 != null) {
                     for (int var17 = 0; var17 < var16.length; var17++) {
                        CustomItemProperties var18 = var16[var17];
                        if (var11 == null) {
                           var11 = new HashSet();
                        }

                        if (var11.add(var15) && matchesProperties(var18, var1, var10) && var18.textureLocation != null) {
                           var13.bindTexture(var18.textureLocation);
                           float var19 = var18.getTextureWidth(var13);
                           if (!var12) {
                              var12 = true;
                              if (Config.isShaders()) {
                                 ShadersRender.method_06622();
                              }

                              GlStateManager.enableBlend();
                              GlStateManager.depthFunc(514);
                              GlStateManager.depthMask(false);
                           }

                           Blender.setupBlend(var18.blend, 1.0F);
                           GlStateManager.disableLighting();
                           GlStateManager.matrixMode(5890);
                           GlStateManager.loadIdentity();
                           GlStateManager.rotate(var18.rotation, 0.0F, 0.0F, 1.0F);
                           float var20 = var19 / 8.0F;
                           GlStateManager.scale(var20, var20 / 2.0F, var20);
                           float var21 = var18.speed * (float)(Minecraft.getSystemTime() % (671099832L & 293673917L)) / 3000.0F / 8.0F;
                           GlStateManager.translate(0.0F, var21, 0.0F);
                           GlStateManager.matrixMode(5888);
                           var2.render(var0, var3, var4, var6, var7, var8, var9);
                        }
                     }
                  }
               }
            }

            if (var12) {
               GlStateManager.enableAlpha();
               GlStateManager.enableBlend();
               GlStateManager.blendFunc(770, 771);
               GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
               GlStateManager.matrixMode(5890);
               GlStateManager.loadIdentity();
               GlStateManager.matrixMode(5888);
               GlStateManager.enableLighting();
               GlStateManager.depthMask(true);
               GlStateManager.depthFunc(515);
               GlStateManager.disableBlend();
               if (Config.isShaders()) {
                  ShadersRender.renderEnchantedGlintEnd();
               }
            }

            return var12;
         }
      }
   }

   public static int[] getPotionIds(int var0) {
      return new int[]{var0, var0 + 16, var0 + 32, var0 + 48};
   }

   public static boolean bindCustomArmorTexture(ItemStack var0, int var1, String var2) {
      if (itemProperties == null) {
         return false;
      } else {
         ResourceLocation var3 = getCustomArmorLocation(var0, var1, var2);
         if (var3 == null) {
            return false;
         } else {
            Config.getTextureManager().bindTexture(var3);
            return true;
         }
      }
   }

   public static int[][] getEnchantmentIdLevels(ItemStack var0) {
      Item var1 = var0.getItem();
      NBTTagList var2 = var1 == Items.enchanted_book ? Items.enchanted_book.getEnchantments(var0) : var0.getEnchantmentTagList();
      if (var2 != null && var2.tagCount() > 0) {
         int[][] var3 = new int[var2.tagCount()][2];

         for (int var4 = 0; var4 < var2.tagCount(); var4++) {
            NBTTagCompound var5 = var2.getCompoundTagAt(var4);
            short var6 = var5.getShort("id");
            short var7 = var5.getShort("lvl");
            var3[var4][0] = var6;
            var3[var4][1] = var7;
         }

         return var3;
      } else {
         return field_0009;
      }
   }
}
