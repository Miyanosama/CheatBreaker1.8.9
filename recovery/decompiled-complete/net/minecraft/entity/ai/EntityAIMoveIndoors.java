package net.minecraft.entity.ai;

import net.minecraft.client.renderer.entity.layers.LayerDeadmau5Head;
import net.minecraft.client.renderer.tileentity.TileEntityChestRenderer;
import net.minecraft.creativetab.CreativeTabs$6;
import net.minecraft.entity.EntityCreature;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.village.Village;
import net.minecraft.village.VillageDoorInfo;
import net.minecraft.world.WorldProviderHell$1;
import net.optifine.config.NbtTagValue;

public class EntityAIMoveIndoors extends EntityAIBase {
   public EntityCreature entityObj;
   public int insidePosZ;
   public CreativeTabs$6 field_0003;
   public TileEntityChestRenderer field_0006;
   public int insidePosX = -1;
   public WorldProviderHell$1 field_0001;
   public NbtTagValue field_0008;
   public LayerDeadmau5Head field_0005;
   public VillageDoorInfo doorInfo;

   @Override
   public boolean continueExecuting() {
      return !this.entityObj.s().noPath();
   }

   @Override
   public void resetTask() {
      this.insidePosX = this.doorInfo.getInsideBlockPos().getX();
      this.insidePosZ = this.doorInfo.getInsideBlockPos().getZ();
      this.doorInfo = null;
   }

   public EntityAIMoveIndoors(EntityCreature var1) {
      this.insidePosZ = -1;
      this.entityObj = var1;
      this.setMutexBits(1);
   }

   @Override
   public void startExecuting() {
      this.insidePosX = -1;
      BlockPos var1 = this.doorInfo.getInsideBlockPos();
      int var2 = var1.getX();
      int var3 = var1.getY();
      int var4 = var1.getZ();
      if (this.entityObj.getDistanceSq(var1) > 256.0) {
         Vec3 var5 = RandomPositionGenerator.findRandomTargetBlockTowards(this.entityObj, 14, 3, new Vec3(var2 + 0.5, var3, var4 + 0.5));
         if (var5 != null) {
            this.entityObj.s().tryMoveToXYZ(var5.xCoord, var5.yCoord, var5.zCoord, 1.0);
         }
      } else {
         this.entityObj.s().tryMoveToXYZ(var2 + 0.5, var3, var4 + 0.5, 1.0);
      }
   }

   @Override
   public boolean shouldExecute() {
      BlockPos var1 = new BlockPos(this.entityObj);
      if ((!this.entityObj.o.isDaytime() || this.entityObj.o.isRaining() && !this.entityObj.o.getBiomeGenForCoords(var1).canRain())
         && !this.entityObj.o.t.getHasNoSky()) {
         if (this.entityObj.getRNG().nextInt(50) != 0) {
            return false;
         } else if (this.insidePosX != -1 && this.entityObj.e(this.insidePosX, this.entityObj.t, this.insidePosZ) < 4.0) {
            return false;
         } else {
            Village var2 = this.entityObj.o.getVillageCollection().getNearestVillage(var1, 14);
            if (var2 == null) {
               return false;
            } else {
               this.doorInfo = var2.getDoorInfo(var1);
               return this.doorInfo != null;
            }
         }
      } else {
         return false;
      }
   }
}
