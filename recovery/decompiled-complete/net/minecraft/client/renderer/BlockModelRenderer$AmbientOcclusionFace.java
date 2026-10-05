package net.minecraft.client.renderer;

import java.util.BitSet;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.layers.LayerSaddle;
import net.minecraft.network.play.server.S0APacketUseBed;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryExplorerTree$1;
import recovered.unidentified.UnidentifiedClass4330;

public class BlockModelRenderer$AmbientOcclusionFace {
   public LayerSaddle field_0003;
   public float[] vertexColorMultiplier = new float[4];
   public S0APacketUseBed field_0002;
   public int[] vertexBrightness = new int[4];
   public UnidentifiedClass4330 field_0000;
   public CategoryExplorerTree$1 field_0001;

   public void setMaxBlockLight() {
      short var1 = 240;
      this.vertexBrightness[0] = this.vertexBrightness[0] | var1;
      this.vertexBrightness[1] = this.vertexBrightness[1] | var1;
      this.vertexBrightness[2] = this.vertexBrightness[2] | var1;
      this.vertexBrightness[3] = this.vertexBrightness[3] | var1;
      this.vertexColorMultiplier[0] = 1.0F;
      this.vertexColorMultiplier[1] = 1.0F;
      this.vertexColorMultiplier[2] = 1.0F;
      this.vertexColorMultiplier[3] = 1.0F;
   }

   public int getVertexBrightness(int var1, int var2, int var3, int var4, float var5, float var6, float var7, float var8) {
      int var9 = (int)((var1 >> 16 & 0xFF) * var5 + (var2 >> 16 & 0xFF) * var6 + (var3 >> 16 & 0xFF) * var7 + (var4 >> 16 & 0xFF) * var8) & 0xFF;
      int var10 = (int)((var1 & 0xFF) * var5 + (var2 & 0xFF) * var6 + (var3 & 0xFF) * var7 + (var4 & 0xFF) * var8) & 0xFF;
      return var9 << 16 | var10;
   }

