package net.minecraft.entity.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;

public class EntityAICreeperSwell extends EntityAIBase {
   public EntityCreeper swellingCreeper;
   public EntityLivingBase creeperAttackTarget;

   @Override
   public void resetTask() {
      this.creeperAttackTarget = null;
   }

   @Override
   public void updateTask() {
      if (this.creeperAttackTarget == null) {
         this.swellingCreeper.setCreeperState(-1);
      } else if (this.swellingCreeper.h(this.creeperAttackTarget) > 49.0) {
         this.swellingCreeper.setCreeperState(-1);
      } else if (!this.swellingCreeper.getEntitySenses().canSee(this.creeperAttackTarget)) {
         this.swellingCreeper.setCreeperState(-1);
      } else {
         this.swellingCreeper.setCreeperState(1);
      }
   }

   @Override
   public void startExecuting() {
      this.swellingCreeper.s().clearPathEntity();
      this.creeperAttackTarget = this.swellingCreeper.getAttackTarget();
   }

   public EntityAICreeperSwell(EntityCreeper var1) {
      this.swellingCreeper = var1;
      this.setMutexBits(1);
   }

   @Override
   public boolean shouldExecute() {
      EntityLivingBase var1 = this.swellingCreeper.getAttackTarget();
      return this.swellingCreeper.getCreeperState() > 0 || var1 != null && this.swellingCreeper.h(var1) < 9.0;
   }
}
