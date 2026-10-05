package net.optifine;

import com.cheatbreaker.client.CheatBreaker;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import javax.imageio.ImageIO;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.BlockStem;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.src.Config;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.optifine.config.ConnectedParser;
import net.optifine.config.MatchBlock;
import net.optifine.render.RenderEnv;
import net.optifine.util.EntityUtils;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.ResUtils;
import net.optifine.util.StrUtils;
import net.optifine.util.TextureUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import net.optifine.CustomColors$2;
import net.optifine.CustomColors$5;
import net.optifine.CustomColors$1;
import net.optifine.CustomColors$4;
import net.optifine.CustomColors$3;

public class CustomColors {
   public static String paletteFormatDefault = "vanilla";
   public static CustomColormap recoveredField2603 = null;
   public static CustomColormap foliagePineColors = null;
   public static CustomColormap foliageBirchColors = null;
   public static CustomColormap swampFoliageColors = null;
   public static CustomColormap swampGrassColors = null;
   public static CustomColormap[] colorsBlockColormaps = null;
   public static CustomColormap[][] blockColormaps = (CustomColormap[][])null;
   public static CustomColormap skyColors = null;
   public static CustomColorFader skyColorFader = new CustomColorFader();
   public static CustomColormap fogColors = null;
   public static CustomColorFader fogColorFader = new CustomColorFader();
   public static CustomColormap recoveredField2605 = null;
   public static CustomColorFader recoveredField2595 = new CustomColorFader();
   public static CustomColormap recoveredField2604 = null;
   public static CustomColorFader recoveredField2607 = new CustomColorFader();
   public static LightMapPack[] lightMapPacks = null;
   public static int lightmapMinDimensionId = 0;
   public static CustomColormap redstoneColors = null;
   public static CustomColormap xpOrbColors = null;
   public static int xpOrbTime = -1;
   public static CustomColormap durabilityColors = null;
   public static CustomColormap stemColors = null;
   public static CustomColormap stemMelonColors = null;
   public static CustomColormap stemPumpkinColors = null;
   public static CustomColormap recoveredField2606 = null;
   public static boolean useDefaultGrassFoliageColors = true;
   public static int particleWaterColor = -1;
   public static int particlePortalColor = -1;
   public static int lilyPadColor = -1;
   public static int expBarTextColor = -1;
   public static int bossTextColor = -1;
   public static int signTextColor = -1;
   public static Vec3 fogColorNether = null;
   public static Vec3 fogColorEnd = null;
   public static Vec3 skyColorEnd = null;
   public static int[] spawnEggPrimaryColors = null;
   public static int[] spawnEggSecondaryColors = null;
   public static float[][] wolfCollarColors = (float[][])null;
   public static float[][] sheepColors = (float[][])null;
   public static int[] textColors = null;
   public static int[] mapColorsOriginal = null;
   public static int[] potionColors = null;
   public static IBlockState recoveredField2596 = Blocks.dirt.getDefaultState();
   public static IBlockState recoveredField2600 = Blocks.water.getDefaultState();
   public static Random random = new Random();
   public static CustomColors.IColorizer recoveredField2597 = new CustomColors$1();
   public static CustomColors.IColorizer recoveredField2599 = new CustomColors$2();
   public static CustomColors.IColorizer recoveredField2601 = new CustomColors$3();
   public static CustomColors.IColorizer recoveredField2598 = new CustomColors$4();
   public static CustomColors.IColorizer recoveredField2602 = new CustomColors$5();

   public static CustomColormap[][] blockListToArray(List var0) {
      CustomColormap[][] var1 = new CustomColormap[var0.size()][];

      for (int var2 = 0; var2 < var0.size(); var2++) {
         List var3 = (List)var0.get(var2);
         if (var3 != null) {
            CustomColormap[] var4 = (net.optifine.CustomColormap[])var3.toArray(new CustomColormap[var3.size()]);
            var1[var2] = var4;
         }
      }

      return var1;
   }

   public static void readColorProperties(String var0) {
      try {
         ResourceLocation var1 = new ResourceLocation(var0);
         InputStream var2 = Config.getResourceStream(var1);
         if (var2 == null) {
            return;
         }

         dbg("Loading " + var0);
         PropertiesOrdered var3 = new PropertiesOrdered();
         var3.load(var2);
         var2.close();
         particleWaterColor = readColor(var3, new String[]{"particle.water", "drop.water"});
         particlePortalColor = readColor(var3, "particle.portal");
         lilyPadColor = readColor(var3, "lilypad");
         expBarTextColor = readColor(var3, "text.xpbar");
         bossTextColor = readColor(var3, "text.boss");
         signTextColor = readColor(var3, "text.sign");
         fogColorNether = readColorVec3(var3, "fog.nether");
         fogColorEnd = readColorVec3(var3, "fog.end");
         skyColorEnd = readColorVec3(var3, "sky.end");
         colorsBlockColormaps = readCustomColormaps(var3, var0);
         spawnEggPrimaryColors = readSpawnEggColors(var3, var0, "egg.shell.", "Spawn egg shell");
         spawnEggSecondaryColors = readSpawnEggColors(var3, var0, "egg.spots.", "Spawn egg spot");
         wolfCollarColors = readDyeColors(var3, var0, "collar.", "Wolf collar");
         sheepColors = readDyeColors(var3, var0, "sheep.", "Sheep");
         textColors = readTextColors(var3, var0, "text.code.", "Text");
         int[] var4 = readMapColors(var3, var0, "map.", "Map");
         if (var4 != null) {
            if (mapColorsOriginal == null) {
               mapColorsOriginal = getMapColors();
            }

            setMapColors(var4);
         }

         potionColors = readPotionColors(var3, var0, "potion.", "Potion");
         xpOrbTime = Config.parseInt(var3.getProperty("xporb.time"), -1);
      } catch (FileNotFoundException var5) {
         return;
      } catch (IOException var6) {
         var6.printStackTrace();
      }
   }

   public static CustomColormap getCustomColors(String var0, int var1, int var2) {
      try {
         ResourceLocation var3 = new ResourceLocation(var0);
         if (!Config.hasResource(var3)) {
            return null;
         } else {
            dbg("Colormap " + var0);
            PropertiesOrdered var4 = new PropertiesOrdered();
            String var5 = StrUtils.replaceSuffix(var0, ".png", ".properties");
            ResourceLocation var6 = new ResourceLocation(var5);
            if (Config.hasResource(var6)) {
               InputStream var7 = Config.getResourceStream(var6);
               var4.load(var7);
               var7.close();
               dbg("Colormap properties: " + var5);
            } else {
               var4.put("format", paletteFormatDefault);
               var4.put("source", var0);
               var5 = var0;
            }

            CustomColormap var9 = new CustomColormap(var4, var5, var1, var2, paletteFormatDefault);
            return !var9.isValid(var5) ? null : var9;
         }
      } catch (Exception var8) {
         var8.printStackTrace();
         return null;
      }
   }

