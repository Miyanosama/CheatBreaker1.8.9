package net.optifine.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelSprite {
   public float maxV;
   public float maxU;
   public float posY;
   public float posZ;
   public int sizeZ;
   public int sizeY;
   public int textureOffsetX;
   public float minV;
   public int sizeX;
   public ModelRenderer modelRenderer = null;
   public int textureOffsetY;
   public float minU;
   public float sizeAdd;
   public float posX;

   public ModelSprite(ModelRenderer var1, int var2, int var3, float var4, float var5, float var6, int var7, int var8, int var9, float var10) {
      this.textureOffsetX = 0;
      this.textureOffsetY = 0;
      this.posX = 0.0F;
      this.posY = 0.0F;
      this.posZ = 0.0F;
      this.sizeX = 0;
      this.sizeY = 0;
      this.sizeZ = 0;
      this.sizeAdd = 0.0F;
      this.minU = 0.0F;
      this.minV = 0.0F;
      this.maxU = 0.0F;
      this.maxV = 0.0F;
      this.modelRenderer = var1;
      this.textureOffsetX = var2;
      this.textureOffsetY = var3;
      this.posX = var4;
      this.posY = var5;
      this.posZ = var6;
      this.sizeX = var7;
      this.sizeY = var8;
      this.sizeZ = var9;
      this.sizeAdd = var10;
      this.minU = var2 / var1.textureWidth;
      this.minV = var3 / var1.textureHeight;
      this.maxU = (var2 + var7) / var1.textureWidth;
      this.maxV = (var3 + var8) / var1.textureHeight;
   }

   public void render(Tessellator var1, float var2) {
      GlStateManager.translate(this.posX * var2, this.posY * var2, this.posZ * var2);
      float var3 = this.minU;
      float var4 = this.maxU;
      float var5 = this.minV;
      float var6 = this.maxV;
      if (this.modelRenderer.mirror) {
         var3 = this.maxU;
         var4 = this.minU;
      }

      if (this.modelRenderer.mirrorV) {
         var5 = this.maxV;
         var6 = this.minV;
      }

      renderItemIn2D(var1, var3, var5, var4, var6, this.sizeX, this.sizeY, var2 * this.sizeZ, this.modelRenderer.textureWidth, this.modelRenderer.textureHeight);
      GlStateManager.translate(-this.posX * var2, -this.posY * var2, -this.posZ * var2);
   }

   public static void renderItemIn2D(Tessellator var0, float var1, float var2, float var3, float var4, int var5, int var6, float var7, float var8, float var9) {
      if (var7 < 6.25E-4F) {
         var7 = 6.25E-4F;
      }

      float var10 = var3 - var1;
      float var11 = var4 - var2;
      double var12 = MathHelper.abs(var10) * (var8 / 16.0F);
      double var14 = MathHelper.abs(var11) * (var9 / 16.0F);
      WorldRenderer var16 = var0.getWorldRenderer();
      GL11.glNormal3f(0.0F, 0.0F, -1.0F);
      var16.begin(7, DefaultVertexFormats.POSITION_TEX);
      var16.pos(0.0, var14, 0.0).tex(var1, var4).endVertex();
      var16.pos(var12, var14, 0.0).tex(var3, var4).endVertex();
      var16.pos(var12, 0.0, 0.0).tex(var3, var2).endVertex();
      var16.pos(0.0, 0.0, 0.0).tex(var1, var2).endVertex();
      var0.draw();
      GL11.glNormal3f(0.0F, 0.0F, 1.0F);
      var16.begin(7, DefaultVertexFormats.POSITION_TEX);
      var16.pos(0.0, 0.0, var7).tex(var1, var2).endVertex();
      var16.pos(var12, 0.0, var7).tex(var3, var2).endVertex();
      var16.pos(var12, var14, var7).tex(var3, var4).endVertex();
      var16.pos(0.0, var14, var7).tex(var1, var4).endVertex();
      var0.draw();
      float var17 = 0.5F * var10 / var5;
      float var18 = 0.5F * var11 / var6;
      GL11.glNormal3f(-1.0F, 0.0F, 0.0F);
      var16.begin(7, DefaultVertexFormats.POSITION_TEX);

      for (int var19 = 0; var19 < var5; var19++) {
         float var20 = (float)var19 / var5;
         float var21 = var1 + var10 * var20 + var17;
         var16.pos(var20 * var12, var14, var7).tex(var21, var4).endVertex();
         var16.pos(var20 * var12, var14, 0.0).tex(var21, var4).endVertex();
         var16.pos(var20 * var12, 0.0, 0.0).tex(var21, var2).endVertex();
         var16.pos(var20 * var12, 0.0, var7).tex(var21, var2).endVertex();
      }

      var0.draw();
      GL11.glNormal3f(1.0F, 0.0F, 0.0F);
      var16.begin(7, DefaultVertexFormats.POSITION_TEX);

      for (int var23 = 0; var23 < var5; var23++) {
         float var26 = (float)var23 / var5;
         float var29 = var1 + var10 * var26 + var17;
         float var22 = var26 + 1.0F / var5;
         var16.pos(var22 * var12, 0.0, var7).tex(var29, var2).endVertex();
         var16.pos(var22 * var12, 0.0, 0.0).tex(var29, var2).endVertex();
         var16.pos(var22 * var12, var14, 0.0).tex(var29, var4).endVertex();
         var16.pos(var22 * var12, var14, var7).tex(var29, var4).endVertex();
      }

      var0.draw();
      GL11.glNormal3f(0.0F, 1.0F, 0.0F);
      var16.begin(7, DefaultVertexFormats.POSITION_TEX);

      for (int var24 = 0; var24 < var6; var24++) {
         float var27 = (float)var24 / var6;
         float var30 = var2 + var11 * var27 + var18;
         float var32 = var27 + 1.0F / var6;
         var16.pos(0.0, var32 * var14, var7).tex(var1, var30).endVertex();
         var16.pos(var12, var32 * var14, var7).tex(var3, var30).endVertex();
         var16.pos(var12, var32 * var14, 0.0).tex(var3, var30).endVertex();
         var16.pos(0.0, var32 * var14, 0.0).tex(var1, var30).endVertex();
      }

      var0.draw();
      GL11.glNormal3f(0.0F, -1.0F, 0.0F);
      var16.begin(7, DefaultVertexFormats.POSITION_TEX);

      for (int var25 = 0; var25 < var6; var25++) {
         float var28 = (float)var25 / var6;
         float var31 = var2 + var11 * var28 + var18;
         var16.pos(var12, var28 * var14, var7).tex(var3, var31).endVertex();
         var16.pos(0.0, var28 * var14, var7).tex(var1, var31).endVertex();
         var16.pos(0.0, var28 * var14, 0.0).tex(var1, var31).endVertex();
         var16.pos(var12, var28 * var14, 0.0).tex(var3, var31).endVertex();
      }

      var0.draw();
   }
}
