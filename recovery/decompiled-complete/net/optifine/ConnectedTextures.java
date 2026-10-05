package net.optifine;

import io.netty.handler.stream.ChunkedWriteHandler$2;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import junit.runner.LoadingTestCollector;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.BlockPane;
import net.minecraft.block.BlockQuartz;
import net.minecraft.block.BlockRailPowered$2;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.block.BlockStainedGlassPane;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EffectRenderer$4;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.init.Blocks;
import net.minecraft.src.Config;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeGenBase;
import net.optifine.config.Matches;
import net.optifine.model.BlockModelUtils;
import net.optifine.model.ListQuadsOverlay;
import net.optifine.reflect.Reflector;
import net.optifine.render.RenderEnv;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.ResUtils;
import net.optifine.util.TileEntityUtils;
import org.java_websocket.SSLSocketChannel$1;

public class ConnectedTextures {
   public static BlockDir[] field_0020 = new BlockDir[]{BlockDir.NORTH, BlockDir.SOUTH, BlockDir.DOWN, BlockDir.UP};
   public static int field_0035;
   public static int field_0019;
   public static boolean multipass = false;
   public static BlockDir[] field_0007 = new BlockDir[]{BlockDir.DOWN_EAST, BlockDir.DOWN_WEST, BlockDir.UP_EAST, BlockDir.UP_WEST};
   public static BlockDir[] field_0009 = new BlockDir[]{BlockDir.DOWN_NORTH, BlockDir.DOWN_SOUTH, BlockDir.UP_NORTH, BlockDir.UP_SOUTH};
   public static BlockDir[] field_0036 = new BlockDir[]{BlockDir.WEST, BlockDir.EAST, BlockDir.SOUTH, BlockDir.NORTH};
   public static BlockDir[] field_0028 = new BlockDir[]{BlockDir.WEST, BlockDir.EAST, BlockDir.UP, BlockDir.DOWN};
   public static Map[] field_0010 = null;
   public static ConnectedProperties[][] field_0038 = (ConnectedProperties[][])null;
   public BlockRailPowered$2 field_0006;
   public static int field_0021;
   public static int field_0025;
   public static ConnectedProperties[][] field_0016 = (ConnectedProperties[][])null;
   public static BlockDir[] field_0030 = new BlockDir[]{BlockDir.SOUTH_EAST, BlockDir.SOUTH_WEST, BlockDir.NORTH_EAST, BlockDir.NORTH_WEST};
   public static BlockDir[] field_0034 = new BlockDir[]{BlockDir.SOUTH, BlockDir.NORTH, BlockDir.DOWN, BlockDir.UP};
   public static BlockDir[] field_0004 = new BlockDir[]{BlockDir.DOWN_SOUTH, BlockDir.DOWN_NORTH, BlockDir.UP_SOUTH, BlockDir.UP_NORTH};
   public static IBlockState field_0015 = Blocks.air.getDefaultState();
   public static BlockDir[] field_0023 = new BlockDir[]{BlockDir.NORTH, BlockDir.SOUTH, BlockDir.UP, BlockDir.DOWN};
   public static int field_0033;
   public ChunkedWriteHandler$2 field_0003;
   public static int field_0002;
   public EffectRenderer$4 field_0005;
   public static TextureAtlasSprite emptySprite = null;
   public static BlockDir[] field_0031 = new BlockDir[]{BlockDir.NORTH_EAST, BlockDir.NORTH_WEST, BlockDir.SOUTH_EAST, BlockDir.SOUTH_WEST};
   public static int field_0024;
   public static int field_0029;
   public static BlockDir[] field_0018 = new BlockDir[]{BlockDir.UP_SOUTH, BlockDir.UP_NORTH, BlockDir.DOWN_SOUTH, BlockDir.DOWN_NORTH};
   public static BlockDir[] field_0037 = new BlockDir[]{BlockDir.DOWN_WEST, BlockDir.DOWN_EAST, BlockDir.UP_WEST, BlockDir.UP_EAST};
   public LoadingTestCollector field_0026;
   public static BlockDir[] field_0014 = new BlockDir[]{BlockDir.UP_EAST, BlockDir.UP_WEST, BlockDir.DOWN_EAST, BlockDir.DOWN_WEST};
   public static BlockDir[] field_0008 = new BlockDir[]{BlockDir.WEST, BlockDir.EAST, BlockDir.NORTH, BlockDir.SOUTH};
   public static int field_0017;
   public static BlockDir[] field_0013 = new BlockDir[]{BlockDir.EAST, BlockDir.WEST, BlockDir.DOWN, BlockDir.UP};
   public static int field_0012;
   public static TextureAtlasSprite SPRITE_DEFAULT = new TextureAtlasSprite("<default>");
   public SSLSocketChannel$1 field_0000;
   public static BlockDir[] field_0039 = new BlockDir[]{BlockDir.WEST, BlockDir.EAST, BlockDir.DOWN, BlockDir.UP};
   public static Map[][] spriteQuadCompactMaps = (Map[][])null;
   public static Map[] field_0027 = null;

   public static TextureAtlasSprite getConnectedTextureRepeat(ConnectedProperties var0, BlockPos var1, int var2) {
      if (var0.tileIcons.length == 1) {
         return var0.tileIcons[0];
      } else {
         int var3 = var1.getX();
         int var4 = var1.getY();
         int var5 = var1.getZ();
         int var6 = 0;
         int var7 = 0;
         switch (var2) {
            case 0:
               var6 = var3;
               var7 = -var5 - 1;
               break;
            case 1:
               var6 = var3;
               var7 = var5;
               break;
            case 2:
               var6 = -var3 - 1;
               var7 = -var4;
               break;
            case 3:
               var6 = var3;
               var7 = -var4;
               break;
            case 4:
               var6 = var5;
               var7 = -var4;
               break;
            case 5:
               var6 = -var5 - 1;
               var7 = -var4;
         }

         var6 %= var0.width;
         var7 %= var0.height;
         if (var6 < 0) {
            var6 += var0.width;
         }

         if (var7 < 0) {
            var7 += var0.height;
         }

         int var8 = var7 * var0.width + var6;
         return var0.tileIcons[var8];
      }
   }

   public static boolean isNeighbour(ConnectedProperties var0, IBlockAccess var1, IBlockState var2, BlockPos var3, int var4, TextureAtlasSprite var5, int var6) {
      IBlockState var7 = var1.getBlockState(var3);
      return isNeighbour(var0, var1, var2, var3, var7, var4, var5, var6);
   }

   public static TextureAtlasSprite method_27392(
      ConnectedProperties var0, IBlockAccess var1, IBlockState var2, BlockPos var3, int var4, int var5, TextureAtlasSprite var6, int var7
   ) {
      TextureAtlasSprite[] var8 = var0.tileIcons;
      TextureAtlasSprite var9 = getConnectedTextureVertical(var0, var1, var2, var3, var4, var5, var6, var7);
      if (var9 != null && var9 != var6 && var9 != var8[3]) {
         return var9;
      } else {
         TextureAtlasSprite var10 = getConnectedTextureHorizontal(var0, var1, var2, var3, var4, var5, var6, var7);
         return var10 == var8[0] ? var8[4] : (var10 == var8[1] ? var8[5] : (var10 == var8[2] ? var8[6] : var10));
      }
   }

   public static void updateIconEmpty(TextureMap var0) {
   }

   public static int getQuartzAxis(int var0, int var1) {
      switch (var1) {
         case 3:
            return 2;
         case 4:
            return 1;
         default:
            return 0;
      }
   }

   public static synchronized BakedQuad getQuad(TextureAtlasSprite var0, BakedQuad var1) {
      if (field_0010 == null) {
         return var1;
      } else {
         int var2 = var0.getIndexInMap();
         if (var2 >= 0 && var2 < field_0010.length) {
            Object var3 = field_0010[var2];
            if (var3 == null) {
               var3 = new IdentityHashMap(1);
               field_0010[var2] = (Map)var3;
            }

            BakedQuad var4 = (BakedQuad)var3.get(var1);
            if (var4 == null) {
               var4 = makeSpriteQuad(var1, var0);
               var3.put(var1, var4);
            }

            return var4;
         } else {
            return var1;
         }
      }
   }

   public static BakedQuad[] method_27394(
      ConnectedProperties var0, IBlockAccess var1, IBlockState var2, BlockPos var3, int var4, int var5, BakedQuad var6, int var7, RenderEnv var8
   ) {
      if (!var6.isFullQuad()) {
         return null;
      } else {
         ListQuadsOverlay var9 = var8.getListQuadsOverlay(var0.layer);

         Object var10;
         try {
            TextureAtlasSprite var11 = getConnectedTextureCtm(var0, var1, var2, var3, var4, var5, var6.getSprite(), var7, var8);
            if (var11 != null) {
               var9.addQuad(getQuadFull(var11, var6, var0.tintIndex), var0.tintBlockState);
            }

            var10 = null;
         } finally {
            if (var9.size() > 0) {
               var8.setOverlaysRendered(true);
            }
         }

         return (BakedQuad[])var10;
      }
   }