   public static void method_29823(EntityFX var0) {
      if (particlePortalColor >= 0) {
         int var1 = particlePortalColor;
         int var2 = var1 >> 16 & 0xFF;
         int var3 = var1 >> 8 & 0xFF;
         int var4 = var1 & 0xFF;
         float var5 = var2 / 255.0F;
         float var6 = var3 / 255.0F;
         float var7 = var4 / 255.0F;
         var0.b(var5, var6, var7);
      }
   }

   public static int getStemColorMultiplier(Block var0, IBlockAccess var1, BlockPos var2, RenderEnv var3) {
      CustomColormap var4 = stemColors;
      if (var0 == Blocks.pumpkin_stem && stemPumpkinColors != null) {
         var4 = stemPumpkinColors;
      }

      if (var0 == Blocks.melon_stem && stemMelonColors != null) {
         var4 = stemMelonColors;
      }

      if (var4 == null) {
         return -1;
      } else {
         int var5 = var3.getMetadata();
         return var4.getColor(var5);
      }
   }

   public static Pair<LightMapPack[], Integer> parseLightMapPacks() {
      String var0 = "mcpatcher/lightmap/world";
      String var1 = ".png";
      String[] var2 = ResUtils.collectFiles(var0, var1);
      HashMap var3 = new HashMap();

      for (int var4 = 0; var4 < var2.length; var4++) {
         String var5 = var2[var4];
         String var6 = StrUtils.removePrefixSuffix(var5, var0, var1);
         int var7 = Config.parseInt(var6, Integer.MIN_VALUE);
         if (var7 == Integer.MIN_VALUE) {
            warn("Invalid dimension ID: " + var6 + ", path: " + var5);
         } else {
            var3.put(var7, var5);
         }
      }

      Set var21 = var3.keySet();
      Integer[] var22 = (java.lang.Integer[])var21.toArray(new Integer[var21.size()]);
      Arrays.sort(var22);
      if (var22.length <= 0) {
         return new ImmutablePair<>(null, 0);
      } else {
         int var23 = var22[0];
         int var24 = var22[var22.length - 1];
         int var8 = var24 - var23 + 1;
         CustomColormap[] var9 = new CustomColormap[var8];

         for (int var10 = 0; var10 < var22.length; var10++) {
            Integer var11 = var22[var10];
            String var12 = (String)var3.get(var11);
            CustomColormap var13 = getCustomColors(var12, -1, -1);
            if (var13 != null) {
               if (var13.getWidth() < 16) {
                  warn("Invalid lightmap width: " + var13.getWidth() + ", path: " + var12);
               } else {
                  int var14 = var11 - var23;
                  var9[var14] = var13;
               }
            }
         }

         LightMapPack[] var25 = new LightMapPack[var9.length];

         for (int var26 = 0; var26 < var9.length; var26++) {
            CustomColormap var27 = var9[var26];
            if (var27 != null) {
               String var28 = var27.recoveredField1455;
               String var29 = var27.recoveredField1457;
               CustomColormap var15 = getCustomColors(var29 + "/" + var28 + "_rain.png", -1, -1);
               CustomColormap var16 = getCustomColors(var29 + "/" + var28 + "_thunder.png", -1, -1);
               LightMap var17 = new LightMap(var27);
               LightMap var18 = var15 != null ? new LightMap(var15) : null;
               LightMap var19 = var16 != null ? new LightMap(var16) : null;
               LightMapPack var20 = new LightMapPack(var17, var18, var19);
               var25[var26] = var20;
            }
         }

         return new ImmutablePair<>(var25, var23);
      }
   }

   public static void dbg(String var0) {
      Config.dbg("CustomColors: " + var0);
   }

   public static int[] readSpawnEggColors(Properties var0, String var1, String var2, String var3) {
      ArrayList var4 = new ArrayList();
      Set var5 = var0.keySet();
      int var6 = 0;

      for (Object var8 : var5) {
         String var9 = (String)var8;
         String var10 = var0.getProperty(var9);
         if (var9.startsWith(var2)) {
            String var11 = StrUtils.removePrefix(var9, var2);
            int var12 = EntityUtils.getEntityIdByName(var11);
            if (var12 < 0) {
               warn("Invalid spawn egg name: " + var9);
            } else {
               int var13 = parseColor(var10);
               if (var13 < 0) {
                  warn("Invalid spawn egg color: " + var9 + " = " + var10);
               } else {
                  while (var4.size() <= var12) {
                     var4.add(-1);
                  }

                  var4.set(var12, var13);
                  var6++;
               }
            }
         }
      }

      if (var6 <= 0) {
         return null;
      } else {
         dbg(var3 + " colors: " + var6);
         int[] var14 = new int[var4.size()];

         for (int var15 = 0; var15 < var14.length; var15++) {
            var14[var15] = (Integer)var4.get(var15);
         }

         return var14;
      }
   }

   public static int getXpOrbColor(float var0) {
      if (xpOrbColors == null) {
         return -1;
      } else {
         int var1 = (int)Math.round((MathHelper.sin(var0) + 1.0F) * (xpOrbColors.getLength() - 1) / 2.0);
         return xpOrbColors.getColor(var1);
      }
   }

   public static void addToBlockList(CustomColormap var0, List var1) {
      int[] var2 = var0.getMatchBlockIds();
      if (var2 != null && var2.length > 0) {
         for (int var3 = 0; var3 < var2.length; var3++) {
            int var4 = var2[var3];
            if (var4 < 0) {
               warn("Invalid block ID: " + var4);
            } else {
               addToList(var0, var1, var4);
            }
         }
      } else {
         warn("No match blocks: " + Config.arrayToString(var2));
      }
   }

   // $VF: synthetic method
   public static CustomColormap access$000() {
      return swampGrassColors;
   }

   public static Vec3 getSkyColor(Vec3 var0, IBlockAccess var1, double var2, double var4, double var6) {
      if (skyColors == null) {
         return var0;
      } else {
         int var8 = skyColors.getColorSmooth(var1, var2, var4, var6, 3);
         int var9 = var8 >> 16 & 0xFF;
         int var10 = var8 >> 8 & 0xFF;
         int var11 = var8 & 0xFF;
         float var12 = var9 / 255.0F;
         float var13 = var10 / 255.0F;
         float var14 = var11 / 255.0F;
         float var15 = (float)var0.xCoord / 0.5F;
         float var16 = (float)var0.yCoord / 0.66275F;
         float var17 = (float)var0.zCoord;
         var12 *= var15;
         var13 *= var16;
         var14 *= var17;
         return skyColorFader.getColor(var12, var13, var14);
      }
   }

