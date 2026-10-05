package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelGuardian;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class RenderGuardian extends RenderLiving<EntityGuardian> {
   public static ResourceLocation GUARDIAN_TEXTURE = new ResourceLocation("textures/entity/guardian.png");
   public static ResourceLocation GUARDIAN_ELDER_TEXTURE = new ResourceLocation("textures/entity/guardian_elder.png");
   public int field_177115_a = ((ModelGuardian)this.f).func_178706_a();
   public static ResourceLocation GUARDIAN_BEAM_TEXTURE = new ResourceLocation("textures/entity/guardian_beam.png");

   public Vec3 func_177110_a(EntityLivingBase var1, double var2, float var4) {
      double var5 = var1.P + (var1.s - var1.P) * var4;
      double var7 = var2 + var1.Q + (var1.t - var1.Q) * var4;
      double var9 = var1.R + (var1.u - var1.R) * var4;
      return new Vec3(var5, var7, var9);
   }

   public void preRenderCallback(EntityGuardian var1, float var2) {
      if (var1.isElder()) {
         GlStateManager.scale(2.35F, 2.35F, 2.35F);
      }
   }

   public void doRender(EntityGuardian var1, double var2, double var4, double var6, float var8, float var9) {
      if (this.field_177115_a != ((ModelGuardian)this.f).func_178706_a()) {
         this.f = new ModelGuardian();
         this.field_177115_a = ((ModelGuardian)this.f).func_178706_a();
      }

      super.doRender((EntityGuardian)var1, var2, var4, var6, var8, var9);
      EntityLivingBase var10 = var1.getTargetedEntity();
      if (var10 != null) {
         float var11 = var1.func_175477_p(var9);
         Tessellator var12 = Tessellator.getInstance();
         WorldRenderer var13 = var12.getWorldRenderer();
         this.a(GUARDIAN_BEAM_TEXTURE);
         GL11.glTexParameterf(3553, 10242, 10497.0F);
         GL11.glTexParameterf(3553, 10243, 10497.0F);
         GlStateManager.disableLighting();
         GlStateManager.disableCull();
         GlStateManager.disableBlend();
         GlStateManager.depthMask(true);
         float var14 = 240.0F;
         OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var14, var14);
         GlStateManager.tryBlendFuncSeparate(770, 1, 1, 0);
         float var15 = (float)var1.o.K() + var9;
         float var16 = var15 * 0.5F % 1.0F;
         float var17 = var1.getEyeHeight();
         GlStateManager.pushMatrix();
         GlStateManager.translate((float)var2, (float)var4 + var17, (float)var6);
         Vec3 var18 = this.func_177110_a(var10, var10.K * 0.5, var9);
         Vec3 var19 = this.func_177110_a(var1, var17, var9);
         Vec3 var20 = var18.subtract(var19);
         double var21 = var20.lengthVector() + 1.0;
         var20 = var20.normalize();
         float var23 = (float)Math.acos(var20.yCoord);
         float var24 = (float)Math.atan2(var20.zCoord, var20.xCoord);
         GlStateManager.rotate(((float) (Math.PI / 2) + -var24) * (180.0F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(var23 * (180.0F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
         byte var25 = 1;
         double var26 = var15 * 0.05 * (1.0 - (var25 & 1) * 2.5);
         var13.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
         float var28 = var11 * var11;
         int var29 = 64 + (int)(var28 * 240.0F);
         int var30 = 32 + (int)(var28 * 192.0F);
         int var31 = 128 - (int)(var28 * 64.0F);
         double var32 = var25 * 0.2;
         double var34 = var32 * 1.41;
         double var36 = 0.0 + Math.cos(var26 + (Math.PI * 3.0 / 4.0)) * var34;
         double var38 = 0.0 + Math.sin(var26 + (Math.PI * 3.0 / 4.0)) * var34;
         double var40 = 0.0 + Math.cos(var26 + (Math.PI / 4)) * var34;
         double var42 = 0.0 + Math.sin(var26 + (Math.PI / 4)) * var34;
         double var44 = 0.0 + Math.cos(var26 + (Math.PI * 5.0 / 4.0)) * var34;
         double var46 = 0.0 + Math.sin(var26 + (Math.PI * 5.0 / 4.0)) * var34;
         double var48 = 0.0 + Math.cos(var26 + (Math.PI * 7.0 / 4.0)) * var34;
         double var50 = 0.0 + Math.sin(var26 + (Math.PI * 7.0 / 4.0)) * var34;
         double var52 = 0.0 + Math.cos(var26 + Math.PI) * var32;
         double var54 = 0.0 + Math.sin(var26 + Math.PI) * var32;
         double var56 = 0.0 + Math.cos(var26 + 0.0) * var32;
         double var58 = 0.0 + Math.sin(var26 + 0.0) * var32;
         double var60 = 0.0 + Math.cos(var26 + (Math.PI / 2)) * var32;
         double var62 = 0.0 + Math.sin(var26 + (Math.PI / 2)) * var32;
         double var64 = 0.0 + Math.cos(var26 + (Math.PI * 3.0 / 2.0)) * var32;
         double var66 = 0.0 + Math.sin(var26 + (Math.PI * 3.0 / 2.0)) * var32;
         double var68 = 0.0;
         double var70 = 0.4999;
         double var72 = -1.0F + var16;
         double var74 = var21 * (0.5 / var32) + var72;
         var13.pos(var52, var21, var54).tex(0.4999, var74).color(var29, var30, var31, 255).endVertex();
         var13.pos(var52, 0.0, var54).tex(0.4999, var72).color(var29, var30, var31, 255).endVertex();
         var13.pos(var56, 0.0, var58).tex(0.0, var72).color(var29, var30, var31, 255).endVertex();
         var13.pos(var56, var21, var58).tex(0.0, var74).color(var29, var30, var31, 255).endVertex();
         var13.pos(var60, var21, var62).tex(0.4999, var74).color(var29, var30, var31, 255).endVertex();
         var13.pos(var60, 0.0, var62).tex(0.4999, var72).color(var29, var30, var31, 255).endVertex();
         var13.pos(var64, 0.0, var66).tex(0.0, var72).color(var29, var30, var31, 255).endVertex();
         var13.pos(var64, var21, var66).tex(0.0, var74).color(var29, var30, var31, 255).endVertex();
         double var76 = 0.0;
         if (var1.W % 2 == 0) {
            var76 = 0.5;
         }

         var13.pos(var36, var21, var38).tex(0.5, var76 + 0.5).color(var29, var30, var31, 255).endVertex();
         var13.pos(var40, var21, var42).tex(1.0, var76 + 0.5).color(var29, var30, var31, 255).endVertex();
         var13.pos(var48, var21, var50).tex(1.0, var76).color(var29, var30, var31, 255).endVertex();
         var13.pos(var44, var21, var46).tex(0.5, var76).color(var29, var30, var31, 255).endVertex();
         var12.draw();
         GlStateManager.popMatrix();
      }
   }

   public ResourceLocation getEntityTexture(EntityGuardian var1) {
      return var1.isElder() ? GUARDIAN_ELDER_TEXTURE : GUARDIAN_TEXTURE;
   }

   public boolean shouldRender(EntityGuardian var1, ICamera var2, double var3, double var5, double var7) {
      if (super.shouldRender((EntityGuardian)var1, var2, var3, var5, var7)) {
         return true;
      } else {
         if (var1.hasTargetedEntity()) {
            EntityLivingBase var9 = var1.getTargetedEntity();
            if (var9 != null) {
               Vec3 var10 = this.func_177110_a(var9, var9.K * 0.5, 1.0F);
               Vec3 var11 = this.func_177110_a(var1, var1.getEyeHeight(), 1.0F);
               if (var2.isBoundingBoxInFrustum(AxisAlignedBB.fromBounds(var11.xCoord, var11.yCoord, var11.zCoord, var10.xCoord, var10.yCoord, var10.zCoord))) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public RenderGuardian(RenderManager var1) {
      super(var1, new ModelGuardian(), 0.5F);
   }
}
