package net.optifine;

import java.util.IdentityHashMap;
import java.util.Map;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.optifine.render.RenderEnv;

public class ConnectedTexturesCompact {
   public static final int recoveredField6 = 4;
   public static final int recoveredField7 = 3;
   public static final int recoveredField8 = 1;
   public static final int recoveredField9 = 2;
   public static final int recoveredField10 = 0;

   public static BakedQuad[] getQuadsCompactV(int var0, int var1, TextureAtlasSprite[] var2, int var3, BakedQuad var4, RenderEnv var5) {
      return getQuadsCompact(ConnectedTexturesCompact.Dir.UP, var0, ConnectedTexturesCompact.Dir.DOWN, var1, var2, var3, var4, var5);
   }

   public static BakedQuad makeSpriteQuadCompact(BakedQuad var0, TextureAtlasSprite var1, int var2, int var3, int var4, int var5, int var6) {
      int[] var7 = (int[])var0.getVertexData().clone();
      TextureAtlasSprite var8 = var0.getSprite();

      for (int var9 = 0; var9 < 4; var9++) {
         fixVertexCompact(var7, var9, var8, var1, var2, var3, var4, var5, var6);
      }

      return new BakedQuad(var7, var0.getTintIndex(), var0.getFace(), var1);
   }

   public static BakedQuad[] getQuadsCompact(
      ConnectedTexturesCompact.Dir var0,
      int var1,
      ConnectedTexturesCompact.Dir var2,
      int var3,
      ConnectedTexturesCompact.Dir var4,
      int var5,
      TextureAtlasSprite[] var6,
      int var7,
      BakedQuad var8,
      RenderEnv var9
   ) {
      BakedQuad var10 = getQuadCompact(var6[var1], var0, var7, var8, var9);
      BakedQuad var11 = getQuadCompact(var6[var3], var2, var7, var8, var9);
      BakedQuad var12 = getQuadCompact(var6[var5], var4, var7, var8, var9);
      return var9.getArrayQuadsCtm(var10, var11, var12);
   }

   public static BakedQuad getQuadCompact(
      TextureAtlasSprite var0, ConnectedTexturesCompact.Dir var1, int var2, int var3, int var4, int var5, int var6, BakedQuad var7, RenderEnv var8
   ) {
      Map[][] var9 = ConnectedTextures.getSpriteQuadCompactMaps();
      if (var9 == null) {
         return var7;
      } else {
         int var10 = var0.getIndexInMap();
         if (var10 >= 0 && var10 < var9.length) {
            Map[] var11 = var9[var10];
            if (var11 == null) {
               var11 = new Map[ConnectedTexturesCompact.Dir.VALUES.length];
               var9[var10] = var11;
            }

            java.util.Map var12 = var11[var1.ordinal()];
            if (var12 == null) {
               var12 = new IdentityHashMap(1);
               var11[var1.ordinal()] = (Map)var12;
            }

            BakedQuad var13 = (BakedQuad)var12.get(var7);
            if (var13 == null) {
               var13 = makeSpriteQuadCompact(var7, var0, var6, var2, var3, var4, var5);
               var12.put(var7, var13);
            }

            return var13;
         } else {
            return var7;
         }
      }
   }

   public static BakedQuad[] getQuadsCompact(int var0, TextureAtlasSprite[] var1, BakedQuad var2, RenderEnv var3) {
      TextureAtlasSprite var4 = var1[var0];
      return ConnectedTextures.getQuads(var4, var2, var3);
   }

   public static BakedQuad getQuadCompact(TextureAtlasSprite var0, ConnectedTexturesCompact.Dir var1, int var2, BakedQuad var3, RenderEnv var4) {
      switch (var1) {
         case UP:
            return getQuadCompact(var0, var1, 0, 0, 16, 8, var2, var3, var4);
         case UP_RIGHT:
            return getQuadCompact(var0, var1, 8, 0, 16, 8, var2, var3, var4);
         case RIGHT:
            return getQuadCompact(var0, var1, 8, 0, 16, 16, var2, var3, var4);
         case DOWN_RIGHT:
            return getQuadCompact(var0, var1, 8, 8, 16, 16, var2, var3, var4);
         case DOWN:
            return getQuadCompact(var0, var1, 0, 8, 16, 16, var2, var3, var4);
         case DOWN_LEFT:
            return getQuadCompact(var0, var1, 0, 8, 8, 16, var2, var3, var4);
         case LEFT:
            return getQuadCompact(var0, var1, 0, 0, 8, 16, var2, var3, var4);
         case UP_LEFT:
            return getQuadCompact(var0, var1, 0, 0, 8, 8, var2, var3, var4);
         default:
            return var3;
      }
   }

