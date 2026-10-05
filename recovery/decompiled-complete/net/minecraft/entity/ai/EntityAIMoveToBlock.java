package net.minecraft.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class EntityAIMoveToBlock extends EntityAIBase {
   public EntityCreature theEntity;
   public int field_179490_f;
   public int timeoutCounter;
   public boolean isAboveDestination;
   public double movementSpeed;
   public int searchLength;
   public BlockPos destinationBlock = BlockPos.ORIGIN;
   public int a;
   public WorldGenerator field_0007;

   @Override
   public void resetTask() {
   }

   @Override
   public boolean continueExecuting() {
      return this.timeoutCounter >= -this.field_179490_f && this.timeoutCounter <= 1200 && this.shouldMoveTo(this.theEntity.o, this.destinationBlock);
   }

   @Override
   public boolean shouldExecute() {
      if (this.a > 0) {
         this.a--;
         return false;
      } else {
         this.a = 200 + this.theEntity.getRNG().nextInt(200);
         return this.searchForDestination();
      }
   }

   @Override
   public void updateTask() {
      if (this.theEntity.getDistanceSqToCenter(this.destinationBlock.up()) > 1.0) {
         this.isAboveDestination = false;
         this.timeoutCounter++;
         if (this.timeoutCounter % 40 == 0) {
            this.theEntity
               .s()
               .tryMoveToXYZ(this.destinationBlock.getX() + 0.5, this.destinationBlock.getY() + 1, this.destinationBlock.getZ() + 0.5, this.movementSpeed);
         }
      } else {
         this.isAboveDestination = true;
         this.timeoutCounter--;
      }
   }

   public boolean searchForDestination() {
      int var1 = this.searchLength;
      boolean var2 = true;
      BlockPos var3 = new BlockPos(this.theEntity);

      for (int var4 = 0; var4 <= 1; var4 = var4 > 0 ? -var4 : 1 - var4) {
         for (int var5 = 0; var5 < var1; var5++) {
            for (int var6 = 0; var6 <= var5; var6 = var6 > 0 ? -var6 : 1 - var6) {
               for (int var7 = var6 < var5 && var6 > -var5 ? var5 : 0; var7 <= var5; var7 = var7 > 0 ? -var7 : 1 - var7) {
                  BlockPos var8 = var3.add(var6, var4 - 1, var7);
                  if (this.theEntity.isWithinHomeDistanceFromPosition(var8) && this.shouldMoveTo(this.theEntity.o, var8)) {
                     this.destinationBlock = var8;
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   public boolean getIsAboveDestination() {
      return this.isAboveDestination;
   }

   @Override
   public void startExecuting() {
      this.theEntity
         .s()
         .tryMoveToXYZ(this.destinationBlock.getX() + 0.5, this.destinationBlock.getY() + 1, this.destinationBlock.getZ() + 0.5, this.movementSpeed);
      this.timeoutCounter = 0;
      this.field_179490_f = this.theEntity.getRNG().nextInt(this.theEntity.getRNG().nextInt(1200) + 1200) + 1200;
   }

   public EntityAIMoveToBlock(EntityCreature var1, double var2, int var4) {
      this.theEntity = var1;
      this.movementSpeed = var2;
      this.searchLength = var4;
      this.setMutexBits(5);
   }

   public abstract boolean shouldMoveTo(World var1, BlockPos var2);
}
