package net.minecraft.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.util.Vec3;

public class EntityAIWander extends EntityAIBase {
   public double xPosition;
   public int executionChance;
   public double yPosition;
   public boolean mustUpdate;
   public EntityCreature entity;
   public double speed;
   public double zPosition;

   public void setExecutionChance(int var1) {
      this.executionChance = var1;
   }

   @Override
   public void startExecuting() {
      this.entity.s().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, this.speed);
   }

   public EntityAIWander(EntityCreature var1, double var2) {
      this(var1, var2, 120);
   }

   @Override
   public boolean shouldExecute() {
      if (!this.mustUpdate) {
         if (this.entity.bh() >= 100) {
            return false;
         }

         if (this.entity.getRNG().nextInt(this.executionChance) != 0) {
            return false;
         }
      }

      Vec3 var1 = RandomPositionGenerator.findRandomTarget(this.entity, 10, 7);
      if (var1 == null) {
         return false;
      } else {
         this.xPosition = var1.xCoord;
         this.yPosition = var1.yCoord;
         this.zPosition = var1.zCoord;
         this.mustUpdate = false;
         return true;
      }
   }

   public void makeUpdate() {
      this.mustUpdate = true;
   }

   @Override
   public boolean continueExecuting() {
      return !this.entity.s().noPath();
   }

   public EntityAIWander(EntityCreature var1, double var2, int var4) {
      this.entity = var1;
      this.speed = var2;
      this.executionChance = var4;
      this.setMutexBits(1);
   }
}
