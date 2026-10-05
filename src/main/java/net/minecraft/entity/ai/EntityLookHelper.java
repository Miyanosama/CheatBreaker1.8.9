package net.minecraft.entity.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class EntityLookHelper {
   public double posZ;
   public float deltaLookYaw;
   public boolean isLooking;
   public float deltaLookPitch;
   public EntityLiving entity;
   public double posX;
   public double posY;

   public float updateRotation(float var1, float var2, float var3) {
      float var4 = MathHelper.wrapAngleTo180_float(var2 - var1);
      if (var4 > var3) {
         var4 = var3;
      }

      if (var4 < -var3) {
         var4 = -var3;
      }

      return var1 + var4;
   }

   public double getLookPosZ() {
      return this.posZ;
   }

   public EntityLookHelper(EntityLiving var1) {
      this.entity = var1;
   }

   public void setLookPosition(double var1, double var3, double var5, float var7, float var8) {
      this.posX = var1;
      this.posY = var3;
      this.posZ = var5;
      this.deltaLookYaw = var7;
      this.deltaLookPitch = var8;
      this.isLooking = true;
   }

   public void setLookPositionWithEntity(Entity var1, float var2, float var3) {
      this.posX = var1.s;
      if (var1 instanceof EntityLivingBase) {
         this.posY = var1.t + var1.getEyeHeight();
      } else {
         this.posY = (var1.getEntityBoundingBox().b + var1.getEntityBoundingBox().e) / 2.0;
      }

      this.posZ = var1.u;
      this.deltaLookYaw = var2;
      this.deltaLookPitch = var3;
      this.isLooking = true;
   }

   public void onUpdateLook() {
      this.entity.z = 0.0F;
      if (this.isLooking) {
         this.isLooking = false;
         double var1 = this.posX - this.entity.s;
         double var3 = this.posY - (this.entity.t + this.entity.getEyeHeight());
         double var5 = this.posZ - this.entity.u;
         double var7 = MathHelper.sqrt_double(var1 * var1 + var5 * var5);
         float var9 = (float)(MathHelper.atan2(var5, var1) * 180.0 / Math.PI) - 90.0F;
         float var10 = (float)(-(MathHelper.atan2(var3, var7) * 180.0 / Math.PI));
         this.entity.z = this.updateRotation(this.entity.z, var10, this.deltaLookPitch);
         this.entity.aK = this.updateRotation(this.entity.aK, var9, this.deltaLookYaw);
      } else {
         this.entity.aK = this.updateRotation(this.entity.aK, this.entity.aI, 10.0F);
      }

      float var11 = MathHelper.wrapAngleTo180_float(this.entity.aK - this.entity.aI);
      if (!this.entity.s().noPath()) {
         if (var11 < -75.0F) {
            this.entity.aK = this.entity.aI - 75.0F;
         }

         if (var11 > 75.0F) {
            this.entity.aK = this.entity.aI + 75.0F;
         }
      }
   }

   public double getLookPosX() {
      return this.posX;
   }

   public double getLookPosY() {
      return this.posY;
   }

   public boolean getIsLooking() {
      return this.isLooking;
   }
}
