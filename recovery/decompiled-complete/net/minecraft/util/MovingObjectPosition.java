package net.minecraft.util;

import net.minecraft.entity.Entity;

public class MovingObjectPosition {
   public Entity entityHit;
   public Vec3 hitVec;
   public EnumFacing sideHit;
   public BlockPos blockPos;
   public MovingObjectPosition$MovingObjectType typeOfHit;

   public MovingObjectPosition(Entity var1) {
      this(var1, new Vec3(var1.s, var1.t, var1.u));
   }

   @Override
   public String toString() {
      return "HitResult{type="
         + this.typeOfHit
         + ", blockpos="
         + this.blockPos
         + ", f="
         + this.sideHit
         + ", pos="
         + this.hitVec
         + ", entity="
         + this.entityHit
         + '}';
   }

   public MovingObjectPosition(Vec3 var1, EnumFacing var2, BlockPos var3) {
      this(MovingObjectPosition$MovingObjectType.BLOCK, var1, var2, var3);
   }

   public MovingObjectPosition(Vec3 var1, EnumFacing var2) {
      this(MovingObjectPosition$MovingObjectType.BLOCK, var1, var2, BlockPos.ORIGIN);
   }

   public MovingObjectPosition(MovingObjectPosition$MovingObjectType var1, Vec3 var2, EnumFacing var3, BlockPos var4) {
      this.typeOfHit = var1;
      this.blockPos = var4;
      this.sideHit = var3;
      this.hitVec = new Vec3(var2.xCoord, var2.yCoord, var2.zCoord);
   }

   public MovingObjectPosition(Entity var1, Vec3 var2) {
      this.typeOfHit = MovingObjectPosition$MovingObjectType.ENTITY;
      this.entityHit = var1;
      this.hitVec = var2;
   }

   public BlockPos getBlockPos() {
      return this.blockPos;
   }
}
