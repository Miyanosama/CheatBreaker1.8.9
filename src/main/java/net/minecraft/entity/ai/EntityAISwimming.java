package net.minecraft.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.pathfinding.PathNavigateGround;

public class EntityAISwimming extends EntityAIBase {
   public EntityLiving theEntity;

   @Override
   public void updateTask() {
      if (this.theEntity.getRNG().nextFloat() < 0.8F) {
         this.theEntity.r().setJumping();
      }
   }

   public EntityAISwimming(EntityLiving var1) {
      this.theEntity = var1;
      this.setMutexBits(4);
      ((PathNavigateGround)var1.s()).setCanSwim(true);
   }

   @Override
   public boolean shouldExecute() {
      return this.theEntity.V() || this.theEntity.ab();
   }
}
