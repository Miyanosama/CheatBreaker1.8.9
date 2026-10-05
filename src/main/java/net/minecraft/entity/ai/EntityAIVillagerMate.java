package net.minecraft.entity.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.BlockPos;
import net.minecraft.village.Village;
import net.minecraft.world.World;

public class EntityAIVillagerMate extends EntityAIBase {
   public EntityVillager villagerObj;
   public Village villageObj;
   public World worldObj;
   public int matingTimeout;
   public EntityVillager mate;

   @Override
   public void resetTask() {
      this.villageObj = null;
      this.mate = null;
      this.villagerObj.setMating(false);
   }

   public EntityAIVillagerMate(EntityVillager var1) {
      this.villagerObj = var1;
      this.worldObj = var1.o;
      this.setMutexBits(3);
   }

   @Override
   public void startExecuting() {
      this.matingTimeout = 300;
      this.villagerObj.setMating(true);
   }

   public void giveBirth() {
      EntityVillager var1 = this.villagerObj.createChild(this.mate);
      this.mate.setGrowingAge(6000);
      this.villagerObj.setGrowingAge(6000);
      this.mate.setIsWillingToMate(false);
      this.villagerObj.setIsWillingToMate(false);
      var1.setGrowingAge(-24000);
      var1.a_(this.villagerObj.s, this.villagerObj.t, this.villagerObj.u, 0.0F, 0.0F);
      this.worldObj.spawnEntityInWorld(var1);
      this.worldObj.setEntityState(var1, (byte)12);
   }

   @Override
   public void updateTask() {
      this.matingTimeout--;
      this.villagerObj.getLookHelper().setLookPositionWithEntity(this.mate, 10.0F, 30.0F);
      if (this.villagerObj.h(this.mate) > 2.25) {
         this.villagerObj.s().tryMoveToEntityLiving(this.mate, 0.25);
      } else if (this.matingTimeout == 0 && this.mate.isMating()) {
         this.giveBirth();
      }

      if (this.villagerObj.getRNG().nextInt(35) == 0) {
         this.worldObj.setEntityState(this.villagerObj, (byte)12);
      }
   }

   @Override
   public boolean continueExecuting() {
      return this.matingTimeout >= 0
         && this.checkSufficientDoorsPresentForNewVillager()
         && this.villagerObj.l() == 0
         && this.villagerObj.getIsWillingToMate(false);
   }

   public boolean checkSufficientDoorsPresentForNewVillager() {
      if (!this.villageObj.isMatingSeason()) {
         return false;
      } else {
         int var1 = (int)(this.villageObj.getNumVillageDoors() * 0.35);
         return this.villageObj.getNumVillagers() < var1;
      }
   }

   @Override
   public boolean shouldExecute() {
      if (this.villagerObj.l() != 0) {
         return false;
      } else if (this.villagerObj.getRNG().nextInt(500) != 0) {
         return false;
      } else {
         this.villageObj = this.worldObj.getVillageCollection().getNearestVillage(new BlockPos(this.villagerObj), 0);
         if (this.villageObj == null) {
            return false;
         } else if (this.checkSufficientDoorsPresentForNewVillager() && this.villagerObj.getIsWillingToMate(true)) {
            Entity var1 = this.worldObj
               .findNearestEntityWithinAABB(EntityVillager.class, this.villagerObj.getEntityBoundingBox().expand(8.0, 3.0, 8.0), this.villagerObj);
            if (var1 == null) {
               return false;
            } else {
               this.mate = (EntityVillager)var1;
               return this.mate.l() == 0 && this.mate.getIsWillingToMate(true);
            }
         } else {
            return false;
         }
      }
   }
}
