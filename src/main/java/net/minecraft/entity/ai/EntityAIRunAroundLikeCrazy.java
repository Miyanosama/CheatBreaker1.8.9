package net.minecraft.entity.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;

public class EntityAIRunAroundLikeCrazy extends EntityAIBase {
   public double speed;
   public double targetX;
   public double targetZ;
   public EntityHorse horseHost;
   public double targetY;

   @Override
   public void startExecuting() {
      this.horseHost.s().tryMoveToXYZ(this.targetX, this.targetY, this.targetZ, this.speed);
   }

   @Override
   public boolean shouldExecute() {
      if (!this.horseHost.isTame() && this.horseHost.l != null) {
         Vec3 var1 = RandomPositionGenerator.findRandomTarget(this.horseHost, 5, 4);
         if (var1 == null) {
            return false;
         } else {
            this.targetX = var1.xCoord;
            this.targetY = var1.yCoord;
            this.targetZ = var1.zCoord;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public boolean continueExecuting() {
      return !this.horseHost.s().noPath() && this.horseHost.l != null;
   }

   @Override
   public void updateTask() {
      if (this.horseHost.getRNG().nextInt(50) == 0) {
         if (this.horseHost.l instanceof EntityPlayer) {
            int var1 = this.horseHost.getTemper();
            int var2 = this.horseHost.getMaxTemper();
            if (var2 > 0 && this.horseHost.getRNG().nextInt(var2) < var1) {
               this.horseHost.setTamedBy((EntityPlayer)this.horseHost.l);
               this.horseHost.o.setEntityState(this.horseHost, (byte)7);
               return;
            }

            this.horseHost.increaseTemper(5);
         }

         this.horseHost.l.mountEntity((Entity)null);
         this.horseHost.l = null;
         this.horseHost.makeHorseRearWithSound();
         this.horseHost.o.setEntityState(this.horseHost, (byte)6);
      }
   }

   public EntityAIRunAroundLikeCrazy(EntityHorse var1, double var2) {
      this.horseHost = var1;
      this.speed = var2;
      this.setMutexBits(1);
   }
}
