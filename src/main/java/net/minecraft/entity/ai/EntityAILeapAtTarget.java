package net.minecraft.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class EntityAILeapAtTarget extends EntityAIBase {
   public EntityLivingBase leapTarget;
   public EntityLiving leaper;
   public float leapMotionY;

   @Override
   public void startExecuting() {
      double var1 = this.leapTarget.s - this.leaper.s;
      double var3 = this.leapTarget.u - this.leaper.u;
      float var5 = MathHelper.sqrt_double(var1 * var1 + var3 * var3);
      this.leaper.v = this.leaper.v + (var1 / var5 * 0.5 * 0.8F + this.leaper.v * 0.2F);
      this.leaper.x = this.leaper.x + (var3 / var5 * 0.5 * 0.8F + this.leaper.x * 0.2F);
      this.leaper.w = this.leapMotionY;
   }

   @Override
   public boolean continueExecuting() {
      return !this.leaper.C;
   }

   public EntityAILeapAtTarget(EntityLiving var1, float var2) {
      this.leaper = var1;
      this.leapMotionY = var2;
      this.setMutexBits(5);
   }

   @Override
   public boolean shouldExecute() {
      this.leapTarget = this.leaper.getAttackTarget();
      if (this.leapTarget == null) {
         return false;
      } else {
         double var1 = this.leaper.h(this.leapTarget);
         return !(var1 >= 4.0) || !(var1 <= 16.0) ? false : (!this.leaper.C ? false : this.leaper.getRNG().nextInt(5) == 0);
      }
   }
}
