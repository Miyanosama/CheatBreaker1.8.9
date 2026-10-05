package net.minecraft.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.world.World;

public class EntityAIOcelotAttack extends EntityAIBase {
   public EntityLiving recoveredField3114;
   public EntityLivingBase recoveredField3115;
   public World recoveredField3116;
   public int recoveredField3117;

   public EntityAIOcelotAttack(EntityLiving var1) {
      this.recoveredField3114 = var1;
      this.recoveredField3116 = var1.o;
      this.setMutexBits(3);
   }

   @Override
   public boolean shouldExecute() {
      EntityLivingBase var1 = this.recoveredField3114.getAttackTarget();
      if (var1 == null) {
         return false;
      } else {
         this.recoveredField3115 = var1;
         return true;
      }
   }

   @Override
   public void updateTask() {
      this.recoveredField3114.getLookHelper().setLookPositionWithEntity(this.recoveredField3115, 30.0F, 30.0F);
      double var1 = this.recoveredField3114.J * 2.0F * this.recoveredField3114.J * 2.0F;
      double var3 = this.recoveredField3114.e(this.recoveredField3115.s, this.recoveredField3115.getEntityBoundingBox().b, this.recoveredField3115.u);
      double var5 = 0.8;
      if (var3 > var1 && var3 < 16.0) {
         var5 = 1.33;
      } else if (var3 < 225.0) {
         var5 = 0.6;
      }

      this.recoveredField3114.s().tryMoveToEntityLiving(this.recoveredField3115, var5);
      this.recoveredField3117 = Math.max(this.recoveredField3117 - 1, 0);
      if (var3 <= var1 && this.recoveredField3117 <= 0) {
         this.recoveredField3117 = 20;
         this.recoveredField3114.attackEntityAsMob(this.recoveredField3115);
      }
   }

   @Override
   public void resetTask() {
      this.recoveredField3115 = null;
      this.recoveredField3114.s().clearPathEntity();
   }

   @Override
   public boolean continueExecuting() {
      return !this.recoveredField3115.isEntityAlive()
         ? false
         : (this.recoveredField3114.h(this.recoveredField3115) > 225.0 ? false : !this.recoveredField3114.s().noPath() || this.shouldExecute());
   }
}
