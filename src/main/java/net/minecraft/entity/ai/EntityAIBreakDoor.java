package net.minecraft.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.EnumDifficulty;

public class EntityAIBreakDoor extends EntityAIDoorInteract {
   public int previousBreakProgress = -1;
   public int breakingTime;

   public EntityAIBreakDoor(EntityLiving var1) {
      super(var1);
   }

   @Override
   public void startExecuting() {
      super.startExecuting();
      this.breakingTime = 0;
   }

   @Override
   public boolean continueExecuting() {
      double var1 = this.a.getDistanceSq(this.b);
      if (this.breakingTime <= 240) {
         BlockDoor var4 = this.c;
         if (!BlockDoor.isOpen(this.a.o, this.b) && var1 < 4.0) {
            return true;
         }
      }

      return false;
   }

   @Override
   public boolean shouldExecute() {
      if (!super.shouldExecute()) {
         return false;
      } else if (!this.a.o.Q().getBoolean("mobGriefing")) {
         return false;
      } else {
         BlockDoor var1 = this.c;
         return !BlockDoor.isOpen(this.a.o, this.b);
      }
   }

   @Override
   public void updateTask() {
      super.updateTask();
      if (this.a.getRNG().nextInt(20) == 0) {
         this.a.o.b(1010, this.b, 0);
      }

      this.breakingTime++;
      int var1 = (int)(this.breakingTime / 240.0F * 10.0F);
      if (var1 != this.previousBreakProgress) {
         this.a.o.sendBlockBreakProgress(this.a.F(), this.b, var1);
         this.previousBreakProgress = var1;
      }

      if (this.breakingTime == 240 && this.a.o.getDifficulty() == EnumDifficulty.HARD) {
         this.a.o.setBlockToAir(this.b);
         this.a.o.b(1012, this.b, 0);
         this.a.o.b(2001, this.b, Block.getIdFromBlock(this.c));
      }
   }

   @Override
   public void resetTask() {
      super.resetTask();
      this.a.o.sendBlockBreakProgress(this.a.F(), this.b, -1);
   }
}
