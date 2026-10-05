package net.minecraft.client.renderer;

import com.cheatbreaker.client.CheatBreaker;
import java.util.BitSet;
import java.util.List;
import java.util.Objects;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockDeadBush;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.src.Config;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.util.Vec3i;
import net.minecraft.world.IBlockAccess;
import net.optifine.BetterSnow;
import net.optifine.CustomColors;
import net.optifine.model.BlockModelCustomizer;
import net.optifine.model.ListQuadsOverlay;
import net.optifine.reflect.Reflector;
import net.optifine.render.RenderEnv;
import net.optifine.shaders.SVertexBuilder;
import net.optifine.shaders.Shaders;

public class BlockModelRenderer {
   public static float aoLightValueOpaque = 0.2F;
   public static boolean separateAoLightValue = false;
   public static EnumWorldBlockLayer[] OVERLAY_LAYERS = new EnumWorldBlockLayer[]{
      EnumWorldBlockLayer.CUTOUT, EnumWorldBlockLayer.CUTOUT_MIPPED, EnumWorldBlockLayer.TRANSLUCENT
   };

   public void renderOverlayModels(
      IBlockAccess var1, IBakedModel var2, IBlockState var3, BlockPos var4, WorldRenderer var5, boolean var6, long var7, RenderEnv var9, boolean var10
   ) {
      if (var9.isOverlaysRendered()) {
         for (int var11 = 0; var11 < OVERLAY_LAYERS.length; var11++) {
            EnumWorldBlockLayer var12 = OVERLAY_LAYERS[var11];
            ListQuadsOverlay var13 = var9.getListQuadsOverlay(var12);
            if (var13.size() > 0) {
               RegionRenderCacheBuilder var14 = var9.getRegionRenderCacheBuilder();
               if (var14 != null) {
                  WorldRenderer var15 = var14.getWorldRendererByLayer(var12);
                  if (!var15.isDrawing()) {
                     var15.begin(7, DefaultVertexFormats.BLOCK);
                     var15.setTranslation(var5.getXOffset(), var5.getYOffset(), var5.getZOffset());
                  }

                  for (int var16 = 0; var16 < var13.size(); var16++) {
                     BakedQuad var17 = var13.getQuad(var16);
                     List var18 = var13.getListQuadsSingle(var17);
                     IBlockState var19 = var13.getBlockState(var16);
                     if (var17.getQuadEmissive() != null) {
                        var13.addQuad(var17.getQuadEmissive(), var19);
                     }

                     var9.reset(var19, var4);
                     if (var10) {
                        this.renderQuadsSmooth(var1, var19, var4, var15, var18, var9);
                     } else {
                        int var20 = var19.getBlock().getMixedBrightnessForBlock(var1, var4.a(var17.getFace()));
                        this.renderQuadsFlat(var1, var19, var4, var17.getFace(), var20, false, var15, var18, var9);
                     }
                  }
               }

               var13.clear();
            }
         }
      }

      if (Config.isBetterSnow() && !var9.isBreakingAnimation() && BetterSnow.shouldRender(var1, var3, var4)) {
         IBakedModel var21 = BetterSnow.getModelSnowLayer();
         IBlockState var22 = BetterSnow.getStateSnowLayer();
         this.renderModel(var1, var21, var22, var4, var5, var6);
      }
   }

   public void renderModelBrightnessColor(IBakedModel var1, float var2, float var3, float var4, float var5) {
      for (EnumFacing var9 : EnumFacing.VALUES) {
         this.renderModelBrightnessColorQuads(var2, var3, var4, var5, var1.getFaceQuads(var9));
      }

      this.renderModelBrightnessColorQuads(var2, var3, var4, var5, var1.getGeneralQuads());
   }

   public BlockModelRenderer() {
      if (Reflector.ForgeModContainer_forgeLightPipelineEnabled.exists()) {
         Reflector.setFieldValue(Reflector.ForgeModContainer_forgeLightPipelineEnabled, false);
      }
   }