   public static int parseColor(String var0) {
      if (var0 == null) {
         return -1;
      } else {
         var0 = var0.trim();

         try {
            return Integer.parseInt(var0, 16) & 16777215;
         } catch (NumberFormatException var2) {
            return -1;
         }
      }
   }

   public static Vec3 getUnderlavaColor(IBlockAccess var0, double var1, double var3, double var5) {
      return getUnderFluidColor(var0, var1, var3, var5, recoveredField2604, recoveredField2607);
   }

   public static int[] getMapColors() {
      MapColor[] var0 = MapColor.mapColorArray;
      int[] var1 = new int[var0.length];
      Arrays.fill(var1, -1);

      for (int var2 = 0; var2 < var0.length && var2 < var1.length; var2++) {
         MapColor var3 = var0[var2];
         if (var3 != null) {
            var1[var2] = var3.colorValue;
         }
      }

      return var1;
   }

   // $VF: synthetic method
   public static CustomColormap method_29853() {
      return recoveredField2603;
   }

   public static int method_29855(String var0) {
      if (var0.equals("potion.water")) {
         return 0;
      } else {
         Potion[] var1 = Potion.potionTypes;

         for (int var2 = 0; var2 < var1.length; var2++) {
            Potion var3 = var1[var2];
            if (var3 != null && var3.getName().equals(var0)) {
               return var3.getId();
            }
         }

         return -1;
      }
   }

   public static Vec3 getFogColorEnd(Vec3 var0) {
      return fogColorEnd == null ? var0 : fogColorEnd;
   }

   public static int getSpawnEggColor(ItemMonsterPlacer var0, ItemStack var1, int var2, int var3) {
      int var4 = var1.getMetadata();
      int[] var5 = var2 == 0 ? spawnEggPrimaryColors : spawnEggSecondaryColors;
      if (var5 == null) {
         return var3;
      } else if (var4 >= 0 && var4 < var5.length) {
         int var6 = var5[var4];
         return var6 < 0 ? var3 : var6;
      } else {
         return var3;
      }
   }

   public static int getColorFromItemStack(ItemStack var0, int var1, int var2) {
      if (var0 == null) {
         return var2;
      } else {
         Item var3 = var0.getItem();
         return var3 == null ? var2 : (var3 instanceof ItemMonsterPlacer ? getSpawnEggColor((ItemMonsterPlacer)var3, var0, var1, var2) : var2);
      }
   }

   public static void addToList(CustomColormap var0, List var1, int var2) {
      while (var2 >= var1.size()) {
         var1.add(null);
      }

      List var3 = (List)var1.get(var2);
      if (var3 == null) {
         var3 = new ArrayList();
         var1.set(var2, var3);
      }

      var3.add(var0);
   }

   public static int getSignTextColor(int var0) {
      return signTextColor < 0 ? var0 : signTextColor;
   }

   public static Vec3 getFogColor(Vec3 var0, IBlockAccess var1, double var2, double var4, double var6) {
      if (fogColors == null) {
         return var0;
      } else {
         int var8 = fogColors.getColorSmooth(var1, var2, var4, var6, 3);
         int var9 = var8 >> 16 & 0xFF;
         int var10 = var8 >> 8 & 0xFF;
         int var11 = var8 & 0xFF;
         float var12 = var9 / 255.0F;
         float var13 = var10 / 255.0F;
         float var14 = var11 / 255.0F;
         float var15 = (float)var0.xCoord / 0.753F;
         float var16 = (float)var0.yCoord / 0.8471F;
         float var17 = (float)var0.zCoord;
         var12 *= var15;
         var13 *= var16;
         var14 *= var17;
         return fogColorFader.getColor(var12, var13, var14);
      }
   }

   public static CustomColormap[][] readBlockColormaps(String[] var0, CustomColormap[] var1, int var2, int var3) {
      String[] var4 = ResUtils.collectFiles(var0, new String[]{".properties"});
      Arrays.sort(var4);
      ArrayList var5 = new ArrayList();

      for (int var6 = 0; var6 < var4.length; var6++) {
         String var7 = var4[var6];
         dbg("Block colormap: " + var7);

         try {
            ResourceLocation var8 = new ResourceLocation("minecraft", var7);
            InputStream var9 = Config.getResourceStream(var8);
            if (var9 == null) {
               warn("File not found: " + var7);
            } else {
               PropertiesOrdered var10 = new PropertiesOrdered();
               var10.load(var9);
               var9.close();
               CustomColormap var11 = new CustomColormap(var10, var7, var2, var3, paletteFormatDefault);
               if (var11.isValid(var7) && var11.isValidMatchBlocks(var7)) {
                  addToBlockList(var11, var5);
               }
            }
         } catch (FileNotFoundException var12) {
            warn("File not found: " + var7);
         } catch (Exception var13) {
            var13.printStackTrace();
         }
      }

      if (var1 != null) {
         for (int var14 = 0; var14 < var1.length; var14++) {
            CustomColormap var15 = var1[var14];
            addToBlockList(var15, var5);
         }
      }

      return var5.size() <= 0 ? (CustomColormap[][])null : blockListToArray(var5);
   }

   public static int getRedstoneColor(IBlockState var0) {
      if (redstoneColors == null) {
         return -1;
      } else {
         int var1 = getRedstoneLevel(var0, 15);
         return redstoneColors.getColor(var1);
      }
   }

