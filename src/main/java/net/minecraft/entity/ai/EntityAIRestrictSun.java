package net.minecraft.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.pathfinding.PathNavigateGround;

public class EntityAIRestrictSun extends EntityAIBase {
   public EntityCreature theEntity;

   @Override
   public boolean shouldExecute() {
      return this.theEntity.o.isDaytime();
   }

   @Override
   public void resetTask() {
      ((PathNavigateGround)this.theEntity.s()).setAvoidSun(false);
   }

   @Override
   public void startExecuting() {
      ((PathNavigateGround)this.theEntity.s()).setAvoidSun(true);
   }

   public EntityAIRestrictSun(EntityCreature var1) {
      this.theEntity = var1;
   }
}
