package net.minecraft.pathfinding;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.World;

public abstract class PathNavigate {
   public IAttributeInstance pathSearchRange;
   public PathFinder pathFinder;
   public float heightRequirement;
   public EntityLiving b;
   public double speed;
   public int totalTicks;
   public World c;
   public int ticksAtLastPos;
   public PathEntity currentPath;
   public Vec3 lastPosCheck = new Vec3(0.0, 0.0, 0.0);

   public boolean o() {
      return this.b.V() || this.b.ab();
   }

   public PathNavigate(EntityLiving var1, World var2) {
      this.heightRequirement = 1.0F;
      this.b = var1;
      this.c = var2;
      this.pathSearchRange = var1.getEntityAttribute(SharedMonsterAttributes.followRange);
      this.pathFinder = this.getPathFinder();
   }

   public PathEntity getPathToEntityLiving(Entity var1) {
      if (!this.canNavigate()) {
         return null;
      } else {
         float var2 = this.getPathSearchRange();
         this.c.B.startSection("pathfind");
         BlockPos var3 = new BlockPos(this.b).up();
         int var4 = (int)(var2 + 16.0F);
         ChunkCache var5 = new ChunkCache(this.c, var3.add(-var4, -var4, -var4), var3.add(var4, var4, var4), 0);
         PathEntity var6 = this.pathFinder.createEntityPathTo(var5, this.b, var1, var2);
         this.c.B.endSection();
         return var6;
      }
   }

   public boolean noPath() {
      return this.currentPath == null || this.currentPath.isFinished();
   }

   public abstract Vec3 getEntityPosition();

   public void setSpeed(double var1) {
      this.speed = var1;
   }

   public abstract boolean isDirectPathBetweenPoints(Vec3 var1, Vec3 var2, int var3, int var4, int var5);

   public void checkForStuck(Vec3 var1) {
      if (this.totalTicks - this.ticksAtLastPos > 100) {
         if (var1.squareDistanceTo(this.lastPosCheck) < 2.25) {
            this.clearPathEntity();
         }

         this.ticksAtLastPos = this.totalTicks;
         this.lastPosCheck = var1;
      }
   }

   public boolean tryMoveToXYZ(double var1, double var3, double var5, double var7) {
      PathEntity var9 = this.getPathToXYZ(MathHelper.floor_double(var1), (int)var3, MathHelper.floor_double(var5));
      return this.setPath(var9, var7);
   }

   public void removeSunnyPath() {
   }

   public PathEntity getPathToXYZ(double var1, double var3, double var5) {
      return this.getPathToPos(new BlockPos(MathHelper.floor_double(var1), (int)var3, MathHelper.floor_double(var5)));
   }

   public void clearPathEntity() {
      this.currentPath = null;
   }

   public void onUpdateNavigation() {
      this.totalTicks++;
      if (!this.noPath()) {
         if (this.canNavigate()) {
            this.pathFollow();
         } else if (this.currentPath != null && this.currentPath.getCurrentPathIndex() < this.currentPath.getCurrentPathLength()) {
            Vec3 var1 = this.getEntityPosition();
            Vec3 var2 = this.currentPath.getVectorFromIndex(this.b, this.currentPath.getCurrentPathIndex());
            if (var1.yCoord > var2.yCoord
               && !this.b.C
               && MathHelper.floor_double(var1.xCoord) == MathHelper.floor_double(var2.xCoord)
               && MathHelper.floor_double(var1.zCoord) == MathHelper.floor_double(var2.zCoord)) {
               this.currentPath.setCurrentPathIndex(this.currentPath.getCurrentPathIndex() + 1);
            }
         }

         if (!this.noPath()) {
            Vec3 var8 = this.currentPath.getPosition(this.b);
            if (var8 != null) {
               AxisAlignedBB var9 = new AxisAlignedBB(var8.xCoord, var8.yCoord, var8.zCoord, var8.xCoord, var8.yCoord, var8.zCoord).expand(0.5, 0.5, 0.5);
               List var3 = this.c.a(this.b, var9.addCoord(0.0, -1.0, 0.0));
               double var4 = -1.0;
               var9 = var9.offset(0.0, 1.0, 0.0);

               for (AxisAlignedBB var7 : (Iterable<AxisAlignedBB>)(Iterable<?>)(var3)) {
                  var4 = var7.method_09646(var9, var4);
               }

               this.b.q().setMoveTo(var8.xCoord, var8.yCoord + var4, var8.zCoord, this.speed);
            }
         }
      }
   }

   public boolean setPath(PathEntity var1, double var2) {
      if (var1 == null) {
         this.currentPath = null;
         return false;
      } else {
         if (!var1.isSamePath(this.currentPath)) {
            this.currentPath = var1;
         }

         this.removeSunnyPath();
         if (this.currentPath.getCurrentPathLength() == 0) {
            return false;
         } else {
            this.speed = var2;
            Vec3 var4 = this.getEntityPosition();
            this.ticksAtLastPos = this.totalTicks;
            this.lastPosCheck = var4;
            return true;
         }
      }
   }

   public abstract PathFinder getPathFinder();

   public boolean tryMoveToEntityLiving(Entity var1, double var2) {
      PathEntity var4 = this.getPathToEntityLiving(var1);
      return var4 != null ? this.setPath(var4, var2) : false;
   }

   public PathEntity getPathToPos(BlockPos var1) {
      if (!this.canNavigate()) {
         return null;
      } else {
         float var2 = this.getPathSearchRange();
         this.c.B.startSection("pathfind");
         BlockPos var3 = new BlockPos(this.b);
         int var4 = (int)(var2 + 8.0F);
         ChunkCache var5 = new ChunkCache(this.c, var3.add(-var4, -var4, -var4), var3.add(var4, var4, var4), 0);
         PathEntity var6 = this.pathFinder.createEntityPathTo(var5, this.b, var1, var2);
         this.c.B.endSection();
         return var6;
      }
   }

   public void pathFollow() {
      Vec3 var1 = this.getEntityPosition();
      int var2 = this.currentPath.getCurrentPathLength();

      for (int var3 = this.currentPath.getCurrentPathIndex(); var3 < this.currentPath.getCurrentPathLength(); var3++) {
         if (this.currentPath.getPathPointFromIndex(var3).yCoord != (int)var1.yCoord) {
            var2 = var3;
            break;
         }
      }

      float var8 = this.b.J * this.b.J * this.heightRequirement;

      for (int var4 = this.currentPath.getCurrentPathIndex(); var4 < var2; var4++) {
         Vec3 var5 = this.currentPath.getVectorFromIndex(this.b, var4);
         if (var1.squareDistanceTo(var5) < var8) {
            this.currentPath.setCurrentPathIndex(var4 + 1);
         }
      }

      int var9 = MathHelper.ceiling_float_int(this.b.J);
      int var10 = (int)this.b.K + 1;
      int var6 = var9;

      for (int var7 = var2 - 1; var7 >= this.currentPath.getCurrentPathIndex(); var7--) {
         if (this.isDirectPathBetweenPoints(var1, this.currentPath.getVectorFromIndex(this.b, var7), var9, var10, var6)) {
            this.currentPath.setCurrentPathIndex(var7);
            break;
         }
      }

      this.checkForStuck(var1);
   }

   public abstract boolean canNavigate();

   public void setHeightRequirement(float var1) {
      this.heightRequirement = var1;
   }

   public float getPathSearchRange() {
      return (float)this.pathSearchRange.getAttributeValue();
   }

   public PathEntity getPath() {
      return this.currentPath;
   }
}
