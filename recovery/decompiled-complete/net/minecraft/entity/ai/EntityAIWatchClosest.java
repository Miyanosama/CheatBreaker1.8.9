package net.minecraft.entity.ai;

import com.cheatbreaker.client.module.type.DamageTintModule;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import recovered.unidentified.UnidentifiedClass1701;

public class EntityAIWatchClosest extends EntityAIBase {
   public DamageTintModule field_0002;
   public EntityLiving theWatcher;
   public float chance;
   public Class<? extends Entity> watchedClass;
   public Entity b;
   public float maxDistanceForPlayer;
   public int lookTime;
   public UnidentifiedClass1701 field_0000;

   @Override
   public boolean continueExecuting() {
      return !this.b.isEntityAlive() ? false : (this.theWatcher.h(this.b) > this.maxDistanceForPlayer * this.maxDistanceForPlayer ? false : this.lookTime > 0);
   }

   @Override
   public void resetTask() {
      this.b = null;
   }

   @Override
   public void startExecuting() {
      this.lookTime = 40 + this.theWatcher.getRNG().nextInt(40);
   }

   public EntityAIWatchClosest(EntityLiving var1, Class<? extends Entity> var2, float var3) {
      this.theWatcher = var1;
      this.watchedClass = var2;
      this.maxDistanceForPlayer = var3;
      this.chance = 0.02F;
      this.setMutexBits(2);
   }

   @Override
   public void updateTask() {
      this.theWatcher.getLookHelper().setLookPosition(this.b.s, this.b.t + this.b.getEyeHeight(), this.b.u, 10.0F, this.theWatcher.getVerticalFaceSpeed());
      this.lookTime--;
   }

   @Override
   public boolean shouldExecute() {
      if (this.theWatcher.getRNG().nextFloat() >= this.chance) {
         return false;
      } else {
         if (this.theWatcher.getAttackTarget() != null) {
            this.b = this.theWatcher.getAttackTarget();
         }

         if (this.watchedClass == EntityPlayer.class) {
            this.b = this.theWatcher.o.getClosestPlayerToEntity(this.theWatcher, this.maxDistanceForPlayer);
         } else {
            this.b = this.theWatcher
               .o
               .findNearestEntityWithinAABB(
                  this.watchedClass, this.theWatcher.getEntityBoundingBox().expand(this.maxDistanceForPlayer, 3.0, this.maxDistanceForPlayer), this.theWatcher
               );
         }

         return this.b != null;
      }
   }

   public EntityAIWatchClosest(EntityLiving var1, Class<? extends Entity> var2, float var3, float var4) {
      this.theWatcher = var1;
      this.watchedClass = var2;
      this.maxDistanceForPlayer = var3;
      this.chance = var4;
      this.setMutexBits(2);
   }
}
