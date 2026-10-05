package net.minecraft.client.renderer.entity;

import com.cheatbreaker.client.util.render.FishingRodHandOffset;

import com.cheatbreaker.client.CheatBreaker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

public class RenderFish extends Render<EntityFishHook> {
   public static ResourceLocation recoveredField2594 = new ResourceLocation("textures/particle/particles.png");

   public ResourceLocation getEntityTexture(EntityFishHook var1) {
      return recoveredField2594;
   }

   public RenderFish(RenderManager var1) {
      super(var1);
   }

   public void doRender(EntityFishHook var1, double var2, double var4, double var6, float var8, float var9) {
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)var2, (float)var4, (float)var6);
      GlStateManager.enableRescaleNormal();
      GlStateManager.scale(0.5F, 0.5F, 0.5F);
      this.bindEntityTexture(var1);
      Tessellator var10 = Tessellator.getInstance();
      WorldRenderer var11 = var10.getWorldRenderer();
      boolean var12 = true;
      byte var13 = 2;
      float var14 = 0.0625F;
      float var15 = 0.125F;
      float var16 = 0.125F;
      float var17 = 0.1875F;
      float var18 = 1.0F;
      float var19 = 0.5F;
      float var20 = 0.5F;
      GlStateManager.rotate(180.0F - this.b.playerViewY, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(-this.b.playerViewX, 1.0F, 0.0F, 0.0F);
      var11.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
      var11.pos(-0.5, -0.5, 0.0).tex(0.0625, 0.1875).normal(0.0F, 1.0F, 0.0F).endVertex();
      var11.pos(0.5, -0.5, 0.0).tex(0.125, 0.1875).normal(0.0F, 1.0F, 0.0F).endVertex();
      var11.pos(0.5, 0.5, 0.0).tex(0.125, 0.125).normal(0.0F, 1.0F, 0.0F).endVertex();
      var11.pos(-0.5, 0.5, 0.0).tex(0.0625, 0.125).normal(0.0F, 1.0F, 0.0F).endVertex();
      var10.draw();
      GlStateManager.disableRescaleNormal();
      GlStateManager.popMatrix();
      if (var1.angler != null) {
         float var21 = var1.angler.getSwingProgress(var9);
         float var22 = MathHelper.sin(MathHelper.sqrt_float(var21) * (float) Math.PI);
         Vec3 var23 = !CheatBreaker.getInstance().getModuleManager().recoveredField1717.recoveredField3733.method_08908()
            ? new Vec3(-0.36, 0.03, 0.35)
            : FishingRodHandOffset.recoveredField3936.method_21047();
         var23 = var23.rotatePitch(-(var1.angler.B + (var1.angler.z - var1.angler.B) * var9) * (float) Math.PI / 180.0F);
         var23 = var23.rotateYaw(-(var1.angler.A + (var1.angler.y - var1.angler.A) * var9) * (float) Math.PI / 180.0F);
         var23 = var23.rotateYaw(var22 * 0.5F);
         var23 = var23.rotatePitch(-var22 * 0.7F);
         double var24 = var1.angler.p + (var1.angler.s - var1.angler.p) * var9 + var23.xCoord;
         double var26 = var1.angler.q + (var1.angler.t - var1.angler.q) * var9 + var23.yCoord;
         double var28 = var1.angler.r + (var1.angler.u - var1.angler.r) * var9 + var23.zCoord;
         double var30 = var1.angler.getEyeHeight();
         if (this.b.options != null && this.b.options.thirdPersonView > 0 || var1.angler != Minecraft.getMinecraft().thePlayer) {
            float var32 = (var1.angler.aJ + (var1.angler.aI - var1.angler.aJ) * var9) * (float) Math.PI / 180.0F;
            double var33 = MathHelper.sin(var32);
            double var35 = MathHelper.cos(var32);
            double var37 = 0.35;
            double var39 = 0.8;
            var24 = var1.angler.p + (var1.angler.s - var1.angler.p) * var9 - var35 * 0.35 - var33 * 0.8;
            var26 = var1.angler.q + var30 + (var1.angler.t - var1.angler.q) * var9 - 0.45;
            var28 = var1.angler.r + (var1.angler.u - var1.angler.r) * var9 - var33 * 0.35 + var35 * 0.8;
            var30 = var1.angler.isSneaking() ? -0.1875 : 0.0;
         }

         double var51 = var1.p + (var1.s - var1.p) * var9;
         double var34 = var1.q + (var1.t - var1.q) * var9 + 0.25;
         double var36 = var1.r + (var1.u - var1.r) * var9;
         double var38 = (float)(var24 - var51);
         double var40 = (float)(var26 - var34) + var30;
         double var42 = (float)(var28 - var36);
         GlStateManager.disableTexture2D();
         GlStateManager.disableLighting();
         var11.begin(3, DefaultVertexFormats.POSITION_COLOR);
         byte var44 = 16;

         for (int var45 = 0; var45 <= 16; var45++) {
            float var46 = var45 / 16.0F;
            var11.pos(var2 + var38 * var46, var4 + var40 * (var46 * var46 + var46) * 0.5 + 0.25, var6 + var42 * var46).color(0, 0, 0, 255).endVertex();
         }

         var10.draw();
         GlStateManager.enableLighting();
         GlStateManager.enableTexture2D();
         super.doRender(var1, var2, var4, var6, var8, var9);
      }
   }
}
