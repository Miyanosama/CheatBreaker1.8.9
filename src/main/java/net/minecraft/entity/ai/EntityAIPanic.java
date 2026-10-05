package net.minecraft.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.util.Vec3;

public class EntityAIPanic extends EntityAIBase {
   public double randPosY;
   public double randPosZ;
   public double a;
   public double randPosX;
   public EntityCreature theEntityCreature;

   @Override
   public boolean shouldExecute() {
      if (this.theEntityCreature.getAITarget() == null && !this.theEntityCreature.isBurning()) {
         return false;
      } else {
         Vec3 var1 = RandomPositionGenerator.findRandomTarget(this.theEntityCreature, 5, 4);
         if (var1 == null) {
            return false;
         } else {
            this.randPosX = var1.xCoord;
            this.randPosY = var1.yCoord;
            this.randPosZ = var1.zCoord;
            return true;
         }
      }
   }

   @Override
   public boolean continueExecuting() {
      return !this.theEntityCreature.s().noPath();
   }

   public EntityAIPanic(EntityCreature var1, double var2) {
      this.theEntityCreature = var1;
      this.a = var2;
      this.setMutexBits(1);
   }

   @Override
   public void startExecuting() {
      this.theEntityCreature.s().tryMoveToXYZ(this.randPosX, this.randPosY, this.randPosZ, this.a);
   }
}