   public static int method_29804(String var0) {
      return var0 == null
         ? -1
         : (
            var0.equals("air")
               ? MapColor.airColor.colorIndex
               : (
                  var0.equals("grass")
                     ? MapColor.grassColor.colorIndex
                     : (
                        var0.equals("sand")
                           ? MapColor.sandColor.colorIndex
                           : (
                              var0.equals("cloth")
                                 ? MapColor.clothColor.colorIndex
                                 : (
                                    var0.equals("tnt")
                                       ? MapColor.tntColor.colorIndex
                                       : (
                                          var0.equals("ice")
                                             ? MapColor.iceColor.colorIndex
                                             : (
                                                var0.equals("iron")
                                                   ? MapColor.ironColor.colorIndex
                                                   : (
                                                      var0.equals("foliage")
                                                         ? MapColor.foliageColor.colorIndex
                                                         : (
                                                            var0.equals("clay")
                                                               ? MapColor.clayColor.colorIndex
                                                               : (
                                                                  var0.equals("dirt")
                                                                     ? MapColor.dirtColor.colorIndex
                                                                     : (
                                                                        var0.equals("stone")
                                                                           ? MapColor.stoneColor.colorIndex
                                                                           : (
                                                                              var0.equals("water")
                                                                                 ? MapColor.waterColor.colorIndex
                                                                                 : (
                                                                                    var0.equals("wood")
                                                                                       ? MapColor.woodColor.colorIndex
                                                                                       : (
                                                                                          var0.equals("quartz")
                                                                                             ? MapColor.quartzColor.colorIndex
                                                                                             : (
                                                                                                var0.equals("gold")
                                                                                                   ? MapColor.goldColor.colorIndex
                                                                                                   : (
                                                                                                      var0.equals("diamond")
                                                                                                         ? MapColor.diamondColor.colorIndex
                                                                                                         : (
                                                                                                            var0.equals("lapis")
                                                                                                               ? MapColor.lapisColor.colorIndex
                                                                                                               : (
                                                                                                                  var0.equals("emerald")
                                                                                                                     ? MapColor.emeraldColor.colorIndex
                                                                                                                     : (
                                                                                                                        var0.equals("podzol")
                                                                                                                           ? MapColor.obsidianColor.colorIndex
                                                                                                                           : (
                                                                                                                              var0.equals("netherrack")
                                                                                                                                 ? MapColor.netherrackColor.colorIndex
                                                                                                                                 : (
                                                                                                                                    var0.equals("snow")
                                                                                                                                          || var0.equals(
                                                                                                                                             "white"
                                                                                                                                          )
                                                                                                                                       ? MapColor.snowColor.colorIndex
                                                                                                                                       : (
                                                                                                                                          var0.equals("adobe")
                                                                                                                                                || var0.equals(
                                                                                                                                                   "orange"
                                                                                                                                                )
                                                                                                                                             ? MapColor.adobeColor
                                                                                                                                                .colorIndex
                                                                                                                                             : (
                                                                                                                                                var0.equals(
                                                                                                                                                      "magenta"
                                                                                                                                                   )
                                                                                                                                                   ? MapColor.magentaColor
                                                                                                                                                      .colorIndex
                                                                                                                                                   : (
                                                                                                                                                      !var0.equals(
                                                                                                                                                               "light_blue"
                                                                                                                                                            )
                                                                                                                                                            && !var0.equals(
                                                                                                                                                               "lightBlue"
                                                                                                                                                            )
                                                                                                                                                         ? (
                                                                                                                                                            var0.equals(
                                                                                                                                                                  "yellow"
                                                                                                                                                               )
                                                                                                                                                               ? MapColor.yellowColor
                                                                                                                                                                  .colorIndex
                                                                                                                                                               : (
                                                                                                                                                                  var0.equals(
                                                                                                                                                                        "lime"
                                                                                                                                                                     )
                                                                                                                                                                     ? MapColor.limeColor
                                                                                                                                                                        .colorIndex
                                                                                                                                                                     : (
                                                                                                                                                                        var0.equals(
                                                                                                                                                                              "pink"
                                                                                                                                                                           )
                                                                                                                                                                           ? MapColor.pinkColor
                                                                                                                                                                              .colorIndex
                                                                                                                                                                           : (
                                                                                                                                                                              var0.equals(
                                                                                                                                                                                    "gray"
                                                                                                                                                                                 )
                                                                                                                                                                                 ? MapColor.grayColor
                                                                                                                                                                                    .colorIndex
                                                                                                                                                                                 : (
                                                                                                                                                                                    var0.equals(
                                                                                                                                                                                          "silver"
                                                                                                                                                                                       )
                                                                                                                                                                                       ? MapColor.silverColor
                                                                                                                                                                                          .colorIndex
                                                                                                                                                                                       : (
                                                                                                                                                                                          var0.equals(
                                                                                                                                                                                                "cyan"
                                                                                                                                                                                             )
                                                                                                                                                                                             ? MapColor.cyanColor
                                                                                                                                                                                                .colorIndex
                                                                                                                                                                                             : (
                                                                                                                                                                                                var0.equals(
                                                                                                                                                                                                      "purple"
                                                                                                                                                                                                   )
                                                                                                                                                                                                   ? MapColor.purpleColor
                                                                                                                                                                                                      .colorIndex
                                                                                                                                                                                                   : (
                                                                                                                                                                                                      var0.equals(
                                                                                                                                                                                                            "blue"
                                                                                                                                                                                                         )
                                                                                                                                                                                                         ? MapColor.blueColor
                                                                                                                                                                                                            .colorIndex
                                                                                                                                                                                                         : (
                                                                                                                                                                                                            var0.equals(
                                                                                                                                                                                                                  "brown"
                                                                                                                                                                                                               )
                                                                                                                                                                                                               ? MapColor.brownColor
                                                                                                                                                                                                                  .colorIndex
                                                                                                                                                                                                               : (
                                                                                                                                                                                                                  var0.equals(
                                                                                                                                                                                                                        "green"
                                                                                                                                                                                                                     )
                                                                                                                                                                                                                     ? MapColor.greenColor
                                                                                                                                                                                                                        .colorIndex
                                                                                                                                                                                                                     : (
                                                                                                                                                                                                                        var0.equals(
                                                                                                                                                                                                                              "red"
                                                                                                                                                                                                                           )
                                                                                                                                                                                                                           ? MapColor.redColor
                                                                                                                                                                                                                              .colorIndex
                                                                                                                                                                                                                           : (
                                                                                                                                                                                                                              var0.equals(
                                                                                                                                                                                                                                    "black"
                                                                                                                                                                                                                                 )
                                                                                                                                                                                                                                 ? MapColor.blackColor
                                                                                                                                                                                                                                    .colorIndex
                                                                                                                                                                                                                                 : -1
                                                                                                                                                                                                                           )
                                                                                                                                                                                                                     )
                                                                                                                                                                                                               )
                                                                                                                                                                                                         )
                                                                                                                                                                                                   )
                                                                                                                                                                                             )
                                                                                                                                                                                       )
                                                                                                                                                                                 )
                                                                                                                                                                           )
                                                                                                                                                                     )
                                                                                                                                                               )
                                                                                                                                                         )
                                                                                                                                                         : MapColor.lightBlueColor
                                                                                                                                                            .colorIndex
                                                                                                                                                   )
                                                                                                                                             )
                                                                                                                                       )
                                                                                                                                 )
                                                                                                                           )
                                                                                                                     )
                                                                                                               )
                                                                                                         )
                                                                                                   )
                                                                                             )
                                                                                       )
                                                                                 )
                                                                           )
                                                                     )
                                                               )
                                                         )
                                                   )
                                             )
                                       )
                                 )
                           )
                     )
               )
         );
   }

   public static Vec3 getSkyColorEnd(Vec3 var0) {
      return skyColorEnd == null ? var0 : skyColorEnd;
   }

