package net.minecraft.client.renderer;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.FaceBakery;
import net.minecraft.client.renderer.chunk.VboChunkFactory;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.crafting.RecipesBanners$RecipeDuplicatePattern;
import net.minecraft.src.Config;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.optifine.CustomColors;
import net.optifine.render.RenderEnv;
import net.optifine.shaders.SVertexBuilder;

public class BlockFluidRenderer {
   public TextureAtlasSprite[] atlasSpritesWater;
   public VboChunkFactory field_0003;
   public RecipesBanners$RecipeDuplicatePattern field_0000;
   public TextureAtlasSprite[] atlasSpritesLava = new TextureAtlasSprite[2];

   public BlockFluidRenderer() {
      this.atlasSpritesWater = new TextureAtlasSprite[2];
      this.initAtlasSprites();
   }

   public void initAtlasSprites() {
      TextureMap var1 = Minecraft.getMinecraft().getTextureMapBlocks();
      this.atlasSpritesLava[0] = var1.getAtlasSprite("minecraft:blocks/lava_still");
      this.atlasSpritesLava[1] = var1.getAtlasSprite("minecraft:blocks/lava_flow");
      this.atlasSpritesWater[0] = var1.getAtlasSprite("minecraft:blocks/water_still");
      this.atlasSpritesWater[1] = var1.getAtlasSprite("minecraft:blocks/water_flow");
   }

   public float getFluidHeight(IBlockAccess var1, BlockPos var2, Material var3) {
      int var4 = 0;
      float var5 = 0.0F;

      for (int var6 = 0; var6 < 4; var6++) {
         BlockPos var7 = var2.add(-(var6 & 1), 0, -(var6 >> 1 & 1));
         if (var1.getBlockState(var7.up()).getBlock().getMaterial() == var3) {
            return 1.0F;
         }

         IBlockState var8 = var1.getBlockState(var7);
         Material var9 = var8.getBlock().getMaterial();
         if (var9 != var3) {
            if (!var9.isSolid()) {
               var5++;
               var4++;
            }
         } else {
            int var10 = var8.getValue(BlockLiquid.b);
            if (var10 >= 8 || var10 == 0) {
               var5 += BlockLiquid.getLiquidHeightPercent(var10) * 10.0F;
               var4 += 10;
            }

            var5 += BlockLiquid.getLiquidHeightPercent(var10);
            var4++;
         }
      }

      return 1.0F - var5 / var4;
   }

