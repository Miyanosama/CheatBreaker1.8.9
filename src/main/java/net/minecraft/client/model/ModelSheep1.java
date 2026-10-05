package net.minecraft.client.model;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;

public class ModelSheep1 extends ModelQuadruped {
   public float headRotationAngleX;

   @Override
   public void setLivingAnimations(EntityLivingBase var1, float var2, float var3, float var4) {
      super.setLivingAnimations(var1, var2, var3, var4);
      this.a.rotationPointY = 6.0F + ((EntitySheep)var1).getHeadRotationPointY(var4) * 9.0F;
      this.headRotationAngleX = ((EntitySheep)var1).getHeadRotationAngleX(var4);
   }

   @Override
   public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7) {
      super.setRotationAngles(var1, var2, var3, var4, var5, var6, var7);
      this.a.rotateAngleX = this.headRotationAngleX;
   }

   public ModelSheep1() {
      super(12, 0.0F);
      this.a = new ModelRenderer(this, 0, 0);
      this.a.addBox(-3.0F, -4.0F, -4.0F, 6, 6, 6, 0.6F);
      this.a.setRotationPoint(0.0F, 6.0F, -8.0F);
      this.b = new ModelRenderer(this, 28, 8);
      this.b.addBox(-4.0F, -10.0F, -7.0F, 8, 16, 6, 1.75F);
      this.b.setRotationPoint(0.0F, 5.0F, 2.0F);
      float var1 = 0.5F;
      this.c = new ModelRenderer(this, 0, 16);
      this.c.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, var1);
      this.c.setRotationPoint(-3.0F, 12.0F, 7.0F);
      this.d = new ModelRenderer(this, 0, 16);
      this.d.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, var1);
      this.d.setRotationPoint(3.0F, 12.0F, 7.0F);
      this.e = new ModelRenderer(this, 0, 16);
      this.e.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, var1);
      this.e.setRotationPoint(-3.0F, 12.0F, -5.0F);
      this.f = new ModelRenderer(this, 0, 16);
      this.f.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, var1);
      this.f.setRotationPoint(3.0F, 12.0F, -5.0F);
   }
}