   public static void updateReddustFX(EntityFX var0, IBlockAccess var1, double var2, double var4, double var6) {
      if (redstoneColors != null) {
         IBlockState var8 = var1.getBlockState(new BlockPos(var2, var4, var6));
         int var9 = getRedstoneLevel(var8, 15);
         int var10 = redstoneColors.getColor(var9);
         int var11 = var10 >> 16 & 0xFF;
         int var12 = var10 >> 8 & 0xFF;
         int var13 = var10 & 0xFF;
         float var14 = var11 / 255.0F;
         float var15 = var12 / 255.0F;
         float var16 = var13 / 255.0F;
         var0.b(var14, var15, var16);
      }
   }

   public static int getExpBarTextColor(int var0) {
      return expBarTextColor < 0 ? var0 : expBarTextColor;
   }

   public static int method_29810(int var0) {
      return bossTextColor < 0 ? var0 : bossTextColor;
   }

   public static int getFluidColor(IBlockAccess var0, IBlockState var1, BlockPos var2, RenderEnv var3) {
      Block var4 = var1.getBlock();
      net.optifine.CustomColors.IColorizer var5 = getBlockColormap(var1);
      if (var5 == null && var1.getBlock().getMaterial() == Material.water) {
         var5 = recoveredField2602;
      }

      return var5 == null
         ? var4.colorMultiplier(var0, var2, 0)
         : (
            Config.isSmoothBiomes() && !((CustomColors.IColorizer)var5).isColorConstant()
               ? getSmoothColorMultiplier(var1, var0, var2, (CustomColors.IColorizer)var5, var3.getColorizerBlockPosM())
               : ((CustomColors.IColorizer)var5).getColor(var1, var0, var2)
         );
   }

   public static Vec3 getUnderwaterColor(IBlockAccess var0, double var1, double var3, double var5) {
      return getUnderFluidColor(var0, var1, var3, var5, recoveredField2605, recoveredField2595);
   }

   public static int getLilypadColorMultiplier(IBlockAccess var0, BlockPos var1) {
      return lilyPadColor < 0 ? Blocks.waterlily.colorMultiplier(var0, var1) : lilyPadColor;
   }

   public static float[] getDyeColors(EnumDyeColor var0, float[][] var1, float[] var2) {
      if (var1 == null) {
         return var2;
      } else if (var0 == null) {
         return var2;
      } else {
         float[] var3 = var1[var0.ordinal()];
         return var3 == null ? var2 : var3;
      }
   }

   public static int getSmoothColorMultiplier(IBlockState var0, IBlockAccess var1, BlockPos var2, CustomColors.IColorizer var3, BlockPosM var4) {
      int var5 = 0;
      int var6 = 0;
      int var7 = 0;
      int var8 = var2.getX();
      int var9 = var2.getY();
      int var10 = var2.getZ();
      BlockPosM var11 = var4;

      for (int var12 = var8 - 1; var12 <= var8 + 1; var12++) {
         for (int var13 = var10 - 1; var13 <= var10 + 1; var13++) {
            var11.setXyz(var12, var9, var13);
            int var14 = var3.getColor(var0, var1, var11);
            var5 += var14 >> 16 & 0xFF;
            var6 += var14 >> 8 & 0xFF;
            var7 += var14 & 0xFF;
         }
      }

      int var15 = var5 / 9;
      int var16 = var6 / 9;
      int var17 = var7 / 9;
      return var15 << 16 | var16 << 8 | var17;
   }

   public static int getDurabilityColor(int var0) {
      if (durabilityColors == null) {
         return -1;
      } else {
         int var1 = var0 * durabilityColors.getLength() / 255;
         return durabilityColors.getColor(var1);
      }
   }

   public static void warn(String var0) {
      Config.warn("CustomColors: " + var0);
   }

   public static CustomColormap[] readCustomColormaps(Properties var0, String var1) {
      ArrayList var2 = new ArrayList();
      String var3 = "palette.block.";
      HashMap var4 = new HashMap();

      for (Object var6 : var0.keySet()) {
         String var7 = (String)var6;
         String var8 = var0.getProperty(var7);
         if (var7.startsWith(var3)) {
            var4.put(var7, var8);
         }
      }

      String[] var16 = (String[])var4.keySet().toArray(new String[var4.size()]);

      for (int var17 = 0; var17 < var16.length; var17++) {
         String var18 = var16[var17];
         String var19 = var0.getProperty(var18);
         dbg("Block palette: " + var18 + " = " + var19);
         String var9 = var18.substring(var3.length());
         String var10 = TextureUtils.getBasePath(var1);
         var9 = TextureUtils.fixResourcePath(var9, var10);
         CustomColormap var11 = getCustomColors(var9, 256, 256);
         if (var11 == null) {
            warn("Colormap not found: " + var9);
         } else {
            ConnectedParser var12 = new ConnectedParser("CustomColors");
            MatchBlock[] var13 = var12.parseMatchBlocks(var19);
            if (var13 != null && var13.length > 0) {
               for (int var14 = 0; var14 < var13.length; var14++) {
                  MatchBlock var15 = var13[var14];
                  var11.addMatchBlock(var15);
               }

               var2.add(var11);
            } else {
               warn("Invalid match blocks: " + var19);
            }
         }
      }

      return var2.size() <= 0 ? null : (CustomColormap[])var2.toArray(new CustomColormap[var2.size()]);
   }

