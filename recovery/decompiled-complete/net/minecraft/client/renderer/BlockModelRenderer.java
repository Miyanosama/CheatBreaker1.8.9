package net.minecraft.client.renderer;

import com.cheatbreaker.client.CheatBreaker;
import java.util.BitSet;
import java.util.List;
import java.util.Objects;
import net.minecraft.block.Block;
import net.minecraft.block.Block$EnumOffsetType;
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
import org.java_websocket.exceptions.InvalidHandshakeException;

public class BlockModelRenderer {
   public InvalidHandshakeException field_0001;
   public static boolean separateAoLightValue = false;
   public static EnumWorldBlockLayer[] OVERLAY_LAYERS = new EnumWorldBlockLayer[]{
      EnumWorldBlockLayer.CUTOUT, EnumWorldBlockLayer.CUTOUT_MIPPED, EnumWorldBlockLayer.TRANSLUCENT
   };
   public static float aoLightValueOpaque = 0.2F;

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
      String var8 = (String)CheatBreaker.getInstance().getModuleManager().field_0047.field_0004.getValue();
      if (Objects.equals(var8, "NO")
         || (
            var3.getBlock() instanceof BlockGlass
               ? !var8.equals("REGULAR") && !var8.equals("ALL")
               : !(var3.getBlock() instanceof BlockStainedGlass) || !var8.equals("ALL")
         )) {
         boolean var9 = CheatBreaker.getInstance().getGlobalSettings().field_0073.method_08908();
         if (!var9 || !(var3.getBlock() instanceof BlockTallGrass) && !(var3.getBlock() instanceof BlockDeadBush) && !(var3.getBlock() instanceof BlockBush)) {
            try {
               if (Config.isShaders()) {
                  SVertexBuilder.pushEntity(var3, var4, var1, var5);
               }

               RenderEnv var10 = var5.getRenderEnv(var3, var4);
               var2 = BlockModelCustomizer.getRenderModel(var2, var3, var10);
               boolean var15 = var7 ? this.renderModelSmooth(var1, var2, var3, var4, var5, var6) : this.renderModelFlat(var1, var2, var3, var4, var5, var6);
               if (var15) {
                  this.renderOverlayModels(var1, var2, var3, var4, var5, var6, 671356482L & 269615373L, var10, var7);
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
               var15 = BlockModelCustomizer.getRenderQuads(var15, var1, var3, var4, var14, var10, -8867299734597622656L & 8867299734511945728L, var9);
               this.renderQuadsFlat(var1, var3, var4, var14, var17, false, var5, var15, var9);
               var7 = true;
            }
         }
      }

      List var18 = var2.getGeneralQuads();
      if (var18.size() > 0) {
         var18 = BlockModelCustomizer.getRenderQuads(var18, var1, var3, var4, (EnumFacing)null, var10, 131129L & 488911492L, var9);
         this.renderQuadsFlat(var1, var3, var4, (EnumFacing)null, -1, true, var5, var18, var9);
         var7 = true;
      }

      return var7;
   }