   public static int getConnectedTextureCtmIndex(
      ConnectedProperties var0, IBlockAccess var1, IBlockState var2, BlockPos var3, int var4, int var5, TextureAtlasSprite var6, int var7, RenderEnv var8
   ) {
      boolean[] var9 = var8.getBorderFlags();
      switch (var5) {
         case 0:
            var9[0] = isNeighbour(var0, var1, var2, var3.west(), var5, var6, var7);
            var9[1] = isNeighbour(var0, var1, var2, var3.east(), var5, var6, var7);
            var9[2] = isNeighbour(var0, var1, var2, var3.north(), var5, var6, var7);
            var9[3] = isNeighbour(var0, var1, var2, var3.south(), var5, var6, var7);
            if (var0.innerSeams) {
               BlockPos var16 = var3.down();
               var9[0] = var9[0] && !isNeighbour(var0, var1, var2, var16.west(), var5, var6, var7);
               var9[1] = var9[1] && !isNeighbour(var0, var1, var2, var16.east(), var5, var6, var7);
               var9[2] = var9[2] && !isNeighbour(var0, var1, var2, var16.north(), var5, var6, var7);
               var9[3] = var9[3] && !isNeighbour(var0, var1, var2, var16.south(), var5, var6, var7);
            }
            break;
         case 1:
            var9[0] = isNeighbour(var0, var1, var2, var3.west(), var5, var6, var7);
            var9[1] = isNeighbour(var0, var1, var2, var3.east(), var5, var6, var7);
            var9[2] = isNeighbour(var0, var1, var2, var3.south(), var5, var6, var7);
            var9[3] = isNeighbour(var0, var1, var2, var3.north(), var5, var6, var7);
            if (var0.innerSeams) {
               BlockPos var15 = var3.up();
               var9[0] = var9[0] && !isNeighbour(var0, var1, var2, var15.west(), var5, var6, var7);
               var9[1] = var9[1] && !isNeighbour(var0, var1, var2, var15.east(), var5, var6, var7);
               var9[2] = var9[2] && !isNeighbour(var0, var1, var2, var15.south(), var5, var6, var7);
               var9[3] = var9[3] && !isNeighbour(var0, var1, var2, var15.north(), var5, var6, var7);
            }
            break;
         case 2:
            var9[0] = isNeighbour(var0, var1, var2, var3.east(), var5, var6, var7);
            var9[1] = isNeighbour(var0, var1, var2, var3.west(), var5, var6, var7);
            var9[2] = isNeighbour(var0, var1, var2, var3.down(), var5, var6, var7);
            var9[3] = isNeighbour(var0, var1, var2, var3.up(), var5, var6, var7);
            if (var0.innerSeams) {
               BlockPos var14 = var3.north();
               var9[0] = var9[0] && !isNeighbour(var0, var1, var2, var14.east(), var5, var6, var7);
               var9[1] = var9[1] && !isNeighbour(var0, var1, var2, var14.west(), var5, var6, var7);
               var9[2] = var9[2] && !isNeighbour(var0, var1, var2, var14.down(), var5, var6, var7);
               var9[3] = var9[3] && !isNeighbour(var0, var1, var2, var14.up(), var5, var6, var7);
            }

            if (var4 == 1) {
               switchValues(0, 1, var9);
               switchValues(2, 3, var9);
            }
            break;
         case 3:
            var9[0] = isNeighbour(var0, var1, var2, var3.west(), var5, var6, var7);
            var9[1] = isNeighbour(var0, var1, var2, var3.east(), var5, var6, var7);
            var9[2] = isNeighbour(var0, var1, var2, var3.down(), var5, var6, var7);
            var9[3] = isNeighbour(var0, var1, var2, var3.up(), var5, var6, var7);
            if (var0.innerSeams) {
               BlockPos var13 = var3.south();
               var9[0] = var9[0] && !isNeighbour(var0, var1, var2, var13.west(), var5, var6, var7);
               var9[1] = var9[1] && !isNeighbour(var0, var1, var2, var13.east(), var5, var6, var7);
               var9[2] = var9[2] && !isNeighbour(var0, var1, var2, var13.down(), var5, var6, var7);
               var9[3] = var9[3] && !isNeighbour(var0, var1, var2, var13.up(), var5, var6, var7);
            }
            break;
         case 4:
            var9[0] = isNeighbour(var0, var1, var2, var3.north(), var5, var6, var7);
            var9[1] = isNeighbour(var0, var1, var2, var3.south(), var5, var6, var7);
            var9[2] = isNeighbour(var0, var1, var2, var3.down(), var5, var6, var7);
            var9[3] = isNeighbour(var0, var1, var2, var3.up(), var5, var6, var7);
            if (var0.innerSeams) {
               BlockPos var12 = var3.west();
               var9[0] = var9[0] && !isNeighbour(var0, var1, var2, var12.north(), var5, var6, var7);
               var9[1] = var9[1] && !isNeighbour(var0, var1, var2, var12.south(), var5, var6, var7);
               var9[2] = var9[2] && !isNeighbour(var0, var1, var2, var12.down(), var5, var6, var7);
               var9[3] = var9[3] && !isNeighbour(var0, var1, var2, var12.up(), var5, var6, var7);
            }
            break;
         case 5:
            var9[0] = isNeighbour(var0, var1, var2, var3.south(), var5, var6, var7);
            var9[1] = isNeighbour(var0, var1, var2, var3.north(), var5, var6, var7);
            var9[2] = isNeighbour(var0, var1, var2, var3.down(), var5, var6, var7);
            var9[3] = isNeighbour(var0, var1, var2, var3.up(), var5, var6, var7);
            if (var0.innerSeams) {
               BlockPos var10 = var3.east();
               var9[0] = var9[0] && !isNeighbour(var0, var1, var2, var10.south(), var5, var6, var7);
               var9[1] = var9[1] && !isNeighbour(var0, var1, var2, var10.north(), var5, var6, var7);
               var9[2] = var9[2] && !isNeighbour(var0, var1, var2, var10.down(), var5, var6, var7);
               var9[3] = var9[3] && !isNeighbour(var0, var1, var2, var10.up(), var5, var6, var7);
            }

            if (var4 == 2) {
               switchValues(0, 1, var9);
               switchValues(2, 3, var9);
            }
      }

      byte var17 = 0;
      if (var9[0] & !var9[1] & !var9[2] & !var9[3]) {
         var17 = 3;
      } else if (!var9[0] & var9[1] & !var9[2] & !var9[3]) {
         var17 = 1;
      } else if (!var9[0] & !var9[1] & var9[2] & !var9[3]) {
         var17 = 12;
      } else if (!var9[0] & !var9[1] & !var9[2] & var9[3]) {
         var17 = 36;
      } else if (var9[0] & var9[1] & !var9[2] & !var9[3]) {
         var17 = 2;
      } else if (!var9[0] & !var9[1] & var9[2] & var9[3]) {
         var17 = 24;
      } else if (var9[0] & !var9[1] & var9[2] & !var9[3]) {
         var17 = 15;
      } else if (var9[0] & !var9[1] & !var9[2] & var9[3]) {
         var17 = 39;
      } else if (!var9[0] & var9[1] & var9[2] & !var9[3]) {
         var17 = 13;
      } else if (!var9[0] & var9[1] & !var9[2] & var9[3]) {
         var17 = 37;
      } else if (!var9[0] & var9[1] & var9[2] & var9[3]) {
         var17 = 25;
      } else if (var9[0] & !var9[1] & var9[2] & var9[3]) {
         var17 = 27;
      } else if (var9[0] & var9[1] & !var9[2] & var9[3]) {
         var17 = 38;
      } else if (var9[0] & var9[1] & var9[2] & !var9[3]) {
         var17 = 14;
      } else if (var9[0] & var9[1] & var9[2] & var9[3]) {
         var17 = 26;
      }

      if (var17 == 0) {
         return var17;
      } else if (!Config.isConnectedTexturesFancy()) {
         return var17;
      } else {
         switch (var5) {
            case 0:
               var9[0] = !isNeighbour(var0, var1, var2, var3.east().north(), var5, var6, var7);
               var9[1] = !isNeighbour(var0, var1, var2, var3.west().north(), var5, var6, var7);
               var9[2] = !isNeighbour(var0, var1, var2, var3.east().south(), var5, var6, var7);
               var9[3] = !isNeighbour(var0, var1, var2, var3.west().south(), var5, var6, var7);
               if (var0.innerSeams) {
                  BlockPos var22 = var3.down();
                  var9[0] = var9[0] || isNeighbour(var0, var1, var2, var22.east().north(), var5, var6, var7);
                  var9[1] = var9[1] || isNeighbour(var0, var1, var2, var22.west().north(), var5, var6, var7);
                  var9[2] = var9[2] || isNeighbour(var0, var1, var2, var22.east().south(), var5, var6, var7);
                  var9[3] = var9[3] || isNeighbour(var0, var1, var2, var22.west().south(), var5, var6, var7);
               }
               break;
            case 1:
               var9[0] = !isNeighbour(var0, var1, var2, var3.east().south(), var5, var6, var7);
               var9[1] = !isNeighbour(var0, var1, var2, var3.west().south(), var5, var6, var7);
               var9[2] = !isNeighbour(var0, var1, var2, var3.east().north(), var5, var6, var7);
               var9[3] = !isNeighbour(var0, var1, var2, var3.west().north(), var5, var6, var7);
               if (var0.innerSeams) {
                  BlockPos var21 = var3.up();
                  var9[0] = var9[0] || isNeighbour(var0, var1, var2, var21.east().south(), var5, var6, var7);
                  var9[1] = var9[1] || isNeighbour(var0, var1, var2, var21.west().south(), var5, var6, var7);
                  var9[2] = var9[2] || isNeighbour(var0, var1, var2, var21.east().north(), var5, var6, var7);
                  var9[3] = var9[3] || isNeighbour(var0, var1, var2, var21.west().north(), var5, var6, var7);
               }
               break;
            case 2:
               var9[0] = !isNeighbour(var0, var1, var2, var3.west().down(), var5, var6, var7);
               var9[1] = !isNeighbour(var0, var1, var2, var3.east().down(), var5, var6, var7);
               var9[2] = !isNeighbour(var0, var1, var2, var3.west().up(), var5, var6, var7);
               var9[3] = !isNeighbour(var0, var1, var2, var3.east().up(), var5, var6, var7);
               if (var0.innerSeams) {
                  BlockPos var20 = var3.north();
                  var9[0] = var9[0] || isNeighbour(var0, var1, var2, var20.west().down(), var5, var6, var7);
                  var9[1] = var9[1] || isNeighbour(var0, var1, var2, var20.east().down(), var5, var6, var7);
                  var9[2] = var9[2] || isNeighbour(var0, var1, var2, var20.west().up(), var5, var6, var7);
                  var9[3] = var9[3] || isNeighbour(var0, var1, var2, var20.east().up(), var5, var6, var7);
               }

               if (var4 == 1) {
                  switchValues(0, 3, var9);
                  switchValues(1, 2, var9);
               }
               break;
            case 3:
               var9[0] = !isNeighbour(var0, var1, var2, var3.east().down(), var5, var6, var7);
               var9[1] = !isNeighbour(var0, var1, var2, var3.west().down(), var5, var6, var7);
               var9[2] = !isNeighbour(var0, var1, var2, var3.east().up(), var5, var6, var7);
               var9[3] = !isNeighbour(var0, var1, var2, var3.west().up(), var5, var6, var7);
               if (var0.innerSeams) {
                  BlockPos var19 = var3.south();
                  var9[0] = var9[0] || isNeighbour(var0, var1, var2, var19.east().down(), var5, var6, var7);
                  var9[1] = var9[1] || isNeighbour(var0, var1, var2, var19.west().down(), var5, var6, var7);
                  var9[2] = var9[2] || isNeighbour(var0, var1, var2, var19.east().up(), var5, var6, var7);
                  var9[3] = var9[3] || isNeighbour(var0, var1, var2, var19.west().up(), var5, var6, var7);
               }
               break;
            case 4:
               var9[0] = !isNeighbour(var0, var1, var2, var3.down().south(), var5, var6, var7);
               var9[1] = !isNeighbour(var0, var1, var2, var3.down().north(), var5, var6, var7);
               var9[2] = !isNeighbour(var0, var1, var2, var3.up().south(), var5, var6, var7);
               var9[3] = !isNeighbour(var0, var1, var2, var3.up().north(), var5, var6, var7);
               if (var0.innerSeams) {
                  BlockPos var18 = var3.west();
                  var9[0] = var9[0] || isNeighbour(var0, var1, var2, var18.down().south(), var5, var6, var7);
                  var9[1] = var9[1] || isNeighbour(var0, var1, var2, var18.down().north(), var5, var6, var7);
                  var9[2] = var9[2] || isNeighbour(var0, var1, var2, var18.up().south(), var5, var6, var7);
                  var9[3] = var9[3] || isNeighbour(var0, var1, var2, var18.up().north(), var5, var6, var7);
               }
               break;
            case 5:
               var9[0] = !isNeighbour(var0, var1, var2, var3.down().north(), var5, var6, var7);
               var9[1] = !isNeighbour(var0, var1, var2, var3.down().south(), var5, var6, var7);
               var9[2] = !isNeighbour(var0, var1, var2, var3.up().north(), var5, var6, var7);
               var9[3] = !isNeighbour(var0, var1, var2, var3.up().south(), var5, var6, var7);
               if (var0.innerSeams) {
                  BlockPos var11 = var3.east();
                  var9[0] = var9[0] || isNeighbour(var0, var1, var2, var11.down().north(), var5, var6, var7);
                  var9[1] = var9[1] || isNeighbour(var0, var1, var2, var11.down().south(), var5, var6, var7);
                  var9[2] = var9[2] || isNeighbour(var0, var1, var2, var11.up().north(), var5, var6, var7);
                  var9[3] = var9[3] || isNeighbour(var0, var1, var2, var11.up().south(), var5, var6, var7);
               }

               if (var4 == 2) {
                  switchValues(0, 3, var9);
                  switchValues(1, 2, var9);
               }
         }

         if (var17 == 13 && var9[0]) {
            var17 = 4;
         } else if (var17 == 15 && var9[1]) {
            var17 = 5;
         } else if (var17 == 37 && var9[2]) {
            var17 = 16;
         } else if (var17 == 39 && var9[3]) {
            var17 = 17;
         } else if (var17 == 14 && var9[0] && var9[1]) {
            var17 = 7;
         } else if (var17 == 25 && var9[0] && var9[2]) {
            var17 = 6;
         } else if (var17 == 27 && var9[3] && var9[1]) {
            var17 = 19;
         } else if (var17 == 38 && var9[3] && var9[2]) {
            var17 = 18;
         } else if (var17 == 14 && !var9[0] && var9[1]) {
            var17 = 31;
         } else if (var17 == 25 && var9[0] && !var9[2]) {
            var17 = 30;
         } else if (var17 == 27 && !var9[3] && var9[1]) {
            var17 = 41;
         } else if (var17 == 38 && var9[3] && !var9[2]) {
            var17 = 40;
         } else if (var17 == 14 && var9[0] && !var9[1]) {
            var17 = 29;
         } else if (var17 == 25 && !var9[0] && var9[2]) {
            var17 = 28;
         } else if (var17 == 27 && var9[3] && !var9[1]) {
            var17 = 43;
         } else if (var17 == 38 && !var9[3] && var9[2]) {
            var17 = 42;
         } else if (var17 == 26 && var9[0] && var9[1] && var9[2] && var9[3]) {
            var17 = 46;
         } else if (var17 == 26 && !var9[0] && var9[1] && var9[2] && var9[3]) {
            var17 = 9;
         } else if (var17 == 26 && var9[0] && !var9[1] && var9[2] && var9[3]) {
            var17 = 21;
         } else if (var17 == 26 && var9[0] && var9[1] && !var9[2] && var9[3]) {
            var17 = 8;
         } else if (var17 == 26 && var9[0] && var9[1] && var9[2] && !var9[3]) {
            var17 = 20;
         } else if (var17 == 26 && var9[0] && var9[1] && !var9[2] && !var9[3]) {
            var17 = 11;
         } else if (var17 == 26 && !var9[0] && !var9[1] && var9[2] && var9[3]) {
            var17 = 22;
         } else if (var17 == 26 && !var9[0] && var9[1] && !var9[2] && var9[3]) {
            var17 = 23;
         } else if (var17 == 26 && var9[0] && !var9[1] && var9[2] && !var9[3]) {
            var17 = 10;
         } else if (var17 == 26 && var9[0] && !var9[1] && !var9[2] && var9[3]) {
            var17 = 34;
         } else if (var17 == 26 && !var9[0] && var9[1] && var9[2] && !var9[3]) {
            var17 = 35;
         } else if (var17 == 26 && var9[0] && !var9[1] && !var9[2] && !var9[3]) {
            var17 = 32;
         } else if (var17 == 26 && !var9[0] && var9[1] && !var9[2] && !var9[3]) {
            var17 = 33;
         } else if (var17 == 26 && !var9[0] && !var9[1] && var9[2] && !var9[3]) {
            var17 = 44;
         } else if (var17 == 26 && !var9[0] && !var9[1] && !var9[2] && var9[3]) {
            var17 = 45;
         }

         return var17;
      }
   }