   public static void update() {
      paletteFormatDefault = "vanilla";
      recoveredField2603 = null;
      foliageBirchColors = null;
      foliagePineColors = null;
      swampGrassColors = null;
      swampFoliageColors = null;
      skyColors = null;
      fogColors = null;
      recoveredField2605 = null;
      recoveredField2604 = null;
      redstoneColors = null;
      xpOrbColors = null;
      xpOrbTime = -1;
      durabilityColors = null;
      stemColors = null;
      recoveredField2606 = null;
      lightMapPacks = null;
      particleWaterColor = -1;
      particlePortalColor = -1;
      lilyPadColor = -1;
      expBarTextColor = -1;
      bossTextColor = -1;
      signTextColor = -1;
      fogColorNether = null;
      fogColorEnd = null;
      skyColorEnd = null;
      colorsBlockColormaps = null;
      blockColormaps = (CustomColormap[][])null;
      useDefaultGrassFoliageColors = true;
      spawnEggPrimaryColors = null;
      spawnEggSecondaryColors = null;
      wolfCollarColors = (float[][])null;
      sheepColors = (float[][])null;
      textColors = null;
      setMapColors(mapColorsOriginal);
      potionColors = null;
      paletteFormatDefault = getValidProperty("mcpatcher/color.properties", "palette.format", CustomColormap.FORMAT_STRINGS, "vanilla");
      String var0 = "mcpatcher/colormap/";
      String[] var1 = new String[]{"water.png", "watercolorX.png"};
      recoveredField2603 = getCustomColors(var0, var1, 256, 256);
      updateUseDefaultGrassFoliageColors();
      if (Config.isCustomColors()) {
         String[] var2 = new String[]{"pine.png", "pinecolor.png"};
         foliagePineColors = getCustomColors(var0, var2, 256, 256);
         String[] var3 = new String[]{"birch.png", "birchcolor.png"};
         foliageBirchColors = getCustomColors(var0, var3, 256, 256);
         String[] var4 = new String[]{"swampgrass.png", "swampgrasscolor.png"};
         swampGrassColors = getCustomColors(var0, var4, 256, 256);
         String[] var5 = new String[]{"swampfoliage.png", "swampfoliagecolor.png"};
         swampFoliageColors = getCustomColors(var0, var5, 256, 256);
         String[] var6 = new String[]{"sky0.png", "skycolor0.png"};
         skyColors = getCustomColors(var0, var6, 256, 256);
         String[] var7 = new String[]{"fog0.png", "fogcolor0.png"};
         fogColors = getCustomColors(var0, var7, 256, 256);
         String[] var8 = new String[]{"underwater.png", "underwatercolor.png"};
         recoveredField2605 = getCustomColors(var0, var8, 256, 256);
         String[] var9 = new String[]{"underlava.png", "underlavacolor.png"};
         recoveredField2604 = getCustomColors(var0, var9, 256, 256);
         String[] var10 = new String[]{"redstone.png", "redstonecolor.png"};
         redstoneColors = getCustomColors(var0, var10, 16, 1);
         xpOrbColors = getCustomColors(var0 + "xporb.png", -1, -1);
         durabilityColors = getCustomColors(var0 + "durability.png", -1, -1);
         String[] var11 = new String[]{"stem.png", "stemcolor.png"};
         stemColors = getCustomColors(var0, var11, 8, 1);
         stemPumpkinColors = getCustomColors(var0 + "pumpkinstem.png", 8, 1);
         stemMelonColors = getCustomColors(var0 + "melonstem.png", 8, 1);
         String[] var12 = new String[]{"myceliumparticle.png", "myceliumparticlecolor.png"};
         recoveredField2606 = getCustomColors(var0, var12, -1, -1);
         Pair var13 = parseLightMapPacks();
         lightMapPacks = (LightMapPack[])var13.getLeft();
         lightmapMinDimensionId = (Integer)var13.getRight();
         readColorProperties("mcpatcher/color.properties");
         blockColormaps = readBlockColormaps(new String[]{var0 + "custom/", var0 + "blocks/"}, colorsBlockColormaps, 256, 256);
         updateUseDefaultGrassFoliageColors();
      }
   }

   public static Vec3 readColorVec3(Properties var0, String var1) {
      int var2 = readColor(var0, var1);
      if (var2 < 0) {
         return null;
      } else {
         int var3 = var2 >> 16 & 0xFF;
         int var4 = var2 >> 8 & 0xFF;
         int var5 = var2 & 0xFF;
         float var6 = var3 / 255.0F;
         float var7 = var4 / 255.0F;
         float var8 = var5 / 255.0F;
         return new Vec3(var6, var7, var8);
      }
   }

   public static float[] getWolfCollarColors(EnumDyeColor var0, float[] var1) {
      return getDyeColors(var0, wolfCollarColors, var1);
   }

   public static BiomeGenBase getColorBiome(IBlockAccess var0, BlockPos var1) {
      BiomeGenBase var2 = var0.getBiomeGenForCoords(var1);
      if (var2 == BiomeGenBase.swampland && !Config.isSwampColors()) {
         var2 = BiomeGenBase.plains;
      }

      return var2;
   }

   public static int[] readTextColors(Properties var0, String var1, String var2, String var3) {
      int[] var4 = new int[32];
      Arrays.fill(var4, -1);
      int var5 = 0;

      for (Object var7 : var0.keySet()) {
         String var8 = (String)var7;
         String var9 = var0.getProperty(var8);
         if (var8.startsWith(var2)) {
            String var10 = StrUtils.removePrefix(var8, var2);
            int var11 = Config.parseInt(var10, -1);
            int var12 = parseColor(var9);
            if (var11 >= 0 && var11 < var4.length && var12 >= 0) {
               var4[var11] = var12;
               var5++;
            } else {
               warn("Invalid color: " + var8 + " = " + var9);
            }
         }
      }

      if (var5 <= 0) {
         return null;
      } else {
         dbg(var3 + " colors: " + var5);
         return var4;
      }
   }

   public static void setMapColors(int[] var0) {
      if (var0 != null) {
         MapColor[] var1 = MapColor.mapColorArray;
         boolean var2 = false;

         for (int var3 = 0; var3 < var1.length && var3 < var0.length; var3++) {
            MapColor var4 = var1[var3];
            if (var4 != null) {
               int var5 = var0[var3];
               if (var5 >= 0 && var4.colorValue != var5) {
                  var4.colorValue = var5;
                  var2 = true;
               }
            }
         }

         if (var2) {
            Minecraft.getMinecraft().getTextureManager().reloadBannerTextures();
         }
      }
   }

   // $VF: synthetic method
   public static CustomColormap access$300() {
      return foliageBirchColors;
   }

   public static float[][] readDyeColors(Properties var0, String var1, String var2, String var3) {
      EnumDyeColor[] var4 = EnumDyeColor.values();
      HashMap var5 = new HashMap();

      for (int var6 = 0; var6 < var4.length; var6++) {
         EnumDyeColor var7 = var4[var6];
         var5.put(var7.getName(), var7);
      }

      float[][] var16 = new float[var4.length][];
      int var17 = 0;

      for (Object var9 : var0.keySet()) {
         String var10 = (String)var9;
         String var11 = var0.getProperty(var10);
         if (var10.startsWith(var2)) {
            String var12 = StrUtils.removePrefix(var10, var2);
            if (var12.equals("lightBlue")) {
               var12 = "light_blue";
            }

            EnumDyeColor var13 = (EnumDyeColor)var5.get(var12);
            int var14 = parseColor(var11);
            if (var13 != null && var14 >= 0) {
               float[] var15 = new float[]{(var14 >> 16 & 0xFF) / 255.0F, (var14 >> 8 & 0xFF) / 255.0F, (var14 & 0xFF) / 255.0F};
               var16[var13.ordinal()] = var15;
               var17++;
            } else {
               warn("Invalid color: " + var10 + " = " + var11);
            }
         }
      }

      if (var17 <= 0) {
         return (float[][])null;
      } else {
         dbg(var3 + " colors: " + var17);
         return var16;
      }
   }