   public void renderQuadsSmooth(IBlockAccess var1, IBlockState var2, BlockPos var3, WorldRenderer var4, List<BakedQuad> var5, RenderEnv var6) {
      Block var7 = var2.getBlock();
      float[] var8 = var6.getQuadBounds();
      BitSet var9 = var6.getBoundsFlags();
      BlockModelRenderer$AmbientOcclusionFace var10 = var6.getAoFace();
      double var11 = var3.getX();
      double var13 = var3.getY();
      double var15 = var3.getZ();
      Block$EnumOffsetType var17 = var7.getOffsetType();
      if (var17 != Block$EnumOffsetType.NONE) {
         long var18 = MathHelper.getPositionRandom(var3);
         var11 += ((float)(var18 >> 16 & 4417055L & 1627916303L) / 15.0F - 0.5) * 0.5;
         var15 += ((float)(var18 >> 24 & 1402339049905258655L & -1402339050503004113L) / 15.0F - 0.5) * 0.5;
         if (var17 == Block$EnumOffsetType.XYZ) {
            var13 += ((float)(var18 >> 20 & 268452143L & 1111625807L) / 15.0F - 1.0) * 0.2;
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
         var4.putBrightness4(
            BlockModelRenderer$AmbientOcclusionFace.access$000(var10)[0],
            BlockModelRenderer$AmbientOcclusionFace.access$000(var10)[1],
            BlockModelRenderer$AmbientOcclusionFace.access$000(var10)[2],
            BlockModelRenderer$AmbientOcclusionFace.access$000(var10)[3]
         );
         int var20 = CustomColors.getColorMultiplier(var19, var2, var1, var3, var6);
         if (!var19.hasTintIndex() && var20 == -1) {
            if (separateAoLightValue) {
               var4.putColorMultiplierRgba(1.0F, 1.0F, 1.0F, BlockModelRenderer$AmbientOcclusionFace.access$100(var10)[0], 4);
               var4.putColorMultiplierRgba(1.0F, 1.0F, 1.0F, BlockModelRenderer$AmbientOcclusionFace.access$100(var10)[1], 3);
               var4.putColorMultiplierRgba(1.0F, 1.0F, 1.0F, BlockModelRenderer$AmbientOcclusionFace.access$100(var10)[2], 2);
               var4.putColorMultiplierRgba(1.0F, 1.0F, 1.0F, BlockModelRenderer$AmbientOcclusionFace.access$100(var10)[3], 1);
            } else {
               var4.putColorMultiplier(
                  BlockModelRenderer$AmbientOcclusionFace.access$100(var10)[0],
                  BlockModelRenderer$AmbientOcclusionFace.access$100(var10)[0],
                  BlockModelRenderer$AmbientOcclusionFace.access$100(var10)[0],
                  4
               );
               var4.putColorMultiplier(
                  BlockModelRenderer$AmbientOcclusionFace.access$100(var10)[1],
                  BlockModelRenderer$AmbientOcclusionFace.access$100(var10)[1],
                  BlockModelRenderer$AmbientOcclusionFace.access$100(var10)[1],
                  3
               );
               var4.putColorMultiplier(
                  BlockModelRenderer$AmbientOcclusionFace.access$100(var10)[2],
                  BlockModelRenderer$AmbientOcclusionFace.access$100(var10)[2],
                  BlockModelRenderer$AmbientOcclusionFace.access$100(var10)[2],
                  2
               );
               var4.putColorMultiplier(
                  BlockModelRenderer$AmbientOcclusionFace.access$100(var10)[3],
                  BlockModelRenderer$AmbientOcclusionFace.access$100(var10)[3],
                  BlockModelRenderer$AmbientOcclusionFace.access$100(var10)[3],
                  1
               );
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
               var4.putColorMultiplierRgba(var22, var23, var24, BlockModelRenderer$AmbientOcclusionFace.access$100(var10)[0], 4);
               var4.putColorMultiplierRgba(var22, var23, var24, BlockModelRenderer$AmbientOcclusionFace.access$100(var10)[1], 3);
               var4.putColorMultiplierRgba(var22, var23, var24, BlockModelRenderer$AmbientOcclusionFace.access$100(var10)[2], 2);
               var4.putColorMultiplierRgba(var22, var23, var24, BlockModelRenderer$AmbientOcclusionFace.access$100(var10)[3], 1);
            } else {
               var4.putColorMultiplier(
                  BlockModelRenderer$AmbientOcclusionFace.access$100(var10)[0] * var22,
                  BlockModelRenderer$AmbientOcclusionFace.access$100(var10)[0] * var23,
                  BlockModelRenderer$AmbientOcclusionFace.access$100(var10)[0] * var24,
                  4
               );
               var4.putColorMultiplier(
                  BlockModelRenderer$AmbientOcclusionFace.access$100(var10)[1] * var22,
                  BlockModelRenderer$AmbientOcclusionFace.access$100(var10)[1] * var23,
                  BlockModelRenderer$AmbientOcclusionFace.access$100(var10)[1] * var24,
                  3
               );
               var4.putColorMultiplier(
                  BlockModelRenderer$AmbientOcclusionFace.access$100(var10)[2] * var22,
                  BlockModelRenderer$AmbientOcclusionFace.access$100(var10)[2] * var23,
                  BlockModelRenderer$AmbientOcclusionFace.access$100(var10)[2] * var24,
                  2
               );
               var4.putColorMultiplier(
                  BlockModelRenderer$AmbientOcclusionFace.access$100(var10)[3] * var22,
                  BlockModelRenderer$AmbientOcclusionFace.access$100(var10)[3] * var23,
                  BlockModelRenderer$AmbientOcclusionFace.access$100(var10)[3] * var24,
                  1
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
      Block$EnumOffsetType var18 = var10.getOffsetType();
      if (var18 != Block$EnumOffsetType.NONE) {
         int var19 = var3.getX();
         int var20 = var3.getZ();
         long var21 = var19 * 3129871 ^ var20 * (116129781L & -1818886132407992323L);
         var21 = var21 * var21 * (1385691183L & -4660618067539804619L) + var21 * (2744458615948711947L & -2744458617351240209L);
         var12 += ((float)(var21 >> 16 & 1183107231L & -1690122454104013265L) / 15.0F - 0.5) * 0.5;
         var16 += ((float)(var21 >> 24 & -8006722254987310801L & 537572495L) / 15.0F - 0.5) * 0.5;
         if (var18 == Block$EnumOffsetType.XYZ) {
            var14 += ((float)(var21 >> 20 & 2157882766802813039L & 805732383L) / 15.0F - 1.0) * 0.2;
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
      switch (BlockModelRenderer$1.$SwitchMap$net$minecraft$util$EnumFacing[var3.ordinal()]) {
         case 1:
            var5.set(1, var6 >= 1.0E-4F || var8 >= 1.0E-4F || var9 <= 0.9999F || var11 <= 0.9999F);
            var5.set(0, (var7 < 1.0E-4F || var1.isFullCube()) && var7 == var10);
            break;
         case 2:
            var5.set(1, var6 >= 1.0E-4F || var8 >= 1.0E-4F || var9 <= 0.9999F || var11 <= 0.9999F);
            var5.set(0, (var10 > 0.9999F || var1.isFullCube()) && var7 == var10);
            break;
         case 3:
            var5.set(1, var6 >= 1.0E-4F || var7 >= 1.0E-4F || var9 <= 0.9999F || var10 <= 0.9999F);
            var5.set(0, (var8 < 1.0E-4F || var1.isFullCube()) && var8 == var11);
            break;
         case 4:
            var5.set(1, var6 >= 1.0E-4F || var7 >= 1.0E-4F || var9 <= 0.9999F || var10 <= 0.9999F);
            var5.set(0, (var11 > 0.9999F || var1.isFullCube()) && var8 == var11);
            break;
         case 5:
            var5.set(1, var7 >= 1.0E-4F || var8 >= 1.0E-4F || var10 <= 0.9999F || var11 <= 0.9999F);
            var5.set(0, (var6 < 1.0E-4F || var1.isFullCube()) && var6 == var9);
            break;
         case 6:
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
               var15 = BlockModelCustomizer.getRenderQuads(var15, var1, var3, var4, var14, var10, -4624317329071668414L & 616240160L, var9);
               this.renderQuadsSmooth(var1, var3, var4, var5, var15, var9);
               var7 = true;
            }
         }
      }

      List var17 = var2.getGeneralQuads();
      if (var17.size() > 0) {
         var17 = BlockModelCustomizer.getRenderQuads(var17, var1, var3, var4, (EnumFacing)null, var10, 214435072L & 539271174L, var9);
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
}
