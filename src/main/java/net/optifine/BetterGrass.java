package net.optifine;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockMycelium;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.init.Blocks;
import net.minecraft.src.Config;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.optifine.model.BlockModelUtils;
import net.optifine.util.PropertiesOrdered;

public class BetterGrass {
   public static final String recoveredField3029 = "blocks/grass_side";
   public static final String recoveredField3030 = "blocks/snow";
   public static final String recoveredField3031 = "blocks/grass_top";
   public static final String recoveredField3032 = "blocks/dirt_podzol_top";
   public static final String recoveredField3033 = "blocks/mycelium_top";
   public static boolean betterGrass = true;
   public static boolean betterMycelium = true;
   public static boolean betterPodzol = true;
   public static boolean betterGrassSnow = true;
   public static boolean betterMyceliumSnow = true;
   public static boolean betterPodzolSnow = true;
   public static boolean grassMultilayer = false;
   public static TextureAtlasSprite spriteGrass = null;
   public static TextureAtlasSprite spriteGrassSide = null;
   public static TextureAtlasSprite spriteMycelium = null;
   public static TextureAtlasSprite spritePodzol = null;
   public static TextureAtlasSprite spriteSnow = null;
   public static boolean spritesLoaded = false;
   public static IBakedModel modelCubeGrass = null;
   public static IBakedModel modelCubeMycelium = null;
   public static IBakedModel modelCubePodzol = null;
   public static IBakedModel modelCubeSnow = null;
   public static boolean modelsLoaded = false;

   public static List getFaceQuadsGrass(IBlockAccess var0, IBlockState var1, BlockPos var2, EnumFacing var3, List var4) {
      Block var5 = var0.getBlockState(var2.up()).getBlock();
      boolean var6 = var5 == Blocks.snow || var5 == Blocks.snow_layer;
      if (Config.isBetterGrassFancy()) {
         if (var6) {
            if (betterGrassSnow && getBlockAt(var2, var3, var0) == Blocks.snow_layer) {
               return modelCubeSnow.getFaceQuads(var3);
            }
         } else if (betterGrass && getBlockAt(var2.down(), var3, var0) == Blocks.grass) {
            return modelCubeGrass.getFaceQuads(var3);
         }
      } else if (var6) {
         if (betterGrassSnow) {
            return modelCubeSnow.getFaceQuads(var3);
         }
      } else if (betterGrass) {
         return modelCubeGrass.getFaceQuads(var3);
      }

      return var4;
   }

   public static boolean getBoolean(Properties var0, String var1, boolean var2) {
      String var3 = var0.getProperty(var1);
      return var3 == null ? var2 : Boolean.parseBoolean(var3);
   }

   public static List method_21656(IBlockAccess var0, IBlockState var1, BlockPos var2, EnumFacing var3, List var4) {
      Block var5 = getBlockAt(var2, EnumFacing.UP, var0);
      if (var1.getValue(BlockDirt.VARIANT) != BlockDirt.DirtType.PODZOL) {
         return var4;
      } else {
         boolean var6 = var5 == Blocks.snow || var5 == Blocks.snow_layer;
         if (Config.isBetterGrassFancy()) {
            if (var6) {
               if (betterPodzolSnow && getBlockAt(var2, var3, var0) == Blocks.snow_layer) {
                  return modelCubeSnow.getFaceQuads(var3);
               }
            } else if (betterPodzol) {
               BlockPos var7 = var2.down().a(var3);
               IBlockState var8 = var0.getBlockState(var7);
               if (var8.getBlock() == Blocks.dirt && var8.getValue(BlockDirt.VARIANT) == BlockDirt.DirtType.PODZOL) {
                  return modelCubePodzol.getFaceQuads(var3);
               }
            }
         } else if (var6) {
            if (betterPodzolSnow) {
               return modelCubeSnow.getFaceQuads(var3);
            }
         } else if (betterPodzol) {
            return modelCubePodzol.getFaceQuads(var3);
         }

         return var4;
      }
   }

   public static Block getBlockAt(BlockPos var0, EnumFacing var1, IBlockAccess var2) {
      BlockPos var3 = var0.a(var1);
      return var2.getBlockState(var3).getBlock();
   }