   public static EnumFacing getFacing(int var0) {
      switch (var0) {
         case 0:
            return EnumFacing.DOWN;
         case 1:
            return EnumFacing.UP;
         case 2:
            return EnumFacing.NORTH;
         case 3:
            return EnumFacing.SOUTH;
         case 4:
            return EnumFacing.WEST;
         case 5:
            return EnumFacing.EAST;
         default:
            return EnumFacing.UP;
      }
   }

   public static TextureAtlasSprite getConnectedTextureHorizontal(
      ConnectedProperties var0, IBlockAccess var1, IBlockState var2, BlockPos var3, int var4, int var5, TextureAtlasSprite var6, int var7
   ) {
      boolean var8;
      boolean var9;
      var8 = false;
      var9 = false;
      label46:
      switch (var4) {
         case 0:
            switch (var5) {
               case 0:
                  var8 = isNeighbour(var0, var1, var2, var3.west(), var5, var6, var7);
                  var9 = isNeighbour(var0, var1, var2, var3.east(), var5, var6, var7);
                  break label46;
               case 1:
                  var8 = isNeighbour(var0, var1, var2, var3.west(), var5, var6, var7);
                  var9 = isNeighbour(var0, var1, var2, var3.east(), var5, var6, var7);
                  break label46;
               case 2:
                  var8 = isNeighbour(var0, var1, var2, var3.east(), var5, var6, var7);
                  var9 = isNeighbour(var0, var1, var2, var3.west(), var5, var6, var7);
                  break label46;
               case 3:
                  var8 = isNeighbour(var0, var1, var2, var3.west(), var5, var6, var7);
                  var9 = isNeighbour(var0, var1, var2, var3.east(), var5, var6, var7);
                  break label46;
               case 4:
                  var8 = isNeighbour(var0, var1, var2, var3.north(), var5, var6, var7);
                  var9 = isNeighbour(var0, var1, var2, var3.south(), var5, var6, var7);
                  break label46;
               case 5:
                  var8 = isNeighbour(var0, var1, var2, var3.south(), var5, var6, var7);
                  var9 = isNeighbour(var0, var1, var2, var3.north(), var5, var6, var7);
               default:
                  break label46;
            }
         case 1:
            switch (var5) {
               case 0:
                  var8 = isNeighbour(var0, var1, var2, var3.east(), var5, var6, var7);
                  var9 = isNeighbour(var0, var1, var2, var3.west(), var5, var6, var7);
                  break label46;
               case 1:
                  var8 = isNeighbour(var0, var1, var2, var3.west(), var5, var6, var7);
                  var9 = isNeighbour(var0, var1, var2, var3.east(), var5, var6, var7);
                  break label46;
               case 2:
                  var8 = isNeighbour(var0, var1, var2, var3.west(), var5, var6, var7);
                  var9 = isNeighbour(var0, var1, var2, var3.east(), var5, var6, var7);
                  break label46;
               case 3:
                  var8 = isNeighbour(var0, var1, var2, var3.west(), var5, var6, var7);
                  var9 = isNeighbour(var0, var1, var2, var3.east(), var5, var6, var7);
                  break label46;
               case 4:
                  var8 = isNeighbour(var0, var1, var2, var3.down(), var5, var6, var7);
                  var9 = isNeighbour(var0, var1, var2, var3.up(), var5, var6, var7);
                  break label46;
               case 5:
                  var8 = isNeighbour(var0, var1, var2, var3.up(), var5, var6, var7);
                  var9 = isNeighbour(var0, var1, var2, var3.down(), var5, var6, var7);
               default:
                  break label46;
            }
         case 2:
            switch (var5) {
               case 0:
                  var8 = isNeighbour(var0, var1, var2, var3.south(), var5, var6, var7);
                  var9 = isNeighbour(var0, var1, var2, var3.north(), var5, var6, var7);
                  break;
               case 1:
                  var8 = isNeighbour(var0, var1, var2, var3.north(), var5, var6, var7);
                  var9 = isNeighbour(var0, var1, var2, var3.south(), var5, var6, var7);
                  break;
               case 2:
                  var8 = isNeighbour(var0, var1, var2, var3.down(), var5, var6, var7);
                  var9 = isNeighbour(var0, var1, var2, var3.up(), var5, var6, var7);
                  break;
               case 3:
                  var8 = isNeighbour(var0, var1, var2, var3.up(), var5, var6, var7);
                  var9 = isNeighbour(var0, var1, var2, var3.down(), var5, var6, var7);
                  break;
               case 4:
                  var8 = isNeighbour(var0, var1, var2, var3.north(), var5, var6, var7);
                  var9 = isNeighbour(var0, var1, var2, var3.south(), var5, var6, var7);
                  break;
               case 5:
                  var8 = isNeighbour(var0, var1, var2, var3.north(), var5, var6, var7);
                  var9 = isNeighbour(var0, var1, var2, var3.south(), var5, var6, var7);
            }
      }

      byte var10 = 3;
      if (var8) {
         if (var9) {
            var10 = 1;
         } else {
            var10 = 2;
         }
      } else if (var9) {
         var10 = 0;
      } else {
         var10 = 3;
      }

      return var0.tileIcons[var10];
   }

