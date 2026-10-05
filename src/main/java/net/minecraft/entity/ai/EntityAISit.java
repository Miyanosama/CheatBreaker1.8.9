package net.minecraft.entity.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;

public class EntityAISit extends EntityAIBase {
   public boolean isSitting;
   public EntityTameable theEntity;

   public void setSitting(boolean var1) {
      this.isSitting = var1;
   }

   @Override
   public void resetTask() {
      this.theEntity.setSitting(false);
   }

   @Override
   public boolean shouldExecute() {
      if (!this.theEntity.isTamed()) {
         return false;
      } else if (this.theEntity.V()) {
         return false;
      } else if (!this.theEntity.C) {
         return false;
      } else {
         EntityLivingBase var1 = this.theEntity.getOwner();
         return var1 == null ? true : (this.theEntity.h(var1) < 144.0 && var1.getAITarget() != null ? false : this.isSitting);
      }
   }

   @Override
   public void startExecuting() {
      this.theEntity.s().clearPathEntity();
      this.theEntity.setSitting(true);
   }

   public EntityAISit(EntityTameable var1) {
      this.theEntity = var1;
      this.setMutexBits(5);
   }
}
