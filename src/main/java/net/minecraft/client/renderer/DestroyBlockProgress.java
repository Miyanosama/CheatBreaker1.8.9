package net.minecraft.client.renderer;

import net.minecraft.util.BlockPos;

public class DestroyBlockProgress {
   public int createdAtCloudUpdateTick;
   public BlockPos position;
   public int miningPlayerEntId;
   public int partialBlockProgress;

   public BlockPos getPosition() {
      return this.position;
   }

   public void setCloudUpdateTick(int var1) {
      this.createdAtCloudUpdateTick = var1;
   }

   public int getPartialBlockDamage() {
      return this.partialBlockProgress;
   }

   public DestroyBlockProgress(int var1, BlockPos var2) {
      this.miningPlayerEntId = var1;
      this.position = var2;
   }

   public void setPartialBlockDamage(int var1) {
      if (var1 > 10) {
         var1 = 10;
      }

      this.partialBlockProgress = var1;
   }

   public int getCreationCloudUpdateTick() {
      return this.createdAtCloudUpdateTick;
   }
}
