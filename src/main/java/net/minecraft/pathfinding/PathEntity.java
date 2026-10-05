package net.minecraft.pathfinding;

import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;

public class PathEntity {
   public int currentPathIndex;
   public int pathLength;
   public PathPoint[] points;

   public int getCurrentPathIndex() {
      return this.currentPathIndex;
   }

   public int getCurrentPathLength() {
      return this.pathLength;
   }

   public boolean isDestinationSame(Vec3 var1) {
      PathPoint var2 = this.getFinalPathPoint();
      return var2 == null ? false : var2.xCoord == (int)var1.xCoord && var2.zCoord == (int)var1.zCoord;
   }

   public Vec3 getPosition(Entity var1) {
      return this.getVectorFromIndex(var1, this.currentPathIndex);
   }

   public boolean isFinished() {
      return this.currentPathIndex >= this.pathLength;
   }

   public PathEntity(PathPoint[] var1) {
      this.points = var1;
      this.pathLength = var1.length;
   }

   public PathPoint getPathPointFromIndex(int var1) {
      return this.points[var1];
   }

   public void setCurrentPathIndex(int var1) {
      this.currentPathIndex = var1;
   }

   public void setCurrentPathLength(int var1) {
      this.pathLength = var1;
   }

   public Vec3 getVectorFromIndex(Entity var1, int var2) {
      double var3 = this.points[var2].xCoord + (int)(var1.J + 1.0F) * 0.5;
      double var5 = this.points[var2].yCoord;
      double var7 = this.points[var2].zCoord + (int)(var1.J + 1.0F) * 0.5;
      return new Vec3(var3, var5, var7);
   }

   public boolean isSamePath(PathEntity var1) {
      if (var1 == null) {
         return false;
      } else if (var1.points.length != this.points.length) {
         return false;
      } else {
         for (int var2 = 0; var2 < this.points.length; var2++) {
            if (this.points[var2].xCoord != var1.points[var2].xCoord
               || this.points[var2].yCoord != var1.points[var2].yCoord
               || this.points[var2].zCoord != var1.points[var2].zCoord) {
               return false;
            }
         }

         return true;
      }
   }

   public void incrementPathIndex() {
      this.currentPathIndex++;
   }

   public PathPoint getFinalPathPoint() {
      return this.pathLength > 0 ? this.points[this.pathLength - 1] : null;
   }
}