   public static void updateIcons(TextureMap var0, IResourcePack var1) {
      String[] var2 = ResUtils.collectFiles(var1, "mcpatcher/ctm/", ".properties", getDefaultCtmPaths());
      Arrays.sort(var2);
      List var3 = makePropertyList(field_0016);
      List var4 = makePropertyList(field_0038);

      for (int var5 = 0; var5 < var2.length; var5++) {
         String var6 = var2[var5];
         Config.dbg("ConnectedTextures: " + var6);

         try {
            ResourceLocation var7 = new ResourceLocation(var6);
            InputStream var8 = var1.getInputStream(var7);
            if (var8 == null) {
               Config.warn("ConnectedTextures file not found: " + var6);
            } else {
               PropertiesOrdered var9 = new PropertiesOrdered();
               var9.load(var8);
               var8.close();
               ConnectedProperties var10 = new ConnectedProperties(var9, var6);
               if (var10.method_21599(var6)) {
                  var10.updateIcons(var0);
                  method_27389(var10, var3);
                  method_27413(var10, var4);
               }
            }
         } catch (FileNotFoundException var11) {
            Config.warn("ConnectedTextures file not found: " + var6);
         } catch (Exception var12) {
            var12.printStackTrace();
         }
      }

      field_0038 = propertyListToArray(var4);
      field_0016 = propertyListToArray(var3);
      multipass = detectMultipass();
      Config.dbg("Multipass connected textures: " + multipass);
   }

   public static BakedQuad makeSpriteQuad(BakedQuad var0, TextureAtlasSprite var1) {
      int[] var2 = (int[])var0.getVertexData().clone();
      TextureAtlasSprite var3 = var0.getSprite();

      for (int var4 = 0; var4 < 4; var4++) {
         fixVertex(var2, var4, var3, var1);
      }

      return new BakedQuad(var2, var0.getTintIndex(), var0.getFace(), var1);
   }

   public static int getSide(EnumFacing var0) {
      if (var0 == null) {
         return -1;
      } else {
         switch (ConnectedTextures$1.$SwitchMap$net$minecraft$util$EnumFacing[var0.ordinal()]) {
            case 1:
               return 0;
            case 2:
               return 1;
            case 3:
               return 5;
            case 4:
               return 4;
            case 5:
               return 2;
            case 6:
               return 3;
            default:
               return -1;
         }
      }
   }

   public static int fixSideByAxis(int var0, int var1) {
      switch (var1) {
         case 0:
            return var0;
         case 1:
            switch (var0) {
               case 0:
                  return 2;
               case 1:
                  return 3;
               case 2:
                  return 1;
               case 3:
                  return 0;
               default:
                  return var0;
            }
         case 2:
            switch (var0) {
               case 0:
                  return 4;
               case 1:
                  return 5;
               case 2:
               case 3:
               default:
                  return var0;
               case 4:
                  return 1;
               case 5:
                  return 0;
            }
         default:
            return var0;
      }
   }

   public static BakedQuad[] getConnectedTextureOverlayRepeat(ConnectedProperties var0, BlockPos var1, int var2, BakedQuad var3, RenderEnv var4) {
      if (!var3.isFullQuad()) {
         return null;
      } else {
         ListQuadsOverlay var5 = var4.getListQuadsOverlay(var0.layer);

         Object var6;
         try {
            TextureAtlasSprite var7 = getConnectedTextureRepeat(var0, var1, var2);
            if (var7 != null) {
               var5.addQuad(getQuadFull(var7, var3, var0.tintIndex), var0.tintBlockState);
            }

            var6 = null;
         } finally {
            if (var5.size() > 0) {
               var4.setOverlaysRendered(true);
            }
         }

         return (BakedQuad[])var6;
      }
   }

   public static boolean detectMultipass() {
      ArrayList var0 = new ArrayList();

      for (int var1 = 0; var1 < field_0016.length; var1++) {
         ConnectedProperties[] var2 = field_0016[var1];
         if (var2 != null) {
            var0.addAll(Arrays.asList(var2));
         }
      }

      for (int var6 = 0; var6 < field_0038.length; var6++) {
         ConnectedProperties[] var8 = field_0038[var6];
         if (var8 != null) {
            var0.addAll(Arrays.asList(var8));
         }
      }

      ConnectedProperties[] var7 = var0.toArray(new ConnectedProperties[var0.size()]);
      HashSet var9 = new HashSet();
      HashSet var3 = new HashSet();

      for (int var4 = 0; var4 < var7.length; var4++) {
         ConnectedProperties var5 = var7[var4];
         if (var5.matchTileIcons != null) {
            var9.addAll(Arrays.asList(var5.matchTileIcons));
         }

         if (var5.tileIcons != null) {
            var3.addAll(Arrays.asList(var5.tileIcons));
         }
      }

      var9.retainAll(var3);
      return !var9.isEmpty();
   }

