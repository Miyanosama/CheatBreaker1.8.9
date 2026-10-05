package net.minecraft.client.model;

import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelZombie extends ModelBiped {
   public ModelZombie(float var1, boolean var2) {
      super(var1, 0.0F, 64, var2 ? 32 : 64);
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

   public ModelZombie() {
      this(0.0F, false);
   }

   public ModelZombie(float var1, float var2, int var3, int var4) {
      super(var1, var2, var3, var4);
   }
}