   public static BakedQuad[] getQuadsCompact(
      ConnectedTexturesCompact.Dir var0,
      int var1,
      ConnectedTexturesCompact.Dir var2,
      int var3,
      TextureAtlasSprite[] var4,
      int var5,
      BakedQuad var6,
      RenderEnv var7
   ) {
      BakedQuad var8 = getQuadCompact(var4[var1], var0, var5, var6, var7);
      BakedQuad var9 = getQuadCompact(var4[var3], var2, var5, var6, var7);
      return var7.getArrayQuadsCtm(var8, var9);
   }

   public static BakedQuad[] getQuadsCompact4(int var0, int var1, int var2, int var3, TextureAtlasSprite[] var4, int var5, BakedQuad var6, RenderEnv var7) {
      return var0 == var1
         ? (
            var2 == var3
               ? getQuadsCompact(ConnectedTexturesCompact.Dir.UP, var0, ConnectedTexturesCompact.Dir.DOWN, var2, var4, var5, var6, var7)
               : getQuadsCompact(
                  ConnectedTexturesCompact.Dir.UP,
                  var0,
                  ConnectedTexturesCompact.Dir.DOWN_LEFT,
                  var2,
                  ConnectedTexturesCompact.Dir.DOWN_RIGHT,
                  var3,
                  var4,
                  var5,
                  var6,
                  var7
               )
         )
         : (
            var2 == var3
               ? getQuadsCompact(
                  ConnectedTexturesCompact.Dir.UP_LEFT,
                  var0,
                  ConnectedTexturesCompact.Dir.UP_RIGHT,
                  var1,
                  ConnectedTexturesCompact.Dir.DOWN,
                  var2,
                  var4,
                  var5,
                  var6,
                  var7
               )
               : (
                  var0 == var2
                     ? (
                        var1 == var3
                           ? getQuadsCompact(ConnectedTexturesCompact.Dir.LEFT, var0, ConnectedTexturesCompact.Dir.RIGHT, var1, var4, var5, var6, var7)
                           : getQuadsCompact(
                              ConnectedTexturesCompact.Dir.LEFT,
                              var0,
                              ConnectedTexturesCompact.Dir.UP_RIGHT,
                              var1,
                              ConnectedTexturesCompact.Dir.DOWN_RIGHT,
                              var3,
                              var4,
                              var5,
                              var6,
                              var7
                           )
                     )
                     : (
                        var1 == var3
                           ? getQuadsCompact(
                              ConnectedTexturesCompact.Dir.UP_LEFT,
                              var0,
                              ConnectedTexturesCompact.Dir.DOWN_LEFT,
                              var2,
                              ConnectedTexturesCompact.Dir.RIGHT,
                              var1,
                              var4,
                              var5,
                              var6,
                              var7
                           )
                           : getQuadsCompact(
                              ConnectedTexturesCompact.Dir.UP_LEFT,
                              var0,
                              ConnectedTexturesCompact.Dir.UP_RIGHT,
                              var1,
                              ConnectedTexturesCompact.Dir.DOWN_LEFT,
                              var2,
                              ConnectedTexturesCompact.Dir.DOWN_RIGHT,
                              var3,
                              var4,
                              var5,
                              var6,
                              var7
                           )
                     )
               )
         );
   }

   public static BakedQuad[] getQuadsCompact(
      ConnectedTexturesCompact.Dir var0,
      int var1,
      ConnectedTexturesCompact.Dir var2,
      int var3,
      ConnectedTexturesCompact.Dir var4,
      int var5,
      ConnectedTexturesCompact.Dir var6,
      int var7,
      TextureAtlasSprite[] var8,
      int var9,
      BakedQuad var10,
      RenderEnv var11
   ) {
      BakedQuad var12 = getQuadCompact(var8[var1], var0, var9, var10, var11);
      BakedQuad var13 = getQuadCompact(var8[var3], var2, var9, var10, var11);
      BakedQuad var14 = getQuadCompact(var8[var5], var4, var9, var10, var11);
      BakedQuad var15 = getQuadCompact(var8[var7], var6, var9, var10, var11);
      return var11.getArrayQuadsCtm(var12, var13, var14, var15);
   }

