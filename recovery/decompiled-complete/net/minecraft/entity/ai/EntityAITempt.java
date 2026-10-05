package net.minecraft.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigateGround;

public class EntityAITempt extends EntityAIBase {
   public boolean isRunning;
   public Item temptItem;
   public double speed;
   public double targetY;
   public EntityPlayer temptingPlayer;
   public EntityCreature temptedEntity;
   public double targetZ;
   public int delayTemptCounter;
   public double yaw;
   public boolean avoidWater;
   public double pitch;
   public boolean scaredByPlayerMovement;
   public double targetX;

   @Override
   public boolean continueExecuting() {
      if (this.scaredByPlayerMovement) {
         if (this.temptedEntity.h(this.temptingPlayer) < 36.0) {
            if (this.temptingPlayer.e(this.targetX, this.targetY, this.targetZ) > 0.010000000000000002) {
               return false;
            }

            if (Math.abs(this.temptingPlayer.z - this.pitch) > 5.0 || Math.abs(this.temptingPlayer.y - this.yaw) > 5.0) {
               return false;
            }
         } else {
            this.targetX = this.temptingPlayer.s;
            this.targetY = this.temptingPlayer.t;
            this.targetZ = this.temptingPlayer.u;
         }

         this.pitch = this.temptingPlayer.z;
         this.yaw = this.temptingPlayer.y;
      }

      return this.shouldExecute();
   }

   public EntityAITempt(EntityCreature var1, double var2, Item var4, boolean var5) {
      this.temptedEntity = var1;
      this.speed = var2;
      this.temptItem = var4;
      this.scaredByPlayerMovement = var5;
      this.setMutexBits(3);
      if (!(var1.s() instanceof PathNavigateGround)) {
         throw new IllegalArgumentException("Unsupported mob type for TemptGoal");
      }
   }

   @Override
   public void resetTask() {
      this.temptingPlayer = null;
      this.temptedEntity.s().clearPathEntity();
      this.delayTemptCounter = 100;
      this.isRunning = false;
      ((PathNavigateGround)this.temptedEntity.s()).setAvoidsWater(this.avoidWater);
   }

   @Override
   public void updateTask() {
      this.temptedEntity.getLookHelper().setLookPositionWithEntity(this.temptingPlayer, 30.0F, this.temptedEntity.getVerticalFaceSpeed());
      if (this.temptedEntity.h(this.temptingPlayer) < 6.25) {
         this.temptedEntity.s().clearPathEntity();
      } else {
         this.temptedEntity.s().tryMoveToEntityLiving(this.temptingPlayer, this.speed);
      }
   }

   @Override
   public boolean shouldExecute() {
      if (this.delayTemptCounter > 0) {
         this.delayTemptCounter--;
         return false;
      } else {
         this.temptingPlayer = this.temptedEntity.o.getClosestPlayerToEntity(this.temptedEntity, 10.0);
         if (this.temptingPlayer == null) {
            return false;
         } else {
            ItemStack var1 = this.temptingPlayer.getCurrentEquippedItem();
            return var1 == null ? false : var1.getItem() == this.temptItem;
         }
      }
   }

   @Override
   public void startExecuting() {
      this.targetX = this.temptingPlayer.s;
      this.targetY = this.temptingPlayer.t;
      this.targetZ = this.temptingPlayer.u;
      this.isRunning = true;
      this.avoidWater = ((PathNavigateGround)this.temptedEntity.s()).getAvoidsWater();
      ((PathNavigateGround)this.temptedEntity.s()).setAvoidsWater(false);
   }

   public boolean isRunning() {
      return this.isRunning;
   }
}
