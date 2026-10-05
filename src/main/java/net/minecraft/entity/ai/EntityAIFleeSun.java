package net.minecraft.entity.ai;

import java.util.Random;
import net.minecraft.entity.EntityCreature;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityAIFleeSun extends EntityAIBase {
   public EntityCreature theCreature;
   public double shelterY;
   public World theWorld;
   public double shelterX;
   public double shelterZ;
   public double movementSpeed;

   @Override
   public boolean shouldExecute() {
      if (!this.theWorld.isDaytime()) {
         return false;
      } else if (!this.theCreature.isBurning()) {
         return false;
      } else if (!this.theWorld.canSeeSky(new BlockPos(this.theCreature.s, this.theCreature.getEntityBoundingBox().b, this.theCreature.u))) {
         return false;
      } else {
         Vec3 var1 = this.findPossibleShelter();
         if (var1 == null) {
            return false;
         } else {
            this.shelterX = var1.xCoord;
            this.shelterY = var1.yCoord;
            this.shelterZ = var1.zCoord;
            return true;
         }
      }
   }

   @Override
   public void startExecuting() {
      this.theCreature.s().tryMoveToXYZ(this.shelterX, this.shelterY, this.shelterZ, this.movementSpeed);
   }

   @Override
   public boolean continueExecuting() {
      return !this.theCreature.s().noPath();
   }

   public EntityAIFleeSun(EntityCreature var1, double var2) {
      this.theCreature = var1;
      this.movementSpeed = var2;
      this.theWorld = var1.o;
      this.setMutexBits(1);
   }

   public Vec3 findPossibleShelter() {
      Random var1 = this.theCreature.getRNG();
      BlockPos var2 = new BlockPos(this.theCreature.s, this.theCreature.getEntityBoundingBox().b, this.theCreature.u);

      for (int var3 = 0; var3 < 10; var3++) {
         BlockPos var4 = var2.add(var1.nextInt(20) - 10, var1.nextInt(6) - 3, var1.nextInt(20) - 10);
         if (!this.theWorld.canSeeSky(var4) && this.theCreature.getBlockPathWeight(var4) < 0.0F) {
            return new Vec3(var4.getX(), var4.getY(), var4.getZ());
         }
      }

      return null;
   }
}
