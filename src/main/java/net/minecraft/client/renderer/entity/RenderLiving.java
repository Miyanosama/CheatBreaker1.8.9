package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.EntityLiving;
import net.minecraft.src.Config;
import net.optifine.shaders.Shaders;

public abstract class RenderLiving<T extends EntityLiving> extends RendererLivingEntity<T> {
   public boolean canRenderName(T var1) {
      return super.canRenderName((T)var1) && (var1.aO() || var1.u_() && var1 == this.b.pointedEntity);
   }

   public void setLightmap(T var1, float var2) {
      int var3 = var1.b_(var2);
      int var4 = var3 % 65536;
      int var5 = var3 / 65536;
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var4 / 1.0F, var5 / 1.0F);
   }

   public RenderLiving(RenderManager var1, ModelBase var2, float var3) {
      super(var1, var2, var3);
   }

   public void renderLeash(T var1, double var2, double var4, double var6, float var8, float var9) {
      if (!Config.isShaders() || !Shaders.isShadowPass) {
         Entity var10 = var1.getLeashedToEntity();
         if (var10 != null) {
            var4 -= (1.6 - var1.K) * 0.5;
            Tessellator var11 = Tessellator.getInstance();
            WorldRenderer var12 = var11.getWorldRenderer();
            double var13 = this.interpolateValue(var10.A, var10.y, var9 * 0.5F) * (float) (Math.PI / 180.0);
            double var15 = this.interpolateValue(var10.B, var10.z, var9 * 0.5F) * (float) (Math.PI / 180.0);
            double var17 = Math.cos(var13);
            double var19 = Math.sin(var13);
            double var21 = Math.sin(var15);
            if (var10 instanceof EntityHanging) {
               var17 = 0.0;
               var19 = 0.0;
               var21 = -1.0;
            }

            double var23 = Math.cos(var15);
            double var25 = this.interpolateValue(var10.p, var10.s, var9) - var17 * 0.7 - var19 * 0.5 * var23;
            double var27 = this.interpolateValue(var10.q + var10.getEyeHeight() * 0.7, var10.t + var10.getEyeHeight() * 0.7, var9) - var21 * 0.5 - 0.25;
            double var29 = this.interpolateValue(var10.r, var10.u, var9) - var19 * 0.7 + var17 * 0.5 * var23;
            double var31 = this.interpolateValue(var1.aJ, var1.aI, var9) * (float) (Math.PI / 180.0) + (Math.PI / 2);
            var17 = Math.cos(var31) * var1.J * 0.4;
            var19 = Math.sin(var31) * var1.J * 0.4;
            double var33 = this.interpolateValue(var1.p, var1.s, var9) + var17;
            double var35 = this.interpolateValue(var1.q, var1.t, var9);
            double var37 = this.interpolateValue(var1.r, var1.u, var9) + var19;
            var2 += var17;
            var6 += var19;
            double var39 = (float)(var25 - var33);
            double var41 = (float)(var27 - var35);
            double var43 = (float)(var29 - var37);
            GlStateManager.disableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.disableCull();
            if (Config.isShaders()) {
               Shaders.beginLeash();
            }

            byte var45 = 24;
            double var46 = 0.025;
            var12.begin(5, DefaultVertexFormats.POSITION_COLOR);

            for (int var48 = 0; var48 <= 24; var48++) {
               float var49 = 0.5F;
               float var50 = 0.4F;
               float var51 = 0.3F;
               if (var48 % 2 == 0) {
                  var49 *= 0.7F;
                  var50 *= 0.7F;
                  var51 *= 0.7F;
               }

               float var52 = var48 / 24.0F;
               var12.pos(var2 + var39 * var52 + 0.0, var4 + var41 * (var52 * var52 + var52) * 0.5 + ((24.0F - var48) / 18.0F + 0.125F), var6 + var43 * var52)
                  .color(var49, var50, var51, 1.0F)
                  .endVertex();
               var12.pos(
                     var2 + var39 * var52 + 0.025,
                     var4 + var41 * (var52 * var52 + var52) * 0.5 + ((24.0F - var48) / 18.0F + 0.125F) + 0.025,
                     var6 + var43 * var52
                  )
                  .color(var49, var50, var51, 1.0F)
                  .endVertex();
            }

            var11.draw();
            var12.begin(5, DefaultVertexFormats.POSITION_COLOR);

            for (int var58 = 0; var58 <= 24; var58++) {
               float var59 = 0.5F;
               float var60 = 0.4F;
               float var61 = 0.3F;
               if (var58 % 2 == 0) {
                  var59 *= 0.7F;
                  var60 *= 0.7F;
                  var61 *= 0.7F;
               }

               float var62 = var58 / 24.0F;
               var12.pos(
                     var2 + var39 * var62 + 0.0,
                     var4 + var41 * (var62 * var62 + var62) * 0.5 + ((24.0F - var58) / 18.0F + 0.125F) + 0.025,
                     var6 + var43 * var62
                  )
                  .color(var59, var60, var61, 1.0F)
                  .endVertex();
               var12.pos(
                     var2 + var39 * var62 + 0.025,
                     var4 + var41 * (var62 * var62 + var62) * 0.5 + ((24.0F - var58) / 18.0F + 0.125F),
                     var6 + var43 * var62 + 0.025
                  )
                  .color(var59, var60, var61, 1.0F)
                  .endVertex();
            }

            var11.draw();
            if (Config.isShaders()) {
               Shaders.endLeash();
            }

            GlStateManager.enableLighting();
            GlStateManager.enableTexture2D();
            GlStateManager.enableCull();
         }
      }
   }

   public double interpolateValue(double var1, double var3, double var5) {
      return var1 + (var3 - var1) * var5;
   }

   public void doRender(T var1, double var2, double var4, double var6, float var8, float var9) {
      super.doRender((T)var1, var2, var4, var6, var8, var9);
      this.renderLeash((T)var1, var2, var4, var6, var8, var9);
   }

   public boolean shouldRender(T var1, ICamera var2, double var3, double var5, double var7) {
      if (super.shouldRender((T)var1, var2, var3, var5, var7)) {
         return true;
      } else if (var1.getLeashed() && var1.getLeashedToEntity() != null) {
         Entity var9 = var1.getLeashedToEntity();
         return var2.isBoundingBoxInFrustum(var9.getEntityBoundingBox());
      } else {
         return false;
      }
   }
}
