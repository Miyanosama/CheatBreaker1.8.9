package net.minecraft.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ITickable;

public class TileEntityEnderChest extends TileEntity implements ITickable {
   public float prevLidAngle;
   public int numPlayersUsing;
   public int ticksSinceSync;
   public float lidAngle;

   public boolean canBeUsed(EntityPlayer var1) {
      return this.b.getTileEntity(this.c) != this ? false : var1.e(this.c.getX() + 0.5, this.c.getY() + 0.5, this.c.getZ() + 0.5) <= 64.0;
   }

   @Override
   public void invalidate() {
      this.updateContainingBlockInfo();
      super.invalidate();
   }

   @Override
   public boolean receiveClientEvent(int var1, int var2) {
      if (var1 == 1) {
         this.numPlayersUsing = var2;
         return true;
      } else {
         return super.receiveClientEvent(var1, var2);
      }
   }

   @Override
   public void update() {
      if (++this.ticksSinceSync % 20 * 4 == 0) {
         this.b.addBlockEvent(this.c, Blocks.ender_chest, 1, this.numPlayersUsing);
      }

      this.prevLidAngle = this.lidAngle;
      int var1 = this.c.getX();
      int var2 = this.c.getY();
      int var3 = this.c.getZ();
      float var4 = 0.1F;
      if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F) {
         double var5 = var1 + 0.5;
         double var7 = var3 + 0.5;
         this.b.playSoundEffect(var5, var2 + 0.5, var7, "random.chestopen", 0.5F, this.b.s.nextFloat() * 0.1F + 0.9F);
      }

      if (this.numPlayersUsing == 0 && this.lidAngle > 0.0F || this.numPlayersUsing > 0 && this.lidAngle < 1.0F) {
         float var11 = this.lidAngle;
         if (this.numPlayersUsing > 0) {
            this.lidAngle += var4;
         } else {
            this.lidAngle -= var4;
         }

         if (this.lidAngle > 1.0F) {
            this.lidAngle = 1.0F;
         }

         float var6 = 0.5F;
         if (this.lidAngle < var6 && var11 >= var6) {
            double var12 = var1 + 0.5;
            double var9 = var3 + 0.5;
            this.b.playSoundEffect(var12, var2 + 0.5, var9, "random.chestclosed", 0.5F, this.b.s.nextFloat() * 0.1F + 0.9F);
         }

         if (this.lidAngle < 0.0F) {
            this.lidAngle = 0.0F;
         }
      }
   }

   public void closeChest() {
      this.numPlayersUsing--;
      this.b.addBlockEvent(this.c, Blocks.ender_chest, 1, this.numPlayersUsing);
   }

   public void openChest() {
      this.numPlayersUsing++;
      this.b.addBlockEvent(this.c, Blocks.ender_chest, 1, this.numPlayersUsing);
   }
}
