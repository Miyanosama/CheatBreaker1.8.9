package net.minecraft.client.model;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelQuadruped extends ModelBase {
   public ModelRenderer d;
   public ModelRenderer c;
   public ModelRenderer e;
   public ModelRenderer b;
   public ModelRenderer a = new ModelRenderer(this, 0, 0);
   public float g = 8.0F;
   public float h = 4.0F;
   public ModelRenderer f;

   public ModelQuadruped(int var1, float var2) {
      this.a.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8, var2);
      this.a.setRotationPoint(0.0F, 18 - var1, -6.0F);
      this.b = new ModelRenderer(this, 28, 8);
      this.b.addBox(-5.0F, -10.0F, -7.0F, 10, 16, 8, var2);
      this.b.setRotationPoint(0.0F, 17 - var1, 2.0F);
      this.c = new ModelRenderer(this, 0, 16);
      this.c.addBox(-2.0F, 0.0F, -2.0F, 4, var1, 4, var2);
      this.c.setRotationPoint(-3.0F, 24 - var1, 7.0F);
      this.d = new ModelRenderer(this, 0, 16);
      this.d.addBox(-2.0F, 0.0F, -2.0F, 4, var1, 4, var2);
      this.d.setRotationPoint(3.0F, 24 - var1, 7.0F);
      this.e = new ModelRenderer(this, 0, 16);
      this.e.addBox(-2.0F, 0.0F, -2.0F, 4, var1, 4, var2);
      this.e.setRotationPoint(-3.0F, 24 - var1, -5.0F);
      this.f = new ModelRenderer(this, 0, 16);
      this.f.addBox(-2.0F, 0.0F, -2.0F, 4, var1, 4, var2);
      this.f.setRotationPoint(3.0F, 24 - var1, -5.0F);
   }

   @Override
   public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7) {
      float var8 = 180.0F / (float)Math.PI;
      this.a.rotateAngleX = var5 / (180.0F / (float)Math.PI);
      this.a.rotateAngleY = var4 / (180.0F / (float)Math.PI);
      this.b.rotateAngleX = (float) (Math.PI / 2);
      this.c.rotateAngleX = MathHelper.cos(var1 * 0.6662F) * 1.4F * var2;
      this.d.rotateAngleX = MathHelper.cos(var1 * 0.6662F + (float) Math.PI) * 1.4F * var2;
      this.e.rotateAngleX = MathHelper.cos(var1 * 0.6662F + (float) Math.PI) * 1.4F * var2;
      this.f.rotateAngleX = MathHelper.cos(var1 * 0.6662F) * 1.4F * var2;
   }

   @Override
   public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      this.setRotationAngles(var2, var3, var4, var5, var6, var7, var1);
      if (this.r) {
         float var8 = 2.0F;
         GlStateManager.pushMatrix();
         GlStateManager.translate(0.0F, this.g * var7, this.h * var7);
         this.a.render(var7);
         GlStateManager.popMatrix();
         GlStateManager.pushMatrix();
         GlStateManager.scale(1.0F / var8, 1.0F / var8, 1.0F / var8);
         GlStateManager.translate(0.0F, 24.0F * var7, 0.0F);
         this.b.render(var7);
         this.c.render(var7);
         this.d.render(var7);
         this.e.render(var7);
         this.f.render(var7);
         GlStateManager.popMatrix();
      } else {
         this.a.render(var7);
         this.b.render(var7);
         this.c.render(var7);
         this.d.render(var7);
         this.e.render(var7);
         this.f.render(var7);
      }
   }
}