   public static synchronized BakedQuad[] getConnectedTextureCtmCompact(
      ConnectedProperties var0, IBlockAccess var1, IBlockState var2, BlockPos var3, int var4, int var5, BakedQuad var6, int var7, RenderEnv var8
   ) {
      TextureAtlasSprite var9 = var6.getSprite();
      int var10 = getConnectedTextureCtmIndex(var0, var1, var2, var3, var4, var5, var9, var7, var8);
      return ConnectedTexturesCompact.getConnectedTextureCtmCompact(var10, var0, var5, var6, var8);
   }

   public static void method_27389(ConnectedProperties var0, List var1) {
      if (var0.matchTileIcons != null) {
         for (int var2 = 0; var2 < var0.matchTileIcons.length; var2++) {
            TextureAtlasSprite var3 = var0.matchTileIcons[var2];
            if (!(var3 instanceof TextureAtlasSprite)) {
               Config.warn("TextureAtlasSprite is not TextureAtlasSprite: " + var3 + ", name: " + var3.getIconName());
            } else {
               int var4 = var3.getIndexInMap();
               if (var4 < 0) {
                  Config.warn("Invalid tile ID: " + var4 + ", icon: " + var3.getIconName());
               } else {
                  addToList(var0, var1, var4);
               }
            }
         }
      }
   }

   public static TextureAtlasSprite getConnectedTextureCtm(
      ConnectedProperties var0, IBlockAccess var1, IBlockState var2, BlockPos var3, int var4, int var5, TextureAtlasSprite var6, int var7, RenderEnv var8
   ) {
      int var9 = getConnectedTextureCtmIndex(var0, var1, var2, var3, var4, var5, var6, var7, var8);
      return var0.tileIcons[var9];
   }

   public static BakedQuad[] getConnectedTexture(
      ConnectedProperties var0, IBlockAccess var1, BlockStateBase var2, BlockPos var3, int var4, BakedQuad var5, int var6, RenderEnv var7
   ) {
      int var8 = 0;
      int var9 = var2.getMetadata();
      int var10 = var9;
      Block var11 = var2.getBlock();
      if (var11 instanceof BlockRotatedPillar) {
         var8 = getWoodAxis(var4, var9);
         if (var0.getMetadataMax() <= 3) {
            var10 = var9 & 3;
         }
      }

      if (var11 instanceof BlockQuartz) {
         var8 = getQuartzAxis(var4, var9);
         if (var0.getMetadataMax() <= 2 && var10 > 2) {
            var10 = 2;
         }
      }

      if (!var0.matchesBlock(var2.getBlockId(), var10)) {
         return null;
      } else {
         if (var4 >= 0 && var0.faces != 63) {
            int var12 = var4;
            if (var8 != 0) {
               var12 = fixSideByAxis(var4, var8);
            }

            if ((1 << var12 & var0.faces) == 0) {
               return null;
            }
         }

         int var14 = var3.getY();
         if (var0.heights != null && !var0.heights.isInRange(var14)) {
            return null;
         } else {
            if (var0.biomes != null) {
               BiomeGenBase var13 = var1.getBiomeGenForCoords(var3);
               if (!var0.matchesBiome(var13)) {
                  return null;
               }
            }

            if (var0.nbtName != null) {
               String var15 = TileEntityUtils.getTileEntityName(var1, var3);
               if (!var0.nbtName.matchesValue(var15)) {
                  return null;
               }
            }

            TextureAtlasSprite var16 = var5.getSprite();
            switch (var0.method) {
               case 1:
                  return getQuads(getConnectedTextureCtm(var0, var1, var2, var3, var8, var4, var16, var9, var7), var5, var7);
               case 2:
                  return getQuads(getConnectedTextureHorizontal(var0, var1, var2, var3, var8, var4, var16, var9), var5, var7);
               case 3:
                  return getQuads(getConnectedTextureTop(var0, var1, var2, var3, var8, var4, var16, var9), var5, var7);
               case 4:
                  return getQuads(getConnectedTextureRandom(var0, var1, var2, var3, var4), var5, var7);
               case 5:
                  return getQuads(getConnectedTextureRepeat(var0, var3, var4), var5, var7);
               case 6:
                  return getQuads(getConnectedTextureVertical(var0, var1, var2, var3, var8, var4, var16, var9), var5, var7);
               case 7:
                  return getQuads(getConnectedTextureFixed(var0), var5, var7);
               case 8:
                  return getQuads(method_27370(var0, var1, var2, var3, var8, var4, var16, var9), var5, var7);
               case 9:
                  return getQuads(method_27392(var0, var1, var2, var3, var8, var4, var16, var9), var5, var7);
               case 10:
                  if (var6 == 0) {
                     return getConnectedTextureCtmCompact(var0, var1, var2, var3, var8, var4, var5, var9, var7);
                  }
               default:
                  return null;
               case 11:
                  return method_27374(var0, var1, var2, var3, var8, var4, var5, var9, var7);
               case 12:
                  return getConnectedTextureOverlayFixed(var0, var5, var7);
               case 13:
                  return getConnectedTextureOverlayRandom(var0, var1, var2, var3, var4, var5, var7);
               case 14:
                  return getConnectedTextureOverlayRepeat(var0, var3, var4, var5, var7);
               case 15:
                  return method_27394(var0, var1, var2, var3, var8, var4, var5, var9, var7);
            }
         }
      }
   }

   public static String[] getDefaultCtmPaths() {
      ArrayList var0 = new ArrayList();
      String var1 = "mcpatcher/ctm/default/";
      if (Config.isFromDefaultResourcePack(new ResourceLocation("textures/blocks/glass.png"))) {
         var0.add(var1 + "glass.properties");
         var0.add(var1 + "glasspane.properties");
      }

      if (Config.isFromDefaultResourcePack(new ResourceLocation("textures/blocks/bookshelf.png"))) {
         var0.add(var1 + "bookshelf.properties");
      }

      if (Config.isFromDefaultResourcePack(new ResourceLocation("textures/blocks/sandstone_normal.png"))) {
         var0.add(var1 + "sandstone.properties");
      }

      String[] var2 = new String[]{
         "white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "silver", "cyan", "purple", "blue", "brown", "green", "red", "black"
      };

      for (int var3 = 0; var3 < var2.length; var3++) {
         String var4 = var2[var3];
         if (Config.isFromDefaultResourcePack(new ResourceLocation("textures/blocks/glass_" + var4 + ".png"))) {
            var0.add(var1 + var3 + "_glass_" + var4 + "/glass_" + var4 + ".properties");
            var0.add(var1 + var3 + "_glass_" + var4 + "/glass_pane_" + var4 + ".properties");
         }
      }

      return var0.toArray(new String[var0.size()]);
   }

   public static synchronized BakedQuad getQuadFull(TextureAtlasSprite var0, BakedQuad var1, int var2) {
      if (field_0027 == null) {
         return null;
      } else if (var0 == null) {
         return null;
      } else {
         int var3 = var0.getIndexInMap();
         if (var3 >= 0 && var3 < field_0027.length) {
            Object var4 = field_0027[var3];
            if (var4 == null) {
               var4 = new EnumMap<>(EnumFacing.class);
               field_0027[var3] = (Map)var4;
            }

            EnumFacing var5 = var1.getFace();
            BakedQuad var6 = (BakedQuad)var4.get(var5);
            if (var6 == null) {
               var6 = BlockModelUtils.makeBakedQuad(var5, var0, var2);
               var4.put(var5, var6);
            }

            return var6;
         } else {
            return null;
         }
      }
   }

   public static void updateIcons(TextureMap var0) {
      field_0038 = (ConnectedProperties[][])null;
      field_0016 = (ConnectedProperties[][])null;
      field_0010 = null;
      spriteQuadCompactMaps = (Map[][])null;
      if (Config.isConnectedTextures()) {
         IResourcePack[] var1 = Config.getResourcePacks();

         for (int var2 = var1.length - 1; var2 >= 0; var2--) {
            IResourcePack var3 = var1[var2];
            updateIcons(var0, var3);
         }

         updateIcons(var0, Config.getDefaultResourcePack());
         ResourceLocation var4 = new ResourceLocation("mcpatcher/ctm/default/empty");
         emptySprite = var0.registerSprite(var4);
         field_0010 = new Map[var0.getCountRegisteredSprites() + 1];
         field_0027 = new Map[var0.getCountRegisteredSprites() + 1];
         spriteQuadCompactMaps = new Map[var0.getCountRegisteredSprites() + 1][];
         if (field_0038.length <= 0) {
            field_0038 = (ConnectedProperties[][])null;
         }

         if (field_0016.length <= 0) {
            field_0016 = (ConnectedProperties[][])null;
         }
      }
   }