   public static String getValidProperty(String var0, String var1, String[] var2, String var3) {
      try {
         ResourceLocation var4 = new ResourceLocation(var0);
         InputStream var5 = Config.getResourceStream(var4);
         if (var5 == null) {
            return var3;
         } else {
            PropertiesOrdered var6 = new PropertiesOrdered();
            var6.load(var5);
            var5.close();
            String var7 = var6.getProperty(var1);
            if (var7 == null) {
               return var3;
            } else {
               List var8 = Arrays.asList(var2);
               if (!var8.contains(var7)) {
                  warn("Invalid value: " + var1 + "=" + var7);
                  warn("Expected values: " + Config.arrayToString(var2));
                  return var3;
               } else {
                  dbg("" + var1 + "=" + var7);
                  return var7;
               }
            }
         }
      } catch (FileNotFoundException var9) {
         return var3;
      } catch (IOException var10) {
         var10.printStackTrace();
         return var3;
      }
   }

   public static void method_29862(EntityFX var0) {
      if (recoveredField2606 != null) {
         int var1 = recoveredField2606.getColorRandom();
         int var2 = var1 >> 16 & 0xFF;
         int var3 = var1 >> 8 & 0xFF;
         int var4 = var1 & 0xFF;
         float var5 = var2 / 255.0F;
         float var6 = var3 / 255.0F;
         float var7 = var4 / 255.0F;
         var0.b(var5, var6, var7);
      }
   }

   public static int[] readMapColors(Properties var0, String var1, String var2, String var3) {
      int[] var4 = new int[MapColor.mapColorArray.length];
      Arrays.fill(var4, -1);
      int var5 = 0;

      for (Object var7 : var0.keySet()) {
         String var8 = (String)var7;
         String var9 = var0.getProperty(var8);
         if (var8.startsWith(var2)) {
            String var10 = StrUtils.removePrefix(var8, var2);
            int var11 = method_29804(var10);
            int var12 = parseColor(var9);
            if (var11 >= 0 && var11 < var4.length && var12 >= 0) {
               var4[var11] = var12;
               var5++;
            } else {
               warn("Invalid color: " + var8 + " = " + var9);
            }
         }
      }

      if (var5 <= 0) {
         return null;
      } else {
         dbg(var3 + " colors: " + var5);
         return var4;
      }
   }

   // $VF: synthetic method
   public static CustomColormap access$100() {
      return swampFoliageColors;
   }

   public static int readColor(Properties var0, String[] var1) {
      for (int var2 = 0; var2 < var1.length; var2++) {
         String var3 = var1[var2];
         int var4 = readColor(var0, var3);
         if (var4 >= 0) {
            return var4;
         }
      }

      return -1;
   }

   // $VF: synthetic method
   public static CustomColormap access$200() {
      return foliagePineColors;
   }

   public static void updateWaterFX(EntityFX var0, IBlockAccess var1, double var2, double var4, double var6, RenderEnv var8) {
      if (recoveredField2603 != null || blockColormaps != null || particleWaterColor >= 0) {
         BlockPos var9 = new BlockPos(var2, var4, var6);
         var8.reset(recoveredField2600, var9);
         int var10 = getFluidColor(var1, recoveredField2600, var9, var8);
         int var11 = var10 >> 16 & 0xFF;
         int var12 = var10 >> 8 & 0xFF;
         int var13 = var10 & 0xFF;
         float var14 = var11 / 255.0F;
         float var15 = var12 / 255.0F;
         float var16 = var13 / 255.0F;
         if (particleWaterColor >= 0) {
            int var17 = particleWaterColor >> 16 & 0xFF;
            int var18 = particleWaterColor >> 8 & 0xFF;
            int var19 = particleWaterColor & 0xFF;
            var14 *= var17 / 255.0F;
            var15 *= var18 / 255.0F;
            var16 *= var19 / 255.0F;
         }

         var0.b(var14, var15, var16);
      }
   }

   public static boolean updateLightmap(World var0, float var1, int[] var2, boolean var3, float var4) {
      if (var0 == null) {
         return false;
      } else if (lightMapPacks == null) {
         return false;
      } else if ((Boolean)CheatBreaker.getInstance().getGlobalSettings().recoveredField563.getValue()
         && (Boolean)CheatBreaker.getInstance().getGlobalSettings().recoveredField486.getValue()) {
         return false;
      } else {
         int var5 = var0.t.getDimensionId();
         int var6 = var5 - lightmapMinDimensionId;
         if (var6 >= 0 && var6 < lightMapPacks.length) {
            LightMapPack var7 = lightMapPacks[var6];
            return var7 != null && var7.updateLightmap(var0, var1, var2, var3, var4);
         } else {
            return false;
         }
      }
   }

   public static float getXpOrbTimer(float var0) {
      if (xpOrbTime <= 0) {
         return var0;
      } else {
         float var1 = 628.0F / xpOrbTime;
         return var0 * var1;
      }
   }

   public static void updateUseDefaultGrassFoliageColors() {
      useDefaultGrassFoliageColors = foliageBirchColors == null
         && foliagePineColors == null
         && swampGrassColors == null
         && swampFoliageColors == null
         && Config.isSwampColors()
         && Config.isSmoothBiomes();
   }

   public static int method_29818(int var0, int var1) {
      if (potionColors == null) {
         return var1;
      } else if (var0 >= 0 && var0 < potionColors.length) {
         int var2 = potionColors[var0];
         return var2 < 0 ? var1 : var2;
      } else {
         return var1;
      }
   }

   public static int readColor(Properties var0, String var1) {
      String var2 = var0.getProperty(var1);
      if (var2 == null) {
         return -1;
      } else {
         var2 = var2.trim();
         int var3 = parseColor(var2);
         if (var3 < 0) {
            warn("Invalid color: " + var1 + " = " + var2);
            return var3;
         } else {
            dbg(var1 + " = " + var2);
            return var3;
         }
      }
   }

   public static CustomColormap getCustomColors(String var0, String[] var1, int var2, int var3) {
      for (int var4 = 0; var4 < var1.length; var4++) {
         String var5 = var1[var4];
         var5 = var0 + var5;
         CustomColormap var6 = getCustomColors(var5, var2, var3);
         if (var6 != null) {
            return var6;
         }
      }

      return null;
   }