   public void updateVertexBrightness(IBlockAccess var1, Block var2, BlockPos var3, EnumFacing var4, float[] var5, BitSet var6) {
      BlockPos var7 = var6.get(0) ? var3.a(var4) : var3;
      BlockModelRenderer$EnumNeighborInfo var8 = BlockModelRenderer$EnumNeighborInfo.getNeighbourInfo(var4);
      BlockPos var9 = var7.a(var8.field_178276_g[0]);
      BlockPos var10 = var7.a(var8.field_178276_g[1]);
      BlockPos var11 = var7.a(var8.field_178276_g[2]);
      BlockPos var12 = var7.a(var8.field_178276_g[3]);
      int var13 = var2.getMixedBrightnessForBlock(var1, var9);
      int var14 = var2.getMixedBrightnessForBlock(var1, var10);
      int var15 = var2.getMixedBrightnessForBlock(var1, var11);
      int var16 = var2.getMixedBrightnessForBlock(var1, var12);
      float var17 = BlockModelRenderer.fixAoLightValue(var1.getBlockState(var9).getBlock().getAmbientOcclusionLightValue());
      float var18 = BlockModelRenderer.fixAoLightValue(var1.getBlockState(var10).getBlock().getAmbientOcclusionLightValue());
      float var19 = BlockModelRenderer.fixAoLightValue(var1.getBlockState(var11).getBlock().getAmbientOcclusionLightValue());
      float var20 = BlockModelRenderer.fixAoLightValue(var1.getBlockState(var12).getBlock().getAmbientOcclusionLightValue());
      boolean var21 = var1.getBlockState(var9.a(var4)).getBlock().isTranslucent();
      boolean var22 = var1.getBlockState(var10.a(var4)).getBlock().isTranslucent();
      boolean var23 = var1.getBlockState(var11.a(var4)).getBlock().isTranslucent();
      boolean var24 = var1.getBlockState(var12.a(var4)).getBlock().isTranslucent();
      float var25;
      int var26;
      if (!var23 && !var21) {
         var25 = var17;
         var26 = var13;
      } else {
         BlockPos var27 = var9.a(var8.field_178276_g[2]);
         var25 = BlockModelRenderer.fixAoLightValue(var1.getBlockState(var27).getBlock().getAmbientOcclusionLightValue());
         var26 = var2.getMixedBrightnessForBlock(var1, var27);
      }

      float var28;
      int var60;
      if (!var24 && !var21) {
         var28 = var17;
         var60 = var13;
      } else {
         BlockPos var29 = var9.a(var8.field_178276_g[3]);
         var28 = BlockModelRenderer.fixAoLightValue(var1.getBlockState(var29).getBlock().getAmbientOcclusionLightValue());
         var60 = var2.getMixedBrightnessForBlock(var1, var29);
      }

      float var30;
      int var61;
      if (!var23 && !var22) {
         var30 = var18;
         var61 = var14;
      } else {
         BlockPos var31 = var10.a(var8.field_178276_g[2]);
         var30 = BlockModelRenderer.fixAoLightValue(var1.getBlockState(var31).getBlock().getAmbientOcclusionLightValue());
         var61 = var2.getMixedBrightnessForBlock(var1, var31);
      }

      float var32;
      int var62;
      if (!var24 && !var22) {
         var32 = var18;
         var62 = var14;
      } else {
         BlockPos var33 = var10.a(var8.field_178276_g[3]);
         var32 = BlockModelRenderer.fixAoLightValue(var1.getBlockState(var33).getBlock().getAmbientOcclusionLightValue());
         var62 = var2.getMixedBrightnessForBlock(var1, var33);
      }

      int var63 = var2.getMixedBrightnessForBlock(var1, var3);
      if (var6.get(0) || !var1.getBlockState(var3.a(var4)).getBlock().isOpaqueCube()) {
         var63 = var2.getMixedBrightnessForBlock(var1, var3.a(var4));
      }

      float var34 = var6.get(0)
         ? var1.getBlockState(var7).getBlock().getAmbientOcclusionLightValue()
         : var1.getBlockState(var3).getBlock().getAmbientOcclusionLightValue();
      var34 = BlockModelRenderer.fixAoLightValue(var34);
      BlockModelRenderer$VertexTranslations var35 = BlockModelRenderer$VertexTranslations.getVertexTranslations(var4);
      if (var6.get(1) && var8.field_178289_i) {
         float var65 = (var20 + var17 + var28 + var34) * 0.25F;
         float var66 = (var19 + var17 + var25 + var34) * 0.25F;
         float var67 = (var19 + var18 + var30 + var34) * 0.25F;
         float var68 = (var20 + var18 + var32 + var34) * 0.25F;
         float var40 = var5[var8.field_178286_j[0].field_178229_m] * var5[var8.field_178286_j[1].field_178229_m];
         float var41 = var5[var8.field_178286_j[2].field_178229_m] * var5[var8.field_178286_j[3].field_178229_m];
         float var42 = var5[var8.field_178286_j[4].field_178229_m] * var5[var8.field_178286_j[5].field_178229_m];
         float var43 = var5[var8.field_178286_j[6].field_178229_m] * var5[var8.field_178286_j[7].field_178229_m];
         float var44 = var5[var8.field_178287_k[0].field_178229_m] * var5[var8.field_178287_k[1].field_178229_m];
         float var45 = var5[var8.field_178287_k[2].field_178229_m] * var5[var8.field_178287_k[3].field_178229_m];
         float var46 = var5[var8.field_178287_k[4].field_178229_m] * var5[var8.field_178287_k[5].field_178229_m];
         float var47 = var5[var8.field_178287_k[6].field_178229_m] * var5[var8.field_178287_k[7].field_178229_m];
         float var48 = var5[var8.field_178284_l[0].field_178229_m] * var5[var8.field_178284_l[1].field_178229_m];
         float var49 = var5[var8.field_178284_l[2].field_178229_m] * var5[var8.field_178284_l[3].field_178229_m];
         float var50 = var5[var8.field_178284_l[4].field_178229_m] * var5[var8.field_178284_l[5].field_178229_m];
         float var51 = var5[var8.field_178284_l[6].field_178229_m] * var5[var8.field_178284_l[7].field_178229_m];
         float var52 = var5[var8.field_178285_m[0].field_178229_m] * var5[var8.field_178285_m[1].field_178229_m];
         float var53 = var5[var8.field_178285_m[2].field_178229_m] * var5[var8.field_178285_m[3].field_178229_m];
         float var54 = var5[var8.field_178285_m[4].field_178229_m] * var5[var8.field_178285_m[5].field_178229_m];
         float var55 = var5[var8.field_178285_m[6].field_178229_m] * var5[var8.field_178285_m[7].field_178229_m];
         this.vertexColorMultiplier[BlockModelRenderer$VertexTranslations.access$200(var35)] = var65 * var40 + var66 * var41 + var67 * var42 + var68 * var43;
         this.vertexColorMultiplier[BlockModelRenderer$VertexTranslations.access$300(var35)] = var65 * var44 + var66 * var45 + var67 * var46 + var68 * var47;
         this.vertexColorMultiplier[BlockModelRenderer$VertexTranslations.access$400(var35)] = var65 * var48 + var66 * var49 + var67 * var50 + var68 * var51;
         this.vertexColorMultiplier[BlockModelRenderer$VertexTranslations.access$500(var35)] = var65 * var52 + var66 * var53 + var67 * var54 + var68 * var55;
         int var56 = this.getAoBrightness(var16, var13, var60, var63);
         int var57 = this.getAoBrightness(var15, var13, var26, var63);
         int var58 = this.getAoBrightness(var15, var14, var61, var63);
         int var59 = this.getAoBrightness(var16, var14, var62, var63);
         this.vertexBrightness[BlockModelRenderer$VertexTranslations.access$200(var35)] = this.getVertexBrightness(
            var56, var57, var58, var59, var40, var41, var42, var43
         );
         this.vertexBrightness[BlockModelRenderer$VertexTranslations.access$300(var35)] = this.getVertexBrightness(
            var56, var57, var58, var59, var44, var45, var46, var47
         );
         this.vertexBrightness[BlockModelRenderer$VertexTranslations.access$400(var35)] = this.getVertexBrightness(
            var56, var57, var58, var59, var48, var49, var50, var51
         );
         this.vertexBrightness[BlockModelRenderer$VertexTranslations.access$500(var35)] = this.getVertexBrightness(
            var56, var57, var58, var59, var52, var53, var54, var55
         );
      } else {
         float var36 = (var20 + var17 + var28 + var34) * 0.25F;
         float var37 = (var19 + var17 + var25 + var34) * 0.25F;
         float var38 = (var19 + var18 + var30 + var34) * 0.25F;
         float var39 = (var20 + var18 + var32 + var34) * 0.25F;
         this.vertexBrightness[BlockModelRenderer$VertexTranslations.access$200(var35)] = this.getAoBrightness(var16, var13, var60, var63);
         this.vertexBrightness[BlockModelRenderer$VertexTranslations.access$300(var35)] = this.getAoBrightness(var15, var13, var26, var63);
         this.vertexBrightness[BlockModelRenderer$VertexTranslations.access$400(var35)] = this.getAoBrightness(var15, var14, var61, var63);
         this.vertexBrightness[BlockModelRenderer$VertexTranslations.access$500(var35)] = this.getAoBrightness(var16, var14, var62, var63);
         this.vertexColorMultiplier[BlockModelRenderer$VertexTranslations.access$200(var35)] = var36;
         this.vertexColorMultiplier[BlockModelRenderer$VertexTranslations.access$300(var35)] = var37;
         this.vertexColorMultiplier[BlockModelRenderer$VertexTranslations.access$400(var35)] = var38;
         this.vertexColorMultiplier[BlockModelRenderer$VertexTranslations.access$500(var35)] = var39;
      }
   }

   public BlockModelRenderer$AmbientOcclusionFace(BlockModelRenderer var1) {
   }

   public BlockModelRenderer$AmbientOcclusionFace() {
      this((BlockModelRenderer)null);
   }

   public int getAoBrightness(int var1, int var2, int var3, int var4) {
      if (var1 == 0) {
         var1 = var4;
      }

      if (var2 == 0) {
         var2 = var4;
      }

      if (var3 == 0) {
         var3 = var4;
      }

      return var1 + var2 + var3 + var4 >> 2 & 16711935;
   }
}
