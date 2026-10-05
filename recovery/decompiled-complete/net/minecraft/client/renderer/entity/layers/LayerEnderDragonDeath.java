package net.minecraft.client.renderer.entity.layers;

import io.netty.handler.codec.socks.SocksInitRequestDecoder$State;
import java.util.Random;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.shader.ShaderLoader$ShaderType;
import net.minecraft.command.CommandReplaceItem;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.inventory.ContainerDispenser;
import net.minecraft.inventory.InventoryLargeChest;
import recovered.unidentified.UnidentifiedClass0682;

public class LayerEnderDragonDeath implements LayerRenderer<EntityDragon> {
   public InventoryLargeChest field_0003;
   public ContainerDispenser field_0005;
   public CommandReplaceItem field_0002;
   public SocksInitRequestDecoder$State field_0004;
   public ShaderLoader$ShaderType field_0000;
   public UnidentifiedClass0682 field_0001;

   @Override
   public boolean shouldCombineTextures() {
      return false;
   }

   public void doRenderLayer(EntityDragon var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      if (var1.deathTicks > 0) {
         Tessellator var9 = Tessellator.getInstance();
         WorldRenderer var10 = var9.getWorldRenderer();
         RenderHelper.disableStandardItemLighting();
         float var11 = (var1.deathTicks + var4) / 200.0F;
         float var12 = 0.0F;
         if (var11 > 0.8F) {
            var12 = (var11 - 0.8F) / 0.2F;
         }

         Random var13 = new Random(-1436159605045198414L & 1436159604066093552L);
         GlStateManager.disableTexture2D();
         GlStateManager.shadeModel(7425);
         GlStateManager.enableBlend();
         GlStateManager.blendFunc(770, 1);
         GlStateManager.disableAlpha();
         GlStateManager.enableCull();
         GlStateManager.depthMask(false);
         GlStateManager.pushMatrix();
         GlStateManager.translate(0.0F, -1.0F, -2.0F);

         for (int var14 = 0; var14 < (var11 + var11 * var11) / 2.0F * 60.0F; var14++) {
            GlStateManager.rotate(var13.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(var13.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(var13.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.rotate(var13.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(var13.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(var13.nextFloat() * 360.0F + var11 * 90.0F, 0.0F, 0.0F, 1.0F);
            float var15 = var13.nextFloat() * 20.0F + 5.0F + var12 * 10.0F;
            float var16 = var13.nextFloat() * 2.0F + 1.0F + var12 * 2.0F;
            var10.begin(6, DefaultVertexFormats.POSITION_COLOR);
            var10.pos(0.0, 0.0, 0.0).color(255, 255, 255, (int)(255.0F * (1.0F - var12))).endVertex();
            var10.pos(-0.866 * var16, var15, -0.5F * var16).color(255, 0, 255, 0).endVertex();
            var10.pos(0.866 * var16, var15, -0.5F * var16).color(255, 0, 255, 0).endVertex();
            var10.pos(0.0, var15, 1.0F * var16).color(255, 0, 255, 0).endVertex();
            var10.pos(-0.866 * var16, var15, -0.5F * var16).color(255, 0, 255, 0).endVertex();
            var9.draw();
         }

         GlStateManager.popMatrix();
         GlStateManager.depthMask(true);
         GlStateManager.disableCull();
         GlStateManager.disableBlend();
         GlStateManager.shadeModel(7424);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.enableTexture2D();
         GlStateManager.enableAlpha();
         RenderHelper.enableStandardItemLighting();
      }
   }
}
