package net.minecraft.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.village.Village;
import net.minecraft.village.VillageDoorInfo;

public class EntityAIRestrictOpenDoor extends EntityAIBase {
   public EntityCreature entityObj;
   public VillageDoorInfo frontDoor;

   @Override
   public boolean continueExecuting() {
      return this.entityObj.o.isDaytime()
         ? false
         : !this.frontDoor.getIsDetachedFromVillageFlag() && this.frontDoor.func_179850_c(new BlockPos(this.entityObj));
   }

   @Override
   public boolean shouldExecute() {
      if (this.entityObj.o.isDaytime()) {
         return false;
      } else {
         BlockPos var1 = new BlockPos(this.entityObj);
         Village var2 = this.entityObj.o.getVillageCollection().getNearestVillage(var1, 16);
         if (var2 == null) {
            return false;
         } else {
            this.frontDoor = var2.getNearestDoor(var1);
            return this.frontDoor == null ? false : this.frontDoor.getDistanceToInsideBlockSq(var1) < 2.25;
         }
      }
   }

   public EntityAIRestrictOpenDoor(EntityCreature var1) {
      this.entityObj = var1;
      if (!(var1.s() instanceof PathNavigateGround)) {
         throw new IllegalArgumentException("Unsupported mob type for RestrictOpenDoorGoal");
      }
   }

   @Override
   public void updateTask() {
      this.frontDoor.incrementDoorOpeningRestrictionCounter();
   }

   @Override
   public void startExecuting() {
      ((PathNavigateGround)this.entityObj.s()).setBreakDoors(false);
      ((PathNavigateGround)this.entityObj.s()).setEnterDoors(false);
   }

   @Override
   public void resetTask() {
      ((PathNavigateGround)this.entityObj.s()).setBreakDoors(true);
      ((PathNavigateGround)this.entityObj.s()).setEnterDoors(true);
      this.frontDoor = null;
   }
}
