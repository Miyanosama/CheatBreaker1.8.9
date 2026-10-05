package net.minecraft.entity.ai;

import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.Vec3;

public class EntityAIPlay extends EntityAIBase {
   public int playTime;
   public double speed;
   public EntityVillager villagerObj;
   public EntityLivingBase targetVillager;

   public EntityAIPlay(EntityVillager var1, double var2) {
      this.villagerObj = var1;
      this.speed = var2;
      this.setMutexBits(1);
   }

   @Override
   public void resetTask() {
      this.villagerObj.setPlaying(false);
      this.targetVillager = null;
   }

   @Override
   public void updateTask() {
      this.playTime--;
      if (this.targetVillager != null) {
         if (this.villagerObj.h(this.targetVillager) > 4.0) {
            this.villagerObj.s().tryMoveToEntityLiving(this.targetVillager, this.speed);
         }
      } else if (this.villagerObj.s().noPath()) {
         Vec3 var1 = RandomPositionGenerator.findRandomTarget(this.villagerObj, 16, 3);
         if (var1 == null) {
            return;
         }

         this.villagerObj.s().tryMoveToXYZ(var1.xCoord, var1.yCoord, var1.zCoord, this.speed);
      }
   }

   @Override
   public boolean continueExecuting() {
      return this.playTime > 0;
   }

   @Override
   public boolean shouldExecute() {
      if (this.villagerObj.l() >= 0) {
         return false;
      } else if (this.villagerObj.getRNG().nextInt(400) != 0) {
         return false;
      } else {
         List var1 = this.villagerObj.o.getEntitiesWithinAABB(EntityVillager.class, this.villagerObj.getEntityBoundingBox().expand(6.0, 3.0, 6.0));
         double var2 = Double.MAX_VALUE;

         for (EntityVillager var5 : (Iterable<EntityVillager>)(Iterable<?>)(var1)) {
            if (var5 != this.villagerObj && !var5.method_01070() && var5.l() < 0) {
               double var6 = var5.h(this.villagerObj);
               if (var6 <= var2) {
                  var2 = var6;
                  this.targetVillager = var5;
               }
            }
         }

         if (this.targetVillager == null) {
            Vec3 var8 = RandomPositionGenerator.findRandomTarget(this.villagerObj, 16, 3);
            if (var8 == null) {
               return false;
            }
         }

         return true;
      }
   }

   @Override
   public void startExecuting() {
      if (this.targetVillager != null) {
         this.villagerObj.setPlaying(true);
      }

      this.playTime = 1000;
   }
}
