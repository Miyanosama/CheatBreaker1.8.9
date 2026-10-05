package net.minecraft.entity.ai;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.entity.EntityCreature;
import net.minecraft.network.play.server.S45PacketTitle$Type;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.village.Village;
import net.minecraft.village.VillageDoorInfo;

public class EntityAIMoveThroughVillage extends EntityAIBase {
   public boolean isNocturnal;
   public S45PacketTitle$Type field_0005;
   public VillageDoorInfo doorInfo;
   public double movementSpeed;
   public List<VillageDoorInfo> doorList = Lists.newArrayList();
   public PathEntity entityPathNavigate;
   public EntityCreature theEntity;

   public VillageDoorInfo findNearestDoor(Village var1) {
      VillageDoorInfo var2 = null;
      int var3 = Integer.MAX_VALUE;

      for (VillageDoorInfo var5 : var1.getVillageDoorInfoList()) {
         int var6 = var5.getDistanceSquared(
            MathHelper.floor_double(this.theEntity.s), MathHelper.floor_double(this.theEntity.t), MathHelper.floor_double(this.theEntity.u)
         );
         if (var6 < var3 && !this.doesDoorListContain(var5)) {
            var2 = var5;
            var3 = var6;
         }
      }

      return var2;
   }

   public void resizeDoorList() {
      if (this.doorList.size() > 15) {
         this.doorList.remove(0);
      }
   }

   public boolean doesDoorListContain(VillageDoorInfo var1) {
      for (VillageDoorInfo var3 : this.doorList) {
         if (var1.getDoorBlockPos().equals(var3.getDoorBlockPos())) {
            return true;
         }
      }

      return false;
   }

   @Override
   public void startExecuting() {
      this.theEntity.s().setPath(this.entityPathNavigate, this.movementSpeed);
   }

   @Override
   public void resetTask() {
      if (this.theEntity.s().noPath() || this.theEntity.getDistanceSq(this.doorInfo.getDoorBlockPos()) < 16.0) {
         this.doorList.add(this.doorInfo);
      }
   }

   @Override
   public boolean continueExecuting() {
      if (this.theEntity.s().noPath()) {
         return false;
      } else {
         float var1 = this.theEntity.J + 4.0F;
         return this.theEntity.getDistanceSq(this.doorInfo.getDoorBlockPos()) > var1 * var1;
      }
   }

   public EntityAIMoveThroughVillage(EntityCreature var1, double var2, boolean var4) {
      this.theEntity = var1;
      this.movementSpeed = var2;
      this.isNocturnal = var4;
      this.setMutexBits(1);
      if (!(var1.s() instanceof PathNavigateGround)) {
         throw new IllegalArgumentException("Unsupported mob for MoveThroughVillageGoal");
      }
   }

   @Override
   public boolean shouldExecute() {
      this.resizeDoorList();
      if (this.isNocturnal && this.theEntity.o.isDaytime()) {
         return false;
      } else {
         Village var1 = this.theEntity.o.getVillageCollection().getNearestVillage(new BlockPos(this.theEntity), 0);
         if (var1 == null) {
            return false;
         } else {
            this.doorInfo = this.findNearestDoor(var1);
            if (this.doorInfo == null) {
               return false;
            } else {
               PathNavigateGround var2 = (PathNavigateGround)this.theEntity.s();
               boolean var3 = var2.getEnterDoors();
               var2.setBreakDoors(false);
               this.entityPathNavigate = var2.getPathToPos(this.doorInfo.getDoorBlockPos());
               var2.setBreakDoors(var3);
               if (this.entityPathNavigate != null) {
                  return true;
               } else {
                  Vec3 var4 = RandomPositionGenerator.findRandomTargetBlockTowards(
                     this.theEntity,
                     10,
                     7,
                     new Vec3(this.doorInfo.getDoorBlockPos().getX(), this.doorInfo.getDoorBlockPos().getY(), this.doorInfo.getDoorBlockPos().getZ())
                  );
                  if (var4 == null) {
                     return false;
                  } else {
                     var2.setBreakDoors(false);
                     this.entityPathNavigate = this.theEntity.s().getPathToXYZ(var4.xCoord, var4.yCoord, var4.zCoord);
                     var2.setBreakDoors(var3);
                     return this.entityPathNavigate != null;
                  }
               }
            }
         }
      }
   }
}
