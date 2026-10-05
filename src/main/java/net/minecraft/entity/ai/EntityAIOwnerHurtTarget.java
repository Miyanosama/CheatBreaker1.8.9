package net.minecraft.entity.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;

public class EntityAIOwnerHurtTarget extends EntityAITarget {
   public EntityTameable theEntityTameable;
   public int field_142050_e;
   public EntityLivingBase theTarget;

   @Override
   public boolean shouldExecute() {
      if (!this.theEntityTameable.isTamed()) {
         return false;
      } else {
         EntityLivingBase var1 = this.theEntityTameable.getOwner();
         if (var1 == null) {
            return false;
         } else {
            this.theTarget = var1.getLastAttacker();
            int var2 = var1.getLastAttackerTime();
            return var2 != this.field_142050_e && this.a(this.theTarget, false) && this.theEntityTameable.shouldAttackEntity(this.theTarget, var1);
         }
      }
   }

   @Override
   public void startExecuting() {
      this.e.setAttackTarget(this.theTarget);
      EntityLivingBase var1 = this.theEntityTameable.getOwner();
      if (var1 != null) {
         this.field_142050_e = var1.getLastAttackerTime();
      }

      super.startExecuting();
   }

   public EntityAIOwnerHurtTarget(EntityTameable var1) {
      super(var1, false);
      this.theEntityTameable = var1;
      this.setMutexBits(1);
   }
}