   public static void fixVertexCompact(int[] var0, int var1, TextureAtlasSprite var2, TextureAtlasSprite var3, int var4, int var5, int var6, int var7, int var8) {
      int var9 = var0.length / 4;
      int var10 = var9 * var1;
      float var11 = Float.intBitsToFloat(var0[var10 + 4]);
      float var12 = Float.intBitsToFloat(var0[var10 + 4 + 1]);
      double var13 = var2.getSpriteU16(var11);
      double var15 = var2.getSpriteV16(var12);
      float var17 = Float.intBitsToFloat(var0[var10 + 0]);
      float var18 = Float.intBitsToFloat(var0[var10 + 1]);
      float var19 = Float.intBitsToFloat(var0[var10 + 2]);
      float var20;
      float var21;
      switch (var4) {
         case 0:
            var20 = var17;
            var21 = 1.0F - var19;
            break;
         case 1:
            var20 = var17;
            var21 = var19;
            break;
         case 2:
            var20 = 1.0F - var17;
            var21 = 1.0F - var18;
            break;
         case 3:
            var20 = var17;
            var21 = 1.0F - var18;
            break;
         case 4:
            var20 = var19;
            var21 = 1.0F - var18;
            break;
         case 5:
            var20 = 1.0F - var19;
            var21 = 1.0F - var18;
            break;
         default:
            return;
      }

      float var22 = 15.968F;
      float var23 = 15.968F;
      if (var13 < var5) {
         var20 = (float)(var20 + (var5 - var13) / var22);
         var13 = var5;
      }

      if (var13 > var7) {
         var20 = (float)(var20 - (var13 - var7) / var22);
         var13 = var7;
      }

      if (var15 < var6) {
         var21 = (float)(var21 + (var6 - var15) / var23);
         var15 = var6;
      }

      if (var15 > var8) {
         var21 = (float)(var21 - (var15 - var8) / var23);
         var15 = var8;
      }

      switch (var4) {
         case 0:
            var17 = var20;
            var19 = 1.0F - var21;
            break;
         case 1:
            var17 = var20;
            var19 = var21;
            break;
         case 2:
            var17 = 1.0F - var20;
            var18 = 1.0F - var21;
            break;
         case 3:
            var17 = var20;
            var18 = 1.0F - var21;
            break;
         case 4:
            var19 = var20;
            var18 = 1.0F - var21;
            break;
         case 5:
            var19 = 1.0F - var20;
            var18 = 1.0F - var21;
            break;
         default:
            return;
      }

      var0[var10 + 4] = Float.floatToRawIntBits(var3.getInterpolatedU(var13));
      var0[var10 + 4 + 1] = Float.floatToRawIntBits(var3.getInterpolatedV(var15));
      var0[var10 + 0] = Float.floatToRawIntBits(var17);
      var0[var10 + 1] = Float.floatToRawIntBits(var18);
      var0[var10 + 2] = Float.floatToRawIntBits(var19);
   }

   public static BakedQuad[] getQuadsCompactH(int var0, int var1, TextureAtlasSprite[] var2, int var3, BakedQuad var4, RenderEnv var5) {
      return getQuadsCompact(ConnectedTexturesCompact.Dir.LEFT, var0, ConnectedTexturesCompact.Dir.RIGHT, var1, var2, var3, var4, var5);
   }

