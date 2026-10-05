package net.minecraft.client.renderer.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.BlockPistonExtension;
import net.minecraft.block.BlockPistonExtension$EnumPistonType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.tileentity.TileEntityPiston;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.optifine.expr.TokenParser;
import org.apache.log4j.pattern.ClassNamePatternConverter;

public class TileEntityPistonRenderer extends TileEntitySpecialRenderer<TileEntityPiston> {
   public NBTTagFloat field_0001;
   public BlockRendererDispatcher blockRenderer = Minecraft.getMinecraft().getBlockRendererDispatcher();
   public ClassNamePatternConverter field_0000;
   public TokenParser field_0002;

   public void renderTileEntityAt(TileEntityPiston var1, double var2, double var4, double var6, float var8, int var9) {
      BlockPos var10 = var1.v();
      IBlockState var11 = var1.getPistonState();
      Block var12 = var11.getBlock();
      if (var12.getMaterial() != Material.air && var1.getProgress(var8) < 1.0F) {
         Tessellator var13 = Tessellator.getInstance();
         WorldRenderer var14 = var13.getWorldRenderer();
         this.bindTexture(TextureMap.locationBlocksTexture);
         RenderHelper.disableStandardItemLighting();
         GlStateManager.blendFunc(770, 771);
         GlStateManager.enableBlend();
         GlStateManager.disableCull();
         if (Minecraft.isAmbientOcclusionEnabled()) {
            GlStateManager.shadeModel(7425);
         } else {
            GlStateManager.shadeModel(7424);
         }

         var14.begin(7, DefaultVertexFormats.BLOCK);
         var14.setTranslation(
            (float)var2 - var10.getX() + var1.method_05321(var8),
            (float)var4 - var10.getY() + var1.method_05316(var8),
            (float)var6 - var10.getZ() + var1.method_05315(var8)
         );
         World var15 = this.getWorld();
         if (var12 == Blocks.piston_head && var1.getProgress(var8) < 0.5F) {
            var11 = var11.withProperty(BlockPistonExtension.SHORT, true);
            this.blockRenderer
               .getBlockModelRenderer()
               .renderModel(var15, this.blockRenderer.getModelFromBlockState(var11, var15, var10), var11, var10, var14, true);
         } else if (var1.shouldPistonHeadBeRendered() && !var1.isExtending()) {
            BlockPistonExtension$EnumPistonType var16 = var12 == Blocks.sticky_piston
               ? BlockPistonExtension$EnumPistonType.STICKY
               : BlockPistonExtension$EnumPistonType.DEFAULT;
            IBlockState var17 = Blocks.piston_head
               .getDefaultState()
               .withProperty(BlockPistonExtension.TYPE, var16)
               .withProperty(BlockPistonExtension.FACING, var11.getValue(BlockPistonBase.FACING));
            var17 = var17.withProperty(BlockPistonExtension.SHORT, var1.getProgress(var8) >= 0.5F);
            this.blockRenderer
               .getBlockModelRenderer()
               .renderModel(var15, this.blockRenderer.getModelFromBlockState(var17, var15, var10), var17, var10, var14, true);
            var14.setTranslation((float)var2 - var10.getX(), (float)var4 - var10.getY(), (float)var6 - var10.getZ());
            var11.withProperty(BlockPistonBase.EXTENDED, true);
            this.blockRenderer
               .getBlockModelRenderer()
               .renderModel(var15, this.blockRenderer.getModelFromBlockState(var11, var15, var10), var11, var10, var14, true);
         } else {
            this.blockRenderer
               .getBlockModelRenderer()
               .renderModel(var15, this.blockRenderer.getModelFromBlockState(var11, var15, var10), var11, var10, var14, false);
         }

         var14.setTranslation(0.0, 0.0, 0.0);
         var13.draw();
         RenderHelper.enableStandardItemLighting();
      }
   }
}