   public boolean renderFluid(IBlockAccess var1, IBlockState var2, BlockPos var3, WorldRenderer var4) {
      boolean var5;
      try {
         if (Config.isShaders()) {
            SVertexBuilder.pushEntity(var2, var3, var1, var4);
         }

         BlockLiquid var6 = (BlockLiquid)var2.getBlock();
         var6.setBlockBoundsBasedOnState(var1, var3);
         TextureAtlasSprite[] var7 = var6.getMaterial() == Material.lava ? this.atlasSpritesLava : this.atlasSpritesWater;
         RenderEnv var8 = var4.getRenderEnv(var2, var3);
         int var9 = CustomColors.getFluidColor(var1, var2, var3, var8);
         float var10 = (var9 >> 16 & 0xFF) / 255.0F;
         float var11 = (var9 >> 8 & 0xFF) / 255.0F;
         float var12 = (var9 & 0xFF) / 255.0F;
         boolean var13 = var6.shouldSideBeRendered(var1, var3.up(), EnumFacing.UP);
         boolean var14 = var6.shouldSideBeRendered(var1, var3.down(), EnumFacing.DOWN);
         boolean[] var15 = var8.getBorderFlags();
         var15[0] = var6.shouldSideBeRendered(var1, var3.north(), EnumFacing.NORTH);
         var15[1] = var6.shouldSideBeRendered(var1, var3.south(), EnumFacing.SOUTH);
         var15[2] = var6.shouldSideBeRendered(var1, var3.west(), EnumFacing.WEST);
         var15[3] = var6.shouldSideBeRendered(var1, var3.east(), EnumFacing.EAST);
         if (var13 || var14 || var15[0] || var15[1] || var15[2] || var15[3]) {
            var5 = false;
            float var16 = 0.5F;
            float var17 = 1.0F;
            float var18 = 0.8F;
            float var19 = 0.6F;
            Material var20 = var6.getMaterial();
            float var21 = this.getFluidHeight(var1, var3, var20);
            float var22 = this.getFluidHeight(var1, var3.south(), var20);
            float var23 = this.getFluidHeight(var1, var3.east().south(), var20);
            float var24 = this.getFluidHeight(var1, var3.east(), var20);
            double var25 = var3.getX();
            double var27 = var3.getY();
            double var29 = var3.getZ();
            float var31 = 0.001F;
            if (var13) {
               var5 = true;
               TextureAtlasSprite var32 = var7[0];
               float var33 = (float)BlockLiquid.getFlowDirection(var1, var3, var20);
               if (var33 > -999.0F) {
                  var32 = var7[1];
               }

               var4.setSprite(var32);
               var21 -= var31;
               var22 -= var31;
               var23 -= var31;
               var24 -= var31;
               float var34;
               float var35;
               float var36;
               float var37;
               float var38;
               float var39;
               float var40;
               float var41;
               if (var33 < -999.0F) {
                  var34 = var32.getInterpolatedU(0.0);
                  var38 = var32.getInterpolatedV(0.0);
                  var35 = var34;
                  var39 = var32.getInterpolatedV(16.0);
                  var36 = var32.getInterpolatedU(16.0);
                  var40 = var39;
                  var37 = var36;
                  var41 = var38;
               } else {
                  float var42 = MathHelper.sin(var33) * 0.25F;
                  float var43 = MathHelper.cos(var33) * 0.25F;
                  float var44 = 8.0F;
                  var34 = var32.getInterpolatedU(8.0F + (-var43 - var42) * 16.0F);
                  var38 = var32.getInterpolatedV(8.0F + (-var43 + var42) * 16.0F);
                  var35 = var32.getInterpolatedU(8.0F + (-var43 + var42) * 16.0F);
                  var39 = var32.getInterpolatedV(8.0F + (var43 + var42) * 16.0F);
                  var36 = var32.getInterpolatedU(8.0F + (var43 + var42) * 16.0F);
                  var40 = var32.getInterpolatedV(8.0F + (var43 - var42) * 16.0F);
                  var37 = var32.getInterpolatedU(8.0F + (var43 - var42) * 16.0F);
                  var41 = var32.getInterpolatedV(8.0F + (-var43 - var42) * 16.0F);
               }

               int var81 = var6.getMixedBrightnessForBlock(var1, var3);
               int var82 = var81 >> 16 & 65535;
               int var84 = var81 & 65535;
               float var45 = var17 * var10;
               float var46 = var17 * var11;
               float var47 = var17 * var12;
               var4.pos(var25 + 0.0, var27 + var21, var29 + 0.0).color(var45, var46, var47, 1.0F).tex(var34, var38).lightmap(var82, var84).endVertex();
               var4.pos(var25 + 0.0, var27 + var22, var29 + 1.0).color(var45, var46, var47, 1.0F).tex(var35, var39).lightmap(var82, var84).endVertex();
               var4.pos(var25 + 1.0, var27 + var23, var29 + 1.0).color(var45, var46, var47, 1.0F).tex(var36, var40).lightmap(var82, var84).endVertex();
               var4.pos(var25 + 1.0, var27 + var24, var29 + 0.0).color(var45, var46, var47, 1.0F).tex(var37, var41).lightmap(var82, var84).endVertex();
               if (var6.shouldRenderSides(var1, var3.up())) {
                  var4.pos(var25 + 0.0, var27 + var21, var29 + 0.0).color(var45, var46, var47, 1.0F).tex(var34, var38).lightmap(var82, var84).endVertex();
                  var4.pos(var25 + 1.0, var27 + var24, var29 + 0.0).color(var45, var46, var47, 1.0F).tex(var37, var41).lightmap(var82, var84).endVertex();
                  var4.pos(var25 + 1.0, var27 + var23, var29 + 1.0).color(var45, var46, var47, 1.0F).tex(var36, var40).lightmap(var82, var84).endVertex();
                  var4.pos(var25 + 0.0, var27 + var22, var29 + 1.0).color(var45, var46, var47, 1.0F).tex(var35, var39).lightmap(var82, var84).endVertex();
               }
            }

            if (var14) {
               var4.setSprite(var7[0]);
               float var63 = var7[0].getMinU();
               float var65 = var7[0].getMaxU();
               float var68 = var7[0].getMinV();
               float var70 = var7[0].getMaxV();
               int var72 = var6.getMixedBrightnessForBlock(var1, var3.down());
               int var74 = var72 >> 16 & 65535;
               int var76 = var72 & 65535;
               float var78 = FaceBakery.getFaceBrightness(EnumFacing.DOWN);
               var4.pos(var25, var27, var29 + 1.0)
                  .color(var10 * var78, var11 * var78, var12 * var78, 1.0F)
                  .tex(var63, var70)
                  .lightmap(var74, var76)
                  .endVertex();
               var4.pos(var25, var27, var29).color(var10 * var78, var11 * var78, var12 * var78, 1.0F).tex(var63, var68).lightmap(var74, var76).endVertex();
               var4.pos(var25 + 1.0, var27, var29)
                  .color(var10 * var78, var11 * var78, var12 * var78, 1.0F)
                  .tex(var65, var68)
                  .lightmap(var74, var76)
                  .endVertex();
               var4.pos(var25 + 1.0, var27, var29 + 1.0)
                  .color(var10 * var78, var11 * var78, var12 * var78, 1.0F)
                  .tex(var65, var70)
                  .lightmap(var74, var76)
                  .endVertex();
               var5 = true;
            }

            for (int var64 = 0; var64 < 4; var64++) {
               int var66 = 0;
               int var69 = 0;
               if (var64 == 0) {
                  var69--;
               }

               if (var64 == 1) {
                  var69++;
               }

               if (var64 == 2) {
                  var66--;
               }

               if (var64 == 3) {
                  var66++;
               }

               BlockPos var71 = var3.add(var66, 0, var69);
               TextureAtlasSprite var73 = var7[1];
               var4.setSprite(var73);
               if (var15[var64]) {
                  float var75;
                  float var77;
                  double var79;
                  double var80;
                  double var83;
                  double var85;
                  if (var64 == 0) {
                     var75 = var21;
                     var77 = var24;
                     var79 = var25;
                     var83 = var25 + 1.0;
                     var80 = var29 + var31;
                     var85 = var29 + var31;
                  } else if (var64 == 1) {
                     var75 = var23;
                     var77 = var22;
                     var79 = var25 + 1.0;
                     var83 = var25;
                     var80 = var29 + 1.0 - var31;
                     var85 = var29 + 1.0 - var31;
                  } else if (var64 == 2) {
                     var75 = var22;
                     var77 = var21;
                     var79 = var25 + var31;
                     var83 = var25 + var31;
                     var80 = var29 + 1.0;
                     var85 = var29;
                  } else {
                     var75 = var24;
                     var77 = var23;
                     var79 = var25 + 1.0 - var31;
                     var83 = var25 + 1.0 - var31;
                     var80 = var29;
                     var85 = var29 + 1.0;
                  }

                  var5 = true;
                  float var86 = var73.getInterpolatedU(0.0);
                  float var48 = var73.getInterpolatedU(8.0);
                  float var49 = var73.getInterpolatedV((1.0F - var75) * 16.0F * 0.5F);
                  float var50 = var73.getInterpolatedV((1.0F - var77) * 16.0F * 0.5F);
                  float var51 = var73.getInterpolatedV(8.0);
                  int var52 = var6.getMixedBrightnessForBlock(var1, var71);
                  int var53 = var52 >> 16 & 65535;
                  int var54 = var52 & 65535;
                  float var55 = var64 < 2 ? FaceBakery.getFaceBrightness(EnumFacing.NORTH) : FaceBakery.getFaceBrightness(EnumFacing.WEST);
                  float var56 = var17 * var55 * var10;
                  float var57 = var17 * var55 * var11;
                  float var58 = var17 * var55 * var12;
                  var4.pos(var79, var27 + var75, var80).color(var56, var57, var58, 1.0F).tex(var86, var49).lightmap(var53, var54).endVertex();
                  var4.pos(var83, var27 + var77, var85).color(var56, var57, var58, 1.0F).tex(var48, var50).lightmap(var53, var54).endVertex();
                  var4.pos(var83, var27 + 0.0, var85).color(var56, var57, var58, 1.0F).tex(var48, var51).lightmap(var53, var54).endVertex();
                  var4.pos(var79, var27 + 0.0, var80).color(var56, var57, var58, 1.0F).tex(var86, var51).lightmap(var53, var54).endVertex();
                  var4.pos(var79, var27 + 0.0, var80).color(var56, var57, var58, 1.0F).tex(var86, var51).lightmap(var53, var54).endVertex();
                  var4.pos(var83, var27 + 0.0, var85).color(var56, var57, var58, 1.0F).tex(var48, var51).lightmap(var53, var54).endVertex();
                  var4.pos(var83, var27 + var77, var85).color(var56, var57, var58, 1.0F).tex(var48, var50).lightmap(var53, var54).endVertex();
                  var4.pos(var79, var27 + var75, var80).color(var56, var57, var58, 1.0F).tex(var86, var49).lightmap(var53, var54).endVertex();
               }
            }

            var4.setSprite((TextureAtlasSprite)null);
            return var5;
         }

         var5 = false;
      } finally {
         if (Config.isShaders()) {
            SVertexBuilder.popEntity(var4);
         }
      }

      return var5;
   }
}