   public static void loadProperties(TextureMap var0) {
      betterGrass = true;
      betterMycelium = true;
      betterPodzol = true;
      betterGrassSnow = true;
      betterMyceliumSnow = true;
      betterPodzolSnow = true;
      spriteGrass = var0.registerSprite(new ResourceLocation("blocks/grass_top"));
      spriteGrassSide = var0.registerSprite(new ResourceLocation("blocks/grass_side"));
      spriteMycelium = var0.registerSprite(new ResourceLocation("blocks/mycelium_top"));
      spritePodzol = var0.registerSprite(new ResourceLocation("blocks/dirt_podzol_top"));
      spriteSnow = var0.registerSprite(new ResourceLocation("blocks/snow"));
      spritesLoaded = true;
      String var1 = "optifine/bettergrass.properties";

      try {
         ResourceLocation var2 = new ResourceLocation(var1);
         if (!Config.hasResource(var2)) {
            return;
         }

         InputStream var3 = Config.getResourceStream(var2);
         if (var3 == null) {
            return;
         }

         boolean var4 = Config.isFromDefaultResourcePack(var2);
         if (var4) {
            Config.dbg("BetterGrass: Parsing default configuration " + var1);
         } else {
            Config.dbg("BetterGrass: Parsing configuration " + var1);
         }

         PropertiesOrdered var5 = new PropertiesOrdered();
         var5.load(var3);
         var3.close();
         betterGrass = getBoolean(var5, "grass", true);
         betterMycelium = getBoolean(var5, "mycelium", true);
         betterPodzol = getBoolean(var5, "podzol", true);
         betterGrassSnow = getBoolean(var5, "grass.snow", true);
         betterMyceliumSnow = getBoolean(var5, "mycelium.snow", true);
         betterPodzolSnow = getBoolean(var5, "podzol.snow", true);
         grassMultilayer = getBoolean(var5, "grass.multilayer", false);
         spriteGrass = registerSprite(var5, "texture.grass", "blocks/grass_top", var0);
         spriteGrassSide = registerSprite(var5, "texture.grass_side", "blocks/grass_side", var0);
         spriteMycelium = registerSprite(var5, "texture.mycelium", "blocks/mycelium_top", var0);
         spritePodzol = registerSprite(var5, "texture.podzol", "blocks/dirt_podzol_top", var0);
         spriteSnow = registerSprite(var5, "texture.snow", "blocks/snow", var0);
      } catch (IOException var6) {
         Config.warn("Error reading: " + var1 + ", " + var6.getClass().getName() + ": " + var6.getMessage());
      }
   }

   public static void updateIcons(TextureMap var0) {
      spritesLoaded = false;
      modelsLoaded = false;
      loadProperties(var0);
   }

   public static TextureAtlasSprite registerSprite(Properties var0, String var1, String var2, TextureMap var3) {
      String var4 = var0.getProperty(var1);
      if (var4 == null) {
         var4 = var2;
      }

      ResourceLocation var5 = new ResourceLocation("textures/" + var4 + ".png");
      if (!Config.hasResource(var5)) {
         Config.warn("BetterGrass texture not found: " + var5);
         var4 = var2;
      }

      ResourceLocation var6 = new ResourceLocation(var4);
      return var3.registerSprite(var6);
   }

   public static List getFaceQuads(IBlockAccess var0, IBlockState var1, BlockPos var2, EnumFacing var3, List var4) {
      if (var3 == EnumFacing.UP || var3 == EnumFacing.DOWN) {
         return var4;
      } else if (!modelsLoaded) {
         return var4;
      } else {
         Block var5 = var1.getBlock();
         return var5 instanceof BlockMycelium
            ? getFaceQuadsMycelium(var0, var1, var2, var3, var4)
            : (
               var5 instanceof BlockDirt
                  ? method_21656(var0, var1, var2, var3, var4)
                  : (var5 instanceof BlockGrass ? getFaceQuadsGrass(var0, var1, var2, var3, var4) : var4)
            );
      }
   }

   public static List getFaceQuadsMycelium(IBlockAccess var0, IBlockState var1, BlockPos var2, EnumFacing var3, List var4) {
      Block var5 = var0.getBlockState(var2.up()).getBlock();
      boolean var6 = var5 == Blocks.snow || var5 == Blocks.snow_layer;
      if (Config.isBetterGrassFancy()) {
         if (var6) {
            if (betterMyceliumSnow && getBlockAt(var2, var3, var0) == Blocks.snow_layer) {
               return modelCubeSnow.getFaceQuads(var3);
            }
         } else if (betterMycelium && getBlockAt(var2.down(), var3, var0) == Blocks.mycelium) {
            return modelCubeMycelium.getFaceQuads(var3);
         }
      } else if (var6) {
         if (betterMyceliumSnow) {
            return modelCubeSnow.getFaceQuads(var3);
         }
      } else if (betterMycelium) {
         return modelCubeMycelium.getFaceQuads(var3);
      }

      return var4;
   }

   public static void update() {
      if (spritesLoaded) {
         modelCubeGrass = BlockModelUtils.makeModelCube(spriteGrass, 0);
         if (grassMultilayer) {
            IBakedModel var0 = BlockModelUtils.makeModelCube(spriteGrassSide, -1);
            modelCubeGrass = BlockModelUtils.joinModelsCube(var0, modelCubeGrass);
         }

         modelCubeMycelium = BlockModelUtils.makeModelCube(spriteMycelium, -1);
         modelCubePodzol = BlockModelUtils.makeModelCube(spritePodzol, 0);
         modelCubeSnow = BlockModelUtils.makeModelCube(spriteSnow, -1);
         modelsLoaded = true;
      }
   }
}
