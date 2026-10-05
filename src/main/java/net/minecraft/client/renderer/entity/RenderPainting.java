package net.minecraft.client.renderer.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderPainting extends Render<EntityPainting> {
   public static ResourceLocation KRISTOFFER_PAINTING_TEXTURE = new ResourceLocation("textures/painting/paintings_kristoffer_zetterstrand.png");

   public RenderPainting(RenderManager var1) {
      super(var1);
   }

   public void renderPainting(EntityPainting var1, int var2, int var3, int var4, int var5) {
      float var6 = -var2 / 2.0F;
      float var7 = -var3 / 2.0F;
      float var8 = 0.5F;
      float var9 = 0.75F;
      float var10 = 0.8125F;
      float var11 = 0.0F;
      float var12 = 0.0625F;
      float var13 = 0.75F;
      float var14 = 0.8125F;
      float var15 = 0.001953125F;
      float var16 = 0.001953125F;
      float var17 = 0.7519531F;
      float var18 = 0.7519531F;
      float var19 = 0.0F;
      float var20 = 0.0625F;

      for (int var21 = 0; var21 < var2 / 16; var21++) {
         for (int var22 = 0; var22 < var3 / 16; var22++) {
            float var23 = var6 + (var21 + 1) * 16;
            float var24 = var6 + var21 * 16;
            float var25 = var7 + (var22 + 1) * 16;
            float var26 = var7 + var22 * 16;
            this.setLightmap(var1, (var23 + var24) / 2.0F, (var25 + var26) / 2.0F);
            float var27 = (var4 + var2 - var21 * 16) / 256.0F;
            float var28 = (var4 + var2 - (var21 + 1) * 16) / 256.0F;
            float var29 = (var5 + var3 - var22 * 16) / 256.0F;
            float var30 = (var5 + var3 - (var22 + 1) * 16) / 256.0F;
            Tessellator var31 = Tessellator.getInstance();
            WorldRenderer var32 = var31.getWorldRenderer();
            var32.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
            var32.pos(var23, var26, -var8).tex(var28, var29).normal(0.0F, 0.0F, -1.0F).endVertex();
            var32.pos(var24, var26, -var8).tex(var27, var29).normal(0.0F, 0.0F, -1.0F).endVertex();
            var32.pos(var24, var25, -var8).tex(var27, var30).normal(0.0F, 0.0F, -1.0F).endVertex();
            var32.pos(var23, var25, -var8).tex(var28, var30).normal(0.0F, 0.0F, -1.0F).endVertex();
            var32.pos(var23, var25, var8).tex(var9, var11).normal(0.0F, 0.0F, 1.0F).endVertex();
            var32.pos(var24, var25, var8).tex(var10, var11).normal(0.0F, 0.0F, 1.0F).endVertex();
            var32.pos(var24, var26, var8).tex(var10, var12).normal(0.0F, 0.0F, 1.0F).endVertex();
            var32.pos(var23, var26, var8).tex(var9, var12).normal(0.0F, 0.0F, 1.0F).endVertex();
            var32.pos(var23, var25, -var8).tex(var13, var15).normal(0.0F, 1.0F, 0.0F).endVertex();
            var32.pos(var24, var25, -var8).tex(var14, var15).normal(0.0F, 1.0F, 0.0F).endVertex();
            var32.pos(var24, var25, var8).tex(var14, var16).normal(0.0F, 1.0F, 0.0F).endVertex();
            var32.pos(var23, var25, var8).tex(var13, var16).normal(0.0F, 1.0F, 0.0F).endVertex();
            var32.pos(var23, var26, var8).tex(var13, var15).normal(0.0F, -1.0F, 0.0F).endVertex();
            var32.pos(var24, var26, var8).tex(var14, var15).normal(0.0F, -1.0F, 0.0F).endVertex();
            var32.pos(var24, var26, -var8).tex(var14, var16).normal(0.0F, -1.0F, 0.0F).endVertex();
            var32.pos(var23, var26, -var8).tex(var13, var16).normal(0.0F, -1.0F, 0.0F).endVertex();
            var32.pos(var23, var25, var8).tex(var18, var19).normal(-1.0F, 0.0F, 0.0F).endVertex();
            var32.pos(var23, var26, var8).tex(var18, var20).normal(-1.0F, 0.0F, 0.0F).endVertex();
            var32.pos(var23, var26, -var8).tex(var17, var20).normal(-1.0F, 0.0F, 0.0F).endVertex();
            var32.pos(var23, var25, -var8).tex(var17, var19).normal(-1.0F, 0.0F, 0.0F).endVertex();
            var32.pos(var24, var25, -var8).tex(var18, var19).normal(1.0F, 0.0F, 0.0F).endVertex();
            var32.pos(var24, var26, -var8).tex(var18, var20).normal(1.0F, 0.0F, 0.0F).endVertex();
            var32.pos(var24, var26, var8).tex(var17, var20).normal(1.0F, 0.0F, 0.0F).endVertex();
            var32.pos(var24, var25, var8).tex(var17, var19).normal(1.0F, 0.0F, 0.0F).endVertex();
            var31.draw();
         }
      }
   }

   public void setLightmap(EntityPainting var1, float var2, float var3) {
      int var4 = MathHelper.floor_double(var1.s);
      int var5 = MathHelper.floor_double(var1.t + var3 / 16.0F);
      int var6 = MathHelper.floor_double(var1.u);
      EnumFacing var7 = var1.b;
      if (var7 == EnumFacing.NORTH) {
         var4 = MathHelper.floor_double(var1.s + var2 / 16.0F);
      }

      if (var7 == EnumFacing.WEST) {
         var6 = MathHelper.floor_double(var1.u - var2 / 16.0F);
      }

      if (var7 == EnumFacing.SOUTH) {
         var4 = MathHelper.floor_double(var1.s - var2 / 16.0F);
      }

      if (var7 == EnumFacing.EAST) {
         var6 = MathHelper.floor_double(var1.u + var2 / 16.0F);
      }

      int var8 = this.b.worldObj.getCombinedLight(new BlockPos(var4, var5, var6), 0);
      int var9 = var8 % 65536;
      int var10 = var8 / 65536;
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var9, var10);
      GlStateManager.color(1.0F, 1.0F, 1.0F);
   }

   public void doRender(EntityPainting var1, double var2, double var4, double var6, float var8, float var9) {
      GlStateManager.pushMatrix();
      GlStateManager.translate(var2, var4, var6);
      GlStateManager.rotate(180.0F - var8, 0.0F, 1.0F, 0.0F);
      GlStateManager.enableRescaleNormal();
      this.bindEntityTexture(var1);
      EntityPainting.EnumArt var10 = var1.art;
      float var11 = 0.0625F;
      GlStateManager.scale(var11, var11, var11);
      this.renderPainting(var1, var10.sizeX, var10.sizeY, var10.offsetX, var10.offsetY);
      GlStateManager.disableRescaleNormal();
      GlStateManager.popMatrix();
      super.doRender(var1, var2, var4, var6, var8, var9);
   }

   public ResourceLocation getEntityTexture(EntityPainting var1) {
      return KRISTOFFER_PAINTING_TEXTURE;
   }
}
