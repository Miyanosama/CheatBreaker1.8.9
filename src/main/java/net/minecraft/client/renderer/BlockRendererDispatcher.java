package net.minecraft.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.SimpleBakedModel;
import net.minecraft.client.resources.model.WeightedBakedModel;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.WorldType;

public class BlockRendererDispatcher implements IResourceManagerReloadListener {
   public BlockFluidRenderer fluidRenderer;
   public BlockModelRenderer blockModelRenderer = new BlockModelRenderer();
   public GameSettings gameSettings;
   public ChestRenderer chestRenderer = new ChestRenderer();
   public BlockModelShapes blockModelShapes;

   public IBakedModel getModelFromBlockState(IBlockState var1, IBlockAccess var2, BlockPos var3) {
      Block var4 = var1.getBlock();
      if (var2.x_() != WorldType.DEBUG_WORLD) {
         try {
            var1 = var4.getActualState(var1, var2, var3);
         } catch (Exception var6) {
         }
      }

      IBakedModel var5 = this.blockModelShapes.getModelForState(var1);
      if (var3 != null && this.gameSettings.allowBlockAlternatives && var5 instanceof WeightedBakedModel) {
         var5 = ((WeightedBakedModel)var5).getAlternativeModel(MathHelper.getPositionRandom(var3));
      }

      return var5;
   }

   public IBakedModel getBakedModel(IBlockState var1, BlockPos var2) {
      IBakedModel var3 = this.blockModelShapes.getModelForState(var1);
      if (var2 != null && this.gameSettings.allowBlockAlternatives && var3 instanceof WeightedBakedModel) {
         var3 = ((WeightedBakedModel)var3).getAlternativeModel(MathHelper.getPositionRandom(var2));
      }

      return var3;
   }

   @Override
   public void onResourceManagerReload(IResourceManager var1) {
      this.fluidRenderer.initAtlasSprites();
   }

   public boolean renderBlock(IBlockState var1, BlockPos var2, IBlockAccess var3, WorldRenderer var4) {
      try {
         int var5 = var1.getBlock().getRenderType();
         if (var5 == -1) {
            return false;
         } else {
            switch (var5) {
               case 1:
                  return this.fluidRenderer.renderFluid(var3, var1, var2, var4);
               case 2:
                  return false;
               case 3:
                  IBakedModel var9 = this.getModelFromBlockState(var1, var3, var2);
                  return this.blockModelRenderer.renderModel(var3, var9, var1, var2, var4);
               default:
                  return false;
            }
         }
      } catch (Throwable var8) {
         CrashReport var6 = CrashReport.makeCrashReport(var8, "Tesselating block in world");
         CrashReportCategory var7 = var6.makeCategory("Block being tesselated");
         CrashReportCategory.addBlockInfo(var7, var2, var1.getBlock(), var1.getBlock().getMetaFromState(var1));
         throw new ReportedException(var6);
      }
   }

   public BlockModelRenderer getBlockModelRenderer() {
      return this.blockModelRenderer;
   }

   public BlockModelShapes getBlockModelShapes() {
      return this.blockModelShapes;
   }

   public BlockRendererDispatcher(BlockModelShapes var1, GameSettings var2) {
      this.fluidRenderer = new BlockFluidRenderer();
      this.blockModelShapes = var1;
      this.gameSettings = var2;
   }

   public void renderBlockDamage(IBlockState var1, BlockPos var2, TextureAtlasSprite var3, IBlockAccess var4) {
      Block var5 = var1.getBlock();
      int var6 = var5.getRenderType();
      if (var6 == 3) {
         var1 = var5.getActualState(var1, var4, var2);
         IBakedModel var7 = this.blockModelShapes.getModelForState(var1);
         IBakedModel var8 = new SimpleBakedModel.Builder(var7, var3).makeBakedModel();
         this.blockModelRenderer.renderModel(var4, var8, var1, var2, Tessellator.getInstance().getWorldRenderer());
      }
   }

   public boolean isRenderTypeChest(Block var1, int var2) {
      if (var1 == null) {
         return false;
      } else {
         int var3 = var1.getRenderType();
         return var3 == 3 ? false : var3 == 2;
      }
   }

   public void renderBlockBrightness(IBlockState var1, float var2) {
      int var3 = var1.getBlock().getRenderType();
      if (var3 != -1) {
         switch (var3) {
            case 1:
            default:
               break;
            case 2:
               this.chestRenderer.renderChestBrightness(var1.getBlock(), var2);
               break;
            case 3:
               IBakedModel var4 = this.getBakedModel(var1, (BlockPos)null);
               this.blockModelRenderer.renderModelBrightness(var4, var1, var2, true);
         }
      }
   }
}
