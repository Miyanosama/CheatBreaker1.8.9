package net.minecraft.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.BlockPos;

public abstract class EntityAIDoorInteract extends EntityAIBase {
   public BlockPos b = BlockPos.ORIGIN;
   public float entityPositionX;
   public boolean hasStoppedDoorInteraction;
   public EntityLiving a;
   public float entityPositionZ;
   public BlockDoor c;

   @Override
   public boolean shouldExecute() {
      if (!this.a.D) {
         return false;
      } else {
         PathNavigateGround var1 = (PathNavigateGround)this.a.s();
         PathEntity var2 = var1.getPath();
         if (var2 != null && !var2.isFinished() && var1.getEnterDoors()) {
            for (int var3 = 0; var3 < Math.min(var2.getCurrentPathIndex() + 2, var2.getCurrentPathLength()); var3++) {
               PathPoint var4 = var2.getPathPointFromIndex(var3);
               this.b = new BlockPos(var4.xCoord, var4.yCoord + 1, var4.zCoord);
               if (this.a.e(this.b.getX(), this.a.t, this.b.getZ()) <= 2.25) {
                  this.c = this.getBlockDoor(this.b);
                  if (this.c != null) {
                     return true;
                  }
               }
            }

            this.b = new BlockPos(this.a).up();
            this.c = this.getBlockDoor(this.b);
            return this.c != null;
         } else {
            return false;
         }
      }
   }

   @Override
   public boolean continueExecuting() {
      return !this.hasStoppedDoorInteraction;
   }

   public EntityAIDoorInteract(EntityLiving var1) {
      this.a = var1;
      if (!(var1.s() instanceof PathNavigateGround)) {
         throw new IllegalArgumentException("Unsupported mob type for DoorInteractGoal");
      }
   }

   public BlockDoor getBlockDoor(BlockPos var1) {
      Block var2 = this.a.o.getBlockState(var1).getBlock();
      return var2 instanceof BlockDoor && var2.getMaterial() == Material.wood ? (BlockDoor)var2 : null;
   }

   @Override
   public void startExecuting() {
      this.hasStoppedDoorInteraction = false;
      this.entityPositionX = (float)(this.b.getX() + 0.5F - this.a.s);
      this.entityPositionZ = (float)(this.b.getZ() + 0.5F - this.a.u);
   }

   @Override
   public void updateTask() {
      float var1 = (float)(this.b.getX() + 0.5F - this.a.s);
      float var2 = (float)(this.b.getZ() + 0.5F - this.a.u);
      float var3 = this.entityPositionX * var1 + this.entityPositionZ * var2;
      if (var3 < 0.0F) {
         this.hasStoppedDoorInteraction = true;
      }
   }
}
