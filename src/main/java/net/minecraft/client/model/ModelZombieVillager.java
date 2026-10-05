package net.minecraft.client.model;

import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelZombieVillager extends ModelBiped {
   public ModelZombieVillager() {
      this(0.0F, 0.0F, false);
   }

   @Override
   public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7) {
      super.setRotationAngles(var1, var2, var3, var4, var5, var6, var7);
      float var8 = MathHelper.sin(this.p * (float) Math.PI);
      float var9 = MathHelper.sin((1.0F - (1.0F - this.p) * (1.0F - this.p)) * (float) Math.PI);
      this.h.rotateAngleZ = 0.0F;
      this.i.rotateAngleZ = 0.0F;
      this.h.rotateAngleY = -(0.1F - var8 * 0.6F);
      this.i.rotateAngleY = 0.1F - var8 * 0.6F;
      this.h.rotateAngleX = (float) (-Math.PI / 2);
      this.i.rotateAngleX = (float) (-Math.PI / 2);
      this.h.rotateAngleX -= var8 * 1.2F - var9 * 0.4F;
      this.i.rotateAngleX -= var8 * 1.2F - var9 * 0.4F;
      this.h.rotateAngleZ = this.h.rotateAngleZ + (MathHelper.cos(var3 * 0.09F) * 0.05F + 0.05F);
      this.i.rotateAngleZ = this.i.rotateAngleZ - (MathHelper.cos(var3 * 0.09F) * 0.05F + 0.05F);
      this.h.rotateAngleX = this.h.rotateAngleX + MathHelper.sin(var3 * 0.067F) * 0.05F;
      this.i.rotateAngleX = this.i.rotateAngleX - MathHelper.sin(var3 * 0.067F) * 0.05F;
   }

   public ModelZombieVillager(float var1, float var2, boolean var3) {
      super(var1, 0.0F, 64, var3 ? 32 : 64);
      if (var3) {
         this.e = new ModelRenderer(this, 0, 0);
         this.e.addBox(-4.0F, -10.0F, -4.0F, 8, 8, 8, var1);
         this.e.setRotationPoint(0.0F, 0.0F + var2, 0.0F);
      } else {
         this.e = new ModelRenderer(this);
         this.e.setRotationPoint(0.0F, 0.0F + var2, 0.0F);
         this.e.setTextureOffset(0, 32).addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, var1);
         this.e.setTextureOffset(24, 32).addBox(-1.0F, -3.0F, -6.0F, 2, 4, 2, var1);
      }
   }
}