   public static BakedQuad[] getConnectedTextureCtmCompact(int var0, ConnectedProperties var1, int var2, BakedQuad var3, RenderEnv var4) {
      if (var1.ctmTileIndexes != null && var0 >= 0 && var0 < var1.ctmTileIndexes.length) {
         int var5 = var1.ctmTileIndexes[var0];
         if (var5 >= 0 && var5 <= var1.tileIcons.length) {
            return getQuadsCompact(var5, var1.tileIcons, var3, var4);
         }
      }

      switch (var0) {
         case 1:
            return getQuadsCompactH(0, 3, var1.tileIcons, var2, var3, var4);
         case 2:
            return getQuadsCompact(3, var1.tileIcons, var3, var4);
         case 3:
            return getQuadsCompactH(3, 0, var1.tileIcons, var2, var3, var4);
         case 4:
            return getQuadsCompact4(0, 3, 2, 4, var1.tileIcons, var2, var3, var4);
         case 5:
            return getQuadsCompact4(3, 0, 4, 2, var1.tileIcons, var2, var3, var4);
         case 6:
            return getQuadsCompact4(2, 4, 2, 4, var1.tileIcons, var2, var3, var4);
         case 7:
            return getQuadsCompact4(3, 3, 4, 4, var1.tileIcons, var2, var3, var4);
         case 8:
            return getQuadsCompact4(4, 1, 4, 4, var1.tileIcons, var2, var3, var4);
         case 9:
            return getQuadsCompact4(4, 4, 4, 1, var1.tileIcons, var2, var3, var4);
         case 10:
            return getQuadsCompact4(1, 4, 1, 4, var1.tileIcons, var2, var3, var4);
         case 11:
            return getQuadsCompact4(1, 1, 4, 4, var1.tileIcons, var2, var3, var4);
         case 12:
            return getQuadsCompactV(0, 2, var1.tileIcons, var2, var3, var4);
         case 13:
            return getQuadsCompact4(0, 3, 2, 1, var1.tileIcons, var2, var3, var4);
         case 14:
            return getQuadsCompactV(3, 1, var1.tileIcons, var2, var3, var4);
         case 15:
            return getQuadsCompact4(3, 0, 1, 2, var1.tileIcons, var2, var3, var4);
         case 16:
            return getQuadsCompact4(2, 4, 0, 3, var1.tileIcons, var2, var3, var4);
         case 17:
            return getQuadsCompact4(4, 2, 3, 0, var1.tileIcons, var2, var3, var4);
         case 18:
            return getQuadsCompact4(4, 4, 3, 3, var1.tileIcons, var2, var3, var4);
         case 19:
            return getQuadsCompact4(4, 2, 4, 2, var1.tileIcons, var2, var3, var4);
         case 20:
            return getQuadsCompact4(1, 4, 4, 4, var1.tileIcons, var2, var3, var4);
         case 21:
            return getQuadsCompact4(4, 4, 1, 4, var1.tileIcons, var2, var3, var4);
         case 22:
            return getQuadsCompact4(4, 4, 1, 1, var1.tileIcons, var2, var3, var4);
         case 23:
            return getQuadsCompact4(4, 1, 4, 1, var1.tileIcons, var2, var3, var4);
         case 24:
            return getQuadsCompact(2, var1.tileIcons, var3, var4);
         case 25:
            return getQuadsCompactH(2, 1, var1.tileIcons, var2, var3, var4);
         case 26:
            return getQuadsCompact(1, var1.tileIcons, var3, var4);
         case 27:
            return getQuadsCompactH(1, 2, var1.tileIcons, var2, var3, var4);
         case 28:
            return getQuadsCompact4(2, 4, 2, 1, var1.tileIcons, var2, var3, var4);
         case 29:
            return getQuadsCompact4(3, 3, 1, 4, var1.tileIcons, var2, var3, var4);
         case 30:
            return getQuadsCompact4(2, 1, 2, 4, var1.tileIcons, var2, var3, var4);
         case 31:
            return getQuadsCompact4(3, 3, 4, 1, var1.tileIcons, var2, var3, var4);
         case 32:
            return getQuadsCompact4(1, 1, 1, 4, var1.tileIcons, var2, var3, var4);
         case 33:
            return getQuadsCompact4(1, 1, 4, 1, var1.tileIcons, var2, var3, var4);
         case 34:
            return getQuadsCompact4(4, 1, 1, 4, var1.tileIcons, var2, var3, var4);
         case 35:
            return getQuadsCompact4(1, 4, 4, 1, var1.tileIcons, var2, var3, var4);
         case 36:
            return getQuadsCompactV(2, 0, var1.tileIcons, var2, var3, var4);
         case 37:
            return getQuadsCompact4(2, 1, 0, 3, var1.tileIcons, var2, var3, var4);
         case 38:
            return getQuadsCompactV(1, 3, var1.tileIcons, var2, var3, var4);
         case 39:
            return getQuadsCompact4(1, 2, 3, 0, var1.tileIcons, var2, var3, var4);
         case 40:
            return getQuadsCompact4(4, 1, 3, 3, var1.tileIcons, var2, var3, var4);
         case 41:
            return getQuadsCompact4(1, 2, 4, 2, var1.tileIcons, var2, var3, var4);
         case 42:
            return getQuadsCompact4(1, 4, 3, 3, var1.tileIcons, var2, var3, var4);
         case 43:
            return getQuadsCompact4(4, 2, 1, 2, var1.tileIcons, var2, var3, var4);
         case 44:
            return getQuadsCompact4(1, 4, 1, 1, var1.tileIcons, var2, var3, var4);
         case 45:
            return getQuadsCompact4(4, 1, 1, 1, var1.tileIcons, var2, var3, var4);
         case 46:
            return getQuadsCompact(4, var1.tileIcons, var3, var4);
         default:
            return getQuadsCompact(0, var1.tileIcons, var3, var4);
      }
   }

   public static enum Dir {
      UP,
      UP_RIGHT,
      RIGHT,
      DOWN_RIGHT,
      DOWN,
      DOWN_LEFT,
      LEFT,
      UP_LEFT;
      // $VF: synthetic field
      public static ConnectedTexturesCompact.Dir[] $VALUES = new ConnectedTexturesCompact.Dir[]{
         UP, UP_RIGHT, RIGHT, ConnectedTexturesCompact.Dir.DOWN_RIGHT, DOWN, ConnectedTexturesCompact.Dir.DOWN_LEFT, ConnectedTexturesCompact.Dir.LEFT, UP_LEFT
      };
      public static ConnectedTexturesCompact.Dir[] VALUES = values();
   }
}
