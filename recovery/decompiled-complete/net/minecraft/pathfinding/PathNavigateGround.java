package net.minecraft.pathfinding;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.pathfinder.WalkNodeProcessor;
import net.optifine.entity.model.ModelAdapterHorse;

public class PathNavigateGround extends PathNavigate {
   public ModelAdapterHorse field_0000;
   public WalkNodeProcessor nodeProcessor;
   public boolean shouldAvoidSun;

   public boolean method_12085(int var1, int var2, int var3, int var4, int var5, int var6, Vec3 var7, double var8, double var10) {
      for (BlockPos var13 : BlockPos.getAllInBox(new BlockPos(var1, var2, var3), new BlockPos(var1 + var4 - 1, var2 + var5 - 1, var3 + var6 - 1))) {
         double var14 = var13.getX() + 0.5 - var7.xCoord;
         double var16 = var13.getZ() + 0.5 - var7.zCoord;
         if (var14 * var8 + var16 * var10 >= 0.0) {
            Block var18 = this.c.getBlockState(var13).getBlock();
            if (!var18.isPassable(this.c, var13)) {
               return false;
            }
         }
      }

      return true;
   }

   @Override
   public Vec3 getEntityPosition() {
      return new Vec3(this.b.s, this.getPathablePosY(), this.b.u);
   }

   public void setEnterDoors(boolean var1) {
      this.nodeProcessor.setEnterDoors(var1);
   }

   public boolean getAvoidsWater() {
      return this.nodeProcessor.getAvoidsWater();
   }

   public boolean getCanSwim() {
      return this.nodeProcessor.getCanSwim();
   }

   public void setCanSwim(boolean var1) {
      this.nodeProcessor.setCanSwim(var1);
   }

   @Override
   public boolean isDirectPathBetweenPoints(Vec3 var1, Vec3 var2, int var3, int var4, int var5) {
      int var6 = MathHelper.floor_double(var1.xCoord);
      int var7 = MathHelper.floor_double(var1.zCoord);
      double var8 = var2.xCoord - var1.xCoord;
      double var10 = var2.zCoord - var1.zCoord;
      double var12 = var8 * var8 + var10 * var10;
      if (var12 < 1.0E-8) {
         return false;
      } else {
         double var14 = 1.0 / Math.sqrt(var12);
         var8 *= var14;
         var10 *= var14;
         var3 += 2;
         var5 += 2;
         if (!this.method_12091(var6, (int)var1.yCoord, var7, var3, var4, var5, var1, var8, var10)) {
            return false;
         } else {
            var3 -= 2;
            var5 -= 2;
            double var16 = 1.0 / Math.abs(var8);
            double var18 = 1.0 / Math.abs(var10);
            double var20 = var6 * 1 - var1.xCoord;
            double var22 = var7 * 1 - var1.zCoord;
            if (var8 >= 0.0) {
               var20++;
            }

            if (var10 >= 0.0) {
               var22++;
            }

            var20 /= var8;
            var22 /= var10;
            int var24 = var8 < 0.0 ? -1 : 1;
            int var25 = var10 < 0.0 ? -1 : 1;
            int var26 = MathHelper.floor_double(var2.xCoord);
            int var27 = MathHelper.floor_double(var2.zCoord);
            int var28 = var26 - var6;
            int var29 = var27 - var7;

            while (var28 * var24 > 0 || var29 * var25 > 0) {
               if (var20 < var22) {
                  var20 += var16;
                  var6 += var24;
                  var28 = var26 - var6;
               } else {
                  var22 += var18;
                  var7 += var25;
                  var29 = var27 - var7;
               }

               if (!this.method_12091(var6, (int)var1.yCoord, var7, var3, var4, var5, var1, var8, var10)) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   @Override
   public boolean canNavigate() {
      return this.b.C || this.getCanSwim() && this.o() || this.b.au() && this.b instanceof EntityZombie && this.b.m instanceof EntityChicken;
   }

   public void setAvoidsWater(boolean var1) {
      this.nodeProcessor.setAvoidsWater(var1);
   }

   @Override
   public PathFinder getPathFinder() {
      this.nodeProcessor = new WalkNodeProcessor();
      this.nodeProcessor.setEnterDoors(true);
      return new PathFinder(this.nodeProcessor);
   }

   public void setAvoidSun(boolean var1) {
      this.shouldAvoidSun = var1;
   }

   public void setBreakDoors(boolean var1) {
      this.nodeProcessor.setBreakDoors(var1);
   }

   public PathNavigateGround(EntityLiving var1, World var2) {
      super(var1, var2);
   }

   public int getPathablePosY() {
      if (this.b.V() && this.getCanSwim()) {
         int var1 = (int)this.b.getEntityBoundingBox().b;
         Block var2 = this.c.getBlockState(new BlockPos(MathHelper.floor_double(this.b.s), var1, MathHelper.floor_double(this.b.u))).getBlock();
         int var3 = 0;

         while (var2 == Blocks.flowing_water || var2 == Blocks.water) {
            var2 = this.c.getBlockState(new BlockPos(MathHelper.floor_double(this.b.s), ++var1, MathHelper.floor_double(this.b.u))).getBlock();
            if (++var3 > 16) {
               return (int)this.b.getEntityBoundingBox().b;
            }
         }

         return var1;
      } else {
         return (int)(this.b.getEntityBoundingBox().b + 0.5);
      }
   }

   public boolean getEnterDoors() {
      return this.nodeProcessor.getEnterDoors();
   }

   @Override
   public void removeSunnyPath() {
      super.removeSunnyPath();
      if (this.shouldAvoidSun) {
         if (this.c.canSeeSky(new BlockPos(MathHelper.floor_double(this.b.s), (int)(this.b.getEntityBoundingBox().b + 0.5), MathHelper.floor_double(this.b.u)))
            )
          {
            return;
         }

         for (int var1 = 0; var1 < this.currentPath.getCurrentPathLength(); var1++) {
            PathPoint var2 = this.currentPath.getPathPointFromIndex(var1);
            if (this.c.canSeeSky(new BlockPos(var2.xCoord, var2.yCoord, var2.zCoord))) {
               this.currentPath.setCurrentPathLength(var1 - 1);
               return;
            }
         }
      }
   }

   public boolean method_12091(int var1, int var2, int var3, int var4, int var5, int var6, Vec3 var7, double var8, double var10) {
      int var12 = var1 - var4 / 2;
      int var13 = var3 - var6 / 2;
      if (!this.method_12085(var12, var2, var13, var4, var5, var6, var7, var8, var10)) {
         return false;
      } else {
         for (int var14 = var12; var14 < var12 + var4; var14++) {
            for (int var15 = var13; var15 < var13 + var6; var15++) {
               double var16 = var14 + 0.5 - var7.xCoord;
               double var18 = var15 + 0.5 - var7.zCoord;
               if (var16 * var8 + var18 * var10 >= 0.0) {
                  Block var20 = this.c.getBlockState(new BlockPos(var14, var2 - 1, var15)).getBlock();
                  Material var21 = var20.getMaterial();
                  if (var21 == Material.air) {
                     return false;
                  }

                  if (var21 == Material.water && !this.b.V()) {
                     return false;
                  }

                  if (var21 == Material.lava) {
                     return false;
                  }
               }
            }
         }

         return true;
      }
   }
}