   public static boolean method_27417(ConnectedProperties var0, IBlockAccess var1, IBlockState var2, BlockPos var3, int var4, TextureAtlasSprite var5, int var6) {
      IBlockState var7 = var1.getBlockState(var3);
      if (var7 == field_0015) {
         return false;
      } else {
         if (var0.matchBlocks != null && var7 instanceof BlockStateBase) {
            BlockStateBase var8 = (BlockStateBase)var7;
            if (!var0.matchesBlock(var8.getBlockId(), var8.getMetadata())) {
               return false;
            }
         }

         if (var0.matchTileIcons != null) {
            TextureAtlasSprite var9 = getNeighbourIcon(var1, var2, var3, var7, var4);
            if (var9 != var5) {
               return false;
            }
         }

         IBlockState var10 = var1.getBlockState(var3.a(getFacing(var4)));
         return var10.getBlock().isOpaqueCube() ? false : var4 != 1 || var10.getBlock() != Blocks.snow_layer;
      }
   }

   public static boolean method_27375(ConnectedProperties var0, IBlockAccess var1, IBlockState var2, BlockPos var3, int var4, TextureAtlasSprite var5, int var6) {
      IBlockState var7 = var1.getBlockState(var3);
      if (!isFullCubeModel(var7)) {
         return false;
      } else {
         if (var0.connectBlocks != null) {
            BlockStateBase var8 = (BlockStateBase)var7;
            if (!Matches.block(var8.getBlockId(), var8.getMetadata(), var0.connectBlocks)) {
               return false;
            }
         }

         if (var0.connectTileIcons != null) {
            TextureAtlasSprite var9 = getNeighbourIcon(var1, var2, var3, var7, var4);
            if (!Config.isSameOne(var9, var0.connectTileIcons)) {
               return false;
            }
         }

         IBlockState var10 = var1.getBlockState(var3.a(getFacing(var4)));
         return var10.getBlock().isOpaqueCube()
            ? false
            : (var4 == 1 && var10.getBlock() == Blocks.snow_layer ? false : !isNeighbour(var0, var1, var2, var3, var7, var4, var5, var6));
      }
   }

   public static int getWoodAxis(int var0, int var1) {
      int var2 = (var1 & 12) >> 2;
      switch (var2) {
         case 1:
            return 2;
         case 2:
            return 1;
         default:
            return 0;
      }
   }

   public static TextureAtlasSprite getConnectedTextureFixed(ConnectedProperties var0) {
      return var0.tileIcons[0];
   }

   public static TextureAtlasSprite getConnectedTextureRandom(ConnectedProperties var0, IBlockAccess var1, BlockStateBase var2, BlockPos var3, int var4) {
      if (var0.tileIcons.length == 1) {
         return var0.tileIcons[0];
      } else {
         int var5 = var4 / var0.symmetry * var0.symmetry;
         if (var0.linked) {
            BlockPos var6 = var3.down();

            for (IBlockState var7 = var1.getBlockState(var6); var7.getBlock() == var2.getBlock(); var7 = var1.getBlockState(var6)) {
               var3 = var6;
               var6 = var6.down();
               if (var6.getY() < 0) {
                  break;
               }
            }
         }

         int var11 = Config.getRandom(var3, var5) & 2147483647;

         for (int var12 = 0; var12 < var0.randomLoops; var12++) {
            var11 = Config.intHash(var11);
         }

         int var13 = 0;
         if (var0.weights == null) {
            var13 = var11 % var0.tileIcons.length;
         } else {
            int var8 = var11 % var0.sumAllWeights;
            int[] var9 = var0.sumWeights;

            for (int var10 = 0; var10 < var9.length; var10++) {
               if (var8 < var9[var10]) {
                  var13 = var10;
                  break;
               }
            }
         }

         return var0.tileIcons[var13];
      }
   }

   public static TextureAtlasSprite getConnectedTextureVertical(
      ConnectedProperties var0, IBlockAccess var1, IBlockState var2, BlockPos var3, int var4, int var5, TextureAtlasSprite var6, int var7
   ) {
      boolean var8 = false;
      boolean var9 = false;
      switch (var4) {
         case 0:
            if (var5 == 1) {
               var8 = isNeighbour(var0, var1, var2, var3.south(), var5, var6, var7);
               var9 = isNeighbour(var0, var1, var2, var3.north(), var5, var6, var7);
            } else if (var5 == 0) {
               var8 = isNeighbour(var0, var1, var2, var3.north(), var5, var6, var7);
               var9 = isNeighbour(var0, var1, var2, var3.south(), var5, var6, var7);
            } else {
               var8 = isNeighbour(var0, var1, var2, var3.down(), var5, var6, var7);
               var9 = isNeighbour(var0, var1, var2, var3.up(), var5, var6, var7);
            }
            break;
         case 1:
            if (var5 == 3) {
               var8 = isNeighbour(var0, var1, var2, var3.down(), var5, var6, var7);
               var9 = isNeighbour(var0, var1, var2, var3.up(), var5, var6, var7);
            } else if (var5 == 2) {
               var8 = isNeighbour(var0, var1, var2, var3.up(), var5, var6, var7);
               var9 = isNeighbour(var0, var1, var2, var3.down(), var5, var6, var7);
            } else {
               var8 = isNeighbour(var0, var1, var2, var3.south(), var5, var6, var7);
               var9 = isNeighbour(var0, var1, var2, var3.north(), var5, var6, var7);
            }
            break;
         case 2:
            if (var5 == 5) {
               var8 = isNeighbour(var0, var1, var2, var3.up(), var5, var6, var7);
               var9 = isNeighbour(var0, var1, var2, var3.down(), var5, var6, var7);
            } else if (var5 == 4) {
               var8 = isNeighbour(var0, var1, var2, var3.down(), var5, var6, var7);
               var9 = isNeighbour(var0, var1, var2, var3.up(), var5, var6, var7);
            } else {
               var8 = isNeighbour(var0, var1, var2, var3.west(), var5, var6, var7);
               var9 = isNeighbour(var0, var1, var2, var3.east(), var5, var6, var7);
            }
      }

      byte var10 = 3;
      if (var8) {
         if (var9) {
            var10 = 1;
         } else {
            var10 = 2;
         }
      } else if (var9) {
         var10 = 0;
      } else {
         var10 = 3;
      }

      return var0.tileIcons[var10];
   }

   public static BakedQuad[] getConnectedTexture(IBlockAccess var0, IBlockState var1, BlockPos var2, BakedQuad var3, RenderEnv var4) {
      TextureAtlasSprite var5 = var3.getSprite();
      if (var5 == null) {
         return var4.getArrayQuadsCtm(var3);
      } else {
         Block var6 = var1.getBlock();
         if (skipConnectedTexture(var0, var1, var2, var3, var4)) {
            var3 = getQuad(emptySprite, var3);
            return var4.getArrayQuadsCtm(var3);
         } else {
            EnumFacing var7 = var3.getFace();
            return getConnectedTextureMultiPass(var0, var1, var2, var7, var3, var4);
         }
      }
   }

   public static BlockDir[] method_27409(int var0, int var1) {
      switch (var0) {
         case 0:
            return field_0031;
         case 1:
            return field_0030;
         case 2:
            if (var1 == 1) {
               return field_0014;
            }

            return field_0037;
         case 3:
            return field_0007;
         case 4:
            return field_0004;
         case 5:
            if (var1 == 2) {
               return field_0018;
            }

            return field_0009;
         default:
            throw new IllegalArgumentException("Unknown side: " + var0);
      }
   }

   public static BakedQuad[] getConnectedTextureOverlayRandom(
      ConnectedProperties var0, IBlockAccess var1, BlockStateBase var2, BlockPos var3, int var4, BakedQuad var5, RenderEnv var6
   ) {
      if (!var5.isFullQuad()) {
         return null;
      } else {
         ListQuadsOverlay var7 = var6.getListQuadsOverlay(var0.layer);

         Object var8;
         try {
            TextureAtlasSprite var9 = getConnectedTextureRandom(var0, var1, var2, var3, var4);
            if (var9 != null) {
               var7.addQuad(getQuadFull(var9, var5, var0.tintIndex), var0.tintBlockState);
            }

            var8 = null;
         } finally {
            if (var7.size() > 0) {
               var6.setOverlaysRendered(true);
            }
         }

         return (BakedQuad[])var8;
      }
   }

   public static void switchValues(int var0, int var1, boolean[] var2) {
      boolean var3 = var2[var0];
      var2[var0] = var2[var1];
      var2[var1] = var3;
   }

   public static TextureAtlasSprite getNeighbourIcon(IBlockAccess var0, IBlockState var1, BlockPos var2, IBlockState var3, int var4) {
      var3 = var3.getBlock().getActualState(var3, var0, var2);
      IBakedModel var5 = Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getModelForState(var3);
      if (var5 == null) {
         return null;
      } else {
         if (Reflector.ForgeBlock_getExtendedState.exists()) {
            var3 = (IBlockState)Reflector.call(var3.getBlock(), Reflector.ForgeBlock_getExtendedState, var3, var0, var2);
         }

         EnumFacing var6 = getFacing(var4);
         List var7 = var5.getFaceQuads(var6);
         if (var7 == null) {
            return null;
         } else {
            if (Config.isBetterGrass()) {
               var7 = BetterGrass.getFaceQuads(var0, var3, var2, var6, var7);
            }

            if (var7.size() > 0) {
               BakedQuad var12 = (BakedQuad)var7.get(0);
               return var12.getSprite();
            } else {
               List var8 = var5.getGeneralQuads();
               if (var8 == null) {
                  return null;
               } else {
                  for (int var9 = 0; var9 < var8.size(); var9++) {
                     BakedQuad var10 = (BakedQuad)var8.get(var9);
                     if (var10.getFace() == var6) {
                        return var10.getSprite();
                     }
                  }

                  return null;
               }
            }
         }
      }
   }

