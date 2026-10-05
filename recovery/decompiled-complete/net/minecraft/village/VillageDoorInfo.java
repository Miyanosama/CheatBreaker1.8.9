package net.minecraft.village;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.optifine.util.CacheObjectArray;

public class VillageDoorInfo {
   public int lastActivityTimestamp;
   public boolean isDetachedFromVillageFlag;
   public EntityCreeper field_0003;
   public GuiContainer field_0006;
   public BlockPos doorBlockPos;
   public CacheObjectArray field_0001;
   public int doorOpeningRestrictionCounter;
   public EnumFacing insideDirection;
   public BlockPos insideBlock;

   public boolean func_179850_c(BlockPos var1) {
      int var2 = var1.getX() - this.doorBlockPos.getX();
      int var3 = var1.getZ() - this.doorBlockPos.getY();
      return var2 * this.insideDirection.getFrontOffsetX() + var3 * this.insideDirection.getFrontOffsetZ() >= 0;
   }

   public int getInsidePosY() {
      return this.lastActivityTimestamp;
   }

   public void resetDoorOpeningRestrictionCounter() {
      this.doorOpeningRestrictionCounter = 0;
   }

   public int getDistanceToInsideBlockSq(BlockPos var1) {
      return (int)this.insideBlock.distanceSq(var1);
   }

   public int getDistanceSquared(int var1, int var2, int var3) {
      return (int)this.doorBlockPos.distanceSq(var1, var2, var3);
   }

   public BlockPos getDoorBlockPos() {
      return this.doorBlockPos;
   }

   public int getInsideOffsetZ() {
      return this.insideDirection.getFrontOffsetZ() * 2;
   }

   public int getDistanceToDoorBlockSq(BlockPos var1) {
      return (int)var1.distanceSq(this.getDoorBlockPos());
   }

   public void func_179849_a(int var1) {
      this.lastActivityTimestamp = var1;
   }

   public void incrementDoorOpeningRestrictionCounter() {
      this.doorOpeningRestrictionCounter++;
   }

   public int getInsideOffsetX() {
      return this.insideDirection.getFrontOffsetX() * 2;
   }

   public void setIsDetachedFromVillageFlag(boolean var1) {
      this.isDetachedFromVillageFlag = var1;
   }

   public VillageDoorInfo(BlockPos var1, EnumFacing var2, int var3) {
      this.doorBlockPos = var1;
      this.insideDirection = var2;
      this.insideBlock = var1.a(var2, 2);
      this.lastActivityTimestamp = var3;
   }

   public VillageDoorInfo(BlockPos var1, int var2, int var3, int var4) {
      this(var1, getFaceDirection(var2, var3), var4);
   }

   public BlockPos getInsideBlockPos() {
      return this.insideBlock;
   }

   public static EnumFacing getFaceDirection(int var0, int var1) {
      return var0 < 0 ? EnumFacing.WEST : (var0 > 0 ? EnumFacing.EAST : (var1 < 0 ? EnumFacing.NORTH : EnumFacing.SOUTH));
   }

   public int getDoorOpeningRestrictionCounter() {
      return this.doorOpeningRestrictionCounter;
   }

   public boolean getIsDetachedFromVillageFlag() {
      return this.isDetachedFromVillageFlag;
   }
}