   public static int getColorMultiplier(BakedQuad var0, IBlockState var1, IBlockAccess var2, BlockPos var3, RenderEnv var4) {
      Block var5 = var1.getBlock();
      IBlockState var6 = var4.getBlockState();
      if (blockColormaps != null) {
         if (!var0.hasTintIndex()) {
            if (var5 == Blocks.grass) {
               var6 = recoveredField2596;
            }

            if (var5 == Blocks.redstone_wire) {
               return -1;
            }
         }

         if (var5 == Blocks.double_plant && var4.getMetadata() >= 8) {
            var3 = var3.down();
            var6 = var2.getBlockState(var3);
         }

         CustomColormap var7 = getBlockColormap(var6);
         if (var7 != null) {
            if (Config.isSmoothBiomes() && !var7.isColorConstant()) {
               return getSmoothColorMultiplier(var1, var2, var3, var7, var4.getColorizerBlockPosM());
            }

            return var7.getColor(var2, var3);
         }
      }

      if (!var0.hasTintIndex()) {
         return -1;
      } else if (var5 == Blocks.waterlily) {
         return getLilypadColorMultiplier(var2, var3);
      } else if (var5 == Blocks.redstone_wire) {
         return getRedstoneColor(var4.getBlockState());
      } else if (var5 instanceof BlockStem) {
         return getStemColorMultiplier(var5, var2, var3, var4);
      } else if (useDefaultGrassFoliageColors) {
         return -1;
      } else {
         int var9 = var4.getMetadata();
         CustomColors.IColorizer var8;
         if (var5 == Blocks.grass || var5 == Blocks.tallgrass || var5 == Blocks.double_plant) {
            var8 = recoveredField2597;
         } else if (var5 == Blocks.double_plant) {
            var8 = recoveredField2597;
            if (var9 >= 8) {
               var3 = var3.down();
            }
         } else if (var5 == Blocks.leaves) {
            switch (var9 & 3) {
               case 0:
                  var8 = recoveredField2599;
                  break;
               case 1:
                  var8 = recoveredField2601;
                  break;
               case 2:
                  var8 = recoveredField2598;
                  break;
               default:
                  var8 = recoveredField2599;
            }
         } else if (var5 == Blocks.leaves2) {
            var8 = recoveredField2599;
         } else {
            if (var5 != Blocks.vine) {
               return -1;
            }

            var8 = recoveredField2599;
         }

         return Config.isSmoothBiomes() && !var8.isColorConstant()
            ? getSmoothColorMultiplier(var1, var2, var3, var8, var4.getColorizerBlockPosM())
            : var8.getColor(var6, var2, var3);
      }
   }

   public static int method_29860(int var0, int var1) {
      if (textColors == null) {
         return var1;
      } else if (var0 >= 0 && var0 < textColors.length) {
         int var2 = textColors[var0];
         return var2 < 0 ? var1 : var2;
      } else {
         return var1;
      }
   }

   public static int[] readPotionColors(Properties var0, String var1, String var2, String var3) {
      int[] var4 = new int[Potion.potionTypes.length];
      Arrays.fill(var4, -1);
      int var5 = 0;

      for (Object var7 : var0.keySet()) {
         String var8 = (String)var7;
         String var9 = var0.getProperty(var8);
         if (var8.startsWith(var2)) {
            int var10 = method_29855(var8);
            int var11 = parseColor(var9);
            if (var10 >= 0 && var10 < var4.length && var11 >= 0) {
               var4[var10] = var11;
               var5++;
            } else {
               warn("Invalid color: " + var8 + " = " + var9);
            }
         }
      }

      if (var5 <= 0) {
         return null;
      } else {
         dbg(var3 + " colors: " + var5);
         return var4;
      }
   }

   public static int getTextureHeight(String var0, int var1) {
      try {
         InputStream var2 = Config.getResourceStream(new ResourceLocation(var0));
         if (var2 == null) {
            return var1;
         } else {
            BufferedImage var3 = ImageIO.read(var2);
            var2.close();
            return var3 == null ? var1 : var3.getHeight();
         }
      } catch (IOException var4) {
         return var1;
      }
   }

   public static Vec3 getWorldFogColor(Vec3 var0, World var1, Entity var2, float var3) {
      int var4 = var1.t.getDimensionId();
      switch (var4) {
         case -1:
            var0 = getFogColorNether(var0);
            break;
         case 0:
            Minecraft var5 = Minecraft.getMinecraft();
            var0 = getFogColor(var0, var5.theWorld, var2.s, var2.t + 1.0, var2.u);
            break;
         case 1:
            var0 = getFogColorEnd(var0);
      }

      return var0;
   }

   public static Vec3 getUnderFluidColor(IBlockAccess var0, double var1, double var3, double var5, CustomColormap var7, CustomColorFader var8) {
      if (var7 == null) {
         return null;
      } else {
         int var9 = var7.getColorSmooth(var0, var1, var3, var5, 3);
         int var10 = var9 >> 16 & 0xFF;
         int var11 = var9 >> 8 & 0xFF;
         int var12 = var9 & 0xFF;
         float var13 = var10 / 255.0F;
         float var14 = var11 / 255.0F;
         float var15 = var12 / 255.0F;
         return var8.getColor(var13, var14, var15);
      }
   }

   public static CustomColormap getBlockColormap(IBlockState var0) {
      if (blockColormaps == null) {
         return null;
      } else if (!(var0 instanceof BlockStateBase)) {
         return null;
      } else {
         BlockStateBase var1 = (BlockStateBase)var0;
         int var2 = var1.getBlockId();
         if (var2 >= 0 && var2 < blockColormaps.length) {
            CustomColormap[] var3 = blockColormaps[var2];
            if (var3 == null) {
               return null;
            } else {
               for (int var4 = 0; var4 < var3.length; var4++) {
                  CustomColormap var5 = var3[var4];
                  if (var5.matchesBlock(var1)) {
                     return var5;
                  }
               }

               return null;
            }
         } else {
            return null;
         }
      }
   }

   public static float[] getSheepColors(EnumDyeColor var0, float[] var1) {
      return getDyeColors(var0, sheepColors, var1);
   }

   public static Vec3 getWorldSkyColor(Vec3 var0, World var1, Entity var2, float var3) {
      int var4 = var1.t.getDimensionId();
      switch (var4) {
         case 0:
            Minecraft var5 = Minecraft.getMinecraft();
            var0 = getSkyColor(var0, var5.theWorld, var2.s, var2.t + 1.0, var2.u);
            break;
         case 1:
            var0 = getSkyColorEnd(var0);
      }

      return var0;
   }

   public static int getRedstoneLevel(IBlockState var0, int var1) {
      Block var2 = var0.getBlock();
      if (!(var2 instanceof BlockRedstoneWire)) {
         return var1;
      } else {
         Comparable var3 = var0.getValue(BlockRedstoneWire.POWER);
         if (!(var3 instanceof Integer)) {
            return var1;
         } else {
            Integer var4 = (Integer)var3;
            return var4;
         }
      }
   }

   public static Vec3 getFogColorNether(Vec3 var0) {
      return fogColorNether == null ? var0 : fogColorNether;
   }

   public interface IColorizer {
      int getColor(IBlockState var1, IBlockAccess var2, BlockPos var3);

      boolean isColorConstant();
   }
}