   public static boolean isFullCubeModel(IBlockState var0) {
      if (var0.getBlock().isFullCube()) {
         return true;
      } else {
         Block var1 = var0.getBlock();
         return var1 instanceof BlockGlass ? true : var1 instanceof BlockStainedGlass;
      }
   }

   public static BakedQuad[] getConnectedTextureSingle(
      IBlockAccess var0, IBlockState var1, BlockPos var2, EnumFacing var3, BakedQuad var4, boolean var5, int var6, RenderEnv var7
   ) {
      Block var8 = var1.getBlock();
      if (!(var1 instanceof BlockStateBase)) {
         return var7.getArrayQuadsCtm(var4);
      } else {
         BlockStateBase var9 = (BlockStateBase)var1;
         TextureAtlasSprite var10 = var4.getSprite();
         if (field_0016 != null) {
            int var11 = var10.getIndexInMap();
            if (var11 >= 0 && var11 < field_0016.length) {
               ConnectedProperties[] var12 = field_0016[var11];
               if (var12 != null) {
                  int var13 = getSide(var3);

                  for (int var14 = 0; var14 < var12.length; var14++) {
                     ConnectedProperties var15 = var12[var14];
                     if (var15 != null && var15.matchesBlockId(var9.getBlockId())) {
                        BakedQuad[] var16 = getConnectedTexture(var15, var0, var9, var2, var13, var4, var6, var7);
                        if (var16 != null) {
                           return var16;
                        }
                     }
                  }
               }
            }
         }

         if (field_0038 != null && var5) {
            int var17 = var7.getBlockId();
            if (var17 >= 0 && var17 < field_0038.length) {
               ConnectedProperties[] var18 = field_0038[var17];
               if (var18 != null) {
                  int var19 = getSide(var3);

                  for (int var20 = 0; var20 < var18.length; var20++) {
                     ConnectedProperties var21 = var18[var20];
                     if (var21 != null && var21.matchesIcon(var10)) {
                        BakedQuad[] var22 = getConnectedTexture(var21, var0, var9, var2, var19, var4, var6, var7);
                        if (var22 != null) {
                           return var22;
                        }
                     }
                  }
               }
            }
         }

         return var7.getArrayQuadsCtm(var4);
      }
   }

   public static BakedQuad[] getConnectedTextureOverlayFixed(ConnectedProperties var0, BakedQuad var1, RenderEnv var2) {
      if (!var1.isFullQuad()) {
         return null;
      } else {
         ListQuadsOverlay var3 = var2.getListQuadsOverlay(var0.layer);

         Object var4;
         try {
            TextureAtlasSprite var5 = getConnectedTextureFixed(var0);
            if (var5 != null) {
               var3.addQuad(getQuadFull(var5, var1, var0.tintIndex), var0.tintBlockState);
            }

            var4 = null;
         } finally {
            if (var3.size() > 0) {
               var2.setOverlaysRendered(true);
            }
         }

         return (BakedQuad[])var4;
      }
   }

   public static TextureAtlasSprite getConnectedTextureTop(
      ConnectedProperties var0, IBlockAccess var1, IBlockState var2, BlockPos var3, int var4, int var5, TextureAtlasSprite var6, int var7
   ) {
      boolean var8 = false;
      switch (var4) {
         case 0:
            if (var5 == 1 || var5 == 0) {
               return null;
            }

            var8 = isNeighbour(var0, var1, var2, var3.up(), var5, var6, var7);
            break;
         case 1:
            if (var5 == 3 || var5 == 2) {
               return null;
            }

            var8 = isNeighbour(var0, var1, var2, var3.south(), var5, var6, var7);
            break;
         case 2:
            if (var5 == 5 || var5 == 4) {
               return null;
            }

            var8 = isNeighbour(var0, var1, var2, var3.east(), var5, var6, var7);
      }

      return var8 ? var0.tileIcons[0] : null;
   }

   public static boolean isNeighbour(
      ConnectedProperties var0, IBlockAccess var1, IBlockState var2, BlockPos var3, IBlockState var4, int var5, TextureAtlasSprite var6, int var7
   ) {
      if (var2 == var4) {
         return true;
      } else if (var0.connect == 2) {
         if (var4 == null) {
            return false;
         } else if (var4 == field_0015) {
            return false;
         } else {
            TextureAtlasSprite var11 = getNeighbourIcon(var1, var2, var3, var4, var5);
            return var11 == var6;
         }
      } else if (var0.connect == 3) {
         return var4 == null ? false : (var4 == field_0015 ? false : var4.getBlock().getMaterial() == var2.getBlock().getMaterial());
      } else if (!(var4 instanceof BlockStateBase)) {
         return false;
      } else {
         BlockStateBase var8 = (BlockStateBase)var4;
         Block var9 = var8.getBlock();
         int var10 = var8.getMetadata();
         return var9 == var2.getBlock() && var10 == var7;
      }
   }

   public static BakedQuad[] getQuads(TextureAtlasSprite var0, BakedQuad var1, RenderEnv var2) {
      if (var0 == null) {
         return null;
      } else if (var0 == SPRITE_DEFAULT) {
         return var2.getArrayQuadsCtm(var1);
      } else {
         BakedQuad var3 = getQuad(var0, var1);
         return var2.getArrayQuadsCtm(var3);
      }
   }

   public static BlockDir[] method_27369(int var0, int var1) {
      switch (var0) {
         case 0:
            return field_0008;
         case 1:
            return field_0036;
         case 2:
            if (var1 == 1) {
               return field_0028;
            }

            return field_0013;
         case 3:
            return field_0039;
         case 4:
            return field_0020;
         case 5:
            if (var1 == 2) {
               return field_0023;
            }

            return field_0034;
         default:
            throw new IllegalArgumentException("Unknown side: " + var0);
      }
   }

   public static void fixVertex(int[] var0, int var1, TextureAtlasSprite var2, TextureAtlasSprite var3) {
      int var4 = var0.length / 4;
      int var5 = var4 * var1;
      float var6 = Float.intBitsToFloat(var0[var5 + 4]);
      float var7 = Float.intBitsToFloat(var0[var5 + 4 + 1]);
      double var8 = var2.getSpriteU16(var6);
      double var10 = var2.getSpriteV16(var7);
      var0[var5 + 4] = Float.floatToRawIntBits(var3.getInterpolatedU(var8));
      var0[var5 + 4 + 1] = Float.floatToRawIntBits(var3.getInterpolatedV(var10));
   }

   public static BakedQuad[] getConnectedTextureMultiPass(IBlockAccess var0, IBlockState var1, BlockPos var2, EnumFacing var3, BakedQuad var4, RenderEnv var5) {
      BakedQuad[] var6 = getConnectedTextureSingle(var0, var1, var2, var3, var4, true, 0, var5);
      if (!multipass) {
         return var6;
      } else if (var6.length == 1 && var6[0] == var4) {
         return var6;
      } else {
         List var7 = var5.getListQuadsCtmMultipass(var6);

         for (int var8 = 0; var8 < var7.size(); var8++) {
            BakedQuad var9 = (BakedQuad)var7.get(var8);
            BakedQuad var10 = var9;

            for (int var11 = 0; var11 < 3; var11++) {
               BakedQuad[] var12 = getConnectedTextureSingle(var0, var1, var2, var3, var10, false, var11 + 1, var5);
               if (var12.length != 1 || var12[0] == var10) {
                  break;
               }

               var10 = var12[0];
            }

            var7.set(var8, var10);
         }

         for (int var13 = 0; var13 < var6.length; var13++) {
            var6[var13] = (BakedQuad)var7.get(var13);
         }

         return var6;
      }
   }

   public static Map[][] getSpriteQuadCompactMaps() {
      return spriteQuadCompactMaps;
   }

   public static List makePropertyList(ConnectedProperties[][] var0) {
      ArrayList var1 = new ArrayList();
      if (var0 != null) {
         for (int var2 = 0; var2 < var0.length; var2++) {
            ConnectedProperties[] var3 = var0[var2];
            ArrayList var4 = null;
            if (var3 != null) {
               var4 = new ArrayList<>(Arrays.asList(var3));
            }

            var1.add(var4);
         }
      }

      return var1;
   }

