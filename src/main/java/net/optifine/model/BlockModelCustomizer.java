package net.optifine.model;

import com.google.common.collect.ImmutableList;
import java.util.List;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.src.Config;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.optifine.BetterGrass;
import net.optifine.ConnectedTextures;
import net.optifine.NaturalTextures;
import net.optifine.SmartLeaves;
import net.optifine.render.RenderEnv;

public class BlockModelCustomizer {
   public static List<BakedQuad> NO_QUADS = ImmutableList.of();

   public static BakedQuad[] getRenderQuads(BakedQuad var0, IBlockAccess var1, IBlockState var2, BlockPos var3, EnumFacing var4, long var5, RenderEnv var7) {
      if (var7.isBreakingAnimation(var0)) {
         return var7.getArrayQuadsCtm(var0);
      } else {
         BakedQuad var8 = var0;
         if (Config.isConnectedTextures()) {
            BakedQuad[] var9 = ConnectedTextures.getConnectedTexture(var1, var2, var3, var0, var7);
            if (var9.length != 1 || var9[0] != var0) {
               return var9;
            }
         }

         if (Config.isNaturalTextures()) {
            var0 = NaturalTextures.getNaturalTexture(var3, var0);
            if (var0 != var8) {
               return var7.getArrayQuadsCtm(var0);
            }
         }

         return var7.getArrayQuadsCtm(var0);
      }
   }

   public static EnumWorldBlockLayer getEmissiveLayer(EnumWorldBlockLayer var0) {
      return var0 != null && var0 != EnumWorldBlockLayer.SOLID ? var0 : EnumWorldBlockLayer.CUTOUT_MIPPED;
   }

   public static IBakedModel getRenderModel(IBakedModel var0, IBlockState var1, RenderEnv var2) {
      if (var2.isSmartLeaves()) {
         var0 = SmartLeaves.getLeavesModel(var0, var1);
      }

      return var0;
   }

   public static List<BakedQuad> getRenderQuads(
      List<BakedQuad> var0, IBlockAccess var1, IBlockState var2, BlockPos var3, EnumFacing var4, EnumWorldBlockLayer var5, long var6, RenderEnv var8
   ) {
      if (var4 != null) {
         if (var8.isSmartLeaves() && SmartLeaves.isSameLeaves(var1.getBlockState(var3.a(var4)), var2)) {
            return NO_QUADS;
         }

         if (!var8.isBreakingAnimation(var0) && Config.isBetterGrass()) {
            var0 = BetterGrass.getFaceQuads(var1, var2, var3, var4, var0);
         }
      }

      List var9 = var8.getListQuadsCustomizer();
      var9.clear();

      for (int var10 = 0; var10 < var0.size(); var10++) {
         BakedQuad var11 = (BakedQuad)var0.get(var10);
         BakedQuad[] var12 = getRenderQuads(var11, var1, var2, var3, var4, var6, var8);
         if (var10 == 0 && var0.size() == 1 && var12.length == 1 && var12[0] == var11 && var11.getQuadEmissive() == null) {
            return var0;
         }

         for (int var13 = 0; var13 < var12.length; var13++) {
            BakedQuad var14 = var12[var13];
            var9.add(var14);
            if (var14.getQuadEmissive() != null) {
               var8.getListQuadsOverlay(getEmissiveLayer(var5)).addQuad(var14.getQuadEmissive(), var2);
               var8.setOverlaysRendered(true);
            }
         }
      }

      return var9;
   }
}
