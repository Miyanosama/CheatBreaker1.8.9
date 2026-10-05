package net.minecraft.pathfinding;

import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.pathfinder.SwimNodeProcessor;

public class PathNavigateSwimmer extends PathNavigate {
   public PathNavigateSwimmer(EntityLiving var1, World var2) {
      super(var1, var2);
   }

   @Override
   public PathFinder getPathFinder() {
      return new PathFinder(new SwimNodeProcessor());
   }

   @Override
   public boolean isDirectPathBetweenPoints(Vec3 var1, Vec3 var2, int var3, int var4, int var5) {
      MovingObjectPosition var6 = this.c.rayTraceBlocks(var1, new Vec3(var2.xCoord, var2.yCoord + this.b.K * 0.5, var2.zCoord), false, true, false);
      return var6 == null || var6.typeOfHit == MovingObjectPosition.MovingObjectType.MISS;
   }

   @Override
   public boolean canNavigate() {
      return this.o();
   }

   @Override
   public Vec3 getEntityPosition() {
      return new Vec3(this.b.s, this.b.t + this.b.K * 0.5, this.b.u);
   }

   @Override
   public void pathFollow() {
      Vec3 var1 = this.getEntityPosition();
      float var2 = this.b.J * this.b.J;
      byte var3 = 6;
      if (var1.squareDistanceTo(this.currentPath.getVectorFromIndex(this.b, this.currentPath.getCurrentPathIndex())) < var2) {
         this.currentPath.incrementPathIndex();
      }

      for (int var4 = Math.min(this.currentPath.getCurrentPathIndex() + var3, this.currentPath.getCurrentPathLength() - 1);
         var4 > this.currentPath.getCurrentPathIndex();
         var4--
      ) {
         Vec3 var5 = this.currentPath.getVectorFromIndex(this.b, var4);
         if (var5.squareDistanceTo(var1) <= 36.0 && this.isDirectPathBetweenPoints(var1, var5, 0, 0, 0)) {
            this.currentPath.setCurrentPathIndex(var4);
            break;
         }
      }

      this.checkForStuck(var1);
   }

   @Override
   public void removeSunnyPath() {
      super.removeSunnyPath();
   }
}