   public static boolean skipConnectedTexture(IBlockAccess var0, IBlockState var1, BlockPos var2, BakedQuad var3, RenderEnv var4) {
      Block var5 = var1.getBlock();
      if (var5 instanceof BlockPane) {
         TextureAtlasSprite var6 = var3.getSprite();
         if (var6.getIconName().startsWith("minecraft:blocks/glass_pane_top")) {
            IBlockState var14 = var0.getBlockState(var2.a(var3.getFace()));
            return var14 == var1;
         }
      }

      if (var5 instanceof BlockPane) {
         EnumFacing var13 = var3.getFace();
         if (var13 != EnumFacing.UP && var13 != EnumFacing.DOWN) {
            return false;
         }

         if (!var3.isFaceQuad()) {
            return false;
         }

         BlockPos var7 = var2.a(var3.getFace());
         IBlockState var8 = var0.getBlockState(var7);
         if (var8.getBlock() != var5) {
            return false;
         }

         if (var5 == Blocks.stained_glass_pane && var8.getValue(BlockStainedGlassPane.COLOR) != var1.getValue(BlockStainedGlassPane.COLOR)) {
            return false;
         }

         var8 = var8.getBlock().getActualState(var8, var0, var7);
         double var9 = var3.getMidX();
         if (var9 < 0.4) {
            if (var8.getValue(BlockPane.P)) {
               return true;
            }
         } else if (var9 > 0.6) {
            if (var8.getValue(BlockPane.N)) {
               return true;
            }
         } else {
            double var11 = var3.method_24051();
            if (var11 < 0.4) {
               if (var8.getValue(BlockPane.b)) {
                  return true;
               }
            } else {
               if (var11 <= 0.6) {
                  return true;
               }

               if (var8.getValue(BlockPane.O)) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public static void method_27413(ConnectedProperties var0, List var1) {
      if (var0.matchBlocks != null) {
         for (int var2 = 0; var2 < var0.matchBlocks.length; var2++) {
            int var3 = var0.matchBlocks[var2].getBlockId();
            if (var3 < 0) {
               Config.warn("Invalid block ID: " + var3);
            } else {
               addToList(var0, var1, var3);
            }
         }
      }
   }

   public static ConnectedProperties[][] propertyListToArray(List var0) {
      ConnectedProperties[][] var1 = new ConnectedProperties[var0.size()][];

      for (int var2 = 0; var2 < var0.size(); var2++) {
         List var3 = (List)var0.get(var2);
         if (var3 != null) {
            ConnectedProperties[] var4 = var3.toArray(new ConnectedProperties[var3.size()]);
            var1[var2] = var4;
         }
      }

      return var1;
   }

   public static void addToList(ConnectedProperties var0, List var1, int var2) {
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

   public static TextureAtlasSprite method_27370(
      ConnectedProperties var0, IBlockAccess var1, IBlockState var2, BlockPos var3, int var4, int var5, TextureAtlasSprite var6, int var7
   ) {
      TextureAtlasSprite[] var8 = var0.tileIcons;
      TextureAtlasSprite var9 = getConnectedTextureHorizontal(var0, var1, var2, var3, var4, var5, var6, var7);
      if (var9 != null && var9 != var6 && var9 != var8[3]) {
         return var9;
      } else {
         TextureAtlasSprite var10 = getConnectedTextureVertical(var0, var1, var2, var3, var4, var5, var6, var7);
         return var10 == var8[0] ? var8[4] : (var10 == var8[1] ? var8[5] : (var10 == var8[2] ? var8[6] : var10));
      }
   }

   public static BakedQuad[] method_27374(
      ConnectedProperties var0, IBlockAccess var1, IBlockState var2, BlockPos var3, int var4, int var5, BakedQuad var6, int var7, RenderEnv var8
   ) {
      if (!var6.isFullQuad()) {
         return null;
      } else {
         TextureAtlasSprite var9 = var6.getSprite();
         BlockDir[] var10 = method_27369(var5, var4);
         boolean[] var11 = var8.getBorderFlags();

         for (int var12 = 0; var12 < 4; var12++) {
            var11[var12] = method_27375(var0, var1, var2, var10[var12].offset(var3), var5, var9, var7);
         }

         ListQuadsOverlay var22 = var8.getListQuadsOverlay(var0.layer);

         Object var13;
         try {
            if (!var11[0] || !var11[1] || !var11[2] || !var11[3]) {
               if (var11[0] && var11[1] && var11[2]) {
                  var22.addQuad(getQuadFull(var0.tileIcons[5], var6, var0.tintIndex), var0.tintBlockState);
                  var13 = null;
                  return (BakedQuad[])var13;
               }

               if (var11[0] && var11[2] && var11[3]) {
                  var22.addQuad(getQuadFull(var0.tileIcons[6], var6, var0.tintIndex), var0.tintBlockState);
                  var13 = null;
                  return (BakedQuad[])var13;
               }

               if (var11[1] && var11[2] && var11[3]) {
                  var22.addQuad(getQuadFull(var0.tileIcons[12], var6, var0.tintIndex), var0.tintBlockState);
                  var13 = null;
                  return (BakedQuad[])var13;
               }

               if (var11[0] && var11[1] && var11[3]) {
                  var22.addQuad(getQuadFull(var0.tileIcons[13], var6, var0.tintIndex), var0.tintBlockState);
                  var13 = null;
                  return (BakedQuad[])var13;
               }

               BlockDir[] var14 = method_27409(var5, var4);
               boolean[] var15 = var8.getBorderFlags2();

               for (int var16 = 0; var16 < 4; var16++) {
                  var15[var16] = method_27375(var0, var1, var2, var14[var16].offset(var3), var5, var9, var7);
               }

               if (var11[1] && var11[2]) {
                  var22.addQuad(getQuadFull(var0.tileIcons[3], var6, var0.tintIndex), var0.tintBlockState);
                  if (var15[3]) {
                     var22.addQuad(getQuadFull(var0.tileIcons[16], var6, var0.tintIndex), var0.tintBlockState);
                  }

                  Object var35 = null;
                  return (BakedQuad[])var35;
               }

               if (var11[0] && var11[2]) {
                  var22.addQuad(getQuadFull(var0.tileIcons[4], var6, var0.tintIndex), var0.tintBlockState);
                  if (var15[2]) {
                     var22.addQuad(getQuadFull(var0.tileIcons[14], var6, var0.tintIndex), var0.tintBlockState);
                  }

                  Object var34 = null;
                  return (BakedQuad[])var34;
               }

               if (var11[1] && var11[3]) {
                  var22.addQuad(getQuadFull(var0.tileIcons[10], var6, var0.tintIndex), var0.tintBlockState);
                  if (var15[1]) {
                     var22.addQuad(getQuadFull(var0.tileIcons[2], var6, var0.tintIndex), var0.tintBlockState);
                  }

                  Object var33 = null;
                  return (BakedQuad[])var33;
               }

               if (var11[0] && var11[3]) {
                  var22.addQuad(getQuadFull(var0.tileIcons[11], var6, var0.tintIndex), var0.tintBlockState);
                  if (var15[0]) {
                     var22.addQuad(getQuadFull(var0.tileIcons[0], var6, var0.tintIndex), var0.tintBlockState);
                  }

                  Object var32 = null;
                  return (BakedQuad[])var32;
               }

               boolean[] var31 = var8.getBorderFlags3();

               for (int var17 = 0; var17 < 4; var17++) {
                  var31[var17] = method_27417(var0, var1, var2, var10[var17].offset(var3), var5, var9, var7);
               }

               if (var11[0]) {
                  var22.addQuad(getQuadFull(var0.tileIcons[9], var6, var0.tintIndex), var0.tintBlockState);
               }

               if (var11[1]) {
                  var22.addQuad(getQuadFull(var0.tileIcons[7], var6, var0.tintIndex), var0.tintBlockState);
               }

               if (var11[2]) {
                  var22.addQuad(getQuadFull(var0.tileIcons[1], var6, var0.tintIndex), var0.tintBlockState);
               }

               if (var11[3]) {
                  var22.addQuad(getQuadFull(var0.tileIcons[15], var6, var0.tintIndex), var0.tintBlockState);
               }

               if (var15[0] && (var31[1] || var31[2]) && !var11[1] && !var11[2]) {
                  var22.addQuad(getQuadFull(var0.tileIcons[0], var6, var0.tintIndex), var0.tintBlockState);
               }

               if (var15[1] && (var31[0] || var31[2]) && !var11[0] && !var11[2]) {
                  var22.addQuad(getQuadFull(var0.tileIcons[2], var6, var0.tintIndex), var0.tintBlockState);
               }

               if (var15[2] && (var31[1] || var31[3]) && !var11[1] && !var11[3]) {
                  var22.addQuad(getQuadFull(var0.tileIcons[14], var6, var0.tintIndex), var0.tintBlockState);
               }

               if (var15[3] && (var31[0] || var31[3]) && !var11[0] && !var11[3]) {
                  var22.addQuad(getQuadFull(var0.tileIcons[16], var6, var0.tintIndex), var0.tintBlockState);
               }

               Object var36 = null;
               return (BakedQuad[])var36;
            }

            var22.addQuad(getQuadFull(var0.tileIcons[8], var6, var0.tintIndex), var0.tintBlockState);
            var13 = null;
         } finally {
            if (var22.size() > 0) {
               var8.setOverlaysRendered(true);
            }
         }

         return (BakedQuad[])var13;
      }
   }
}
