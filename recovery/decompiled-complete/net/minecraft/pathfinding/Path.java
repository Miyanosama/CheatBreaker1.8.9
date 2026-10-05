package net.minecraft.pathfinding;

import io.netty.util.internal.PlatformDependent0$3;
import net.minecraft.block.BlockChest;
import net.minecraft.block.properties.PropertyEnum;

public class Path {
   public BlockChest field_0002;
   public PathPoint[] pathPoints = new PathPoint[1024];
   public int count;
   public PropertyEnum field_0003;
   public PlatformDependent0$3 field_0000;

   public void clearPath() {
      this.count = 0;
   }

   public void sortForward(int var1) {
      PathPoint var2 = this.pathPoints[var1];
      float var3 = var2.distanceToTarget;

      while (true) {
         int var4 = 1 + (var1 << 1);
         int var5 = var4 + 1;
         if (var4 >= this.count) {
            break;
         }

         PathPoint var6 = this.pathPoints[var4];
         float var7 = var6.distanceToTarget;
         PathPoint var8;
         float var9;
         if (var5 >= this.count) {
            var8 = null;
            var9 = Float.POSITIVE_INFINITY;
         } else {
            var8 = this.pathPoints[var5];
            var9 = var8.distanceToTarget;
         }

         if (var7 < var9) {
            if (var7 >= var3) {
               break;
            }

            this.pathPoints[var1] = var6;
            var6.index = var1;
            var1 = var4;
         } else {
            if (var9 >= var3) {
               break;
            }

            this.pathPoints[var1] = var8;
            var8.index = var1;
            var1 = var5;
         }
      }

      this.pathPoints[var1] = var2;
      var2.index = var1;
   }

   public boolean isPathEmpty() {
      return this.count == 0;
   }

   public void changeDistance(PathPoint var1, float var2) {
      float var3 = var1.distanceToTarget;
      var1.distanceToTarget = var2;
      if (var2 < var3) {
         this.sortBack(var1.index);
      } else {
         this.sortForward(var1.index);
      }
   }

   public void sortBack(int var1) {
      PathPoint var2 = this.pathPoints[var1];
      float var4 = var2.distanceToTarget;

      while (var1 > 0) {
         int var3 = var1 - 1 >> 1;
         PathPoint var5 = this.pathPoints[var3];
         if (var4 >= var5.distanceToTarget) {
            break;
         }

         this.pathPoints[var1] = var5;
         var5.index = var1;
         var1 = var3;
      }

      this.pathPoints[var1] = var2;
      var2.index = var1;
   }

   public PathPoint addPoint(PathPoint var1) {
      if (var1.index >= 0) {
         throw new IllegalStateException("OW KNOWS!");
      } else {
         if (this.count == this.pathPoints.length) {
            PathPoint[] var2 = new PathPoint[this.count << 1];
            System.arraycopy(this.pathPoints, 0, var2, 0, this.count);
            this.pathPoints = var2;
         }

         this.pathPoints[this.count] = var1;
         var1.index = this.count;
         this.sortBack(this.count++);
         return var1;
      }
   }

   public PathPoint dequeue() {
      PathPoint var1 = this.pathPoints[0];
      this.pathPoints[0] = this.pathPoints[--this.count];
      this.pathPoints[this.count] = null;
      if (this.count > 0) {
         this.sortForward(0);
      }

      var1.index = -1;
      return var1;
   }
}
