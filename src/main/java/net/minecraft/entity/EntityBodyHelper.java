package net.minecraft.entity;

import net.minecraft.util.MathHelper;

public class EntityBodyHelper {
   public float prevRenderYawHead;
   public int rotationTickCounter;
   public EntityLivingBase theLiving;

   public EntityBodyHelper(EntityLivingBase var1) {
      this.theLiving = var1;
   }

   public float computeAngleWithBound(float var1, float var2, float var3) {
      float var4 = MathHelper.wrapAngleTo180_float(var1 - var2);
      if (var4 < -var3) {
         var4 = -var3;
      }

      if (var4 >= var3) {
         var4 = var3;
      }

      return var1 - var4;
   }

   public void updateRenderAngles() {
      double var1 = this.theLiving.s - this.theLiving.p;
      double var3 = this.theLiving.u - this.theLiving.r;
      if (var1 * var1 + var3 * var3 > 2.5000003E-7F) {
         this.theLiving.aI = this.theLiving.y;
         this.theLiving.aK = this.computeAngleWithBound(this.theLiving.aI, this.theLiving.aK, 75.0F);
         this.prevRenderYawHead = this.theLiving.aK;
         this.rotationTickCounter = 0;
      } else {
         float var5 = 75.0F;
         if (Math.abs(this.theLiving.aK - this.prevRenderYawHead) > 15.0F) {
            this.rotationTickCounter = 0;
            this.prevRenderYawHead = this.theLiving.aK;
         } else {
            this.rotationTickCounter++;
            byte var6 = 10;
            if (this.rotationTickCounter > 10) {
               var5 = Math.max(1.0F - (this.rotationTickCounter - 10) / 10.0F, 0.0F) * 75.0F;
            }
         }

         this.theLiving.aI = this.computeAngleWithBound(this.theLiving.aK, this.theLiving.aI, var5);
      }
   }
}
