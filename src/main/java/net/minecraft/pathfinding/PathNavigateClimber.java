package net.minecraft.pathfinding;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class PathNavigateClimber extends PathNavigateGround {
   public BlockPos targetPosition;

   @Override
   public boolean tryMoveToEntityLiving(Entity var1, double var2) {
      PathEntity var4 = this.getPathToEntityLiving(var1);
      if (var4 != null) {
         return this.setPath(var4, var2);
      } else {
         this.targetPosition = new BlockPos(var1);
         this.speed = var2;
         return true;
      }
   }

   @Override
   public PathEntity getPathToEntityLiving(Entity var1) {
      this.targetPosition = new BlockPos(var1);
      return super.getPathToEntityLiving(var1);
   }

   public PathNavigateClimber(EntityLiving var1, World var2) {
      super(var1, var2);
   }

   @Override
   public PathEntity getPathToPos(BlockPos var1) {
      this.targetPosition = var1;
      return super.getPathToPos(var1);
   }

   @Override
   public void onUpdateNavigation() {
      if (!this.noPath()) {
         super.onUpdateNavigation();
      } else if (this.targetPosition != null) {
         double var1 = this.b.J * this.b.J;
         if (!(this.b.getDistanceSqToCenter(this.targetPosition) >= var1)
            || !(this.b.t <= this.targetPosition.getY())
               && !(
                  this.b.getDistanceSqToCenter(new BlockPos(this.targetPosition.getX(), MathHelper.floor_double(this.b.t), this.targetPosition.getZ())) >= var1
               )) {
            this.targetPosition = null;
         } else {
            this.b.q().setMoveTo(this.targetPosition.getX(), this.targetPosition.getY(), this.targetPosition.getZ(), this.speed);
         }
      }
   }
}
