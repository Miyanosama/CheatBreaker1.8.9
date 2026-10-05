package recovered.unidentified;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.potion.PotionAttackDamage;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class UnidentifiedClass1595 extends Render<EntityFallingBlock> {
   public BlockFarmland field_0001;
   public PotionAttackDamage field_0002;
   public AbstractClientPlayer field_0003;
   public UnidentifiedClass3810 field_0000;

   public void method_10913(EntityFallingBlock var1, double var2, double var4, double var6, float var8, float var9) {
      if (var1.getBlock() != null) {
         this.a(TextureMap.locationBlocksTexture);
         IBlockState var10 = var1.getBlock();
         Block var11 = var10.getBlock();
         BlockPos var12 = new BlockPos(var1);
         World var13 = var1.getWorldObj();
         if (var10 != var13.getBlockState(var12) && var11.getRenderType() != -1 && var11.getRenderType() == 3) {
            GlStateManager.pushMatrix();
            GlStateManager.translate((float)var2, (float)var4, (float)var6);
            GlStateManager.disableLighting();
            Tessellator var14 = Tessellator.getInstance();
            WorldRenderer var15 = var14.getWorldRenderer();
            var15.begin(7, DefaultVertexFormats.BLOCK);
            int var16 = var12.getX();
            int var17 = var12.getY();
            int var18 = var12.getZ();
            var15.setTranslation(-var16 - 0.5F, -var17, -var18 - 0.5F);
            BlockRendererDispatcher var19 = Minecraft.getMinecraft().getBlockRendererDispatcher();
            IBakedModel var20 = var19.getModelFromBlockState(var10, var13, (BlockPos)null);
            var19.getBlockModelRenderer().renderModel(var13, var20, var10, var12, var15, false);
            var15.setTranslation(0.0, 0.0, 0.0);
            var14.draw();
            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
            super.doRender(var1, var2, var4, var6, var8, var9);
         }
      }
   }

   public UnidentifiedClass1595(RenderManager var1) {
      super(var1);
      this.c = 0.5F;
   }

   public ResourceLocation method_10912(EntityFallingBlock var1) {
      return TextureMap.locationBlocksTexture;
   }
}