   public boolean renderModel(IBlockAccess var1, IBakedModel var2, IBlockState var3, BlockPos var4, WorldRenderer var5, boolean var6) {
      boolean var7 = Minecraft.isAmbientOcclusionEnabled() && var3.getBlock().getLightValue() == 0 && var2.isAmbientOcclusion();
      String var8 = (String)CheatBreaker.getInstance().getModuleManager().recoveredField1727.recoveredField1523.getValue();
      if (Objects.equals(var8, "NO")
         || (
            var3.getBlock() instanceof BlockGlass
               ? !var8.equals("REGULAR") && !var8.equals("ALL")
               : !(var3.getBlock() instanceof BlockStainedGlass) || !var8.equals("ALL")
         )) {
         boolean var9 = CheatBreaker.getInstance().getGlobalSettings().recoveredField532.method_08908();
         if (!var9 || !(var3.getBlock() instanceof BlockTallGrass) && !(var3.getBlock() instanceof BlockDeadBush) && !(var3.getBlock() instanceof BlockBush)) {
            try {
               if (Config.isShaders()) {
                  SVertexBuilder.pushEntity(var3, var4, var1, var5);
               }

               RenderEnv var10 = var5.getRenderEnv(var3, var4);
               var2 = BlockModelCustomizer.getRenderModel(var2, var3, var10);
               boolean var15 = var7 ? this.renderModelSmooth(var1, var2, var3, var4, var5, var6) : this.renderModelFlat(var1, var2, var3, var4, var5, var6);
               if (var15) {
                  this.renderOverlayModels(var1, var2, var3, var4, var5, var6, 0L, var10, var7);
               }

               if (Config.isShaders()) {
                  SVertexBuilder.popEntity(var5);
               }

               return var15;
            } catch (Throwable var13) {
               CrashReport var11 = CrashReport.makeCrashReport(var13, "Tesselating block model");
               CrashReportCategory var12 = var11.makeCategory("Block model being tesselated");
               CrashReportCategory.addBlockInfo(var12, var4, var3);
               var12.addCrashSection("Using AO", var7);
               throw new ReportedException(var11);
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public boolean renderModelFlat(IBlockAccess var1, IBakedModel var2, IBlockState var3, BlockPos var4, WorldRenderer var5, boolean var6) {
      boolean var7 = false;
      Block var8 = var3.getBlock();
      RenderEnv var9 = var5.getRenderEnv(var3, var4);
      EnumWorldBlockLayer var10 = var5.getBlockLayer();

      for (EnumFacing var14 : EnumFacing.VALUES) {
         List var15 = var2.getFaceQuads(var14);
         if (!var15.isEmpty()) {
            BlockPos var16 = var4.a(var14);
            if (!var6 || var8.shouldSideBeRendered(var1, var16, var14)) {
               int var17 = var8.getMixedBrightnessForBlock(var1, var16);
               var15 = BlockModelCustomizer.getRenderQuads(var15, var1, var3, var4, var14, var10, 0L, var9);
               this.renderQuadsFlat(var1, var3, var4, var14, var17, false, var5, var15, var9);
               var7 = true;
            }
         }
      }

      List var18 = var2.getGeneralQuads();
      if (var18.size() > 0) {
         var18 = BlockModelCustomizer.getRenderQuads(var18, var1, var3, var4, (EnumFacing)null, var10, 0L, var9);
         this.renderQuadsFlat(var1, var3, var4, (EnumFacing)null, -1, true, var5, var18, var9);
         var7 = true;
      }

      return var7;
   }

   public void renderQuadsSmooth(IBlockAccess var1, IBlockState var2, BlockPos var3, WorldRenderer var4, List<BakedQuad> var5, RenderEnv var6) {
      Block var7 = var2.getBlock();
      float[] var8 = var6.getQuadBounds();
      BitSet var9 = var6.getBoundsFlags();
      BlockModelRenderer.AmbientOcclusionFace var10 = var6.getAoFace();
      double var11 = var3.getX();
      double var13 = var3.getY();
      double var15 = var3.getZ();
      Block.EnumOffsetType var17 = var7.getOffsetType();
      if (var17 != Block.EnumOffsetType.NONE) {
         long var18 = MathHelper.getPositionRandom(var3);
         var11 += ((float)(var18 >> 16 & 15L) / 15.0F - 0.5) * 0.5;
         var15 += ((float)(var18 >> 24 & 15L) / 15.0F - 0.5) * 0.5;
         if (var17 == Block.EnumOffsetType.XYZ) {
            var13 += ((float)(var18 >> 20 & 15L) / 15.0F - 1.0) * 0.2;
         }
      }

      for (BakedQuad var19 : var5) {
         this.fillQuadBounds(var7, var19.getVertexData(), var19.getFace(), var8, var9);
         var10.updateVertexBrightness(var1, var7, var3, var19.getFace(), var8, var9);
         if (var19.getSprite().isEmissive) {
            var10.setMaxBlockLight();
         }

         if (var4.isMultiTexture()) {
            var4.addVertexData(var19.getVertexDataSingle());
         } else {
            var4.addVertexData(var19.getVertexData());
         }

         var4.putSprite(var19.getSprite());
         var4.putBrightness4(var10.vertexBrightness[0], var10.vertexBrightness[1], var10.vertexBrightness[2], var10.vertexBrightness[3]);
         int var20 = CustomColors.getColorMultiplier(var19, var2, var1, var3, var6);
         if (!var19.hasTintIndex() && var20 == -1) {
            if (separateAoLightValue) {
               var4.putColorMultiplierRgba(1.0F, 1.0F, 1.0F, var10.vertexColorMultiplier[0], 4);
               var4.putColorMultiplierRgba(1.0F, 1.0F, 1.0F, var10.vertexColorMultiplier[1], 3);
               var4.putColorMultiplierRgba(1.0F, 1.0F, 1.0F, var10.vertexColorMultiplier[2], 2);
               var4.putColorMultiplierRgba(1.0F, 1.0F, 1.0F, var10.vertexColorMultiplier[3], 1);
            } else {
               var4.putColorMultiplier(var10.vertexColorMultiplier[0], var10.vertexColorMultiplier[0], var10.vertexColorMultiplier[0], 4);
               var4.putColorMultiplier(var10.vertexColorMultiplier[1], var10.vertexColorMultiplier[1], var10.vertexColorMultiplier[1], 3);
               var4.putColorMultiplier(var10.vertexColorMultiplier[2], var10.vertexColorMultiplier[2], var10.vertexColorMultiplier[2], 2);
               var4.putColorMultiplier(var10.vertexColorMultiplier[3], var10.vertexColorMultiplier[3], var10.vertexColorMultiplier[3], 1);
            }
         } else {
            int var21;
            if (var20 != -1) {
               var21 = var20;
            } else {
               var21 = var7.colorMultiplier(var1, var3, var19.getTintIndex());
            }

            if (EntityRenderer.anaglyphEnable) {
               var21 = TextureUtil.anaglyphColor(var21);
            }

            float var22 = (var21 >> 16 & 0xFF) / 255.0F;
            float var23 = (var21 >> 8 & 0xFF) / 255.0F;
            float var24 = (var21 & 0xFF) / 255.0F;
            if (separateAoLightValue) {
               var4.putColorMultiplierRgba(var22, var23, var24, var10.vertexColorMultiplier[0], 4);
               var4.putColorMultiplierRgba(var22, var23, var24, var10.vertexColorMultiplier[1], 3);
               var4.putColorMultiplierRgba(var22, var23, var24, var10.vertexColorMultiplier[2], 2);
               var4.putColorMultiplierRgba(var22, var23, var24, var10.vertexColorMultiplier[3], 1);
            } else {
               var4.putColorMultiplier(
                  var10.vertexColorMultiplier[0] * var22, var10.vertexColorMultiplier[0] * var23, var10.vertexColorMultiplier[0] * var24, 4
               );
               var4.putColorMultiplier(
                  var10.vertexColorMultiplier[1] * var22, var10.vertexColorMultiplier[1] * var23, var10.vertexColorMultiplier[1] * var24, 3
               );
               var4.putColorMultiplier(
                  var10.vertexColorMultiplier[2] * var22, var10.vertexColorMultiplier[2] * var23, var10.vertexColorMultiplier[2] * var24, 2
               );
               var4.putColorMultiplier(
                  var10.vertexColorMultiplier[3] * var22, var10.vertexColorMultiplier[3] * var23, var10.vertexColorMultiplier[3] * var24, 1
               );
            }
         }

         var4.putPosition(var11, var13, var15);
      }
   }

   public void renderQuadsFlat(
      IBlockAccess var1, IBlockState var2, BlockPos var3, EnumFacing var4, int var5, boolean var6, WorldRenderer var7, List<BakedQuad> var8, RenderEnv var9
   ) {
      Block var10 = var2.getBlock();
      BitSet var11 = var9.getBoundsFlags();
      double var12 = var3.getX();
      double var14 = var3.getY();
      double var16 = var3.getZ();
      Block.EnumOffsetType var18 = var10.getOffsetType();
      if (var18 != Block.EnumOffsetType.NONE) {
         int var19 = var3.getX();
         int var20 = var3.getZ();
         long var21 = var19 * 3129871 ^ var20 * 116129781L;
         var21 = var21 * var21 * 42317861L + var21 * 11L;
         var12 += ((float)(var21 >> 16 & 15L) / 15.0F - 0.5) * 0.5;
         var16 += ((float)(var21 >> 24 & 15L) / 15.0F - 0.5) * 0.5;
         if (var18 == Block.EnumOffsetType.XYZ) {
            var14 += ((float)(var21 >> 20 & 15L) / 15.0F - 1.0) * 0.2;
         }
      }

      for (BakedQuad var27 : var8) {
         if (var6) {
            this.fillQuadBounds(var10, var27.getVertexData(), var27.getFace(), (float[])null, var11);
            var5 = var11.get(0) ? var10.getMixedBrightnessForBlock(var1, var3.a(var27.getFace())) : var10.getMixedBrightnessForBlock(var1, var3);
         }

         if (var27.getSprite().isEmissive) {
            var5 |= 240;
         }

         if (var7.isMultiTexture()) {
            var7.addVertexData(var27.getVertexDataSingle());
         } else {
            var7.addVertexData(var27.getVertexData());
         }

         var7.putSprite(var27.getSprite());
         var7.putBrightness4(var5, var5, var5, var5);
         int var29 = CustomColors.getColorMultiplier(var27, var2, var1, var3, var9);
         if (var27.hasTintIndex() || var29 != -1) {
            int var22;
            if (var29 != -1) {
               var22 = var29;
            } else {
               var22 = var10.colorMultiplier(var1, var3, var27.getTintIndex());
            }

            if (EntityRenderer.anaglyphEnable) {
               var22 = TextureUtil.anaglyphColor(var22);
            }

            float var23 = (var22 >> 16 & 0xFF) / 255.0F;
            float var24 = (var22 >> 8 & 0xFF) / 255.0F;
            float var25 = (var22 & 0xFF) / 255.0F;
            var7.putColorMultiplier(var23, var24, var25, 4);
            var7.putColorMultiplier(var23, var24, var25, 3);
            var7.putColorMultiplier(var23, var24, var25, 2);
            var7.putColorMultiplier(var23, var24, var25, 1);
         }

         var7.putPosition(var12, var14, var16);
      }
   }

   public void renderModelBrightness(IBakedModel var1, IBlockState var2, float var3, boolean var4) {
      Block var5 = var2.getBlock();
      var5.setBlockBoundsForItemRender();
      GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
      int var6 = var5.getRenderColor(var5.getStateForEntityRender(var2));
      if (EntityRenderer.anaglyphEnable) {
         var6 = TextureUtil.anaglyphColor(var6);
      }

      float var7 = (var6 >> 16 & 0xFF) / 255.0F;
      float var8 = (var6 >> 8 & 0xFF) / 255.0F;
      float var9 = (var6 & 0xFF) / 255.0F;
      if (!var4) {
         GlStateManager.color(var3, var3, var3, 1.0F);
      }

      this.renderModelBrightnessColor(var1, var3, var7, var8, var9);
   }

   public static void updateAoLightValue() {
      aoLightValueOpaque = 1.0F - Config.getAmbientOcclusionLevel() * 0.8F;
      separateAoLightValue = Config.isShaders() && Shaders.isSeparateAo();
   }

   public boolean renderModel(IBlockAccess var1, IBakedModel var2, IBlockState var3, BlockPos var4, WorldRenderer var5) {
      Block var6 = var3.getBlock();
      var6.setBlockBoundsBasedOnState(var1, var4);
      return this.renderModel(var1, var2, var3, var4, var5, true);
   }

   public void renderModelBrightnessColorQuads(float var1, float var2, float var3, float var4, List<BakedQuad> var5) {
      Tessellator var6 = Tessellator.getInstance();
      WorldRenderer var7 = var6.getWorldRenderer();

      for (BakedQuad var9 : var5) {
         var7.begin(7, DefaultVertexFormats.ITEM);
         var7.addVertexData(var9.getVertexData());
         var7.putSprite(var9.getSprite());
         if (var9.hasTintIndex()) {
            var7.putColorRGB_F4(var2 * var1, var3 * var1, var4 * var1);
         } else {
            var7.putColorRGB_F4(var1, var1, var1);
         }

         Vec3i var10 = var9.getFace().getDirectionVec();
         var7.putNormal(var10.getX(), var10.getY(), var10.getZ());
         var6.draw();
      }
   }

   public static float fixAoLightValue(float var0) {
      return var0 == 0.2F ? aoLightValueOpaque : var0;
   }

   public void fillQuadBounds(Block var1, int[] var2, EnumFacing var3, float[] var4, BitSet var5) {
      float var6 = 32.0F;
      float var7 = 32.0F;
      float var8 = 32.0F;
      float var9 = -32.0F;
      float var10 = -32.0F;
      float var11 = -32.0F;
      int var12 = var2.length / 4;

      for (int var13 = 0; var13 < 4; var13++) {
         float var14 = Float.intBitsToFloat(var2[var13 * var12]);
         float var15 = Float.intBitsToFloat(var2[var13 * var12 + 1]);
         float var16 = Float.intBitsToFloat(var2[var13 * var12 + 2]);
         var6 = Math.min(var6, var14);
         var7 = Math.min(var7, var15);
         var8 = Math.min(var8, var16);
         var9 = Math.max(var9, var14);
         var10 = Math.max(var10, var15);
         var11 = Math.max(var11, var16);
      }

      if (var4 != null) {
         var4[EnumFacing.WEST.getIndex()] = var6;
         var4[EnumFacing.EAST.getIndex()] = var9;
         var4[EnumFacing.DOWN.getIndex()] = var7;
         var4[EnumFacing.UP.getIndex()] = var10;
         var4[EnumFacing.NORTH.getIndex()] = var8;
         var4[EnumFacing.SOUTH.getIndex()] = var11;
         int var17 = EnumFacing.VALUES.length;
         var4[EnumFacing.WEST.getIndex() + var17] = 1.0F - var6;
         var4[EnumFacing.EAST.getIndex() + var17] = 1.0F - var9;
         var4[EnumFacing.DOWN.getIndex() + var17] = 1.0F - var7;
         var4[EnumFacing.UP.getIndex() + var17] = 1.0F - var10;
         var4[EnumFacing.NORTH.getIndex() + var17] = 1.0F - var8;
         var4[EnumFacing.SOUTH.getIndex() + var17] = 1.0F - var11;
      }

      float var18 = 1.0E-4F;
      float var19 = 0.9999F;
      switch (var3) {
         case DOWN:
            var5.set(1, var6 >= 1.0E-4F || var8 >= 1.0E-4F || var9 <= 0.9999F || var11 <= 0.9999F);
            var5.set(0, (var7 < 1.0E-4F || var1.isFullCube()) && var7 == var10);
            break;
         case UP:
            var5.set(1, var6 >= 1.0E-4F || var8 >= 1.0E-4F || var9 <= 0.9999F || var11 <= 0.9999F);
            var5.set(0, (var10 > 0.9999F || var1.isFullCube()) && var7 == var10);
            break;
         case NORTH:
            var5.set(1, var6 >= 1.0E-4F || var7 >= 1.0E-4F || var9 <= 0.9999F || var10 <= 0.9999F);
            var5.set(0, (var8 < 1.0E-4F || var1.isFullCube()) && var8 == var11);
            break;
         case SOUTH:
            var5.set(1, var6 >= 1.0E-4F || var7 >= 1.0E-4F || var9 <= 0.9999F || var10 <= 0.9999F);
            var5.set(0, (var11 > 0.9999F || var1.isFullCube()) && var8 == var11);
            break;
         case WEST:
            var5.set(1, var7 >= 1.0E-4F || var8 >= 1.0E-4F || var10 <= 0.9999F || var11 <= 0.9999F);
            var5.set(0, (var6 < 1.0E-4F || var1.isFullCube()) && var6 == var9);
            break;
         case EAST:
            var5.set(1, var7 >= 1.0E-4F || var8 >= 1.0E-4F || var10 <= 0.9999F || var11 <= 0.9999F);
            var5.set(0, (var9 > 0.9999F || var1.isFullCube()) && var6 == var9);
      }
   }

   public boolean renderModelSmooth(IBlockAccess var1, IBakedModel var2, IBlockState var3, BlockPos var4, WorldRenderer var5, boolean var6) {
      boolean var7 = false;
      Block var8 = var3.getBlock();
      RenderEnv var9 = var5.getRenderEnv(var3, var4);
      EnumWorldBlockLayer var10 = var5.getBlockLayer();

      for (EnumFacing var14 : EnumFacing.VALUES) {
         List var15 = var2.getFaceQuads(var14);
         if (!var15.isEmpty()) {
            BlockPos var16 = var4.a(var14);
            if (!var6 || var8.shouldSideBeRendered(var1, var16, var14)) {
               var15 = BlockModelCustomizer.getRenderQuads(var15, var1, var3, var4, var14, var10, 0L, var9);
               this.renderQuadsSmooth(var1, var3, var4, var5, var15, var9);
               var7 = true;
            }
         }
      }

      List var17 = var2.getGeneralQuads();
      if (var17.size() > 0) {
         var17 = BlockModelCustomizer.getRenderQuads(var17, var1, var3, var4, (EnumFacing)null, var10, 0L, var9);
         this.renderQuadsSmooth(var1, var3, var4, var5, var17, var9);
         var7 = true;
      }

      return var7;
   }

   public boolean renderModelAmbientOcclusion(IBlockAccess var1, IBakedModel var2, Block var3, BlockPos var4, WorldRenderer var5, boolean var6) {
      IBlockState var7 = var1.getBlockState(var4);
      return this.renderModelSmooth(var1, var2, var7, var4, var5, var6);
   }

   public boolean renderModelStandard(IBlockAccess var1, IBakedModel var2, Block var3, BlockPos var4, WorldRenderer var5, boolean var6) {
      IBlockState var7 = var1.getBlockState(var4);
      return this.renderModelFlat(var1, var2, var7, var4, var5, var6);
   }

   public static class AmbientOcclusionFace {
      public float[] vertexColorMultiplier = new float[4];
      public int[] vertexBrightness = new int[4];

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
         BlockModelRenderer.EnumNeighborInfo var8 = BlockModelRenderer.EnumNeighborInfo.getNeighbourInfo(var4);
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
         BlockModelRenderer.VertexTranslations var35 = BlockModelRenderer.VertexTranslations.getVertexTranslations(var4);
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
            this.vertexColorMultiplier[var35.field_178191_g] = var65 * var40 + var66 * var41 + var67 * var42 + var68 * var43;
            this.vertexColorMultiplier[var35.field_178200_h] = var65 * var44 + var66 * var45 + var67 * var46 + var68 * var47;
            this.vertexColorMultiplier[var35.field_178201_i] = var65 * var48 + var66 * var49 + var67 * var50 + var68 * var51;
            this.vertexColorMultiplier[var35.field_178198_j] = var65 * var52 + var66 * var53 + var67 * var54 + var68 * var55;
            int var56 = this.getAoBrightness(var16, var13, var60, var63);
            int var57 = this.getAoBrightness(var15, var13, var26, var63);
            int var58 = this.getAoBrightness(var15, var14, var61, var63);
            int var59 = this.getAoBrightness(var16, var14, var62, var63);
            this.vertexBrightness[var35.field_178191_g] = this.getVertexBrightness(var56, var57, var58, var59, var40, var41, var42, var43);
            this.vertexBrightness[var35.field_178200_h] = this.getVertexBrightness(var56, var57, var58, var59, var44, var45, var46, var47);
            this.vertexBrightness[var35.field_178201_i] = this.getVertexBrightness(var56, var57, var58, var59, var48, var49, var50, var51);
            this.vertexBrightness[var35.field_178198_j] = this.getVertexBrightness(var56, var57, var58, var59, var52, var53, var54, var55);
         } else {
            float var36 = (var20 + var17 + var28 + var34) * 0.25F;
            float var37 = (var19 + var17 + var25 + var34) * 0.25F;
            float var38 = (var19 + var18 + var30 + var34) * 0.25F;
            float var39 = (var20 + var18 + var32 + var34) * 0.25F;
            this.vertexBrightness[var35.field_178191_g] = this.getAoBrightness(var16, var13, var60, var63);
            this.vertexBrightness[var35.field_178200_h] = this.getAoBrightness(var15, var13, var26, var63);
            this.vertexBrightness[var35.field_178201_i] = this.getAoBrightness(var15, var14, var61, var63);
            this.vertexBrightness[var35.field_178198_j] = this.getAoBrightness(var16, var14, var62, var63);
            this.vertexColorMultiplier[var35.field_178191_g] = var36;
            this.vertexColorMultiplier[var35.field_178200_h] = var37;
            this.vertexColorMultiplier[var35.field_178201_i] = var38;
            this.vertexColorMultiplier[var35.field_178198_j] = var39;
         }
      }

      public AmbientOcclusionFace(BlockModelRenderer var1) {
      }

      public AmbientOcclusionFace() {
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

   public static enum EnumNeighborInfo {
      DOWN(
         new EnumFacing[]{EnumFacing.WEST, EnumFacing.EAST, EnumFacing.NORTH, EnumFacing.SOUTH},
         0.5F,
         false,
         new BlockModelRenderer.Orientation[0],
         new BlockModelRenderer.Orientation[0],
         new BlockModelRenderer.Orientation[0],
         new BlockModelRenderer.Orientation[0]
      ),
      UP(
         new EnumFacing[]{EnumFacing.EAST, EnumFacing.WEST, EnumFacing.NORTH, EnumFacing.SOUTH},
         1.0F,
         false,
         new BlockModelRenderer.Orientation[0],
         new BlockModelRenderer.Orientation[0],
         new BlockModelRenderer.Orientation[0],
         new BlockModelRenderer.Orientation[0]
      ),
      NORTH(
         new EnumFacing[]{EnumFacing.UP, EnumFacing.DOWN, EnumFacing.EAST, EnumFacing.WEST},
         0.8F,
         true,
         new BlockModelRenderer.Orientation[]{
            BlockModelRenderer.Orientation.UP,
            BlockModelRenderer.Orientation.FLIP_WEST,
            BlockModelRenderer.Orientation.UP,
            BlockModelRenderer.Orientation.WEST,
            BlockModelRenderer.Orientation.FLIP_UP,
            BlockModelRenderer.Orientation.WEST,
            BlockModelRenderer.Orientation.FLIP_UP,
            BlockModelRenderer.Orientation.FLIP_WEST
         },
         new BlockModelRenderer.Orientation[]{
            BlockModelRenderer.Orientation.UP,
            BlockModelRenderer.Orientation.FLIP_EAST,
            BlockModelRenderer.Orientation.UP,
            BlockModelRenderer.Orientation.EAST,
            BlockModelRenderer.Orientation.FLIP_UP,
            BlockModelRenderer.Orientation.EAST,
            BlockModelRenderer.Orientation.FLIP_UP,
            BlockModelRenderer.Orientation.FLIP_EAST
         },
         new BlockModelRenderer.Orientation[]{
            BlockModelRenderer.Orientation.DOWN,
            BlockModelRenderer.Orientation.FLIP_EAST,
            BlockModelRenderer.Orientation.DOWN,
            BlockModelRenderer.Orientation.EAST,
            BlockModelRenderer.Orientation.FLIP_DOWN,
            BlockModelRenderer.Orientation.EAST,
            BlockModelRenderer.Orientation.FLIP_DOWN,
            BlockModelRenderer.Orientation.FLIP_EAST
         },
         new BlockModelRenderer.Orientation[]{
            BlockModelRenderer.Orientation.DOWN,
            BlockModelRenderer.Orientation.FLIP_WEST,
            BlockModelRenderer.Orientation.DOWN,
            BlockModelRenderer.Orientation.WEST,
            BlockModelRenderer.Orientation.FLIP_DOWN,
            BlockModelRenderer.Orientation.WEST,
            BlockModelRenderer.Orientation.FLIP_DOWN,
            BlockModelRenderer.Orientation.FLIP_WEST
         }
      ),
      SOUTH(
         new EnumFacing[]{EnumFacing.WEST, EnumFacing.EAST, EnumFacing.DOWN, EnumFacing.UP},
         0.8F,
         true,
         new BlockModelRenderer.Orientation[]{
            BlockModelRenderer.Orientation.UP,
            BlockModelRenderer.Orientation.FLIP_WEST,
            BlockModelRenderer.Orientation.FLIP_UP,
            BlockModelRenderer.Orientation.FLIP_WEST,
            BlockModelRenderer.Orientation.FLIP_UP,
            BlockModelRenderer.Orientation.WEST,
            BlockModelRenderer.Orientation.UP,
            BlockModelRenderer.Orientation.WEST
         },
         new BlockModelRenderer.Orientation[]{
            BlockModelRenderer.Orientation.DOWN,
            BlockModelRenderer.Orientation.FLIP_WEST,
            BlockModelRenderer.Orientation.FLIP_DOWN,
            BlockModelRenderer.Orientation.FLIP_WEST,
            BlockModelRenderer.Orientation.FLIP_DOWN,
            BlockModelRenderer.Orientation.WEST,
            BlockModelRenderer.Orientation.DOWN,
            BlockModelRenderer.Orientation.WEST
         },
         new BlockModelRenderer.Orientation[]{
            BlockModelRenderer.Orientation.DOWN,
            BlockModelRenderer.Orientation.FLIP_EAST,
            BlockModelRenderer.Orientation.FLIP_DOWN,
            BlockModelRenderer.Orientation.FLIP_EAST,
            BlockModelRenderer.Orientation.FLIP_DOWN,
            BlockModelRenderer.Orientation.EAST,
            BlockModelRenderer.Orientation.DOWN,
            BlockModelRenderer.Orientation.EAST
         },
         new BlockModelRenderer.Orientation[]{
            BlockModelRenderer.Orientation.UP,
            BlockModelRenderer.Orientation.FLIP_EAST,
            BlockModelRenderer.Orientation.FLIP_UP,
            BlockModelRenderer.Orientation.FLIP_EAST,
            BlockModelRenderer.Orientation.FLIP_UP,
            BlockModelRenderer.Orientation.EAST,
            BlockModelRenderer.Orientation.UP,
            BlockModelRenderer.Orientation.EAST
         }
      ),
      WEST(
         new EnumFacing[]{EnumFacing.UP, EnumFacing.DOWN, EnumFacing.NORTH, EnumFacing.SOUTH},
         0.6F,
         true,
         new BlockModelRenderer.Orientation[]{
            BlockModelRenderer.Orientation.UP,
            BlockModelRenderer.Orientation.SOUTH,
            BlockModelRenderer.Orientation.UP,
            BlockModelRenderer.Orientation.FLIP_SOUTH,
            BlockModelRenderer.Orientation.FLIP_UP,
            BlockModelRenderer.Orientation.FLIP_SOUTH,
            BlockModelRenderer.Orientation.FLIP_UP,
            BlockModelRenderer.Orientation.SOUTH
         },
         new BlockModelRenderer.Orientation[]{
            BlockModelRenderer.Orientation.UP,
            BlockModelRenderer.Orientation.NORTH,
            BlockModelRenderer.Orientation.UP,
            BlockModelRenderer.Orientation.FLIP_NORTH,
            BlockModelRenderer.Orientation.FLIP_UP,
            BlockModelRenderer.Orientation.FLIP_NORTH,
            BlockModelRenderer.Orientation.FLIP_UP,
            BlockModelRenderer.Orientation.NORTH
         },
         new BlockModelRenderer.Orientation[]{
            BlockModelRenderer.Orientation.DOWN,
            BlockModelRenderer.Orientation.NORTH,
            BlockModelRenderer.Orientation.DOWN,
            BlockModelRenderer.Orientation.FLIP_NORTH,
            BlockModelRenderer.Orientation.FLIP_DOWN,
            BlockModelRenderer.Orientation.FLIP_NORTH,
            BlockModelRenderer.Orientation.FLIP_DOWN,
            BlockModelRenderer.Orientation.NORTH
         },
         new BlockModelRenderer.Orientation[]{
            BlockModelRenderer.Orientation.DOWN,
            BlockModelRenderer.Orientation.SOUTH,
            BlockModelRenderer.Orientation.DOWN,
            BlockModelRenderer.Orientation.FLIP_SOUTH,
            BlockModelRenderer.Orientation.FLIP_DOWN,
            BlockModelRenderer.Orientation.FLIP_SOUTH,
            BlockModelRenderer.Orientation.FLIP_DOWN,
            BlockModelRenderer.Orientation.SOUTH
         }
      ),
      EAST(
         new EnumFacing[]{EnumFacing.DOWN, EnumFacing.UP, EnumFacing.NORTH, EnumFacing.SOUTH},
         0.6F,
         true,
         new BlockModelRenderer.Orientation[]{
            BlockModelRenderer.Orientation.FLIP_DOWN,
            BlockModelRenderer.Orientation.SOUTH,
            BlockModelRenderer.Orientation.FLIP_DOWN,
            BlockModelRenderer.Orientation.FLIP_SOUTH,
            BlockModelRenderer.Orientation.DOWN,
            BlockModelRenderer.Orientation.FLIP_SOUTH,
            BlockModelRenderer.Orientation.DOWN,
            BlockModelRenderer.Orientation.SOUTH
         },
         new BlockModelRenderer.Orientation[]{
            BlockModelRenderer.Orientation.FLIP_DOWN,
            BlockModelRenderer.Orientation.NORTH,
            BlockModelRenderer.Orientation.FLIP_DOWN,
            BlockModelRenderer.Orientation.FLIP_NORTH,
            BlockModelRenderer.Orientation.DOWN,
            BlockModelRenderer.Orientation.FLIP_NORTH,
            BlockModelRenderer.Orientation.DOWN,
            BlockModelRenderer.Orientation.NORTH
         },
         new BlockModelRenderer.Orientation[]{
            BlockModelRenderer.Orientation.FLIP_UP,
            BlockModelRenderer.Orientation.NORTH,
            BlockModelRenderer.Orientation.FLIP_UP,
            BlockModelRenderer.Orientation.FLIP_NORTH,
            BlockModelRenderer.Orientation.UP,
            BlockModelRenderer.Orientation.FLIP_NORTH,
            BlockModelRenderer.Orientation.UP,
            BlockModelRenderer.Orientation.NORTH
         },
         new BlockModelRenderer.Orientation[]{
            BlockModelRenderer.Orientation.FLIP_UP,
            BlockModelRenderer.Orientation.SOUTH,
            BlockModelRenderer.Orientation.FLIP_UP,
            BlockModelRenderer.Orientation.FLIP_SOUTH,
            BlockModelRenderer.Orientation.UP,
            BlockModelRenderer.Orientation.FLIP_SOUTH,
            BlockModelRenderer.Orientation.UP,
            BlockModelRenderer.Orientation.SOUTH
         }
      );
      public EnumFacing[] field_178276_g;
      public BlockModelRenderer.Orientation[] field_178285_m;
      public boolean field_178289_i;
      public float field_178288_h;
      public BlockModelRenderer.Orientation[] field_178286_j;
      // $VF: synthetic field
      public static BlockModelRenderer.EnumNeighborInfo[] $VALUES = new BlockModelRenderer.EnumNeighborInfo[]{
         BlockModelRenderer.EnumNeighborInfo.DOWN,
         BlockModelRenderer.EnumNeighborInfo.UP,
         BlockModelRenderer.EnumNeighborInfo.NORTH,
         SOUTH,
         BlockModelRenderer.EnumNeighborInfo.WEST,
         EAST
      };
      public BlockModelRenderer.Orientation[] field_178287_k;
      public BlockModelRenderer.Orientation[] field_178284_l;
      public static BlockModelRenderer.EnumNeighborInfo[] VALUES = new BlockModelRenderer.EnumNeighborInfo[6];

      EnumNeighborInfo(
         EnumFacing[] var3,
         float var4,
         boolean var5,
         BlockModelRenderer.Orientation[] var6,
         BlockModelRenderer.Orientation[] var7,
         BlockModelRenderer.Orientation[] var8,
         BlockModelRenderer.Orientation[] var9
      ) {
         this.field_178276_g = var3;
         this.field_178288_h = var4;
         this.field_178289_i = var5;
         this.field_178286_j = var6;
         this.field_178287_k = var7;
         this.field_178284_l = var8;
         this.field_178285_m = var9;
      }

      static {
         VALUES[EnumFacing.DOWN.getIndex()] = DOWN;
         VALUES[EnumFacing.UP.getIndex()] = UP;
         VALUES[EnumFacing.NORTH.getIndex()] = NORTH;
         VALUES[EnumFacing.SOUTH.getIndex()] = SOUTH;
         VALUES[EnumFacing.WEST.getIndex()] = WEST;
         VALUES[EnumFacing.EAST.getIndex()] = EAST;
      }

      public static BlockModelRenderer.EnumNeighborInfo getNeighbourInfo(EnumFacing var0) {
         return VALUES[var0.getIndex()];
      }
   }

   public static enum Orientation {
      DOWN(EnumFacing.DOWN, false),
      UP(EnumFacing.UP, false),
      NORTH(EnumFacing.NORTH, false),
      SOUTH(EnumFacing.SOUTH, false),
      WEST(EnumFacing.WEST, false),
      EAST(EnumFacing.EAST, false),
      FLIP_DOWN(EnumFacing.DOWN, true),
      FLIP_UP(EnumFacing.UP, true),
      FLIP_NORTH(EnumFacing.NORTH, true),
      FLIP_SOUTH(EnumFacing.SOUTH, true),
      FLIP_WEST(EnumFacing.WEST, true),
      FLIP_EAST(EnumFacing.EAST, true);
      // $VF: synthetic field
      public static BlockModelRenderer.Orientation[] $VALUES = new BlockModelRenderer.Orientation[]{
         BlockModelRenderer.Orientation.DOWN,
         BlockModelRenderer.Orientation.UP,
         BlockModelRenderer.Orientation.NORTH,
         BlockModelRenderer.Orientation.SOUTH,
         WEST,
         BlockModelRenderer.Orientation.EAST,
         FLIP_DOWN,
         BlockModelRenderer.Orientation.FLIP_UP,
         BlockModelRenderer.Orientation.FLIP_NORTH,
         BlockModelRenderer.Orientation.FLIP_SOUTH,
         BlockModelRenderer.Orientation.FLIP_WEST,
         BlockModelRenderer.Orientation.FLIP_EAST
      };
      public int field_178229_m;

      Orientation(EnumFacing var3, boolean var4) {
         this.field_178229_m = var3.getIndex() + (var4 ? EnumFacing.values().length : 0);
      }
   }

   public static enum VertexTranslations {
      DOWN(0, 1, 2, 3),
      UP(2, 3, 0, 1),
      NORTH(3, 0, 1, 2),
      SOUTH(0, 1, 2, 3),
      WEST(3, 0, 1, 2),
      EAST(1, 2, 3, 0);
      // $VF: synthetic field
      public static BlockModelRenderer.VertexTranslations[] $VALUES = new BlockModelRenderer.VertexTranslations[]{
         BlockModelRenderer.VertexTranslations.DOWN,
         BlockModelRenderer.VertexTranslations.UP,
         BlockModelRenderer.VertexTranslations.NORTH,
         BlockModelRenderer.VertexTranslations.SOUTH,
         WEST,
         BlockModelRenderer.VertexTranslations.EAST
      };
      public int field_178198_j;
      public int field_178201_i;
      public int field_178191_g;
      public static BlockModelRenderer.VertexTranslations[] VALUES = new BlockModelRenderer.VertexTranslations[6];
      public int field_178200_h;

      static {
         VALUES[EnumFacing.DOWN.getIndex()] = DOWN;
         VALUES[EnumFacing.UP.getIndex()] = UP;
         VALUES[EnumFacing.NORTH.getIndex()] = NORTH;
         VALUES[EnumFacing.SOUTH.getIndex()] = SOUTH;
         VALUES[EnumFacing.WEST.getIndex()] = WEST;
         VALUES[EnumFacing.EAST.getIndex()] = EAST;
      }

      public static BlockModelRenderer.VertexTranslations getVertexTranslations(EnumFacing var0) {
         return VALUES[var0.getIndex()];
      }

      VertexTranslations(int var3, int var4, int var5, int var6) {
         this.field_178191_g = var3;
         this.field_178200_h = var4;
         this.field_178201_i = var5;
         this.field_178198_j = var6;
      }
   }
}
