package net.minecraft.client.renderer.entity.layers;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.util.MathHelper;

public class LayerCape implements LayerRenderer<AbstractClientPlayer> {
   public RenderPlayer playerRenderer;

   public LayerCape(RenderPlayer var1) {
      this.playerRenderer = var1;
   }

   @Override
   public boolean shouldCombineTextures() {
      return false;
   }

   public void doRenderLayer(AbstractClientPlayer var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      if (var1.hasPlayerInfo() && !var1.isInvisible() && var1.isWearing(EnumPlayerModelParts.CAPE) && var1.getLocationCape() != null) {
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.playerRenderer.a(var1.getLocationCape());
         GlStateManager.pushMatrix();
         GlStateManager.translate(0.0F, 0.0F, 0.125F);
         double var9 = var1.prevChasingPosX + (var1.chasingPosX - var1.prevChasingPosX) * var4 - (var1.p + (var1.s - var1.p) * var4);
         double var11 = var1.prevChasingPosY + (var1.chasingPosY - var1.prevChasingPosY) * var4 - (var1.q + (var1.t - var1.q) * var4);
         double var13 = var1.prevChasingPosZ + (var1.chasingPosZ - var1.prevChasingPosZ) * var4 - (var1.r + (var1.u - var1.r) * var4);
         float var15 = var1.aJ + (var1.aI - var1.aJ) * var4;
         double var16 = MathHelper.sin(var15 * (float) Math.PI / 180.0F);
         double var18 = -MathHelper.cos(var15 * (float) Math.PI / 180.0F);
         float var20 = (float)var11 * 10.0F;
         var20 = MathHelper.clamp_float(var20, -6.0F, 32.0F);
         float var21 = (float)(var9 * var16 + var13 * var18) * 100.0F;
         float var22 = (float)(var9 * var18 - var13 * var16) * 100.0F;
         if (var21 < 0.0F) {
            var21 = 0.0F;
         }

         if (var21 > 165.0F) {
            var21 = 165.0F;
         }

         if (var20 < -5.0F) {
            var20 = -5.0F;
         }

         float var23 = var1.prevCameraYaw + (var1.cameraYaw - var1.prevCameraYaw) * var4;
         var20 += MathHelper.sin((var1.L + (var1.M - var1.L) * var4) * 6.0F) * 32.0F * var23;
         if (var1.isSneaking()) {
            var20 += 25.0F;
            GlStateManager.translate(0.0F, 0.142F, -0.0178F);
         }

         GlStateManager.rotate(6.0F + var21 / 2.0F + var20, 1.0F, 0.0F, 0.0F);
         GlStateManager.rotate(var22 / 2.0F, 0.0F, 0.0F, 1.0F);
         GlStateManager.rotate(-var22 / 2.0F, 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
         this.playerRenderer.getMainModel().renderCape(0.0625F);
         GlStateManager.popMatrix();
      }
   }
}
